package Point_Of_Sale.Events;

import Point_Of_Sale.NumberConversion;
import Point_Of_Sale.TextReadWrite;
import Point_Of_Sale.Transactions.TRAN_TYPE;
import Point_Of_Sale.Transactions.TransactionGenerator;

public class EventFactory {
    public static Event getEvent(EVENT_TYPE type) {
        switch (type) {
        case TRANS:
            TransactionEvent event = new TransactionEvent();
            event.evType = type;

            int choice=0;
            System.out.println("1.\tSale\n2.\tRefund: "); // determine
            switch(NumberConversion.toInt(TextReadWrite))
            if ((choice = NumberConversion.toInt(TextReadWrite.getScanner().nextLine())) != 0) {
                switch (choice) {
                case 1:
                    event.tranType = TRAN_TYPE.SALE;
                    break;
                case 2:
                    event.tranType = TRAN_TYPE.REFUND;
                    break;
                default:
                    return null;
                }
            }

            event.transaction = TransactionGenerator.getTran(event.tranType);
            return event;
        default:
            return null;
        }
    }
}
