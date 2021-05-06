package Point_Of_Sale.DB;

import Point_Of_Sale.EventDefs;
import Point_Of_Sale.Users.Client;

public class DBQueryBuilder {
    private static String queries[] = { 
            //client queries
            "call sp_addClient",
            "call sp_remClient",
            "call sp_getClient",

            //employee queries
            "call sp_addEmployee",
            "call sp_remEmployee",
            "call sp_getEmployee",

            //Product queries
            "call sp_addProduct",
            "call sp_remProduct",
            "call sp_getProduct",

            //Account queries
            "call sp_addAccount",
            "call sp_remAccount",
            "call sp_getAccount",

            //Card queries
            "call sp_linkCard",
            "call sp_unlinkCard",
            "call sp_getCard",

            //stock queries
            "call sp_increaseStock",
            "call sp_decreaseStock"
    };

    public static String buildQry(EventDefs.QueryEvent qryEv) {
        switch (qryEv.qryType) {
        case ADD_CLI:
            Client c = (Client) qryEv.data;
            return String.format("%s(%s,%s,%s);", queries[0], c.getName(), c.getEmail(), c.getID());
        }
        return "";
    }
}
