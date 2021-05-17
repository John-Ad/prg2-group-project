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
        System.out.println(((TransactionEvent)event).tranType);1
        
       
        
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
            switch(NumberConversion.toInt(input)) {
                case NumberConversion.ERROR:
                    System.out.println("Invalid input");
                    break;
                case 1:
                    Event rg= EventFactory.getEvent(EVENT_TYPE.TRANS);
                    eventHandler(rg);
                    running= false;
                    break;
                case 2:
                    while (true) { 
                        System.out.println("choose an option (number)\n1.\tAdd Stock\n2.\tRemove Stock\n3.\tSearch Stock\n4.\tRetrieve Stock\n5.\noptoin:\t");    //loop for option 2
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
                        }
                    }
            }
        }
    }
}



