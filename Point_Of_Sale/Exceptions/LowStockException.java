package Point_Of_Sale.Exceptions;

public class LowStockException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1644671719984808504L;

    public LowStockException() {
        super("Low stock detected");
    }

    public LowStockException(String message) {
        super(message);
    }
}
