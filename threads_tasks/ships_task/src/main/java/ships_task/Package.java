package ships_task;

public class Package {

    private String name;
    private static int number = 1;
    private int id;

    public Package() {
        this.name = "Package "+number;
        id = number++;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
