package gastation_task;

import java.util.LinkedList;
import java.util.Queue;

public class FuelColumn {

    private String name;
    private Queue<Driver> drivers;
    private static int number = 1;
    private int id;

    public FuelColumn(String name) {
        this.id = number++;
        this.name = name;
        this.drivers = new LinkedList<Driver>();
    }

    public void addDriver(Driver driver){
        this.drivers.offer(driver);
    }

    public Driver removeDriver(){
        return this.drivers.poll();
    }

    public Driver getNextDriverInfo(){
        return this.drivers.peek();
    }

    public String getName() {
        return name;
    }

    public boolean isEmpty(){
        boolean result = false;
        if(this.drivers.size() == 0 ){
            result = true;
        }
        return result;
    }

    public int getId() {
        return id;
    }
}
