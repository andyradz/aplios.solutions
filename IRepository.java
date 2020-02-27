package com.codigo.aplios.group.database.repository;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.codigo.aplios.group.database.models.location.EntityModel;

//TODO: dodać możliwośc aktualizacji wartośi wszystkich rekordów o dane wartości

/**
 * Szablon funkcjonalności repozytorium rekordów encji. Zawiera podstawowe
 * operacje wykonywane na zbiorze danych i wykonywanych na bazie danych.
 *
 * @param <T> Typ danych składowanych rekordów encji
 * @author andrzej radziszewski
 * @version 1.0.0.0
 * @since 2019
 * @category Repository
 */
public interface IRepository<T extends EntityModel> {

	/**
	 * Metoda zwraca wszystkie dostępne rekordy encji zapisane w tabeli bazy danych.
	 *
	 * @return Kolekcja rekordów tabeli bazy danych
	 */
	Set<T> select();

	/**
	 * Metoda zwraca selektywnie dostępny rekord encji zapisany w tabeli bazy
	 * danych. Selekcja odbywa się po kluczu rekordu tabeli w bazie danych.
	 *
	 * @param entityKey Wartość klucza tabeli bazy danych
	 * @return Rekord tabeli bazy danych
	 */
	default Optional<T> select(final Long entityKey) {

		return this.select()
				.stream()
				.filter(entity -> entity.getId()
						.equals(entityKey))
				.findAny();
	}

	/**
	 * Metoda zwraca selektywnie dostępne rekordy encji zapisany w tabeli bazy
	 * danych. Selekcja odbywa sie na podstawie warunku zdefiniowanego jako
	 * Predicate.
	 *
	 * @param predicate Warunek selekcji rekordów zbioru danych
	 * @return Kolekcja rekordów tabeli bazy danych
	 */
	default Set<T> select(final Predicate<T> predicate) {

		return Collections.unmodifiableSet(this.select()
				.stream()
				.filter(predicate)
				.collect(Collectors.toSet()));
	}

	/**
	 * Metoda zwraca wszystkie dostępne rekordy encji zapisane w tabeli bazy danych.
	 *
	 * @return Iterator kolekcji rekordów tabeli bazy danych
	 */
	default Iterator<T> selectAll() {

		return this.select()
				.stream()
				.iterator();
	}

	/**
	 * Metoda znajduje wspólne encje zbioru rekordów i kolekcji przekazanych encji.
	 *
	 * @param entities Kolekcja encji do sprawdzenia
	 * @return Kolekcja rekordów tabeli bazy danych
	 */
	default Set<T> selectIntersect(final Set<T> entities) {

		return Collections.unmodifiableSet(select().stream()
				.distinct()
				.filter(entities::contains)
				.collect(Collectors.toSet()));
	}

	/**
	 * Metoda znajduje różnicę encji zbioru rekordów i kolekcji przekazanych encji.
	 *
	 * @param entities Kolekcja encji do sprawdzenia
	 * @return Kolekcja rekordów tabeli bazy danych
	 */
	default Set<T> selectExcept(final Set<T> entities) {

		return Collections.unmodifiableSet(this.select()
				.stream()
				.distinct()
				.filter(((Predicate<T>) entities::contains).negate())
				.collect(Collectors.toSet()));
	}

	/**
	 * Metoda wstawia encje do rekordów tabeli bazy danych
	 *
	 * @param entity Encja kolekcji tabeli zbioru danych
	 * @return Wartośc logiczna TRUE, FALSE wskazujący stan wykonanej operacji
	 */
	boolean insert(T entity);

	/**
	 * Metoda wstawia encje do rekordów tabeli bazy danych
	 *
	 * @param entities Tablica encji do wstawienia
	 */
	default void insert(final T[] entities) {

		this.insert(Arrays.asList(entities));
	}

	/**
	 * Metoda wstawia encje do rekordów tabeli bazy danych
	 *
	 * @param entities Kolekcja encji do wstawienia
	 */
	default void insert(final Iterable<T> entities) {

		entities.forEach(this::insert);
	}

	/**
	 * Metoda aktualizuje rekord encji tabeli bazy danych
	 *
	 * @param entity Encja do aktualizacji
	 */
	void update(T entity);

	/**
	 * Metoda aktualizuje rekordy encji tabeli bazy danych
	 *
	 * @param entities Tablica encji do aktualizacji
	 */
	default void update(final T[] entities) {

		this.update(Arrays.asList(entities));
	}

	/**
	 * Metoda aktualizuje rekordy encji tabeli bazy danych
	 *
	 * @param entities Kolekcja encji do aktualizacji
	 */
	default void update(final Iterable<T> entities) {

		entities.forEach(this::update);
	}

	/**
	 * Metoda usuwa encje z rekordów tabeli bazy danych
	 *
	 * @param entity Encja do usunięcia
	 */
	void delete(T entity);

	/**
	 * Metoda usuwa wszystkie rekordy encji tabeli bazy danych
	 *
	 * @return Ilość usuniętych rekordów encji tabeli bazy danych
	 */
	long deleteAll();

	/**
	 * Metoda usuwa encje rekordów tabeli bazy danych.
	 *
	 * @param entities Kolekcja encji do usunięcia
	 * @return Ilość usuniętych encji rekordów
	 */
	Long deleteFrom(final Iterable<T> entities);

	/**
	 * Metoda usuwa encje rekordów tabeli bazy danych.
	 *
	 * @param entities Kolekcja encji do usnięcia
	 */
	default void delete(final Iterable<T> entities) {

		entities.forEach(this::delete);
	}

	/**
	 * Metoda usuwa selektywnie rekord encji tabeli bazy danych. Selekcja odbywa się
	 * na podstawie klucza encji danych.
	 *
	 * @param entityKey Wartość klucza encji danych
	 */
	default void delete(final Long entityKey) {

		this.delete(entity -> entity.getId()
				.equals(entityKey));
	}

	/**
	 * Metoda usuwa selektywnie rekordy encji tabeli bazy danych. Selekcja odbywa
	 * się na podstawie warunku określonego jako Predicate.
	 *
	 * @param predicate Warunek selecji opeary na Predicate
	 */
	default void delete(final Predicate<T> predicate) {

		this.select()
				.forEach(this::delete);
	}

	/**
	 * Metoda usuwa rekordy encji tabeli bazy danych.
	 *
	 * @param entities Encje rekordów do usunięcia
	 */
	default void delete(final T[] entities) {

		this.delete(Arrays.asList(entities));
	}

	/**
	 * Metoda zlicza ilość rekordów tabeli bazy danych
	 *
	 * @return Ilość rekordów encji tabeli bazy danych
	 */
	default long count() {

		return this.select()
				.stream()
				.count();
	}

	/**
	 * Metoda zlicza asynchronicznie ilość rekordów tabeli bazy danych
	 *
	 * @return Ilość rekordów encji tabeli bazy danych
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	default Long countAsync() throws InterruptedException, ExecutionException {

		return supplyAsync(() -> this.select()
				.stream()
				.count())
						.get();
	}

	boolean isAutoCommit();

	/**
	 * @return
	 */
	boolean persist();

	/**
	 * Metoda
	 *
	 * @return
	 */
	boolean undone();

	Query getQuery(String sqlCommand);

	TypedQuery<T> getTypedQuery(String sqlCommand, Class<T> enitytClass);

}