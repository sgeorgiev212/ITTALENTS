package main.bow;

import main.bow.Bow;

public class CarbonBow extends Bow implements ISeniorBow{

    private int aim;
    private int stabilizator;

    public CarbonBow(String manufacturer, double weight, int bowStrength) {
        super(manufacturer, weight, bowStrength,"carbon");
        this.aim = 2;
        this.stabilizator = 1;
    }

    @Override
    public boolean checkBowStrength(int bowStrength) {
        return (bowStrength>=28 && bowStrength<=48);
    }

    @Override
    public int getAim() {
        return 2;
    }

    @Override
    public int getStabilizer() {
        return 1;
    }

    @Override
    public String getBowType() {
        return "carbon";
    }
}
