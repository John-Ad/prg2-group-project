package Point_Of_Sale.Users;

public abstract class User {
    private String ID;
    private String name; 
    private String email;

    public User() {
        this.ID = "";
        this.name = "";
        this.email = "";
    }
    public User(String id, String name, String email) {
        this.ID = id;
        this.name = name;
        this.email = email;
    }

    public String getID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return this.email;
    }
}
