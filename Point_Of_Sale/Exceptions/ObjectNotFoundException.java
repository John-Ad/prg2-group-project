package Point_Of_Sale.Exceptions;

public class ObjectNotFoundException extends Exception {
    public ObjectNotFoundException() {
        super("Low stock detected");
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
