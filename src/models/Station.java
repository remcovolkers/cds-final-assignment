package models;

public class Station implements Comparable<Station> {
    private final String code;
    private final String fullName;
    private final String slug;
    private final String country;
    private final String type;
    private final double geoLat;
    private final double geoLng;

    public Station(
            String code,
            String fullName,
            String slug,
            String country,
            String type,
            double geoLat,
            double geoLng) {
        this.code = code;
        //waarom moeten WIJ een station delen met die Lichtenvoordsen!!
        if (fullName.equalsIgnoreCase("Lichtenvoorde-Groenlo")) {
            this.fullName = "Grolle B-)";
        } else {
            this.fullName = fullName;
        }
        this.slug = slug;
        this.country = country;
        this.type = type;
        this.geoLat = geoLat;
        this.geoLng = geoLng;
    }


    public String getCode() {
        return code;
    }

    public String getFullName() {
        return fullName;
    }

    public double getGeoLat() {
        return geoLat;
    }

    public double getGeoLng() {
        return geoLng;
    }

    public double haversineDistanceTo(Station other) {
        final double R = 6371.0; // Radius van de aarde in kilometers
        double latDistance = Math.toRadians(other.geoLat - this.geoLat);
        double lonDistance = Math.toRadians(other.geoLng - this.geoLng);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this.geoLat)) * Math.cos(Math.toRadians(other.geoLat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Retourneert de afstand in kilometers
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Station station = (Station) obj;
        return code.equals(station.code);
    }

    @Override
    public int compareTo(Station other) {
        return this.code.compareTo(other.code);
    }

    @Override
    public String toString() {
        return "Station{\n" +
                "\tfullName: " + fullName +
                ",\n\tcode: " + code +
                ",\n\tgeoLat: " + geoLat +
                ",\n\tgeoLng: " + geoLng +
                "\n}";
    }
}
