package Point_Of_Sale.Events;

import Point_Of_Sale.Transactions.TRAN_TYPE;
import Point_Of_Sale.Transactions.Transaction;

public class TransactionEvent extends Event {
    public TRAN_TYPE tranType;
    public Transaction transaction;

    public TransactionEvent(EVENT_TYPE type) {
        super(type);
    }
}
