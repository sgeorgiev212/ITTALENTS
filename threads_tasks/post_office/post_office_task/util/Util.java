package post_office_task.util;

import java.util.Random;

public class Util {

    private static String[] names = {"Ivan","Petkan","Msho","Ivo","Niki"};
    private static String[] lastNames = {"Dimitrov","Todorov","Georgiev","Iordanov","Cvetkov"};
    private static String[] addresses = {"Sofia","Varna","Plovdiv","Ruse","Burgas"};

    public static String getRandomName(){
        return names[new Random().nextInt(names.length)];
    }
    public static String getRandomLastName(){
        return lastNames[new Random().nextInt(lastNames.length)];
    }

    public static String getRandomAddress(){
        return addresses[new Random().nextInt(addresses.length)];
    }

    public static int getRandomSize(int min,int max){
        return new Random().nextInt(max-min+1)+min;
    }

}
