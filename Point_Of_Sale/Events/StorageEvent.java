package Point_Of_Sale.Events;

import Point_Of_Sale.Storage.STORAGE_TYPE;

public class StorageEvent extends Event {   //  event for storage
    public STORAGE_TYPE storageType;        // type of storage
    public Object Data;                      // data to be stored

    public StorageEvent(EVENT_TYPE type) {      // constructor
        super(type);
    }
}
