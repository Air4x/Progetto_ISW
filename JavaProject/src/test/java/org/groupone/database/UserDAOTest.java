package org.groupone.database;
import java.sql.SQLException;
import java.util.ArrayList;

import org.groupone.entity.Author;
import org.groupone.entity.Organizer;
import org.groupone.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert.*;
import org.groupone.utility.ID;

import static org.junit.Assert.*;

public class UserDAOTest {

	private UserDAO dao;

	@Before
	public void setUP() throws SQLException{
			dao = new UserDAO();
	}

	@Test
	public void isUserPresentByIDPositive() throws SQLException {
		UserDAO dao = new UserDAO();
		ID id = new ID("31353664-3930-4465-a334-323164633932");
		try {
			boolean result = dao.isUserPresentByID(id);
			assertTrue("failure - utente non trovato", result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void isUserPresentByIDNegative() throws SQLException {
		UserDAO dao = new UserDAO();
		ID id = ID.generate();
		try {
			boolean result = dao.isUserPresentByID(id);
			assertFalse("failure - utente non trovato", result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void isUserPresentByEmailPositive() {
		UserDAO dao = new UserDAO();
		String email = "test_autore_1@test.com";
		try {
			boolean result = dao.isUserPresentByEmail(email);
			assertTrue("failure - utente non trovato", result);
			} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void isUserPresentByEmailNegative() {
		UserDAO dao = new UserDAO();
		String email = "foo.bar@bazbazbar.spam";
		try {
			boolean result = dao.isUserPresentByEmail(email);
			assertFalse("failure - utente trovato", result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getUserByEmail() {
		UserDAO dao = new UserDAO();
		String email = "test_autore_1@test.com";
		try {
			User u = dao.getUserByEmail(email);
			assertNotNull("failure - utente non trovato", u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void  getUserRoleByID() {
		UserDAO dao = new UserDAO();
		ID id = new ID("31353664-3930-4465-a334-323164633932");
		String expectedRole = "autore";
		try {
			String role = dao.getUserRoleByID(id);
			assertEquals(expectedRole, role);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getUserByID() {
		UserDAO dao = new UserDAO();
		ID id = new ID("31353664-3930-4465-a334-323164633932");
		Author expectedAuthor = new Author("Federico II", "test_autore_1@test.com", "Barrows", "Cindy", "4hJd8kLpT2xYw0Rz", id);
		try {
			Author author = (Author) dao.getUserByID(id);
			assertEquals(expectedAuthor, author);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getAllAuthors() {
		UserDAO dao = new UserDAO();
		try {
			ArrayList<Author> as = dao.getAllAuthors();
			assertFalse(as.isEmpty());
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testSaveUserOKAuthor(){
		try{
		Author user = new Author("GG","g@gmail.com","Panzatti","Giulio","PPPPP",ID.generate());
		dao.saveUser(user);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Erreor in save user");
		}
	}

	@Test
	public void testSaveUserOrganizer(){
		try{
		Organizer user = new Organizer("GG","g@gmail.com","Panzatti","Giulio","PPPPP",ID.generate());
		dao.saveUser(user);
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Erreor in save user");
		}
	}

	@Test
	public void testSaveUserNullInsert (){
		this.dao= null;
		try{
		Organizer user = new Organizer("GG","g@gmail.com","Panzatti","Giulio","PPPPP",ID.generate());
		dao.saveUser(user); 
		}catch(SQLException | NullPointerException e){
			System.out.println("SQLException");
			assertTrue(true);
		}
	}
}
