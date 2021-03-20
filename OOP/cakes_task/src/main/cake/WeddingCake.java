package main.cake;

import main.cake.Cake;

public class WeddingCake extends Cake {

    private int numberOfFloors;

    public WeddingCake(String name, String description, double price, String kind,int numberOfFloors) {
        super(name, description, price, kind, "wedding");
        if(numberOfFloors>=1) {
            this.numberOfFloors = numberOfFloors;
        }
    }

    @Override
    public boolean checkKind(String kind) {
        boolean result = false;
        if(kind.equals("big") || kind.equals("small") || kind.equals("medium")){
            result=true;
        }
        return result;
    }
}
