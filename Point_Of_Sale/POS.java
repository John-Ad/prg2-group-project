package Point_Of_Sale;

import Point_Of_Sale.Events.EVENT_TYPE;
import Point_Of_Sale.Events.Event;
import Point_Of_Sale.Events.EventFactory;
import Point_Of_Sale.Events.ReportEvent;
import Point_Of_Sale.Events.StorageEvent;
import Point_Of_Sale.Events.TransactionEvent;
import Point_Of_Sale.Products.PRODUCT_TYPE;
import Point_Of_Sale.Products.Product;
import Point_Of_Sale.Products.ProductFactory;
import Point_Of_Sale.Reports.REPORT_TYPE;
import Point_Of_Sale.Reports.Report;
import Point_Of_Sale.Storage.STORAGE_TYPE;
import Point_Of_Sale.Storage.Storage;
import Point_Of_Sale.Transactions.TRAN_TYPE;
import Point_Of_Sale.Transactions.TransactionGenerator;
import Point_Of_Sale.Users.*;
import Point_Of_Sale.TextReadWrite;
import Point_Of_Sale.Events.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// "Choose an option (number)\n1.\tTransaction\n2.\tStock management\n3.\tCustomer management\n4.\tAccount management\n5.\tEmployee management\n6.\tExit\noption:\t");

// enums
enum MENUS {
    MAIN, TRAN, STOCK_MNG, CUST_MNG, EMP_MNG, REPORTS, EXIT;

    public static int toInt(MENUS type) {
        switch (type) {
        case MAIN:
            return 0;
        case TRAN:
            return 1;
        case STOCK_MNG:
            return 2;
        case CUST_MNG:
            return 3;
        case EMP_MNG:
            return 4;
        case REPORTS:
            return 5;
        case EXIT:
            return 999;
        default:
            return 0;
        }
    }

    public static MENUS fromInt(int opt) {
        switch (opt) {
        case 0:
            return MAIN;
        case 1:
            return TRAN;
        case 2:
            return STOCK_MNG;
        case 3:
            return CUST_MNG;
        case 4:
            return EMP_MNG;
        case 5:
            return REPORTS;
        case 999:
            return EXIT;
        default:
            return null;
        }
    }
}

public class POS implements Runnable {

    private static final String menus[] = {
            "Choose an option (number)\n1.\tTransaction\n2.\tStock management\n3.\tCustomer management\n4.\tEmployee management\n5.\tReport management\n6.\tExit\noption:\t",
            "choose an option (number)\n1.\tSale\n2.\tRefund\n3.\tBack\nOption: ",
            "choose an option (number)\n1.\tAdd Stock\n2.\tRemove Stock\n3.\tSearch Stock\n4.\tIncrease stock qty\n5.\tBack\noption:\t",
            "choose an option (number)\n1.\tAdd Customer\n2.\tRemove Customer\n3.\tSearch Customer\n4.\tBack\noption:\t",
            "choose an option (number)\n1.\tAdd Employee\n2.\tRemove Employee\n3.\tSearch Employee\n4.\tBack\noption:\t",
            "choose an option (number)\n1.\tCreate Items Report\n2.\tCreate Transfers Report\n3.\tCreate Customers Report\n4.\tRead Items Report\n5.\tRead Transfers Report\n6.\tRead Customers Report\n7.\tBack\noption:\t" };
    private static final ArrayList<HashMap<String, Integer>> optMaps = new ArrayList<>(Arrays.asList(initOptMaps(6),
            initOptMaps(3), initOptMaps(5), initOptMaps(4), initOptMaps(4), initOptMaps(7)));

    private static HashMap<String, Integer> initOptMaps(int size) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 1; i < size; i++) {
            map.put(Integer.toString(i), i);
        }
        map.put(Integer.toString(size), MENUS.toInt(MENUS.EXIT));
        return map;
    }

    private boolean running;
    private Thread main;

    public POS() {
        running = true;
        main = new Thread(this);
        main.start();
    }

    private void eventHandler(Event event) {
        switch (event.evType) {
        case TRANS:
            TransactionEvent sale = (TransactionEvent) event;
            if (sale.transaction != null) {
                if (sale.transaction.getClient().getCard().checkPin()) {
                    if (sale.transaction.getAmount() < 0) { //if the amount is negative then the client gets a refund
                        sale.transaction.getClient().getAcc().credit(-sale.transaction.getAmount());
                    } else {
                        sale.transaction.getClient().getAcc().debit(sale.transaction.getAmount());
                    }
                    Storage.addObject(STORAGE_TYPE.STORE_TRAN, sale.transaction);
                }
            }
            break;
        case STORAGE:
            StorageEvent store = (StorageEvent) event;

            // cust storage
            switch (store.storageType) {
            //  customer storage
            case STORE_CUST:    // add cust
                Client addCust = (Client) UserFactory.getUser(USER_TYPE.CLIENT);
                if (addCust != null) {
                    Storage.addObject(store.storageType, addCust);
                }
                break;
            case FIND_CUST:     // find cust
                System.out.println("Enter email: ");
                Client fClient = (Client) Storage.findObject(store.storageType, TextReadWrite.getScanner().nextLine());
                if (fClient != null) {
                    System.out.println(fClient.toString());
                } else {
                    System.out.println("Customer not found");
                }
                break;
            case REM_CUST:      // remove cust
                System.out.println("Enter email: ");
                Storage.remObject(store.storageType, TextReadWrite.getScanner().nextLine());
                break;
            // ----------------------

            // product storage
            case STORE_PROD:        // add prod
                int opt;
                // loop until valid input entered
                while (true) {
                    System.out.println("Perishable (1) or nonperishable(2)");
                    if ((opt = NumberConversion
                            .toInt(TextReadWrite.getScanner().nextLine())) != NumberConversion.ERROR) {
                        break;
                    } else {
                        System.out.println("Invalid input!");
                    }
                }
                Product addProd = (Product) ProductFactory.getProduct(PRODUCT_TYPE.fromInt(opt));
                if (addProd != null) {
                    Storage.addObject(store.storageType, addProd);
                }
                break;
            case FIND_PROD:     // find prod
                System.out.println("Enter product name: ");
                Product fProd = (Product) Storage.findObject(store.storageType, TextReadWrite.getScanner().nextLine());
                if (fProd != null) {
                    System.out.println(fProd.toString());
                } else {
                    System.out.println("product not found");
                }
                break;
            case REM_PROD:      // remove prod
                System.out.println("Enter product name: ");
                Storage.remObject(store.storageType, TextReadWrite.getScanner().nextLine());
                break;
            case INC_PROD:      // increase product quantity
                System.out.println("Enter product name: ");
                Product incProd = (Product) Storage.findObject(STORAGE_TYPE.FIND_PROD, TextReadWrite.getScanner().nextLine());
                if (incProd == null) {
                    System.out.println("product not found");
                    return;
                }
                String input;
                int qty;
                // loop until valid input provided
                while (true) {
                    System.out.println("Number of items to add: ");
                    input = TextReadWrite.getScanner().nextLine();
                    if ((qty = NumberConversion.toInt(input)) != NumberConversion.ERROR) {
                        break;
                    } else {
                        System.out.println("invalid input");
                    }
                }
                qty+=incProd.getQty();
                incProd.setQty(qty);
                // first remove old object
                Storage.remObject(STORAGE_TYPE.REM_PROD, incProd.getName());
                //  save updated object
                Storage.addObject(STORAGE_TYPE.STORE_PROD, incProd);

                break;
            // -------------------------

            // employee storage
            case STORE_EMP: // add emp
                Employee addEmp = (Employee) UserFactory.getUser(USER_TYPE.EMPLOYEE);
                if (addEmp != null) {
                    Storage.addObject(store.storageType, addEmp);
                }
                break;
            case FIND_EMP:  //find emp
                System.out.println("Enter email: ");
                Employee fEmp = (Employee) Storage.findObject(store.storageType, TextReadWrite.getScanner().nextLine());
                if (fEmp != null) {
                    System.out.println(fEmp.toString());
                } else {
                    System.out.println("Employee not found");
                }
                break;
            case REM_EMP:   //remove emp
                System.out.println("Enter email: ");
                Storage.remObject(store.storageType, TextReadWrite.getScanner().nextLine());
                break;
            // -------------------------
            }
            break;
        case REPORT:
            ReportEvent rep = (ReportEvent) event;
            rep.report = new Report(rep.reportType);

            try {
                // read report or create new report depending on report type
                if (rep.reportType == REPORT_TYPE.READ_CUSTOMERS || rep.reportType == REPORT_TYPE.READ_ITEMS
                        || rep.reportType == REPORT_TYPE.READ_TRANSFERS) {
                    System.out.print(rep.report.getOldReport());
                } else {
                    rep.report.createNewReport();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            break;
        }
    }

    @Override
    public void run() { //main worker thread
        ArrayList<Product> lowStock=null;

        //  executor service will execute a single thread which checks stock
        ExecutorService exServ = Executors.newSingleThreadExecutor();
        Future f=exServ.submit(new StockChecker()); // result will be saved in f
;
        Scanner scanner = TextReadWrite.getScanner();
        ArrayList<Integer> menuList = new ArrayList<>(); //keeps track of menus
        String input;
        int currentMenu = MENUS.toInt(MENUS.MAIN); // staring menu to main
        int option;

        while (running) {

            //  check if stockchecker found low stock, then restart it
            try{
                if (f.isDone()) {
                    lowStock = (ArrayList<Product>) f.get();
                    f = exServ.submit(new StockChecker());
                }
            } catch (Exception ex) {
                System.out.println("checking stock");
            }

            // if there was low stock display it
            if (lowStock != null && lowStock.size() > 0) {
                System.out.println("\t" + lowStock.size() + " item(s) with low stock:");
                for (Product p : lowStock) {
                    System.out.println(p.toString());
                }
            }

            //  if current menu is main menu add chosen menu to menu list
            if (menuList.size() == 0 || menuList.get(menuList.size() - 1) != currentMenu) {
                menuList.add(currentMenu); // add menu to end of array
            }

            System.out.print(menus[currentMenu]); // display current menu 

            input = scanner.nextLine();

            if (optMaps.get(currentMenu).containsKey(input)) {      // check if input is valid
                if (MENUS.fromInt(optMaps.get(currentMenu).get(input)) == MENUS.EXIT) {     // check if user chose to exit/go back
                    if (menuList.size() > 1) {      // if size > 1 then go to previous menu
                        menuList.remove(menuList.size() - 1);   // remove current menu from list 
                        currentMenu = menuList.get(menuList.size() - 1);    // set current menu to prev menu in list
                    } else {        // exit application
                        running = false;
                        f.cancel(true);         // cancel task
                        f = null;
                    }
                } else {    // user was chose an option other than EXIT in current menu
                    if (currentMenu == MENUS.toInt(MENUS.MAIN)) { // if main menu, change menu
                        currentMenu = optMaps.get(currentMenu).get(input);
                    } else { // start event
                        Event event = null;

                        //  each case represents a different event taking place
                        switch (MENUS.fromInt(currentMenu)) {
                        case TRAN:      // transaction eg sale refund
                            TransactionEvent eventT = (TransactionEvent) EventFactory.getEvent(EVENT_TYPE.TRANS);
                            eventT.tranType = TRAN_TYPE.fromInt(optMaps.get(currentMenu).get(input));
                            eventT.transaction = TransactionGenerator.getTran(eventT.tranType);
                            event = eventT;
                            break;
                        case STOCK_MNG:     // goes to stock management options
                            StorageEvent eventS = (StorageEvent) EventFactory.getEvent(EVENT_TYPE.STORAGE);
                            eventS.storageType = STORAGE_TYPE.prodFromInt(optMaps.get(currentMenu).get(input));
                            event = eventS;
                            break;
                        case CUST_MNG:      // goes to customer management options
                            StorageEvent eventC = (StorageEvent) EventFactory.getEvent(EVENT_TYPE.STORAGE);
                            eventC.storageType = STORAGE_TYPE.custFromInt(optMaps.get(currentMenu).get(input));
                            event = eventC;
                            break;
                        case EMP_MNG:       // goes to employee management options
                            StorageEvent eventE = (StorageEvent) EventFactory.getEvent(EVENT_TYPE.STORAGE);
                            eventE.storageType = STORAGE_TYPE.empFromInt(optMaps.get(currentMenu).get(input));
                            event = eventE;
                            break;
                        case REPORTS:       // goes to report management options
                            ReportEvent eventR = (ReportEvent) EventFactory.getEvent(EVENT_TYPE.REPORT);
                            eventR.reportType = REPORT_TYPE.fromInt(optMaps.get(currentMenu).get(input));
                            event = eventR;
                            break;
                        default:
                            break;
                        }

                        eventHandler(event);        // pass event to event handler
                    }
                }
            } else {
                System.out.print("invalid Input!\n");
            }
        }
    }
}
