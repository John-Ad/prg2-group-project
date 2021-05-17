package Point_Of_Sale.Reports;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import Point_Of_Sale.*;
import Point_Of_Sale.Storage.STORAGE_TYPE;
import Point_Of_Sale.Storage.Storage;

public class Report {
    private REPORT_TYPE type;

    private String header;
    private ArrayList<ArrayList<String>> data;

    public Report(REPORT_TYPE type) {
        this.type = type;
        this.header = REPORT_TYPE.getHeader(type);
        this.data = null;
    }

    private void generateData() {
        switch (this.type) {
        case CUSTOMERS:
            break;
        default:
            break;
        }
    }

    private String generateReport() throws Exception {
        String report = header + "\n";
        for (ArrayList<String> row : data) {
            for(String elem: row){
                report += String.format("%20s", elem);
            }
            report += "\n";
        }
        return report;
    }

    public String getOldReport() throws Exception {
        String report = "";
        Scanner scanner = new Scanner(new File(REPORT_TYPE.getFileName(type)));
        while (scanner.hasNext()) {
            report += scanner.nextLine();
        }
        return report;
    }

    public void createNewReport() throws Exception {
        
    }
}
