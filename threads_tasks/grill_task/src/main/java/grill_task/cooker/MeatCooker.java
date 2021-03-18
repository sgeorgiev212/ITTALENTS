package grill_task.cooker;

import grill_task.grillShop.GrillShop;
import grill_task.meat.Meat;
import grill_task.meat.MeatBalls;
import grill_task.meat.Pleskavica;
import grill_task.meat.Steak;
import grill_task.util.Util;

public class MeatCooker extends Thread{

    private GrillShop grillShop;

    public MeatCooker(GrillShop grillShop) {
        this.grillShop = grillShop;
    }

    @Override
    public void run() {
        while (true){
           Meat meat = this.getRandomMeat();
           while (this.grillShop.isMeatTypeFull(meat)){
               meat = this.getRandomMeat();
           }
           this.grillShop.cookMeat(meat);
            try {
                sleep(meat.getCookingTimeSeconds()*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

}
