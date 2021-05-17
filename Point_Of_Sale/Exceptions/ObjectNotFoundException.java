package Point_Of_Sale.Exceptions;

public class ObjectNotFoundException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 9117240159297041848L;

    public ObjectNotFoundException() {
        super("Low stock detected");
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }
}
