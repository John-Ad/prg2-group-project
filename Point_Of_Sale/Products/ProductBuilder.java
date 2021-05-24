package Point_Of_Sale.Products;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import Point_Of_Sale.NumberConversion;
import Point_Of_Sale.TextReadWrite;
import Point_Of_Sale.Storage.STORAGE_TYPE;
import Point_Of_Sale.Storage.Storage;

public class ProductBuilder {
    private static ArrayList<Object> buildProdDetails() {
        // general product details
        String uID = Integer.toString((int) Math.floor(Math.random() * 900000) + 100000); //random 6 digit value
        String name;
        String desc;
        double price;
        float weight;
        int qty;
        //  end of product details

        System.out.print("\nenter name: ");
        name = TextReadWrite.getScanner().nextLine();

        // check if product already exists
        if (Storage.findObject(STORAGE_TYPE.FIND_PROD, name) != null) {
            System.out.println("product with that name already exist!");
            return null;        // return null if user exists
        }

        System.out.print("\nenter description: ");
        desc = TextReadWrite.getScanner().nextLine();

        // loop until valid price is entered 
        System.out.print("\nenter price: ");
        while ((price = NumberConversion.toDouble(TextReadWrite.getScanner().nextLine())) == NumberConversion.ERROR) {
            System.out.print("\ninvalid price entered!");
            System.out.print("\nenter price: ");
        }

        // loop until valid weight is entered 
        System.out.print("\nenter weight: ");
        while ((weight = NumberConversion.toFloat(TextReadWrite.getScanner().nextLine())) == NumberConversion.ERROR) {
            System.out.print("\ninvalid weight entered!");
            System.out.print("\nenter weight: ");
        }

        // loop until valid quantity is entered 
        System.out.print("\nenter quantity: ");
        while ((qty = NumberConversion.toInt(TextReadWrite.getScanner().nextLine())) == NumberConversion.ERROR) {
            System.out.print("\ninvalid quantity entered!");
            System.out.print("\nenter quantity: ");
        }

        // return array of objects because of the different var types used eg int, double ,float
        return new ArrayList<Object>(Arrays.asList(uID, name, desc, price, weight, qty));     
    }

    public static PerishableProduct buildPerProd() {
        //build general user details
        ArrayList<Object> pData = buildProdDetails();
        if (pData==null){   // if null, user already exists
            return null;   
        }
        Date date;  

        // loop until valid date entered
        while (true) {
            System.out.print("\nEnter expiration date (dd/mm/yy): ");
            try {
                date = new SimpleDateFormat("dd/mm/yyyy").parse(TextReadWrite.getScanner().nextLine());
                break;
            } catch (Exception ex) {    // catch invalid date exception
                System.out.println("Invalid date entered");
            }
        }


        return new PerishableProduct((String)pData.get(0),(String)pData.get(1), (String)pData.get(2), (double)pData.get(3), (float)pData.get(4), (int)pData.get(5),
                date.toString());   // build product with retrieved values and return   // pdata values casted to match where necessary
    }

    public static NonPerishableProduct buildNonPerProd() {  // build non perishable product
        ArrayList<Object> pData = buildProdDetails();   // get general user details
        if (pData==null){   // if null, user alread exists
            return null;
        }
        Date date;

        // loop until valid date entered
        while (true) {
            System.out.print("\nEnter manufactured date (dd/mm/yy): ");
            try {
                date = new SimpleDateFormat("dd/mm/yyyy").parse(TextReadWrite.getScanner().nextLine());
                break;
            } catch (Exception ex) {        // catch exceptions thrown when invalid date entered
                System.out.println("Invalid date entered");
            }
        }

        return new NonPerishableProduct((String)pData.get(0),(String)pData.get(1), (String)pData.get(2), (double)pData.get(3), (float)pData.get(4), (int)pData.get(5),
                date.toString());   // cast general data to match // return non perishable product built with retrieved data
    }
}
