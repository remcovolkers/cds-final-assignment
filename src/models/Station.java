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
