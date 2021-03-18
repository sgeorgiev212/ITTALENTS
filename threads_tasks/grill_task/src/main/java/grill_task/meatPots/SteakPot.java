package grill_task.meatPots;

import grill_task.meat.Meat;

import java.util.HashMap;

public class SteakPot extends MeatPot{

    private HashMap<String,Integer> meats;


    public SteakPot() {
        this.meats = new HashMap<>();
        if(!meats.containsKey("steak")){
            meats.put("steak",0);
        }
    }

    public void addMeat(Meat meat){
        if(meat.getType().equals("steak")){
            meats.put("steak",meats.get("steak")+1);
        }
    }

    @Override
    public String getPotName() {
        return "steak pot";
    }

    @Override
    public boolean isEmpty() {
        boolean result = false;
        if(meats.get("steak") == 0){
            result = true;
        }
        return  result;
    }

    @Override
    public void removeMeat(Meat meat) {
        this.meats.put(meat.getType(),meats.get(meat.getType())-1);
    }

    @Override
    public String getStoredMeatType() {
        return "steak";
    }

    public boolean isFull() {
        boolean result = false;
        if(this.meats.size() == MAX_QUANTITY){
            result = true;
        }
        return result;
    }
}
