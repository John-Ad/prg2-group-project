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

            for (boolean i = true; i == true;) {
                System.out.println("1.\tSale\n2.\tRefund: ");
                switch (NumberConversion.toInt(TextReadWrite.getScanner().nextLine())) {
                case NumberConversion.ERROR:
                    System.out.println("Invalid input");
                    break;
                case 1:
                    event.tranType = TRAN_TYPE.SALE;
                    i = false;
                    break;
                case 2:
                    event.tranType = TRAN_TYPE.REFUND;
                    i = false;
                    break;
                }
            }

            event.transaction = TransactionGenerator.getTran(event.tranType);
            return event;
        case STORAGE:
            return new StorageEvent();
        case REPORT:
            return new ReportEvent();
        default:
            return null;
        }
    }
}
