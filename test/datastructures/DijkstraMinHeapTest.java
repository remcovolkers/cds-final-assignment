package datastructures;

import advancedalgorithms.DijkstraAlgorithm;
import models.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraMinHeapTest {
    private DijkstraMinHeap heap;

    @BeforeEach
    void setUp() {
        heap = new DijkstraMinHeap();
    }

    @Test
    void addAndPopTest() {
        Station station1 = new Station("STA", "Station A", "slug-a", "CountryA", "type-a", 50.01, 4.01);
        Station station2 = new Station("STB", "Station B", "slug-b", "CountryB", "type-b", 51.02, 4.02);

        heap.add(new DijkstraAlgorithm.StationDistancePair(station1, 10.0));
        heap.add(new DijkstraAlgorithm.StationDistancePair(station2, 20.0));


        assertEquals(10.0, heap.pop().distance());
        assertEquals(20.0, heap.pop().distance());
        assertTrue(heap.isEmpty());
    }

    @Test
    void decreaseKeyTest() {
        Station station1 = new Station("STA", "Station A", "slug-a", "CountryA", "type-a", 50.01, 4.01);
        Station station2 = new Station("STB", "Station B", "slug-b", "CountryB", "type-b", 51.02, 4.02);

        heap.add(new DijkstraAlgorithm.StationDistancePair(station1, 10.0));
        heap.add(new DijkstraAlgorithm.StationDistancePair(station2, 20.0));

        heap.decreaseKey(station2, 5.0);

        assertEquals(5.0, heap.pop().distance());
        assertEquals(10.0, heap.pop().distance());
        assertTrue(heap.isEmpty());
    }

    @Test
    void popFromEmptyHeapTest() {
        assertThrows(IllegalStateException.class, () -> {
            heap.pop();
        });
    }

    @Test
    void decreaseKeyNonExistingStationTest() {
        Station station1 = new Station("STA", "Station A", "slug-a", "CountryA", "type-a", 50.01, 4.01);
        heap.add(new DijkstraAlgorithm.StationDistancePair(station1, 10.0));

        heap.decreaseKey(new Station("STB", "Station B", "slug-b", "CountryB", "type-b", 51.02, 4.02), 20);

        assertEquals(10.0, heap.pop().distance());
        assertTrue(heap.isEmpty());
    }
}
