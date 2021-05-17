package Point_Of_Sale;

import Point_Of_Sale.EventDefs.EVENT_TYPE;
import Point_Of_Sale.EventDefs.Event;
import Point_Of_Sale.EventDefs.QueryEvent;;

public class EventFactory {
    public static Event getEvent(int type) {
        switch (EVENT_TYPE.fromInt(type)) {
        case QUERY:
            return new QueryEvent();
        default:
            return null;
        }
    }
}
