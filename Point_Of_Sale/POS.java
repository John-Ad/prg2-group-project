package Point_Of_Sale;

import Point_Of_Sale.Events.EVENT_TYPE;
import Point_Of_Sale.Events.Event;
import Point_Of_Sale.Events.EventFactory;
import Point_Of_Sale.Users.*;

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

    private void eventHandler(Event ev) {
        
    }

    @Override
    public void run() {                             //main worker thread
        //      testing

        //-------------------------------------------
        
        Scanner scanner = TextReadWrite.getScanner();
        String input;
        int option;



        while (running) {
            System.out.println("Choose an option (number)\n1.\tSale\n2.\tStock management\n3.\tCustomer management\n4.\tAccount management\n5.\tEmployee management\n6.\tExit\noption:\t");
            input = scanner.nextLine();
            switch (NumberConversion.toInt(input)) {
            case NumberConversion.ERROR:
                System.out.println("Invalid input");
                break;
            case 1:
                running = false;
                break;
            case 2:
                while (true) {      //loop for option 2
                    Event ev = EventFactory.getEvent(EVENT_TYPE.TRANS);
                    this.eventHandler(ev);
                }
                break;
            }
        }
    }
}



