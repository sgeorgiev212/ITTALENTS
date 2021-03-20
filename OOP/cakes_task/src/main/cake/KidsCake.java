package main.cake;

import main.cake.Cake;

public class KidsCake extends Cake {

    private String kidName;

    public KidsCake(String name, String description, double price, String kind,String kidName) {
        super(name, description, price, kind, "kids");
        if(kidName.length()>=3){
            this.kidName=kidName;
        }
    }

    @Override
    public boolean checkKind(String kind) {
        boolean result = false;
        if(kind.equals("birthDay") || kind.equals("nameDay") || kind.equals("firstSteps")){
            result=true;
        }
        return result;
    }
}
