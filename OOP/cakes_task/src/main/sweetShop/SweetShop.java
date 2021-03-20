package main.sweetShop;

import main.cake.Cake;
import main.deliveryGuy.DeliveryGuy;
import main.util.Util;

import java.util.*;

public class SweetShop {

    private String name;
    private String address;
    private String phone;
    private HashSet<DeliveryGuy> deliveryGuys;
    private HashMap<String,HashMap<String, ArrayList<Cake>>> catalogue;
    private double money;

    public SweetShop(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.deliveryGuys = new HashSet<>();
        this.catalogue = new HashMap<>();
        this.money=0;
    }

    public void addDeliveryGuy(DeliveryGuy deliveryGuy){
        this.deliveryGuys.add(deliveryGuy);
    }

    public void addCake(Cake cake){
        if(!this.catalogue.containsKey(cake.getType())){
            this.catalogue.put(cake.getType(),new HashMap<>());
        }
        if(!this.catalogue.get(cake.getType()).containsKey(cake.getKind())) {
            this.catalogue.get(cake.getType()).put(cake.getKind(), new ArrayList<>());
        }
        this.catalogue.get(cake.getType()).get(cake.getKind()).add(cake);
    }

    public boolean checkForCake(Cake cake){
        boolean result = false;
        if(this.catalogue.get(cake.getType()).get(cake.getKind())!=null) {
            if (this.catalogue.get(cake.getType()).get(cake.getKind()).size() >= 1) {
                result = true;
            }
        }
        return result;
    }

    public void removeCake(Cake cake){
        ArrayList<Cake> cakes =catalogue.get(cake.getType()).get(cake.getKind());
        Iterator<Cake> it = cakes.iterator();
        while(it.hasNext()){
            Cake inner = it.next();
            if(inner == cake){
                it.remove();
                break;
            }
        }
    }

    public DeliveryGuy getDeliveryGuy(){
        int number = Util.randomize(0,this.deliveryGuys.size()-1);
        DeliveryGuy deliveryGuy = null;
        int counter = 0;
        for (DeliveryGuy guy : deliveryGuys) {
            if(counter==number){
                deliveryGuy = guy;
            }
            counter++;
        }
        return deliveryGuy;
    }

    public void collectMoneyFromOrder(double money){
        this.money+=money;
    }

    public void printAvailableCakes(){
        for(Map.Entry<String,HashMap<String,ArrayList<Cake>>> entry : this.catalogue.entrySet()){
            System.out.println("====================TYPE "+entry.getKey()+"============================");
            HashSet<Map.Entry<String,ArrayList<Cake>>> kinds = new HashSet<>();
            kinds.addAll(entry.getValue().entrySet());
            for(Map.Entry<String,ArrayList<Cake>> kind : kinds){
                System.out.println("kind: "+kind.getKey());
                ArrayList<Cake> cakesForKind = kind.getValue();
                for (Cake cake : cakesForKind) {
                    System.out.println(cake);
                }
            }
        }
    }

    public double getMoney() {
        return money;
    }

    public HashSet<DeliveryGuy> getDeliveryGuys() {
        return deliveryGuys;
    }
}
