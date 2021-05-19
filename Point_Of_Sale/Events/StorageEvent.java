package Point_Of_Sale.Events;

import Point_Of_Sale.Storage.STORAGE_TYPE;

public class StorageEvent extends Event {
    public STORAGE_TYPE storageType;
    public Object Data;

    public StorageEvent(EVENT_TYPE type) {
        super(type);
    }
}
