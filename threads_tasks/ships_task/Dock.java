import java.util.LinkedList;
import java.util.Queue;

public class Dock {

    private Queue<Ship> ships;
    private String name;
    private static  int number;
    private int dockNumger;

    public Dock(String name) {
        this.name = name;
        this.ships = new LinkedList<>();
        this.dockNumger = number++;
    }

    public void addShipToDock(Ship ship){
        this.ships.add(ship);
    }

    public String getName() {
        return name;
    }

    public boolean isEmpty(){
        boolean result = false;
        if(this.ships.size() == 0){
            result = true;
        }
        return result;
    }

    public Ship removeShipFromDock(){
        return this.ships.poll();
    }

    public int getDockNumger() {
        return dockNumger;
    }
}
