package com.codigo.aplios.data.struct;

import java.util.Arrays;

/**
 * In computer science, a stack is an abstract data type that serves as a
 * collection of elements, with two principal operations: push, which adds an
 * element to the collection, and pop, which removes the most recently added
 * element that was not yet removed.
 * <p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Stack_(abstract_data_type)">Stack
 *      (Wikipedia)</a> <br>
 * @author Justin Wetherell <phishman3579@gmail.com>
 */
@SuppressWarnings("unchecked")
public interface Stack<T> extends IStack<T> {

	/**
	 * This stack implementation is backed by an array.
	 * <p>
	 *
	 * @author Justin Wetherell <phishman3579@gmail.com>
	 */
	public static class ArrayStack<T> implements Stack<T> {

		private static final int MINIMUM_SIZE = 1024;

		private T[] array;
		private int size;

		private ArrayStack() {

			this.array = (T[]) new Object[ArrayStack.MINIMUM_SIZE];
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean push(final T value) {

			if (this.size >= this.array.length)
				this.grow();
			this.array[this.size++] = value;
			return true;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public T pop() {

			if (this.size <= 0)
				return null;

			final T t = this.array[--this.size];
			this.array[this.size] = null;

			final int shrinkSize = this.array.length >> 1;
			if ((shrinkSize >= ArrayStack.MINIMUM_SIZE) && (this.size < shrinkSize))
				this.shrink();

			return t;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public T peek() {

			if (this.size <= 0)
				return null;

			final T t = this.array[--this.size];
			return t;
		}

		/**
		 * Get item at index.
		 *
		 * @param index of item.
		 * @return T at index.
		 */
		public T get(final int index) {

			if ((index >= 0) && (index < this.size))
				return this.array[index];
			return null;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean remove(final T value) {

			for (int i = 0; i < this.size; i++) {
				final T obj = this.array[i];
				if (obj.equals(value))
					return (this.remove(i));
			}
			return false;
		}

		private boolean remove(final int index) {

			if (index != --this.size)
				// Shift the array down one spot
				System.arraycopy(this.array, index + 1, this.array, index, this.size - index);
			this.array[this.size] = null;

			final int shrinkSize = this.array.length >> 1;
			if ((shrinkSize >= ArrayStack.MINIMUM_SIZE) && (this.size < shrinkSize))
				this.shrink();

			return true;
		}

		// Grow the array by 50%
		private void grow() {

			final int growSize = this.size + (this.size << 1);
			this.array = Arrays.copyOf(this.array, growSize);
		}

		// Shrink the array by 50%
		private void shrink() {

			final int shrinkSize = this.array.length >> 1;
			this.array = Arrays.copyOf(this.array, shrinkSize);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void clear() {

			this.size = 0;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean contains(final T value) {

			for (int i = 0; i < this.size; i++) {
				final T obj = this.array[i];
				if (obj.equals(value))
					return true;
			}
			return false;
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

			int localSize = 0;
			for (int i = 0; i < this.array.length; i++) {
				final T t = this.array[i];
				if (i < this.size) {
					if (t == null)
						return false;
					localSize++;
				} else if (t != null)
					return false;
			}
			return (localSize == this.size);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public java.util.Queue<T> toLifoQueue() {

			return (new JavaCompatibleArrayStack<>(this));
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public java.util.Collection<T> toCollection() {

			return (new JavaCompatibleArrayStack<>(this));
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String toString() {

			final StringBuilder builder = new StringBuilder();
			for (int i = this.size - 1; i >= 0; i--)
				builder.append(this.array[i])
					.append(", ");
			return builder.toString();
		}
	}

	/**
	 * This stack implementation is backed by a linked list.
	 * <p>
	 *
	 * @author Justin Wetherell <phishman3579@gmail.com>
	 */
	public static class LinkedStack<T> implements Stack<T> {

		private Node<T> top;
		private int size;

		private LinkedStack() {

			this.top = null;
			this.size = 0;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean push(final T value) {

			return this.push(new Node<>(value));
		}

		/**
		 * Push node onto the stack.
		 *
		 * @param node to push on the stack.
		 */
		private boolean push(final Node<T> node) {

			if (this.top == null)
				this.top = node;
			else {
				final Node<T> oldTop = this.top;
				this.top = node;
				this.top.below = oldTop;
				oldTop.above = this.top;
			}
			this.size++;
			return true;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public T pop() {

			if (this.top == null)
				return null;

			final Node<T> nodeToRemove = this.top;
			this.top = nodeToRemove.below;
			if (this.top != null)
				this.top.above = null;

			final T value = nodeToRemove.value;
			this.size--;
			return value;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public T peek() {

			return (this.top != null)
					? this.top.value
					: null;
		}

		/**
		 * Get item at index.
		 *
		 * @param index of item.
		 * @return T at index.
		 */
		public T get(final int index) {

			Node<T> current = this.top;
			for (int i = 0; i < index; i++) {
				if (current == null)
					break;
				current = current.below;
			}
			return (current != null)
					? current.value
					: null;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean remove(final T value) {

			// Find the node
			Node<T> node = this.top;
			while ((node != null) && (!node.value.equals(value)))
				node = node.below;

			if (node == null)
				return false;

			return this.remove(node);
		}

		private boolean remove(final Node<T> node) {

			final Node<T> above = node.above;
			final Node<T> below = node.below;
			if ((above != null) && (below != null)) {
				above.below = below;
				below.above = above;
			} else if ((above != null) && (below == null))
				above.below = null;
			else if ((above == null) && (below != null)) {
				// Node is the top
				below.above = null;
				this.top = below;
			} else
				// prev==null && next==null
				this.top = null;
			this.size--;
			return true;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void clear() {

			this.top = null;
			this.size = 0;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean contains(final T value) {

			if (this.top == null)
				return false;
			Node<T> node = this.top;
			while (node != null) {
				if (node.value.equals(value))
					return true;
				node = node.below;
			}
			return false;
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

			final java.util.Set<T> keys = new java.util.HashSet<>();
			final Node<T> node = this.top;
			if (node != null) {
				keys.add(node.value);
				if (node.above != null)
					return false;
				Node<T> child = node.below;
				while (child != null) {
					if (!this.validate(child, keys))
						return false;
					child = child.below;
				}
			}
			return (keys.size() == this.size());
		}

		private boolean validate(final Node<T> node, final java.util.Set<T> keys) {

			if (node.value == null)
				return false;
			keys.add(node.value);

			final Node<T> child = node.below;
			if ((child != null) && !child.above.equals(node))
				return false;
			return true;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public java.util.Queue<T> toLifoQueue() {

			return (new JavaCompatibleLinkedStack<>(this));
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public java.util.Collection<T> toCollection() {

			return (new JavaCompatibleLinkedStack<>(this));
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public String toString() {

			final StringBuilder builder = new StringBuilder();
			Node<T> node = this.top;
			while (node != null) {
				builder.append(node.value)
					.append(", ");
				node = node.below;
			}
			return builder.toString();
		}

		private static class Node<T> {

			private T value = null;
			private Node<T> above = null;
			private Node<T> below = null;

			private Node(final T value) {

				this.value = value;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public String toString() {

				return "value=" + this.value
						+ " above="
						+ ((this.above != null)
								? this.above.value
								: "NULL")
						+ " below="
						+ ((this.below != null)
								? this.below.value
								: "NULL");
			}
		}
	}

	public static class JavaCompatibleArrayStack<T> extends java.util.AbstractQueue<T> {

		private ArrayStack<T> stack = null;

		public JavaCompatibleArrayStack(final ArrayStack<T> stack) {

			this.stack = stack;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean add(final T value) {

			return this.stack.push(value);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean remove(final Object value) {

			return this.stack.remove((T) value);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean contains(final Object value) {

			return this.stack.contains((T) value);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean offer(final T value) {

			return this.stack.push(value);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public T peek() {

			return this.stack.peek();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public T poll() {

			return this.stack.pop();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int size() {

			return this.stack.size();
		}

		/**
		 * {@inheritDoc}
		 *
		 * This iterator is NOT thread safe and is invalid when the data structure is
		 * modified.
		 */
		@Override
		public java.util.Iterator<T> iterator() {

			return (new ArrayStackIterator<>(this.stack));
		}

		private static class ArrayStackIterator<T> implements java.util.Iterator<T> {

			private ArrayStack<T> stack = null;
			private int last = -1;
			private int index = 0;

			private ArrayStackIterator(final ArrayStack<T> stack) {

				this.stack = stack;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean hasNext() {

				return ((this.index + 1) <= this.stack.size);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public T next() {

				if (this.index >= this.stack.size)
					return null;
				this.last = this.index;
				return this.stack.array[this.index++];
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void remove() {

				this.stack.remove(this.last);
			}
		}
	}

	public static class JavaCompatibleLinkedStack<T> extends java.util.AbstractQueue<T> {

		private LinkedStack<T> stack = null;

		public JavaCompatibleLinkedStack(final LinkedStack<T> stack) {

			this.stack = stack;
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean add(final T value) {

			return this.stack.push(value);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean remove(final Object value) {

			return this.stack.remove((T) value);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean contains(final Object value) {

			return this.stack.contains((T) value);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean offer(final T value) {

			return this.stack.push(value);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public T peek() {

			return this.stack.peek();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public T poll() {

			return this.stack.pop();
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public int size() {

			return this.stack.size();
		}

		/**
		 * {@inheritDoc}
		 *
		 * This iterator is NOT thread safe and is invalid when the data structure is
		 * modified.
		 */
		@Override
		public java.util.Iterator<T> iterator() {

			return (new LinkedStackIterator<>(this.stack));
		}

		private static class LinkedStackIterator<T> implements java.util.Iterator<T> {

			private LinkedStack<T> stack = null;
			private LinkedStack.Node<T> lastNode = null;
			private LinkedStack.Node<T> nextNode = null;

			private LinkedStackIterator(final LinkedStack<T> stack) {

				this.stack = stack;
				LinkedStack.Node<T> current = stack.top;
				while ((current != null) && (current.below != null))
					current = current.below;
				this.nextNode = current;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public boolean hasNext() {

				return (this.nextNode != null);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public T next() {

				final LinkedStack.Node<T> current = this.nextNode;
				if (current != null) {
					this.lastNode = this.nextNode;
					this.nextNode = current.above;
					return current.value;
				}
				return null;
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			public void remove() {

				this.stack.remove(this.lastNode);
			}
		}
	}
}