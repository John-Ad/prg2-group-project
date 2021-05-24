package Point_Of_Sale.Storage;

import Point_Of_Sale.Events.EVENT_TYPE;

public enum STORAGE_TYPE {
    STORE_PROD,
    STORE_CUST,
    STORE_EMP,
    STORE_TRAN,
    GET_PROD,
    GET_CUST,
    GET_EMP,
    GET_TRAN,
    REM_PROD,
    REM_CUST,
    REM_EMP,
    REM_TRAN,
    FIND_PROD,
    FIND_CUST,
    FIND_EMP,
    FIND_TRAN,
    INC_PROD;


    public String getFileName() {
        switch (this) {
        case STORE_CUST:
            return "cust.ser";
        case GET_CUST:
            return "cust.ser";
        case REM_CUST:
            return "cust.ser";
        case FIND_CUST:
            return "cust.ser";
        case STORE_PROD:
            return "prod.ser";
        case GET_PROD:
            return "prod.ser";
        case REM_PROD:
            return "prod.ser";
        case FIND_PROD:
            return "prod.ser";
        case STORE_EMP:
            return "emp.ser";
        case GET_EMP:
            return "emp.ser";
        case REM_EMP:
            return "emp.ser";
        case FIND_EMP:
            return "emp.ser";
        case STORE_TRAN:
            return "tran.ser";
        case GET_TRAN:
            return "tran.ser";
        case REM_TRAN:
            return "tran.ser";
        case FIND_TRAN:
            return "tran.ser";
        default:
            return "";
        }
    }

    public static STORAGE_TYPE custFromInt(int opt) {
        switch (opt) {
        case 1:
            return STORE_CUST;
        case 2:
            return REM_CUST;
        case 3:
            return FIND_CUST;
        default:
            return null;
        }
    }

    public static STORAGE_TYPE prodFromInt(int opt) {
        switch (opt) {
        case 1:
            return STORE_PROD;
        case 2:
            return REM_PROD;
        case 3:
            return FIND_PROD;
        case 4:
            return INC_PROD;
        default:
            return null;
        }
    }

    public static STORAGE_TYPE empFromInt(int opt) {
        switch (opt) {
        case 1:
            return STORE_EMP;
        case 2:
            return REM_EMP;
        case 3:
            return FIND_EMP;
        default:
            return null;
        }
    }
}
