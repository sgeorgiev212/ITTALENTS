package grill_task.garnish;

public class RuskaSalad extends Garnish{
    public RuskaSalad() {
        super("ruska");
    }

    @Override
    public int getCookingTimeSeconds() {
        return 13;
    }

    @Override
    public double getPrice() {
        return 1.15;
    }
}
