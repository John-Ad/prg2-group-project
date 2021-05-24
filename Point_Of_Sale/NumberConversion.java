package Point_Of_Sale;

public class NumberConversion {     
    public static final int ERROR = -9999999;

    public static int toInt(String str) { // int conversion
        try {
            return Integer.parseInt(str);
        } catch (Exception ex) {
            return ERROR;
        }
    }

    public static double toDouble(String str) { // double conversion
        try {
            return Double.parseDouble(str);
        } catch (Exception ex) {
            return ERROR;
        }
    }

    public static float toFloat(String str) {   // float conversion
        try {
            return Float.parseFloat(str);
        } catch (Exception ex) {
            return ERROR;
        }
    }
}
