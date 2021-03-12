package post_office_task;

import post_office_task.post_office.PostOffice;
import post_office_task.shipment.Shipment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Queue;

public class DatabaseOperations {

    private static Connection conn;

    public DatabaseOperations(){
        conn = DatabaseConnection.getInstance().getConnection();
    }

    public  void insertToInfoDatabase(PostOffice postOffice){
        String sql = "insert into shipments(type,sender_name,sender_lastName,receiver_name,receiver_lastName) values (?,?,?,?,?)";
        Queue<Shipment> storage = postOffice.getStorage();
        for (Shipment shipment : storage) {
            try(PreparedStatement ps = conn.prepareStatement(sql)){
                ps.setString(1,shipment.getType());
                ps.setString(2,shipment.getSender().getFirst_name());
                ps.setString(3,shipment.getSender().getLast_name());
                ps.setString(4,shipment.getReceiver().getLast_name());
                ps.setString(5,shipment.getReceiver().getLast_name());
                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        }

}
