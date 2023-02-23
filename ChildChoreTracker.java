import java.util.HashMap;

public class ChildChoreTracker {
    private HashMap<String, Integer> choreMap;
    private HashMap<String, Integer> coinsMap;

    public ChildChoreTracker() {
        choreMap = new HashMap<>();
        coinsMap = new HashMap<>();
    }

    // Add a new chore with its corresponding coin reward
    public void addChore(String chore, int coins) {
        choreMap.put(chore, 0);
        coinsMap.put(chore, coins);
    }

    // Update the completion status of a chore
    public void completeChore(String chore) {
        choreMap.put(chore, 1);
    }

    // Get the coin reward for a chore
    public int getCoins(String chore) {
        return coinsMap.get(chore);
    }

    // Get the total number of coins earned by the child
    public int getTotalCoins() {
        int totalCoins = 0;
        for (String chore : choreMap.keySet()) {
            if (choreMap.get(chore) == 1) {
                totalCoins += coinsMap.get(chore);
            }
        }
        return totalCoins;
    }

    // Print the status of all chores and the total number of coins earned
    public void printStatus() {
        System.out.println("Chore\t\tCompletion\tCoins Earned");
        for (String chore : choreMap.keySet()) {
            String completionStatus = (choreMap.get(chore) == 1) ? "Completed" : "Incomplete";
            int coinsEarned = (choreMap.get(chore) == 1) ? coinsMap.get(chore) : 0;
            System.out.println(chore + "\t\t" + completionStatus + "\t\t" + coinsEarned);
        }
        System.out.println("Total Coins Earned: " + getTotalCoins());
    }

public static void main(String[] args) {
    ChildChoreTracker tracker = new ChildChoreTracker();

    // Add some chores with their corresponding coin rewards
    tracker.addChore("Wash dishes", 10);
    tracker.addChore("Clean room", 20);
    tracker.addChore("Take out trash", 5);

    // Mark some chores as completed
    tracker.completeChore("Wash dishes");
    tracker.completeChore("Clean room");

    // Print the status of all chores and the total number of coins earned
    tracker.printStatus();
    }
}
