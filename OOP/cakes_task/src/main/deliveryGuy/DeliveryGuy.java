package main.deliveryGuy;

import main.order.Order;

import java.util.HashSet;

public class DeliveryGuy {

    private String name;
    private String phone;
    private HashSet<Order> orders;
    private double tips;

    public DeliveryGuy(String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.orders = new HashSet<>();
        this.tips = 0;
    }

    public void addOrder(Order order){
        this.orders.add(order);
    }

    public void receiveTip(double money){
        this.tips+=money;
    }

    public double getTips() {
        return tips;
    }

    @Override
    public String toString() {
        return this.name +" "+this.tips;
    }

    public HashSet<Order> getOrders() {
        return orders;
    }
}
