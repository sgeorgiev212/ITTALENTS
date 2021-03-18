package grill_task.fileCreator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class FileCreator {

    public static void addOrderToFile(String order, double price, LocalDateTime time){
        File file = new File("orders.txt");
        try(BufferedWriter writer = new BufferedWriter( new FileWriter(file,true))){
            writer.write(order+", "+price+", "+time+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
