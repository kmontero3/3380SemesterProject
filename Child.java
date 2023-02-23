public class Child {
    private int id;
    private String name;
    private int coinsAvailable;

    public Child(int id, String name, int coinsAvailable) {
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
}