package utilities;

import java.util.NoSuchElementException;
import java.util.Stack;
import exceptions.TreeException;


/**
 * This class represents a generic Binary Search Tree (BST).
 * It provides methods to manipulate the tree including adding elements,
 * searching for elements, and iterating through the tree in various orders.
 *
 * @param <E> The type of elements held in this BSTree.
 */

public class BSTree<E extends Comparable<? super E>> implements BSTreeADT<E> {

    private static final long serialVersionUID = 1L;
    private BSTreeNode<E> root;
    private int size;

    /**
     * Constructs an empty Binary Search Tree.
     */
    
    public BSTree() {
        root = null;
        size = 0;
    }
    
    /**
     * Retrieves the root node of the tree.
     *
     * @return The root node of the tree.
     * @throws TreeException if the root is null.
     */
    
    @Override
    public BSTreeNode<E> getRoot() throws TreeException {
        if (root == null) {
            throw new TreeException("The root is empty.");
        }
        return root;
    }
    
    /**
     * Calculates the height of the tree.
     *
     * @return The height of the tree.
     */

    @Override
    public int getHeight() {
        return root == null ? 0 : root.getHeight();
    }
    
    /**
     * Returns the number of elements in the tree.
     *
     * @return The size of the tree.
     */

    @Override
    public int size() {
        return size;
    }
    
    /**
     * Checks if the tree is empty.
     *
     * @return true if the tree is empty, false otherwise.
     */

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Clears the tree, removing all elements.
     */

    @Override
    public void clear() {
        root = null;
        size = 0;
    }
    
    /**
     * Checks if the tree contains a specific element.
     *
     * @param entry The element to be checked.
     * @return true if the element is in the tree, false otherwise.
     * @throws TreeException if the tree is empty.
     */

    @Override
    public boolean contains(E entry) throws TreeException {
        return search(entry) != null;
    }
    
    /**
     * Searches for a specific element in the tree.
     *
     * @param entry The element to be searched for.
     * @return The node containing the element, or null if not found.
     * @throws TreeException if the tree is empty.
     */

    @Override
    public BSTreeNode<E> search(E entry) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("The tree is empty.");
        }
        BSTreeNode<E> current = root;
        while (current != null) {
            int comparison = current.getElement().compareTo(entry);
            if (comparison == 0) {
                return current;
            }
            current = comparison > 0 ? current.getLeft() : current.getRight();
        }
        return null;
    }
    
    /**
     * Adds a new element to the tree.
     *
     * @param newEntry The element to be added.
     * @return true if the element is added successfully, false otherwise.
     * @throws NullPointerException if the new entry is null.
     */
    
    @Override
    public boolean add(E newEntry) throws NullPointerException {
        if (newEntry == null) {
            throw new NullPointerException("Cannot add null to the tree.");
        }
        if (root == null) {
            root = new BSTreeNode<>(newEntry, null, null);
            size++;
            return true;
        } else {
            return addHelper(newEntry, root);
        }
    }

    private boolean addHelper(E newEntry, BSTreeNode<E> node) {
        int comparison = newEntry.compareTo(node.getElement());
        if (comparison < 0) {
            if (node.getLeft() == null) {
                node.setLeft(new BSTreeNode<>(newEntry));
                size++;
                return true;
            } else {
                return addHelper(newEntry, node.getLeft());
            }
        } else if (comparison > 0) {
            if (node.getRight() == null) {
                node.setRight(new BSTreeNode<>(newEntry));
                size++;
                return true;
            } else {
                return addHelper(newEntry, node.getRight());
            }
        } else {
            return false;
        }
    }
    /**
     * Returns an iterator for traversing the tree in in-order.
     *
     * @return An iterator for in-order traversal.
     */

    @Override
    public utilities.Iterator<E> inorderIterator() {
        return new utilities.Iterator<E>() {
            Stack<BSTreeNode<E>> stack = new Stack<>();
            {
                pushLeftSubtree(root, stack);
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public E next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in iteration.");
                }
                BSTreeNode<E> node = stack.pop();
                pushLeftSubtree(node.getRight(), stack);
                return node.getElement();
            }
        };
    }

    private void pushLeftSubtree(BSTreeNode<E> node, Stack<BSTreeNode<E>> stack) {
        while (node != null) {
            stack.push(node);
            node = node.getLeft();
        }
    }

  
    @Override
    public utilities.Iterator<E> preorderIterator() {
        return new utilities.Iterator<E>() {
            Stack<BSTreeNode<E>> stack = new Stack<>();
            {
                if (root != null) {
                    stack.push(root);
                }
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }

            @Override
            public E next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in iteration.");
                }
                BSTreeNode<E> current = stack.pop();
                if (current.getRight() != null) stack.push(current.getRight());
                if (current.getLeft() != null) stack.push(current.getLeft());
                return current.getElement();
            }
        };
    }
    

    /**
     * Returns an iterator for traversing the tree in post-order.
     *
     * @return An iterator for post-order traversal.
     */
    
    @Override
    public utilities.Iterator<E> postorderIterator() {
        return new utilities.Iterator<E>() {
            Stack<BSTreeNode<E>> stack = new Stack<>();
            Stack<BSTreeNode<E>> output = new Stack<>();
            {
                if (root != null) {
                    stack.push(root);
                }
                while (!stack.isEmpty()) {
                    BSTreeNode<E> current = stack.pop();
                    output.push(current);
                    if (current.getLeft() != null) stack.push(current.getLeft());
                    if (current.getRight() != null) stack.push(current.getRight());
                }
            }

            @Override
            public boolean hasNext() {
                return !output.isEmpty();
            }

            @Override
            public E next() throws NoSuchElementException {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in iteration.");
                }
                return output.pop().getElement();
            }
        };
    }
}
