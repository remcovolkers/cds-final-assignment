package datastructures;

import models.Station;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RemcoHashMapTest {
    private RemcoHashMap<String, Station> map;

    @Before
    public void setUp() {
        map = new RemcoHashMap<>();
        map.put("001", new Station("001", "Station A", "slug-a", "NL", "type-a", 50.01, 4.01));
        map.put("002", new Station("002", "Station B", "slug-b", "BE", "type-b", 51.02, 4.02));
        map.put("003", new Station("003", "Station C", "slug-c", "DE", "type-c", 52.03, 4.03));
    }

    @Test
    public void testPutAndGet() {
        assertEquals("Station A", map.get("001").getNaamVolledig());
        assertEquals("Station B", map.get("002").getNaamVolledig());
        assertEquals("Station C", map.get("003").getNaamVolledig());
    }

    @Test
    public void testSize() {
        assertEquals(3, map.size());
    }

    @Test
    public void testCollisionHandling() {
        // Voeg twee stations toe met dezelfde hashcode (voor de test kun je ervoor zorgen dat de hashcode hetzelfde is)
        map.put("001A", new Station("001A", "Station A1", "slug-a", "NL", "type-a", 50.01, 4.01));
        map.put("001B", new Station("001B", "Station B1", "slug-b", "BE", "type-b", 51.02, 4.02));

        assertEquals("Station A1", map.get("001A").getNaamVolledig());
        assertEquals("Station B1", map.get("001B").getNaamVolledig());
    }

    @Test
    public void testReplaceValue() {
        map.put("001", new Station("001", "Station A New", "slug-a", "NL", "type-a", 50.01, 4.01));
        assertEquals("Station A New", map.get("001").getNaamVolledig());
        assertEquals(3, map.size());
    }
}
