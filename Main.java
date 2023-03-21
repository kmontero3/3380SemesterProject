import java.sql.*;
import java.util.Random;


//when the main code is implemented, it the new data will be uploaded to the database, which will also be 
//connected to our app. Whenever chores are completed, the Child's coins available will increase by the 
//chore reward amount and whenever a reward is bought, the child's coins will decrease by the amount of 
//the coin reward. This implementation is not possible without being connected to the database.
public class Main {
    // Connection parameters for MySQL database
    private static final String URL = "jdbc:mysql://localhost:3306/because";
    private static final String USER = "root";
    private static final String PASSWORD = "";


    public static void main(String[] args) throws SQLException {
        //theUI.showmainpage();
        Connection connection = null;
        Statement statement = null;

        
        
            // Establish connection to database
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();

            // Upload example names
            
            String[] childNames = {"Alice", "Bob", "Charlie", "Diana", "Emma", "Frank", "Grace", "Henry", "Ivy", "Jack"};
            String[] parentNames = {"Kevin","Kevin","Kevin","Alston","Lauren","Jesse","Emory","Emory","Tzuriel","Alston"};
            for (int i=0; i<10; i++) {
                // String sql = "INSERT INTO child (parent_id, name, coins_available) VALUES (1, '" + name + "', 0)";
                // statement.executeUpdate(sql);
                Parent parent = new Parent(i, parentNames[i],"abcd");
                Child child = new Child(i, childNames[i], 0, parent);
                System.out.println(child.getId() + " " + child.getName() + " " + child.getCoinsAvailable() + " " + child.getParent().getName());
            }
    
            // Upload example chores
            String[] choreNames = {"Wash dishes", "Vacuum floor", "Take out trash", "Feed pets", "Do laundry"};
            for (String name : choreNames) {
                // String sql = "INSERT INTO chore_list (name, reward_amount, completed, reoccurs) VALUES ('" + name + "', 10, 0, 'Daily')";
                // statement.executeUpdate(sql);
                Chore chore = new Chore((int)(Math.random()*100+1), name, (int)(Math.random()*100+1), false, 1);
                System.out.println(chore.chore_name + " " + chore.rewardAmount);
            }

            // Upload example rewards
            String[] rewardNames = {"Toy car", "Stuffed animal", "Book", "Video game", "Candy"};
            int[] rewardCoinAmounts = {20, 30, 15, 50, 5};
            for (int i = 0; i < rewardNames.length; i++) {
                // String sql = "INSERT INTO reward (name, coin_amount) VALUES ('" + rewardNames[i] + "', " + rewardCoinAmounts[i] + ")";
                // statement.executeUpdate(sql);
                Reward reward = new Reward(i, rewardNames[i], rewardCoinAmounts[i]);
                System.out.println(reward.getName() +" "+ reward.getCoinAmount());
            }

        //     System.out.println("Data added successfully!");

        // } catch (SQLException e) {
        //     e.printStackTrace();
        // } finally {
        //     // Close statement and connection
        //     try {
        //         statement.close();
        //         connection.close();
        //     } catch (SQLException e) {
        //         e.printStackTrace();
        //     }
        
    }
}
