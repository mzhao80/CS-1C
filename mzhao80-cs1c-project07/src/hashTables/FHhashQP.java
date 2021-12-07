package hashTables;

/**
 * A class that implements a HashTable
 *
 * @param <E> Generic class of item to be used in HashTable
 */
public class FHhashQP<E> {
    /**
     * Flag value for if this is active
     */
    protected static final int ACTIVE = 0;
    /**
     * Flag value for if this is empty
     */
    protected static final int EMPTY = 1;
    /**
     * Flag value for if this is deleted
     */
    protected static final int DELETED = 2;
    /**
     * Default table size
     */
    static final int INIT_TABLE_SIZE = 97;
    /**
     * Default max load factor
     */
    static final double INIT_MAX_LAMBDA = 0.49;
    /**
     * HashEntry array for data
     */
    protected HashEntry<E>[] mArray;
    /**
     * Size
     */
    protected int mSize;
    /**
     * Load size
     */
    protected int mLoadSize;
    /**
     * HashTable size
     */
    protected int mTableSize;
    /**
     * Maximum load factor
     */
    protected double mMaxLambda;

    /**
     * Constructor for FHhashQP
     *
     * @param tableSize requested initial table size (should be larger than default size)
     */
    public FHhashQP(int tableSize) {
        mLoadSize = mSize = 0;
        if (tableSize < INIT_TABLE_SIZE)
            mTableSize = INIT_TABLE_SIZE;
        else
            mTableSize = nextPrime(tableSize);

        allocateArray();  // uses mTableSize;
        mMaxLambda = INIT_MAX_LAMBDA;
    }

    /**
     * Default constructor for FHhashQP
     */
    public FHhashQP() {
        this(INIT_TABLE_SIZE);
    }

    /**
     * Inserts a new item into the HashTable
     *
     * @param x Item to be inserted
     * @return Whether the insertion was successful
     */
    public boolean insert(E x) {
        int bucket = findPos(x);

        if (mArray[bucket].state == ACTIVE)
            return false;

        mArray[bucket].data = x;
        mArray[bucket].state = ACTIVE;
        mSize++;

        // check load factor
        if (++mLoadSize > mMaxLambda * mTableSize)
            rehash();

        return true;
    }

    /**
     * Removes an item from the HashTable
     *
     * @param x Item to be removed
     * @return Whether the removal was successful
     */
    public boolean remove(E x) {
        int bucket = findPos(x);

        if (mArray[bucket].state != ACTIVE)
            return false;

        mArray[bucket].state = DELETED;
        mSize--; // mLoadSize not dec'd because it counts any non-EMP location
        return true;
    }

    /**
     * Checks whether the HashTable contains a given item
     *
     * @param x Item to be checked for existence in HashTable
     * @return Whether the item is in the HashTable
     */
    public boolean contains(E x) {
        return mArray[findPos(x)].state == ACTIVE;
    }

    /**
     * Accessor for HashTable size
     *
     * @return HashTable size
     */
    public int size() {
        return mSize;
    }

    /**
     * Clears the HashTable
     */
    void makeEmpty() {
        int k, size = mArray.length;

        for (k = 0; k < size; k++)
            mArray[k].state = EMPTY;
        mSize = mLoadSize = 0;
    }

    /**
     * Sets maximum load factor
     *
     * @param lam requested load factor
     * @return True if successful, false if failed
     */
    public boolean setMaxLambda(double lam) {
        if (lam < .1 || lam > INIT_MAX_LAMBDA)
            return false;
        mMaxLambda = lam;
        return true;
    }

    // protected methods of class ----------------------

    /**
     * Returns the position of an item in the HashTable
     *
     * @param x Item to be found
     * @return Position of the item
     */
    int findPos(E x) {
        int kthOddNum = 1;
        int index = myHash(x);

        while (mArray[index].state != EMPTY
                && !mArray[index].data.equals(x)) {
            index += kthOddNum; // k squared = (k-1) squared + kth odd #
            kthOddNum += 2;     // compute next odd #
            if (index >= mTableSize)
                index -= mTableSize;
        }
        return index;
    }

    /**
     * Rehashes the HashTable into a larger HashTable
     */
    protected void rehash() {
        // we save old list and size then we can reallocate freely
        HashEntry<E>[] oldArray = mArray;
        int k, oldTableSize = mTableSize;

        mTableSize = nextPrime(2 * oldTableSize);

        // allocate a larger, empty array
        allocateArray();  // uses mTableSize;

        // use the insert() algorithm to re-enter old data
        mSize = mLoadSize = 0;
        for (k = 0; k < oldTableSize; k++)
            if (oldArray[k].state == ACTIVE)
                insert(oldArray[k].data);
    }

    /**
     * HashCode
     *
     * @param x Item to be hashed
     * @return HashCode for the provided item
     */
    protected int myHash(E x) {
        int hashVal;

        hashVal = x.hashCode() % mTableSize;
        if (hashVal < 0)
            hashVal += mTableSize;

        return hashVal;
    }

    /**
     * Finds the next prime greater than this integer
     *
     * @param n An integer
     * @return next prime greater than the provided integer
     */
    protected static int nextPrime(int n) {
        int k, candidate, loopLim;

        // loop doesn't work for 2 or 3
        if (n <= 2)
            return 2;
        else if (n == 3)
            return 3;

        for (candidate = (n % 2 == 0) ? n + 1 : n; true; candidate += 2) {
            // all primes > 3 are of the form 6k +/- 1
            loopLim = (int) ((Math.sqrt((double) candidate) + 1) / 6);

            // we know it is odd.  check for divisibility by 3
            if (candidate % 3 == 0)
                continue;

            // now we can check for divisibility of 6k +/- 1 up to sqrt
            for (k = 1; k <= loopLim; k++) {
                if (candidate % (6 * k - 1) == 0)
                    break;
                if (candidate % (6 * k + 1) == 0)
                    break;
            }
            if (k > loopLim)
                return candidate;
        }
    }

    /**
     * Initializes the HashTable
     */
    void allocateArray() {
        int k;

        mArray = new HashEntry[mTableSize];
        for (k = 0; k < mTableSize; k++)
            mArray[k] = new HashEntry<>();
    }
}

