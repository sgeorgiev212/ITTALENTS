package main.cake;

import main.cake.Cake;

public class SpecialCake extends Cake {

    private String eventName;

    public SpecialCake(String name, String description, double price, String kind,String eventName) {
        super(name, description, price, kind, "special");
        if(eventName.length()>=3){
            this.eventName=eventName;
        }
    }

    @Override
    public boolean checkKind(String kind) {
        boolean result = false;
        if(kind.equals("anniversary") || kind.equals("company") || kind.equals("advertise")){
            result=true;
        }
        return result;
    }
}
