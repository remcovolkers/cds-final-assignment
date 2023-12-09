package models;

public class Track {
    private final String stationVan;
    private final String stationNaar;
    private final boolean binnenland;

    public Track(String stationVan, String stationNaar, int binnenland) {
        this.stationVan = stationVan;
        this.stationNaar = stationNaar;
        this.binnenland = binnenland == 30;
    }

    public String getStationVan() {
        return stationVan;
    }

    public String getStationNaar() {
        return stationNaar;
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
