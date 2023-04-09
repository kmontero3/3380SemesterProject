package code;

import java.util.ArrayList;
import java.util.UUID;

public class ConcreteAppFactory implements AppFactory {
	 @Override
	    public Achievement createAchievement(String name, String description, int extraCoins, int requiredChores) {
	        return new Achievement(name, description, extraCoins, requiredChores);
	    }

	    @Override
	    public Child createChild(UUID id, String name, int coinsAvailable, Parent parent, ArrayList<Reward> rewards, ArrayList<Chore> chores) {
	        return new Child(id, name, coinsAvailable, parent, rewards, chores);
	    }

	    @Override
	    public Chore createChore(UUID id, String name, int rewardAmount, boolean completed, int recurrenceFrequency) {
	        return new Chore(id, name, rewardAmount, completed, recurrenceFrequency);
	    }

	    @Override
	    public Parent createParent(UUID id, String name, String password) {
	        return new Parent(id, name, password);
	    }

	    @Override
	    public Reward createReward(UUID id, String name, int cost) {
	        return new Reward(id, name, cost);
	    }
}

