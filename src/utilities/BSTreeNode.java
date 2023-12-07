
package utilities;

import java.io.Serializable;

/**
 * Represents a node in a binary search tree (BST).
 * Each node stores a data element and references to its left and right children.
 *
 * @param <E> the type of the data stored in the node
 */
public class BSTreeNode<E extends Comparable<? super E>> implements Serializable {
    private static final long serialVersionUID = 1L;

    private E element;
    private BSTreeNode<E> left, right;

    /**
     * Constructs an empty BSTreeNode with no data and no child nodes.
     */
    public BSTreeNode() {
        this(null, null, null);
    }

    /**
     * Constructs a BSTreeNode with specified data and no child nodes.
     *
     * @param element The data to be stored in the node.
     */
    public BSTreeNode(E element) {
        this(element, null, null);
    }

    /**
     * Constructs a BSTreeNode with specified data and left and right children.
     *
     * @param element The data to be stored in the node.
     * @param left    The left child of the node.
     * @param right   The right child of the node.
     */
    public BSTreeNode(E element, BSTreeNode<E> left, BSTreeNode<E> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    /**
     * Adds a new node to the subtree of this node.
     *
     * @param newNode The new node to be added.
     * @return true if the node was added, false if not (e.g., if newNode is null).
     */
    public boolean add(BSTreeNode<E> newNode) {
        if (newNode == null) {
            return false;
        }
        int comparison = newNode.getElement().compareTo(element);
        if (comparison <= 0) {
            if (left == null) {
                left = newNode;
            } else {
                left.add(newNode);
            }
        } else {
            if (right == null) {
                right = newNode;
            } else {
                right.add(newNode);
            }
        }
        return true;
    }

    /**
     * Retrieves the data element stored in this node.
     *
     * @return The data element of this node.
     */
    public E getElement() {
        return element;
    }

    /**
     * Sets or updates the data element of this node.
     *
     * @param element The new data element to be stored in the node.
     */
    public void setElement(E element) {
        this.element = element;
    }

    /**
     * Gets the left child of this node.
     *
     * @return The left child node.
     */
    public BSTreeNode<E> getLeft() {
        return left;
    }

    /**
     * Sets the left child of this node.
     *
     * @param left The node to be set as the left child.
     */
    public void setLeft(BSTreeNode<E> left) {
        this.left = left;
    }

    /**
     * Gets the right child of this node.
     *
     * @return The right child node.
     */
    public BSTreeNode<E> getRight() {
        return right;
    }

    /**
     * Sets the right child of this node.
     *
     * @param right The node to be set as the right child.
     */
    public void setRight(BSTreeNode<E> right) {
        this.right = right;
    }

    /**
     * Checks if this node has a left child.
     *
     * @return true if this node has a left child, false otherwise.
     */
    public boolean hasLeft() {
        return left != null;
    }

    /**
     * Checks if this node has a right child.
     *
     * @return true if this node has a right child, false otherwise.
     */
    public boolean hasRight() {
        return right != null;
    }

    /**
     * Checks if this node is a leaf (has no children).
     *
     * @return true if this node is a leaf, false otherwise.
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }

    /**
     * Computes the height of the subtree rooted at this node.
     *
     * @return The height of the subtree.
     */
    public int getHeight() {
        int leftHeight = left != null ? left.getHeight() : 0;
        int rightHeight = right != null ? right.getHeight() : 0;
        return 1 + Math.max(leftHeight, rightHeight);
    }
}
