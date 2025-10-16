package org.groupone.utility;

import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordManagerTest {

    @Test
    public void get() {
        assertTrue(PasswordManager.getInstance().get("email_host").equalsIgnoreCase("mail.smtp.gmail.com"));
    }
}
