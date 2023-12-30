package commands;

import core.ApplicationData;
import models.Station;
import sortalgorithms.InsertionSort;
import sortalgorithms.QuickSort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SortCommand implements Command {
    private final ApplicationData appData;
    private final Scanner scanner;

    public SortCommand(ApplicationData appData, Scanner scanner) {
        this.appData = appData;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("\n >> SORTEER MENU << \n");
        System.out.println("Kies het type sorteeralgoritme:");
        System.out.println("1. Insertion Sort");
        System.out.println("2. Quick Sort");

        String invoer = scanner.nextLine();
        switch (invoer) {
            case "1":
                insertionSortUitvoering();
                break;
            case "2":
                quickSortUitvoering();
                break;
            default:
                System.out.println("Ongeldige keuze.");
                break;
        }
    }

    List<Station> insertionSortUitvoering() {
        List<Station> gesorteerdeStations = new ArrayList<>(appData.getStations());
        System.out.println("Sorteren van stations met InsertionSort...");

        long startTijd = System.nanoTime();
        InsertionSort.insertionSort(gesorteerdeStations);
        long eindTijd = System.nanoTime();

        System.out.println("InsertionSort voltooid in " + ((eindTijd - startTijd) / 1_000_000) + " milliseconden.");
        gesorteerdeStations.stream().limit(5).forEach(station -> System.out.println(station.getStationsCode()));
        return gesorteerdeStations;
    }

    List<Station> quickSortUitvoering() {
        List<Station> gesorteerdeStations = new ArrayList<>(appData.getStations());
        System.out.println("Sorteren van stations met QuickSort...");

        long startTijd = System.nanoTime();
        QuickSort.quickSort(gesorteerdeStations, 0, gesorteerdeStations.size() - 1);
        long eindTijd = System.nanoTime();

        System.out.println("QuickSort voltooid in " + ((eindTijd - startTijd) / 1_000_000) + " milliseconden.");
        gesorteerdeStations.stream().limit(5).forEach(station -> System.out.println(station.getStationsCode()));
        return gesorteerdeStations;
    }
}
