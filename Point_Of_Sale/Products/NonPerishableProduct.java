package Point_Of_Sale.Products;

public class NonPerishableProduct extends Product {
    private String manufacturedDate;

    public NonPerishableProduct() {
        super();
        this.manufacturedDate = "";
    }

    public NonPerishableProduct(String id, String desc, double price, float weight, int qty, String manuDate) {
        super(id, desc, price, weight, qty);
        this.manufacturedDate = manuDate;
    }

    public String getManuDate(){
        return this.manufacturedDate;
    }

    public void setManuDate(String date) {
        this.manufacturedDate = date;
    }
}
