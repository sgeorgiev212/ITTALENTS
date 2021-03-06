import java.sql.*;
import java.time.LocalDateTime;

public class Database {

    private String url = "jdbc:mysql://localhost:3306/ships";
    private String user = "root";
    private String password = "root";

    public Database() {
    }

    public void addPackageToDatabase(Statistics statistics){

        String boatName = statistics.getShipName();
        int dockId = statistics.getDockNumber();
        int craneId = statistics.getCraneId();
        LocalDateTime time = statistics.getTime();
        int packageId = statistics.getPackageNumber();
        Timestamp timestamp = Timestamp.valueOf(time);

        String sql = "insert into port_shipments(boat_name,dock_id,crane_id,unloading_time,package_id) "+
                "values('"+boatName+"','"+dockId+"','"+craneId+"','"+timestamp+"','"+packageId+"')";
        try {
            Statement statement = this.getConnection().createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void printDockInfo(int dockNumber){
        try {
            Statement statement = this.getConnection().createStatement();
            String sql = "select package_id,boat_name,crane_id,unloading_time from port_shipments where dock_id ="+dockNumber+"";
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("Dock "+dockNumber);
            if(!rs.next()){
                System.out.println("No ships arrived at dock "+dockNumber);
            }
            while (rs.next()){
                System.out.println("Package "+rs.getInt("package_id")+" Ship: "+rs.getString("boat_name")+" Crane: "+rs.getInt("crane_id")+" Date: "+rs.getDate("unloading_time"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void printNumberOfShips(int dockNumber){
        try {
            Statement statement = this.getConnection().createStatement();
            String sql = "select count(*) from port_shipments where dock_id = "+dockNumber+"";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                System.out.println("Dock "+dockNumber+" :  "+rs.getInt("count(*)")+" ships");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void printCraneInfo(int craneId){
        try {
            Statement statement = this.getConnection().createStatement();
            String sql = "select count(*) from port_shipments where crane_id = '"+craneId+"'";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                System.out.println("Crane "+craneId+" :  "+rs.getInt("count(*)")+" packages");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void printMostLoadedShip(){
        try {
            Statement statement = this.getConnection().createStatement();
            String sql = "select count(package_id),boat_name from port_shipments group by  boat_name order by count(package_id) desc limit 1";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                System.out.println("Ship with most number of packages: "+rs.getString("boat_name")+" number of packages: "+rs.getInt("count(package_id)"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    private Connection getConnection(){
        Connection conn = null;
        try {
            Connection connection = DriverManager.getConnection(url,user,password);
            conn = connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  conn;
    }

}
