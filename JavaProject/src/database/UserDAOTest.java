package database;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Author;
import entity.User;
import org.junit.Test;
import org.junit.Assert.*;
import utility.ID;

import static org.junit.Assert.*;

public class UserDAOTest {

	@Test
	public void isUserPresentByIDPositive() throws SQLException {
		UserDAO dao = new UserDAO();
		ID id = new ID("3a9e468f-ff6b-4a84-bbc0-fb3f9e9c5024");
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
		String email = "domenico.cotroneo@unina.it";
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
		String email = "domenico.cotroneo@unina.it";
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
		ID id = new ID("9c388e06-3c9e-43bd-9327-acbffed869d3");
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
		ID id = new ID("9c388e06-3c9e-43bd-9327-acbffed869d3");
		Author expectedAuthor = new Author("Seconda Università di Napoli", "gian.rombanini@outlook.it", "Rombanini", "Gianmarco", "batuffolino", id);
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
}
