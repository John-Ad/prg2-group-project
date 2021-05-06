package Point_Of_Sale;

import Point_Of_Sale.Users.*;

import java.util.Scanner;

import Point_Of_Sale.EventDefs;
import Point_Of_Sale.DB.DBQueryBuilder;
import Point_Of_Sale.EventDefs.EVENT_TYPE;
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
        if (ev.evType == EventDefs.EVENT_TYPE.QUERY) {
            QueryEvent qEv = (QueryEvent) ev;
            System.out.println(DBQueryBuilder.buildQry(qEv));
        }
    }

    @Override
    public void run() {                             //main worker thread
        //      testing
        /*Client client = new Client();
        client.setName("sdkf");
        client.setEmail("sdf");
        client.setID("randID");

        QueryEvent ev = new QueryEvent();
        ev.evType = EVENT_TYPE.QUERY;
        ev.qryType = QUERY_TYPE.ADD_CLI;
        ev.data = client;

        String qry = DBQueryBuilder.buildQry(ev);
        System.out.println(qry);*/
        //-------------------------------------------
        
        Scanner scanner = new Scanner(System.in);
        EVENT_TYPE ev;
        int option;
        while (running) {
            System.out.format("Choose an option (number)\n1.\tSale\n2.\tStock management\n3.\tCustomer management\n4.\tAccount management\n5.\tEmployee management\n6.\tExit\noption:\t");
            try{
                option = scanner.nextInt();
                ev = EVENT_TYPE.fromInt(option);
                System.out.println(ev);
            } catch (Exception error) {
                System.out.println("\ninvalid input\n");
                scanner.nextLine();
            }
        }
    }
}



