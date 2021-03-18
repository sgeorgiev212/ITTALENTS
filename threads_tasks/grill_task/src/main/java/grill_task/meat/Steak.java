package grill_task.meat;

public class Steak extends Meat{
    public Steak() {
        super("steak");
    }

    @Override
    public int getCookingTimeSeconds() {
        return 7;
    }

    @Override
    public double getPrice() {
        return 3;
    }
}
