package Point_Of_Sale;

import java.util.Scanner;

public class ScannerAccess {            //singleton class for Scanner access
    private static Scanner scanner;

    public static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
