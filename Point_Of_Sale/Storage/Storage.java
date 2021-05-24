package Point_Of_Sale.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import Point_Of_Sale.Products.Product;
import Point_Of_Sale.Transactions.Transaction;
import Point_Of_Sale.Users.Client;
import Point_Of_Sale.Users.Employee;

public class Storage {
    private Storage() {
    }

    // get file object needed to write data to files
    private static ObjectOutputStream getOutStream(String file) {
        try {
            FileOutputStream fs = new FileOutputStream(new File(file));
            ObjectOutputStream os = new ObjectOutputStream(fs);
            return os;
        } catch (Exception ex) {
            System.out.println("failed to open file");
            return null;
        }
    }
    //---------------------------------------------------

    // get file object needed to read data from files
    private static ObjectInputStream getInStream(String file) {
        try {
            FileInputStream fs = new FileInputStream(new File(file));
            ObjectInputStream os = new ObjectInputStream(fs);
            return os;
        } catch (Exception ex) {

            /* if any error occurs eg file doesnt exist or is empty
                initialize file with empty data structure eg arrayList or hashmap
            */
            System.out.println("Creating new file");

            // objects to initialize file with
            HashMap<String, Object> map = new HashMap<String, Object>();
            ArrayList<Object> arr = new ArrayList<Object>();
            //----------------------------------------------

            //  based on file type save one of the 2 objects above
            switch (file) {
            case "tran.ser":
                writeObjects(file, arr);
                break;
            case "cust.ser":
                writeObjects(file, map);
                break;
            case "prod.ser":
                writeObjects(file, map);
                break;
            case "emp.ser":
                writeObjects(file, map);
                break;
            }
            //  ----------------------------------

            // retry retrieving the input stream
            return getInStream(file);
        }
    }
    //-----------------------------------------

    //  save objects passed in to file passed in
    public static void writeObjects(String file, Object data) {
        try {
            ObjectOutputStream os = getOutStream(file); 
            os.writeObject(data);
            os.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //-----------------------------------------

    //  read all objects in file based on type passed in 
    public static Object readObjects(STORAGE_TYPE type) {
        try {
            ObjectInputStream os = getInStream(type.getFileName());
            Object data = os.readObject();
            os.close();
            return data;
        } catch (Exception ex) {
            
            ex.printStackTrace();
            return null;
        }
    }
    //-----------------------------------------

    // find a specific object in file based on type passed
    public static Object findObject(STORAGE_TYPE type, String item) {
        switch (type) {
        case FIND_CUST:
            HashMap<String, Client> cmap = (HashMap<String, Client>) Storage.readObjects(type); //retrieve stored data
            if (cmap.containsKey(item)) {
                return cmap.get(item);
            }
            break;
        case FIND_PROD:
            HashMap<String, Product> pmap = (HashMap<String, Product>) Storage.readObjects(type); //retrieve stored data
            if (pmap.containsKey(item)) {
                return pmap.get(item);
            }
            break;
        case FIND_EMP:
            HashMap<String, Employee> emap = (HashMap<String, Employee>) Storage.readObjects(type); //retrieve stored data
            if (emap.containsKey(item)) {
                return emap.get(item);
            }
            break;
        default:
            return null;
        }
        return null;
    }

    // add a single object to a file based type passed in
    public static void addObject(STORAGE_TYPE type, Object item) {
        // transactions use arraylists and the rest use hashmaps
        /**
         *  first the structure containing all objects are retrieved
         *  item added to structure
         *  structure resaved
         */
        switch (type) {
        case STORE_CUST:
            Client c = (Client) item; //cast object to correct type
            HashMap<String, Client> cmap = (HashMap<String, Client>) Storage.readObjects(type); //retrieve stored data
            cmap.put(c.getEmail(), c); //add item to others
            Storage.writeObjects(type.getFileName(), cmap); // store data
            break;
        case STORE_PROD:
            Product p = (Product) item;
            HashMap<String, Product> pmap = (HashMap<String, Product>) Storage.readObjects(type); //retrieve stored data
            pmap.put(p.getName(), p);
            Storage.writeObjects(type.getFileName(), pmap);
            break;
        case STORE_TRAN:
            Transaction t = (Transaction) item;
            ArrayList<Transaction> arr = (ArrayList<Transaction>) Storage.readObjects(type);
            arr.add(t);
            Storage.writeObjects(type.getFileName(), arr);
            break;
        case STORE_EMP:
            Employee e = (Employee) item; //cast object to correct type
            HashMap<String, Employee> emap = (HashMap<String, Employee>) Storage.readObjects(type); //retrieve stored data
            emap.put(e.getEmail(), e); //add item to others
            Storage.writeObjects(type.getFileName(), emap); // store data
            break;
        }
        //--------------------------------------------------------------------------
    }
    //--------------------------------------------------------------------------

    //  remove a single object from file depending on type passed in
    public static void remObject(STORAGE_TYPE type, String item) {
        //  transactions are never removed
        /**
         *  get structure containing all objects
         *  remove specific object
         *  resave structure
         */
        switch (type) {
            case REM_CUST:
                HashMap<String, Client> cmap = (HashMap<String, Client>) Storage.readObjects(type); //retrieve stored data
                cmap.remove(item);
                Storage.writeObjects(type.getFileName(), cmap);
                break;
            case REM_EMP:
                HashMap<String, Employee> emap = (HashMap<String, Employee>) Storage.readObjects(type); //retrieve stored data
                emap.remove(item);
                Storage.writeObjects(type.getFileName(), emap);
            case REM_PROD:
                HashMap<String, Product> pmap = (HashMap<String, Product>) Storage.readObjects(type); //retrieve stored data
                pmap.remove(item);
                Storage.writeObjects(type.getFileName(), pmap);
                break;
        }
    }
    //----------------------------------------------------
}