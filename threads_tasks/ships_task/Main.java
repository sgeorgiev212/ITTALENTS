public class Main {

    public static void main(String[] args) {
        Harbour harbour = new Harbour();

        Storage storage = new Storage("Storage 1");
        Storage storage2 = new Storage("Storage 2");
        harbour.addStorage(storage);
        harbour.addStorage(storage2);

        Ship ship = new Ship("Ship 1",harbour);
        Ship ship2 = new Ship("Ship 2",harbour);
        Ship ship3 = new Ship("Ship 3",harbour);
        Ship ship4 = new Ship("Ship 4",harbour);
        Ship ship5 = new Ship("Ship 5",harbour);
        Ship ship6 = new Ship("Ship 6",harbour);
        Ship ship7 = new Ship("Ship 7",harbour);

        Crane crane = new Crane("Crane 1",harbour,1);
        Crane crane2 = new Crane("Crane 2",harbour,2);

        StorageWorker worker = new StorageWorker(storage,"worker 1");
        StorageWorker worker2 = new StorageWorker(storage2,"worker 2");

        ship.start();
        ship2.start();
        ship3.start();
        ship4.start();
        ship5.start();
        ship6.start();
        ship7.start();

        crane.start();
        crane2.start();

        worker.start();
        worker2.start();

        try {
            Thread.currentThread().sleep(40000);
            System.out.println("=======================HARBOUR STATISTICS====================");
            Database database = new Database();
            database.printDockInfo(1);
            database.printDockInfo(2);
            database.printDockInfo(3);
            database.printDockInfo(4);
            database.printDockInfo(5);
            database.printNumberOfShips(1);
            database.printNumberOfShips(2);
            database.printNumberOfShips(3);
            database.printNumberOfShips(4);
            database.printNumberOfShips(5);
            database.printCraneInfo(1);
            database.printCraneInfo(2);
            database.printMostLoadedShip();
            System.out.println("=============================================================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
