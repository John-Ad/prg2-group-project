package Point_Of_Sale.Events;

public enum EVENT_TYPE {
    QUERY(1), TRANS(2), REPORT(3);

    public static EVENT_TYPE fromInt(int x) {
        switch (x) {
        case 1:
            return QUERY;
        case 2:
            return TRANS;
        case 3:
            return REPORT;
        }
        return null;
    }

    EVENT_TYPE(int numVal) {
    }
}
