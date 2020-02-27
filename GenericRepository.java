package com.codigo.aplios.group.database.repository;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;

import com.codigo.aplios.group.database.models.location.EntityModel;

//https://www.logicbig.com/tutorials/java-ee-tutorial/jpa/optimistic-lock-force-increment-use-case.html
//optimistic lock

// @PersistenceContext(synchronization = SynchronizationType.UNSYNCHRONIZED,
// type = PersistenceContextType.EXTENDED)
//http://websystique.com/spring-security/secure-spring-rest-api-using-basic-authentication/
public class GenericRepository<T extends EntityModel> implements IRepository<T>, Closeable {

	private static final Logger LOG = Logger.getLogger(GenericRepository.class);
	private final EntityManagerFactory entityManagerFactory;
	private EntityManager enityManager;
	private final Class<T> entityClass;
	private final boolean isAutoCommit;

	/**
	 * Podstawowy konstruktor obiektu klasy <code>GenericRepository<T></code>
	 *
	 * @param entityClass Parametr typu encji
	 * @param entityStoreName Nazwa bazy danych
	 */
	public GenericRepository(final Class<T> entityClass, final String entityStoreName) {

		this(entityClass, entityStoreName, false);
	}

	public GenericRepository(final Class<T> entityClass, final String entityStoreName,
			final boolean isAutoCommit) {

		this(entityClass, entityStoreName, isAutoCommit, false);
	}

	public GenericRepository(final Class<T> entityClass, final String entityStoreName,
			final boolean isAutoCommit, final boolean dd) {

		this.entityClass = entityClass;
		this.isAutoCommit = false;
		this.entityManagerFactory = Persistence.createEntityManagerFactory(entityStoreName);
		this.enityManager = this.entityManagerFactory.createEntityManager();
		this.enityManager.setFlushMode(FlushModeType.COMMIT);
	}

	public List<T> selectById(final Long entityKey) {

		final EntityManager em = this.getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<T> query = cb.createQuery(this.entityClass);

		final Root<T> c = query.from(this.entityClass);
		final ParameterExpression<Long> p = cb.parameter(Long.class,
				"id");

		query.select(c)
				.where(cb.equal(c.get("id"),
						p));

		return em.createQuery(query)
				.setParameter("id",
						entityKey)
				.getResultList();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Set<T> select() {

		return this.runInTransaction(entityManager -> {

			final TypedQuery<T> query = this.createTypedQuery((cb, r, q) -> q);
			query.setLockMode(LockModeType.PESSIMISTIC_READ);

			try {

				return Collections.unmodifiableSet(query.getResultList()
						.stream()
						.collect(Collectors.toSet()));

			} catch (final Exception exception) {
				LOG.error(exception);
				return Collections.emptySet();
			}
		});
	}

	// -----------------------------------------------------------------------------------------------------------------

	@Override
	public boolean insert(final T entity) {

		final Consumer<EntityManager> sqlCommand = entityManager -> {

			if (isNull(entity.getId()))
				entityManager.persist((entity));

			else if (nonNull(entityManager.find(this.entityClass,
					entity.getId())))
				entityManager.merge((entity));
		};

		try {

			return this.runInTransaction(sqlCommand);

		} catch (final Exception exception) {

			LOG.error(exception);
			return false;
		}
	}

	// -----------------------------------------------------------------------------------------------------------------
	@Override
	public void insert(final Iterable<T> entities) {

		this.runInTransaction(entityManager -> {
			entities.forEach(this::insert);
		});
	}

	// -----------------------------------------------------------------------------------------------------------------
	@Override
	public void delete(final T entity) {

		this.delete(entity.getId());
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Long entityKey) {

		this.runInTransaction(entityManager -> {

			final T managedEntity =
					entityManager.find(this.entityClass,
							entityKey,
							LockModeType.NONE);

			if (nonNull(managedEntity))
				entityManager.remove(managedEntity);
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long deleteFrom(final Iterable<T> entities) {

		return this.runInTransaction(entityManager -> {

			var entitiesStream = StreamSupport.stream(entities.spliterator(),
					false);

			if (entitiesStream.count() <= 0L)
				return 0L;

			final CriteriaBuilder cb = entityManager.getCriteriaBuilder();

			final CriteriaDelete<T> delete = cb.createCriteriaDelete(this.entityClass);

			final Root<T> e = delete.from(this.entityClass);

			entitiesStream = StreamSupport.stream(entities.spliterator(),
					false);
			delete.where(e.in(entitiesStream.collect(Collectors.toList())));

			return Long.valueOf(entityManager.createQuery(delete)
					.executeUpdate());
		});
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long deleteAll() {

		return (this.runInTransaction(entityManager -> {

			final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			final CriteriaDelete<T> query = criteriaBuilder.createCriteriaDelete(this.entityClass);

			return entityManager.createQuery(query)
					.executeUpdate();

		})).longValue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public long count() {

		final EntityManager em = this.getEntityManager();

		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> query = cb.createQuery(Long.class);

		final Root<T> root = query.from(this.entityClass);

		query.select(cb.count(root));

		return em.createQuery(query)
				.getSingleResult();
	}

	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(final Iterable<T> entities) {

		this.runInTransaction(entityManager -> {

			StreamSupport.stream(entities.spliterator(),
					false)
					.map(T::getId)
					.map(id -> entityManager.find(this.entityClass,
							id))
					.filter(Objects::nonNull)
					.forEach(entityManager::remove);
		});
	}

	private <R extends Object> R runInTransaction(final Function<EntityManager, R> sqlCommand) {

		final EntityManager entityManager = this.getEntityManager();
		final EntityTransaction transaction = entityManager.getTransaction();

		final R result;

		if (!transaction.isActive())
			transaction.begin();

		try {

			result = sqlCommand.apply(entityManager);

			if (this.isAutoCommit())
				transaction.commit();

			return result;

		} catch (final RollbackException exception) {

			if ((transaction.isActive()))
				transaction.rollback();

			LOG.error("Wystąpił problem podczas zatwierdzania aktywnej transakcji");
			LOG.error(exception.getMessage());

		} catch (final Exception exception) {

			if (transaction.isActive())
				transaction.rollback();

			LOG.error("Wystąpił problem podczas zatwierdzania aktywnej transakcji");
			LOG.error(exception.getMessage());
		}

		return (R) Function.identity();
	}

	// -----------------------------------------------------------------------------------------------------------------
	private boolean runInTransaction(final Consumer<EntityManager> sqlCommand) {

		final Function<EntityManager, Boolean> sqlTransaction = entityManager -> {

			final EntityTransaction tran = entityManager.getTransaction();

			if (!tran.isActive())
				tran.begin();

			try {

				sqlCommand.accept(entityManager);

				if (this.isAutoCommit())
					tran.commit();

			} catch (final Exception ex) {

				if ((tran.isActive()))
					tran.rollback();

				LOG.error(ex);
				return false;
			}

			return true;
		};

		return this.run(sqlTransaction);
	}

	// -----------------------------------------------------------------------------------------------------------------
	private <R extends Object> R run(final Function<EntityManager, R> sqlCommand) {

		final EntityManager entityManager = this.getEntityManager();

		try {

			return sqlCommand.apply(entityManager);

		} catch (final Exception exception) {

			LOG.error(exception);
			return (R) Function.identity();
		}
	}

	private TypedQuery<T> createTypedQuery(final IQueryable<T> queryBuilder) {

		final EntityManager entityManager = this.getEntityManager();
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<T> query = builder.createQuery(this.entityClass);
		final Root<T> root = query.from(this.entityClass);

		CriteriaQuery<T> criteriaQuery = query.select(root);
		criteriaQuery = queryBuilder.build(builder,
				root,
				criteriaQuery);

		final TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
		typedQuery.setFlushMode(FlushModeType.COMMIT);

		return typedQuery;
	}

	// -----------------------------------------------------------------------------------------------------------------
	@Override
	public boolean isAutoCommit() {

		return this.isAutoCommit;
	}

	// -----------------------------------------------------------------------------------------------------------------
	@Override
	public void update(final T entity) {

		this.runInTransaction(entityManager -> {
			entityManager.merge((entity));
		});
	}

	// ------------------------------------------------------------------------------------------------------------------
	@Override
	public void update(final Iterable<T> entities) {

		this.runInTransaction(entityManager -> {
			entities.forEach(this::update);
		});
	}

	protected EntityManager getEntityManager() {

		if (isNull(this.enityManager))
			this.enityManager = this.entityManagerFactory.createEntityManager();

		if (!this.enityManager.isOpen())
			this.enityManager = this.entityManagerFactory.createEntityManager();

		this.enityManager.setFlushMode(FlushModeType.COMMIT);
		return this.enityManager;
	}

	protected EntityManager getNewEntityManager() throws Exception {

		this.enityManager = this.entityManagerFactory.createEntityManager();

		if (isNull(this.enityManager))
			throw new NullPointerException("EntityManager instance has not been created!");

		if (!this.enityManager.isOpen())
			throw new IllegalStateException("EntityManager instance has not been opened!");

		this.enityManager.setFlushMode(FlushModeType.COMMIT);
		return this.enityManager;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public List<T> finda(final IQueryable<T> queryBuilder) {

		return Collections.unmodifiableList(this.createQuery(queryBuilder)
				.getResultList());
	}
	// https://github.com/janhalasa/JpaCriteriaWithLambdaExpressions/blob/master/src/main/java/com/halasa/criterialambda/dao/builder/QueryBuilder.java

	// -----------------------------------------------------------------------------------------------------------------
	protected TypedQuery<T> createQuery(final IQueryable<T> queryBuilder) {

		return this.createTypedQuery(queryBuilder);
	}

	// -----------------------------------------------------------------------------------------------------------------
	public List<T> find(final IQueryable<T> queryBuilder) {

		return Collections.unmodifiableList(this.createQuery(queryBuilder)
				.getResultList());
	}

	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * @param predicateBuilders Restricting query conditions. If you supply more
	 *        than one predicate, they will be joined by conjunction.
	 * @return
	 *
	 */
	protected List<T> findWhere(final IPredicateBuilder<T>[] predicateBuilders) {

		return Collections.unmodifiableList(this
				.createTypedQuery((cb, root, query) -> (query.where(this.buildPredicates(cb,
						root,
						predicateBuilders))))
				.getResultList());
	}

	// -----------------------------------------------------------------------------------------------------------------
	//
	// /**
	// * @param predicateBuilders
	// * Restricting query conditions. If you supply more than one predicate, they
	// will be
	// * joined by conjunction.
	// */
	protected T where(final IPredicateBuilder<T>[] predicateBuilders) {

		return this
				.createTypedQuery((cb, root, query) -> (query.where(this.buildPredicates(cb,
						root,
						predicateBuilders))))
				.getSingleResult();
	}

	// -----------------------------------------------------------------------------------------------------------------
	//
	// /**
	// * @param predicateBuilders
	// * Restricting query conditions. If you supply more than one predicate, they
	// will be
	// * joined by conjunction.
	// */
	protected void deleteWhere(final IPredicateBuilder<T>[] predicateBuilders) {

		final CriteriaBuilder cb = this.getEntityManager()
				.getCriteriaBuilder();

		final CriteriaDelete<T> delete = cb.createCriteriaDelete(this.entityClass);
		final Root<T> root = delete.from(this.entityClass);

		if ((predicateBuilders != null) && (predicateBuilders.length > 0))
			delete.where(this.buildPredicates(cb,
					root,
					predicateBuilders));

		this.getEntityManager()
				.createQuery(delete)
				.executeUpdate();
	}

	// -----------------------------------------------------------------------------------------------------------------
	private Predicate[] buildPredicates(final CriteriaBuilder cb, final Root<T> root,
			final IPredicateBuilder<T>[] predicateBuilders) {

		final List<Predicate> predicates = new LinkedList<>();
		if ((predicateBuilders != null) && (predicateBuilders.length > 0))
			for (final IPredicateBuilder<T> builder : predicateBuilders)
				predicates.add(builder.build(cb,
						root));

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	@Override
	public void close() throws IOException {

		final EntityManager em = this.getEntityManager();

		if (em.isOpen()) {

			final EntityTransaction transaction = em.getTransaction();

			if (transaction.isActive())
				transaction.rollback();

			em.close();

		}

		if (this.entityManagerFactory.isOpen())
			this.entityManagerFactory.close();

	}

	// -----------------------------------------------------------------------------------------------------------------

	@Override
	public Query getQuery(final String sqlCommand) {

		final EntityManager em = this.getEntityManager();
		final EntityTransaction transaction = em.getTransaction();

		if (!transaction.isActive())
			transaction.begin();

		return em.createQuery(sqlCommand);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TypedQuery<T> getTypedQuery(final String sqlCommand, final Class<T> entityClass) {

		final EntityManager em = this.getEntityManager();
		final EntityTransaction transaction = em.getTransaction();

		if (!transaction.isActive())
			transaction.begin();

		return em
				.createQuery(sqlCommand,
						entityClass);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean persist() {

		final var transaction = this.getEntityManager()
				.getTransaction();

		if (!transaction.isActive())
			return false;

		try {

			if (transaction.isActive()) {

				transaction.commit();
				return true;
			}

		} catch (final Exception exception) {

			if (transaction.isActive())
				transaction.rollback();

			LOG.error(exception);
		}

		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean undone() {

		final var transaction = this.getEntityManager()
				.getTransaction();

		if (!transaction.isActive())
			return false;

		try {

			if (transaction.isActive()) {

				transaction.rollback();
				return true;
			}

		} catch (final Exception exception) {

			LOG.error(exception);
		}

		return false;
	}

	public long counting() {// to już dedykowana specjalistyczna funkcja

		final CriteriaBuilder criteriaBuilder = this.getEntityManager()
				.getCriteriaBuilder();
		final CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
		final Root<T> employee = query.from(this.entityClass);
		query.select(criteriaBuilder.countDistinct(employee));
		final TypedQuery<Long> typedQuery = this.getEntityManager()
				.createQuery(query);
		return typedQuery.getSingleResult();
	}

	@Override
	public int hashCode() {

		return Objects.hash(enityManager,
				entityClass,
				entityManagerFactory,
				isAutoCommit);
	}

	@Override
	public boolean equals(final Object object) {

		if (isNull(object))
			return false;

		if (this == object)
			return true;

		if (!(object instanceof GenericRepository))
			return false;

		final GenericRepository<T> other = GenericRepository.class.cast(object);
		return Objects.equals(enityManager,
				other.enityManager)
				&& Objects.equals(entityClass,
						other.entityClass)
				&& Objects.equals(entityManagerFactory,
						other.entityManagerFactory)
				&& (isAutoCommit == other.isAutoCommit);
	}

	@Override
	public String toString() {

		return "GenericRepository [entityManagerFactory="
				+ entityManagerFactory
				+ ", enityManager="
				+ enityManager
				+ ", entityType="
				+ entityClass
				+ ", isAutoCommit="
				+ isAutoCommit
				+ "]";
	}

}