import java.sql.*;

public class ChoreTrackerDB {
    private Connection conn;
    
    public ChoreTrackerDB(String url, String user, String password) throws SQLException {
        conn = DriverManager.getConnection("127.0.0.1", "root", "");
    }
    public Child getChild(int id) throws SQLException {
        String query = "SELECT name, coins_available FROM child WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String name = rs.getString("name");
            int coinsAvailable = rs.getInt("coins_available");
            return new Child(id, name, coinsAvailable);
        } else {
            return null;
        }
    }

    public Chore getChore(int id) throws SQLException {
        String query = "SELECT chore_name, reward_amount, completed, recurrence FROM chore_list WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String chore_name = rs.getString("chore_name");
            int rewardAmount = rs.getInt("reward_amount");
            boolean completed = rs.getBoolean("completed");
            int recurrence = rs.getInt("recurrence");
            return new Chore(id, chore_name, rewardAmount, completed, recurrence);
        } else {
            return null;
        }
    }

    public Reward getReward(int id) throws SQLException {
        String query = "SELECT item, coin_amount FROM reward WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String item = rs.getString("item");
            int coinAmount = rs.getInt("coin_amount");
            return new Reward(id, item, coinAmount);
        } else {
            return null;
        }
    }

    public void updateChildCoins(int id, int coins) throws SQLException {
        String query = "UPDATE child SET coins_available = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, coins);
        ps.setInt(2, id);
        ps.executeUpdate();
    }

    public void updateChoreCompleted(int id, boolean completed) throws SQLException {
        String query = "UPDATE chore_list SET completed = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setBoolean(1, completed);
        ps.setInt(2, id);
        ps.executeUpdate();
    }

    public void updateRewardCoinAmount(int id, int coinAmount) throws SQLException {
        String query = "UPDATE reward SET coin_amount = ? WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, coinAmount);
        ps.setInt(2, id);
        ps.executeUpdate();
    }

    public void close() throws SQLException {
        conn.close();
    }

    public void addChild(Child child) throws SQLException {
        String query = "INSERT INTO child (name, coins_available) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, child.getName());
        ps.setInt(2, child.getCoinsAvailable());
        ps.executeUpdate();
    }

    public void addChore(Chore chore) throws SQLException {
        String query = "INSERT INTO chore_list (chore_name, reward_amount, completed, recurrence) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, chore.chore_name);
        ps.setInt(2, chore.rewardAmount);
        ps.setBoolean(3, chore.isComplete);
        ps.setInt(4, chore.recurrenceFrequency);
    }
}