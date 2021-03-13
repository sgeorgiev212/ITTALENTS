package ships_task;

import ships_task.DataBase.DatabaseOperations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Harbour {

      private ArrayList<Dock> docks;
      private ArrayList<Storage> storages;
      private CopyOnWriteArrayList<PackageInfo> statistics;

    public Harbour() {
        this.docks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            docks.add(new Dock());
        }
        this.storages = new ArrayList<>();
        storages.add(new Storage());
        storages.add(new Storage());
        this.statistics = new CopyOnWriteArrayList<>();
        Distributor distributor1 = new Distributor("Distributor 1",storages.get(0));
        Distributor distributor2 = new Distributor("Distributor 2",storages.get(1));
        distributor1.start();
        distributor2.start();
    }

    public synchronized void goToHarbour(Ship ship){
        Dock dock = this.getRandomDock();
        dock.addShip(ship);
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
        try {
            for (Dock dock : docks) {
                if(!dock.isEmpty()) {
                    Storage storage = this.getRandomStorage();
                    Ship ship = dock.seeNextShip();
                    for (Package pack : ship.getPackages()) {
                        Thread.currentThread().sleep(2000);
                        storage.addPackage(pack);
                        System.out.println(crane.getCraneName() + " unloaded " + pack.getName() + " at " + storage.getName());
                        PackageInfo info = new PackageInfo(pack.getId(),dock.getId(),ship.getShipName(),crane.getCraneId(),LocalDateTime.now());
                        this.statistics.add(info);
                        DatabaseOperations.addInfoToDB(info);
                    }
                    dock.removeShip(ship);
                    System.out.println(ship.getShipName() + " left " + dock.getName());
                }
                notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Dock getRandomDock(){
        Dock dock =  null;
        int number = Util.randomize(1,5);
        switch (number){
            case 1:
                dock = docks.get(0);
                break;
            case 2:
                dock = docks.get(1);
                break;
            case 3:
                dock = docks.get(2);
                break;
            case 4:
                dock = docks.get(3);
                break;
            case 5:
                dock = docks.get(4);
                break;

        }
        return dock;
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
        return this.storages.get(new Random().nextInt(storages.size()));
    }

    public ArrayList<Dock> getDocks() {
        return docks;
    }
}
