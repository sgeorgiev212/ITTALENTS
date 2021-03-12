package post_office_task.file_operations;

import post_office_task.post_office.PostOffice;
import post_office_task.shipment.Shipment;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Queue;

public class FileCreator {

    public static void addShipmentsToFile(PostOffice postOffice, LocalDate date){
        StringBuilder builder = new StringBuilder();
        Queue<Shipment> storage = postOffice.getStorage();
        for (Shipment shipment : storage) {
            builder.append(shipment.getType()+"\n");
            builder.append("Sender: "+shipment.getSender().getFirst_name()+" "+shipment.getSender().getLast_name()+"\n");
            builder.append("Receiver: "+shipment.getReceiver().getFirst_name()+" "+shipment.getReceiver().getLast_name()+"\n");
        }
        File f = new File(date+" archive.txt");
        try {
            f.createNewFile();
            PrintStream writer = new PrintStream(f);
            writer.println(builder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
