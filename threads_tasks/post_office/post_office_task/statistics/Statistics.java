package post_office_task.statistics;


import post_office_task.Person.MailMan;
import post_office_task.post_office.PostOffice;
import post_office_task.shipment.Shipment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TreeMap;

public class Statistics {

     private PostOffice postOffice;

    public Statistics(PostOffice postOffice) {
        this.postOffice = postOffice;
    }

    public void printShipmentsByDate(LocalDate date){
        System.out.println("Shipments for "+date+": ");
        TreeMap<LocalDate, TreeMap<Integer, ArrayList<Shipment>>> archive = this.postOffice.getArchive();
        TreeMap<Integer, ArrayList<Shipment>> inner = archive.get(date);
        for (ArrayList<Shipment> list : inner.values()) {
            for (Shipment shipment : list) {
                System.out.println(shipment.getName());
            }
        }
    }
    public void printLettersPercentage(LocalDate date){
        int numberOfLetters = 0;
        int numberOfPackages = 0;
        TreeMap<Integer, ArrayList<Shipment>> inner = postOffice.getArchive().get(date);
        for (ArrayList<Shipment> list : inner.values()) {
            for (Shipment shipment : list) {
                if(shipment.getName().contains("Package")){
                    numberOfPackages++;
                }else{
                    numberOfLetters++;
                }
            }
        }
        int sum = numberOfLetters+numberOfPackages;
        double percentage = (100*numberOfLetters)/sum;
        System.out.println("Number of letters: "+numberOfLetters);
        System.out.println("Number of packages: "+numberOfPackages);
        System.out.println("Letters percentage: "+percentage+"%");
    }

    public void breakablePacksPercentage() {
        ArrayList<Package> packages = new ArrayList<>();
        int numberOfPackages = 0;
        int numberOfBreakablePackages = 0;
        TreeMap<LocalDate, TreeMap<Integer, ArrayList<Shipment>>> archive = this.postOffice.getArchive();
        for (TreeMap<Integer, ArrayList<Shipment>> inner : archive.values()) {
            for (ArrayList<Shipment> value : inner.values()) {
                for (Shipment shipment : value) {
                    if (shipment.getName().contains("Package")) {
                        if (shipment.isBreakable()) {
                            numberOfBreakablePackages++;
                        }
                        numberOfPackages++;
                    }
                }
            }
        }
        double percentage = 0;
        if (numberOfBreakablePackages != 0) {
            percentage = (numberOfBreakablePackages * 100) / numberOfPackages;
        }
        System.out.println("Number of packages: "+numberOfPackages);
        System.out.println("Number of breakable packages: "+numberOfBreakablePackages);
        System.out.println("Breakable packages percentage: "+percentage);
    }

    public void printMailManInfo(ArrayList<MailMan> mailmen){
        mailmen.sort((o1,o2)-> o1.getNumberOfDeliveries() - o2.getNumberOfDeliveries());
        for (MailMan mailman : mailmen) {
            System.out.println(mailman.getFirst_name()+" : "+mailman.getNumberOfDeliveries()+" deliveries");
        }
    }

}
