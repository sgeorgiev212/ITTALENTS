package grill_task.meatPots;

import grill_task.meat.Meat;

import java.util.HashMap;

public class MeatBallPot extends MeatPot{

    private HashMap<String,Integer> meats;



    public MeatBallPot() {
        this.meats = new HashMap<>();
        if(!meats.containsKey("meatball")){
            meats.put("meatball",0);
        }
    }

    @Override
    public String getStoredMeatType() {
        return "meatball";
    }

    @Override
    public void addMeat(Meat meat) {
        if(meat.getType().equals("meatball")){
            meats.put("meatball",meats.get("meatball")+1);
        }
    }

    @Override
    public String getPotName() {
        return "meatball pot";
    }

    @Override
    public boolean isEmpty() {
        boolean result = false;
        if(meats.get("meatball") == 0){
            result = true;
        }
        return  result;
    }

    @Override
    public void removeMeat(Meat meat) {
        this.meats.put(meat.getType(),meats.get(meat.getType())-1);
    }

    @Override
    public boolean isFull() {
        boolean result = false;
        if(this.meats.size() == MAX_QUANTITY){
            result = true;
        }
        return result;
    }
}
