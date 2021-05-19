package Point_Of_Sale.Products;

public enum PRODUCT_TYPE {
    PERISHABLE, NON_PERISHABLE;

    public static PRODUCT_TYPE fromInt(int opt) {
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
