package Point_Of_Sale.Products;

public class ProductFactory {
     public static Product getProduct(PRODUCT_TYPE type) {
        switch (type) {
        case PERISHABLE:
            return new PerishableProduct();
        case NON_PERISHABLE:
            return new NonPerishableProduct();
        }
        return null;
    }   
}
