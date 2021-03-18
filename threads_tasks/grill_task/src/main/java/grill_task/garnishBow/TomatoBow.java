package grill_task.garnishBow;

import grill_task.garnish.Garnish;

import java.util.concurrent.ConcurrentHashMap;

public class TomatoBow extends GarnishBow{

    private ConcurrentHashMap<String,Integer> salads;

    public TomatoBow() {
        this.salads = new ConcurrentHashMap<>();
        if(!salads.containsKey("tomato")){
            salads.put("tomato",0);
        }
    }

    @Override
    public String getStoredGarnishType() {
        return "tomato";
    }

    @Override
    public void addGarnish(Garnish garnish) {
        if(garnish.getType().equals("tomato")){
            salads.put("tomato", salads.get("tomato")+1);
        }
    }

    @Override
    public String getBowName() {
        return "tomato bow";
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
        if (salads.get("tomato") == 0){
            result = true;
        }
        return result;
    }

    @Override
    public void removeGarnish(Garnish garnish) {
        this.salads.put(garnish.getType(),salads.get(garnish.getType())-1);
    }
}
