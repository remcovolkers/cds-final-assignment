package advancedalgorithms;

import datastructures.Graph;
import models.Station;
import models.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AStarAlgorithmTest {
    private Station startStation;
    private Station endStation;

    private Station intermediateStation;
    private AStarAlgorithm aStarAlgorithm;

    @BeforeEach
    void setUp() {
        // Maak stations
        startStation = new Station("STA", "Start Station", "slug-a", "CountryA", "type-a", 50.01, 4.01);
        endStation = new Station("STB", "End Station", "slug-b", "CountryB", "type-b", 51.02, 4.02);
        intermediateStation = new Station("STC", "Intermediate Station", "slug-c", "CountryC", "type-c", 51.00, 4.03);

        // Maak tracks
        ArrayList<Track> tracks = new ArrayList<>();
        tracks.add(new Track(startStation, intermediateStation, false));
        tracks.add(new Track(intermediateStation, endStation, false));

        // Maak graph en voeg stations en tracks toe
        Graph graph = new Graph();
        graph.addStation(startStation);
        graph.addStation(endStation);
        graph.addStation(intermediateStation);
        for (Track track : tracks) {
            graph.addTrack(track);
        }

        // Initialiseer A* algoritme
        aStarAlgorithm = new AStarAlgorithm(graph, endStation, tracks);
    }

    @Test
    void testAStarAlgorithm() {
        // Voer A* algoritme uit
        aStarAlgorithm.execute(startStation);

        // Test of het pad correct is gereconstrueerd
        Station currentStation = endStation;
        List<Station> path = new ArrayList<>();
        while (currentStation != null) {
            path.addFirst(currentStation); // Voeg aan begin toe voor correcte volgorde
            currentStation = aStarAlgorithm.getCameFrom().get(currentStation);
        }

        // Verwacht dat het pad startStation -> intermediateStation -> endStation is
        assertNotNull(path);
        assertEquals(3, path.size());
        assertEquals(startStation, path.get(0));
        assertEquals(intermediateStation, path.get(1));
        assertEquals(endStation, path.get(2));
    }
}
