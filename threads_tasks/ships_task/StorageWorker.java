public class StorageWorker extends Thread{

    private Storage storage;
    private String name;

    public StorageWorker(Storage storage, String name) {
        this.storage = storage;
        this.name = name;
    }

    @Override
    public void run() {
        while (true){
            try {
            this.storage.removePackages(this);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public String getWorkerName() {
        return name;
    }
}
