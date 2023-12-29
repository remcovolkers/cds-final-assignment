package datastructures;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RemcoMinHeapTest {
    private RemcoMinHeap<Integer> minHeap;

    @Before
    public void setUp() {
        minHeap = new RemcoMinHeap<>();
    }

    @Test
    public void testAdd() {
        minHeap.add(4);
        minHeap.add(5);
        minHeap.add(8);
        minHeap.add(2);
        minHeap.add(1);
        minHeap.add(9);
        assertEquals(Integer.valueOf(1), minHeap.peek());
    }

    @Test
    public void testRemove() {
        minHeap.add(4);
        minHeap.add(5);
        minHeap.add(8);
        minHeap.add(2);
        minHeap.add(1);
        minHeap.add(9);
        assertEquals(Integer.valueOf(1), minHeap.pop());
        assertEquals(Integer.valueOf(2), minHeap.pop());
        assertEquals(Integer.valueOf(4), minHeap.peek());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(minHeap.isEmpty());
        minHeap.add(4);
        assertFalse(minHeap.isEmpty());
    }

    @Test(expected = IllegalStateException.class)
    public void testRemoveFromEmptyHeap() {
        assertTrue(minHeap.isEmpty());
        minHeap.pop();
    }
}
