package Point_Of_Sale.Transactions;

import java.io.Serializable;
import java.util.ArrayList;

import Point_Of_Sale.Products.Product;
import Point_Of_Sale.Users.Client;

public class Transaction implements Serializable{
    private int numOfItems;
    private ArrayList<Product> items;
    private double amount;
    private Client client;

    private void setNumOfItems() {
        this.numOfItems = this.items.size();
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setItems(ArrayList<Product> items) {
        this.items = items;
        setNumOfItems();
    }

    public int getNumOfItems() {
        return this.numOfItems;
    }

    public double getAmount() {
        return this.amount;
    }

    public Client getClient() {
        return this.client;
    }

    public  ArrayList<Product> getItems() {
        return this.items;
    }
}
