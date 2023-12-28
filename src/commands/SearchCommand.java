package commands;

import core.ApplicationData;
import datastructures.RemcoBST;
import datastructures.RemcoList;
import datastructures.RemcoHashMap;
import models.Station;
import searchalgorithms.BinarySearch;
import searchalgorithms.LinearSearch;
import searchalgorithms.SearchAlgorithm;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class SearchCommand implements Command {
    private final ApplicationData appData;
    private final Scanner scanner;

    public SearchCommand(ApplicationData appData, Scanner scanner) {
        this.appData = appData;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Wat wil je gebruiken om te zoeken:");
        System.out.println("1. Binary Search");
        System.out.println("2. Linear Search");
        System.out.println("3. Hashmap Search");
        System.out.println("4. BST Search");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                performBinarySearch();
                break;
            case "2":
                performLinearSearch();
                break;
            case "3":
                performHashMapSearch();
                break;
            case "4":
                performBSTSearch();
                break;
            default:
                System.out.println("Ongeldige keuze.");
                break;
        }
    }


    private void performBinarySearch() {
        System.out.println("Voer de stationscode in voor Binary Search:");
        String code = scanner.nextLine();
        SearchAlgorithm<List<Station>> binarySearch = new BinarySearch();
        List<Station> stationsList = appData.getStations();
        stationsList.sort(Comparator.comparing(Station::getCode));
        Station foundStation = binarySearch.search(stationsList, code);
        if (foundStation != null) {
            System.out.println("Station gevonden: " + foundStation.getFullName());
        } else {
            System.out.println("Station niet gevonden.");
        }
    }

    private void performLinearSearch() {
        System.out.println("Voer de stationscode in voor Linear Search:");
        String code = scanner.nextLine();
        SearchAlgorithm<RemcoList<Station>> linearSearch = new LinearSearch();
        RemcoList<Station> stationsList = appData.getStationsRemcoList();
        Station foundStation = linearSearch.search(stationsList, code);
        if (foundStation != null) {
            System.out.println("Station gevonden: " + foundStation.getFullName());
        } else {
            System.out.println("Station niet gevonden.");
        }
    }

    private void performHashMapSearch() {
        System.out.println("Voer de stationscode in voor HashMap Search:");
        String code = scanner.nextLine();
        RemcoHashMap<String, Station> hashMap = new RemcoHashMap<>();

        //Vul hashmap
        for (Station station : appData.getStations()) {
            hashMap.put(station.getCode(), station);
        }

        // Tijdmeting starten
        long startTimeHashMap = System.nanoTime();
        Station foundStationHashMap = hashMap.get(code.toUpperCase());
        // Tijdmeting beëindigen
        long endTimeHashMap = System.nanoTime();
        // Bereken de duur in nanoseconden
        long durationInNanoHashMap = (endTimeHashMap - startTimeHashMap);

        if (foundStationHashMap != null) {
            System.out.println("Station gevonden: " + foundStationHashMap.getFullName() +
                    " in " + durationInNanoHashMap + " nanoseconden.");
        } else {
            System.out.println("Station niet gevonden. Zoektijd: " + durationInNanoHashMap + " nanoseconden.");
        }
    }


    private void performBSTSearch() {
        System.out.println("Voer de stationscode in voor BST Search:");
        String code = scanner.nextLine();
        RemcoBST bst = new RemcoBST();

        for (Station station : appData.getStations()) {
            bst.add(station);
        }

        // Tijdmeting starten
        long startTimeBST = System.nanoTime();
        Station foundStationBST = bst.findByCode(code.toUpperCase());
        // Tijdmeting beëindigen
        long endTimeBST = System.nanoTime();
        // Bereken de duur in nanoseconden
        long durationInNanoBST = (endTimeBST - startTimeBST);

        if (foundStationBST != null) {
            System.out.println("Station gevonden: " + foundStationBST.getFullName() +
                    " in " + durationInNanoBST + " nanoseconden.");
        } else {
            System.out.println("Station niet gevonden. Zoektijd: " + durationInNanoBST + " nanoseconden.");
        }

    }
}
