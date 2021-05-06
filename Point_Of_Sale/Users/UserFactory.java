package Point_Of_Sale.Users;

public class UserFactory {
    public static User getUser(USER_TYPE type) {
        switch (type) {
        case CLIENT:
            return new Client();
        case EMPLOYEE:
            return new Employee();
        }
        return null;
    }
}
