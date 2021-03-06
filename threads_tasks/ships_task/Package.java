public class Package {

    private static int number = 1;
    private int id;

    public Package() {
        this.id = number++;
    }

    public int getId() {
        return id;
    }
}
