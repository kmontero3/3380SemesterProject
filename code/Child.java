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
        achievements.remove(achievement);			FileWriter fileWriter = null;
        this.coinsAvailable += achievement.getExtraCoins();	
    }			try {

			File file = new File(CSV_FILE_PATH);
    public boolean hasAchievement(Achievement achievement) {				boolean exists = file.exists();
        return achievements.contains(achievement);	
    }				// If file doesn't exist, create it and write header

			if (!exists) {
    public void completeAchievement(Achievement achievement) {					file.createNewFile();
        if (hasAchievement(achievement)) {					fileWriter = new FileWriter(CSV_FILE_PATH);
            achievement.setCompleted(true);					fileWriter.append(CSV_HEADER);
        }					fileWriter.append(NEW_LINE_SEPARATOR);
    }				} else {

				// If file exists, append to it
    public void completedChore(Child child, Chore chore) {					fileWriter = new FileWriter(CSV_FILE_PATH, false);
        int updatedCoins = chore.getRewardAmount() + child.coinsAvailable;					fileWriter.append(CSV_HEADER);
        child.updateTotalChoresCompleted();					fileWriter.append(NEW_LINE_SEPARATOR);
        updateCoins(updatedCoins);				}
        removeChore(chore);	
    }				// Write child data to CSV file

			for (Child child : Main.children) {
    public void addReward(Child child, Reward reward) {					fileWriter.append(child.id.toString());
        int updatedCoins = child.coinsAvailable - reward.getCoinAmount();					fileWriter.append(CSV_DELIMITER);
        updateCoins(updatedCoins);					fileWriter.append(child.name);
        rewards.add(reward);					fileWriter.append(CSV_DELIMITER);
    }					fileWriter.append(Integer.toString(child.coinsAvailable));
    public void removeReward(Reward reward) {					fileWriter.append(CSV_DELIMITER);
        rewards.remove(reward);					fileWriter.append(child.parent.getId().toString());
    }					fileWriter.append(CSV_DELIMITER);
    public boolean hasReward(Reward reward) {					if (child.rewards == null || child.rewards.isEmpty()) {
        for (Reward r : rewards) {						fileWriter.append("[]");
            if (r.getName().equals(reward.getName())) {					} else {
                return true;						String rewardIds = child.rewards.stream().map(reward -> reward.getId().toString())
            }					            .collect(Collectors.joining(","));
        }					    fileWriter.append(rewardIds);
        return false;					}
    }					fileWriter.append(CSV_DELIMITER);

				if (child.chores == null || child.chores.isEmpty()) {

					fileWriter.append("[]");
    public static void saveChildrentoCSV() {					} else {
        FileWriter fileWriter = null;						String choreIds = child.chores.stream().map(chore -> chore.getId().toString())

				            .collect(Collectors.joining(","));
        try {					    fileWriter.append(choreIds);
            File file = new File(CSV_FILE_PATH);					}
            boolean exists = file.exists();					fileWriter.append(NEW_LINE_SEPARATOR);

			}
            // If file doesn't exist, create it and write header	
            if (!exists) {			} catch (Exception e) {
                file.createNewFile();				System.out.println("Error while updating CSV file: " + e);
                fileWriter = new FileWriter(CSV_FILE_PATH);			} finally {
                fileWriter.append(CSV_HEADER);				try {
                fileWriter.append(NEW_LINE_SEPARATOR);					fileWriter.flush();
            } else {					fileWriter.close();
                // If file exists, append to it				} catch (IOException e) {
                fileWriter = new FileWriter(CSV_FILE_PATH, true);					System.out.println("Error while flushing/closing fileWriter: " + e);
            }				}

		}
            // Write child data to CSV file		}
            for (Child child : Main.children) {	
            fileWriter.append(child.id.toString());		public static void loadChildrenFromCSV() {
            fileWriter.append(CSV_DELIMITER);			try {
            fileWriter.append(child.name);				File file = new File(CSV_FILE_PATH);
            fileWriter.append(CSV_DELIMITER);				if (!file.exists()) {
            fileWriter.append(Integer.toString(child.coinsAvailable));					System.out.println("Chores CSV file does not exist");
            fileWriter.append(CSV_DELIMITER);					saveChildrentoCSV();
            fileWriter.append(child.parent.getId().toString());				}
            fileWriter.append(CSV_DELIMITER);				BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE_PATH));
             fileWriter.append(String.join(",", child.rewards.stream().map(Reward::getName).collect(Collectors.toList())));				Main.children = new ArrayList<Child>();
            fileWriter.append(CSV_DELIMITER);				String line = reader.readLine(); // skip the header line
            fileWriter.append(String.join(",", child.chores.stream().map(Chore::getName).collect(Collectors.toList())));				while ((line = reader.readLine()) != null) {
            fileWriter.append(NEW_LINE_SEPARATOR);					String[] fields = line.split(CSV_DELIMITER);
            }					UUID id = UUID.fromString(fields[0]);

				String name = fields[1];
        } catch (Exception e) {					int coinsAvailable = Integer.parseInt(fields[2]);
            System.out.println("Error while updating CSV file: " + e);					UUID parentId = UUID.fromString(fields[3]);
        } finally {					String[] rewardStrings = fields[4].trim().isEmpty() ? new String[] {} : fields[4].split(",");
            try {					ArrayList<Reward> rewards = new ArrayList<>();
                fileWriter.flush();					for (String rewardString : rewardStrings) {
                fileWriter.close();						if (!rewardString.equals("[]")) {
            } catch (IOException e) {							UUID rewardId = UUID.fromString(rewardString);
                System.out.println("Error while flushing/closing fileWriter: " + e);							if (Main.getRewardByID(rewardId, Main.rewards) != null) {
            }								rewards.add(Main.getRewardByID(rewardId, Main.rewards));
        }							}
    }						}
public static void loadChildrenFromCSV() {					}
    BufferedReader reader = null;					String[] choreStrings = fields[5].trim().isEmpty() ? new String[] {} : fields[5].split(",");
    try {					//System.out.println(Arrays.toString(choreStrings));
        File file = new File(CSV_FILE_PATH);					ArrayList<Chore> chores = new ArrayList<>();
            if (!file.exists()) {					for (String choreString : choreStrings) {
                System.out.println("Chores CSV file does not exist");						System.out.println(choreString);
                return;						if (!choreString.equals("[]")) {
            }							UUID choreId = UUID.fromString(choreString);
        reader = new BufferedReader(new FileReader(CSV_FILE_PATH));							System.out.println(choreId.toString());
        Main.children = new ArrayList<Child>();							if (Main.getChoreByID(choreId, Main.chores) != null) {
        String line = reader.readLine(); // skip the header line								System.out.println("------------------------------");
        while ((line = reader.readLine()) != null) {								chores.add(Main.getChoreByID(choreId, Main.chores));
            String[] fields = line.split(CSV_DELIMITER);							}
            UUID id = UUID.fromString(fields[0]);						}
            String name = fields[1];					}
            int coinsAvailable = Integer.parseInt(fields[2]);					Parent parent = findParentById(parentId);
            UUID parentId = UUID.fromString(fields[3]);					Child child = new Child(id, name, coinsAvailable, parent, rewards, chores);
            String[] rewardStrings = fields[4].split(",");					// find the parent object and add the child to their list of children
            ArrayList<Reward> rewards = new ArrayList<>();	
            for (String rewardString : rewardStrings) {					// add the child to the Main.children list
                if(Main.getRewardByName(rewardString ,Main.rewards) != null){					Main.children.add(child);
                    rewards.add(Main.getRewardByName( rewardString ,Main.rewards));				}
                }				reader.close();
            }			} catch (IOException e) {
            String[] choreStrings = fields[5].split(",");				System.out.println("Error while loading CSV file: " + e);
            ArrayList<Chore> chores = new ArrayList<>();			} finally {
            for (String choreString : choreStrings) {			}
                if(Main.getChoreByName( choreString ,Main.chores) != null){		}
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


// helper method to find the parent object by its ID	// helper method to find the parent object by its ID
private static Parent findParentById(UUID id) {		private static Parent findParentById(UUID id) {
    for (Parent parent : Main.parents) {			for (Parent parent : Main.parents) {
        if (parent.getId().equals(id)) {				if (parent.getId().equals(id)) {
            return parent;					return parent;
        }				}
    }			}
    return null; // parent not found			return null; // parent not found
}		}


}	}
