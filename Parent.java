public class Parent {
    private int id;
    private String name;
    private String password;

    public Parent(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCoinsAvailable() {
        return password;
    }
}