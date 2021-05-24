package Point_Of_Sale.Events;

public class EventFactory {         // returns a specifc type of event depending on ev type
    public static Event getEvent(EVENT_TYPE type) {     
        switch (type) {
        case TRANS:
             return new TransactionEvent(type);     // transaction event
        case STORAGE:
            return new StorageEvent(type);          // storage event
        case REPORT:
            return new ReportEvent(type);           // report event
        default:
            return null;
        }
    }
}
