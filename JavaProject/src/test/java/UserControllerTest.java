package test.java;

import static org.junit.Assert.*;
import DTO.RUserDTO;
import database.UserDAO;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import controller.UserController;

import java.sql.SQLException;

public class UserControllerTest {

    private UserController user_controller;
    private UserDAO dao;

    @Before
    public void setUp() throws SQLException {
        user_controller = new UserController();
        dao = new UserDAO();
    }

    @After
    public void tearDown() throws SQLException {}

    @Test
    public void testRegisterUserIsPresent() throws SQLException {
        RUserDTO fake_user = user_controller.registerUser("Seconda Università di Napoli","gian.rombanini@outlook.it","Rombanini","Gianmarco","batuffolino", "autore");
        assertNull(fake_user);

    }

    @Test
    public void testRegisterUserIsNotPresent() throws SQLException {
        /*Bisogna cambiare solo l'email per vedere che il test e andato a buon fine*/
        String new_email = "ppppp.bbb@gmail.com";
        RUserDTO fake_user =user_controller.registerUser("Seconda Università di Napoli",new_email,"Rombanini","Gianmarco","batuffolino", "autore");
        assertEquals(new_email,fake_user.getEmail());
    }

    @Test
    public void testLoginUserOK() throws SQLException {
        RUserDTO fake_user = user_controller.login("gian.rombanini@outlook.it","batuffolino");
        assertEquals(fake_user.getEmail(),"gian.rombanini@outlook.it");
    }

    @Test
    public void testLoginUserNotOK() throws SQLException {
        RUserDTO fake_user = user_controller.login("vpn.north@outtlook.it","NINTENDO");
        assertNull(fake_user);
    }

    @Test
    public void testGetAuthorByEmailOK() throws SQLException {
        RUserDTO fake_user = user_controller.getRAuthorBYEmail("gian.rombanini@outlook.it");
        assertEquals("autore",fake_user.getRole());
    }

    @Test
    public void testGetAuthorByEmailNotOK() throws SQLException {
        RUserDTO fake_user = user_controller.getRAuthorBYEmail("domenico.cotroneo@unina.it");
        assertNull(fake_user);
    }
}
