project folder:
mzhao80-cs1c-project06/


Brief description of submitted files:

src/lazyTrees/SuperMarket.java
    - Simulates the market scenario of buying and adding items to a store's inventory. Implements BST with lazy deletion
     to keep track of total inventory ("deleted" + non deleted) and current inventory (non deleted only).

src/lazyTrees/LazySearchTree.java
    - Binary Search Tree implementing lazy deletion
        - LazySTNode is the inner node class used by LazySearchTree

src/lazyTrees/Item.java
    - One object of Item class represents one item in the inventory, with two class members.

src/lazyTrees/Traverser.java
    - This interface imposes a requirement that objects of an implementing class be traversable.

src/lazyTrees/PrintObject.java
    - Implements Traverser interface. Prints a String representation of a given object being traversed

resources/RUN.txt
    - console output of SuperMarket.java

resources/inventory_log.txt
    - Tests the LazySearchTree by adding and removing items from the inventory

resources/inventory_short.txt
    - Short inventory file to test for removal of root node from LazySearchTree

resources/inventory_invalid_removal.txt
    - An example of testing the boundary condition when removing an item that may not exist

resources/test1.txt
    - First test of adding several items and then removing them all.

resources/test2.txt
    - Second test of adding several items and then removing them all.

resources/test3.txt
    - Testing the removal of an item not in the tree.