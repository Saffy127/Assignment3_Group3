package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import exceptions.TreeException;
import utilities.BSTree;
import utilities.BSTreeNode;
import utilities.Iterator;

public class BSTreeTests {

    private BSTree<Integer> tree;

    @Before
    public void setUp() {
        tree = new BSTree<>();
        tree.add(2);
        tree.add(4);
        tree.add(1);
        tree.add(3);
        tree.add(5);
    }

    @After
    public void tearDown() {
        tree.clear();
    }

    @Test
    public void testBSTree() {
        assertNotNull("Construction is built incorrectly", new BSTree<Integer>());
    }

    @Test
    public void testGetRoot() throws TreeException {
        BSTreeNode<Integer> actual = tree.getRoot();
        assertEquals("The root implemented incorrectly", Integer.valueOf(2), actual.getElement());
    }

    @Test(expected = TreeException.class)
    public void testGetRootWithEmptyTree() throws TreeException {
        new BSTree<Integer>().getRoot();
    }

    @Test
    public void testGetHeight() {
        assertEquals("The getHeight method implemented incorrectly", 3, tree.getHeight());
    }

    @Test
    public void testSize() {
        assertEquals("The size method is incorrectly implemented", 5, tree.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse("The isEmpty method is incorrectly implemented", tree.isEmpty());
    }

    @Test
    public void testClear() {
        tree.clear();
        assertTrue("The clear method is incorrectly implemented", tree.isEmpty());
    }

    @Test
    public void testContains() throws TreeException {
        assertTrue("The contains method is incorrectly implemented", tree.contains(5));
    }

    @Test
    public void testSearch() throws TreeException {
        BSTreeNode<Integer> actual = tree.search(5);
        assertNotNull("The search method is incorrectly implemented", actual);
        assertEquals("The search method is incorrectly implemented", Integer.valueOf(5), actual.getElement());
    }

    @Test
    public void testAdd() throws TreeException {
        assertTrue("The add method is incorrectly implemented", tree.contains(5));
        assertEquals("The add method is incorrectly implemented", 5, tree.size());
    }

    @Test
    public void testInorderIterator() {
        Iterator<Integer> iterator = tree.inorderIterator();
        Integer[] expected = new Integer[]{1, 2, 3, 4, 5};
        assertArrayEquals("The inorderIterator method is incorrectly implemented", expected, getIteratorContents(iterator));
    }

    @Test
    public void testPreorderIterator() {
        Iterator<Integer> iterator = tree.preorderIterator();
        Integer[] expected = new Integer[]{2, 1, 4, 3, 5};
        assertArrayEquals("The preorderIterator method is incorrectly implemented", expected, getIteratorContents(iterator));
    }

    @Test
    public void testPostorderIterator() {
        Iterator<Integer> iterator = tree.postorderIterator();
        Integer[] expected = new Integer[]{1, 3, 5, 4, 2};
        assertArrayEquals("The postorderIterator method is incorrectly implemented", expected, getIteratorContents(iterator));
    }

    private Integer[] getIteratorContents(Iterator<Integer> iterator) {
        java.util.ArrayList<Integer> contents = new java.util.ArrayList<>();
        while (iterator.hasNext()) {
            contents.add(iterator.next());
        }
        return contents.toArray(new Integer[0]);
    }
}
