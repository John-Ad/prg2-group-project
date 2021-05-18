package Point_Of_Sale;

import Point_Of_Sale.Events.EVENT_TYPE;
import Point_Of_Sale.Events.Event;
import Point_Of_Sale.Events.EventFactory;
import Point_Of_Sale.Events.TransactionEvent;
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
        System.out.println(((TransactionEvent)event).tranType);
        
       
        
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
                    Event event= EventFactory.getEvent(EVENT_TYPE.TRANS);
                    eventHandler(event);
                    running= false;
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




