package com.codigo.aplios.group.database.repository;

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

//http://websystique.com/spring-security/secure-spring-rest-api-using-basic-authentication/
public class GenericRepository<T extends EntityModel> implements IRepository<T>, Closeable {

	private static final Logger log = Logger.getLogger(GenericRepository.class);
	private final EntityManagerFactory entityManagerFactory;
	private final EntityManager enityManager;
	private final Class<T> entityType;
	private final boolean isAutoCommit;

	/**
	 * Podstawowy konstruktor obiektu klasy <code>GenericRepository<T></code>
	 *
	 * @param entityType  Parametr typu encji
	 * @param dbStoreName Nazwa bazy danych
	 */
	public GenericRepository(final Class<T> entityType, final String dbStoreName) {

		this.entityType = entityType;
		this.isAutoCommit = false;
		this.entityManagerFactory = Persistence.createEntityManagerFactory(dbStoreName);
		this.enityManager = entityManagerFactory.createEntityManager();
	}

	public GenericRepository(final EntityManager entityManager, final Class<T> entityType, final String dbStoreName)
			throws Exception {

		if (Objects.isNull(entityManager))
			throw new Exception("EntityManager is null!");

		if (!entityManager.isOpen())
			throw new Exception("EntityManager has not been opened!");

		this.entityType = entityType;
		this.isAutoCommit = false;
		this.entityManagerFactory = Persistence.createEntityManagerFactory(dbStoreName);
		this.enityManager = entityManager;
	}

	public List<T> selectById(final Long entityKey) {

		final EntityManager em = this.getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<T> query = cb.createQuery(this.entityType);

		final Root<T> c = query.from(this.entityType);
		final ParameterExpression<Long> p = cb.parameter(Long.class, "id");

		query.select(c)
			.where(cb.equal(c.get("id"), p));

		return em.createQuery(query)
			.setParameter("id", entityKey)
			.getResultList();
	}

	@Override
	public Set<T> select() {

		return this.runInTransaction(entityManager -> {

			final TypedQuery<T> query = this.createTypedQuery((cb, r, q) -> q);
			query.setLockMode(LockModeType.PESSIMISTIC_READ);

			try {

				return query.getResultList().stream().collect(Collectors.toSet());

			} catch (final Exception ex) {
				log.error(ex);
				return Collections.emptySet();
			}
		});
	}

	// -----------------------------------------------------------------------------------------------------------------
	@Override
	public boolean insert(final T entity) {

		final Consumer<EntityManager> sqlCommand = entityManager -> {

			if (Objects.isNull(entity.getId()))
				entityManager.persist((entity));

			else if (Objects.nonNull(entityManager.find(this.entityType, entity.getId())))
				entityManager.merge((entity));
		};

		try {

			return this.runInTransaction(sqlCommand);

		} catch (final Exception ex) {

			log.error(ex);
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
	@Override
	public void delete(final Long entityKey) {

		this.runInTransaction(entityManager -> {

			final T managedEntity =
					entityManager.find(this.entityType, entityKey, LockModeType.PESSIMISTIC_WRITE);

			if (Objects.nonNull(managedEntity))
				entityManager.remove(managedEntity);
		});
	}

	@Override
	public Long deleteFrom(final Iterable<T> entities) {

		return this.runInTransaction(entityManager -> {

			var entitiesStream = StreamSupport.stream(entities.spliterator(), false);

			if (entitiesStream.count() <= 0L)
				return 0L;

			final CriteriaBuilder cb = entityManager.getCriteriaBuilder();

			final CriteriaDelete<T> delete = cb.createCriteriaDelete(this.entityType);

			final Root<T> e = delete.from(this.entityType);

			entitiesStream = StreamSupport.stream(entities.spliterator(), false);
			delete.where(e.in(entitiesStream.collect(Collectors.toList())));

			return Long.valueOf(entityManager.createQuery(delete).executeUpdate());
		});
	}

	/**
	 *
	 */
	@Override
	public long deleteAll() {

		return (this.runInTransaction(entityManager -> {

			final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			final CriteriaDelete<T> query = criteriaBuilder.createCriteriaDelete(this.entityType);

			return entityManager.createQuery(query)
				.executeUpdate();

		})).longValue();
	}

	@Override
	public long count() {

		final EntityManager em = this.getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> query = cb.createQuery(Long.class);

		final Root<T> root = query.from(this.entityType);

		query.select(cb.count(root));

		return em.createQuery(query)
			.getSingleResult();
	}

	// -----------------------------------------------------------------------------------------------------------------
	@Override
	public void delete(final Iterable<T> entities) {

		this.runInTransaction(entityManager -> {

			StreamSupport.stream(entities.spliterator(), false)
				.map(T::getId)
				.map(id -> entityManager.find(this.entityType, id))
				.filter(Objects::nonNull)
				.forEach(entityManager::remove);
		});
	}

	// -----------------------------------------------------------------------------------------------------------------
	private <R> R runInTransaction(final Function<EntityManager, R> operator) {

		final EntityManager entityManager = this.getEntityManager();
		final EntityTransaction tran = entityManager.getTransaction();

		final R result;

		if (!(tran.isActive()))
			tran.begin();

		try {
			result = operator.apply(entityManager);

//				if (this.isAutoCommit())
//					entityManager.flush();

			tran.commit();

			return result;

		} catch (final Exception ex) {
			tran.rollback();
			log.error(ex);
		}

		return (R) Function.identity();
	}

	// -----------------------------------------------------------------------------------------------------------------
	private boolean runInTransaction(final Consumer<EntityManager> sqlCommand) {

		final Function<EntityManager, Boolean> sqlTransaction = entityManager -> {

			final EntityTransaction tran = entityManager.getTransaction();
			tran.begin();

			try {
				sqlCommand.accept(entityManager);

//				if (this.isAutoCommit())
//					entityManager.flush();

				tran.commit();

			} catch (final Exception ex) {
				tran.rollback();
				log.error(ex);
				return false;
			}

			return true;
		};

		return this.run(sqlTransaction);
	}

	// -----------------------------------------------------------------------------------------------------------------
	private <R> R run(final Function<EntityManager, R> sqlTransaction) {

		final EntityManager entityManager = this.getEntityManager();

		try {

			return sqlTransaction.apply(entityManager);

		} catch (final Exception ex) {

			log.error(ex);
			return (R) Function.identity();
		}
	}

	// -----------------------------------------------------------------------------------------------------------------
	private void run(final Consumer<EntityManager> operator) {

		this.run(entityManager -> {
			operator.accept(entityManager);
			return Function.identity();
		});
	}

	private TypedQuery<T> createTypedQuery(final IQueryable<T> queryBuilder) {

		final EntityManager entityManager = this.getEntityManager();
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<T> query = builder.createQuery(this.entityType);
		final Root<T> root = query.from(this.entityType);

		CriteriaQuery<T> criteriaQuery = query.select(root);
		criteriaQuery = queryBuilder.build(builder, root, criteriaQuery);

		final TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
		typedQuery.setFlushMode(this.isAutoCommit
				? FlushModeType.AUTO
				: FlushModeType.COMMIT);

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

	private EntityManager getEntityManager() {

		if (Objects.isNull(this.enityManager))
			return this.entityManagerFactory.createEntityManager();
		//
//		if (!em.isOpen())
//			return null;// throw exception
//
//		em.setFlushMode(this.isAutoCommit
//				? FlushModeType.AUTO
//				: FlushModeType.COMMIT);

		return this.enityManager;
	}

	// -----------------------------------------------------------------------------------------------------------------
	public List<T> finda(final IQueryable<T> queryBuilder) {

		return this.createQuery(queryBuilder)
			.getResultList();
	}
	// https://github.com/janhalasa/JpaCriteriaWithLambdaExpressions/blob/master/src/main/java/com/halasa/criterialambda/dao/builder/QueryBuilder.java

	// -----------------------------------------------------------------------------------------------------------------
	protected TypedQuery<T> createQuery(final IQueryable<T> queryBuilder) {

		return this.createTypedQuery(queryBuilder);
	}

	// -----------------------------------------------------------------------------------------------------------------
	public List<T> find(final IQueryable<T> queryBuilder) {

		return this.createQuery(queryBuilder)
			.getResultList();
	}

	// -----------------------------------------------------------------------------------------------------------------
	/**
	 * @param  predicateBuilders Restricting query conditions. If you supply more
	 *                           than one predicate, they will be joined by
	 *                           conjunction.
	 * @return
	 *
	 */
	protected List<T> findWhere(final IPredicateBuilder<T>[] predicateBuilders) {

		return this
			.createTypedQuery((cb, root, query) -> (query.where(this.buildPredicates(cb, root, predicateBuilders))))
			.getResultList();
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
			.createTypedQuery((cb, root, query) -> (query.where(this.buildPredicates(cb, root, predicateBuilders))))
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

		final CriteriaDelete<T> delete = cb.createCriteriaDelete(this.entityType);
		final Root<T> root = delete.from(this.entityType);

		if ((predicateBuilders != null) && (predicateBuilders.length > 0))
			delete.where(this.buildPredicates(cb, root, predicateBuilders));

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
				predicates.add(builder.build(cb, root));

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	@Override
	public void close() throws IOException {

		if (this.entityManagerFactory.isOpen())
			this.entityManagerFactory.close();

	}

	// -----------------------------------------------------------------------------------------------------------------

	@Override
	public Query getQuery(final String sqlCommand) {

		return this.getEntityManager().createQuery(sqlCommand);
	}

	@Override
	public int hashCode() {

		return Objects.hash(enityManager, entityManagerFactory, entityType, isAutoCommit);
	}

	@Override
	public boolean equals(final Object obj) {

		if (this == obj)
			return true;

		if (!(obj instanceof GenericRepository))
			return false;

		final GenericRepository<T> other = GenericRepository.class.cast(obj);

		return Objects.equals(enityManager, other.enityManager)
				&& Objects.equals(entityManagerFactory, other.entityManagerFactory)
				&& Objects.equals(entityType, other.entityType)
				&& (isAutoCommit == other.isAutoCommit);
	}

	@Override
	public String toString() {

		return "GenericRepository [entityManagerFactory="
				+ entityManagerFactory
				+ ", enityManager="
				+ enityManager
				+ ", entityType="
				+ entityType
				+ ", isAutoCommit="
				+ isAutoCommit
				+ "]";
	}
}