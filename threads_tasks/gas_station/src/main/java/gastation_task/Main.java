package gastation_task;

import com.google.gson.Gson;
import gastation_task.databaseOperations.DataBaseConnection;
import gastation_task.databaseOperations.DatabaseInfo;
import gastation_task.databaseOperations.DatabaseOperations;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        GasStation gasStation = new GasStation();

        Driver driver1 = new Driver(gasStation);
        Driver driver2 = new Driver(gasStation);
        Driver driver3 = new Driver(gasStation);
        Driver driver4 = new Driver(gasStation);
        Driver driver5 = new Driver(gasStation);
        Driver driver6 = new Driver(gasStation);

        Worker worker1 = new Worker("Worker 1",gasStation);
        Worker worker2 = new Worker("Worker 2",gasStation);

        driver1.start();
        driver2.start();
        driver3.start();
        driver4.start();
        driver5.start();
        driver6.start();

        worker1.start();
        worker2.start();

        Thread statistics = new Thread(() -> {
            while (true){
                try {
                    Thread.currentThread().sleep(20000);
                    System.out.println("=================GAS STATION INFO=================");
                    for (FuelColumn column : gasStation.getColumns()) {
                        DatabaseOperations.printColumnInfo(column.getId());
                    }
                    System.out.println("---------------");
                    DatabaseOperations.printNumberOfCars();
                    System.out.println("---------------");
                    DatabaseOperations.printFuelInfo();
                    System.out.println("---------------");
                    DatabaseOperations.printEarnedMoney();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("====================================================");
            }
        });
        statistics.start();
    }

}
