import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Shop extends Thread{

    private Warehouse warehouse;
    private HashMap<String, HashMap<String,Integer>> products;
    private static final int MIN_QUANTITY = 6;
    private String name;
    private static int number = 1;

    public Shop(Warehouse warehouse) {
        this.name = "shop "+number++;
        this.warehouse = warehouse;
        this.products = new HashMap<>();
        this.products = new HashMap<>();
        this.products.put("fruits",new HashMap<>());
        this.products.put("vegetables",new HashMap<>());
        this.products.put("meats",new HashMap<>());

        this.products.get("fruits").put("banana",10);
        this.products.get("fruits").put("orange",10);
        this.products.get("fruits").put("apple",10);
        this.products.get("vegetables").put("potato",10);
        this.products.get("vegetables").put("eggplant",10);
        this.products.get("vegetables").put("cucumber",10);
        this.products.get("meats").put("pork",10);
        this.products.get("meats").put("beef",10);
        this.products.get("meats").put("chicken",10);
    }


    public synchronized void sellProduct(String product,int quantity,Client client){
        while(productIsDeficit(product)){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.removeProduct(product,quantity);
        System.out.println(client.getClientName()+" bought "+quantity+" "+product+" from "+name);
        notifyAll();

    }

    @Override
    public void run() {
        while (true){
            ArrayList<String> deficitProducts = getDeficitProducts();
            if(deficitProducts.size()>=1) {
                for (String product : deficitProducts) {
                this.warehouse.deliverProducts(product,this);
                }
            }
        }
    }

    private ArrayList<String> getDeficitProducts(){
        ArrayList<String> deficitProducts = new ArrayList<>();
        for (HashMap<String, Integer> inner : products.values()) {
            for (Map.Entry<String, Integer> entry : inner.entrySet()) {
                if(entry.getValue() < MIN_QUANTITY){
                    deficitProducts.add(entry.getKey());
                }
            }
        }
        return deficitProducts;
    }

    private boolean productIsDeficit(String product){
        boolean result = false;
        for(HashMap<String,Integer> inner : products.values()){
            for (Map.Entry<String,Integer> entry : inner.entrySet()){
                if (entry.getKey().equals(product)){
                    if(entry.getValue()<MIN_QUANTITY){
                        result = true;
                        break;
                    }
                }
            }
        }
        return result;
    }

    private void removeProduct(String product,int quantity){
        for(HashMap<String,Integer> inner : products.values()){
            for (Map.Entry<String,Integer> entry : inner.entrySet()){
                if (entry.getKey().equals(product)){
                    inner.put(product,inner.get(product)-quantity);
                        break;
                    }
                }
            }
    }

    public String getShopName() {
        return name;
    }

    public  void receiveProduct(String product){
        for(HashMap<String,Integer> inner : products.values()){
            for (Map.Entry<String,Integer> entry : inner.entrySet()){
                if (entry.getKey().equals(product)){
                    inner.put(product,inner.get(product)+5);
                    break;
                }
            }
        }
    }
}


