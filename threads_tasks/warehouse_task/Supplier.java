public class Supplier extends Thread{

    private Warehouse warehouse;

    public Supplier(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (true){
            this.warehouse.receiveProducts();
        }
    }
}
