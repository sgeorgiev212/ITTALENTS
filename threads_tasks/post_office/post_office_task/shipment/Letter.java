package post_office_task.shipment;

import post_office_task.Person.Citizen;

public class Letter extends Shipment{

    private String name;
    private static int number = 1;


    public Letter(Citizen sender, Citizen receiver) {
        super(sender, receiver);
        this.name = "Letter "+number++;
    }

    public double getShipmentPrice() {
        return 0.5;
    }

    public String getName() {
        return name;
    }

    @Override
    public double getDeliveryTimeSeconds() {
        return 1;
    }

    @Override
    public boolean isBreakable() {
        return false;
    }

    @Override
    public String getType() {
        return "Letter";
    }
}
