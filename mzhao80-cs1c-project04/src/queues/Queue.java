package queues;

import java.util.*;

/**
 * Objects of type Queue manage items in a singly linked list that can enqueue() items to the end and dequeue() items
 * from the front of the queue.
 *
 * @param <T> The class of objects that the Queue will hold.
 */
public class Queue<T> implements Iterable<T> {
    /**
     * Name of this instance. Used for testing and debugging purposes.
     */
    private String name;
    /**
     * Points to front of the queue.
     */
    private Node head;
    /**
     * Points to end of the queue.
     */
    private Node tail;
    /**
     * Size of Queue
     */
    private int size = 0;
    /**
     * Modification counter (for use by iterator)
     */
    private int modCount = 0;

    /**
     * Creates a queue using a user-assigned name
     *
     * @param name Name of this queue
     */
    public Queue(String name) {
        this.name = name;
    }

    /**
     * Adds a generic item to the end of the queue.
     *
     * @param item Item to add to end of the queue.
     */
    public void enqueue(T item) {
        if (isEmpty()) {
            tail = new Node(item, null);
            head = tail;
        } else {
            tail = tail.insert(item);
        }
        size++;
        modCount++;
    }

    /**
     * Removes a generic item from the front of the queue and returns it.
     *
     * @return removed generic item, previously at the front of the queue.
     * @throws NoSuchElementException queue is empty.
     */
    public T dequeue() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T toReturn = head.data;
        head = head.next;
        size--;
        modCount++;
        return toReturn;
    }

    /**
     * Returns generic item at the front of the queue without removing it.
     *
     * @return generic item at the front of the queue
     */
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return head.data;
    }

    /**
     * Checks if the queue has no items.
     *
     * @return true if queue is empty, false if queue is not empty
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the queue.
     *
     * @return size of the queue
     */
    public int size() {
        return size;
    }

    /**
     * Accessor for the name of the queue.
     *
     * @return name of the queue.
     */
    public String getName() {
        return name;
    }

    /**
     * String representation of the queue and its included items.
     *
     * @return String representation of the queue and its included items.
     */
    public String toString() {
        if (isEmpty()) {
            return name + ":\n[ ]";
        }
        String toReturn = name + ":\n[";
        for (T song : this) {
            toReturn += song.toString() + ";\n";
        }
        return toReturn.substring(0, toReturn.length() - 2) + "]";
    }

    /**
     * Returns a new QueueIterator
     *
     * @return QueueIterator over this Queue
     */
    public Iterator<T> iterator() {
        return new QueueIterator();
    }

    /**
     * Instantiates Node objects used in the Queue.
     */
    private class Node {
        /**
         * Next item in queue.
         */
        Node next;
        /**
         * Data contained by node.
         */
        T data;

        /**
         * Creates a node for the Queue.
         *
         * @param x   Data in node
         * @param nxt Next node in Queue
         */
        Node(T x, Node nxt) {
            data = x;
            next = nxt;
        }

        /**
         * Inserts a new node after this.
         *
         * @param x The element to insert.
         * @return next The newly added node.
         */
        Node insert(T x) {
            next = new Node(x, next);
            return next;
        }

        /**
         * Removes next Node after this Node. Used for iterator.
         *
         * @return The recently removed item.
         */
        T removeNext() {
            T toRemove = next.data;
            next = next.next;
            return toRemove;
        }
    }

    /**
     * Implements an iterator to iterate over the Queue.
     */
    private class QueueIterator implements Iterator<T> {
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
         * Checks whether there are more items in the Queue
         *
         * @return true if Queue contains more items, false if it does not
         */
        public boolean hasNext() {
            return mCurrentIndex < size;
        }

        /**
         * Iterates to next item in the Queue and returns its value
         *
         * @return next item in the Queue
         */
        public T next() {
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
            size--;

            mLastNodeReturned = null;
        }

        /**
         * Constructor for iterator.
         */
        QueueIterator() {
            mCurrentNode = head;
            mCurrentIndex = 0;
        }
    }
}
