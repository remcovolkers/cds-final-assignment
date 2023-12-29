package datastructures;

import advancedalgorithms.DijkstraAlgorithm.StationDistancePair;
import models.Station;

import java.util.ArrayList;
import java.util.List;

public class DijkstraMinHeap {
    private final List<StationDistancePair> items;

    public DijkstraMinHeap() {
        this.items = new ArrayList<>();
    }

    public void add(StationDistancePair item) {
        items.add(item);
        siftUp();
    }

    public StationDistancePair pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty.");
        }

        StationDistancePair item = items.getFirst();
        StationDistancePair lastItem = items.removeLast();
        if (!isEmpty()) {
            items.set(0, lastItem);
            siftDown();
        }

        return item;
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
            if (items.get(index).compareTo(items.get(smallerChildIndex)) > 0) {
                swap(index, smallerChildIndex);
            } else {
                break;
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

    private StationDistancePair getParent(int i) {
        return items.get(getParentIndex(i));
    }

    private boolean hasLeftChild(int i) {
        return getLeftChildIndex(i) < items.size();
    }

    private int getLeftChildIndex(int i) {
        return 2 * i + 1;
    }

    private StationDistancePair getLeftChild(int i) {
        return items.get(getLeftChildIndex(i));
    }

    private boolean hasRightChild(int i) {
        return getRightChildIndex(i) < items.size();
    }

    private int getRightChildIndex(int i) {
        return 2 * i + 2;
    }

    private StationDistancePair getRightChild(int i) {
        return items.get(getRightChildIndex(i));
    }

    private void swap(int i, int j) {
        StationDistancePair temp = items.get(i);
        items.set(i, items.get(j));
        items.set(j, temp);
    }

    private void siftUpFromIndex(int index) {
        while (hasParent(index) && getParent(index).compareTo(items.get(index)) > 0) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    public void decreaseKey(Station station, double newDistance) {
        // Vind het index van de StationDistancePair voor de gegeven station
        int index = -1;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).station().equals(station)) {
                index = i;
                break;
            }
        }

        // Als het station niet gevonden is of de nieuwe afstand is niet kleiner, doe niets
        if (index == -1 || items.get(index).distance() <= newDistance) {
            return;
        }

        // Update de afstand en sorteer de heap opnieuw
        items.set(index, new StationDistancePair(station, newDistance));
        siftUpFromIndex(index);
    }
}
