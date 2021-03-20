package main.order;

import main.cake.Cake;
import main.client.Client;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {

    private Client client;
    private double price;
    private String address;
    private ArrayList<Cake> orderedCakes;
    LocalDateTime time;

    public Order(Client client, double price, String address, ArrayList<Cake> orderedCakes, LocalDateTime time) {
        this.client = client;
        this.price = price;
        this.address = address;
        this.orderedCakes = orderedCakes;
        this.time = time;
    }
}
