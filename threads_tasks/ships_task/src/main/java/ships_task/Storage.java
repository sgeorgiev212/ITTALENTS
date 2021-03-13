package ships_task;

import java.util.LinkedList;
import java.util.Queue;

public class Storage {

    private Queue<Package> packages;
    private String name;
    private static int number = 1;

    public Storage() {
        this.packages = new LinkedList<>();
        this.name = "Storage "+number++;
    }

    public synchronized void addPackage(Package pack){
        this.packages.offer(pack);
        notifyAll();
    }

    public synchronized void removePackage(Distributor distributor){
        while (this.packages.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Package pack = packages.poll();
        try {
            Thread.currentThread().sleep(5000);
            System.out.println(distributor.getDistributorName()+" delivered "+pack.getName()+" from "+name);
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }
}
