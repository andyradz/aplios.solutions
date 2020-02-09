package com.codigo.aplios.domain.model;

import java.io.Serializable;
import java.util.List;

public interface Dao<PK extends Serializable, T extends Domain<PK>> {

	/**
	 * Persist the given entity into through EntityManager.
	 *
	 * @param t entity to be saved.
	 */
	T save(T t);

	/**
	 * Find all items of this type in the database.
	 *
	 * @return a List of T elements from database.
	 */
	List<T> findAll();

	/**
	 * Find an item from database based on its ID.
	 *
	 * @param id to look for.
	 * @return found entity or null if no entity is found.
	 */
	T find(PK id);

	/**
	 * Delete the item from database.
	 *
	 * @param t item to delete.
	 */
	void remove(T t);
}
