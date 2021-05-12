package Point_Of_Sale.Reports;

public enum REPORT_TYPE {
    TEST,
    EARNINGS,
    PAYROLL;

    public static String getFileName(REPORT_TYPE type) {
        switch (type) {
        case TEST:
            return "test.txt";
        case EARNINGS:
            return "earnings.txt";
        case PAYROLL:
            return "payroll.txt";
        }
        return "";
    }
}

