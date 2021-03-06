import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Harbour {

  private ArrayList<Dock> docks;
  private ArrayList<Storage> storages;
  private CopyOnWriteArrayList<Statistics> statistics;

    public Harbour() {
        this.docks = new ArrayList<>();
        this.docks.add(new Dock("Dock 1"));
        this.docks.add(new Dock("Dock 2"));
        this.docks.add(new Dock("Dock 3"));
        this.docks.add(new Dock("Dock 4"));
        this.docks.add(new Dock("Dock 5"));
        this.storages = new ArrayList<>();
        this.statistics = new CopyOnWriteArrayList<>();
    }

    public synchronized void goToHarbour(Ship ship){
        Dock dock = this.getRandomDock();
        dock.addShipToDock(ship);
        System.out.println(ship.getShipName()+" arrived at "+dock.getName());
        notifyAll();
    }

    public synchronized void unloadShips(Crane crane){
        while (harbourIsEmpty()){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Dock dock : docks) {
            if(!dock.isEmpty()){
                try {
                    Ship ship = dock.removeShipFromDock();
                    System.out.println(ship.getShipName()+" is being unloaded by "+crane.getCraneName());
                    Thread.currentThread().sleep(ship.getPackages().size()*1000);
                    Storage storage = this.getRandomStorage();
                    storage.addPackages(ship.getPackages());
                    System.out.println(ship.getShipName()+"'s packages were added to "+storage.getName());
                    for (Package pack : ship.getPackages()) {
                    Statistics statistics = new Statistics(pack.getId(),dock.getDockNumger(),ship.getShipName(),crane.getCraneId(), LocalDateTime.now());
                    this.statistics.add(statistics);
                    Database database = new Database();
                    database.addPackageToDatabase(statistics);
                    notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    public Dock getRandomDock(){
        return this.docks.get(new Random().nextInt(docks.size()));
    }

    public boolean harbourIsEmpty(){
        boolean result = false;
        for (Dock dock : docks) {
            if (dock.isEmpty()){
                result = true;
            }else{
                result = false;
                break;
            }
        }
        return result;
    }

    public Storage getRandomStorage(){
        Storage storage;
        if(new Random().nextBoolean()){
            storage = this.storages.get(0);
        }else{
            storage = this.storages.get(1);
        }
        return storage;
    }

    public void addStorage(Storage storage){
        this.storages.add(storage);
    }
}
