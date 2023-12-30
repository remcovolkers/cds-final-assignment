package datastructures;

import java.util.ArrayList;
import java.util.List;

// Overgenomen / aangepast van:
// https://gist.github.com/flexelem/70b120ac9bf2965f419f - https://www.geeksforgeeks.org/min-heap-in-java/
public class RemcoMinHeap<T extends Comparable<T>> {
    private final List<T> items;

    public RemcoMinHeap() {
        this.items = new ArrayList<>();
    }

    public void add(T item) {
        items.add(item);
        siftUp();
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty.");
        }

        T item = items.getFirst();
        T lastItem = items.removeLast();

        if (!isEmpty()) {
            items.set(0, lastItem);
            siftDown();
        }

        return item;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is leeg!");
        }
        return items.getFirst();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    private void siftUp() {
        int index = items.size() - 1;
        while (hasParent(index) && getParent(index).compareTo(items.get(index)) > 0) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void siftDown() {
        int index = 0;
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && getRightChild(index).compareTo(getLeftChild(index)) < 0) {
                smallerChildIndex = getRightChildIndex(index);
            }
            if (items.get(index).compareTo(items.get(smallerChildIndex)) < 0) {
                break;
            } else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }

    private boolean hasParent(int i) {
        return i > 0;
    }

    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    private T getParent(int i) {
        return items.get(getParentIndex(i));
    }

    private boolean hasLeftChild(int i) {
        return getLeftChildIndex(i) < items.size();
    }

    private int getLeftChildIndex(int i) {
        return 2 * i + 1;
    }

    private T getLeftChild(int i) {
        return items.get(getLeftChildIndex(i));
    }

    private boolean hasRightChild(int i) {
        return getRightChildIndex(i) < items.size();
    }

    private int getRightChildIndex(int i) {
        return 2 * i + 2;
    }

    private T getRightChild(int i) {
        return items.get(getRightChildIndex(i));
    }

    private void swap(int i, int j) {
        T temp = items.get(i);
        items.set(i, items.get(j));
        items.set(j, temp);
    }
}
