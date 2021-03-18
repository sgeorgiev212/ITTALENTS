package grill_task.grillShop;

public class GrillShopWorker extends Thread{

    private GrillShop grillShop;

    public GrillShopWorker(GrillShop grillShop) {
        this.grillShop = grillShop;
    }

    @Override
    public void run() {
        while (true){
            this.grillShop.takeOrder();

        }
    }
}
