package Point_Of_Sale.Products;

public class PerishableProduct extends Product {
    private String expirationDate;

    public PerishableProduct() {
        super();
        this.expirationDate = "";
    }

    public PerishableProduct(String id, String name, String desc, double price, float weight, int qty, String exp) {
        super(id, name, desc, price, weight, qty);
        this.expirationDate = exp;
    }

    //      getters/setters
    public String getExpDate(){
        return this.expirationDate;
    }

    public void setExpDate(String date) {
        this.expirationDate = date;
    }
}
