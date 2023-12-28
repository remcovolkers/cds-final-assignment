package commands;

import core.ApplicationData;
import models.Station;
import sortalgorithms.InsertionSort;
import sortalgorithms.QuickSort;

import java.util.ArrayList;
import java.util.Comparator;
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

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                performInsertionSort();
                break;
            case "2":
                performQuickSort();
                break;
            default:
                System.out.println("Ongeldige keuze.");
                break;
        }
    }

    private void performInsertionSort() {
        List<Station> stationsToSort = new ArrayList<>(appData.getStations());
        System.out.println("Sorteren van stations met InsertionSort...");

        long startTime = System.nanoTime();
        InsertionSort.insertionSort(stationsToSort);
        long endTime = System.nanoTime();

        System.out.println("InsertionSort voltooid in " + ((endTime - startTime) / 1_000_000) + " milliseconden.");
        stationsToSort.stream().limit(5).forEach(station -> System.out.println(station.getCode()));
    }

    private void performQuickSort() {
        List<Station> stationsToSort = new ArrayList<>(appData.getStations());
        System.out.println("Sorteren van stations met QuickSort...");

        long startTime = System.nanoTime();
        QuickSort.quickSort(stationsToSort, 0, stationsToSort.size() - 1);
        long endTime = System.nanoTime();

        System.out.println("QuickSort voltooid in " + ((endTime - startTime) / 1_000_000) + " milliseconden.");
        stationsToSort.stream().limit(5).forEach(station -> System.out.println(station.getCode()));
    }
}
