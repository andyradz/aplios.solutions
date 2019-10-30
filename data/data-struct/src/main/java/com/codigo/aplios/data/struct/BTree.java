package com.codigo.aplios.data.struct;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;

/**
 * B-tree is a tree data structure that keeps data sorted and allows searches,
 * sequential access, insertions, and deletions in logarithmic time. The B-tree
 * is a generalization of a binary search tree in that a node can have more than
 * two children. Unlike self-balancing binary search trees, the B-tree is
 * optimized for systems that read and write large blocks of data. It is
 * commonly used in databases and file-systems.
 * <p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/B-tree">B-Tree (Wikipedia)</a>
 *      <br>
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
@SuppressWarnings("unchecked")
public class BTree<T extends Comparable<T>> implements ITree<T> {

	// Default to 2-3 Tree
	private int minKeySize = 1;
	private int minChildrenSize = this.minKeySize + 1; // 2
	private int maxKeySize = 2 * this.minKeySize; // 2
	private int maxChildrenSize = this.maxKeySize + 1; // 3

	private Node<T> root = null;
	private int size = 0;

	/**
	 * Constructor for B-Tree which defaults to a 2-3 B-Tree.
	 */
	public BTree() {

	}

	/**
	 * Constructor for B-Tree of ordered parameter. Order here means minimum number
	 * of keys in a non-root node.
	 *
	 * @param order of the B-Tree.
	 */
	public BTree(final int order) {

		this.minKeySize = order;
		this.minChildrenSize = this.minKeySize + 1;
		this.maxKeySize = 2 * this.minKeySize;
		this.maxChildrenSize = this.maxKeySize + 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean add(final T value) {

		if (this.root == null) {
			this.root = new Node<>(null, this.maxKeySize, this.maxChildrenSize);
			this.root.addKey(value);
		} else {
			Node<T> node = this.root;
			while (node != null) {
				if (node.numberOfChildren() == 0) {
					node.addKey(value);
					if (node.numberOfKeys() <= this.maxKeySize)
						// A-OK
						break;
					// Need to split up
					this.split(node);
					break;
				}
				// Navigate

				// Lesser or equal
				final T lesser = node.getKey(0);
				if (value.compareTo(lesser) <= 0) {
					node = node.getChild(0);
					continue;
				}

				// Greater
				final int numberOfKeys = node.numberOfKeys();
				final int last = numberOfKeys - 1;
				final T greater = node.getKey(last);
				if (value.compareTo(greater) > 0) {
					node = node.getChild(numberOfKeys);
					continue;
				}

				// Search internal nodes
				for (int i = 1; i < node.numberOfKeys(); i++) {
					final T prev = node.getKey(i - 1);
					final T next = node.getKey(i);
					if ((value.compareTo(prev) > 0) && (value.compareTo(next) <= 0)) {
						node = node.getChild(i);
						break;
					}
				}
			}
		}

		this.size++;

		return true;
	}

	/**
	 * The node's key size is greater than maxKeySize, split down the middle.
	 *
	 * @param nodeToSplit to split.
	 */
	private void split(final Node<T> nodeToSplit) {

		Node<T> node = nodeToSplit;
		final int numberOfKeys = node.numberOfKeys();
		final int medianIndex = numberOfKeys / 2;
		final T medianValue = node.getKey(medianIndex);

		final Node<T> left = new Node<>(null, this.maxKeySize, this.maxChildrenSize);
		for (int i = 0; i < medianIndex; i++)
			left.addKey(node.getKey(i));
		if (node.numberOfChildren() > 0)
			for (int j = 0; j <= medianIndex; j++) {
				final Node<T> c = node.getChild(j);
				left.addChild(c);
			}

		final Node<T> right = new Node<>(null, this.maxKeySize, this.maxChildrenSize);
		for (int i = medianIndex + 1; i < numberOfKeys; i++)
			right.addKey(node.getKey(i));
		if (node.numberOfChildren() > 0)
			for (int j = medianIndex + 1; j < node.numberOfChildren(); j++) {
				final Node<T> c = node.getChild(j);
				right.addChild(c);
			}

		if (node.parent == null) {
			// new root, height of tree is increased
			final Node<T> newRoot = new Node<>(null, this.maxKeySize, this.maxChildrenSize);
			newRoot.addKey(medianValue);
			node.parent = newRoot;
			this.root = newRoot;
			node = this.root;
			node.addChild(left);
			node.addChild(right);
		} else {
			// Move the median value up to the parent
			final Node<T> parent = node.parent;
			parent.addKey(medianValue);
			parent.removeChild(node);
			parent.addChild(left);
			parent.addChild(right);

			if (parent.numberOfKeys() > this.maxKeySize)
				this.split(parent);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T remove(final T value) {

		T removed = null;
		final Node<T> node = this.getNode(value);
		removed = this.remove(value, node);
		return removed;
	}

	/**
	 * Remove the value from the Node and check invariants
	 *
	 * @param value T to remove from the tree
	 * @param node  Node to remove value from
	 * @return True if value was removed from the tree.
	 */
	private T remove(final T value, final Node<T> node) {

		if (node == null)
			return null;

		T removed = null;
		final int index = node.indexOf(value);
		removed = node.removeKey(value);
		if (node.numberOfChildren() == 0) {
			// leaf node
			if ((node.parent != null) && (node.numberOfKeys() < this.minKeySize))
				this.combined(node);
			else if ((node.parent == null) && (node.numberOfKeys() == 0))
				// Removing root node with no keys or children
				this.root = null;
		} else {
			// internal node
			final Node<T> lesser = node.getChild(index);
			final Node<T> greatest = this.getGreatestNode(lesser);
			final T replaceValue = this.removeGreatestValue(greatest);
			node.addKey(replaceValue);
			if ((greatest.parent != null) && (greatest.numberOfKeys() < this.minKeySize))
				this.combined(greatest);
			if (greatest.numberOfChildren() > this.maxChildrenSize)
				this.split(greatest);
		}

		this.size--;

		return removed;
	}

	/**
	 * Remove greatest valued key from node.
	 *
	 * @param node to remove greatest value from.
	 * @return value removed;
	 */
	private T removeGreatestValue(final Node<T> node) {

		T value = null;
		if (node.numberOfKeys() > 0)
			value = node.removeKey(node.numberOfKeys() - 1);
		return value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void clear() {

		this.root = null;
		this.size = 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean contains(final T value) {

		final Node<T> node = this.getNode(value);
		return (node != null);
	}

	/**
	 * Get the node with value.
	 *
	 * @param value to find in the tree.
	 * @return Node<T> with value.
	 */
	private Node<T> getNode(final T value) {

		Node<T> node = this.root;
		while (node != null) {
			final T lesser = node.getKey(0);
			if (value.compareTo(lesser) < 0) {
				if (node.numberOfChildren() > 0)
					node = node.getChild(0);
				else
					node = null;
				continue;
			}

			final int numberOfKeys = node.numberOfKeys();
			final int last = numberOfKeys - 1;
			final T greater = node.getKey(last);
			if (value.compareTo(greater) > 0) {
				if (node.numberOfChildren() > numberOfKeys)
					node = node.getChild(numberOfKeys);
				else
					node = null;
				continue;
			}

			for (int i = 0; i < numberOfKeys; i++) {
				final T currentValue = node.getKey(i);
				if (currentValue.compareTo(value) == 0)
					return node;

				final int next = i + 1;
				if (next <= last) {
					final T nextValue = node.getKey(next);
					if ((currentValue.compareTo(value) < 0) && (nextValue.compareTo(value) > 0)) {
						if (next < node.numberOfChildren()) {
							node = node.getChild(next);
							break;
						}
						return null;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Get the greatest valued child from node.
	 *
	 * @param nodeToGet child with the greatest value.
	 * @return Node<T> child with greatest value.
	 */
	private Node<T> getGreatestNode(final Node<T> nodeToGet) {

		Node<T> node = nodeToGet;
		while (node.numberOfChildren() > 0)
			node = node.getChild(node.numberOfChildren() - 1);
		return node;
	}

	/**
	 * Combined children keys with parent when size is less than minKeySize.
	 *
	 * @param node with children to combined.
	 * @return True if combined successfully.
	 */
	private boolean combined(final Node<T> node) {

		final Node<T> parent = node.parent;
		final int index = parent.indexOf(node);
		final int indexOfLeftNeighbor = index - 1;
		final int indexOfRightNeighbor = index + 1;

		Node<T> rightNeighbor = null;
		int rightNeighborSize = -this.minChildrenSize;
		if (indexOfRightNeighbor < parent.numberOfChildren()) {
			rightNeighbor = parent.getChild(indexOfRightNeighbor);
			rightNeighborSize = rightNeighbor.numberOfKeys();
		}

		// Try to borrow neighbor
		if ((rightNeighbor != null) && (rightNeighborSize > this.minKeySize)) {
			// Try to borrow from right neighbor
			final T removeValue = rightNeighbor.getKey(0);
			final int prev = this.getIndexOfPreviousValue(parent, removeValue);
			final T parentValue = parent.removeKey(prev);
			final T neighborValue = rightNeighbor.removeKey(0);
			node.addKey(parentValue);
			parent.addKey(neighborValue);
			if (rightNeighbor.numberOfChildren() > 0)
				node.addChild(rightNeighbor.removeChild(0));
		} else {
			Node<T> leftNeighbor = null;
			int leftNeighborSize = -this.minChildrenSize;
			if (indexOfLeftNeighbor >= 0) {
				leftNeighbor = parent.getChild(indexOfLeftNeighbor);
				leftNeighborSize = leftNeighbor.numberOfKeys();
			}

			if ((leftNeighbor != null) && (leftNeighborSize > this.minKeySize)) {
				// Try to borrow from left neighbor
				final T removeValue = leftNeighbor.getKey(leftNeighbor.numberOfKeys() - 1);
				final int prev = this.getIndexOfNextValue(parent, removeValue);
				final T parentValue = parent.removeKey(prev);
				final T neighborValue = leftNeighbor.removeKey(leftNeighbor.numberOfKeys() - 1);
				node.addKey(parentValue);
				parent.addKey(neighborValue);
				if (leftNeighbor.numberOfChildren() > 0)
					node.addChild(leftNeighbor.removeChild(leftNeighbor.numberOfChildren() - 1));
			} else if ((rightNeighbor != null) && (parent.numberOfKeys() > 0)) {
				// Can't borrow from neighbors, try to combined with right neighbor
				final T removeValue = rightNeighbor.getKey(0);
				final int prev = this.getIndexOfPreviousValue(parent, removeValue);
				final T parentValue = parent.removeKey(prev);
				parent.removeChild(rightNeighbor);
				node.addKey(parentValue);
				for (int i = 0; i < rightNeighbor.keysSize; i++) {
					final T v = rightNeighbor.getKey(i);
					node.addKey(v);
				}
				for (int i = 0; i < rightNeighbor.childrenSize; i++) {
					final Node<T> c = rightNeighbor.getChild(i);
					node.addChild(c);
				}

				if ((parent.parent != null) && (parent.numberOfKeys() < this.minKeySize))
					// removing key made parent too small, combined up tree
					this.combined(parent);
				else if (parent.numberOfKeys() == 0) {
					// parent no longer has keys, make this node the new root
					// which decreases the height of the tree
					node.parent = null;
					this.root = node;
				}
			} else if ((leftNeighbor != null) && (parent.numberOfKeys() > 0)) {
				// Can't borrow from neighbors, try to combined with left neighbor
				final T removeValue = leftNeighbor.getKey(leftNeighbor.numberOfKeys() - 1);
				final int prev = this.getIndexOfNextValue(parent, removeValue);
				final T parentValue = parent.removeKey(prev);
				parent.removeChild(leftNeighbor);
				node.addKey(parentValue);
				for (int i = 0; i < leftNeighbor.keysSize; i++) {
					final T v = leftNeighbor.getKey(i);
					node.addKey(v);
				}
				for (int i = 0; i < leftNeighbor.childrenSize; i++) {
					final Node<T> c = leftNeighbor.getChild(i);
					node.addChild(c);
				}

				if ((parent.parent != null) && (parent.numberOfKeys() < this.minKeySize))
					// removing key made parent too small, combined up tree
					this.combined(parent);
				else if (parent.numberOfKeys() == 0) {
					// parent no longer has keys, make this node the new root
					// which decreases the height of the tree
					node.parent = null;
					this.root = node;
				}
			}
		}

		return true;
	}

	/**
	 * Get the index of previous key in node.
	 *
	 * @param node  to find the previous key in.
	 * @param value to find a previous value for.
	 * @return index of previous key or -1 if not found.
	 */
	private int getIndexOfPreviousValue(final Node<T> node, final T value) {

		for (int i = 1; i < node.numberOfKeys(); i++) {
			final T t = node.getKey(i);
			if (t.compareTo(value) >= 0)
				return i - 1;
		}
		return node.numberOfKeys() - 1;
	}

	/**
	 * Get the index of next key in node.
	 *
	 * @param node  to find the next key in.
	 * @param value to find a next value for.
	 * @return index of next key or -1 if not found.
	 */
	private int getIndexOfNextValue(final Node<T> node, final T value) {

		for (int i = 0; i < node.numberOfKeys(); i++) {
			final T t = node.getKey(i);
			if (t.compareTo(value) >= 0)
				return i;
		}
		return node.numberOfKeys() - 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {

		return this.size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean validate() {

		if (this.root == null)
			return true;
		return this.validateNode(this.root);
	}

	/**
	 * Validate the node according to the B-Tree invariants.
	 *
	 * @param node to validate.
	 * @return True if valid.
	 */
	private boolean validateNode(final Node<T> node) {

		final int keySize = node.numberOfKeys();
		if (keySize > 1)
			// Make sure the keys are sorted
			for (int i = 1; i < keySize; i++) {
				final T p = node.getKey(i - 1);
				final T n = node.getKey(i);
				if (p.compareTo(n) > 0)
					return false;
			}
		final int childrenSize = node.numberOfChildren();
		if (node.parent == null) {
			// root
			if (keySize > this.maxKeySize)
				// check max key size. root does not have a min key size
				return false;
			else if (childrenSize == 0)
				// if root, no children, and keys are valid
				return true;
			else if (childrenSize < 2)
				// root should have zero or at least two children
				return false;
			else if (childrenSize > this.maxChildrenSize)
				return false;
		} else // non-root
		if (keySize < this.minKeySize)
			return false;
		else if (keySize > this.maxKeySize)
			return false;
		else if (childrenSize == 0)
			return true;
		else if (keySize != (childrenSize - 1))
			// If there are chilren, there should be one more child then
			// keys
			return false;
		else if (childrenSize < this.minChildrenSize)
			return false;
		else if (childrenSize > this.maxChildrenSize)
			return false;

		final Node<T> first = node.getChild(0);
		// The first child's last key should be less than the node's first key
		if (first.getKey(first.numberOfKeys() - 1)
			.compareTo(node.getKey(0)) > 0)
			return false;

		final Node<T> last = node.getChild(node.numberOfChildren() - 1);
		// The last child's first key should be greater than the node's last key
		if (last.getKey(0)
			.compareTo(node.getKey(node.numberOfKeys() - 1)) < 0)
			return false;

		// Check that each node's first and last key holds it's invariance
		for (int i = 1; i < node.numberOfKeys(); i++) {
			final T p = node.getKey(i - 1);
			final T n = node.getKey(i);
			final Node<T> c = node.getChild(i);
			if (p.compareTo(c.getKey(0)) > 0)
				return false;
			if (n.compareTo(c.getKey(c.numberOfKeys() - 1)) < 0)
				return false;
		}

		for (int i = 0; i < node.childrenSize; i++) {
			final Node<T> c = node.getChild(i);
			final boolean valid = this.validateNode(c);
			if (!valid)
				return false;
		}

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public java.util.Collection<T> toCollection() {

		return (new JavaCompatibleBTree<>(this));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {

		return TreePrinter.getString(this);
	}

	private static class Node<T extends Comparable<T>> {

		private T[] keys = null;
		private int keysSize = 0;
		private Node<T>[] children = null;
		private int childrenSize = 0;
		private final Comparator<Node<T>> comparator = (arg0, arg1) -> arg0.getKey(0)
			.compareTo(arg1.getKey(0));

		protected Node<T> parent = null;

		private Node(final Node<T> parent, final int maxKeySize, final int maxChildrenSize) {

			this.parent = parent;
			this.keys = (T[]) new Comparable[maxKeySize + 1];
			this.keysSize = 0;
			this.children = new Node[maxChildrenSize + 1];
			this.childrenSize = 0;
		}

		private T getKey(final int index) {

			return this.keys[index];
		}

		private int indexOf(final T value) {

			for (int i = 0; i < this.keysSize; i++)
				if (this.keys[i].equals(value))
					return i;
			return -1;
		}

		private void addKey(final T value) {

			this.keys[this.keysSize++] = value;
			Arrays.sort(this.keys, 0, this.keysSize);
		}

		private T removeKey(final T value) {

			T removed = null;
			boolean found = false;
			if (this.keysSize == 0)
				return null;
			for (int i = 0; i < this.keysSize; i++)
				if (this.keys[i].equals(value)) {
					found = true;
					removed = this.keys[i];
				} else if (found)
					// shift the rest of the keys down
					this.keys[i - 1] = this.keys[i];
			if (found) {
				this.keysSize--;
				this.keys[this.keysSize] = null;
			}
			return removed;
		}

		private T removeKey(final int index) {

			if (index >= this.keysSize)
				return null;
			final T value = this.keys[index];
			for (int i = index + 1; i < this.keysSize; i++)
				// shift the rest of the keys down
				this.keys[i - 1] = this.keys[i];
			this.keysSize--;
			this.keys[this.keysSize] = null;
			return value;
		}

		private int numberOfKeys() {

			return this.keysSize;
		}

		private Node<T> getChild(final int index) {

			if (index >= this.childrenSize)
				return null;
			return this.children[index];
		}

		private int indexOf(final Node<T> child) {

			for (int i = 0; i < this.childrenSize; i++)
				if (this.children[i].equals(child))
					return i;
			return -1;
		}

		private boolean addChild(final Node<T> child) {

			child.parent = this;
			this.children[this.childrenSize++] = child;
			Arrays.sort(this.children, 0, this.childrenSize, this.comparator);
			return true;
		}

		private boolean removeChild(final Node<T> child) {

			boolean found = false;
			if (this.childrenSize == 0)
				return found;
			for (int i = 0; i < this.childrenSize; i++)
				if (this.children[i].equals(child))
					found = true;
				else if (found)
					// shift the rest of the keys down
					this.children[i - 1] = this.children[i];
			if (found) {
				this.childrenSize--;
				this.children[this.childrenSize] = null;
			}
			return found;
		}

		private Node<T> removeChild(final int index) {

			if (index >= this.childrenSize)
				return null;
			final Node<T> value = this.children[index];
			this.children[index] = null;
			for (int i = index + 1; i < this.childrenSize; i++)
				// shift the rest of the keys down
				this.children[i - 1] = this.children[i];
			this.childrenSize--;
			this.children[this.childrenSize] = null;
			return value;
		}

		private int numberOfChildren() {

			return this.childrenSize;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String toString() {

			final StringBuilder builder = new StringBuilder();

			builder.append("keys=[");
			for (int i = 0; i < this.numberOfKeys(); i++) {
				final T value = this.getKey(i);
				builder.append(value);
				if (i < (this.numberOfKeys() - 1))
					builder.append(", ");
			}
			builder.append("]\n");

			if (this.parent != null) {
				builder.append("parent=[");
				for (int i = 0; i < this.parent.numberOfKeys(); i++) {
					final T value = this.parent.getKey(i);
					builder.append(value);
					if (i < (this.parent.numberOfKeys() - 1))
						builder.append(", ");
				}
				builder.append("]\n");
			}

			if (this.children != null)
				builder.append("keySize=")
					.append(this.numberOfKeys())
					.append(" children=")
					.append(this.numberOfChildren())
					.append("\n");

			return builder.toString();
		}
	}

	private static class TreePrinter {

		public static <T extends Comparable<T>> String getString(final BTree<T> tree) {

			if (tree.root == null)
				return "Tree has no nodes.";
			return TreePrinter.getString(tree.root, "", true);
		}

		private static <T extends Comparable<T>> String getString(final Node<T> node, final String prefix,
				final boolean isTail) {

			final StringBuilder builder = new StringBuilder();

			builder.append(prefix)
				.append((isTail
						? "└── "
						: "├── "));
			for (int i = 0; i < node.numberOfKeys(); i++) {
				final T value = node.getKey(i);
				builder.append(value);
				if (i < (node.numberOfKeys() - 1))
					builder.append(", ");
			}
			builder.append("\n");

			if (node.children != null) {
				for (int i = 0; i < (node.numberOfChildren() - 1); i++) {
					final Node<T> obj = node.getChild(i);
					builder.append(TreePrinter.getString(obj, prefix + (isTail
							? "    "
							: "│   "), false));
				}
				if (node.numberOfChildren() >= 1) {
					final Node<T> obj = node.getChild(node.numberOfChildren() - 1);
					builder.append(TreePrinter.getString(obj, prefix + (isTail
							? "    "
							: "│   "), true));
				}
			}

			return builder.toString();
		}
	}

	public static class JavaCompatibleBTree<T extends Comparable<T>> extends java.util.AbstractCollection<T> {

		private BTree<T> tree = null;

		public JavaCompatibleBTree(final BTree<T> tree) {

			this.tree = tree;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean add(final T value) {

			return this.tree.add(value);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean remove(final Object value) {

			return (this.tree.remove((T) value) != null);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean contains(final Object value) {

			return this.tree.contains((T) value);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int size() {

			return this.tree.size();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public java.util.Iterator<T> iterator() {

			return (new BTreeIterator<>(this.tree));
		}

		private static class BTreeIterator<C extends Comparable<C>> implements java.util.Iterator<C> {

			private BTree<C> tree = null;
			private BTree.Node<C> lastNode = null;
			private C lastValue = null;
			private int index = 0;
			private final Deque<BTree.Node<C>> toVisit = new ArrayDeque<>();

			protected BTreeIterator(final BTree<C> tree) {

				this.tree = tree;
				if ((tree.root != null) && (tree.root.keysSize > 0))
					this.toVisit.add(tree.root);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean hasNext() {

				if (((this.lastNode != null) && (this.index < this.lastNode.keysSize)) || (this.toVisit.size() > 0))
					return true;
				return false;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public C next() {

				if ((this.lastNode != null) && (this.index < this.lastNode.keysSize)) {
					this.lastValue = this.lastNode.getKey(this.index++);
					return this.lastValue;
				}
				while (this.toVisit.size() > 0) {
					// Go thru the current nodes
					final BTree.Node<C> n = this.toVisit.pop();

					// Add non-null children
					for (int i = 0; i < n.childrenSize; i++)
						this.toVisit.add(n.getChild(i));

					// Update last node (used in remove method)
					this.index = 0;
					this.lastNode = n;
					this.lastValue = this.lastNode.getKey(this.index++);
					return this.lastValue;
				}
				return null;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void remove() {

				if ((this.lastNode != null) && (this.lastValue != null)) {
					// On remove, reset the iterator (very inefficient, I know)
					this.tree.remove(this.lastValue, this.lastNode);

					this.lastNode = null;
					this.lastValue = null;
					this.index = 0;
					this.toVisit.clear();
					if ((this.tree.root != null) && (this.tree.root.keysSize > 0))
						this.toVisit.add(this.tree.root);
				}
			}
		}
	}
}
