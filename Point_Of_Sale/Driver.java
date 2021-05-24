package Point_Of_Sale;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import Point_Of_Sale.*;
import Point_Of_Sale.Events.Event;
import Point_Of_Sale.Events.EventFactory;
import Point_Of_Sale.Products.NonPerishableProduct;
import Point_Of_Sale.Products.PRODUCT_TYPE;
import Point_Of_Sale.Products.PerishableProduct;
import Point_Of_Sale.Products.Product;
import Point_Of_Sale.Products.ProductFactory;
import Point_Of_Sale.Storage.STORAGE_TYPE;
import Point_Of_Sale.Storage.Storage;
import Point_Of_Sale.Transactions.TRAN_TYPE;
import Point_Of_Sale.Users.Client;
import Point_Of_Sale.Users.USER_TYPE;
import Point_Of_Sale.Users.UserFactory;

public class Driver {
    public static void main(String args[]) {

        POS pos=new POS();      // create pos object // main loop is triggered in constructor
   }
}
