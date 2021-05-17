package Point_Of_Sale;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import Point_Of_Sale.*;
import Point_Of_Sale.Events.Event;
import Point_Of_Sale.Events.EventFactory;
import Point_Of_Sale.Products.NonPerishableProduct;
import Point_Of_Sale.Products.PRODUCT_TYPE;
import Point_Of_Sale.Products.PerishableProduct;
import Point_Of_Sale.Products.Product;
import Point_Of_Sale.Products.ProductFactory;
import Point_Of_Sale.Storage.STORAGE_TYPE;
import Point_Of_Sale.Storage.Storage;
import Point_Of_Sale.Transactions.TRAN_TYPE;
import Point_Of_Sale.Users.Client;
import Point_Of_Sale.Users.USER_TYPE;
import Point_Of_Sale.Users.UserFactory;

public class Driver {
    public static void main(String args[]) throws FileNotFoundException {

        POS pos = new POS();

        //Event ev = EventFactory.getEvent(TRAN_TYPE.SALE);

        /* remove old clients stored based on email+name and replace with clients stored based on email
        HashMap<String, Client> map = new HashMap<String, Client>();
        Client cli = (Client) UserFactory.getUser(USER_TYPE.CLIENT);
        map.put(cli.getEmail(), cli);
        Storage.writeObjects(STORAGE_TYPE.STORE_CUST, map);
        */


        /* test retrieval of test objects
        HashMap<String, Product> map = (HashMap<String, Product>) Storage.readObjects(STORAGE_TYPE.GET_PROD);

        while(true){
            System.out.print("enter product name: ");
            if (map.containsKey(TextReadWrite.getScanner().nextLine())) {
                System.out.println("found");
            } else {
                System.out.println("not found");
                break;
            }
        }
        */


        /* add test products
        HashMap<String, Product> map = new HashMap<String, Product>();

        Product prod;
        for (int i = 0; i < 3; i++) {
            prod = ProductFactory.getProduct(PRODUCT_TYPE.PERISHABLE);
            map.put(prod.getName(), prod);
        }
        Storage.writeObjects(STORAGE_TYPE.STORE_PROD, map);
        */

        /*
        HashMap<String, Client> map = (HashMap<String, Client>) Storage.readObjects(STORAGE_TYPE.STORE_CUST);

        //System.out.print(map.get("sdfsdf").toString());

        Iterator iterator = map.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry m = (Map.Entry) iterator.next();
            Client cl = (Client)m.getValue();
            System.out.println(cl.toString());
        }
        */

        /* convert arraylist storage to map storage
        HashMap<String, Client> map = new HashMap<String, Client>();

        ArrayList<Client> clients = (ArrayList<Client>) Storage.readObjects(STORAGE_TYPE.STORE_CUST);

        for (Client cli : clients) {
            map.put(cli.getEmail() + cli.getName(), cli);
        }
        System.out.print(map.get(clients.get(0).getEmail() + clients.get(0).getName()).toString());

        Storage.writeObjects(STORAGE_TYPE.STORE_CUST, map);
        */
        /*
        ArrayList<Client> clients = (ArrayList<Client>) Storage.readObjects(STORAGE_TYPE.STORE_CUST);

        System.out.print(clients.get(1).toString());
        */

        /*  Storage write testing
        ArrayList<Client> clients = new ArrayList<Client>();

        clients.add((Client) UserFactory.getUser(USER_TYPE.CLIENT));
        clients.add((Client) UserFactory.getUser(USER_TYPE.CLIENT));
        clients.add((Client) UserFactory.getUser(USER_TYPE.CLIENT));

        Storage.writeObjects(STORAGE_TYPE.STORE_CUST, clients);
        */


        /* builder tester
        PerishableProduct prod = (PerishableProduct) ProductFactory.getProduct(PRODUCT_TYPE.PERISHABLE);

        System.out.print(prod);
        */
        /*      thread checking
        ExecutorService exServ = Executors.newSingleThreadExecutor();
        Future f = exServ.submit(new StockChecker());

        try {
            f.get();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
        */

        /*
        Scanner scanner = ScannerAccess.getScanner(REPORT_TYPE.EARNINGS);
        
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
        */
        //POS pos = new POS();

        /* DB testing
        try {
            System.out.println(DBConnection.query("select * from Test;").getString("name"));
            System.out.println(DBConnection.query("select name from Test;").getDouble("salary"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
        /*  Card testing
        Card card = new Card("0123");
        card.checkPin();
        */
        /*
        try {
            Scanner scanner = new Scanner(new File("test.txt"));
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        */
    }
}
