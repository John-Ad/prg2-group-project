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

    private static ObjectOutputStream getOutStream(String file) throws Exception {
        FileOutputStream fs = new FileOutputStream(new File(file));
        ObjectOutputStream os = new ObjectOutputStream(fs);
        return os;
    }

    private static ObjectInputStream getInStream(String file) throws Exception {
        FileInputStream fs = new FileInputStream(new File(file));
        ObjectInputStream os = new ObjectInputStream(fs);
        return os;
    }

    public static void writeObjects(STORAGE_TYPE type, Object data) {
        try {
            ObjectOutputStream os = getOutStream(type.getFileName());
            os.writeObject(data);
            os.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

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

    public static void addObject(STORAGE_TYPE type, Object item) {
        switch (type) {
        case STORE_CUST:
            Client c = (Client) item; //cast object to correct type
            HashMap<String, Client> cmap = (HashMap<String, Client>) Storage.readObjects(type); //retrieve stored data
            cmap.put(c.getEmail(), c); //add item to others
            Storage.writeObjects(type, cmap); // store data
            break;
        case STORE_PROD:
            Product p = (Product) item;
            HashMap<String, Product> pmap = (HashMap<String, Product>) Storage.readObjects(type); //retrieve stored data
            pmap.put(p.getName(), p);
            Storage.writeObjects(type, pmap);
            break;
        case STORE_TRAN:
            Transaction t = (Transaction) item;
            ArrayList<Transaction> arr = (ArrayList<Transaction>) Storage.readObjects(type);
            arr.add(t);
            Storage.writeObjects(type, arr);
            break;
        case STORE_EMP:
            Employee e = (Employee) item; //cast object to correct type
            HashMap<String, Employee> emap = (HashMap<String, Employee>) Storage.readObjects(type); //retrieve stored data
            emap.put(e.getEmail(), e); //add item to others
            Storage.writeObjects(type, emap); // store data
            break;
        }
    }

    public static void remObject(STORAGE_TYPE type, String item) {
        switch (type) {
            case REM_CUST:
                HashMap<String, Client> cmap = (HashMap<String, Client>) Storage.readObjects(type); //retrieve stored data
                cmap.remove(item);
                Storage.writeObjects(type, cmap);
                break;
            case REM_EMP:
                HashMap<String, Employee> emap = (HashMap<String, Employee>) Storage.readObjects(type); //retrieve stored data
                emap.remove(item);
                Storage.writeObjects(type, emap);
            case REM_PROD:
                HashMap<String, Product> pmap = (HashMap<String, Product>) Storage.readObjects(type); //retrieve stored data
                pmap.remove(item);
                Storage.writeObjects(type, pmap);
                break;
        }
    }
}