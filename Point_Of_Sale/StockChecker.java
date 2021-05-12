package Point_Of_Sale;

public class StockChecker implements Runnable {
    private Thread stockChecker;
    private boolean lowStock;

    StockChecker() {
        stockChecker = new Thread(this);
        stockChecker.start();
    }

    @Override               // code that will check stock levels
    public void run() {
        /**
         *  1. get db for low stock levels
         *  2. notify system
         */
    }
}
