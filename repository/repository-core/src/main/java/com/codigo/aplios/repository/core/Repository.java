package com.codigo.aplios.repository.core;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.codigo.aplios.domain.model.common.EntityModel;

public interface Repository<T extends EntityModel> {

	Set<T> select();

	default Optional<T> select(final Long key) {

		return this.select().stream().filter(entity -> entity.getId().equals(key)).findAny();
	}

	default Set<T> select(final Predicate<T> predicate) {

		return this.select().stream().filter(predicate).collect(Collectors.toSet());
	}

	Long insert(T entity);


	@SuppressWarnings("unchecked")
	default Long insert(final T... entities) {

		return this.insert(Arrays.asList(entities));
	}

	default Long insert(final Iterable<T> entities) {

		entities.forEach(this::insert);
		return Long.valueOf(0);
	}

	void update(T entity);

	@SuppressWarnings("unchecked")
	default void update(final T... entities) {

		this.update(Arrays.asList(entities));
	}

	default void update(final Collection<T> entities) {

		entities.forEach(this::update);
	}

	void delete(T entity);

	Long deleteAll();

	default void delete(final Iterator<T> entities) {

		entities.forEachRemaining(this::delete);
	}

	default void delete(final Long keyId) {

		this.delete(entity -> entity.getId().equals(keyId));
	}

	default void delete(final Predicate<T> predicate) {

		this.select().forEach(this::delete);
	}

	default void delete(final Collection<T> entities) {

		entities.forEach(this::delete);
	}

	@SuppressWarnings("unchecked")
	default void delete(final T... entities) {

		this.delete(Arrays.asList(entities));
	}

	default Long count() {

		return this.select().stream().count();
	}

	default Long countAsync() throws InterruptedException, ExecutionException {

		final CompletableFuture<Long> completableFuture = CompletableFuture
				.supplyAsync(() -> this.select().stream().count());

		return completableFuture.get();
	}

	boolean isAutoCommit();

	Set<T> union(Iterable<T> entities);

	Set<T> except(Iterable<T> entities);

}
