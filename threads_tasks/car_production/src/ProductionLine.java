import parts.CarPart;

import java.util.ArrayList;

public class ProductionLine extends Thread{

    private String name;
    private ArrayList<CarPart> parts;

    public ProductionLine(String name,ArrayList<CarPart> parts) {
        this.name = name;
        this.parts = parts;
    }

    @Override
    public void run() {
        for (CarPart part : parts) {
            try {
                sleep(part.getBuildTimeSeconds()*1000);
                System.out.println(this.name+" built "+part.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
