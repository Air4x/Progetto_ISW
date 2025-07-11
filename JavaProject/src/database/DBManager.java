package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final String DB_USER = "mysql";
    private static final String DB_PASSWORD = "134534";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";

    private DBManager() throws IllegalAccessException{
        throw new IllegalAccessException("Utility class");
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}