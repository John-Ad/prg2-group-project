package Point_Of_Sale;

import java.sql.SQLException;

import Point_Of_Sale.POS;
import Point_Of_Sale.DB.DBConnection;

public class Driver {
    public static void main(String args[]) {
        //POS pos = new POS();

        /* DB testing
        try {
            System.out.println(DBConnection.query("select * from Test;").getString("name"));
            System.out.println(DBConnection.query("select name from Test;").getDouble("salary"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
        /*  Card testing
        Card card = new Card("0123");
        card.checkPin();
        */
    }
}
