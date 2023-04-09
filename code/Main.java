package code;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

//when the main code is implemented, it the new data will be uploaded to the database, which will also be 
//connected to our app. Whenever chores are completed, the Child's coins available will increase by the 
//chore reward amount and whenever a reward is bought, the child's coins will decrease by the amount of 
//the coin reward. This implementation is not possible without being connected to the database.
public class Main {
    
    public static ArrayList<Parent> parents = new ArrayList<>();
    public static ArrayList<Child> children = new ArrayList<>();
    public static ArrayList<Reward> rewards = new ArrayList<>();
    public static ArrayList<Chore> chores = new ArrayList<>();

    
    public static Child getChildByName(ArrayList<Child> children, String name) {
        for (Child child : children) {
            if (child.getName().equals(name)) {
                return child;
            }
        }
        return null;
    }

    public void getterForGetChildByName(ArrayList<Child> children, String name){
        getChildByName(children, name);
    }

    public static Parent getParentByName(ArrayList<Parent> parents, String name, String password) {
        for (Parent parent : parents) {
            if (parent.getName().equals(name) && parent.getPassword().equals(password)) {
                return parent;
            }
        }
        return null;
    }
    public static Parent getParentByID(ArrayList<Parent> parents, UUID id) {
        for (Parent parent : parents) {
            if (parent.getId().equals(id)) {
                return parent;
            }
        }
        return null;
    }

    public static ArrayList<Child> findChildrenByParent(ArrayList<Child> children, Parent parent) {
        ArrayList<Child> childrenWithSameParent = new ArrayList<>();
        for (Child child : children) {
            if (child.getParent().equals(parent)) {
                childrenWithSameParent.add(child);
            }
        }
        return childrenWithSameParent;
    }

    // public static Chore getChoreByName(String name, Chore[] chores2) {
    //     for (Chore chore : chores2) {
    //         if (chore.getName().equals(name)) {
    //             return chore;
    //         }
    //     }
    //     return null; // chore not found
    // }
    public static Chore getChoreByName(String choreName, ArrayList<Chore> chores2) {
        for (Chore chore : chores2) {
            if (chore.getName().equals(choreName)) {
                return chore;
            }
        }
        return null; // chore not found
    }

    public static Reward getRewardByName(String rewardName, ArrayList<Reward> rewards) {
        for (Reward reward : rewards) {
            if (reward.getName().equals(reward.getName())) {
                return reward;
            }
        }
        return null;
    }

    public static void printChoresByChild(Child child) {
        for (Chore chore : child.getChores()) {
            if(chore != null){
            System.out.println("\t \t" + chore.getName() + " Reward Amount: " + chore.getRewardAmount() + " Completed: " + chore.isComplete());
            }
        }

    }
    public static String choresByChildToSting(Child child) {
        String str = "";
        for (Chore chore : child.getChores()) {
            if(chore != null){
            str += "" + chore.getName() + " Reward Amount: " + chore.getRewardAmount() + " Completed: " + chore.isComplete() + "\t";
            }
        }
        return str;

    }
    public static String rewardsByChildToString(Child child) {
        String str = "";
        for (Reward reward : child.getRewards()) {
            if(reward != null){
            str += reward.getName() + " \t";
            }
        }
        return str;

    }

    public static void printRewardsByChild(Child child) {
        for (Reward reward : child.getRewards()) {
            System.out.println("\t \t" + reward.getName());
        }
    }
    
    


    public static void main(String[] args) throws SQLException, InterruptedException {
        // theUI.showmainpage();
        // Connection connection = null;
        // Statement statement = null;

        // Establish connection to database
        // connection = DriverManager.getConnection(URL, USER, PASSWORD);
        // statement = connection.createStatement();



        // upload example children and parents
        final int MAX_CHILDREN = 3;
        final int NUM_PARENTS = 50;
        final String[] NAMES = {"Avery", "Bailey", "Carter", "Dylan", "Ethan", "Finley", "Grayson", "Harper", "Isabella", "Jaxon", "Kennedy", "Liam", "Mason", "Noah", "Olivia", "Parker", "Quinn", "Riley", "Sophia", "Taylor", "Victoria", "Wyatt", "Xander", "Yasmine", "Zoe"};

        final Random random = new Random();



        for (int i = 0; i < NUM_PARENTS; i++) {
            UUID parentId = UUID.randomUUID();
            String parentName = NAMES[random.nextInt(NAMES.length)];
            String password = parentName + "1";
            Parent parent = new Parent(parentId, parentName, password);

            int numChildren = random.nextInt(MAX_CHILDREN) + 1;
            for (int j = 0; j < numChildren; j++) {
                UUID childId = UUID.randomUUID();
                String childName = NAMES[random.nextInt(NAMES.length)];
                Child child = new Child(childId, childName, 0, parent, new ArrayList<Reward>(), new ArrayList<Chore>());
                children.add(child);
            }

            parents.add(parent);
        }

  

        //Upload example chores
        final int MAX_REWARD_AMOUNT = 150;
        final int MIN_REWARD_AMOUNT = 5;
        final int REWARD_AMOUNT_STEP = 5;
        final int MAX_RECURRENCE_FREQUENCY = 7; // in days

        final String[] CHORE_NAMES = {
            "Cut the grass",
            "Take out the trash",
            "Do the dishes",
            "Sweep the floor",
            "Clean the bathroom",
            "Wash the car",
            "Walk the dog",
            "Fold laundry",
            "Vacuum the carpets",
            "Clean the windows"
        };



            for (String choreName : CHORE_NAMES) {
                int rewardAmount = random.nextInt((MAX_REWARD_AMOUNT - MIN_REWARD_AMOUNT) / REWARD_AMOUNT_STEP + 1) * REWARD_AMOUNT_STEP + MIN_REWARD_AMOUNT;
                chores.add(new Chore(UUID.randomUUID(), choreName, rewardAmount, false, random.nextInt(MAX_RECURRENCE_FREQUENCY) + 1));
            }


        final int MAX_COIN_AMOUNT = 150;
        final int MIN_COIN_AMOUNT = 5;
        final int COIN_AMOUNT_STEP = 5;

        final String[] REWARD_NAMES = {
            "Video game time",
            "Ice cream",
            "Toy",
            "Stickers",
            "Movie night",
            "Pizza party",
            "Extra allowance",
            "Lego set",
            "Board game",
            "Book"
        };




        for (String choreName : REWARD_NAMES) {
            String rewardName = REWARD_NAMES[random.nextInt(REWARD_NAMES.length)];
            int coinAmount = random.nextInt((MAX_COIN_AMOUNT - MIN_COIN_AMOUNT) / COIN_AMOUNT_STEP + 1) * COIN_AMOUNT_STEP + MIN_COIN_AMOUNT;
            Reward reward = new Reward(UUID.randomUUID(), rewardName, coinAmount);
            rewards.add(reward);
        }
    



        
        //loadCSVData();
        //saveCSVData();
            //THIS IS THE CODE TO TEST THE WHOLE THING
            parentMode();

    }
    public static String username;
    public static String password;
    public static void parentMode() {
        Scanner in = new Scanner(System.in);
        Boolean validLogin = false;
        Parent par=null;
        
        if (!validLogin) {new LoginFrame(parents);}
    
        int choice = 1;
        int option;
        while(choice == 1){
            System.out.println("Hello! What would you like to do today");
            System.out.println("1: Add a Child");
            System.out.println("2: Assign a chore");
            System.out.println("3: See Child chore, coin, or reward status");
            System.out.println("4: Enter child mode");
            System.out.println("5: Log out");
            option = in.nextInt();
            ArrayList<Child> parChildren = findChildrenByParent(children,par);

            //parChildren = 
            switch(option) {
                
                case 1: 
                    System.out.print("enter child's name:");
                    String cName = in.nextLine();
                    in.nextLine();
                    Child newChild = new Child(UUID.randomUUID(),cName, 0, par, new ArrayList<Reward>(), new ArrayList<Chore>());
                    System.out.println(newChild.getId() + " " + newChild.getName() + " " + newChild.getParent().getName());
                    children.add(newChild);
                    System.out.println("Child successfully added!");
                    System.out.println("What do you want to do now?  1: Back to Main Menu | 2: Exit");
                    choice = in.nextInt();
                    if(choice==1) {
                        continue;
                    }
                    else {
                        break;
                    }
                    
                
                case 2: 
                    System.out.println("Which child would you like to assign a chore to?");
                    System.out.println("Children: "  ); //list children that belong to parent
                    for(Child child : parChildren) {
                        System.out.println(child.getName());
                    }
                    in.nextLine();
                    cName = in.nextLine();
                    Child tempChild = getChildByName(parChildren, cName);
                    System.out.println("What chore would you like to add?");
                    for(Chore chore : chores) {
                        System.out.println(chore.getName());
                    }
                    String choreName = in.nextLine();
                    Chore tempChore = getChoreByName(choreName, chores);
                    tempChild.addChore(tempChore);
                    //if entered name = parents kid 
                        //prompt list of chores to assign (maybe option to add new chore)
                        //assign chore and display success message
                    System.out.println("What do you want to do now?  1: Back to Main Menu | 2: Exit");
                    choice = in.nextInt();
                    if(choice==1) {
                        continue;
                    }
                    else {
                        break;
                    }
                
                case 3:
                    //"select child you would like to view with option to select all"
                    //display results
                    // for(Child i: parChildren) {
                    //     System.out.println(i.getName());
                    // }
                    // System.out.println("enter the name of the child. (or 'ALL' to view all children)");
                    // cName = in.nextLine();
                    // if(cName.equals("ALL")){
                    for(Child i: parChildren) {
                        System.out.println(i.getName());
                        System.out.println("\t Chores:");
                        printChoresByChild(i);
                        System.out.println("\t Coins Available:" + i.getCoinsAvailable());
                        System.out.println("\t \t Available Rewards:");
                        printRewardsByChild(i);
                    }
                    



                    System.out.println("What do you want to do now?  1: Back to Main Menu | 2: Exit");
                    choice = in.nextInt();
                    
                    if(choice==1) {
                        continue;
                    }
                    else {
                        break;
                    }

                case 4: //child mode
                    for (Child i: parChildren){
                        System.out.println(i.getName());
                    }
                    
                    System.out.println("What is your name?");
                    in.nextLine();
                    String childEnteredName = in.nextLine();
                    Child chil = getChildByName(parChildren, childEnteredName);

                    childMode(chil);
                    return;

                    // System.out.println("Hi " + chil.getName() + "!");

                
                    // System.out.println("What do you want to do now?  1: Back to Main Menu | 2: Exit");
                    // choice = in.nextInt();
                    // if(choice==1) {
                    //     continue;
                    // }
                    // else {
                    //     break;
                    // }
            
            }
        }
    
        
    }


    public static void childMode(Child child) {
        Scanner in = new Scanner(System.in);

        int choice = 1;
        int option;
        while(choice ==1) {
            System.out.print("Available Coins: " + child.getCoinsAvailable() + "\t\t\t Rewards: ");
            for (Reward reward : child.getRewards()) {
                System.out.print(reward.getName() + "  ");
            }
            System.out.println("\n Chores: ");
            
            for (Chore chore : child.getChores()) {
                System.out.print(chore.getName());
            }
            printChoresByChild(child);
            System.out.println("1: Complete a chore");
            System.out.println("2. Enter Store");
            System.out.println("3. Exit");
            System.out.println("what is your choice?");
            option = in.nextInt();

            switch(option) {
                case 1:
                    System.out.println("Which chore would you like to complete?");
                    printChoresByChild(child);
                    in.nextLine();
                    String tempChore = in.nextLine();
                    Chore  chore = getChoreByName(tempChore, child.getChores());
                    child.completedChore(child, chore);
                    System.out.println("You succesfully completed " + chore.getName() + "! You now have " + child.getCoinsAvailable() + "coins.");
                    System.out.println("What do you want to do now?  1: Back to Main Menu | 2: Exit");
                    choice = in.nextInt();
                    
                    if(choice==1) {
                        continue;
                    }
                    else {
                        break;
                    }
                case 2:
                    Boolean validReward = false;
                    while(!validReward) {
                        String tempReward;
                        System.out.println("You have " + child.getCoinsAvailable() + " coins availble");
                        for (Reward reward : rewards) {
                            System.out.println(reward.getName() + "\t $" + reward.getCoinAmount());
                        }
                        System.out.println("\n What reward would you like to buy? Press 0 to escape");
                        in.nextLine();
                        tempReward = in.nextLine();
                        if (tempReward=="0") {
                            validReward=true;
                        }
                        Reward reward = getRewardByName(tempReward, rewards);
                        if(child.getCoinsAvailable() < reward.getCoinAmount()) {
                            System.out.println("You don't have enough coins to buy " + reward.getName() + ". Please buy something worth less than " + child.getCoinsAvailable() +" coins.");
                        }
                        else {
                            child.addReward(child, reward);
                            validReward=true;
                        }
                    }
                    System.out.println("What do you want to do now?  1: Back to Main Menu | 2: Exit");
                    choice = in.nextInt();
                    
                    if(choice==1) {
                        continue;
                    }
                    else {
                        break;
                    }
                case 3:
                    System.out.println("Re-entering Parent Mode");
                    parentMode();
                    break;
            }
        }
    }
  /*  public static void loadCSVData(){
        Child.loadChildrenFromCSV();
        Chore.loadChoresFromCSV();
        Parent.loadParentsFromCSV();
        Reward.loadRewardsFromCSV();
    }
    public static void saveCSVData(){
        Child.saveChildrentoCSV();
        Chore.saveChoresToCSV();
        Parent.saveParentsToCSV();
        Reward.saveRewardsToCSV();
    }*/
}