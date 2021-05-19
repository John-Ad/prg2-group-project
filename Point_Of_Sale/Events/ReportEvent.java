package Point_Of_Sale.Events;

import Point_Of_Sale.Reports.REPORT_TYPE;
import Point_Of_Sale.Reports.Report;

public class ReportEvent extends Event {
    public REPORT_TYPE reportType;
    public Report report;

    public ReportEvent(EVENT_TYPE type){
        super(type);
    }
}
