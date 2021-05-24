package Point_Of_Sale.Events;

import Point_Of_Sale.Transactions.TRAN_TYPE;
import Point_Of_Sale.Transactions.Transaction;

public class TransactionEvent extends Event {       // event for transactions
    public TRAN_TYPE tranType;                      // type of tran
    public Transaction transaction;                 // actual transaction object to manipulate

    public TransactionEvent(EVENT_TYPE type) {
        super(type);
    }
}
