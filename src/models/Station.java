package models;

public class Station {
    private int id;
    private String code;
    private int uic;
    private String nameShort;
    private String nameMedium;
    private String nameLong;
    private String slug;
    private String country;
    private String type;
    private double geoLat;
    private double geoLng;

    public Station(int id, String code, int uic, String nameShort, String nameMedium, String nameLong, String slug, String country, String type, double geoLat, double geoLng) {
        this.id = id;
        this.code = code;
        this.uic = uic;
        this.nameShort = nameShort;
        this.nameMedium = nameMedium;
        this.nameLong = nameLong;
        this.slug = slug;
        this.country = country;
        this.type = type;
        this.geoLat = geoLat;
        this.geoLng = geoLng;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", uic=" + uic +
                ", nameShort='" + nameShort + '\'' +
                ", nameMedium='" + nameMedium + '\'' +
                ", nameLong='" + nameLong + '\'' +
                ", slug='" + slug + '\'' +
                ", country='" + country + '\'' +
                ", type='" + type + '\'' +
                ", geoLat=" + geoLat +
                ", geoLng=" + geoLng +
                '}';
    }
}
