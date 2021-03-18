package grill_task.breadBasket;

import grill_task.bread.Bread;
import grill_task.meat.Meat;

public abstract class BreadBasket {

    protected static final int MAX_QUANTITY = 30;

    public abstract String getStoredBreadType();
    public abstract void addBread(Bread bread);
    public abstract String getBasketName();
    public abstract boolean isEmpty();
    public abstract void removeBread(Bread bread);
    public abstract boolean isFull();

}
