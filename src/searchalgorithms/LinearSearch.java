package searchalgorithms;

import datastructures.RemcoList;
import models.Station;

public class LinearSearch implements SearchAlgorithm<RemcoList<Station>> {
    @Override
    public Station search(RemcoList<Station> stations, String searchTerm) {
        RemcoList.Node<Station> current = stations.getHead(); // Begin bij het hoofd van de lijst
        while (current != null) {
            if (current.getData().getCode().equalsIgnoreCase(searchTerm)) {
                // Station gevonden
                return current.getData();
            }
            current = current.getNext(); // Ga naar het volgende element
        }
        // Station niet gevonden
        return null;
    }
}
