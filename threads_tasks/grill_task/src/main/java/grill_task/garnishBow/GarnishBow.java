package grill_task.garnishBow;

import grill_task.garnish.Garnish;
import grill_task.meat.Meat;

public abstract class GarnishBow{


    protected static final int MAX_QUANTITY = 10;

    public abstract String getStoredGarnishType();
    public abstract void addGarnish(Garnish garnish);
    public abstract String getBowName();
    public abstract boolean isFull();
    public abstract boolean isEmpty();
    public  abstract void removeGarnish(Garnish garnish);

}
