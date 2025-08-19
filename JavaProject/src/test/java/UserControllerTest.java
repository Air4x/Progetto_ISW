package test.java;

import static org.junit.Assert.*;

import java.lang.System;
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
        RUserDTO fake_user =user_controller.registerUser("Seconda Università di Napoli","mario.pezzela@gmail.com","Rombanini","Gianmarco","batuffolino", "autore");
        System.out.println("Nome: "+fake_user.getName()+"\nCognome: "+fake_user.getLastname()+"\nEmail: "+fake_user.getEmail()+"\nAffiliazione: "+fake_user.getAffiliation()+"\nId: "+fake_user.getId()+"\nRuolo: "+fake_user.getRole());
        assertEquals("luca.boticelli@gmail.com",fake_user.getEmail());
    }

    @Test
    public void testLoginUserOK() throws SQLException {
        RUserDTO u = user_controller.login("gian.rombanini@outtlook.it","batuffolino");
        assertEquals(u.getEmail(),"gian.rombanini@outtlook.it");
    }

    @Test
    public void testLogoutUser() throws SQLException {
        boolean res = dao.isUserPresentByEmail("");
        assertEquals(true,res);
    }
}
