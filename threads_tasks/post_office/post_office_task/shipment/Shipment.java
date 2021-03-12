package post_office_task.shipment;

import post_office_task.Person.Citizen;

public abstract class Shipment {

    private Citizen sender;
    private Citizen receiver;

    public Shipment(Citizen sender, Citizen receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    public abstract double getShipmentPrice();
    public abstract String getName();
    public abstract double getDeliveryTimeSeconds();
    public abstract boolean isBreakable();
    public abstract String getType();

    public Citizen getSender() {
        return sender;
    }

    public Citizen getReceiver() {
        return receiver;
    }
}
