package gastation_task.databaseOperations;

import gastation_task.ClientInfo;
import gastation_task.fileOperations.FileOperations;


import javax.xml.transform.Result;
import java.sql.*;
import java.time.LocalDateTime;

public class DatabaseOperations {

    private static Connection conn;

    public static void insertToDatabase(ClientInfo info){
        String sql = "insert into station_loadings(kolonka_id,fuel_type,fuel_quantity,loading_time) values(?,?,?,?)";
        conn = DataBaseConnection.getInstance().getConnection();
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1,info.getColumn().getId());
            statement.setString(2,info.getFuelType());
            statement.setDouble(3,info.getFuelAmount());
            Timestamp time = Timestamp.valueOf(info.getTime());
            statement.setTimestamp(4,time);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void printColumnInfo(int columnNumber){
        StringBuilder builder = new StringBuilder();
        builder.append("fuel column "+columnNumber+"\n");
        System.out.println("Fuel Column "+columnNumber+":");
        String sql = "select fuel_type,fuel_quantity,loading_time from station_loadings where kolonka_id = ? order by loading_time";
        conn = DataBaseConnection.getInstance().getConnection();
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1,columnNumber);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                String fuelType = rs.getString("fuel_type");
                double quantity = rs.getDouble("fuel_quantity");
                LocalDateTime time  = rs.getTimestamp("loading_time").toLocalDateTime();
                System.out.println(fuelType+","+quantity+","+time);
                builder.append(fuelType+","+quantity+","+time+"\n");
            }
            String info = builder.toString();
            FileOperations.addInfoToFIle(info);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void printNumberOfCars(){
        String sql = "select kolonka_id,count(*) from station_loadings\n" +
                "group by kolonka_id";
        conn = DataBaseConnection.getInstance().getConnection();
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int colNum = rs.getInt("kolonka_id");
                int numberOfCars = rs.getInt("count(*)");
                System.out.println("Column "+colNum+" : "+numberOfCars+" cars");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void printFuelInfo(){
        String sql = "select fuel_type,sum(fuel_quantity) from station_loadings group by fuel_type;";
        conn = DataBaseConnection.getInstance().getConnection();
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String fuelType = rs.getString("fuel_type");
                int amount = rs.getInt("sum(fuel_quantity)");
                System.out.println(fuelType+" : "+amount+" liters");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void printEarnedMoney(){
        String sql1 = "select fuel_type,sum(fuel_quantity) from station_loadings where fuel_type = 'gas'";
        String sql2 = "select fuel_type,sum(fuel_quantity) from station_loadings where fuel_type = 'diesel'";
        String sql3 = "select fuel_type,sum(fuel_quantity) from station_loadings where fuel_type = 'petrol'";
        conn = DataBaseConnection.getInstance().getConnection();
        double moneyFromGas = 0;
        double moneyFromDiesel = 0;
        double moneyFromPetrol = 0;
        try(PreparedStatement ps1 = conn.prepareStatement(sql1);
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            PreparedStatement ps3 = conn.prepareStatement(sql3)){
            ResultSet rs1 = ps1.executeQuery();
            if(rs1.next()){
                double amount = rs1.getDouble("sum(fuel_quantity)");
                amount*=1.60;
                moneyFromGas = amount;
                System.out.println("Money earned from gas: "+amount);
            }
            ResultSet rs2 = ps2.executeQuery();
            if(rs2.next()){
                double amount = rs1.getDouble("sum(fuel_quantity)");
                amount*=2.40;
                moneyFromDiesel = amount;
                System.out.println("Money earned from diesel: "+amount);
            }
            ResultSet rs3 = ps3.executeQuery();
            if(rs3.next()){
                double amount = rs1.getDouble("sum(fuel_quantity)");
                amount*=2.00;
                moneyFromPetrol = amount;
                System.out.println("Money earned from petrol: "+amount);
            }
            double sum = moneyFromDiesel+moneyFromGas+moneyFromPetrol;
            System.out.println("Money earned from all fuels: "+sum);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
