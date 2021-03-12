package gastation_task;

public class Worker extends Thread{

   private String name;
   private GasStation gasStation;

    public Worker(String name,GasStation gasStation) {
        this.name = name;
        this.gasStation = gasStation;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(5000);
                this.gasStation.loadCarsWithFuel(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getWorkerName(){
        return name;
    }
}
