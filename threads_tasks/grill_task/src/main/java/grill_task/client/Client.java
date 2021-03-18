package grill_task.client;

import grill_task.bread.Bread;
import grill_task.bread.WhiteBread;
import grill_task.bread.WholeBread;
import grill_task.garnish.*;
import grill_task.grillShop.GrillShop;
import grill_task.meat.Meat;
import grill_task.meat.MeatBalls;
import grill_task.meat.Pleskavica;
import grill_task.meat.Steak;
import grill_task.util.Util;

import java.util.Random;

public class Client extends Thread{

    private String name;
    private static int number = 1;
    private GrillShop grillShop;
    private Meat meat;
    private Garnish garnish;
    private Bread bread;

    public Client(GrillShop grillShop) {
        this.name = "Client "+number++;
        this.grillShop = grillShop;
        this.meat = this.getRandomMeat();
        this.garnish = this.getRanomGarnish();
        this.bread = this.getRandomBread();
    }

    @Override
    public void run() {
        this.grillShop.goToGrillShop(this);
    }

    public String getClientName(){
        return name;
    }

    private Meat getRandomMeat(){
        Meat meat = null;
        int number = Util.randomize(1,3);
        switch (number){
            case  1:
                meat = new Steak();
                break;
            case  2:
                meat = new Pleskavica();
                break;
            case  3:
                meat = new MeatBalls();
                break;
        }
        return meat;
    }

    private Garnish getRanomGarnish(){
        Garnish garnish = null;
        int number = Util.randomize(1,5);
        switch (number){
            case 1:
                garnish = new CarrotSalad();
                break;
            case 2:
                garnish = new Liutenitsa();
                break;
            case 3:
                garnish = new RuskaSalad();
                break;
            case 4:
                garnish = new Snejanka();
                break;
            case 5:
                garnish = new TomatoSalad();
                break;
        }
        return garnish;
    }

    public Bread getRandomBread(){
        Bread bread = null;
        if(new Random().nextBoolean()){
            bread = new WhiteBread();
        }else{
            bread = new WholeBread();
        }
        return bread;
    }

    public Meat getMeat() {
        return meat;
    }

    public Garnish getGarnish() {
        return garnish;
    }

    public Bread getBread() {
        return bread;
    }
}
