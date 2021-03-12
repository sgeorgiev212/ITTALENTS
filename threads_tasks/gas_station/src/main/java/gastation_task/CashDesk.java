package gastation_task;

import gastation_task.databaseOperations.DataBaseConnection;
import gastation_task.databaseOperations.DatabaseOperations;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

public class CashDesk {

    private Queue<Driver> drivers;
    private String name;
    private GasStation gasStation;

    public CashDesk(String name,GasStation gasStation) {
        this.drivers = new LinkedList<>();
        this.name = name;
        this.gasStation = gasStation;
    }

    public synchronized void addDriverToCashDesk(Driver driver){
        this.drivers.offer(driver);
        System.out.println(driver.getDriverName()+" is going to pay on "+this.name);
        notifyAll();
    }

    public synchronized void removeDriverFromQueue(CashDeskWorker worker){
        while (this.drivers.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Driver driver = this.drivers.poll();
        System.out.println(driver.getDriverName()+" payed to "+worker.getCashDeskWorkerName()+" at "+this.name);
        FuelColumn column = driver.getColumn();
        column.removeDriver();
        System.out.println(driver.getDriverName()+" is leaving "+column.getName());
        ClientInfo clientInfo = new ClientInfo(column,driver.getFuelType(),driver.getFuelAmount(), LocalDateTime.now());
        this.gasStation.addInfo(clientInfo);
        DatabaseOperations.insertToDatabase(clientInfo);
        notifyAll();
    }

}
