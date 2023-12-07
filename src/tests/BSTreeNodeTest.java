package tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utilities.BSTreeNode;

public class BSTreeNodeTest {
    private BSTreeNode<Integer> node;
    private BSTreeNode<Integer> left;
    private BSTreeNode<Integer> right;

    @Before
    public void setUp() {
        node = new BSTreeNode<>();
        left = new BSTreeNode<>(1);
        right = new BSTreeNode<>(3);
    }

    @After
    public void tearDown() {
        node = null;
        left = null;
        right = null;
    }

    @Test
    public void testBSTreeNodeNoArgumentConstructor() {
        Assert.assertNotNull("Construction failed", new BSTreeNode<>());
    }

    @Test
    public void testBSTreeNodeEConstructor() {
        node = new BSTreeNode<>(2);
        Assert.assertEquals("Construction failed", Integer.valueOf(2), node.getElement());
    }

    @Test
    public void testBSTreeNodeEConstructorWithLeftAndRight() {
        node = new BSTreeNode<>(2, left, right);
        Assert.assertNotNull("Construction failed", node);
        Assert.assertEquals("Left node incorrect", left, node.getLeft());
        Assert.assertEquals("Right node incorrect", right, node.getRight());
    }

    @Test
    public void testAddNull() {
        node = new BSTreeNode<>(2);
        Assert.assertFalse("Test for Adding Null value failed", node.add(null));
    }

    @Test
    public void testAddLeft() {
        node = new BSTreeNode<>(3);
        Assert.assertTrue("Test for Adding left node failed", node.add(left));
        Assert.assertEquals("Left node not added correctly", left, node.getLeft());
    }

    @Test
    public void testAddRight() {
        node = new BSTreeNode<>(1);
        Assert.assertTrue("Test for Adding right node failed", node.add(right));
        Assert.assertEquals("Right node not added correctly", right, node.getRight());
    }

    @Test
    public void testGetElement() {
        node = new BSTreeNode<>(2);
        Assert.assertEquals("Test for getElement failed", Integer.valueOf(2), node.getElement());
    }

    @Test
    public void testSetElement() {
        node = new BSTreeNode<>();
        node.setElement(1);
        Assert.assertEquals("Test for setElement failed", Integer.valueOf(1), node.getElement());
    }

    @Test
    public void testGetLeft() {
        node = new BSTreeNode<>(2, left, null);
        Assert.assertEquals("Test for getLeft failed", left, node.getLeft());
    }

    @Test
    public void testSetLeft() {
        node = new BSTreeNode<>(2);
        node.setLeft(left);
        Assert.assertEquals("Test for setLeft failed", left, node.getLeft());
    }

    @Test
    public void testGetRight() {
        node = new BSTreeNode<>(2, null, right);
        Assert.assertEquals("Test for getRight failed", right, node.getRight());
    }

    @Test
    public void testSetRight() {
        node = new BSTreeNode<>(2);
        node.setRight(right);
        Assert.assertEquals("Test for setRight failed", right, node.getRight());
    }

    @Test
    public void testHasLeft() {
        node = new BSTreeNode<>(2, left, null);
        Assert.assertTrue("Test for hasLeft failed", node.hasLeft());
    }

    @Test
    public void testHasRight() {
        node = new BSTreeNode<>(2, null, right);
        Assert.assertTrue("Test for hasRight failed", node.hasRight());
    }

    @Test
    public void testIsLeaf() {
        node = new BSTreeNode<>(2);
        Assert.assertTrue("Test for isLeaf failed", node.isLeaf());
    }

    @Test
    public void testGetHeight() {
        node = new BSTreeNode<>(2, left, right);
        BSTreeNode<Integer> newNode = new BSTreeNode<>(4);
        node.add(newNode);
        int expectedHeight = 3;
        Assert.assertEquals("Test for getHeight failed", expectedHeight, node.getHeight());
    }
}
