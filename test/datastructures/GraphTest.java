package datastructures;

import models.Station;
import models.Track;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GraphTest {
    private Graph graph;

    @Before
    public void setUp() {
        graph = new Graph();

        // Voeg stations toe
        Station stationA = new Station("STA", "Station A", "slug-a", "CountryA", "type-a", 50.01, 4.01);
        Station stationB = new Station("STB", "Station B", "slug-b", "CountryB", "type-b", 51.02, 4.02);
        graph.addStation(stationA);
        graph.addStation(stationB);

        // Voeg track toe
        graph.addTrack(new Track(stationA, stationB, true));
    }

    @Test
    public void testAddEnGetStations() {
        assertEquals("Aantal stations komt niet overeen", 2, graph.getStations().size());
        assertNotNull("Station STA moet aanwezig zijn", graph.getStation("STA"));
        assertNotNull("Station STB moet aanwezig zijn", graph.getStation("STB"));
    }

    @Test
    public void testGetAlleTracks() {
        List<Track> allTracks = graph.getAlleTracks();
        assertNotNull("Tracks lijst mag niet null zijn", allTracks);
        assertFalse("Tracks lijst mag niet leeg zijn", allTracks.isEmpty());
        assertEquals("Aantal tracks komt niet overeen", 1, allTracks.size());
    }

    @Test
    public void testToGraphVizFormat() {
        String graphViz = graph.toGraphViz();
        assertTrue("GraphViz string moet de verbinding bevatten", graphViz.startsWith("digraph G {"));
        assertTrue("GraphViz string moet de verbinding bevatten", graphViz.contains("\"STA\" -> \"STB\""));
    }

    @Test
    public void testToString() {
        String description = graph.toString();
        assertTrue("Beschrijving moet aantal stations bevatten", description.contains("2 stations"));
        assertTrue("Beschrijving moet aantal verbindingen bevatten", description.contains("1 verbindingen"));
    }
}
