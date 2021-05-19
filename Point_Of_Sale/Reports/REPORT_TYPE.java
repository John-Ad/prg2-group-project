package Point_Of_Sale.Reports;

public enum REPORT_TYPE {
    ITEMS,
    TRANSFERS,
    CUSTOMERS,
    READ_ITEMS,
    READ_TRANSFERS,
    READ_CUSTOMERS;

    public static String getFileName(REPORT_TYPE type) {
        switch (type) {
        case ITEMS:
            return "items.txt";
        case READ_ITEMS:
            return "items.txt";
        case TRANSFERS:
            return "transfers.txt";
        case READ_TRANSFERS:
            return "transfers.txt";
        case CUSTOMERS:
            return "customers.txt";
        case READ_CUSTOMERS:
            return "customers.txt";
        default:
            return "";
        }
    }

    public static String getHeader(REPORT_TYPE type) {
        switch (type) {
        case ITEMS:
            return String.format("%-20s\t\t%-20s\t\t%-20s\t\t%-20s\t\t%-20s\n", "Item ID", "Description", "Price",
                    "Qty sold", "Total");
        case TRANSFERS:
            return String.format("%-20s\t\t%-20s\t\t%-20s\t\t%-20s\n", "Account ID", "Total Credited", "Total Debited",
                    "Total Transfers");
        case CUSTOMERS:
            return String.format("%-20s\t|\t%-20s\t|\t%-20s\t|\t%-20s\n", "Name", "Email", "Items bought",
                    "Total spent");
        default:
            return "";
        }
    }

    public static REPORT_TYPE fromInt(int opt) {
        switch (opt) {
        case 1:
            return ITEMS;
        case 2:
            return TRANSFERS;
        case 3:
            return CUSTOMERS;
        case 4:
            return READ_ITEMS;
        case 5:
            return READ_TRANSFERS;
        case 6:
            return READ_CUSTOMERS;
        default:
            return null;
        }
    }
}

