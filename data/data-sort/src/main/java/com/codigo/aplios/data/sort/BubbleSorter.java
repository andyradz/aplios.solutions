package com.codigo.aplios.data.sort;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Klasa implementuje mechanizm sortowania. Sortowanie wykonywanie jest na
 * podstawie algorytmu bąbelkowego.
 *
 * Algorytm sortowania bąbelkowego jest uważany za bardzo zły algorytm
 * sortujący. Można go stosować tylko dla niewielkiej liczby elementów w
 * sortowanym zbiorze (do około 5000). Przy większych zbiorach czas sortowania
 * może być zbyt długi.
 *
 * @author andrzej.radziszewski
 * @category ordering
 * @version 1.0.0.0
 * @since 2017
 *
 * @param <T>
 * Generyczny typ elementów kolekcji
 */
public final class BubbleSorter<T>
        extends AbstractSorter<T> {

    /**
     * Podstawowy konstruktor obiektu klasy
     *
     * @param comparator
     * Mechanizm porównymania obiektów
     */
    public BubbleSorter(final Comparator<T> comparator) {

        super(comparator);
    }

    /**
     * Podstawowy konstruktor obiektu klasy
     *
     * @param comparator
     * Mechanizm porównymania elementów kolekcji danych
     * @param sortingMode
     * Kolejność sortowania elementów kolekcji danych
     */
    public BubbleSorter(final Comparator<T> comparator, final SortingOrder sortingMode) {

        super(comparator, sortingMode);
    }

    /*
     * (non-Javadoc)
     *
     * @see com.codigo.aplios.explorer.data.sorting.ISortable#sort(java.util.List)
     */
    @Override     
    public void sort(final List<T> data) {
        
        boolean swapped = false;

        for (int idx = 0; idx < data.size() - AbstractSorter.ONE && swapped; idx++)
            for (int jdx = 0; jdx < data.size() - idx; jdx++)
                if (this.sortMode.apply(data.get(jdx - AbstractSorter.ONE), data.get(jdx))) {
                    Collections.swap(data, jdx - AbstractSorter.ONE, jdx);
                    swapped = true;
                }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.codigo.aplios.explorer.data.sorting.ISortable#sort(java.util.stream.
     * Stream)
     */
    @Override
    public void sort(final Stream<T> data) {

        this.sort(data.collect(Collectors.toList()));
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.codigo.aplios.explorer.data.sorting.ISortable#sort(java.lang.Object[])
     */
    @Override
    public void sort(final T[] data) {

        boolean swapped = true;

        for (int idx = 0; idx < data.length - AbstractSorter.ONE && swapped; idx++) {
            swapped = false;
            for (int jdx = AbstractSorter.ONE; jdx < data.length - idx; jdx++)
                if (this.sortMode.apply(data[jdx - AbstractSorter.ONE], data[jdx])) {
                    this.swapper.swap(data, jdx, jdx - AbstractSorter.ONE);
                    swapped = true;
                }
        }
    }

}
