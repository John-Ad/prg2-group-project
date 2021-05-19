package Point_Of_Sale.Transactions;

public enum TRAN_TYPE {
    SALE, REFUND;

    public static TRAN_TYPE fromInt(int opt) {
        switch (opt) {
        case 1:
            return SALE;
        case 2:
            return REFUND;
        default:
            return null;
        }
    }
}
