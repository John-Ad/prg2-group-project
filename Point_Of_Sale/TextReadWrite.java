package Point_Of_Sale;

// java imports
import java.util.Scanner;

public class TextReadWrite {            //singleton class for Scanner access
    private static Scanner userScanner; 

    public static Scanner getScanner() {
        if (userScanner == null) {              // if not yet created for first time, create new instance
            userScanner = new Scanner(System.in);
        }
        return userScanner;     // pass same instance to all callers
    }
}
