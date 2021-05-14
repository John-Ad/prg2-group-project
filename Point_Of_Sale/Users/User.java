package Point_Of_Sale.Users;

import java.io.Serializable;

public abstract class User implements Serializable{
    private static final long serialVersionUID = 1L;    // id for serialization
    private String ID;
    private String name;
    private String email;

    // constructors
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
    // end of constructors

    // getters/setters
    public String getID() {
        return this.ID;
    }

    public void setID(String id) {
        this.ID = id;
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
    // end of getters/setters

    @Override
    public String toString() {      // custom to string
        return String.format("%-10s\t\t%-20s\t\t%-10s\n%-10s\t\t%-20s\t\t%-10s", "ID", "Name", "Email", this.ID, this.name,
                this.email);
    }
}
