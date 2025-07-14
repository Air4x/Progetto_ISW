package database;

import utility.PasswordManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final String DB_USER = "mysql";
    private static final String DB_PASSWORD = "";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/testDB";

    private DBManager() throws IllegalAccessException{
        throw new IllegalAccessException("Utility class");
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
