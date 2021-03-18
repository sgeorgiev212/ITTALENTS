package grill_task.garnishBow;

import grill_task.garnish.Garnish;

import java.util.concurrent.ConcurrentHashMap;

public class CarrotBow extends GarnishBow{

    private ConcurrentHashMap<String,Integer> salads;

    public CarrotBow() {
        this.salads = new ConcurrentHashMap<>();
        if(!salads.containsKey("carrot")){
            salads.put("carrot",0);
        }
    }

    @Override
    public String getStoredGarnishType() {
        return "carrot";
    }

    @Override
    public void addGarnish(Garnish garnish) {
        if(garnish.getType().equals("carrot")){
            salads.put("carrot", salads.get("carrot")+1);
        }
    }

    @Override
    public String getBowName() {
        return "carrot bow";
    }

    @Override
    public boolean isFull() {
        boolean result = false;
        if(this.salads.size() == MAX_QUANTITY){
            result = true;
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        boolean result = false;
        if (salads.get("carrot") == 0){
            result = true;
        }
        return result;
    }

    @Override
    public void removeGarnish(Garnish garnish) {
        this.salads.put(garnish.getType(),salads.get(garnish.getType())-1);
    }
}
