package ships_task;

public class Distributor extends Thread{

    private String name;
    private Storage storage;

    public Distributor(String name, Storage storage) {
        this.name = name;
        this.storage = storage;
    }

    @Override
    public void run() {
        while (true){
            this.storage.removePackage(this);
        }
    }

    public String getDistributorName(){
        return name;
    }
}
