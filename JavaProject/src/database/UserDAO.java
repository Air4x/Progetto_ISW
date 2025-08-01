package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import entity.Author;
import entity.Organizer;
import entity.User;
import utility.ID;


/**
 * Classe responsabile per le operazioni sulla tabella utente del database
 *
 * @author Mario Calcagno
 */
public class UserDAO {
    /**
     * La connesione al database, ottenuta da DBManager
     */
    private Connection conn;

    /**
     * Costruttore di UserDAO, prova a creare una connesione con il database
     *
     */
    public UserDAO() {
        try {
            this.conn = DBManager.getConnection();
        } catch (SQLException e) {
              e.printStackTrace();
        }
    }

    /**
     * Permette di ottenere il ruolo di un utente dato il suo id
     *
     * @param l'id dell'utente
     * @return una stringa, che può essere "autore" o "organizzatore"
     */
    public String getUserRoleByID(ID id) throws SQLException {
        String sql = "SELECT role FROM user WHERE id = ?";
	PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.setString(1, id.toString());
	ResultSet rs = stmt.executeQuery();
	return rs.getString("role");
    }

    /**
     * Permette di ottenere un utente dato il suo id
     *
     * @param l'id dell'utente
     * @return Un istanza della classe Author/Organizer rappresentante l'utente
     */
    public User getUserByID(ID id) throws SQLException {
        String sql = "SELECT affiliazione, email, cognome, nome, password, id, ruolo FROM user WHERE id = ?";
	PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.setString(1, id.toString());
	ResultSet rs =  stmt.executeQuery();
        stmt.close();
        String role = rs.getString("ruolo");
        if (role.equals("Autore")) {
            return new Author(rs.getString("affiliazione"),
			      rs.getString("email"),
			      rs.getString("cognome"),
			      rs.getString("nome"),
			      rs.getString("password"),
			      new ID(rs.getString("id")));
        } else {
            return new Organizer(rs.getString("affiliazione"),
				 rs.getString("email"),
				 rs.getString("cognome"),
				 rs.getString("nome"),
				 rs.getString("password"),
				 new ID(rs.getString("id")));
        }
    }

    /**
     * Predicato che verifica la presenza di un utente nel database
     * dato il suo id
     * @param l'id dell'utenet
     * @return se l'utente è presente o meno
     */
    public boolean isUserPresentByID(ID id) throws SQLException {
        boolean result = false;
        String sql = "SELECT id FROM user WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.setString(1, id.toString());
        ResultSet rs = stmt.executeQuery();
        // ResultSet has a next() method that return the next row, if the set is empty (there is no next row) it returns
        // false, so we can verify if a user is in the database with a while loop on rs.next()
        while (rs.next()) {
            result = true;
        }
        return result;
    }

    /**
     * Predicato che verifica la presenza di un utente data la sua
     * email
     * @param l'email dell'utente
     * @return se l'utente è presente o meno
     */
    public boolean isUserPresentByEmail(String email) throws SQLException {
        boolean result = false;
        String sql = "SELECT id FROM user WHERE email = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        // ResultSet has a next() method that return the next row, if
        // the set is empty (there is no next row) it returns false,
        // so we can verify if a user is in the database with a while
        // loop on rs.next()
        while (rs.next()) {
            result = true;
        }
        return result;
    }

    /**
     * Permette di ottenere un utente data la sua email
     *
     * @param l'email dell'utente
     * @return un istanza di Author/Organizer che rappresenta l'utente
     */
    public User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT affiliazione, email, cognome, nome, password, id, ruolo FROM user WHERE email = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.setString(1, email);
        ResultSet rs =  stmt.executeQuery();
        stmt.close();
        String role = rs.getString("ruolo");
        if (role.equals("Autore")) {
            return new Author(rs.getString("affiliazione"),
			      rs.getString("email"),
			      rs.getString("cognome"),
			      rs.getString("nome"),
			      rs.getString("password"),
			      new ID(rs.getString("id")));
        } else {
            return new Organizer(rs.getString("affiliazione"),
				 rs.getString("email"),
				 rs.getString("cognome"),
				 rs.getString("nome"),
				 rs.getString("password"),
				 new ID(rs.getString("id")));
        }
    }


    /**
     * Permette di ottenere una lista di tutti gli autori presenti nel
     * database
     *
     * @return La lista di tutti gli autori nel database
     */
    public ArrayList<Author> getAllAuthors() throws SQLException {
        ArrayList<Author> authors = new ArrayList<Author>();
        String sql = "SELECT nome cognome email affiliazione id password FROM user WHERE role = 'Autore'";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            Author a = new Author(rs.getString("affiliazione"),
				  rs.getString("email"),
				  rs.getString("cognome"),
				  rs.getString("nome"),
				  rs.getString("password"),
				  new ID(rs.getString("id")));
            authors.add(a);
        }

        return authors;
    }

    /**
     * Permette di inserire un nuovo utente all'interno del database
     *
     * @param Un utente da salvare
     */
    public void saveUser(User user) throws SQLException {
        if (user.getRole().equals("autore")) {
            Author a = (Author) user;
            String sql = "INSERT INTO user(id, nome, cognome, email, password, affiliazione, ruolo) VALUES(?, ?, ?, ?, ?, 'autore');";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, a.getId().toString());
	    stmt.setString(2, a.getName());
	    stmt.setString(3, a.getLastName());
	    stmt.setString(4, a.getEmail());
	    stmt.setString(5, a.getPassword());
	    int nRowsUpdated = stmt.executeUpdate();
        } else if (user.getRole().equals("organizer")) {
            Organizer o = (Organizer) user;
            String sql = "INSERT INTO user(id, nome, cognome, email, password, affiliazione, ruolo) VALUES(?, ?, ? ,?, ?, 'organizzatore);'";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, o.getId().toString());
	    stmt.setString(2, o.getName());
	    stmt.setString(3, o.getLastName());
	    stmt.setString(4, o.getEmail());
	    stmt.setString(5, o.getPassword());
            int nRowsUpdated = stmt.executeUpdate(sql);
        }
    }
}
