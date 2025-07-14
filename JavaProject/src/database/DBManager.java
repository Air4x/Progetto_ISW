package database;

import utility.PasswordManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final String DB_USER = PasswordManager.getInstance().get("db_user");
    private static final String DB_PASSWORD = PasswordManager.getInstance().get("db_password");
    private static final String DB_URL = PasswordManager.getInstance().get("db_url");

    private DBManager() throws IllegalAccessException{
        throw new IllegalAccessException("Utility class");
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
