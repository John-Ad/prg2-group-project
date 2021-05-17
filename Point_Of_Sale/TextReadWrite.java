package Point_Of_Sale;

// java imports
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// pos imports
import Point_Of_Sale.Reports.REPORT_TYPE;

public class TextReadWrite {            //singleton class for Scanner access
    private static Scanner userScanner;

    public static Scanner getScanner() {
        if (userScanner == null) {
            userScanner = new Scanner(System.in);
        }
        return userScanner;
    }
}
