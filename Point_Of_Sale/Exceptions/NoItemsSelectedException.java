package Point_Of_Sale.Exceptions;

public class NoItemsSelectedException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 7993728455456948187L;

    public NoItemsSelectedException() {
        super("No items selected!! Cancelling transaction");
        }

    public NoItemsSelectedException(String message) {
        super(message);
    }
}
