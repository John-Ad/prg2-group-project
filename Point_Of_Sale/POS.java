package Point_Of_Sale;

import Point_Of_Sale.Events.EVENT_TYPE;
import Point_Of_Sale.Events.Event;
import Point_Of_Sale.Events.EventFactory;
import Point_Of_Sale.Users.*;
import Point_Of_Sale.TextReadWrite;
import Point_Of_Sale.Events.Event;

import java.util.Scanner;

<<<<<<< HEAD


=======
>>>>>>> afc0b48209488da6a4de243fdfbe347f492ab391
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

<<<<<<< HEAD
    private void eventHandler(Event event) {
        
       
=======
    private void eventHandler(Event ev) {
>>>>>>> afc0b48209488da6a4de243fdfbe347f492ab391
        
    }

    @Override
    public void run() {                             //main worker thread
        //      testing

        //-------------------------------------------
        
        Scanner scanner = TextReadWrite.getScanner();
        String input;
        int option;



        while (running) {
<<<<<<< HEAD
            System.out.println("Choose an option (number)\n1.\tTransaction\n2.\tStock management\n3.\tCustomer management\n4.\tAccount management\n5.\tEmployee management\n6.\tExit\noption:\t");
            input = scanner.nextLine();
            switch(NumberConversion.toInt(input)) {
                case NumberConversion.ERROR:
                    System.out.println("Invalid input");
                    break;
                case 1:
                    running= false;
                    break;
                case 2:
                    while (true) { 
                        system.out.println("choose an option (number)\n1.\tAdd Stock\n2.\tRemove Stock\n3.\tSearch Stock\n4.\tRetrieve Stock\n5.\noptoin:\t");    //loop for option 2
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
=======
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
>>>>>>> afc0b48209488da6a4de243fdfbe347f492ab391
            }
        }
    }
}



