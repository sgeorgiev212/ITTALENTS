package grill_task.cooker;

import grill_task.grillShop.GrillShop;
import grill_task.garnish.*;
import grill_task.util.Util;

public class GarnishCooker extends Thread{

    private GrillShop grillShop;

    public GarnishCooker(GrillShop grillShop) {
        this.grillShop = grillShop;
    }

    @Override
    public void run() {
        while (true){
            try {
            Garnish garnish = this.getRanomGarnish();
            while (this.grillShop.isGarnishTypeFull(garnish)){
                garnish = this.getRanomGarnish();
            }
            this.grillShop.makeGarnish(garnish);
                sleep(garnish.getCookingTimeSeconds()*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
}
