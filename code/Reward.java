package code;

import java.io.*;
import java.util.ArrayList;
import java.util.UUID;

public class Reward {
    private UUID id;
    private String name;
    private int coinAmount;

    // CSV constants
    /*private static final String CSV_HEADER = "ID,Name,Coin Amount";
    private static final String CSV_FILE_PATH = "rewarddata.csv";
    private static final String CSV_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";*/

    public Reward(UUID id, String name, int coinAmount) {
        this.id = id;
        this.name = name;
        this.coinAmount = coinAmount;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCoinAmount() {
        return coinAmount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoinAmount(int coinAmount) {
        this.coinAmount = coinAmount;
    }

    /*public static void saveRewardsToCSV() {
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
        // Write CSV header
        fileWriter.append(CSV_HEADER);
        fileWriter.append("\n");

        for (Reward reward : Main.rewards) {

            fileWriter.append(reward.getId().toString());
            fileWriter.append(CSV_DELIMITER);
            fileWriter.append(reward.getName());
            fileWriter.append(CSV_DELIMITER);
            fileWriter.append(Integer.toString(reward.getCoinAmount()));
            fileWriter.append(NEW_LINE_SEPARATOR);
            
        }
        } catch (IOException e) {
            System.out.println("Error while loading parents from CSV file: " + e);
        } finally {
        fileWriter.flush();
        fileWriter.close();
        }
    }*/

   /* public static void loadRewardsFromCSV() {
        BufferedReader reader = null;
        try{
        
        File file = new File(CSV_FILE_PATH);
            if (!file.exists()) {
                System.out.println("Chores CSV file does not exist");
                return;
            }
        Main.rewards = new ArrayList<>();
        reader = new BufferedReader(new FileReader(CSV_FILE_PATH));

        // Skip CSV header
        String line = reader.readLine();

        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(CSV_DELIMITER);

            UUID id = UUID.fromString(fields[0]);
            String name = fields[1];
            int coinAmount = Integer.parseInt(fields[2]);

            Reward reward = new Reward(id, name, coinAmount);
            Main.rewards.add(reward);
        }
        }
        catch (IOException e) {
        System.out.println("Error while loading CSV file: " + e);
    } 
    finally{
        try {
                reader.close();
            } catch (IOException e) {
                System.out.println("Error while closing reader: " + e);
            }
    }
    }*/
}
