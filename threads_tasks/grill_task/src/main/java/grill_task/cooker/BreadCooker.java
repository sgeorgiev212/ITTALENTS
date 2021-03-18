package grill_task.cooker;

import grill_task.grillShop.GrillShop;
import grill_task.bread.Bread;
import grill_task.bread.WhiteBread;
import grill_task.bread.WholeBread;

import java.util.Random;

public class BreadCooker extends Thread{

    private GrillShop grillShop;

    public BreadCooker(GrillShop grillShop) {
        this.grillShop = grillShop;
    }

    @Override
    public void run() {
        while (true){
            try {
          Bread bread = this.getRandomBread();
          while (this.grillShop.isBreadTypeFull(bread)){
              bread = this.getRandomBread();
          }
          this.grillShop.makeBread(bread);
                sleep(bread.getCookingTimeSeconds()*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
}
