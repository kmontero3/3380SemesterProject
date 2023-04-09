package code;

import java.util.ArrayList;
import java.util.UUID;

public interface AppFactory {
    Achievement createAchievement(String name, String description, int extraCoins, int requiredChores);
    Child createChild(UUID id, String name, int coinsAvailable, Parent parent, ArrayList<Reward> rewards, ArrayList<Chore> chores);
    Chore createChore(UUID id, String name, int rewardAmount, boolean completed, int recurrenceFrequency);
    Parent createParent(UUID id, String name, String password);
    Reward createReward(UUID id, String name, int cost);
}
