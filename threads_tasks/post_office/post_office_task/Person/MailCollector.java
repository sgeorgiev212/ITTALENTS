package post_office_task.Person;

import post_office_task.post_office.PostOffice;

public class MailCollector extends MailMan implements Runnable{


    public MailCollector(String first_name, String last_name,PostOffice postOffice) {
        super(first_name, last_name, 0,postOffice);

    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.currentThread().sleep(5000);
                this.postOffice.collectLetters(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
