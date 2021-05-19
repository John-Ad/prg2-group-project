package Point_Of_Sale;

import Point_Of_Sale.Events.EVENT_TYPE;
import Point_Of_Sale.Events.Event;
import Point_Of_Sale.Events.EventFactory;
import Point_Of_Sale.Events.StorageEvent;
import Point_Of_Sale.Events.TransactionEvent;
import Point_Of_Sale.Products.PRODUCT_TYPE;
import Point_Of_Sale.Products.Product;
import Point_Of_Sale.Products.ProductFactory;
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
    MAIN, TRAN, STOCK_MNG, CUST_MNG, ACC_MNG, EMP_MNG, REPORTS;

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
        default:
            return null;
        }
    }
}

public class POS implements Runnable {
    private static final String menus[] = {
            "Choose an option (number)\n1.\tTransaction\n2.\tStock management\n3.\tCustomer management\n4.\tEmployee management\n5.\tReport management\n6.\tExit\noption:\t",
            "choose an option (number)\n1.\tSale\n2.\tRefund: ",
            "choose an option (number)\n1.\tAdd Stock\n2.\tRemove Stock\n3.\tSearch Stock\n4.\tBack\noption:\t",
            "choose an option (number)\n1.\tAdd Customer\n2.\tRemove Customer\n3.\tSearch Customer\n4.\tBack\noption:\t", 
            "choose an option (number)\n1.\tAdd Employee\n2.\tRemove Employee\n3.\tSearch Employee\n4.\tBack\noption:\t" };
    private static final ArrayList<HashMap<String, Integer>> optMaps = new ArrayList<>(
            Arrays.asList(initOptMaps(6), initOptMaps(2), initOptMaps(4), initOptMaps(4), initOptMaps(4)));

    private static HashMap<String, Integer> initOptMaps(int size) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 1; i <= size; i++) {
            map.put(Integer.toString(i), i);
        }
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
                Storage.addObject(store.storageType, (Client) UserFactory.getUser(USER_TYPE.CLIENT));
                break;
            case FIND_CUST:
                System.out.println("Enter email: ");
                Client fClient = (Client) Storage.findObject(store.storageType, TextReadWrite.getScanner().nextLine());
                if (fClient != null) {
                    System.out.println(fClient.toString());
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
                Storage.addObject(store.storageType, ProductFactory.getProduct(PRODUCT_TYPE.fromInt(opt)));
                break;
            case FIND_PROD:
                System.out.println("Enter product name: ");
                Product fProd = (Product) Storage.findObject(store.storageType, TextReadWrite.getScanner().nextLine());
                if (fProd != null) {
                    System.out.println(fProd.toString());
                }
                break;
            case REM_PROD:
                System.out.println("Enter product name: ");
                Storage.remObject(store.storageType, TextReadWrite.getScanner().nextLine());
                break;
            // -------------------------

            // employee storage
            case STORE_EMP:
                Storage.addObject(store.storageType, (Employee) UserFactory.getUser(USER_TYPE.EMPLOYEE));
                break;
            case FIND_EMP:
                System.out.println("Enter email: ");
                Employee fEmp = (Employee) Storage.findObject(store.storageType, TextReadWrite.getScanner().nextLine());
                if (fEmp != null) {
                    System.out.println(fEmp.toString());
                }
                break;
            case REM_EMP:
                System.out.println("Enter email: ");
                Storage.remObject(store.storageType, TextReadWrite.getScanner().nextLine());
                break;
            // -------------------------
            }
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

            input = TextReadWrite.getScanner().nextLine();

            if (optMaps.get(currentMenu).containsKey(input)) {
                if (currentMenu == MENUS.toInt(MENUS.MAIN)) { // if main menu, change menu
                    if (optMaps.get(currentMenu).get(input) == 7) { // exit application
                        return;
                    }
                    currentMenu = optMaps.get(currentMenu).get(input);
                } else { // start event
                    Event event = null;

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
                    }

                    eventHandler(event);
                }
            } else {
                System.out.print("invalid Input");
            }
        }
    }
}
