package hashTables;

/**
 * A wrapper for an item in a HashTable
 *
 * @param <E> Generic class of item
 */
public class HashEntry<E> {
    /**
     * Data to be contained
     */
    public E data;
    /**
     * Flag for active, empty, or deleted
     */
    public int state;

    /**
     * Constructor for HashEntry
     *
     * @param x  Item to be contained
     * @param st State of HashTable entry
     */
    public HashEntry(E x, int st) {
        data = x;
        state = st;
    }

    /**
     * Default constructor for HashEntry
     */
    public HashEntry() {
        this(null, FHhashQP.EMPTY);
    }
}
