package Point_Of_Sale.Events;

import Point_Of_Sale.NumberConversion;
import Point_Of_Sale.TextReadWrite;
import Point_Of_Sale.Transactions.TRAN_TYPE;
import Point_Of_Sale.Transactions.TransactionGenerator;

public class EventFactory {
    public static Event getEvent(EVENT_TYPE type) {
        switch (type) {
        case TRANS:
             return new TransactionEvent(type);
        case STORAGE:
            return new StorageEvent(type);
        case REPORT:
            return new ReportEvent(type);
        default:
            return null;
        }
    }
}
