package main.bow;

public abstract class Bow implements IBow{

    private String manufacturer;
    private double weight;
    private int bowStrength;
    protected String type;

    public Bow(String manufacturer, double weight, int bowStrength,String type) {
        this.manufacturer = manufacturer;
        this.weight = weight;
        if(this.checkBowStrength(bowStrength)) {
            this.bowStrength = bowStrength;
        }
        this.type = type;
    }

    public abstract boolean checkBowStrength(int bowStrength);

}
