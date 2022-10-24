package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    static final String dbUri = "jdbc:mysql://localhost:3306/client_schedule?connectionTimeZone=SERVER";
    static final String dbUser = "sqlUser";
    static final String dbPassword = "Passw0rd!";

    static private Connection connection;

    public static void open() {
        if (connection == null) connection = create();
    }

    public static void close() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            ErrorHandling.printTraceAndExit(e);
        }
    }

    public static Connection get() {
        if (connection == null) connection = create();
        return connection;
    }

    private static Connection create() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(dbUri, dbUser, dbPassword);
        } catch (ClassNotFoundException | SQLException e) {
            ErrorHandling.printTraceAndExit(e);
            return null;
        }
    }
}
