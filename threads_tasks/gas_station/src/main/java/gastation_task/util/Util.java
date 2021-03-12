package gastation_task.util;

import java.util.Random;

public class Util {

    public static int getRandomNumber(int min, int max){
        return new Random().nextInt(max-min+1)+min;
    }

}
