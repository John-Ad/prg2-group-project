package Point_Of_Sale;

public class EventDefs {
    public static enum EVENT_TYPE {
        QUERY(1), TRANS(2), REPORT(3);

        public static EVENT_TYPE fromInt(int x){
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

        EVENT_TYPE(int numVal){}
    }

    public static enum QUERY_TYPE { // will be used to determine the type of query string to return
        ADD_CLI, REM_CLI, GET_CLI,

        ADD_EMP, REM_EMP, GET_EMP,

        ADD_PROD, REM_PROD, GET_PROD,

        ADD_ACC, REM_ACC, GET_ACC,

        LINK_CARD, UNLINK_CARD, GET_CARD,

        INC_STOCK, DEC_STOCK
    }

    public static abstract class Event {
        public EVENT_TYPE evType;
    }

    public static class QueryEvent extends Event {
        public QUERY_TYPE qryType;
        public Object data;

        public QueryEvent() {
            this.qryType = null;
            this.data = null;
        }
    }
}
