package database;

import utility.PasswordManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe che si occupa di impostare la connesione al database
 *
 */
public class DBManager {
    private static final String DB_USER = PasswordManager.getInstance().get("db_user");
    private static final String DB_PASSWORD = PasswordManager.getInstance().get("db_password");
    private static final String DB_URL = PasswordManager.getInstance().get("db_url");

    /**
     * Costruttore della classe DBManager
     *
     */
    private DBManager() throws IllegalAccessException{
        throw new IllegalAccessException("Utility class");
    }

    /**
     * Metodo statico che permette di ottenere la connesione al
     * database
     *
     * @return Un oggetto Connection che rappresenta la connesione
     * vera e propria
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
