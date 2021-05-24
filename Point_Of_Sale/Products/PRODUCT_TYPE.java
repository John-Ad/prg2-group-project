package Point_Of_Sale.Products;

// enum for product types 
public enum PRODUCT_TYPE {
    PERISHABLE, NON_PERISHABLE;     // types of products

    public static PRODUCT_TYPE fromInt(int opt) {       // convert from int to a product type
        switch (opt) {
        case 1:
            return PERISHABLE;
        case 2:
            return NON_PERISHABLE;
        default:
            return null;
        }
    }
}
