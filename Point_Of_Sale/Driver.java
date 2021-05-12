package Point_Of_Sale;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import Point_Of_Sale.POS;
import Point_Of_Sale.DB.DBConnection;
import Point_Of_Sale.Exceptions.LowStockException;
import Point_Of_Sale.Reports.REPORT_TYPE;

public class Driver {
    public static void main(String args[]) throws FileNotFoundException {
        ExecutorService exServ = Executors.newSingleThreadExecutor();
        Future f = exServ.submit(new StockChecker());

        try {
            f.get();
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

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