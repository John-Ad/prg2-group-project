package Point_Of_Sale.Exceptions;

public class ObjectAlreadyExistsException extends Exception {
    public ObjectAlreadyExistsException() {
        super("Low stock detected");
    }

    public ObjectAlreadyExistsException(String message) {
        super(message);
    }
}
