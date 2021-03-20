package main;

import main.cake.KidsCake;
import main.cake.SpecialCake;
import main.cake.StandardCake;
import main.cake.WeddingCake;
import main.client.Client;
import main.client.Corporate;
import main.client.Private;
import main.deliveryGuy.DeliveryGuy;
import main.sweetShop.SweetShop;
import main.util.Util;

import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        SweetShop sweetShop = new SweetShop("Sladki talanti","sofia","1235456");

        for (int i = 0; i < 5; i++) {
            DeliveryGuy deliveryGuy = new DeliveryGuy(Util.getDeliveryGuyName(),Util.getAddress());
            sweetShop.addDeliveryGuy(deliveryGuy);
        }

        for (int i = 0; i < 30; i++) {
            int number = Util.randomize(1,4);
                switch (number){
                    case 1:
                        String kind = Util.getCakeKind("standard");
                        boolean hasSyrop = new Random().nextBoolean();
                        StandardCake standard = new StandardCake(Util.getCakeName(),"cake",Util.randomize(8,20),kind,hasSyrop);
                        sweetShop.addCake(standard);
                        break;
                    case 2:
                        String weddingKind = Util.getCakeKind("wedding");
                        WeddingCake wedding = new WeddingCake(Util.getCakeName(),"wedding cake",Util.randomize(60,260),weddingKind,Util.randomize(1,5));
                        sweetShop.addCake(wedding);
                        break;
                    case 3:
                        String specialKind = Util.getCakeKind("special");
                        SpecialCake specialCake = new SpecialCake(Util.getCakeName(),"special",Util.randomize(50,300),specialKind,"event");
                        sweetShop.addCake(specialCake);
                        break;
                    case 4:
                        String kidsKind = Util.getCakeKind("kids");
                        KidsCake kidsCake = new KidsCake(Util.getCakeName(),"kids",Util.randomize(30,180),kidsKind,Util.getKidName());
                        sweetShop.addCake(kidsCake);
                        break;
                }
        }

        ArrayList<Client> clients = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
        Private client = new Private(Util.getClientName(),"1234",sweetShop);
        clients.add(client);
        }
        for (int i = 0; i < 5; i++) {
            Corporate client = new Corporate(Util.getClientName(),"1234",sweetShop);
            clients.add(client);
        }

        System.out.println("available cakes before orders");
        sweetShop.printAvailableCakes();

        for (Client client : clients) {
            client.makeOrder();
        }
        System.out.println();
        System.out.println("available cakes after orders");
        sweetShop.printAvailableCakes();

        System.out.println("shop money after orders: " +sweetShop.getMoney());

        ArrayList<DeliveryGuy> guys = new ArrayList<>();
        guys.addAll(sweetShop.getDeliveryGuys());
        guys.sort((o1, o2) -> Double.compare(o2.getTips(),o1.getTips()));

        System.out.println("=================Delivery guys sorted by tips=====================");
        for (DeliveryGuy guy : guys) {
            System.out.println(guy);
        }
        System.out.println("==================================================================");

        ArrayList<DeliveryGuy> guys2 = new ArrayList<>();
        guys2.addAll(sweetShop.getDeliveryGuys());
        guys2.sort((o1, o2) -> o2.getOrders().size()-o1.getOrders().size());
        System.out.println("=================Delivery guys sorted by orders=====================");
        for (DeliveryGuy guy : guys) {
            System.out.println(guy+" number of orders: "+guy.getOrders().size());
        }
        System.out.println("==================================================================");

        clients.sort((o1, o2) -> Double.compare(o2.getMoneyPaidForCakes(),o1.getMoneyPaidForCakes()));
        System.out.println("client who payed the most " + clients.get(0));
    }
}
