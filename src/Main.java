public class Main {
    public static void main(String[] args) {
        Application app = new Application();
        app.initializeArrays();

        System.out.println(app.getStations().getFirst());
        System.out.println(app.getTracks().getFirst());
    }
}