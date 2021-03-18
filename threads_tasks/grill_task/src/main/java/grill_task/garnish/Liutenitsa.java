package grill_task.garnish;

public class Liutenitsa extends Garnish{
    public Liutenitsa() {
        super("liutenitsa");
    }

    @Override
    public int getCookingTimeSeconds() {
        return 11;
    }

    @Override
    public double getPrice() {
        return 1.1;
    }

}
