package post_office_task.post_office;

import post_office_task.shipment.Letter;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class MailBox {

    private CopyOnWriteArrayList<Letter> letters;
    private static int number = 1;
    private String name;

    public MailBox() {
        this.name = "Mailbox "+number++;
        this.letters = new CopyOnWriteArrayList<Letter>();
    }

    public void addLetter(Letter letter){
        this.letters.add(letter);
    }

    public boolean isEmpty(){
        boolean result = false;
        if(this.letters.size() == 0){
            result = true;
        }
        return result;
    }

    public CopyOnWriteArrayList<Letter> getLetters() {
        return letters;
    }

    public String getName() {
        return name;
    }

    public void removeLetter(Letter letter){
        this.letters.remove(letter);
    }
}
