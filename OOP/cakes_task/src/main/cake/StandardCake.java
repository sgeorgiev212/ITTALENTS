package main.cake;

import main.cake.Cake;

public class StandardCake extends Cake {

    boolean hasSyrop;

    public StandardCake(String name, String description, double price, String kind,boolean hasSyrop) {
        super(name, description, price, kind, "standard");
        this.hasSyrop =hasSyrop;
    }

    @Override
    public boolean checkKind(String kind){
        boolean result = false;
        if(kind.equals("biscuit") || kind.equals("ekler") || kind.equals("fruit") || kind.equals("chocolate")){
            result=true;
        }
        return result;
    }
}
