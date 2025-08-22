package org.groupone.controller;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import jakarta.mail.MessagingException;

public class NotificationControllerTest {

    private NotificationController notification_Controller;

    @Before
    public void setUp() throws SQLException{
        notification_Controller = new NotificationController();
    }

    // Test per l'invio delle notifiche e in maniera implicita test di createMessage() e sendEmail()
    @Test
    public void testInvioNotifiche() throws SQLException, MessagingException{
        notification_Controller.invioNotifiche();
    }

}
