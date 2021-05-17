package Point_Of_Sale.Storage;

public enum STORAGE_TYPE {
    STORE_PROD,
    STORE_CUST,
    STORE_EMP,
    STORE_TRAN,
    GET_PROD,
    GET_CUST,
    GET_EMP,
    GET_TRAN;

    public String getFileName() {
        switch (this) {
        case STORE_CUST:
            return "cust.ser";
        case GET_CUST:
            return "cust.ser";
        case STORE_PROD:
            return "prod.ser";
        case GET_PROD:
            return "prod.ser";
        case STORE_EMP:
            return "emp.ser";
        case GET_EMP:
            return "emp.ser";
        case STORE_TRAN:
            return "tran.ser";
        case GET_TRAN:
            return "tran.ser";
        default:
            return "";
        }
    }
}
