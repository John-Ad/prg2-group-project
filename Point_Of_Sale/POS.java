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
            if (sale.transaction.getClient().getCard().checkPin()) {
                if (sale.transaction.getAmount() < 0) { //if the amount is negative then the client gets a refund
                    sale.transaction.getClient().getAcc().credit(-sale.transaction.getAmount());
                } else {
                    sale.transaction.getClient().getAcc().debit(sale.transaction.getAmount());
                }
                Storage.addObject(STORAGE_TYPE.STORE_TRAN, sale.transaction);
            }
            break;
        case STORAGE:
            StorageEvent store = (StorageEvent) event;
            switch (store.storageType) {
            //  customer storage
            case STORE_CUST:
                Client addCust = (Client) UserFactory.getUser(USER_TYPE.CLIENT);
                if (addCust != null) {
                    Storage.addObject(store.storageType, addCust);
                }
                break;
            case FIND_CUST:
                System.out.println("Enter email: ");
                Client fClient = (Client) Storage.findObject(store.storageType, TextReadWrite.getScanner().nextLine());
                if (fClient != null) {
                    System.out.println(fClient.toString());
                } else {
                    System.out.println("Customer not found");
                }
                break;
            case REM_CUST:
                System.out.println("Enter email: ");
                Storage.remObject(store.storageType, TextReadWrite.getScanner().nextLine());
                break;
            // ----------------------

            // product storage
            case STORE_PROD:
                int opt;
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
            case FIND_PROD:
                System.out.println("Enter product name: ");
                Product fProd = (Product) Storage.findObject(store.storageType, TextReadWrite.getScanner().nextLine());
                if (fProd != null) {
                    System.out.println(fProd.toString());
                } else {
                    System.out.println("product not found");
                }
                break;
            case REM_PROD:
                System.out.println("Enter product name: ");
                Storage.remObject(store.storageType, TextReadWrite.getScanner().nextLine());
                break;
            case INC_PROD:
                System.out.println("Enter product name: ");
                Product incProd = (Product) Storage.findObject(STORAGE_TYPE.FIND_PROD, TextReadWrite.getScanner().nextLine());
                if (incProd == null) {
                    System.out.println("product not found");
                    return;
                }
                String input;
                int qty;
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
                Storage.remObject(STORAGE_TYPE.REM_PROD, incProd.getName());
                Storage.addObject(STORAGE_TYPE.STORE_PROD, incProd);

                break;
            // -------------------------

            // employee storage
            case STORE_EMP:
                Employee addEmp = (Employee) UserFactory.getUser(USER_TYPE.EMPLOYEE);
                if (addEmp != null) {
                    Storage.addObject(store.storageType, addEmp);
                }
                break;
            case FIND_EMP:
                System.out.println("Enter email: ");
                Employee fEmp = (Employee) Storage.findObject(store.storageType, TextReadWrite.getScanner().nextLine());
                if (fEmp != null) {
                    System.out.println(fEmp.toString());
                } else {
                    System.out.println("Employee not found");
                }
                break;
            case REM_EMP:
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
        Scanner scanner = TextReadWrite.getScanner();
        ArrayList<Integer> menuList = new ArrayList<>(); //keeps track of menus
        String input;
        int currentMenu = MENUS.toInt(MENUS.MAIN); // staring menu to main
        int option;

        while (running) {

            if (menuList.size() == 0 || menuList.get(menuList.size() - 1) != currentMenu) {
                menuList.add(currentMenu); // add menu to end of array
            }

            System.out.print(menus[currentMenu]); // display current menu 

            input = scanner.nextLine();

            if (optMaps.get(currentMenu).containsKey(input)) {      // check if input is valid
                if (MENUS.fromInt(optMaps.get(currentMenu).get(input)) == MENUS.EXIT) {     // check if user choose to exit/go back
                    if (menuList.size() > 1) {      // if size > 1 then go to previous menu
                        menuList.remove(menuList.size() - 1);
                        currentMenu = menuList.get(menuList.size() - 1);
                    } else {        // exit application
                        running = false;
                    }
                } else {
                    if (currentMenu == MENUS.toInt(MENUS.MAIN)) { // if main menu, change menu
                        currentMenu = optMaps.get(currentMenu).get(input);
                    } else { // start event
                        Event event = null;

                        //  each case represents a different event taking place
                        switch (MENUS.fromInt(currentMenu)) {
                        case TRAN:
                            TransactionEvent eventT = (TransactionEvent) EventFactory.getEvent(EVENT_TYPE.TRANS);
                            eventT.tranType = TRAN_TYPE.fromInt(optMaps.get(currentMenu).get(input));
                            eventT.transaction = TransactionGenerator.getTran(eventT.tranType);
                            event = eventT;
                            break;
                        case STOCK_MNG:
                            StorageEvent eventS = (StorageEvent) EventFactory.getEvent(EVENT_TYPE.STORAGE);
                            eventS.storageType = STORAGE_TYPE.prodFromInt(optMaps.get(currentMenu).get(input));
                            event = eventS;
                            break;
                        case CUST_MNG:
                            StorageEvent eventC = (StorageEvent) EventFactory.getEvent(EVENT_TYPE.STORAGE);
                            eventC.storageType = STORAGE_TYPE.custFromInt(optMaps.get(currentMenu).get(input));
                            event = eventC;
                            break;
                        case EMP_MNG:
                            StorageEvent eventE = (StorageEvent) EventFactory.getEvent(EVENT_TYPE.STORAGE);
                            eventE.storageType = STORAGE_TYPE.empFromInt(optMaps.get(currentMenu).get(input));
                            event = eventE;
                            break;
                        case REPORTS:
                            ReportEvent eventR = (ReportEvent) EventFactory.getEvent(EVENT_TYPE.REPORT);
                            eventR.reportType = REPORT_TYPE.fromInt(optMaps.get(currentMenu).get(input));
                            event = eventR;
                            break;
                        default:
                            break;
                        }

                        eventHandler(event);
                    }
                }
            } else {
                System.out.print("invalid Input!\n");
            }
        }
    }
}
