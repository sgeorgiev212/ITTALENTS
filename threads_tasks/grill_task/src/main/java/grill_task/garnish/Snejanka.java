package grill_task.garnish;

public class Snejanka extends Garnish {
    public Snejanka() {
        super("snejanka");
    }

    @Override
    public int getCookingTimeSeconds() {
        return 7;
    }

    @Override
    public double getPrice() {
        return 1.2;
    }
}
