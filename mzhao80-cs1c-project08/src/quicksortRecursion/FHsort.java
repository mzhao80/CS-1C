package quicksortRecursion;

public class FHsort {
    /**
     * Array size limit before Quicksort switches to Insertion Sort
     */
    protected static int QS_RECURSION_LIMIT = 0;

    /**
     * Mutator for recursion size limit
     *
     * @param limit Array size limit before Quicksort switches to Insertion Sort
     */
    public static void setRecursionLimit(int limit) {
        QS_RECURSION_LIMIT = limit;
    }

    /**
     * Quicksort implementation (public)
     *
     * @param a   Array to be sorted
     * @param <E> Generic type of element to be sorted
     */
    public static <E extends Comparable<? super E>> void quickSort(E[] a) {
        quickSort(a, 0, a.length - 1);
    }

    /**
     * Finds the pivot point to begin Quicksort.
     *
     * @param a     Array to be sorted
     * @param left  Leftmost value of array
     * @param right Rightmost value of array
     * @param <E>   Generic type of element to be sorted
     * @return Pivot point for Quicksort
     */
    protected static <E extends Comparable<? super E>> E median3(E[] a, int left, int right) {
        int center;
        E tmp;

        center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0) {
            tmp = a[center];
            a[center] = a[left];
            a[left] = tmp;
        }
        if (a[right].compareTo(a[left]) < 0) {
            tmp = a[right];
            a[right] = a[left];
            a[left] = tmp;
        }
        if (a[right].compareTo(a[center]) < 0) {
            tmp = a[right];
            a[right] = a[center];
            a[center] = tmp;
        }

        tmp = a[center];
        a[center] = a[right - 1];
        a[right - 1] = tmp;

        return a[right - 1];
    }

    /**
     * Protected implementation of Quicksort
     *
     * @param a     Array to be sorted
     * @param left  Left position for quicksort
     * @param right Right position for quicksort
     * @param <E>   Generic type of element to be sorted
     */
    protected static <E extends Comparable<? super E>> void quickSort(E[] a, int left, int right) {
        E pivot, tmp;
        int i, j;

        if (left + QS_RECURSION_LIMIT <= right) {
            pivot = median3(a, left, right);
            for (i = left, j = right - 1; ; ) {
                while (a[++i].compareTo(pivot) < 0)
                    ;
                while (pivot.compareTo(a[--j]) < 0)
                    ;
                if (i < j) {
                    tmp = a[i];
                    a[i] = a[j];
                    a[j] = tmp;
                } else
                    break;
            }

            tmp = a[i];
            a[i] = a[right - 1];
            a[right - 1] = tmp;

            quickSort(a, left, i - 1);
            quickSort(a, i + 1, right);
        } else
            insertionSort(a, left, right);
    }

    /**
     * Insertion sort to be used at end of quicksort
     *
     * @param a     Array to be sorted
     * @param start Start index for sorting
     * @param stop  Stop index for sorting
     * @param <E>   Generic type of element to be sorted
     */
    protected static <E extends Comparable<? super E>> void insertionSort(E[] a, int start, int stop) {
        int k, pos;
        E tmp;

        for (pos = start + 1; pos <= stop; pos++) {
            tmp = a[pos];
            for (k = pos; k > 0 && tmp.compareTo(a[k - 1]) < 0; k--)
                a[k] = a[k - 1];
            a[k] = tmp;
        }
    }
}

