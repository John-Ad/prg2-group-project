package Point_Of_Sale.Products;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import Point_Of_Sale.NumberConversion;
import Point_Of_Sale.TextReadWrite;

public class ProductBuilder {
    private static ArrayList<Object> buildProdDetails() {
        String uID = Integer.toString((int) Math.floor(Math.random() * 900000) + 100000); //random 6 digit value
        String name;
        String desc;
        double price;
        float weight;
        int qty;

        System.out.print("\nenter name: ");
        name = TextReadWrite.getScanner().nextLine();

        System.out.print("\nenter description: ");
        desc = TextReadWrite.getScanner().nextLine();

        System.out.print("\nenter price: ");
        while ((price = NumberConversion.toDouble(TextReadWrite.getScanner().nextLine())) == NumberConversion.ERROR) {
            System.out.print("\ninvalid price entered!");
            System.out.print("\nenter price: ");
        }

        System.out.print("\nenter weight: ");
        while ((weight = NumberConversion.toFloat(TextReadWrite.getScanner().nextLine())) == NumberConversion.ERROR) {
            System.out.print("\ninvalid weight entered!");
            System.out.print("\nenter weight: ");
        }

        System.out.print("\nenter quantity: ");
        while ((qty = NumberConversion.toInt(TextReadWrite.getScanner().nextLine())) == NumberConversion.ERROR) {
            System.out.print("\ninvalid quantity entered!");
            System.out.print("\nenter quantity: ");
        }

        return new ArrayList<Object>(Arrays.asList(uID, name, desc, price, weight, qty));
    }

    public static PerishableProduct buildPerProd() {
        ArrayList<Object> pData = buildProdDetails();
        Date date;

        while (true) {
            System.out.print("\nEnter expiration date (dd/mm/yy): ");
            try {
                date = new SimpleDateFormat("dd/mm/yyyy").parse(TextReadWrite.getScanner().nextLine());
                break;
            } catch (Exception ex) {
                System.out.println("Invalid date entered");
            }
        }


        return new PerishableProduct((String)pData.get(0),(String)pData.get(1), (String)pData.get(2), (double)pData.get(3), (float)pData.get(4), (int)pData.get(5),
                date.toString());
    }

    public static NonPerishableProduct buildNonPerProd() {
        ArrayList<Object> pData = buildProdDetails();
        Date date;

        while (true) {
            System.out.print("\nEnter manufactured date (dd/mm/yy): ");
            try {
                date = new SimpleDateFormat("dd/mm/yyyy").parse(TextReadWrite.getScanner().nextLine());
                break;
            } catch (Exception ex) {
                System.out.println("Invalid date entered");
            }
        }

        return new NonPerishableProduct((String)pData.get(0),(String)pData.get(1), (String)pData.get(2), (double)pData.get(3), (float)pData.get(4), (int)pData.get(5),
                date.toString());
    }
}
