package Point_Of_Sale.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Storage {
    private Storage() {
    }

    private static ObjectOutputStream getOutStream(String file) throws Exception {
        FileOutputStream fs = new FileOutputStream(new File(file));
        ObjectOutputStream os = new ObjectOutputStream(fs);
        return os;
    }

    private static ObjectInputStream getInStream(String file) throws Exception {
        FileInputStream fs = new FileInputStream(new File(file));
        ObjectInputStream os = new ObjectInputStream(fs);
        return os;
    }

    public static void writeObjects(STORAGE_TYPE type, Object data) {
        try {
            ObjectOutputStream os = getOutStream(type.getFileName());
            os.writeObject(data);
            os.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Object readObjects(STORAGE_TYPE type) {
        try {
            ObjectInputStream os = getInStream(type.getFileName());
            Object data = os.readObject();
            os.close();
            return data;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static Object findObject(STORAGE_TYPE type, Object item) {
        try{
            
        } catch (Exception ex) {

        }

        return null;
    }
}