package gastation_task.fileOperations;

import java.io.*;
import java.nio.Buffer;

public class FileOperations {

    public static void  addInfoToFIle(String info){
      File file = new File("report-1-10.03.2021.txt");
        try (BufferedWriter writer= new BufferedWriter(new FileWriter(file,true));){
            writer.write(info);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
