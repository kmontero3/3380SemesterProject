import java.sql.*;

public class Main {
    // Connection parameters for MySQL database
    private static final String URL = "jdbc:mysql://localhost:3306/because";
    private static final String USER = "root";
    private static final String PASSWORD = "PhiOrDie!2251";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            // Establish connection to database
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();

            // Upload example names
            String[] childNames = {"Alice", "Bob", "Charlie", "Diana", "Emma", "Frank", "Grace", "Henry", "Ivy", "Jack"};
            for (String name : childNames) {
                String sql = "INSERT INTO child (parent_id, name, coins_available) VALUES (1, '" + name + "', 0)";
                statement.executeUpdate(sql);
            }

            // Upload example chores
            String[] choreNames = {"Wash dishes", "Vacuum floor", "Take out trash", "Feed pets", "Do laundry"};
            for (String name : choreNames) {
                String sql = "INSERT INTO chore_list (name, reward_amount, completed, reoccurs) VALUES ('" + name + "', 10, 0, 'Daily')";
                statement.executeUpdate(sql);
            }

            // Upload example rewards
            String[] rewardNames = {"Toy car", "Stuffed animal", "Book", "Video game", "Candy"};
            int[] rewardCoinAmounts = {20, 30, 15, 50, 5};
            for (int i = 0; i < rewardNames.length; i++) {
                String sql = "INSERT INTO reward (name, coin_amount) VALUES ('" + rewardNames[i] + "', " + rewardCoinAmounts[i] + ")";
                statement.executeUpdate(sql);
            }

            System.out.println("Data added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close statement and connection
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
