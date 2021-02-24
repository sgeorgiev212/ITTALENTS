package parts;

public abstract class CarPart {

    private String name;

    public CarPart(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int getBuildTimeSeconds();

}
