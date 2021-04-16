package main.bow;

import main.bow.Bow;

public class AluminumBow extends Bow implements ISeniorBow{
    private int aim;

    public AluminumBow(String manufacturer, double weight, int bowStrength) {
        super(manufacturer, weight, bowStrength,"aluminum");
        this.aim=1;
    }

    @Override
    public boolean checkBowStrength(int bowStrength) {
        return (bowStrength>=25 && bowStrength<=40);
    }

    public int getAim() {
        return 1;
    }

    @Override
    public int getStabilizer() {
        return 0;
    }

    @Override
    public String getBowType() {
        return "aluminum";
    }
}
