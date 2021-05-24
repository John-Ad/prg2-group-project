package Point_Of_Sale.Events;

public enum EVENT_TYPE {
    STORAGE, TRANS, REPORT;     // type of events

    //  convert an int to EVENT TYPE
    public static EVENT_TYPE fromInt(int x) {
        switch (x) {
        case 1:
            return STORAGE;
        case 2:
            return TRANS;
        case 3:
            return REPORT;
        default:
            return null;
        }
    }
}
