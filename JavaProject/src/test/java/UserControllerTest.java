package test.java;

import static org.junit.Assert.*;

import DTO.RUserDTO;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import controller.UserController;

import java.sql.SQLException;

public class UserControllerTest {

    private UserController user_controller;

    @Before
    public void setUp() throws SQLException {
        user_controller = new UserController();
    }

    @After
    public void tearDown() throws SQLException {}

    @Test
    public void testRegisterUser() throws SQLException {
        RUserDTO fake_user = user_controller.registerUser("Seconda Università di Napoli","gian.rombanini@outtlook.it","Rombanini","Gianmarco","batuffolino", "autore");

    }

}
