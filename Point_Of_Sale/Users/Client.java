package Point_Of_Sale.Users;

public class Client extends User {
    private String accID;

    public Client() {
        super();

        accID = "";
    }

    public Client(String id, String name, String email, String accID) {
        super(id, name, email);
        this.accID = accID;
    }

    public String getAccID() {
        return this.accID;
    }

    public void setAccID(String accID) {
        this.accID = accID;
    }
}
