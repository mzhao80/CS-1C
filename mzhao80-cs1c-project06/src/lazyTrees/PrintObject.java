package lazyTrees;

/**
 * Implements Traverser interface. Prints a String representation of a given object being traversed
 *
 * @param <T> Generic type of the object that is to be printed
 */
public class PrintObject<T> implements Traverser<T> {
    /**
     * Prints a String representation of the given item
     *
     * @param item item to print
     */
    public void visit(T item) {
        System.out.print(item.toString() + " ");
    }
}
