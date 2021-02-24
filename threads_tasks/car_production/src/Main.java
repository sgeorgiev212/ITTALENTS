import parts.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {


        ArrayList<CarPart> parts1 = new ArrayList<>();
        parts1.add(new Tire());
        parts1.add(new Tire());
        parts1.add(new Tire());
        parts1.add(new Tire());
        parts1.add(new Seat());


        ArrayList<CarPart> parts2 = new ArrayList<>();
        parts2.add(new Seat());
        parts2.add(new Seat());
        parts2.add(new Seat());
        parts2.add(new Seat());

        ArrayList<CarPart> parts3 = new ArrayList<>();
        parts3.add(new Engine());
        parts3.add(new Frame());

        ProductionLine line1 = new ProductionLine("line1",parts1);
        ProductionLine line2 = new ProductionLine("line 2",parts2);
        ProductionLine line3 = new ProductionLine("line 3",parts3);

        long startTime = System.currentTimeMillis();
        line1.start();
        line2.start();
        line3.start();

        try {
            line1.join();
            line2.join();
            line3.join();
            long end = System.currentTimeMillis();
            System.out.println("the whole process took: "+(end-startTime)+" seconds");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
