package lazyTrees;

/**
 * This interface imposes a requirement that objects of an implementing class be traversable.
 *
 * @param <E> Generic type to be traversed
 */
public interface Traverser<E> {
    /**
     * Visits an object. Can perform operations on object while visiting.
     *
     * @param x Object to be visited
     */
    void visit(E x);
}
