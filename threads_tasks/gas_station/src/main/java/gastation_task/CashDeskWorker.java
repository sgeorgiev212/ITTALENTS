package gastation_task;

public class CashDeskWorker extends Thread{

    private CashDesk cashDesk;
    private String name;

    public CashDeskWorker(CashDesk cashDesk,String name) {
        this.cashDesk = cashDesk;
        this.name = name;
    }

    @Override
    public void run() {
        while (true){
            try {
                sleep(5000);
                this.cashDesk.removeDriverFromQueue(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public String getCashDeskWorkerName(){
        return  this.name;
    }
}
