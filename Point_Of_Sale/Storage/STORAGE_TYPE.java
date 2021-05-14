package Point_Of_Sale.Storage;

public enum STORAGE_TYPE {
    STORE_PROD,
    STORE_CUST,
    STORE_EMP,
    STORE_TRAN;

    public String getFileName() {
        switch (this) {
        case STORE_CUST:
            return "cust.ser";
        default:
            return "";
        }
    }
}
