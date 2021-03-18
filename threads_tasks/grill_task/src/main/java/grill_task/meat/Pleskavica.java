package grill_task.meat;

public class Pleskavica extends Meat{
    public Pleskavica() {
        super("pleskavica");
    }

    @Override
    public int getCookingTimeSeconds() {
        return 6;
    }

    @Override
    public double getPrice() {
        return 2;
    }
}
