package Point_Of_Sale.Users;

import Point_Of_Sale.Account;

public class Client extends User {
    private Account account;
    private int cardNum;

    public Client() {
        super();
    }

    public Client(String id, String name, String email) {
        super(id, name, email);
    }

    public Account getAccID() {
        return this.account;
    }

    public void setAccID(Account acc) {
        this.account = acc;
    }

    public int getCardNum() {
        return this.cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }
}
