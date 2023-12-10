package searchalgorithms;

import models.Station;

import java.util.List;

public interface SearchAlgorithm {
    Station search(List<Station> stations, String searchTerm);
}
