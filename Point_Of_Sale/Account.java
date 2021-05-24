package Point_Of_Sale;

import java.io.Serializable;

public class Account implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String accountID;
    private double balance;

    private Card card; //replace with card class

    public Account() {
        this.accountID = "";
        this.balance = 0;
        this.card = new Card();
        this.card.setPin();
    }

    public Account(String accID, double bal) {
        this.accountID = accID;
        this.balance = bal;
        this.card = new Card();
        card.setPin();
    }

    //      getters/setters
    public String getAccID() {
        return this.accountID;
    }

    public void setAccID(String accID) {
        this.accountID = accID;
    }

    public double getBalance() {
        return this.balance;
    }

    public Card getCard() {
        return this.card;
    }
    //  ---------- end of getters/setters -------------

    //      worker methods
    public boolean debit(double amount) {
        if (this.balance >= amount) {   // check if balance is enough
            this.balance -= amount;
            System.out.println("Transaction successful!\nAvailable balance: " + this.balance + "\n");
            return true;
        }
        System.out.format("Transaction failed! Insufficient funds!\nAvailable balance: %s\n", this.balance);
        return false;
    }

    public void credit(double amount) {
        this.balance += amount;
    }
    //      end of worker methods
}
