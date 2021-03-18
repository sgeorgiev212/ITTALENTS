package grill_task.garnish;

public class TomatoSalad extends Garnish{
    public TomatoSalad() {
        super("tomato");
    }

    @Override
    public int getCookingTimeSeconds() {
        return 3;
    }

    @Override
    public double getPrice() {
        return 1.3;
    }
}
