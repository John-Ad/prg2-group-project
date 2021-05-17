package Point_Of_Sale.Exceptions;

public class ObjectAlreadyExistsException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 6137972686884928592L;

    public ObjectAlreadyExistsException() {
        super("Low stock detected");
    }

    public ObjectAlreadyExistsException(String message) {
        super(message);
    }
}
