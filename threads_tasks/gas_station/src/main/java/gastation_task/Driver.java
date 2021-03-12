package gastation_task;

import gastation_task.util.Util;

import java.util.Random;

public class Driver extends Thread{

    private static int number = 1;
    private String name;
    private GasStation gasStation;
    private FuelColumn column;
    private String fuelType;
    private double fuelAmount;
    private boolean isLoaded;

    public Driver(GasStation gasStation) {
        this.name = "Driver "+number++;
        this.gasStation = gasStation;
        this.isLoaded = false;
    }

    @Override
    public void run() {
        this.setRandomFuel();
        this.setRandomAmount();
        this.gasStation.getFuel(this);
    }

    public String getDriverName(){
        return this.name;
    }

    public void setColumn(FuelColumn column) {
        this.column = column;
    }

    public FuelColumn getColumn() {
        return column;
    }

    public boolean isLoaded(){
        return  this.isLoaded;
    }

    public void markLoaded(){
        this.isLoaded = true;
    }

    public void setRandomFuel(){
        Random random = new Random();
        int number = random.nextInt(3);
        switch (number){
            case 0:
                this.fuelType = "Gas";
                break;
            case 1:
                this.fuelType = "Diesel";
                break;
            case 2:
                this.fuelType = "Petrol";
                break;
        }
    }

    public void setRandomAmount(){
       double amount = Util.getRandomNumber(10,40);
       this.fuelAmount = amount;
    }

    public String getFuelType() {
        return fuelType;
    }

    public double getFuelAmount() {
        return fuelAmount;
    }
}
