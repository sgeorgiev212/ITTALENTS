public class Client extends Thread{

    private Shop shop;
    private String name;
    private static int number = 1;

    public Client(Shop shop) {
        this.name = "client "+number++;
        this.shop = shop;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String product = Util.getRandomProduct();
                int quantity = Util.randomize(1, 4);
                this.shop.sellProduct(product, quantity, this);
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public String getClientName() {
        return name;
    }
}
