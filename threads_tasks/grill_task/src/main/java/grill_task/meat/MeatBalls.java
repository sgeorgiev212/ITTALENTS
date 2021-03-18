package grill_task.meat;

public class MeatBalls extends Meat{
    public MeatBalls() {
        super("meatball");
    }

    @Override
    public int getCookingTimeSeconds() {
        return 5;
    }

    @Override
    public double getPrice() {
        return 1;
    }
}
