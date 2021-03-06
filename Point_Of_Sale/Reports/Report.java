package Point_Of_Sale.Reports;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import Point_Of_Sale.*;
import Point_Of_Sale.Products.Product;
import Point_Of_Sale.Storage.STORAGE_TYPE;
import Point_Of_Sale.Storage.Storage;
import Point_Of_Sale.Transactions.Transaction;
import Point_Of_Sale.Users.Client;

public class Report {       // contains all methods/attributes to manipulate a report
    // instance variables
    private REPORT_TYPE type;

    private String header;
    private ArrayList<ArrayList<String>> data;
    //---------------------------------------

    public Report(REPORT_TYPE type) {
        this.type = type;
        this.header = REPORT_TYPE.getHeader(type);
        this.data = new ArrayList<ArrayList<String>>();
    }

    private void generateData() {   // generate report data
        data.clear();           // clear current data 
        ArrayList<String> row;  // each row represents a list values data about 1 item

        switch (this.type) {
        case CUSTOMERS:
            // data to be generated
            String name, email;
            int itemsBought = 0;
            double totalSpent = 0;
            //---------------------

            // retrieve transaction data and client data
            ArrayList<Transaction> arrC = new ArrayList<Transaction>(
                    ((ArrayList<Transaction>) Storage.readObjects(STORAGE_TYPE.GET_TRAN))); // create copy of array
            HashMap<String, Client> cli = (HashMap<String, Client>) Storage.readObjects(STORAGE_TYPE.GET_CUST);
            //---------------------------------------------

            // iterate through clients and for each client search through transactions and build data
            for (Map.Entry<String, Client> elem : cli.entrySet()) {
                name = ((Client) elem.getValue()).getName();
                email = ((Client) elem.getValue()).getEmail();
                row = new ArrayList<String>();

                // loop through transactions and get data
                for (int i = 0; i < arrC.size(); i++) {
                    if (arrC.get(i).getClient().getEmail().compareTo(email) == 0) {
                        if (arrC.get(i).getAmount() < 0) {
                            itemsBought -= arrC.get(i).getNumOfItems();
                        } else {
                            itemsBought += arrC.get(i).getNumOfItems();
                        }
                        totalSpent += arrC.get(i).getAmount();      //if amount < 0 negative number added

                        arrC.remove(i); //remove transaction
                        i--; //reposition index to offset removed elem
                    }
                }
                // ----------------------------------------

                //add data to array
                row.add(name);
                row.add(email);
                row.add(Integer.toString(itemsBought));
                row.add(Double.toString(totalSpent));
                //--------------------------------------

                data.add(row); // add row to data array

                //reset shared variables
                itemsBought = 0;
                totalSpent = 0;
                //----------------------
            }
            //-----------------------------------------------------------------------------------------
            break;
        case ITEMS:
            // data to be generated
            String pID, desc, price;
            int qty = 0;
            double total = 0;
            //---------------------

            // retrieve transaction data and client data
            ArrayList<Transaction> arrP = new ArrayList<Transaction>(
                    (ArrayList<Transaction>) Storage.readObjects(STORAGE_TYPE.GET_TRAN)); // create copy of array
            HashMap<String, Product> prod = (HashMap<String, Product>) Storage.readObjects(STORAGE_TYPE.GET_PROD);
            //---------------------------------------------

            for (Map.Entry<String, Product> elem : prod.entrySet()) {
                pID = ((Product) elem.getValue()).getProdID();
                desc = ((Product) elem.getValue()).getDesc();
                price = Double.toString(((Product) elem.getValue()).getPrice());
                row = new ArrayList<String>();

                // for each transaction loop through the items bought and see if they match the current prod being checked
                for (Transaction t : arrP) {
                    for (Product p : t.getItems()) {
                        if (p.getProdID().compareTo(pID) == 0) {
                            if (t.getAmount() < 0) {        //check if sale or refund
                                qty--;
                            } else {
                                qty++;
                            }
                        }
                    }
                }
                // --------------------------------------------------------

                total = qty * Double.parseDouble(price);

                // add data to row
                row.add(pID);
                row.add(desc);
                row.add(price);
                row.add(Integer.toString(qty));
                row.add(Double.toString(total));
                //--------------------------------

                // add row to data array
                data.add(row);
                //--------------------------------

                qty = 0;
                total = 0;
            }
            break;
        case TRANSFERS:
            // data to generate
            String accID;
            double totalCred = 0, totalDeb = 0, totalTran = 0;
            // ----------------

            // retrieve transaction data and client data
            ArrayList<Transaction> arrT = new ArrayList<Transaction>(
                    ((ArrayList<Transaction>) Storage.readObjects(STORAGE_TYPE.GET_TRAN))); // create copy of array
            HashMap<String, Client> mapC = (HashMap<String, Client>) Storage.readObjects(STORAGE_TYPE.GET_CUST);
            //---------------------------------------------

            // for each account, loop through all transactions and see where the account was credited or debited
            for (Map.Entry<String, Client> elem : mapC.entrySet()) {
                Account acc = ((Client) elem.getValue()).getAcc();
                accID = acc.getAccID();
                row = new ArrayList<String>();

                for (Transaction t : arrT) {
                    if (t.getClient().getAcc().getAccID().compareTo(accID) == 0) {
                        if (t.getAmount() < 0) { // if amount < 0 then a refund occurred
                            totalCred += (-t.getAmount());
                        } else {
                            totalDeb += t.getAmount();
                        }
                    }
                }

                totalTran = totalCred + totalDeb;       // set total transfers

                // add data to array
                row.add(accID);
                row.add(Double.toString(totalCred));
                row.add(Double.toString(totalDeb));
                row.add(Double.toString(totalTran));
                //--------------------------

                // add row to data array
                data.add(row);
                //--------------------------------

                // reset shared variables
                totalCred = 0;
                totalDeb = 0;
                totalTran = 0;
                //--------------------------
            }
            // ----------------------------------------------------
            break;
        default:
            break;
        }
    }

    private String generateReport() throws Exception {
        // generate new data
        generateData();
        // -----------------

        // construct report
        String report = header;
        for (ArrayList<String> row : data) {
            report += "\r\n";
            for (String elem : row) {
                report += String.format("%-20s\t\t", elem);
            }
        }
        //-----------------

        return report;
    }

    public String getOldReport() throws Exception {
        String report = "";
        Scanner scanner = new Scanner(new File(REPORT_TYPE.getFileName(type))); // scanner that reads files
        // read file line by line
        while (scanner.hasNext()) {
            report += scanner.nextLine() + "\n";
        }
        //-----------------------

        return report;
    }

    public void createNewReport() throws Exception {
        //open a file writer
        FileWriter writer = new FileWriter(new File(REPORT_TYPE.getFileName(this.type)));

        // save report
        writer.write(generateReport());
        writer.close();
    }
}
