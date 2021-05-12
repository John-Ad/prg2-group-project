package Point_Of_Sale.Products;

public abstract class Product {
    private String productID;
    private String description;
    private double price;
    private float weight;
    private int quantity;

    public Product() {
        this.productID = "";
        this.price = 0;
        this.weight = 0;
    }

    public Product(String id, double price, float weight) {
        this.productID = id;
        this.price = price;
        this.weight = weight;
    }

    //  getters/setters
    public String getProdID(){
        return this.productID;
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

    public void setProdID(String id){
        this.productID = id;
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
}
