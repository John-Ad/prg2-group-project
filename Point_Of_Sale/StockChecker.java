package Point_Of_Sale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import Point_Of_Sale.Exceptions.LowStockException;
import Point_Of_Sale.Products.Product;
import Point_Of_Sale.Storage.STORAGE_TYPE;
import Point_Of_Sale.Storage.Storage;

public class StockChecker implements Callable<ArrayList<Product>> {
    @Override
    public ArrayList<Product> call() {
        ArrayList<Product> arr = new ArrayList<>();
        while (true) {
            HashMap<String, Product> map = (HashMap<String, Product>) Storage.readObjects(STORAGE_TYPE.GET_PROD);
            for (Map.Entry<String, Product> elem : map.entrySet()) {
                System.out.println(((Product) elem.getValue()).getQty());
                if (((Product) elem.getValue()).getQty() < Product.MIN_QTY) {
                    arr.add((Product) elem.getValue());
                }
            }
            if (arr.size() > 0) {
                return arr;
            }
        }
    }
    /**
     *  1. check prod file for low stock levels
     *  2. throw exception to be caught by POS
     */
}
