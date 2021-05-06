package Point_Of_Sale;

public class ErrorHandling {
    public static boolean checkIntFromString(String str){   // int error handling
        try{
            Integer.parseInt(str);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean checkDoubleFromString(String str) {   // double error handling
        try {
            Double.parseDouble(str);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
