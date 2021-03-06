package grill_task.garnishBow;

import grill_task.garnish.Garnish;

import java.util.concurrent.ConcurrentHashMap;

public class RuskaSaladBow extends GarnishBow{

    private ConcurrentHashMap<String,Integer> salads;

    public RuskaSaladBow() {
        this.salads = new ConcurrentHashMap<>();
        if(!salads.containsKey("ruska")){
            salads.put("ruska",0);
        }
    }

    @Override
    public String getStoredGarnishType() {
        return "ruska";
    }

    @Override
    public void addGarnish(Garnish garnish) {
        if(garnish.getType().equals("ruska")){
            salads.put("ruska", salads.get("ruska")+1);
        }
    }

    @Override
    public String getBowName() {
        return "ruska salad bow";
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
        if (salads.get("ruska") == 0){
            result = true;
        }
        return result;
    }

    @Override
    public void removeGarnish(Garnish garnish) {
        this.salads.put(garnish.getType(),salads.get(garnish.getType())-1);
    }
}
