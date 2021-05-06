package Point_Of_Sale.Products;

public class NonPerishableProduct extends Product {
    private String manufacturedDate;

    public NonPerishableProduct() {
        super();
        this.manufacturedDate = "";
    }

    public NonPerishableProduct(String id, double price, float weight, String manuDate) {
        super(id, price, weight);
        this.manufacturedDate = manuDate;
    }

    public String getManuDate(){
        return this.manufacturedDate;
    }

    public void setManuDate(String date) {
        this.manufacturedDate = date;
    }
}
