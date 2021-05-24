package Point_Of_Sale.Users;

//  returns subclass of user depending on type provided
public class UserFactory {
    public static User getUser(USER_TYPE type) {
        switch (type) {
        case CLIENT:
            return UserBuilder.buildClient();
        case EMPLOYEE:
            return UserBuilder.buildEmployee();
        default:
            return null;
        }
    }
}
