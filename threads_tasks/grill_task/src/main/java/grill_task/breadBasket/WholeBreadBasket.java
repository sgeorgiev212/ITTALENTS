package grill_task.breadBasket;

import grill_task.bread.Bread;

import java.util.concurrent.ConcurrentHashMap;

public class WholeBreadBasket extends BreadBasket{

    private ConcurrentHashMap<String,Integer> breads;


    public WholeBreadBasket() {
        this.breads = new ConcurrentHashMap<>();
        if(!breads.containsKey("whole")){
            breads.put("whole",0);
        }
    }

    @Override
    public String getStoredBreadType() {
        return "whole";
    }

    @Override
    public void addBread(Bread bread) {
        if(bread.getType().equals("whole")){
            breads.put("whole",breads.get("whole")+1);
        }
    }

    @Override
    public String getBasketName() {
        return "whole bread basekt";
    }

    @Override
    public boolean isEmpty() {
        boolean result = false;
        if(breads.get("whole") == 0){
            result = true;
        }
        return result;
    }

    @Override
    public void removeBread(Bread bread) {
        this.breads.put(bread.getType(),breads.get(bread.getType())-1);
    }

    @Override
    public boolean isFull() {
        boolean result = false;
        if (this.breads.size() == MAX_QUANTITY){
            result = true;
        }
        return result;
    }
}
