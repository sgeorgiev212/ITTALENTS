package post_office_task.post_office;

import post_office_task.Person.Citizen;
import post_office_task.Person.MailCollector;
import post_office_task.Person.MailMan;
import post_office_task.Person.Person;
import post_office_task.shipment.Letter;
import post_office_task.shipment.Shipment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;

public class PostOffice {

    private ArrayList<MailBox> mailBoxes;
    private BlockingDeque<Shipment> storage;
    private TreeMap<LocalDate, TreeMap<Integer,ArrayList<Shipment>>> archive;
    private static final int MIN_QUANTITY = 6;

    public PostOffice() {
        this.mailBoxes = new ArrayList<MailBox>();
        for (int i = 0; i < 25; i++) {
            this.mailBoxes.add(new MailBox());
        }
        this.storage = new LinkedBlockingDeque<>();
        this.archive = new TreeMap<>();
    }

    public void addLetterToMailBox(Citizen citizen,Letter letter){
        synchronized (mailBoxes) {
            MailBox mailBox = this.getRandomMailBox();
            mailBox.addLetter(letter);
            System.out.println(citizen.getFirst_name() + " added " + letter.getName() + " to " + mailBox.getName());
            mailBoxes.notifyAll();
        }
    }

    public void collectLetters(MailCollector collector){
        synchronized (mailBoxes){
            while(this.storage.size()>MIN_QUANTITY){
                try {
                    mailBoxes.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (MailBox mailBox : mailBoxes) {
                if(!mailBox.isEmpty()){
                    CopyOnWriteArrayList<Letter> letters = mailBox.getLetters();
                    for (Letter letter : letters) {
                        System.out.println(letter.getName()+" was collected by "+collector.getFirst_name()+" from "+mailBox.getName());
                        this.addShipment(collector,letter);
                        mailBox.removeLetter(letter);
                    }
                }
                mailBoxes.notifyAll();
            }
        }
    }

    public synchronized void addShipment(Person p, Shipment shipment){
        this.storage.offer(shipment);
        this.addToArchive(shipment);
        System.out.println(p.getFirst_name()+" added "+shipment.getName()+" to the post office storage");
        notifyAll();
    }

    public synchronized void deliverShipments(MailMan mailMan){
        while (this.storage.size() < MIN_QUANTITY){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Shipment shipment = this.storage.poll();
        double deliveryTime = shipment.getDeliveryTimeSeconds();
        try {
            Thread.currentThread().sleep((long) (deliveryTime*1000));
            System.out.println(mailMan.getFirst_name()+" delivered "+shipment.getName());
            mailMan.increaseNumberOfDeliveries();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyAll();

    }

    private void addToArchive(Shipment shipment){
        if(!this.archive.containsKey(LocalDate.now())){
            this.archive.put(LocalDate.now(),new TreeMap<>());
        }
        if(!this.archive.get(LocalDate.now()).containsKey(LocalTime.now().getHour())){
            this.archive.get(LocalDate.now()).put(LocalTime.now().getHour(),new ArrayList<>());
        }
        this.archive.get(LocalDate.now()).get(LocalTime.now().getHour()).add(shipment);
    }

    public MailBox getRandomMailBox(){
        return this.mailBoxes.get(new Random().nextInt(this.mailBoxes.size()));
    }

    public TreeMap<LocalDate, TreeMap<Integer, ArrayList<Shipment>>> getArchive() {
        return archive;
    }

    public Queue<Shipment> getStorage() {
        return storage;
    }
}
