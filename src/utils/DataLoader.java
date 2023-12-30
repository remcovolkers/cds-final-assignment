package utils;

import datastructures.RemcoList;
import models.Station;
import models.Track;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataLoader {
    public static ArrayList<Station> loadStations(String filePath) {
        ArrayList<Station> stations = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || line.isEmpty()) {
                    continue;
                }
                Station station = createStationFromLine(line);
                if (station != null) {
                    stations.add(station);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stations;
    }

    public static RemcoList<Station> laadStationsNaarRemcoList(String filePath) {
        RemcoList<Station> stations = new RemcoList<>();
        //duplicate code is jammer maar helaas, wordt onleesbaar met generieke oplossing, i tried
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || line.isEmpty()) {
                    continue;
                }
                Station station = createStationFromLine(line);
                if (station != null) {
                    stations.add(station);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stations;
    }


    public static ArrayList<Track> loadTracks(String filePath, Map<String, Station> stationsMap) {
        ArrayList<Track> tracks = new ArrayList<>();
        Pattern pattern = Pattern.compile(
                "^(\\w+)," +              // stationVan
                        "(\\w+)," +             // stationNaar
                        "\\d+," +               // TariffFirstClass veld (overgeslagen)
                        "\\d+," +               // TariffSecondClass veld (overgeslagen)
                        "(30|0)$");             // binnenland (30 == true, 0 == false)

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || line.isEmpty()) {
                    System.out.println("Overslaan (Commentaar of lege regel): " + line);
                    continue;  // Sla commentaar en lege regels over
                }
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    String stationVanCode = matcher.group(1);
                    String stationNaarCode = matcher.group(2);

                    Station stationVan = stationsMap.get(stationVanCode.toUpperCase());
                    Station stationNaar = stationsMap.get(stationNaarCode.toUpperCase());

                    if (stationVan == null || stationNaar == null) {
                        System.out.println("Station niet gevonden voor regel: " + line);
                        continue; // Station niet gevonden, ga door naar de volgende regel
                    }

                    boolean binnenland = Integer.parseInt(matcher.group(3)) == 30;
                    Track track = new Track(stationVan, stationNaar, binnenland);
                    tracks.add(track);
                } else {
                    System.out.println("Regel voldoet niet aan patroon: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tracks;
    }


    public static Station createStationFromLine(String line) {
        Pattern pattern = Pattern.compile(
                "^"                     // Begin van de regel
                        + "([1-9]\\d{0,3}),"      // 'id': 1 tot 4 cijfers, niet beginnend met 0 (Groep 1)
                        + "([A-Z]{1,6}),"         // 'code': 1 tot 6 hoofdletters (Groep 2)
                        + "(\\d{7}),"             // 'uic': 7 cijfers (Group 3)
                        + "(.*?),"                // 'name_short': alle tekens (Groep 4)
                        + "(.*?),"                // 'name_medium': alle tekens (Groep 5)
                        + "(.*?),"                // 'name_long': alle tekens (Groep 6)
                        + "(.*?),"                // 'slug': alle tekens (Group 7)
                        + "(NL|D|B|F|A|CH),"    // 'country': keuze uit NL, D, B, F, A, CH (Groep 8)
                        + "(knooppuntIntercitystation|stoptreinstation|intercitystation|knooppuntStoptreinstation|megastation|sneltreinstation|knooppuntSneltreinstation|facultatiefStation)," // 'type': gespecificeerde stationstypes (Group 9)
                        + "(\\d{1,3}\\.\\d+),"    // 'geo_lat': coördinaten (bv. 123.456) (Groep 10)
                        + "(\\d{1,3}\\.\\d+)"     // 'geo_lng': coördinaten (bv. 123.456) (Groep 11)
                        + "$"                   // Einde van de regel
        );

        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            return new Station(
                    matcher.group(2), // code
                    matcher.group(6), // name_long
                    matcher.group(7), // slug
                    matcher.group(8), // country
                    matcher.group(9), // type
                    Double.parseDouble(matcher.group(10)), // geoLat
                    Double.parseDouble(matcher.group(11))  // geoLng
            );
        }
        return null;
    }
}
