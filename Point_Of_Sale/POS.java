package Point_Of_Sale;

import Point_Of_Sale.Events.EVENT_TYPE;
import Point_Of_Sale.Events.Event;
import Point_Of_Sale.Events.EventFactory;
import Point_Of_Sale.Events.TransactionEvent;
import Point_Of_Sale.Storage.STORAGE_TYPE;
import Point_Of_Sale.Storage.Storage;
import Point_Of_Sale.Transactions.TRAN_TYPE;
import Point_Of_Sale.Transactions.TransactionGenerator;
import Point_Of_Sale.Users.*;
import Point_Of_Sale.TextReadWrite;
import Point_Of_Sale.Events.Event;

import java.util.Scanner;



/**
 *  event handler definitions
 */

// end of event definitions

public class POS implements Runnable {
    private boolean running;
    private Thread main;


    public POS() {
        running = true;
        main = new Thread(this);
        main.start();
    }

    private void eventHandler(Event event) {
        switch(event.evType) {
            case TRANS:
                TransactionEvent sale = (TransactionEvent) event;
                if(sale.transaction.getClient().getCard().checkPin()) {
                    if(sale.transaction.getAmount()<0) {                        //if the amount is negative then the client gets a refund
                        sale.transaction.getClient().getAcc().credit(-sale.transaction.getAmount());
                    } else {
                        sale.transaction.getClient().getAcc().debit(sale.transaction.getAmount());
                    }
                    Storage.addObject(STORAGE_TYPE.STORE_TRAN, sale.transaction);
                }
        }
        
       
        
    }

    @Override
    public void run() {                             //main worker thread
        //      testing

        //-------------------------------------------
        
        Scanner scanner = TextReadWrite.getScanner();
        String input;
        int option;



        while (running) {
            System.out.println("Choose an option (number)\n1.\tTransaction\n2.\tStock management\n3.\tCustomer management\n4.\tAccount management\n5.\tEmployee management\n6.\tExit\noption:\t");
            input = scanner.nextLine();
            int i=0;
            switch(NumberConversion.toInt(input)) {
                case NumberConversion.ERROR:
                    System.out.println("Invalid input");
                    break;
                case 1:
                TransactionEvent event = (TransactionEvent)EventFactory.getEvent(EVENT_TYPE.TRANS);
                event.evType=EVENT_TYPE.TRANS;
    
                for (boolean b = true; b == true;) {
                    System.out.println("1.\tSale\n2.\tRefund: ");
                    switch (NumberConversion.toInt(TextReadWrite.getScanner().nextLine())) {
                    case NumberConversion.ERROR:
                        System.out.println("Invalid input");
                        break;
                    case 1:
                        event.tranType = TRAN_TYPE.SALE;
                        b = false;
                        break;
                    case 2:
                        event.tranType = TRAN_TYPE.REFUND;
                        b = false;
                        break;
                    }
                }
    
                event.transaction = TransactionGenerator.getTran(event.tranType);
                eventHandler(event);
                    break;
                case 2:
                    while (i==0) { 
                        System.out.println("choose an option (number)\n1.\tAdd Stock\n2.\tRemove Stock\n3.\tSearch Stock\n4.\tRetrieve Stock\n5.\tCount\n6.\tExit\noptoin:\t");    //loop for option 2
                        input = scanner.nextLine();
                        switch(NumberConversion.toInt(input)) {
                            case NumberConversion.ERROR:
                                System.out.println("Invalid input");
                                break;
                            case 1:
                                running= false;
                                break;
                            case 2:
                                running= false;
                                break;
                            case 3:
                                running= false;
                                break;
                            case 4:
                                running= false;
                                break;
                            case 5:
                                running= false;
                                break;
                            case 6:
                                i=2;
                                break;
                        }
                    }
                case 3:
                    while (i==0) {
                        System.out.println("choose an option (number)\n1.\tAdd User\n2.\tRemove User\n3.\tSearch User\n4.\tRetrieve User\n5.\tCount User\n6.\t Exit\noption:\t");
                        input = scanner.nextLine();
                        switch(NumberConversion.toInt(input)) {
                            case NumberConversion.ERROR:
                            System.out.println("Invalid input");
                            break;
                        case 1:
        
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            i=2;
                            break;
                        }                        }
                    }   
                    i=0;
            }
        
        }
    }




