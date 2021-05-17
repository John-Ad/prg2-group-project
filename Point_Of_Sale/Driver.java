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
import Point_Of_Sale.Products.PRODUCT_TYPE;
import Point_Of_Sale.Products.PerishableProduct;
import Point_Of_Sale.Products.ProductFactory;
import Point_Of_Sale.Storage.STORAGE_TYPE;
import Point_Of_Sale.Storage.Storage;
import Point_Of_Sale.Users.Client;
import Point_Of_Sale.Users.USER_TYPE;
import Point_Of_Sale.Users.UserFactory;

public class Driver {
    public static void main(String args[]) throws FileNotFoundException {

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
