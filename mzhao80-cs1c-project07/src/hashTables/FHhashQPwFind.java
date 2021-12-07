package hashTables;

import java.util.*;

/**
 * Implements key find methods for the HashTable
 *
 * @param <KeyType> Generic type of key in HashTable
 * @param <E>       Generic type of item in HashTable
 */
public class FHhashQPwFind<KeyType, E extends Comparable<KeyType>>
        extends FHhashQP<E> {
    /**
     * Default constructor initializing HashTable with default size
     */
    public FHhashQPwFind() {
        super();
    }

    /**
     * Constructor initializing HashTable with specified size
     *
     * @param tableSize Requested initial HashTable size (should be larger than default size)
     */
    public FHhashQPwFind(int tableSize) {
        super(tableSize);
    }

    /**
     * Finds an item given its key
     *
     * @param key Key for the requested item
     * @return Requested item
     * @throws NoSuchElementException
     */
    public E find(KeyType key) throws NoSuchElementException {
        HashEntry entry = mArray[findPosKey(key)];
        if (entry.state == ACTIVE) {
            return (E) entry.data;
        }
        throw new NoSuchElementException();
    }

    /**
     * Finds the HashTable array index of an item given its key
     *
     * @param key Key for the requested item
     * @return HashTable array index of requested item based on its key
     */
    protected int findPosKey(KeyType key) {
        int kthOddNum = 1;
        int index = myHashKey(key);
        while (mArray[index].state != EMPTY && !mArray[index].data.equals(key)) {
            index += kthOddNum;
            kthOddNum += 2;
            if (index >= mTableSize)
                index -= mTableSize;
        }
        return index;
    }

    /**
     * HashCode for key
     *
     * @param key Key for item in HashTable
     * @return HashCode for key
     */
    protected int myHashKey(KeyType key) {
        int hashVal = key.hashCode() % mTableSize;
        if (hashVal < 0) {
            hashVal += mTableSize;
        }
        return hashVal;
    }

}
