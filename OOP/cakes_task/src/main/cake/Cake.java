package main.cake;

public abstract class Cake {

    private String name;
    private String description;
    private double price;
    private String kind;
    private String type;

    public Cake(String name, String description, double price, String kind, String type) {
        this.name = name;
        this.description = description;
        this.price = price;
        if(this.checkKind(kind)){
            this.kind = kind;
        }
        this.type = type;
    }

    public abstract boolean checkKind(String kind);

    public String getKind() {
        return kind;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return this.name + " " + this.price + " " + this.type;
    }
}
