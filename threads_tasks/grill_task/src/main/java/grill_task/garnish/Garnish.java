package grill_task.garnish;

public abstract class Garnish {

    private String type;

    public Garnish(String type) {
        this.type = type;
    }

    public abstract int getCookingTimeSeconds();
    public abstract double getPrice();

    public String getType() {
        return type;
    }

}
