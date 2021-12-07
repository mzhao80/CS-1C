package lazyTrees;

import java.util.*;

/**
 * Binary Search Tree implementing lazy deletion
 *
 * @param <E> Generic type of item to be stored in tree
 */
public class LazySearchTree<E extends Comparable<? super E>> implements Cloneable {
    /**
     * Apparent size of the tree (excluding lazily deleted nodes.)
     */
    protected int mSize;
    /**
     * True size of the tree (including lazily deleted nodes.)
     */
    protected int mSizeHard;
    /**
     * Root of the tree
     */
    protected LazySTNode mRoot;

    /**
     * Constructor for tree
     */
    public LazySearchTree() {
        clear();
    }

    /**
     * Checks if tree is empty
     *
     * @return true if empty, false if not empty
     */
    public boolean empty() {
        return (mSize == 0);
    }

    /**
     * Returns apparent size of tree, excluding all lazily deleted nodes
     *
     * @return apparent size of tree, excluding all lazily deleted nodes
     */
    public int size() {
        return mSize;
    }

    /**
     * Returns true size of tree, including all lazily deleted nodes
     *
     * @return true size of tree, including all lazily deleted nodes
     */
    public int sizeHard() {
        return mSizeHard;
    }

    /**
     * Clears tree
     */
    public void clear() {
        mSize = 0;
        mSizeHard = 0;
        mRoot = null;
    }

    /**
     * Returns height of tree
     *
     * @return height of tree
     */
    public int showHeight() {
        return findHeight(mRoot, -1);
    }

    /**
     * Finds minimum value in tree
     *
     * @return minimum value in tree
     */
    public E findMin() {
        if (mRoot == null)
            throw new NoSuchElementException();
        return findMin(mRoot).data;
    }

    /**
     * Finds maximum value in tree
     *
     * @return maximum value in tree
     */
    public E findMax() {
        if (mRoot == null)
            throw new NoSuchElementException();
        return findMax(mRoot).data;
    }

    /**
     * Finds given item in tree. Throws NoSuchElementException if item does not exist in tree.
     *
     * @param x the item which we wish to find
     * @return the item if it is present
     * @throws NoSuchElementException thrown if node not found
     */
    public E find(E x) throws NoSuchElementException {
        LazySTNode resultNode;
        resultNode = find(mRoot, x);
        if (resultNode == null)
            throw new NoSuchElementException();
        return resultNode.data;
    }

    /**
     * Checks if the given item is contained within the tree
     *
     * @param x the item which we wish to determine if it is in the tree
     * @return true if the item is in the tree, false if it is not in the tree
     */
    public boolean contains(E x) {
        return find(mRoot, x) != null;
    }

    /**
     * Inserts a new item into the tree
     *
     * @param x item to be inserted
     * @return true if successful, false if not
     */
    public boolean insert(E x) {
        int oldSize = mSize;
        mRoot = insert(mRoot, x);
        return (mSize != oldSize);
    }

    /**
     * Removes an item from the tree
     *
     * @param x item to be removed
     * @return true if successful, false if not
     */
    public boolean remove(E x) {
        int oldSize = mSize;
        remove(mRoot, x);
        return (mSize != oldSize);
    }

    /**
     * Traverses through all nodes of the tree (including those lazily deleted) and applies the given functor
     *
     * @param func Functor to be applied to all nodes of the tree
     * @param <F>  Generic type of functor
     */
    public <F extends Traverser<? super E>>
    void traverseHard(F func) {
        traverseHard(func, mRoot);
    }

    /**
     * Traverses through valid nodes of the tree (excluding those lazily deleted) and applies the given functor
     *
     * @param func Functor to be applied to all nodes of the tree (excluding those lazily deleted)
     * @param <F>  Generic type of functor
     */
    public <F extends Traverser<? super E>>
    void traverseSoft(F func) {
        traverseSoft(func, mRoot);
    }

    /**
     * Clones this tree
     *
     * @return a clone of this tree
     * @throws CloneNotSupportedException thrown if clone not supported
     */
    public Object clone() throws CloneNotSupportedException {
        LazySearchTree<E> newObject = (LazySearchTree<E>) super.clone();
        newObject.clear();  // can't point to other's data

        newObject.mRoot = cloneSubtree(mRoot);
        newObject.mSize = mSize;
        newObject.mSizeHard = mSizeHard;

        return newObject;
    }

    // private helper methods ----------------------------------------

    /**
     * Finds the node containing the minimum value in the tree
     *
     * @param root Current root for traversal
     * @return Node containing the minimum value in the tree, or null if not found
     */
    protected LazySTNode findMin(LazySTNode root) {
        if (root == null)
            return null;
        if (root.lftChild == null) {
            if (!root.deleted) {
                return root;
            } else if (root.rtChild == null) {
                return null;
            }
            return findMin(root.rtChild);
        }
        return findMin(root.lftChild);
    }

    /**
     * Finds the node containing the maximum value in the tree
     *
     * @param root Current root for traversal
     * @return Node containing the maximum value in the tree, or null if not found
     */
    protected LazySTNode findMax(LazySTNode root) {
        if (root == null)
            return null;
        if (root.rtChild == null) {
            if (!root.deleted) {
                return root;
            } else if (root.lftChild == null) {
                return null;
            }
            return findMax(root.lftChild);
        }
        return findMax(root.rtChild);
    }

    /**
     * Inserts a new node into the tree
     *
     * @param root root of the tree
     * @param x    item to be inserted as a new node
     * @return inserted node
     */
    protected LazySTNode insert(LazySTNode root, E x) {
        int compareResult;  // avoid multiple calls to compareTo()

        if (root == null) {
            mSize++;
            mSizeHard++;
            return new LazySTNode(x, null, null);
        }

        compareResult = x.compareTo(root.data);
        if (compareResult < 0)
            root.lftChild = insert(root.lftChild, x);
        else if (compareResult > 0)
            root.rtChild = insert(root.rtChild, x);
        else {
            if (root.deleted) {
                root.deleted = false;
            }
            mSize++;
        }
        return root;
    }

    /**
     * Removes a node from the tree
     *
     * @param root Current root for traversal
     * @param x    item to be removed
     */
    protected void remove(LazySTNode root, E x) {
        int compareResult;  // avoid multiple calls to compareTo()

        if (root != null) {
            compareResult = x.compareTo(root.data);
            if (compareResult < 0)
                remove(root.lftChild, x);
            else if (compareResult > 0)
                remove(root.rtChild, x);
            else {
                // found the node
                root.deleted = true;
                mSize--;
            }
        }
    }

    /**
     * Traverses the tree and applies a functor to all nodes (including those lazily deleted)
     *
     * @param func     functor to be applied
     * @param treeNode current node to traverse
     * @param <F>      generic functor type
     */
    protected <F extends Traverser<? super E>>
    void traverseHard(F func, LazySTNode treeNode) {
        if (treeNode == null)
            return;

        traverseHard(func, treeNode.lftChild);
        func.visit(treeNode.data);
        traverseHard(func, treeNode.rtChild);
    }

    /**
     * Traverses the tree and applies a functor to valid nodes (excluding those lazily deleted)
     *
     * @param func     functor to be applied
     * @param treeNode current node to traverse
     * @param <F>      generic functor type
     */
    protected <F extends Traverser<? super E>>
    void traverseSoft(F func, LazySTNode treeNode) {
        if (treeNode == null)
            return;

        traverseSoft(func, treeNode.lftChild);
        if (!treeNode.deleted) {
            func.visit(treeNode.data);
        }
        traverseSoft(func, treeNode.rtChild);
    }

    /**
     * Finds a node with the given item.
     *
     * @param root Current root for traversal
     * @param x    Item to find
     * @return Node with the item to find, or null if item not found
     */
    protected LazySTNode find(LazySTNode root, E x) {
        int compareResult;  // avoid multiple calls to compareTo()

        if (root == null)
            return null;

        compareResult = x.compareTo(root.data);
        if (compareResult < 0)
            return find(root.lftChild, x);
        if (compareResult > 0)
            return find(root.rtChild, x);
        if (root.deleted) {
            return null;
        }
        return root;   // found
    }

    /**
     * Clones this subtree
     *
     * @param root root of subtree to be cloned
     * @return a clone of this subtree
     */
    protected LazySTNode cloneSubtree(LazySTNode root) {
        LazySTNode newNode;
        if (root == null)
            return null;

        newNode = new LazySTNode
                (
                        root.data,
                        cloneSubtree(root.lftChild),
                        cloneSubtree(root.rtChild)
                );
        return newNode;
    }

    /**
     * Finds the height of this tree
     *
     * @param treeNode Root for traversal
     * @param height   Height of this tree
     * @return Height of this tree
     */
    protected int findHeight(LazySTNode treeNode, int height) {
        int leftHeight, rightHeight;
        if (treeNode == null)
            return height;
        height++;
        leftHeight = findHeight(treeNode.lftChild, height);
        rightHeight = findHeight(treeNode.rtChild, height);
        return (leftHeight > rightHeight) ? leftHeight : rightHeight;
    }

    /**
     * Node helper class for LazySearchTree
     */
    private class LazySTNode {
        // use public access so the tree or other classes can access members
        /**
         * Left child node
         */
        public LazySTNode lftChild;
        /**
         * Right child node
         */
        public LazySTNode rtChild;
        /**
         * Data of node
         */
        public E data;
        /**
         * True if node has been lazily deleted, false if it has not
         */
        public boolean deleted;

        /**
         * Constructor for LazySTNode
         *
         * @param d   data of node
         * @param lft left child of node
         * @param rt  right child of node
         */
        public LazySTNode(E d, LazySTNode lft, LazySTNode rt) {
            lftChild = lft;
            rtChild = rt;
            data = d;
        }

        /**
         * Default constructor for LazySTNode
         */
        public LazySTNode() {
            this(null, null, null);
        }
    }

}
