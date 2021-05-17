package Point_Of_Sale.Events;

public enum EVENT_TYPE {
    STORAGE, TRANS, REPORT;

    public static EVENT_TYPE fromInt(int x) {
        switch (x) {
        case 1:
            return STORAGE;
        case 2:
            return TRANS;
        case 3:
            return REPORT;
        }
        return null;
    }
}
