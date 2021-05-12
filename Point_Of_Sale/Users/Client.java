package Point_Of_Sale.Users;

import Point_Of_Sale.Account;
import Point_Of_Sale.Card;

public class Client extends User {
    private Account account;

    public Client() {
        super();
        account = new Account();
    }

    public Client(Account acc){
        super();
        account = acc;
    }

    public Client(String id, String name, String email) {
        super(id, name, email);
        account = new Account();
    }

    public Client(String id, String name, String email, Account acc) {
        super(id, name, email);
        account = acc;
    }

    public Account getAcc() {
        return this.account;
    }

    public void setAcc(Account acc) {
        this.account = acc;
    }

    public Card getCard() {
        return this.account.getCard();
    }
}
