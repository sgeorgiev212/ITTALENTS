package ships_task.DataBase;

import ships_task.Files.FileCreator;
import ships_task.PackageInfo;

import java.io.File;
import java.sql.*;
import java.time.LocalDateTime;

public class DatabaseOperations {

    private static int reportNumber = 1;

    public static void addInfoToDB(PackageInfo info){
        String sql = "insert into port_shipments(boat_name,dock_id,crane_id,unloading_time,package_id) values(?,?,?,?,?)";
        Connection connection = DatabaseConnection.getInstance().getConnection();
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1,info.getShipName());
            statement.setInt(2,info.getDockId());
            statement.setInt(3,info.getCraneId());
            Timestamp timestamp = Timestamp.valueOf(info.getUnloading_time());
            statement.setTimestamp(4,timestamp);
            statement.setInt(5,info.getPackageId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static String printDockStats(int dockId){
        String sql = "select * from port_shipments where dock_id = ?";
        Connection connection = DatabaseConnection.getInstance().getConnection();
        StringBuilder builder = new StringBuilder();
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,dockId);
            ResultSet rs = statement.executeQuery();
            System.out.println("Dock "+dockId);
            builder.append("Dock "+dockId+"\n");
            if(rs.next()) {
                while (rs.next()) {
                    int packageId = rs.getInt("package_id");
                    String shipName = rs.getString("boat_name");
                    int craneId = rs.getInt("crane_id");
                    LocalDateTime time = rs.getTimestamp("unloading_time").toLocalDateTime();
                    System.out.println("Package " + packageId + ", Ship " + shipName + ", Crane " + craneId + ", " + time);
                    builder.append("Package " + packageId + ", Ship " + shipName + ", Crane " + craneId + ", " + time+"\n");
                }
            }else{
                System.out.println("NO ships have arrived at dock "+dockId);
                builder.append("no ships arrived");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return builder.toString();
    }

   public static void printDockInfo(){
        String sql = "select dock_id,count(*) as ships from port_shipments group by dock_id";
        Connection connection = DatabaseConnection.getInstance().getConnection();
        StringBuilder builder = new StringBuilder();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int dockId = rs.getInt("dock_id");
                int numberOfShips = rs.getInt("ships");
                System.out.println("Dock "+dockId+" : "+numberOfShips+" ships");
                builder.append("Dock "+dockId+" : "+numberOfShips+" ships \n");
            }
            FileCreator.addInfoToFile(builder.toString(),reportNumber++,LocalDateTime.now());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
   }

    public static void printCraneInfo(){
        String sql = "select crane_id,count(*) as packages from port_shipments group by crane_id;";
        Connection connection = DatabaseConnection.getInstance().getConnection();
        StringBuilder builder = new StringBuilder();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int dockId = rs.getInt("crane_id");
                int numberOfShips = rs.getInt("packages");
                System.out.println("Crane "+dockId+" : "+numberOfShips+" packages");
                builder.append("Crane "+dockId+" : "+numberOfShips+" packages \n");
            }
            FileCreator.addInfoToFile(builder.toString(),reportNumber++,LocalDateTime.now());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void printMostLoadedShip(){
        String sql = "select boat_name,count(*) as packages from port_shipments group by boat_name order by packages desc limit 1;";
        Connection connection = DatabaseConnection.getInstance().getConnection();
        StringBuilder builder = new StringBuilder();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                String shipName = rs.getString("boat_name");
                int numberOfPackages = rs.getInt("packages");
                System.out.println("Ship with most packages:");
                System.out.println("Ship "+shipName+" : "+numberOfPackages+" packages");
                builder.append("Ship with most packages:\n");
                builder.append("Ship "+shipName+" : "+numberOfPackages+" packages");
            }
            FileCreator.addInfoToFile(builder.toString(),reportNumber++,LocalDateTime.now());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void incrementReportNumber(){
        reportNumber++;
    }

    public static int getReportNumber() {
        return reportNumber;
    }
}
