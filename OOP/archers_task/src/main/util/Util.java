package main.util;

import java.util.Random;

public class Util {

    private static String[] menNames={"gosho","stancho","tonkata","dragan","petko"};
    private static String[] womenNames={"viktoria","kristina","daqna","cveti","mihaela"};

    public static int randmoize(int min,int max){
        return new Random().nextInt(max-min+1)+min;
    }

    public static String getManName(){
        return menNames[new Random().nextInt(menNames.length)];
    }

    public static String getWomanName(){
        return womenNames[new Random().nextInt(womenNames.length)];
    }


}
