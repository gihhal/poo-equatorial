package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

    private static Connection connection;

    private ConnectionManager() {}

    public static Connection getConnection() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection(
                        "jdbc:sqlite:energia.db"
                );
            }
            return connection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
