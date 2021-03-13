package ships_task;

import ships_task.DataBase.DatabaseConnection;
import ships_task.DataBase.DatabaseOperations;
import ships_task.Files.FileCreator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDateTime;

public class Demo {

    public static void main(String[] args) {

        ships_task.Harbour harbour = new ships_task.Harbour();

        ships_task.Ship ship1 = new ships_task.Ship(harbour);
        ships_task.Ship ship2 = new ships_task.Ship(harbour);
        ships_task.Ship ship3 = new ships_task.Ship(harbour);
        ships_task.Ship ship4 = new ships_task.Ship(harbour);
        ships_task.Ship ship5 = new ships_task.Ship(harbour);
        ships_task.Ship ship6 = new ships_task.Ship(harbour);

        ships_task.Crane crane1 = new ships_task.Crane(harbour);
        ships_task.Crane crane2 = new ships_task.Crane(harbour);

        ship1.start();
        ship2.start();
        ship3.start();
        ship4.start();
        ship5.start();
        ship6.start();

        crane1.start();
        crane2.start();

        Thread statistics = new Thread(() -> {
            while (true) {
                try {
                    Thread.currentThread().sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("================================HARBOUR STATISTICS================================");
                StringBuilder builder = new StringBuilder();
                for (Dock dock : harbour.getDocks()) {
                    builder.append(DatabaseOperations.printDockStats(dock.getId())+"\n");
                }
                FileCreator.addInfoToFile(builder.toString(),DatabaseOperations.getReportNumber(),LocalDateTime.now());
                DatabaseOperations.incrementReportNumber();
                DatabaseOperations.printDockInfo();
                DatabaseOperations.printCraneInfo();
                DatabaseOperations.printMostLoadedShip();
                System.out.println("==================================================================================");
            }
        });
        statistics.start();
    }

}
