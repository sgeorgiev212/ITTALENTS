package ships_task;

import java.util.ArrayList;

public class Ship extends Thread{

    private String name;
    private static int number = 1;
    private ArrayList<Package> packages;
    private Harbour harbour;

    public Ship(Harbour harbour) {
        this.name = "Ship "+number++;
        this.packages = new ArrayList<>();
        int number = Util.randomize(1,4);
        for (int i = 0; i < number; i++) {
            this.packages.add(new Package());
        }
        this.harbour = harbour;
    }

    @Override
    public void run() {
        this.harbour.goToHarbour(this);
    }

    public String getShipName() {
        return name;
    }

    public ArrayList<Package> getPackages() {
        return packages;
    }
}
