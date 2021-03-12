package post_office_task.Person;

import post_office_task.post_office.PostOffice;

public class MailMan extends Person implements Runnable{

    private int yearsOfExperience;
    protected PostOffice postOffice;
    private int numberOfDeliveries;

    public MailMan(String first_name, String last_name,int yearsOfExperience,PostOffice postOffice) {
        super(first_name, last_name);
        this.yearsOfExperience = yearsOfExperience;
        this.postOffice = postOffice;
        this.numberOfDeliveries = 0;
    }

    @Override
    public void run() {
      while (true){
          this.postOffice.deliverShipments(this);
      }
    }

    public void increaseNumberOfDeliveries(){
        this.numberOfDeliveries++;
    }

    public int getNumberOfDeliveries() {
        return numberOfDeliveries;
    }
}
