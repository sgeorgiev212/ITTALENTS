package grill_task.meat;

public abstract class Meat {

    private String type;

    public Meat(String type) {
        this.type = type;
    }

    public abstract int getCookingTimeSeconds();
    public abstract double getPrice();

    public String getType() {
        return type;
    }

}
