package searchalgorithms;

import datastructures.RemcoList;
import models.Station;

public class LinearSearch implements SearchAlgorithm<RemcoList<Station>> {
    //Inspiratie van https://www.geeksforgeeks.org/linear-search/ en vooral de sheets
    @Override
    public Station search(RemcoList<Station> stations, String searchTerm) {
        RemcoList.Node<Station> huidigStation = stations.getHead(); // Begin bij het eerste element van de lijst
        while (huidigStation != null) {
            if (huidigStation.getData().getStationsCode().equalsIgnoreCase(searchTerm)) {
                // Station gevonden
                return huidigStation.getData();
            }
            huidigStation = huidigStation.getNext(); // Ga naar het volgende element
        }
        // Station niet gevonden
        return null;
    }
}
