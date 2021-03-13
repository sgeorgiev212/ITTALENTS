package ships_task;

import java.util.Random;

public class Util {

    public static int randomize(int min,int max){
        return new Random().nextInt(max-min+1)+min;
    }

}
