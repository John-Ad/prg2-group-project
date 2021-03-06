package Point_Of_Sale.Products;

public class NonPerishableProduct extends Product {         // class for non perishable products
    //private instance variables
    private String manufacturedDate;                    

    // constructors
    public NonPerishableProduct() {
        super();
        this.manufacturedDate = "";
    }

    public NonPerishableProduct(String id, String name, String desc, double price, float weight, int qty,
            String manuDate) {
        super(id, name, desc, price, weight, qty);
        this.manufacturedDate = manuDate;
    }
    //------------------------------

    // getters setters
    public String getManuDate(){
        return this.manufacturedDate;
    }

    public void setManuDate(String date) {
        this.manufacturedDate = date;
    }
    //----------------------------
}
