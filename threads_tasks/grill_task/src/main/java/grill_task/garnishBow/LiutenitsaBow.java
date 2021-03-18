package grill_task.garnishBow;

import grill_task.garnish.Garnish;

import java.util.concurrent.ConcurrentHashMap;

public class LiutenitsaBow extends GarnishBow{

    private ConcurrentHashMap<String,Integer> salads;

    public LiutenitsaBow() {
        this.salads = new ConcurrentHashMap<>();
        if(!salads.containsKey("liutenitsa")){
            salads.put("liutenitsa",0);
        }
    }

    @Override
    public String getStoredGarnishType() {
        return "liutenitsa";
    }

    @Override
    public void addGarnish(Garnish garnish) {
        if(garnish.getType().equals("liutenitsa")){
            salads.put("liutenitsa", salads.get("liutenitsa")+1);
        }
    }

    @Override
    public String getBowName() {
        return "liutenitsa bow";
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
        if (salads.get("liutenitsa") == 0){
            result = true;
        }
        return result;
    }

    @Override
    public void removeGarnish(Garnish garnish) {
        this.salads.put(garnish.getType(),salads.get(garnish.getType())-1);
    }
}
