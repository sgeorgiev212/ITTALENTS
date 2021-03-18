package grill_task.fileCreator;

import grill_task.database.DatabaseOperations;


import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;

public class FileOpeartions {

    public static void readInfoFromFIleAndInsertToDB() {
        File file = new File("orders.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String[] order = scanner.nextLine().split(", ");
                String breadTye = order[0];
                String meatType = order[1];
                String garnishType = order[2];
                String price = order[3];
                String date = order[4];
                int breadId = getBreadId(breadTye);
                int meatId = getMeatType(meatType);
                int garnishId = getGarnishType(garnishType);
                DatabaseOperations.insertInfoToDB(27,breadId,meatId,garnishId, LocalDateTime.now());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int getBreadId(String breadType){
        if(breadType.equals("whole")){
            return 2;
        }else{
            return 1;
        }
    }

    private static  int getGarnishType(String garnishType){
        int number = 0;
        switch (garnishType){
            case "ruska":
                number= 1;
                break;
            case "liutenitsa":
                number= 2;
                break;
            case "snejanka":
                number= 3;
                break;
            case "carrot":
                number= 4;
                break;
            case "tomato":
                number= 5;
                break;
        }
        return number;
    }

    private static int getMeatType(String meatType){
        int number = 0;
        switch (meatType){
            case "meatball":
                number = 1;
                break;
            case "pleskavica":
                number = 2;
                break;
            case "steak":
                number = 3;
                break;
        }
        return number;
    }


}
