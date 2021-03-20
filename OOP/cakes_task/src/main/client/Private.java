package main.client;

import main.cake.*;
import main.deliveryGuy.DeliveryGuy;
import main.order.Order;
import main.sweetShop.SweetShop;
import main.util.Util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

public class Private extends Client {

    private int numberOfVouchers;
    private ArrayList<Double> vouchers;

    public Private(String name, String phone, SweetShop sweetShop) {
        super(name, phone,sweetShop);
        this.numberOfVouchers= Util.randomize(1,4);
        this.vouchers = new ArrayList<>();
        this.addRandomVouchers();
    }

    @Override
    public void makeOrder() {
        ArrayList<Cake> orderedCakes = this.generateCakes();
        boolean areCakesAvailable = true;
        for (Cake orderedCake : orderedCakes) {
            if(!this.sweetShop.checkForCake(orderedCake)){
                System.out.println("not all ordered cakes are available");
                areCakesAvailable=false;
                break;
            }
        }
        if(areCakesAvailable){
            double priceOfOrder = 0;
            for (Cake orderedCake : orderedCakes) {
                priceOfOrder+=orderedCake.getPrice();
            }
            Order order = new Order(this,priceOfOrder,Util.getAddress(),orderedCakes, LocalDateTime.now());
            DeliveryGuy guy = this.sweetShop.getDeliveryGuy();
            guy.addOrder(order);

            double priceOfVouchers = 0;
            for (Double voucher : vouchers) {
                priceOfVouchers+=voucher;
            }
            double priceWithDiscount = priceOfOrder - priceOfVouchers;
            this.addPaidMoney(priceWithDiscount);
            double tip = priceWithDiscount*2/100;
            guy.receiveTip(tip);
            this.sweetShop.collectMoneyFromOrder(priceWithDiscount);
        }

    }

    public ArrayList<Cake> generateCakes(){
        int numberOfCakes = Util.randomize(1,3);
        ArrayList<Cake> orderedCakes = new ArrayList<>();
        for (int i = 0; i < numberOfCakes; i++) {
            int num = Util.randomize(1,3);
            switch (num){
                case 1:
                    String kind = Util.getCakeKind("standard");
                    boolean hasSyrop = new Random().nextBoolean();
                    StandardCake standard = new StandardCake(Util.getCakeName(),"cake",Util.randomize(8,20),kind,hasSyrop);
                    orderedCakes.add(standard);
                    break;
                case 2:
                    String weddingKind = Util.getCakeKind("wedding");
                    WeddingCake wedding = new WeddingCake(Util.getCakeName(),"wedding cake",Util.randomize(60,260),weddingKind,Util.randomize(1,5));
                    orderedCakes.add(wedding);
                    break;
                case 3:
                    String specialKind = Util.getCakeKind("special");
                    SpecialCake specialCake = new SpecialCake(Util.getCakeName(),"special",Util.randomize(50,300),specialKind,"event");
                    orderedCakes.add(specialCake);
                    break;
                case 4:
                    String kidsKind = Util.getCakeKind("kids");
                    KidsCake kidsCake = new KidsCake(Util.getCakeName(),"kids",Util.randomize(30,180),kidsKind,Util.getKidName());
                    orderedCakes.add(kidsCake);
                    break;
            }
        }
        return orderedCakes;
    }

    private void addRandomVouchers(){
        for (int i = 0; i < numberOfVouchers; i++) {
            this.vouchers.add(Util.randomizeDouble(10,30));
        }
    }

}
