package grill_task.meatPots;

import grill_task.meat.Meat;

import java.util.HashMap;

public class PleskavicaPot extends MeatPot{

    private HashMap<String,Integer> meats;

    public PleskavicaPot() {
        this.meats = new HashMap<>();
        if(!meats.containsKey("pleskavica")){
            meats.put("pleskavica",0);
        }
    }

    @Override
    public String getStoredMeatType() {
        return "pleskavica";
    }

    @Override
    public void addMeat(Meat meat) {
        if(meat.getType().equals("pleskavica")){
            meats.put("pleskavica",meats.get("pleskavica")+1);
        }
    }

    @Override
    public String getPotName() {
        return "pleskavica pot";
    }

    @Override
    public boolean isEmpty() {
        boolean result = false;
        if(meats.get("pleskavica") == 0){
            result = true;
        }
        return  result;
    }

    @Override
    public void removeMeat(Meat meat) {
        this.meats.put(meat.getType(),meats.get(meat.getType())-1);
    }

    public boolean isFull() {
        boolean result = false;
        if(this.meats.size() == MAX_QUANTITY){
            result = true;
        }
        return result;
    }
}
