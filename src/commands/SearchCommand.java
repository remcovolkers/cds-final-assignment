package commands;

import core.ApplicationData;
import datastructures.RemcoBST;
import datastructures.RemcoHashMap;
import datastructures.RemcoList;
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
        System.out.println("\n >> SEARCH MENU << \n");
        System.out.println("Wat wil je gebruiken om te zoeken:");
        System.out.println("1. Binary Search");
        System.out.println("2. Linear Search");
        System.out.println("3. Hashmap Search");
        System.out.println("4. BST Search");

        String invoer = scanner.nextLine();
        switch (invoer) {
            case "1":
                System.out.println("Voer de stationscode in voor Binary Search:");
                String codeBinair = scanner.nextLine();
                binairZoeken(codeBinair);
                break;
            case "2":
                System.out.println("Voer de stationscode in voor Linear Search:");
                String codeLineair = scanner.nextLine();
                lineairZoeken(codeLineair);
                break;
            case "3":
                System.out.println("Voer de stationscode in voor HashMap Search:");
                String codeHashMap = scanner.nextLine();
                hashMapZoeken(codeHashMap);
                break;
            case "4":
                System.out.println("Voer de stationscode in voor BST Search:");
                String codeBST = scanner.nextLine();
                bstZoeken(codeBST);
                break;
            default:
                System.out.println("Ongeldige keuze.");
                break;
        }
    }


    Station binairZoeken(String stationsCode) {
        SearchAlgorithm<List<Station>> binairZoeken = new BinarySearch();
        List<Station> stations = appData.getStations();
        stations.sort(Comparator.comparing(Station::getStationsCode));
        Station gevondenStation = binairZoeken.search(stations, stationsCode);
        if (gevondenStation != null) {
            System.out.println("Station gevonden: " + gevondenStation.getNaamVolledig());
            return gevondenStation;
        } else {
            System.out.println("Station niet gevonden.");
        }
        return null;
    }

    Station lineairZoeken(String stationsCode) {
        SearchAlgorithm<RemcoList<Station>> lineairZoeken = new LinearSearch();
        RemcoList<Station> stationsList = appData.getStationsRemcoList();
        Station gevondenStation = lineairZoeken.search(stationsList, stationsCode);
        if (gevondenStation != null) {
            System.out.println("Station gevonden: " + gevondenStation.getNaamVolledig());
            return gevondenStation;
        } else {
            System.out.println("Station niet gevonden.");
        }
        return null;
    }

    Station hashMapZoeken(String stationsCode) {
        RemcoHashMap<String, Station> hashMap = new RemcoHashMap<>();

        //Vul hashmap
        for (Station station : appData.getStations()) {
            hashMap.put(station.getStationsCode(), station);
        }

        // Tijdmeting starten
        long startTijdZoekenHashmap = System.nanoTime();
        Station foundStation = hashMap.get(stationsCode.toUpperCase());
        // Tijdmeting beëindigen
        long eindTijdZoekenHashmap = System.nanoTime();
        // Bereken de duur in nanoseconden
        long duurHashmapZoekActieNano = (eindTijdZoekenHashmap - startTijdZoekenHashmap);

        if (foundStation != null) {
            System.out.println("Station gevonden: " + foundStation.getNaamVolledig() +
                    " in " + duurHashmapZoekActieNano + " nanoseconden.");
            return foundStation;
        } else {
            System.out.println("Station niet gevonden. Zoektijd: " + duurHashmapZoekActieNano + " nanoseconden.");
        }
        return null;
    }


    Station bstZoeken(String code) {
        RemcoBST bst = new RemcoBST();
        for (Station station : appData.getStations()) {
            bst.add(station);
        }
        // Tijdmeting starten
        long startTijdBSTZoeken = System.nanoTime();
        Station foundStation = bst.vindMetCode(code.toUpperCase());
        // Tijdmeting beëindigen
        long eindTijdBSTZoeken = System.nanoTime();
        // Bereken de duur in nanoseconden
        long duurZoekActieBSTNano = (eindTijdBSTZoeken - startTijdBSTZoeken);

        if (foundStation != null) {
            System.out.println("Station gevonden: " + foundStation.getNaamVolledig() +
                    " in " + duurZoekActieBSTNano + " nanoseconden.");
            return foundStation;
        } else {
            System.out.println("Station niet gevonden. Zoektijd: " + duurZoekActieBSTNano + " nanoseconden.");
        }
        return null;
    }
}
