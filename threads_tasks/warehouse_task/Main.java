public class Main {

    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
    Supplier supplier = new Supplier(warehouse);

    Shop shop = new Shop(warehouse);
    Shop shop2 = new Shop(warehouse);
    Shop shop3 = new Shop(warehouse);

    Client client1 = new Client(shop);
    Client client2 = new Client(shop);
    Client client3 = new Client(shop);
    Client client4 = new Client(shop2);
    Client client5 = new Client(shop2);
    Client client6 = new Client(shop2);
    Client client7 = new Client(shop3);
    Client client8 = new Client(shop3);
    Client client9 = new Client(shop3);
    Thread statistics = new Thread(() -> {
        while (true){
            try {
                Thread.currentThread().sleep(8000);
                warehouse.printAvailableProducts();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });
    statistics.setDaemon(true);

    supplier.start();
    shop.start();
    shop2.start();
    shop3.start();

    client1.start();
    client2.start();
    client3.start();
    client4.start();
    client5.start();
    client6.start();
    client7.start();
    client8.start();
    client9.start();
    statistics.start();




    }

}
