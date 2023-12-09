package utils;

import models.Station;
import models.Track;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataLoader {
    public static ArrayList<Station> loadStations(String filePath) {
        ArrayList<Station> stations = new ArrayList<>();
        Pattern pattern = Pattern.compile(
                "^.*?," +                            // id: overslaan (kan elk karakter zijn, inclusief leeg)
                        "(\\w+)," +                          // Code: één of meer woordkarakters
                        ".*?," +                            // Overslaan, kan elk karakter zijn (inclusief leeg)
                        ".*?," +                            // Hetzelfde voor de volgende velden
                        ".*?," +
                        ".*?," +
                        "(.*?)," +                          // name_long: elk karakter, lazy
                        "(.+?)," +                          // slug: elk karakter, lazy
                        "(NL|D)," +                         // country: 'NL' of 'D'
                        "(knooppuntIntercitystation|" +     // type: een van deze opgegeven stationtypes
                        "stoptreinstation|" +
                        "intercitystation|" +
                        "knooppuntStoptreinstation|" +
                        "megastation|" +
                        "sneltreinstation|" +
                        "knooppuntSneltreinstation|" +
                        "facultatiefStation)," +
                        "([\\d.-]+)," +                     // geoLat: getal (inclusief negatief en decimaal)
                        "([\\d.-]+)" +                      // geoLng: getal (inclusief negatief en decimaal)
                        "$"
        );


        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            //skip header
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || line.isEmpty()) {
                    continue;  // Sla commentaar en lege regels over
                }
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    Station station = new Station(matcher.group(1), // code
                            matcher.group(2), // name_long
                            matcher.group(3), // slug
                            matcher.group(4), // country
                            matcher.group(5), // type
                            Double.parseDouble(matcher.group(6)), // geoLat
                            Double.parseDouble(matcher.group(7))  // geoLng
                    );
                    stations.add(station);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(stations.size());
        return stations;
    }


    public static ArrayList<Track> loadTracks(String filePath) {
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
                    continue;  // Sla commentaar en lege regels over
                }
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    Track track = new Track(matcher.group(1), matcher.group(2), Integer.parseInt(matcher.group(3)));
                    tracks.add(track);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tracks;
    }

}
