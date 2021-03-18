package grill_task.grillShop;

import grill_task.client.Client;
import grill_task.bread.Bread;
import grill_task.breadBasket.BreadBasket;
import grill_task.breadBasket.WhiteBreadBasket;
import grill_task.breadBasket.WholeBreadBasket;
import grill_task.fileCreator.FileCreator;
import grill_task.garnish.Garnish;
import grill_task.garnishBow.*;
import grill_task.meat.Meat;
import grill_task.meatPots.MeatBallPot;
import grill_task.meatPots.MeatPot;
import grill_task.meatPots.PleskavicaPot;
import grill_task.meatPots.SteakPot;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.CopyOnWriteArrayList;

public class GrillShop {

    private CopyOnWriteArrayList<MeatPot> meatPots;
    private CopyOnWriteArrayList<BreadBasket> breadBaskets;
    private CopyOnWriteArrayList<GarnishBow> garnishBows;
    private double money;
    private Queue<Client> clients;

    public GrillShop() {
        this.meatPots = new CopyOnWriteArrayList<>();
        this.meatPots.add(new SteakPot());
        this.meatPots.add(new PleskavicaPot());
        this.meatPots.add(new MeatBallPot());
        this.breadBaskets = new CopyOnWriteArrayList<>();
        breadBaskets.add(new WhiteBreadBasket());
        breadBaskets.add(new WholeBreadBasket());
        this.garnishBows = new CopyOnWriteArrayList<>();
        this.garnishBows.add(new CarrotBow());
        this.garnishBows.add(new LiutenitsaBow());
        this.garnishBows.add(new RuskaSaladBow());
        this.garnishBows.add(new SnejankaBow());
        this.garnishBows.add(new TomatoBow());
        this.clients = new LinkedList<>();
    }

    public void cookMeat(Meat meat){
        synchronized (meatPots){
            for (MeatPot meatPot : meatPots) {
                if (meatPot.getStoredMeatType().equals(meat.getType())){
                    meatPot.addMeat(meat);
                    System.out.println("The meat cooker added "+meat.getType()+" to the "+meatPot.getPotName());
                }
            }
            meatPots.notifyAll();
        }
    }

    public void makeBread(Bread bread){
        synchronized (breadBaskets){
            for (BreadBasket breadBasket : breadBaskets) {
                if(breadBasket.getStoredBreadType().equals(bread.getType())){
                    breadBasket.addBread(bread);
                    System.out.println("The bread cooker added "+bread.getType()+" bread to the "+breadBasket.getBasketName());
                }
            }
            breadBaskets.notifyAll();
        }
    }

    public void makeGarnish(Garnish garnish){
        synchronized (garnishBows){
            for (GarnishBow garnishBow : garnishBows) {
                if(garnishBow.getStoredGarnishType().equals(garnish.getType())){
                    garnishBow.addGarnish(garnish);
                    System.out.println("The garnish maker added "+garnish.getType()+" salad to the "+garnishBow.getBowName());
                }
            }
            garnishBows.notifyAll();
        }
    }

    public synchronized void takeOrder(){
        while (this.clients.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Client client = this.clients.poll();
        Bread bread = client.getBread();
        Meat meat = client.getMeat();
        Garnish garnish = client.getGarnish();
        synchronized (breadBaskets) {
            while (breadIsNotReady(bread)){
                try {
                    breadBaskets.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.sellBread(bread);
            breadBaskets.notifyAll();
        }
        synchronized (meatPots) {
            while (meatIsNotReady(meat)){
                try {
                    meatPots.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.sellMeat(meat);
            meatPots.notifyAll();
        }
        synchronized (garnishBows) {
            while (garnishIsNotReady(garnish)){
                try {
                    garnishBows.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.sellGarnish(garnish);
            garnishBows.notifyAll();
        }
        this.money+=bread.getPrice();
        this.money+=garnish.getPrice();
        this.money+=meat.getPrice();
        double price = bread.getPrice() + garnish.getPrice()  + meat.getPrice();
        System.out.println(client.getClientName()+" got "+bread.getType()+"bread, "+meat.getType()+" and "+garnish.getType());
        System.out.println(client.getClientName()+" payed and is leaving");
        String order = bread.getType()+", "+meat.getType()+", "+garnish.getType();
        FileCreator.addOrderToFile(order,price, LocalDateTime.now());
        notifyAll();
    }

    public synchronized void goToGrillShop(Client client){
        this.clients.offer(client);
        System.out.println(client.getClientName()+" went to the grill shop");
        System.out.println(client.getClientName()+" ordered "+client.getBread().getType()+
                "bread, "+client.getMeat().getType()
                +", "+client.getGarnish().getType());
        notifyAll();
    }

    public boolean isGarnishTypeFull(Garnish garnish){
        boolean result = false;
        for (GarnishBow garnishBow : garnishBows) {
            if(garnishBow.getStoredGarnishType().equals(garnish.getType())) {
                if (garnishBow.isFull()) {
                    result = true;
                }
            }
        }
        return  result;
    }

    public boolean isMeatTypeFull(Meat meat){
        boolean result = false;
        for (MeatPot meatPot : meatPots) {
            if(meatPot.getStoredMeatType().equals(meat.getType())){
               if (meatPot.isFull()){
                   result = true;
               }
            }
        }

        return result;
    }

    public boolean isBreadTypeFull(Bread bread){
        boolean result = false;
        for (BreadBasket breadBasket : breadBaskets) {
            if (breadBasket.getStoredBreadType().equals(bread.getType())){
                if (breadBasket.isFull()){
                    result = true;
                }
            }
        }
        return result;
    }


    public boolean breadIsNotReady(Bread bread){
        boolean result = false;
        for (BreadBasket breadBasket : breadBaskets) {
            if(breadBasket.getStoredBreadType().equals(bread.getType())){
                if(breadBasket.isEmpty()){
                    result = true;
                }
            }
        }
        return result;
    }

    private boolean meatIsNotReady(Meat meat){
        boolean result = false;
        for (MeatPot meatPot : meatPots) {
            if(meatPot.getStoredMeatType().equals(meat.getType())){
                if (meatPot.isEmpty()){
                    return true;
                }
            }
        }
        return result;
    }

    private boolean garnishIsNotReady(Garnish garnish){
        boolean result = false;
        for (GarnishBow garnishBow : garnishBows) {
            if (garnishBow.getStoredGarnishType().equals(garnish.getType())){
                if (garnishBow.isEmpty()){
                    return true;
                }
            }
        }
        return result;
    }

    private void sellBread(Bread bread){
        for (BreadBasket breadBasket : breadBaskets) {
            if(breadBasket.getStoredBreadType().equals(bread.getType())){
                if(!breadBasket.isEmpty()){
                    breadBasket.removeBread(bread);
                }
            }
        }
    }

    private void sellMeat(Meat meat){
        for (MeatPot meatPot : meatPots) {
            if(meatPot.getStoredMeatType().equals(meat.getType())){
                if(!meatPot.isEmpty()){
                    meatPot.removeMeat(meat);
                }
            }
        }
    }

    public void sellGarnish(Garnish garnish){
        for (GarnishBow garnishBow : garnishBows) {
            if(garnishBow.getStoredGarnishType().equals(garnish.getType())){
                if(garnishBow.isEmpty()){
                    garnishBow.removeGarnish(garnish);
                }
            }
        }
    }

    public double getMoney() {
        return money;
    }
}
