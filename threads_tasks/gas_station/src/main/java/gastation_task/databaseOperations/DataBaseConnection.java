package gastation_task.databaseOperations;

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {

    private static DataBaseConnection instance;
    private Connection conn;
    private DatabaseInfo info;

    private DataBaseConnection(){
        this.loadDatabaseInfo();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(info.getUrl(),info.getUsername(), info.getPassword());
        } catch (SQLException e) {
            System.out.println("Unable to connect to database! "+e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DataBaseConnection getInstance(){
        if(instance == null){
            instance = new DataBaseConnection();
        }
        return instance;
    }

    public Connection getConnection(){
        return conn;
    }

    public void loadDatabaseInfo(){
      Gson gson = new Gson();
        try {
            DatabaseInfo database = gson.fromJson(new FileReader("database.json"),DatabaseInfo.class);
            this.info = database;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
