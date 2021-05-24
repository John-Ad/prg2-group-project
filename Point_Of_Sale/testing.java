package Point_Of_Sale;

import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import Point_Of_Sale.Events.EVENT_TYPE;
import Point_Of_Sale.Events.Event;
import Point_Of_Sale.Events.EventFactory;
import Point_Of_Sale.Events.ReportEvent;
import Point_Of_Sale.Products.PRODUCT_TYPE;
import Point_Of_Sale.Products.Product;
import Point_Of_Sale.Products.ProductBuilder;
import Point_Of_Sale.Products.ProductFactory;
import Point_Of_Sale.Reports.REPORT_TYPE;
import Point_Of_Sale.Reports.Report;
import Point_Of_Sale.Storage.STORAGE_TYPE;
import Point_Of_Sale.Storage.Storage;
import Point_Of_Sale.Transactions.TRAN_TYPE;
import Point_Of_Sale.Transactions.Transaction;
import Point_Of_Sale.Transactions.TransactionGenerator;
import Point_Of_Sale.Users.Client;
import Point_Of_Sale.Users.Employee;
import Point_Of_Sale.Users.USER_TYPE;
import Point_Of_Sale.Users.UserBuilder;
import Point_Of_Sale.Users.UserFactory;

public class testing {
    public static void main(String args[]) {

        /*
        HashMap<String, Employee> map = new HashMap<String, Employee>();
        Employee emp = UserBuilder.buildEmployee();
        Storage.writeObjects(STORAGE_TYPE.STORE_EMP, map);


        Product p = ProductBuilder.buildNonPerProd();
        HashMap<String, Product> map = new HashMap<String, Product>();
        map.put(p.getName(), p);

        Storage.writeObjects(STORAGE_TYPE.STORE_PROD, map);

        ExecutorService exServ = Executors.newSingleThreadExecutor();
        Future f = exServ.submit(new StockChecker());

        
        while (true) {
            if (f.isDone()) {
                try {
                    ArrayList<Product> p = (ArrayList<Product>) f.get();
                    System.out.print(p.get(0).getName());
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }

                f = exServ.submit(new StockChecker());
                break;
            }
        }
        */
        
         /*
        Product p = ProductFactory.getProduct(PRODUCT_TYPE.NON_PERISHABLE);
        Storage.addObject(STORAGE_TYPE.STORE_PROD, p);
        */       
        /* Storage and Report testing
        for (int i = 0; i < 1; i++) {
            Transaction t = TransactionGenerator.getTran(TRAN_TYPE.REFUND);
            Storage.addObject(STORAGE_TYPE.STORE_TRAN, t);
        }

        for (int i = 0; i < 2; i++) {
            Client c = (Client) UserFactory.getUser(USER_TYPE.CLIENT);
            Storage.addObject(STORAGE_TYPE.STORE_CUST, c);
        }

        ReportEvent ev = (ReportEvent) EventFactory.getEvent(EVENT_TYPE.REPORT);
        ev.report = new Report(REPORT_TYPE.ITEMS);
        try {
            ev.report.createNewReport();
            System.out.print(ev.report.getOldReport());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        */
    }
}
