import java.util.Random;

public class Util {

    public static String[] products = {"banana","orange","apple","potato","eggplant","cucumber","pork","beef","chicken"};

    public static int randomize(int min,int max){
        return new Random().nextInt(max-min+1)+min;
    }

    public static String getRandomProduct(){
        return products[new Random().nextInt(products.length)];
    }

}
