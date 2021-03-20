package main.client;

import main.sweetShop.SweetShop;

public abstract class Client {

    private String name;
    private String phone;
    protected SweetShop sweetShop;
    double moneyPaidForCakes;

    public Client(String name, String phone,SweetShop sweetShop) {
        this.name = name;
        this.phone = phone;
        this.sweetShop =sweetShop;
        this.moneyPaidForCakes=0;
    }

    public abstract void makeOrder();
    public void addPaidMoney(double moneyPaidForCakes){
        this.moneyPaidForCakes+=moneyPaidForCakes;
    }

    public void setMoneyPaidForCakes(double moneyPaidForCakes) {
        this.moneyPaidForCakes = moneyPaidForCakes;
    }

    public double getMoneyPaidForCakes() {
        return moneyPaidForCakes;
    }

    @Override
    public String toString() {
        return this.name+" "+this.moneyPaidForCakes;
    }
}
