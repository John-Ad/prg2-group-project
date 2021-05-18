package Point_Of_Sale.Events;

import Point_Of_Sale.NumberConversion;
import Point_Of_Sale.TextReadWrite;
import Point_Of_Sale.Transactions.TRAN_TYPE;
import Point_Of_Sale.Transactions.TransactionGenerator;

public class EventFactory {
    public static Event getEvent(EVENT_TYPE type) {
        switch (type) {
        case TRANS:
             return new TransactionEvent();
        case STORAGE:
            return new StorageEvent();
        case REPORT:
            return new ReportEvent();
        default:
            return null;
        }
    }
}
