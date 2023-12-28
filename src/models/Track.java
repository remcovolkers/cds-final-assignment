package models;

public class Track {
    private final Station stationVan;
    private final Station stationNaar;
    private final boolean binnenland;
    private final double distance;

    public Track(Station stationVan, Station stationNaar, boolean binnenland) {
        Coordinate coordVan = new Coordinate(stationVan.getGeoLat(), stationVan.getGeoLng());
        Coordinate coordNaar = new Coordinate(stationNaar.getGeoLat(), stationNaar.getGeoLng());
        this.stationVan = stationVan;
        this.stationNaar = stationNaar;
        this.binnenland = binnenland;
        this.distance = coordVan.haversineDistance(coordNaar);
    }

    public Station getStationVan() {
        return stationVan;
    }

    public Station getStationNaar() {
        return stationNaar;
    }

    public boolean getBinnenland() {
        return binnenland;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Track {" +
                "van='" + stationVan + '\'' +
                ", naar='" + stationNaar + '\'' +
                ", binnenland?=" + binnenland +
                '}';
    }


}
