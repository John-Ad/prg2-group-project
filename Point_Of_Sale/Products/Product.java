package Point_Of_Sale.Products;

import java.io.Serializable;

// product base class
public abstract class Product implements Serializable {
    public static final int MIN_QTY = 5;    // used when checking stock levels

    // instance variables
    private String productID;
    private String name;
    private String description;
    private double price;
    private float weight;
    private int quantity;
    //------------------------

    // constructors
    public Product() {
        this.productID = "";
        this.name = "";
        this.description = "";
        this.price = 0;
        this.weight = 0;
        this.quantity = 0;
    }

    public Product(String id, String name, String desc, double price, float weight, int qty) {
        this.productID = id;
        this.name = name;
        this.description = desc;
        this.price = price;
        this.weight = weight;
        this.quantity = qty;
    }
    // end of constructors

    //  getters/setters
    public String getProdID() {
        return this.productID;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public float getWeight() {
        return this.weight;
    }

    public int getQty() {
        return this.quantity;
    }

    public String getDesc() {
        return this.description;
    }

    public void setProdID(String id) {
        this.productID = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setWeigth(float weight) {
        this.weight = weight;
    }

    public void setQty(int qty) {
        this.quantity = qty;
    }

    public void setDesc(String desc) {
        this.description = desc;
    }
    // end of getters/setters

    @Override
    public String toString() {      // return a formated string desrcibing product
        return String.format("%-10s\t\t%-30s\t\t%-10s\t\t%-10s\t\t%-10s\n%-10s\t\t%-30s\t\t%-10s\t\t%-10s\t\t%-10s",
                "ID", "Desc", "Price", "Weight", "Qty", this.productID, this.description, this.price, this.weight,
                this.quantity);
    }
}
