package edu.princeton.cs.algs4.exercise.c1_3;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Counter;
import edu.princeton.cs.algs4.LinkedStack;

/**
 * The <tt>Stack</tt> class represents a last-in-first-out (LIFO) stack of
 * generic items. It supports the usual <em>push</em> and <em>pop</em>
 * operations, along with methods for peeking at the top item, testing if the
 * stack is empty, and iterating through the items in LIFO order.
 * <p>
 * This implementation uses a singly-linked list with a static nested class for
 * linked-list nodes. See {@link LinkedStack} for the version from the textbook
 * that uses a non-static nested class. The <em>push</em>, <em>pop</em>,
 * <em>peek</em>, <em>size</em>, and <em>is-empty</em> operations all take
 * constant time in the worst case.
 * <p>
 * For additional documentation, see <a href="/algs4/13stacks">Section 1.3</a>
 * of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class Stack<Item> implements Iterable<Item> {
	private int N; // size of the stack
	private Node<Item> first; // top of stack
	private final Counter counter;

	// helper linked list class
	private static class Node<Item> {
		private Item item;
		private Node<Item> next;
	}

	/**
	 * Initializes an empty stack.
	 */
	public Stack() {
		first = null;
		N = 0;
		counter = new Counter("counter");
	}

	/**
	 * Is this stack empty?
	 * 
	 * @return true if this stack is empty; false otherwise
	 */
	public boolean isEmpty() {
		return first == null;
	}

	/**
	 * Returns the number of items in the stack.
	 * 
	 * @return the number of items in the stack
	 */
	public int size() {
		return N;
	}

	/**
	 * Adds the item to this stack.
	 * 
	 * @param item
	 *            the item to add
	 */
	public void push(Item item) {
		counter.increment();
		Node<Item> oldfirst = first;
		first = new Node<Item>();
		first.item = item;
		first.next = oldfirst;
		N++;
	}

	/**
	 * Removes and returns the item most recently added to this stack.
	 * 
	 * @return the item most recently added
	 * @throws java.util.NoSuchElementException
	 *             if this stack is empty
	 */
	public Item pop() {
		if (isEmpty())
			throw new NoSuchElementException("Stack underflow");
		counter.increment();
		Item item = first.item; // save item to return
		first = first.next; // delete first node
		N--;
		return item; // return the saved item
	}

	/**
	 * Returns (but does not remove) the item most recently added to this stack.
	 * 
	 * @return the item most recently added to this stack
	 * @throws java.util.NoSuchElementException
	 *             if this stack is empty
	 */
	public Item peek() {
		if (isEmpty())
			throw new NoSuchElementException("Stack underflow");
		return first.item;
	}

	/**
	 * Returns a string representation of this stack.
	 * 
	 * @return the sequence of items in the stack in LIFO order, separated by
	 *         spaces
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Item item : this)
			s.append(item + " ");
		return s.toString();
	}

	/**
	 * Returns an iterator to this stack that iterates through the items in LIFO
	 * order.
	 * 
	 * @return an iterator to this stack that iterates through the items in LIFO
	 *         order.
	 */
	@Override
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(first);
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator<Item> implements Iterator<Item> {
		private Node<Item> current;
		private final int currentCount;

		public ListIterator(Node<Item> first) {
			current = first;
			currentCount = counter.tally();
		}

		@Override
		public boolean hasNext() {
			if (currentCount != counter.tally())
				throw new ConcurrentModificationException();
			return current != null;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	/**
	 * Unit tests the <tt>Stack</tt> data type.
	 */
	public static void main(String[] args) {
		// Stack<String> s = new Stack<String>();
		// while (!StdIn.isEmpty()) {
		// String item = StdIn.readString();
		// if (!item.equals("-"))
		// s.push(item);
		// else if (!s.isEmpty())
		// StdOut.print(s.pop() + " ");
		// }
		// StdOut.println("(" + s.size() + " left on stack)");
		Stack<String> s = new Stack<String>();
		s.push("a");
		s.push("b");
		s.push("c");
		for (String str : s) {
			System.out.println(str);
			s.push("d");
		}
	}
}

/*************************************************************************
 * Copyright 2002-2012, Robert Sedgewick and Kevin Wayne.
 *
 * This file is part of algs4-package.jar, which accompanies the textbook
 *
 * Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne, Addison-Wesley
 * Professional, 2011, ISBN 0-321-57351-X. http://algs4.cs.princeton.edu
 *
 *
 * algs4-package.jar is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * algs4-package.jar is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * algs4-package.jar. If not, see http://www.gnu.org/licenses.
 *************************************************************************/

