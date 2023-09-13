package models;

public class Connection {
    private String field1;
    private String field2;
    private int field3;
    private int field4;
    private int field5;

    public Connection(String field1, String field2, int field3, int field4, int field5) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
        this.field5 = field5;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "field1='" + field1 + '\'' +
                ", field2='" + field2 + '\'' +
                ", field3=" + field3 +
                ", field4=" + field4 +
                ", field5=" + field5 +
                '}';
    }
}
