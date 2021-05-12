package Point_Of_Sale.Exceptions;

public class LowStockException extends Exception {
    public LowStockException() {
        super("Low stock detected");
    }
    public LowStockException(String message) {
        super(message);
    }
}
