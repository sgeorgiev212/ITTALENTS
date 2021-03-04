import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Warehouse {

    private HashMap<String,HashMap<String,Integer>> products;
    private static final int MIN_QUANTITY = 15;

    public Warehouse() {
        this.products = new HashMap<>();
        this.products.put("fruits",new HashMap<>());
        this.products.put("vegetables",new HashMap<>());
        this.products.put("meats",new HashMap<>());

        this.products.get("fruits").put("banana",15);
        this.products.get("fruits").put("orange",15);
        this.products.get("fruits").put("apple",15);
        this.products.get("vegetables").put("potato",15);
        this.products.get("vegetables").put("eggplant",15);
        this.products.get("vegetables").put("cucumber",15);
        this.products.get("meats").put("pork",15);
        this.products.get("meats").put("beef",15);
        this.products.get("meats").put("chicken",15);

    }

    public synchronized void receiveProducts(){
              while (!hasDeficitProducts()){
                  try {
                      wait();
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
              ArrayList<String> deficitProducts = this.getDeficitProducts();
        for (String deficitProduct : deficitProducts) {
            for (HashMap<String, Integer> inner : products.values()) {
                if(inner.containsKey(deficitProduct)){
                    inner.put(deficitProduct,inner.get(deficitProduct)+25);
                }
            }
        }

        notifyAll();
    }

    public synchronized void deliverProducts(String product,Shop shop){
        while(productIsDeficit(product)){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.removeProduct(product);
        System.out.println("the warehouse supplied "+shop.getShopName()+" with "+product);
        shop.receiveProduct(product);
        notifyAll();
    }

    public boolean hasDeficitProducts(){
        boolean result = false;
        for (HashMap<String, Integer> inner : products.values()) {
            for (Map.Entry<String, Integer> entry : inner.entrySet()) {
                if(entry.getValue() < MIN_QUANTITY){
                    result = true;
                    break;
                }
            }
        }
        return  result;
    }

    public ArrayList<String> getDeficitProducts(){
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

    public boolean productIsDeficit(String product){
        boolean result = false;
        for (HashMap<String, Integer> inner : products.values()) {
            for (Map.Entry<String, Integer> entry : inner.entrySet()) {
               if(entry.getKey().equals(product)){
                   if(entry.getValue()<MIN_QUANTITY){
                       result = true;
                       break;
                   }
               }
            }
        }
        return  result;
    }

    public void removeProduct(String product){
        for (HashMap<String, Integer> inner : products.values()) {
            for (Map.Entry<String, Integer> entry : inner.entrySet()) {
                if(entry.getKey().equals(product)){
                    inner.put(product,inner.get(product)-5);
                }
            }
        }
    }

    public void printAvailableProducts(){
        System.out.println("====================Warehouse Products=======================");
        for (HashMap<String, Integer> value : products.values()) {
            for (Map.Entry<String, Integer> entry : value.entrySet()) {
                System.out.println(entry.getKey()+": "+entry.getValue());
            }
        }
        System.out.println("==============================================================");
    }

}
