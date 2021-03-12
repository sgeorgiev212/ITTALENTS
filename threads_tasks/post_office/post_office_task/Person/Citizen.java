package post_office_task.Person;
import post_office_task.shipment.Package;
import post_office_task.post_office.PostOffice;
import post_office_task.shipment.Letter;
import post_office_task.util.Util;

import java.util.Random;

public class Citizen extends Person implements Runnable{

    private String address;
    private PostOffice postOffice;

    public Citizen(String first_name, String last_name,String address,PostOffice postOffice) {
        super(first_name, last_name);
        this.address = address;
        this.postOffice = postOffice;
    }

    @Override
    public void run() {
        if(new Random().nextBoolean()) {
            Citizen receiver = new Citizen(Util.getRandomName(), Util.getRandomLastName(), Util.getRandomAddress(), postOffice);
            Letter letter = new Letter(this, receiver);
            this.postOffice.addLetterToMailBox(this, letter);
        }else{
            if(new Random().nextBoolean()){
                Citizen receiver = new Citizen(Util.getRandomName(), Util.getRandomLastName(), Util.getRandomAddress(), postOffice);
                Letter letter = new Letter(this, receiver);
                this.postOffice.addShipment(this,letter);
            }else{
                Citizen receiver = new Citizen(Util.getRandomName(), Util.getRandomLastName(), Util.getRandomAddress(), postOffice);
                int length = Util.getRandomSize(40,80);
                int width = Util.getRandomSize(40,80);
                int height = Util.getRandomSize(40,80);
                boolean isBreakable = new Random().nextBoolean();
                Package pack = new Package(this,receiver,length,width,height,isBreakable);
                this.postOffice.addShipment(this,pack);
            }

        }
    }
}
