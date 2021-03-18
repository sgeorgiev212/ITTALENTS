package grill_task.bread;

public class WholeBread extends Bread{
    public WholeBread() {
        super("whole");
    }

    @Override
    public int getCookingTimeSeconds() {
        return 8;
    }

    @Override
    public double getPrice() {
        return 1.4;
    }
}
