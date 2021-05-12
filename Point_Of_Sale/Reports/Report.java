package Point_Of_Sale.Reports;

import java.io.FileNotFoundException;
import java.util.Scanner;

import Point_Of_Sale.*;

public class Report {
    private static final String formats[]={
        "\tID\t\tName\t\tSurname\t\tAge"
    };

    public static void getReport(REPORT_TYPE type) throws Exception{
        Scanner scanner = ScannerAccess.getScanner(type);
        while(scanner.hasNext()){
            System.out.println(scanner.nextLine());
        }
    }

    public static void generateReport(REPORT_TYPE type) throws Exception {
    }
}
