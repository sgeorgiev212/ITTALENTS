package grill_task.garnishBow;

import grill_task.garnish.Garnish;

import java.util.concurrent.ConcurrentHashMap;

public class SnejankaBow extends GarnishBow{

    private ConcurrentHashMap<String,Integer> salads;

    public SnejankaBow() {
        this.salads = new ConcurrentHashMap<>();
        if(!salads.containsKey("snejanka")){
            salads.put("snejanka",0);
        }
    }

    @Override
    public String getStoredGarnishType() {
        return "snejanka";
    }

    @Override
    public void addGarnish(Garnish garnish) {
        if(garnish.getType().equals("snejanka")){
            salads.put("snejanka", salads.get("snejanka")+1);
        }
    }

    @Override
    public String getBowName() {
        return "snejanka bow";
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
        if (salads.get("snejanka") == 0){
            result = true;
        }
        return result;
    }

    @Override
    public void removeGarnish(Garnish garnish) {
        this.salads.put(garnish.getType(),salads.get(garnish.getType())-1);
    }
}
