package grill_task.garnish;

public class CarrotSalad extends Garnish{
    public CarrotSalad() {
        super("carrot");
    }

    @Override
    public int getCookingTimeSeconds() {
        return 2;
    }

    @Override
    public double getPrice() {
        return 0.8;
    }
}
