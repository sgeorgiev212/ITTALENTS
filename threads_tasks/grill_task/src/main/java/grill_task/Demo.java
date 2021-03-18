package grill_task;

import grill_task.client.Client;
import grill_task.cooker.BreadCooker;
import grill_task.cooker.GarnishCooker;
import grill_task.cooker.MeatCooker;
import grill_task.database.DatabaseOperations;
import grill_task.fileCreator.FileOpeartions;
import grill_task.grillShop.GrillShop;
import grill_task.grillShop.GrillShopWorker;

public class Demo {

    public static void main(String[] args) {
        GrillShop grillShop = new GrillShop();

        MeatCooker meatCooker = new MeatCooker(grillShop);
        BreadCooker breadCooker = new BreadCooker(grillShop);
        GarnishCooker garnishCooker = new GarnishCooker(grillShop);
        GrillShopWorker worker = new GrillShopWorker(grillShop);

        Client client = new Client(grillShop);
        Client client2 = new Client(grillShop);
        Client client3 = new Client(grillShop);
        Client client4 = new Client(grillShop);
        Client client5 = new Client(grillShop);
        Client client6 = new Client(grillShop);

        meatCooker.start();
        breadCooker.start();
        garnishCooker.start();
        worker.start();
        client.start();
        client2.start();
        client3.start();
        client4.start();
        client5.start();
        client6.start();

      Thread statistics = new Thread(() -> {
          while (true){
              try {
                  Thread.currentThread().sleep(20000);
                  FileOpeartions.readInfoFromFIleAndInsertToDB();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      });
      statistics.setDaemon(true);
      statistics.start();

      Thread databaseStats = new Thread(() -> {
          while (true){
              try {
                  Thread.currentThread().sleep(30000);
                  System.out.println("======================INFO FROM DATABASE===================");
                  DatabaseOperations.printNumberOfOrders();
                  DatabaseOperations.printMostSelledMeat();
                  DatabaseOperations.printMoneyFromOrders(grillShop);
                  DatabaseOperations.printShopsInfo();
                  DatabaseOperations.printShopWithMostWholeBread();
                  DatabaseOperations.printLeastBoughtGarnish();
                  System.out.println("===========================================================");
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
      });
      databaseStats.setDaemon(true);
      databaseStats.start();

    }
}
