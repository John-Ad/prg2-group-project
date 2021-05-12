package Point_Of_Sale;

// java imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// pos imports
import Point_Of_Sale.Reports.REPORT_TYPE;

public class ScannerAccess {            //singleton class for Scanner access
    private static Scanner userScanner;
    private static Scanner fileScanner;

    public static Scanner getScanner() {
        if (userScanner == null) {
            userScanner = new Scanner(System.in);
        }
        return userScanner;
    }

    public static Scanner getScanner(REPORT_TYPE file) throws FileNotFoundException {
        if (userScanner == null) {
            userScanner = new Scanner(new File(REPORT_TYPE.getFileName(file)));
        }
        return userScanner;
    }
}
