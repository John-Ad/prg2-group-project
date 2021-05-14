package Point_Of_Sale;

import Point_Of_Sale.Users.*;

import java.util.Scanner;

import Point_Of_Sale.EventDefs;
import Point_Of_Sale.DB.QueryBuilder;
import Point_Of_Sale.EventDefs.EVENT_TYPE;
import Point_Of_Sale.EventDefs.Event;
import Point_Of_Sale.EventDefs.QUERY_TYPE;
import Point_Of_Sale.EventDefs.QueryEvent;

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

    private void eventHandler(EventDefs.Event ev) {
        switch (ev.evType) {
        case QUERY:
            QueryEvent qEv = (QueryEvent) ev;
            System.out.println(QueryBuilder.buildQry(qEv));
            break;
        case REPORT:
            break;
        case TRANS:
            break;
        }
    }

    @Override
    public void run() {                             //main worker thread
        //      testing

        //-------------------------------------------
        
        Scanner scanner = new Scanner(System.in);
        Event ev;
        String input;
        int option;

        while (running) {
            System.out.format("Choose an option (number)\n1.\tSale\n2.\tStock management\n3.\tCustomer management\n4.\tAccount management\n5.\tEmployee management\n6.\tExit\noption:\t");
            input = scanner.nextLine();
            if ((option = NumberConversion.toInt(input)) != NumberConversion.ERROR) {
                ev = EventFactory.getEvent(option);
                eventHandler(ev);
            }
        }
    }
}



