package grill_task.database;

import grill_task.grillShop.GrillShop;

import java.sql.*;
import java.time.LocalDateTime;

public class DatabaseOperations {

   public static void insertInfoToDB(int shopId, int breadType, int meatType, int garnishType, LocalDateTime time){
       Connection connection = DatabaseConnection.getInstance().getConnection();
       String sql = "insert into sales(shop_id,bread_type_id,meat_type_id,garnish_type_id,date_created) values (?,?,?,?,?)";
       try(PreparedStatement ps = connection.prepareStatement(sql)){
          ps.setInt(1,shopId);
          ps.setInt(2,breadType);
          ps.setInt(3,meatType);
          ps.setInt(4,garnishType);
          Timestamp timestamp = Timestamp.valueOf(time);
          ps.setTimestamp(5,timestamp);
          ps.executeUpdate();
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }

   }

   public static void printNumberOfOrders(){
       Connection connection = DatabaseConnection.getInstance().getConnection();
       String sql = "select count(*) from sales where shop_id = 27";
       try(PreparedStatement ps = connection.prepareStatement(sql)){
           ResultSet rs = ps.executeQuery();
           while (rs.next()){
               System.out.println("Amount of orders made "+rs.getInt("count(*)"));
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
   }

   public static void printMostSelledMeat(){
       Connection connection = DatabaseConnection.getInstance().getConnection();
       String sql = "select name,count(*) as number from sales s join meat_types m on s.meat_type_id = m.id group by s.meat_type_id order by number desc limit 1;";
       try(PreparedStatement ps = connection.prepareStatement(sql)){
           ResultSet rs = ps.executeQuery();
           while (rs.next()){
               System.out.println("Most selled meat is: "+rs.getString("name")+" "+rs.getInt("number")+" numbers");
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
   }

   public static void printMoneyFromOrders(GrillShop grillShop){
       System.out.println("money earned from orders: "+grillShop.getMoney());
   }

   public static void printShopsInfo(){
       Connection connection = DatabaseConnection.getInstance().getConnection();
       String sql = "select s.name,count(*) as orders from shops s join sales sal on s.id = sal.shop_id group by sal.shop_id;";
       try(PreparedStatement ps = connection.prepareStatement(sql)){
           ResultSet rs = ps.executeQuery();
           while (rs.next()){
               System.out.println("Shop "+rs.getString("name") +" number of sales: "+rs.getInt("orders"));
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
   }

   public static void printShopWithMostWholeBread(){
       Connection connection = DatabaseConnection.getInstance().getConnection();
       String sql = "select s.name,count(bread_type_id) from shops s join sales sa on s.id = sa.shop_id join bread_types b on sa.bread_type_id = b.id group by b.id having b.id = 2;";
       try(PreparedStatement ps = connection.prepareStatement(sql)){
           ResultSet rs = ps.executeQuery();
           while (rs.next()){
               System.out.println(rs.getString("name")+" number of whole breads: "+rs.getInt("count(bread_type_id)"));
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
   }


   public static void printLeastBoughtGarnish(){
       Connection connection = DatabaseConnection.getInstance().getConnection();
       String sql = "select g.name, count(*) from garnish_types g join sales s on g.id = s.garnish_type_id group by s.garnish_type_id order by count(*) desc limit 1;";
       try(PreparedStatement ps = connection.prepareStatement(sql)){
           ResultSet rs = ps.executeQuery();
           while (rs.next()){
               System.out.println(rs.getString("name")+ " "+rs.getInt("count(*)"));
           }
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
   }
}
