package Point_Of_Sale.Events;

import Point_Of_Sale.Reports.REPORT_TYPE;

public class ReportEvent extends Event {
    public REPORT_TYPE reportType;
    public Report report;
}
