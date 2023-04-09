package code;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.stream.Collectors;

public class Child {
    private UUID id;
    private String name;
    private int coinsAvailable;
    private Parent parent;
    private ArrayList<Reward> rewards;
    private ArrayList<Chore> chores;
    private ArrayList<Achievement> achievements;

    private int totalChoresAssigned = 0;
    private int totalChoresCompleted = 0;

    private static final String CSV_FILE_PATH = "childdata.csv";
    private static final String CSV_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String CSV_HEADER = "id,name,coinsAvailable,parent_id,rewards,chores";

    public Child(UUID id, String name, int coinsAvailable, Parent parent, ArrayList<Reward> rewards, ArrayList<Chore> arrayList) {
        this.parent = parent;
        this.id = id;
        this.name = name;
        this.coinsAvailable = coinsAvailable;
        this.rewards = rewards;
        this.chores = arrayList;
        this.achievements = new ArrayList<>();
        initializeAchievements();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCoinsAvailable() {
        return coinsAvailable;
    }

    public Parent getParent() {
        return parent;
    }

    public ArrayList<Reward> getRewards() {
        return this.rewards;
    }

    public ArrayList<Chore> getChores() {
        return this.chores;
    }

    public int getTotalChoresAssigned(){
        return this.totalChoresAssigned;
    }

    public int getTotalChoresCompleted(){
        return this.totalChoresCompleted;
    }

    public void updateTotalChoresAssigned(){
        this.totalChoresAssigned += 1;
    }

    public void updateTotalChoresCompleted(){
        this.totalChoresCompleted += 1;
    }

    public void addChore(Chore chore) {
        chores.add(chore);
    }

    public void removeChore(Chore chore) {
        chores.remove(chore);
    }

    public void updateCoins(int newCoins) {
        coinsAvailable = newCoins;
    }

    private void initializeAchievements() {
        achievements.add(new Achievement("Complete 5 chores", "Claim 10 extra coins", 10, 5));
        achievements.add(new Achievement("Complete 10 chores", "Claim 20 extra coins", 20, 10));
        achievements.add(new Achievement("Complete 20 chores", "Claim 50 extra coins", 50, 20));
    }
    
    public ArrayList<Achievement> getAchievements() {
        return achievements;
    }

    public void addAchievement(Achievement achievement) {
        achievements.add(achievement);
    }

    public void removeAchievement(Achievement achievement) {
        achievements.remove(achievement);
        this.coinsAvailable += achievement.getExtraCoins();
    }
    
    public boolean hasAchievement(Achievement achievement) {
        return achievements.contains(achievement);
    }

    public void completeAchievement(Achievement achievement) {
        if (hasAchievement(achievement)) {
            achievement.setCompleted(true);
        }
    }
    
    public void completedChore(Child child, Chore chore) {
        int updatedCoins = chore.getRewardAmount() + child.coinsAvailable;
        child.updateTotalChoresCompleted();
        updateCoins(updatedCoins);
        removeChore(chore);
    }
    
    public void addReward(Child child, Reward reward) {
        int updatedCoins = child.coinsAvailable - reward.getCoinAmount();
        updateCoins(updatedCoins);
        rewards.add(reward);
    }
    public void removeReward(Reward reward) {
        rewards.remove(reward);
    }
    public boolean hasReward(Reward reward) {
        for (Reward r : rewards) {
            if (r.getName().equals(reward.getName())) {
                return true;
            }
        }
        return false;
    }
    

    public static void saveChildrentoCSV() {
        FileWriter fileWriter = null;

        try {
            File file = new File(CSV_FILE_PATH);
            boolean exists = file.exists();

            // If file doesn't exist, create it and write header
            if (!exists) {
                file.createNewFile();
                fileWriter = new FileWriter(CSV_FILE_PATH);
                fileWriter.append(CSV_HEADER);
                fileWriter.append(NEW_LINE_SEPARATOR);
            } else {
                // If file exists, append to it
                fileWriter = new FileWriter(CSV_FILE_PATH, true);
            }

            // Write child data to CSV file
            for (Child child : Main.children) {
            fileWriter.append(child.id.toString());
            fileWriter.append(CSV_DELIMITER);
            fileWriter.append(child.name);
            fileWriter.append(CSV_DELIMITER);
            fileWriter.append(Integer.toString(child.coinsAvailable));
            fileWriter.append(CSV_DELIMITER);
            fileWriter.append(child.parent.getId().toString());
            fileWriter.append(CSV_DELIMITER);
             fileWriter.append(String.join(",", child.rewards.stream().map(Reward::getName).collect(Collectors.toList())));
            fileWriter.append(CSV_DELIMITER);
            fileWriter.append(String.join(",", child.chores.stream().map(Chore::getName).collect(Collectors.toList())));
            fileWriter.append(NEW_LINE_SEPARATOR);
            }

        } catch (Exception e) {
            System.out.println("Error while updating CSV file: " + e);
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter: " + e);
            }
        }
    }
public static void loadChildrenFromCSV() {
    BufferedReader reader = null;
    try {
        File file = new File(CSV_FILE_PATH);
            if (!file.exists()) {
                System.out.println("Chores CSV file does not exist");
                return;
            }
        reader = new BufferedReader(new FileReader(CSV_FILE_PATH));
        Main.children = new ArrayList<Child>();
        String line = reader.readLine(); // skip the header line
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(CSV_DELIMITER);
            UUID id = UUID.fromString(fields[0]);
            String name = fields[1];
            int coinsAvailable = Integer.parseInt(fields[2]);
            UUID parentId = UUID.fromString(fields[3]);
            String[] rewardStrings = fields[4].split(",");
            ArrayList<Reward> rewards = new ArrayList<>();
            for (String rewardString : rewardStrings) {
                if(Main.getRewardByName(rewardString ,Main.rewards) != null){
                    rewards.add(Main.getRewardByName( rewardString ,Main.rewards));
                }
            }
            String[] choreStrings = fields[5].split(",");
            ArrayList<Chore> chores = new ArrayList<>();
            for (String choreString : choreStrings) {
                if(Main.getChoreByName( choreString ,Main.chores) != null){
                    chores.add(Main.getChoreByName( choreString ,Main.chores));
                }
            }
            Parent parent = findParentById(parentId);
            Child child = new Child(id, name,coinsAvailable, parent, rewards, chores);
            // find the parent object and add the child to their list of children
            
            // add the child to the Main.children list
            Main.children.add(child);
        }
    } 
    catch (IOException e) {
        System.out.println("Error while loading CSV file: " + e);
    } finally {
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("Error while closing reader: " + e);
        }
    }
}

// helper method to find the parent object by its ID
private static Parent findParentById(UUID id) {
    for (Parent parent : Main.parents) {
        if (parent.getId().equals(id)) {
            return parent;
        }
    }
    return null; // parent not found
}

}