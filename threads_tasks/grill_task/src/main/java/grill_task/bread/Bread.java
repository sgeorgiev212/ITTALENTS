package grill_task.bread;

public abstract class Bread {

    private String type;

    public Bread(String type) {
        this.type = type;
    }

    public abstract int getCookingTimeSeconds();
    public abstract double getPrice();

    public String getType() {
        return type;
    }

}
