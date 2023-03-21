public class Child {
    private int id;
    private String name;
    private int coinsAvailable;
    private Parent parent;
    private Reward[] rewards;
    private Chore[] chores;


    public Child(int id, String name, int coinsAvailable, Parent parent) {
        this.parent = parent;
        this.id = id;
        this.name = name;
        this.coinsAvailable = coinsAvailable;
    }

    public int getId() {
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

    public Reward[] getRewards() {
        return rewards;
    }

    public Chore[] getChores() {
        return chores;
    }

    public void addChore(Chore chore) {
        // Check if chores array is null
        if (chores == null) {
            chores = new Chore[1];
            chores[0] = chore;
        } else {
            // Create a new array with length increased by 1
            Chore[] newChores = new Chore[chores.length + 1];
    
            // Copy existing chores to new array
            for (int i = 0; i < chores.length; i++) {
                newChores[i] = chores[i];
            }
    
            // Add new chore to end of new array
            newChores[chores.length] = chore;
    
            // Replace old array with new array
            chores = newChores;
        }
    }

    public void removeChore(Chore chore) {
        // Check if chores array is null or empty
        if (chores == null || chores.length == 0) {
            return;
        }

        // Search for index of chore in array
        int index = -1;
        for (int i = 0; i < chores.length; i++) {
            if (chores[i].equals(chore)) {
                index = i;
                break;
            }
        }

        // If chore not found, return
        if (index == -1) {
            return;
        }

        // Create a new array with length decreased by 1
        Chore[] newChores = new Chore[chores.length - 1];

        // Copy existing chores to new array, skipping the removed chore
        int j = 0;
        for (int i = 0; i < chores.length; i++) {
            if (i != index) {
                newChores[j++] = chores[i];
            }
        }

        // Replace old array with new array
        chores = newChores;
    }
    public void updateCoins(int newCoins) {
        coinsAvailable = newCoins;
    }


}