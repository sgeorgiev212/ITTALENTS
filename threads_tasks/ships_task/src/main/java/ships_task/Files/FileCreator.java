package ships_task.Files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class FileCreator {

    private static int  numberOfReport = 1;

    public static void addInfoToFile(String text, int number, LocalDateTime localDateTime){
          File file = new File("report-"+number+"-"+localDateTime.toString().replace(":","").replace(".","")+".txt");
        try( BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
           writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
