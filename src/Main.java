import commands.Command;
import commands.CommandFactory;
import core.ApplicationData;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApplicationData appData = new ApplicationData();
        appData.init("src/resources/stations.csv", "src/resources/tracks.csv");

        System.out.println("\nWelkom bij de Spoormanager-applicatie!\n");

        while (true) {
            System.out.println("\nHOOFDMENU\n-----------");
            System.out.println("Kies een optie:");
            System.out.println("1. Zoeken");
            System.out.println("2. Sorteren");
            System.out.println("3. 'java-graphviz' Functionaliteit");
            System.out.println("4. Geavanceerde Algoritmen");
            System.out.println("0. Sluit de applicatie");

            System.out.print("Voer het nummer van uw keuze in: ");
            String input = scanner.nextLine();
            System.out.println("--------------------------");

            Command command = CommandFactory.getCommand(input, appData, scanner);

            if (command != null) {
                command.execute();
            } else {
                System.out.println("Ongeldige invoer, probeer het opnieuw.");
            }
        }
    }
}