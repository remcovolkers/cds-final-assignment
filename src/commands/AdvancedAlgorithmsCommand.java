package commands;

import core.ApplicationData;

import java.util.Scanner;

public class AdvancedAlgorithmsCommand implements Command {
    private final ApplicationData appData;
    private final Scanner scanner;

    public AdvancedAlgorithmsCommand(ApplicationData appData, Scanner scanner) {
        this.appData = appData;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("\n >> ADVANCED ALGORITHMS MENU << \n");

        System.out.println("Selecteer een geavanceerde algoritme-optie:");
        System.out.println("1. Stations binnen een rechthoekige zone");
        System.out.println("2. Routeplanning");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                performRectangleZoneCommand();
                break;
            case "2":
                performRoutePlanningCommand();
                break;
            default:
                System.out.println("Ongeldige keuze.");
                break;
        }
    }

    private void performRectangleZoneCommand() {
        System.out.println("Voer de coördinaten in van de noordwestelijke hoek (bv. 52.5,4.8):");
        String nwCorner = scanner.nextLine();
        System.out.println("Voer de coördinaten in van de zuidoostelijke hoek (bv. 51.8,5.3):");
        String seCorner = scanner.nextLine();
    }

    private void performRoutePlanningCommand() {
        System.out.println("Voer de stationscode in van het startstation:");
        String startStation = scanner.nextLine();
        System.out.println("Voer de stationscode in van het eindstation:");
        String eindStation = scanner.nextLine();
    }
}
