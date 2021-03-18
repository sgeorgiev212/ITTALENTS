package grill_task.bread;

public class WhiteBread extends Bread{
    public WhiteBread() {
        super("white");
    }

    @Override
    public int getCookingTimeSeconds() {
        return 5;
    }

    @Override
    public double getPrice() {
        return 0.9;
    }
}
