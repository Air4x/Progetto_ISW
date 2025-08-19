package database;

import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class DBManagerTest {

    @Test
    public void getConnection() {
        try {
            Connection conn = DBManager.getConnection();
            assertNotNull("should not be null", conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
