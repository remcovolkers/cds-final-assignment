import datastructures.RemcoList;
import models.Station;
import searchalgorithms.BinarySearch;
import searchalgorithms.LinearSearch;
import searchalgorithms.SearchAlgorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Application app = new Application();
        app.initializeArrays();

        System.out.println("Welkom bij de Spoormanager-applicatie!");

        while (true) {
            System.out.println("\nKies een optie:");
            System.out.println("1. Bepaal de kortste route tussen twee stations");
            System.out.println("2. Bepaal de minimum cost spanning tree binnen een rechthoek van GPS-coördinaten");
            System.out.println("3. Toon het spoornetwerk op de kaart");
            System.out.println("4. Zoek een station (binarySearch)");
            System.out.println("5. Zoek een station (linearSearch)");
            System.out.println("0. Sluit de applicatie");

            System.out.print("Voer het nummer van uw keuze in: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    // Voer logica uit om de kortste route te bepalen
                    System.out.println("Kortste route functionaliteit is nog niet geïmplementeerd.");
                    break;
                case "2":
                    // Voer logica uit om de minimum spanning tree te bepalen
                    System.out.println("Minimum cost spanning tree functionaliteit is nog niet geïmplementeerd.");
                    break;
                case "3":
                    // Voer logica uit om het spoornetwerk te tonen
                    System.out.println("Spoornetwerk weergave functionaliteit is nog niet geïmplementeerd.");
                    break;

                case "4":
                    System.out.println("\n > BINAIR ZOEKEN GESTART < \n \nGeef stationscode op:");
                    //Zoekquery input van gebruiker
                    String binarySearchQuery = scanner.nextLine();
                    SearchAlgorithm<List<Station>> searchAlgorithm = new BinarySearch();
                    ArrayList<Station> sortedStations = app.getStations();

                    //Sorteer voor binarySearch
                    sortedStations.sort(Comparator.comparing(Station::getCode));
                    // Tijdmeting starten
                    long startTime = System.nanoTime();
                    Station foundStation = searchAlgorithm.search(sortedStations, binarySearchQuery);
                    // Tijdmeting beëindigen
                    long endTime = System.nanoTime();
                    // Bereken de duur in nanoseconden
                    long durationInNano = (endTime - startTime);

                    if (foundStation != null) {
                        System.out.println("Station gevonden: " + foundStation.getFullName() +
                                " in " + durationInNano + " nanoseconden.");
                    } else {
                        System.out.println("Station niet gevonden. Zoektijd: " + durationInNano + " nanoseconden.");
                    }
                    break;

                case "5":
                    System.out.println("\n > LINEAIR ZOEKEN GESTART < \n \nGeef stationscode op:");
                    // Zoekquery input van gebruiker
                    String linearSearchQuery = scanner.nextLine();
                    SearchAlgorithm<RemcoList<Station>> linearSearch = new LinearSearch();
                    RemcoList<Station> stationsList = app.getStationsRemcoList();

                    // Tijdmeting starten
                    long startTimeLinear = System.nanoTime();
                    Station foundStationLinear = linearSearch.search(stationsList, linearSearchQuery);
                    // Tijdmeting beëindigen
                    long endTimeLinear = System.nanoTime();
                    // Bereken de duur in nanoseconden
                    long durationInNanoLinear = (endTimeLinear - startTimeLinear);

                    if (foundStationLinear != null) {
                        System.out.println("Station gevonden: " + foundStationLinear.getFullName() +
                                " in " + durationInNanoLinear + " nanoseconden.");
                    } else {
                        System.out.println("Station niet gevonden. Zoektijd: " + durationInNanoLinear + " nanoseconden.");
                    }
                    break;


                case "0":
                    System.out.println("Bedankt voor het gebruiken van de Spoormanager-applicatie. Tot ziens!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Ongeldige invoer, probeer het opnieuw.");
                    break;
            }
        }
    }
}
