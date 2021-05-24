package Point_Of_Sale.Events;

import Point_Of_Sale.Reports.REPORT_TYPE;
import Point_Of_Sale.Reports.Report;

public class ReportEvent extends Event {        // event responsible for handling reports
    public REPORT_TYPE reportType;          //  report type
    public Report report;                   // actual report to be manipulated

    public ReportEvent(EVENT_TYPE type){
        super(type);
    }
}
