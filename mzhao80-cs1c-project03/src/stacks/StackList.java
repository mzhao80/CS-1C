package stacks;

import java.util.*;

/**
 * Instantiates a StackList of String links for browser navigation.
 *
 * @param <E> Generic type of StackList
 */
public class StackList<E> implements Iterable<E> {
    /**
     * Name of StackList (used in printing)
     */
    private String name;
    /**
     * Head node
     */
    private Node top;
    /**
     * Size of StackList
     */
    private int mSize = 0;
    /**
     * Modification counter (for use by iterator)
     */
    private int modCount = 0;

    /**
     * Receives one parameter, the name of the stack. Initializes the two attributes name and top.
     *
     * @param name Name of this instance.
     */
    public StackList(String name) {
        this.name = name;
        top = new Node(null, null);
    }

    /**
     * Returns a new StackIterator
     *
     * @return StackIterator over this StackList
     */
    public Iterator<E> iterator() {
        return new StackIterator();
    }

    /**
     * Adds a generic item to the top of the stack.
     *
     * @param x generic item to be added to StackList
     */
    public void push(E x) {
        top.insert(x);
        mSize++;
        modCount++;
    }

    /**
     * Removes the item from the top of the stack and returns it.
     *
     * @return item that was at the top of the stack before removal.
     */
    public E pop() {
        if (mSize == 0) {
            throw new NoSuchElementException();
        }
        mSize--;
        modCount++;
        return top.removeNext();
    }

    /**
     * Returns the generic item at the top of the stack.
     *
     * @return item that is at the top of the stack.
     */
    public E peek() {
        if (mSize == 0) {
            return null;
        }
        return top.next.data;
    }

    /**
     * Clears the StackList
     */
    public void clear() {
        mSize = 0;
        top = new Node(null, null);
        modCount++;
    }

    /**
     * Prints the name of the StackList and the number of links in the stack
     *
     * @return Name of StackList and number of links in each stack
     */
    public String toString() {
        if (mSize == 0) {
            return String.format("%s with 0 links:\n[ ]", name);
        } else if (mSize == 1) {
            return String.format("%s with 1 link:\n[%s]", name, top.next.data);
        } else {
            String toReturn = String.format("%s with %s links:\n[", name, mSize);
            for (E link : this) {
                toReturn += link + ", ";
            }
            return toReturn.substring(0, toReturn.length() - 2) + "]";
        }
    }

    /**
     * Checks if the top of the stack is pointing to anything
     *
     * @return True if the stack is empty, False if it is not
     */
    public boolean isEmpty() {
        return mSize == 0;
    }

    /**
     * Returns number of elements in the StackList
     *
     * @return number of elements in StackList
     */
    public int size() {
        return mSize;
    }

    /**
     * Instantiates Node objects used in the StackList.
     */
    private class Node {
        /**
         * Next item in stack.
         */
        Node next;
        /**
         * Data contained by node.
         */
        E data;

        /**
         * Creates a node for the StackList.
         *
         * @param x   Data in node
         * @param nxt Next node in StackList
         */
        Node(E x, Node nxt) {
            next = nxt;
            data = x;
        }

        /**
         * Inserts a new node after this.
         *
         * @param x The element to insert.
         */
        void insert(E x) {
            next = new Node(x, next);
        }

        /**
         * Removes next node after this.
         *
         * @return The recently removed element.
         */
        E removeNext() {
            Node temp = next;
            next = next.next;
            return temp.data;
        }
    }

    /**
     * Implements an iterator to iterate over the StackList.
     */
    private class StackIterator implements Iterator<E> {
        /**
         * For use with remove
         */
        protected static final int NOT_VALID = -1;
        /**
         * For use with remove
         */
        protected static final int NEXT = 10;
        /**
         * Current node
         */
        protected Node mCurrentNode;
        /**
         * Current index of iterator
         */
        protected int mCurrentIndex;

        /**
         * for ConcurrentModificationExceptions
         */
        protected int mIterModCount = modCount;

        /**
         * for removal of node and IllegalStateExceptions
         */
        protected Node mLastNodeReturned = null;
        /**
         * for removal of node
         */
        protected Node mLast2NodeReturned = null;
        /**
         * for IllegalStateExceptions
         */
        protected int mLastIteration = NOT_VALID;

        /**
         * Checks whether there are more items in the StackList
         *
         * @return True if StackList contains more items, False if it does not
         */
        public boolean hasNext() {
            return mCurrentIndex < mSize;
        }

        /**
         * Iterates to next item in the StackList and returns its value
         *
         * @return next item in the StackList
         */
        public E next() {
            if (mIterModCount != modCount)
                throw new ConcurrentModificationException();
            if (!hasNext())
                throw new NoSuchElementException();
            mLast2NodeReturned = mLastNodeReturned;
            mLastNodeReturned = mCurrentNode;
            mCurrentNode = mCurrentNode.next;
            mCurrentIndex++;
            mLastIteration = NEXT;
            return mLastNodeReturned.data;
        }

        /**
         * Removes most previously returned item.
         */
        public void remove() {
            if (mIterModCount != modCount)
                throw new ConcurrentModificationException();
            if (mLastNodeReturned == null)
                throw new IllegalStateException();
            mLast2NodeReturned.removeNext();
            if (mLastIteration == NEXT)
                mCurrentIndex--;
            mSize--;

            mLastNodeReturned = null;
        }

        /**
         * Constructor for iterator.
         */
        StackIterator() {
            mCurrentNode = top.next;
            mCurrentIndex = 0;
        }
    }
}