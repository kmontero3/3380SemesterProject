public class Chore {
    String chore_name;
    int rewardAmount;
    boolean isComplete;
    int recurrenceFrequency; // in days
    
    public Chore(int id, String chore_name, int rewardAmount, boolean completed, int recurrenceFrequency) {
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
}
