package grill_task.meatPots;

import grill_task.meat.Meat;

public abstract class MeatPot {

    protected static final int MAX_QUANTITY = 20;

    public abstract String getStoredMeatType();
    public abstract void addMeat(Meat meat);
    public abstract String getPotName();
    public abstract boolean isEmpty();
    public abstract void removeMeat(Meat meat);
    public abstract boolean isFull();


}
