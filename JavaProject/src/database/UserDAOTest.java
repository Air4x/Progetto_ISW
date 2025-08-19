package database;

import java.sql.SQLException;

import entity.User;
import  org.junit.Test;

import static org.junit.Assert.*;

public class UserDAOTest {
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
			System.out.println(u);
			assertNotNull("failure - utente non trovato", u);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
