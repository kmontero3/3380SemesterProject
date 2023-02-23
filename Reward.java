public class Reward {
    private String name;
    private int coinAmount;
    
    public Reward(int id, String name, int coinAmount) {
        this.name = name;
        this.coinAmount = coinAmount;
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
}
