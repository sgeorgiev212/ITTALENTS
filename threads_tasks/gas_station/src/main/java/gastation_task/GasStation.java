package gastation_task;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GasStation{

    private ArrayList<FuelColumn> columns;
    private ArrayList<CashDesk> cashDesks;
    private CopyOnWriteArrayList<ClientInfo> info;

    public GasStation() {
        this.columns = new ArrayList();
        this.columns.add(new FuelColumn("Column 1"));
        this.columns.add(new FuelColumn("Column 2"));
        this.columns.add(new FuelColumn("Column 3"));
        this.columns.add(new FuelColumn("Column 4"));
        this.columns.add(new FuelColumn("Column 5"));
        this.cashDesks = new ArrayList<>();
        CashDesk cashDesk = new CashDesk("CashDesk 1",this);
        CashDesk cashDesk2 = new CashDesk("CashDesk 2",this);
        this.cashDesks.add(cashDesk);
        this.cashDesks.add(cashDesk2);
        CashDeskWorker cashDeskWorker1 = new CashDeskWorker(cashDesk,"Desk Worker 1");
        CashDeskWorker cashDeskWorker2 = new CashDeskWorker(cashDesk2,"Desk Worker 2");
        cashDeskWorker1.start();
        cashDeskWorker2.start();
        this.info = new CopyOnWriteArrayList<>();
    }

    public synchronized void getFuel(Driver driver){
        FuelColumn column = this.getRandomColumn();
        column.addDriver(driver);
        driver.setColumn(column);
        System.out.println(driver.getDriverName()+" arrived at "+column.getName());
    }

    public synchronized  void loadCarsWithFuel(Worker worker){
       while (columnsAreEmpty()){
           try {
               wait();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }

        for (FuelColumn column : columns) {
            if(!column.isEmpty()){
                Driver driver = column.getNextDriverInfo();
                if(!driver.isLoaded()) {
                    System.out.println(driver.getDriverName() + "'s car was loaded by " + worker.getWorkerName() + " on "
                            + column.getName()+" with "+driver.getFuelAmount()+" liters of "+driver.getFuelType());
                    driver.markLoaded();
                    CashDesk cashDesk = this.getRandomCashDesk();
                    cashDesk.addDriverToCashDesk(driver);
                }
                notifyAll();
            }
        }

    }

    private FuelColumn getRandomColumn(){
        return this.columns.get(new Random().nextInt(this.columns.size()));
    }

    private boolean columnsAreEmpty(){
        boolean result = false;
        for (FuelColumn column : columns) {
            if(column.isEmpty()){
                result = true;
            }else{
                result = false;
                break;
            }
        }
        return  result;
    }

    public void addCashDesk(CashDesk cashDesk){
        this.cashDesks.add(cashDesk);
    }

    public CashDesk getRandomCashDesk(){
        CashDesk cashDesk = null;
        if(new Random().nextBoolean()){
            cashDesk = this.cashDesks.get(0);
        }else{
            cashDesk = this.cashDesks.get(1);
        }
        return cashDesk;
    }

    public void addInfo(ClientInfo info){
        this.info.add(info);
    }

    public ArrayList<FuelColumn> getColumns() {
        return columns;
    }
}
