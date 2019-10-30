package com.codigo.aplios.repository.core;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.codigo.aplios.domain.model.common.EntityModel;


//HashSet doesn’t maintain any order, the elements would be returned in any random order.

//HashSet allows null values however if you insert more than one nulls it would still return only one null value.
//HashSet is non-synchronized.
//The iterator returned by this class is fail-fast which means iterator would throw ConcurrentModificationException if HashSet has been modified after creation of iterator, by any means except iterator’s own remove method.

// dodać runintran transaction gedzie bedziemy logowac operacje
// dodać state encji
enum EntityState {
	TRANSIENT, MANAGED, DETACHED, REMOVED;

}

public class MemoryRepository<T extends EntityModel> implements Repository<T> {

	private final Set<T> dataSet;

	private int counterId;

	public MemoryRepository() {

		this.dataSet = new HashSet<>();
	}

	@Override
	public Set<T> select() {

		return this.dataSet;
	}

	@Override
	public void insert(final T entity) {
		//HashSet doesn’t allow duplicates. If you try to add a duplicate element in HashSet, the old value would be overwritten.		
		this.dataSet.add(entity);
	}

	@Override
	public void insert(final Collection<T> entities) {

		entities.forEach(this::insert);
	}

	@Override
	@SafeVarargs
	public final void insert(final T... entities) {

		Stream.of(entities).forEach(this::insert);
	}

	@Override
	public void delete(final Integer key) {

		this.dataSet.removeIf(item -> item.getId().equals(key));
	}

	@Override
	public void delete(final Predicate<T> predicate) {

		this.delete(this.select(predicate));
	}

	@Override
	public void delete(final Collection<T> entities) {

		this.dataSet.removeAll(entities);
	}

	@Override
	public long delete() {

		final long dataCount = this.dataSet.size();
		this.dataSet.clear();
		return dataCount;
	}

	@Override
	public boolean isAutoCommit() {

		return true;
	}

	@Override
	public void update(final T entity) {

		this.dataSet.add(entity);
	}

	@Override
	public void delete(T entity) {

		this.dataSet.remove(entity);
		// throw new Exception("Delete record has failed!");
	}
}
