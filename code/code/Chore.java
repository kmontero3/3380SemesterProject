package code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class Chore {
    private UUID id;
    private String chore_name;
    private int rewardAmount;
    private boolean isComplete;
    private int recurrenceFrequency; // in days

    private static final String CSV_FILE_PATH = "childdata.csv";
    private static final String CSV_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String CSV_HEADER = "ID,Name,RewardAmount,Completed,RecurrenceFrequency\n";

    
    public Chore(UUID id, String chore_name, int rewardAmount, boolean completed, int recurrenceFrequency) {
        this.chore_name = chore_name;
        this.rewardAmount = rewardAmount;
        this.isComplete = false;
        this.recurrenceFrequency = recurrenceFrequency;
    }
    
    
    public String getName() {
        return chore_name;
    }
    
    public int getRewardAmount() {
        return rewardAmount;
    }
    
    public boolean isComplete() {
        return isComplete;
    }
    
    public int getRecurrenceFrequency() {
        return recurrenceFrequency;
    }
    
    public void setComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }

    // public String getChore() {
    //     return null;
    // }

    // public boolean isCompleted() {
    //     return false;
    // }

    // public int getRecurrence() {
    //     return 0;
    // }
    // Save chores to CSV file
    public static void saveChoresToCSV() {
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
            for (Chore chore : Main.chores) {
                fileWriter.append(chore.getId().toString());
                fileWriter.append(CSV_DELIMITER);
                fileWriter.append(chore.getName());
                fileWriter.append(CSV_DELIMITER);
                fileWriter.append(Integer.toString(chore.getRewardAmount()));
                fileWriter.append(CSV_DELIMITER);
                fileWriter.append(Boolean.toString(chore.isComplete()));
                fileWriter.append(CSV_DELIMITER);
                fileWriter.append(Integer.toString(chore.getRecurrenceFrequency()));
                fileWriter.append(NEW_LINE_SEPARATOR);
            }
        } catch (IOException e) {
            System.out.println("Error while saving chores to CSV file: " + e.getMessage());
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.flush();
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Error while closing fileWriter: " + e.getMessage());
            }
        }
    }

    private Object getId() {
        return id;
    }


    // Load chores from CSV file
    public static void loadChoresFromCSV() {
        Main.chores = new ArrayList<>();
        BufferedReader reader = null;
        try {
            File file = new File(CSV_FILE_PATH);
            if (!file.exists()) {
                System.out.println("Chores CSV file does not exist");
                return;
            }
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine(); // skip header
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                UUID id = UUID.fromString(fields[0]);
                String name = fields[1];
                int rewardAmount = Integer.parseInt(fields[2]);
                boolean isComplete = Boolean.parseBoolean(fields[3]);
                int recurrenceFrequency = Integer.parseInt(fields[4]);
                Chore chore = new Chore(id, name, rewardAmount, isComplete, recurrenceFrequency);
                Main.chores.add(chore);
                
            }
        } catch (IOException e) {
            System.out.println("Error while loading chores from CSV file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error while parsing CSV file: " + e.getMessage());
        }
}
}
