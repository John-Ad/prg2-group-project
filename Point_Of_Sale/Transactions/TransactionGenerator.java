package Point_Of_Sale.Transactions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Point_Of_Sale.TextReadWrite;
import Point_Of_Sale.Exceptions.NoItemsSelectedException;
import Point_Of_Sale.Products.Product;
import Point_Of_Sale.Storage.STORAGE_TYPE;
import Point_Of_Sale.Storage.Storage;
import Point_Of_Sale.Users.Client;
import Point_Of_Sale.Users.USER_TYPE;
import Point_Of_Sale.Users.UserFactory;

//  gets user input and builds up transaction 
public class TransactionGenerator {
    //  either get existing client or create new client and return it
    private static Client getClient() {     
        String email = "";

        System.out.print("Enter customer email: ");
        email = TextReadWrite.getScanner().nextLine();

        // check if client exists and return it if it does
        HashMap<String, Client> map = (HashMap<String, Client>) Storage.readObjects(STORAGE_TYPE.GET_CUST);
        if (map.containsKey(email)) {
            return map.get(email);
        }

        // if not, create new client
        Client client = (Client) UserFactory.getUser(USER_TYPE.CLIENT);
        Storage.addObject(STORAGE_TYPE.STORE_CUST, client); // save client

        return client;
    }

    // choose items to add to transaction
    private static ArrayList<Product> getItems() throws NoItemsSelectedException {
        String name;
        Scanner scanner = TextReadWrite.getScanner();
        HashMap<String, Product> map = (HashMap<String, Product>) Storage.readObjects(STORAGE_TYPE.GET_PROD);
        ArrayList<Product> items = new ArrayList<Product>();

        // loop until done adding products
        while (true) {
            System.out.print("Enter product name (or 'done' to finish): ");
            name = scanner.nextLine();
            name = name.toLowerCase();

            if (name.compareTo("done") == 0) {
                break;
            }

            if (map.containsKey(name)) {
                items.add(map.get(name));
            } else {
                System.out.println("Product does not exist");
            }
        }

        if (items.size() == 0) {    // if now items selected throw error
            throw new NoItemsSelectedException();
        }

        return items;
    }

    //  calculate total price of products
    private static double getAmount(ArrayList<Product> items) {
        double sum = 0;
        for (Product item : items) {
            sum += item.getPrice();
        }
        return sum;
    }

    //  return a transaction built up with user input
    public static Transaction getTran(TRAN_TYPE type) {
        try {
            Transaction transaction = new Transaction();

            transaction.setClient(getClient());
            transaction.setItems(getItems());
            transaction.setAmount(      // set amout as negative if sale is actually a refund, 
                    (type == TRAN_TYPE.SALE) ? getAmount(transaction.getItems()) : -getAmount(transaction.getItems())
            );

            return transaction;
        } catch (NoItemsSelectedException ex) {     // handle the no items exception thrown in getItems()
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
