package post_office_task.shipment;

import post_office_task.Person.Citizen;

public class Package extends Shipment {

    private int length;
    private int width;
    private int height;
    private boolean isBreakable;
    private String name;
    private static int number = 1;

    public Package(Citizen sender, Citizen receiver, int length, int width, int height, boolean isBreakable) {
        super(sender, receiver);
        this.length = length;
        this.width = width;
        this.height = height;
        this.isBreakable = isBreakable;
        this.name = "Package "+number++;
    }

    public double getShipmentPrice() {
        double price = 2;
        if(length>60 || width>60 || height>60){
            price *=2;
        }
        if(this.isBreakable){
            price*=2;
        }
        return price;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getDeliveryTimeSeconds() {
        return 1.5;
    }

    @Override
    public boolean isBreakable() {
        return this.isBreakable;
    }

    @Override
    public String getType() {
        return "Package";
    }
}
