package main.util;

import java.util.Random;

public class Util {

    private static String[] cakeNames ={"bomba","shoko","eklerova","exoticMix","qgodova"};
    private static String[] standardCakes ={"biscuit","ekler","fruit","chocolate"};
    private static String[] weddingCakes ={"big","small","medium"};
    private static String[] specialCakes ={"anniversary","company","advertise"};
    private static String[] kidsCake ={"birthDay","nameDay","firstSteps"};
    private static String[] kidName={"pepi","krisi","viki"};
    private static String[] deliveryGuyNames={"petar","ivan","pavel"};
    private static String[] clientNames={"nikolai","misho","pesho"};
    private static String[] address={"sofia","plovdiv","burgas","varna"};

    public static int randomize(int min,int max){
        return new Random().nextInt(max-min+1)+min;
    }

    public static double randomizeDouble(int min,int max){
        return new Random().nextInt(max-min+1)+min;
    }

    public static String getCakeName(){
        return cakeNames[new Random().nextInt(cakeNames.length)];
    }
    public static String getClientName(){
        return clientNames[new Random().nextInt(clientNames.length)];
    }
    public static String getDeliveryGuyName(){
        return deliveryGuyNames[new Random().nextInt(deliveryGuyNames.length)];
    }
    public static String getKidName(){
        return kidName[new Random().nextInt(kidName.length)];
    }
    public static String getAddress(){
        return address[new Random().nextInt(address.length)];
    }

    public static String getCakeKind(String type){
        String kind = null;
        switch (type){
            case "standard":
                kind = standardCakes[new Random().nextInt(standardCakes.length)];
                break;
            case "wedding":
                kind = weddingCakes[new Random().nextInt(weddingCakes.length)];
                break;
            case "special":
                kind = specialCakes[new Random().nextInt(specialCakes.length)];
                break;
            case "kids":
                kind = kidsCake[new Random().nextInt(kidsCake.length)];
                break;
        }
        return kind;
    }



}
