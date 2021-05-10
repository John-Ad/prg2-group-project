package Point_Of_Sale.DB;

import java.sql.*;

public class DBConnection {
    private static Connection dbConn;
    private static Statement statement;

    public static ResultSet query(String qry) {
        try {
            if (dbConn == null) {
                dbConn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/Point_Of_Sale?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                        "dev", "dev");
                statement = dbConn.createStatement();
                statement.isPoolable();
            }

            return statement.executeQuery(qry);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}

