import java.util.ArrayList;

public class Ship extends Thread{

    private String name;
    private ArrayList<Package> packages;
    Harbour harbour;

    public Ship(String name,Harbour harbour) {
        this.name = name;
        this.packages = new ArrayList<>();
        int numberOfPackages = Util.randomize(1,4);
        for (int i = 0; i < numberOfPackages; i++) {
             packages.add(new Package());
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
