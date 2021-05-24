package Point_Of_Sale.Users;

import java.util.ArrayList;
import java.util.Arrays;

import Point_Of_Sale.Account;
import Point_Of_Sale.NumberConversion;
import Point_Of_Sale.TextReadWrite;
import Point_Of_Sale.Storage.STORAGE_TYPE;
import Point_Of_Sale.Storage.Storage;
import Point_Of_Sale.Users.Client;
import Point_Of_Sale.Users.Employee;

public class UserBuilder {
    private static ArrayList<String> buildUserDetails() {   // obtains values common to all users
        String uID = Integer.toString((int) Math.floor(Math.random() * 90000) + 10000); //random 5 digit value
        String name, email;

        System.out.print("enter name: ");
        name = TextReadWrite.getScanner().nextLine();

        System.out.print("\nenter email: ");
        email = TextReadWrite.getScanner().nextLine();

        if (Storage.findObject(STORAGE_TYPE.FIND_CUST, email) != null
                || Storage.findObject(STORAGE_TYPE.FIND_EMP, email) != null) {
            System.out.println("user with that email address already exists!");
            return null;
        }

        return new ArrayList<>(Arrays.asList(uID, name, email));    //return array of user values
    }

    public static Client buildClient() {
        //buid account and card     //account class handles Card construction
        String accID = Long.toString((long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L); //random 10 digit value
        Account account = new Account(accID, 0);

        //obtain general data; build and return client
        ArrayList<String> uData = buildUserDetails();
        if (uData == null) {
            return null;
        }

        return new Client(uData.get(0), uData.get(1), uData.get(2), account);
    }

    public static Employee buildEmployee() {
        //obtain general data
        ArrayList<String> uData = buildUserDetails();
        if (uData == null) {
            return null;
        }

        //obtain employee specific data
        String input, reg;
        double salary;

        System.out.print("enter tax number: ");
        reg = TextReadWrite.getScanner().nextLine();
        
        while (true) {
            System.out.print("enter salary: ");
            if ((salary = NumberConversion.toDouble(TextReadWrite.getScanner().nextLine())) != NumberConversion.ERROR) {
                return new Employee(uData.get(0), uData.get(1), uData.get(2), salary, reg);
            } else {
                System.out.print("Invalid number entered: ");
            }
        }
    }
}
