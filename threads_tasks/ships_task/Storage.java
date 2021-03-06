import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Storage {

    private String name;
    private Queue<Package> packages;

    public Storage(String name) {
        this.name = name;
        this.packages = new LinkedList<>();
    }

    public synchronized void addPackages(ArrayList<Package> packages){
        this.packages.addAll(packages);
        notifyAll();
    }

    public synchronized void removePackages(StorageWorker worker){
        while (this.packages.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Package pack = this.packages.poll();
        System.out.println("package "+pack.getId()+" was removed from "+this.name+" by "+worker.getWorkerName());
        notifyAll();
    }

    public String getName() {
        return name;
    }
}
