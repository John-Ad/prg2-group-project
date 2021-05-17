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

public class TransactionGenerator {
    private static Client getClient() {
        String email = "";

        System.out.print("Enter customer email: ");
        email = TextReadWrite.getScanner().nextLine();

        HashMap<String, Client> map = (HashMap<String, Client>) Storage.readObjects(STORAGE_TYPE.GET_CUST);
        if (map.containsKey(email)) {
            return map.get(email);
        }

        Client client = (Client) UserFactory.getUser(USER_TYPE.CLIENT);
        map.put(client.getEmail(), client);
        Storage.writeObjects(STORAGE_TYPE.STORE_CUST, map);

        return client;
    }

    private static ArrayList<Product> getItems() throws NoItemsSelectedException {
        String name;
        Scanner scanner = TextReadWrite.getScanner();
        HashMap<String, Product> map = (HashMap<String, Product>) Storage.readObjects(STORAGE_TYPE.GET_PROD);
        ArrayList<Product> items = new ArrayList<Product>();

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

        if (items.size() == 0) {
            throw new NoItemsSelectedException();
        }

        return items;
    }

    private static double getAmount(ArrayList<Product> items) {
        double sum = 0;
        for (Product item : items) {
            sum += item.getPrice();
        }
        return sum;
    }

    public static Transaction getTran(TRAN_TYPE type) {
        try {
            Transaction transaction = new Transaction();

            transaction.setClient(getClient());
            transaction.setItems(getItems());
            transaction.setAmount(
                    (type == TRAN_TYPE.SALE) ? getAmount(transaction.getItems()) : -getAmount(transaction.getItems())
            );

            return transaction;
        } catch (NoItemsSelectedException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
