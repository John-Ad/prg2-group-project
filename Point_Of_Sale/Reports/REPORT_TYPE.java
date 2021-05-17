package Point_Of_Sale.Reports;

public enum REPORT_TYPE {
    ITEMS,
    TRANSFERS,
    CUSTOMERS;

    public static String getFileName(REPORT_TYPE type) {
        switch (type) {
        case ITEMS:
            return "items.txt";
        case TRANSFERS:
            return "transfers.txt";
        case CUSTOMERS:
            return "customers.txt";
        default:
            return "";
        }
    }

    public static String getHeader(REPORT_TYPE type) {
        switch (type) {
        case ITEMS:
            return String.format("%-10s\t\t%-20s\t\t%-10\t\t%-10\t\t%-10", "Item ID", "Description", "Price",
                    "Qty sold", "Total");
        case TRANSFERS:
            return String.format("%-10s\t\t%-20s\t\t%-20s\t\t%-20s", "Account ID", "Total Credited",
                    "Total Debited", "Total Transfers");
        case CUSTOMERS:
            return String.format("%-20s\t\t%-20\t\t%-15\t\t%-15", "Name", "Email", "Items bought", "Total spent");
        default:
            return "";
        }
    }
}

