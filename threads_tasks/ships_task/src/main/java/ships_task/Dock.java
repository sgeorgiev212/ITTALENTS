package ships_task;

import java.util.LinkedList;
import java.util.Queue;

public class Dock {

    private String name;
    private static int number = 1;
    private int id;
    private Queue<Ship> ships;

    public Dock() {
        this.name = "Dock "+number;
        this.id = number++;
        this.ships = new LinkedList<>();
    }

    public void addShip(Ship ship){
        this.ships.offer(ship);
    }

    public Ship removeShip(Ship ship){
        return this.ships.poll();
    }

    public Ship seeNextShip(){
        return this.ships.peek();
    }

    public String getName() {
        return name;
    }

    public boolean isEmpty(){
        boolean result = false;
        if (this.ships.size() == 0) {
            result = true;
        }
        return result;
    }

    public int getId() {
        return id;
    }
}
