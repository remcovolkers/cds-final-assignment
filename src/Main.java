public class Main {
    public static void main(String[] args) {
        Application app = new Application();
        app.initializeArrays();

        System.out.println(app.getStations());
        System.out.println(app.getConnections());
    }
}