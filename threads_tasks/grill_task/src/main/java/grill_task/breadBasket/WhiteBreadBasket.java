package grill_task.breadBasket;

import grill_task.bread.Bread;

import java.util.concurrent.ConcurrentHashMap;

public class WhiteBreadBasket extends BreadBasket{

    private ConcurrentHashMap<String,Integer> breads;


    public WhiteBreadBasket() {
        this.breads = new ConcurrentHashMap<>();
        if(!breads.containsKey("white")){
            breads.put("white",0);
        }
    }

    @Override
    public String getStoredBreadType() {
        return "white";
    }

    @Override
    public void addBread(Bread bread) {
        if(bread.getType().equals("white")){
            breads.put("white",breads.get("white")+1);
        }
    }

    @Override
    public String getBasketName() {
        return "white bread basket";
    }

    @Override
    public boolean isEmpty() {
        boolean result = false;
        if(breads.get("white") == 0){
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
