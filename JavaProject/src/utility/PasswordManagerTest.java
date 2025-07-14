package utility;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordManagerTest {

    @Test
    public void get() {
        assertSame(PasswordManager.getInstance().get("db_user"), "mysql");
    }
}