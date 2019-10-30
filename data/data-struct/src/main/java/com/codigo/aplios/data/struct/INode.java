package com.codigo.aplios.data.struct;

import java.util.List;

/**
 * Dla każdego drzewa można określić: długość drogi u (głębokość) - liczba
 * wierzchołków, przez które należy przejść od korzenia do wierzchołka u
 * wysokość u - maksymalna liczba wierzchołków na drodze od u do pewnego liścia
 * wysokość drzewa = głębokość = wysokość korzenia +1 ścieżka z u do v - zbiór
 * wierzchołków, przez które należy przejść z wierzchołka u do v droga - ścieżka
 * skierowana stopień wierzchołka - liczba jego bezpośrednich następników
 * stopień drzewa - maksymalny stopień wierzchołka
 *
 * @author dp0470
 *
 * @param <T>
 */
public interface INode<T> {
	// Długość ścieżki prostej od korzenia do danego węzła nazywa się poziomem węzła
	// (ang. node level). Korzeń drzewa ma zawsze poziom 0. W naszym drzewie węzły
	// B, C i D mają poziom 1, a E, F, G i H mają poziom 2. Wysokość drzewa (ang.
	// tree height) jest równa największemu poziomowi węzłów (lub najdłuższej
	// ścieżce rozpoczynającej się w korzeniu). Dla naszego drzewa wysokość jest
	// równa 2. Wysokość węzła (ang. node height), to długość najdłuższej ścieżki od
	// tego węzła do liścia. Dla korzenia wysokość węzła jest równa wysokości
	// drzewa:

	// Nodelevel -
	// TreeHeight

	// Wszystkie wierzchołki połączone z danym wierzchołkiem, a leżące na następnym
	// poziomie są nazywane dziećmi tego węzła (np. dziećmi wierzchołka F są B i G,
	// natomiast wierzchołka B: A i D). Wierzchołek może mieć dowolną liczbę dzieci,
	// jeśli nie ma ich wcale nazywany jest liściem. Liśćmi w przykładowym drzewie
	// są A, C, E, H.

	Node<T> getParent(); // zwraca referencje rodzica

	void setParent(Node<T> parent); // ustawia rodzica dla węzła

	T getData(); // zwraca przechowywane dane

	void setData(T data); // ustawia dane w węźle

	int getDegree(); // zwraca stopień węzła

	Node<T> getChild(int i); // zwraca referencje do i-tego dziecka

	boolean isLeaf(); // sprawdza czy węzeł jest liściem

	Node<T> addChild(Node<T> child); // dodaje do węzła dziecko (inny węzeł)

	Node<T> addChild(T data); // tworzy i dodaje do węzła dziecko z danymi

	Node<T> removeChild(int i); // usuwa i-te dziecko węzła

	void removeChildren(); // usuwa wszystkie dzieci węzła

	Node<T> getLeftMostChild(); // zwraca pierwsze dziecko węzła (z lewej)

	List<Node<T>> getChildren(); // zwraca listę dzieci

	Node<T> getRightSibling(); // zwraca kolejny element siostrzany węzła

	T accept(final INodeVisitable<T> visitor);

	@Override
	String toString(); // wyświetla węzeł (najczęściej dane)

}
