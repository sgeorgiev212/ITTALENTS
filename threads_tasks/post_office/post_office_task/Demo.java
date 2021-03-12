package post_office_task;


import post_office_task.Person.Citizen;
import post_office_task.Person.MailCollector;
import post_office_task.Person.MailMan;
import post_office_task.file_operations.FileCreator;
import post_office_task.post_office.PostOffice;
import post_office_task.statistics.Statistics;
import post_office_task.util.Util;

import java.time.LocalDate;
import java.util.ArrayList;

public class Demo {

    public static void main(String[] args) {
        PostOffice postOffice = new PostOffice();

        Citizen citizen1 = new Citizen(Util.getRandomName(), Util.getRandomLastName(), Util.getRandomAddress(), postOffice);
        Citizen citizen2 = new Citizen(Util.getRandomName(), Util.getRandomLastName(), Util.getRandomAddress(), postOffice);
        Citizen citizen3 = new Citizen(Util.getRandomName(), Util.getRandomLastName(), Util.getRandomAddress(), postOffice);
        Citizen citizen4 = new Citizen(Util.getRandomName(), Util.getRandomLastName(), Util.getRandomAddress(), postOffice);
        Citizen citizen5 = new Citizen(Util.getRandomName(), Util.getRandomLastName(), Util.getRandomAddress(), postOffice);
        Citizen citizen6 = new Citizen(Util.getRandomName(), Util.getRandomLastName(), Util.getRandomAddress(), postOffice);

        MailCollector collector1 = new MailCollector("Collector 1", "", postOffice);
        MailCollector collector2 = new MailCollector("Collector 2", "", postOffice);

        MailMan mailMan1 = new MailMan("Mail man 1", "", 3, postOffice);
        MailMan mailMan2 = new MailMan("Mail man 2", "", 4, postOffice);

        Thread citizenThread = new Thread(citizen1);
        Thread citizenThread2 = new Thread(citizen2);
        Thread citizenThread3 = new Thread(citizen3);
        Thread citizenThread4 = new Thread(citizen4);
        Thread citizenThread5 = new Thread(citizen5);
        Thread citizenThread6 = new Thread(citizen6);

        Thread collectorThread1 = new Thread(collector1);
        Thread collectorThread2 = new Thread(collector2);

        Thread mailManThread1 = new Thread(mailMan1);
        Thread mailManThread2 = new Thread(mailMan2);

        citizenThread.start();
        citizenThread2.start();
        citizenThread3.start();
        citizenThread4.start();
        citizenThread5.start();
        citizenThread6.start();

        collectorThread1.start();
        collectorThread2.start();

        mailManThread1.start();
        mailManThread2.start();

        ArrayList<MailMan> mailmen = new ArrayList<>();
        mailmen.add(mailMan1);
        mailmen.add(mailMan2);
        Statistics statistics = new Statistics(postOffice);
        Thread statsThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.currentThread().sleep(10000);
                    System.out.println("================================================");
                    statistics.printShipmentsByDate(LocalDate.now());
                    statistics.printLettersPercentage(LocalDate.now());
                    statistics.breakablePacksPercentage();
                    statistics.printMailManInfo(mailmen);
                    System.out.println("================================================");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        statsThread.start();

        Thread fileThread = new Thread(() -> {
            while (true) {
                FileCreator.addShipmentsToFile(postOffice, LocalDate.now());
            }
        });
        fileThread.start();

        Thread database = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(15000);
                        DatabaseOperations operations = new DatabaseOperations();
                        operations.insertToInfoDatabase(postOffice);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        database.start();

    }
}
