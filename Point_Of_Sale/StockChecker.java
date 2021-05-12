package Point_Of_Sale;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import Point_Of_Sale.Exceptions.LowStockException;

public class StockChecker implements Callable<Integer> {
    @Override
    public Integer call() throws LowStockException {
        /*      testing
        while (true) {
            System.out.println("enter int: ");
            if (ScannerAccess.getScanner().nextInt() == 1)
                break;
        }
        throw new LowStockException();
        */
        return null;
    }
    /**
     *  1. get db for low stock levels
     *  2. throw exception to be caught by POS
     */
}
