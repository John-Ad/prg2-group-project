package Point_Of_Sale.Products;

//return specific subclass of product
public class ProductFactory {
    public static Product getProduct(PRODUCT_TYPE type) {   
        switch (type) {
        case PERISHABLE:
            return ProductBuilder.buildPerProd();
        case NON_PERISHABLE:
            return ProductBuilder.buildNonPerProd();
        default:
            return null;
        }
    }
}
