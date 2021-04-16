package main.bow;

import main.bow.Bow;

public class WoodenBow extends Bow {
    public WoodenBow(String manufacturer, double weight, int bowStrength) {
        super(manufacturer, weight, bowStrength,"wooden");
    }

    @Override
    public boolean checkBowStrength(int bowStrength) {
        return (bowStrength>=20 && bowStrength<=30);
    }

    @Override
    public int getAim() {
        return 0;
    }

    @Override
    public int getStabilizer() {
        return 0;
    }

    @Override
    public String getBowType() {
        return "wooden";
    }
}
