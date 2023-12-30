package models;

public class Station implements Comparable<Station> {
    private final String stationsCode;
    private final String volledigeNaam;
    private final String slug;
    private final String land;
    private final String type;
    private final double geoLat;
    private final double geoLng;

    public Station(
            String stationsCode,
            String volledigeNaam,
            String slug,
            String land,
            String type,
            double geoLat,
            double geoLng) {
        this.stationsCode = stationsCode;
        //waarom moeten WIJ een station delen met die Lichtenvoordsen!!
        if (volledigeNaam.equalsIgnoreCase("Lichtenvoorde-Groenlo")) {
            this.volledigeNaam = "Grolle B-)";
        } else {
            this.volledigeNaam = volledigeNaam;
        }
        this.slug = slug;
        this.land = land;
        this.type = type;
        this.geoLat = geoLat;
        this.geoLng = geoLng;
    }


    public String getStationsCode() {
        return stationsCode;
    }

    public String getNaamVolledig() {
        return volledigeNaam;
    }

    public double getGeoLat() {
        return geoLat;
    }

    public double getGeoLng() {
        return geoLng;
    }

    public double haversineDistanceTo(Station other) {
        //source Coordinate.java == blackboard
        final double R = 6371.0;
        double latDistance = Math.toRadians(other.geoLat - this.geoLat);
        double lonDistance = Math.toRadians(other.geoLng - this.geoLng);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this.geoLat)) * Math.cos(Math.toRadians(other.geoLat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Station station = (Station) obj;
        return stationsCode.equals(station.stationsCode);
    }

    @Override
    public int compareTo(Station other) {
        return this.stationsCode.compareTo(other.stationsCode);
    }

    @Override
    public String toString() {
        return "Station{\n" +
                "\tfullName: " + volledigeNaam +
                ",\n\tcode: " + stationsCode +
                ",\n\tgeoLat: " + geoLat +
                ",\n\tgeoLng: " + geoLng +
                "\n}";
    }
}
