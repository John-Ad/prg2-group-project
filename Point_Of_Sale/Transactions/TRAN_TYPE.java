package Point_Of_Sale.Transactions;

//  enumeration of transaction types
public enum TRAN_TYPE {
    SALE, REFUND;   // different transaction types

    public static TRAN_TYPE fromInt(int opt) {  // return transaction type based on integer provided
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
