package code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class Parent {
    private UUID id;
    private String name;
    private String password;

    private static final String CSV_FILE_PATH = "parentdata.csv";
    private static final String CSV_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
    private static final String CSV_HEADER = "ID,Name,Password\n";

    public Parent(UUID id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }


    // Write parents to CSV file
    public static void saveParentsToCSV() {
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

            // write header row
            fileWriter.append("ID");
            fileWriter.append(CSV_DELIMITER);
            fileWriter.append("Name");
            fileWriter.append(CSV_DELIMITER);
            fileWriter.append("Password");
            fileWriter.append("\n");

            // write data rows
            for (Parent parent : Main.parents) {
                fileWriter.append(parent.getId().toString());
                fileWriter.append(CSV_DELIMITER);
                fileWriter.append(parent.getName());
                fileWriter.append(CSV_DELIMITER);
                fileWriter.append(parent.getPassword());
                fileWriter.append("\n");
            }
            System.out.println("Parents saved to CSV file successfully!");

        } catch (IOException e) {

            System.out.println("Error while writing parents to CSV file: " + e);

        } finally {

            try {
                fileWriter.flush();
                fileWriter.close();

            } catch (IOException e) {

                System.out.println("Error while flushing/closing fileWriter: " + e);
            }
        }
    }

    // Load parents from CSV file
    public static void loadParentsFromCSV() {

        BufferedReader reader = null;

        try {
            File file = new File(CSV_FILE_PATH);
            if (!file.exists()) {
                System.out.println("Chores CSV file does not exist");
                return;
            }

            reader = new BufferedReader(new FileReader(CSV_FILE_PATH));
            Main.parents = new ArrayList<Parent>();
            String line = reader.readLine(); // skip the header line
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(CSV_DELIMITER);
                UUID id = UUID.fromString(fields[0]);
                String name = fields[1];
                String password = fields[2];
                Parent parent = new Parent(id, name, password);
                // add the parent to the Main.parents list
                Main.parents.add(parent);

            }

            System.out.println("Parents loaded from CSV file successfully!");

        } catch (IOException e) {

            System.out.println("Error while loading parents from CSV file: " + e);

        } finally {

            try {
                reader.close();
                
            } catch (IOException e) {
                System.out.println("Error while closing reader: " + e);
            }

        }
    }
}
