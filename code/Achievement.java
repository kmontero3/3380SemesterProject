package code;

public class Achievement {
    private String name;
    private String description;
    private boolean completed;
    private int extraCoins;
    private int requiredChores;

    public Achievement(String name, String description, int extraCoins, int requiredChores) {
        this.name = name;
        this.description = description;
        this.extraCoins = extraCoins;
        this.requiredChores = requiredChores;
        this.completed = false;
    }


    public int getExtraCoins() {
        return extraCoins;
    }
    
    public int getRequiredChores() {
        return requiredChores;
    }
    
    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}