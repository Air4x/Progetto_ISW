package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

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
     * @param id l'id dell'utente
     * @return una stringa, che può essere "autore" o "organizzatore"
     */
    public String getUserRoleByID(ID id) throws SQLException {
        String sql = "SELECT RUOLO FROM Utenti WHERE ID = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, id.toString());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getString("RUOLO");
        }
    }

    /**
     * Permette di ottenere un utente dato il suo id
     *
     * @param l'id dell'utente
     * @return Un istanza della classe Autore/Organizer rappresentante l'utente
     */
    public User getUserByID(ID id) throws SQLException {
        String sql = "SELECT AFFILIAZIONE, EMAIL, COGNOME, NOME, PASSWORD, ID, RUOLO FROM Utenti WHERE id = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, id.toString());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String role = rs.getString("RUOLO");
            if (role.equals("autore")) {
                return new Author(rs.getString("AFFILIAZIONE"),
                        rs.getString("EMAIL"),
                        rs.getString("COGNOME"),
                        rs.getString("NOME"),
                        rs.getString("PASSWORD"),
                        new ID(rs.getString("ID")));
            } else {
                return new Organizer(rs.getString("AFFILIAZIONE"),
                        rs.getString("EMAIL"),
                        rs.getString("COGNOME"),
                        rs.getString("NOME"),
                        rs.getString("PASSWORD"),
                        new ID(rs.getString("ID")));
            }
        }
    }

    /**
     * Predicato che verifica la presenza di un utente nel database
     * dato il suo id
     *
     * @param l'id dell'utenet
     * @return se l'utente è presente o meno
     */
    public boolean isUserPresentByID(ID id) throws SQLException {
        boolean result = false;
        String sql = "SELECT ID FROM Utenti WHERE ID = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, id.toString());
            ResultSet rs = stmt.executeQuery();
            // ResultSet has a next() method that return the next row, if the set is empty (there is no next row) it returns
            // false, so we can verify if a user is in the database with a while loop on rs.next()
            while (rs.next()) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Predicato che verifica la presenza di un utente data la sua
     * email
     *
     * @param l'email dell'utente
     * @return se l'utente è presente o meno
     */
    public boolean isUserPresentByEmail(String email) throws SQLException {
        boolean result = false;
        String sql = "SELECT ID FROM Utenti WHERE EMAIL = ?";
        try(PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            // ResultSet has a next() method that return the next row, if
            // the set is empty (there is no next row) it returns false,
            // so we can verify if a user is in the database with a while
            // loop on rs.next()
            while (rs.next()) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Permette di ottenere un utente data la sua email
     *
     * @param l'email dell'utente
     * @return un istanza di Autore/Organizer che rappresenta l'utente
     */
    public User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT AFFILIAZIONE, EMAIL, COGNOME, NOME, PASSWORD, ID, RUOLO FROM Utenti WHERE EMAIL = ?;";
        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            String role = rs.getString("RUOLO");
            if (role.equals("autore")) {
                return new Author(rs.getString("AFFILIAZIONE"),
                        rs.getString("EMAIL"),
                        rs.getString("COGNOME"),
                        rs.getString("NOME"),
                        rs.getString("PASSWORD"),
                        new ID(rs.getString("ID")));
            } else {
                return new Organizer(rs.getString("AFFILIAZIONE"),
                        rs.getString("EMAIL"),
                        rs.getString("COGNOME"),
                        rs.getString("NOME"),
                        rs.getString("PASSWORD"),
                        new ID(rs.getString("ID")));
            }

        }
    }


    /**
     * Permette di ottenere una lista di tutti gli autori presenti nel
     * database
     *
     * @return La lista di tutti gli autori nel database
     */
    public ArrayList<Author> getAllAuthors() throws SQLException {
        ArrayList<Author> authors = new ArrayList<>();
        String sql = "SELECT NOME, COGNOME, EMAIL, AFFILIAZIONE, ID, PASSWORD FROM Utenti WHERE RUOLO = 'autore'";
        try(PreparedStatement stmt = conn.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery();
            rs.next();
            while (rs.next()) {
                Author a = new Author(rs.getString("AFFILIAZIONE"),
                        rs.getString("EMAIL"),
                        rs.getString("COGNOME"),
                        rs.getString("NOME"),
                        rs.getString("PASSWORD"),
                        new ID(rs.getString("ID")));
                authors.add(a);
            }
        }
        return authors;
    }

    /**
     * Permette di inserire un nuovo user all'interno del database
     *
     * @param Un user da salvare
     */
    public void saveUser(User user) throws SQLException {
        if (user.getRole().equals("autore")) {
            Author a = (Author) user;
            String sql = "Insert Into Utenti(ID, NOME, COGNOME, EMAIL, PASSWORD, AFFILIAZIONE, RUOLO) VALUES(?, ?, ?, ?, ?, ?,'autore');";
            try(PreparedStatement stmt = conn.prepareStatement(sql);) {
                stmt.setString(1, a.getId().toString());
                stmt.setString(2, a.getName());
                stmt.setString(3, a.getLastName());
                stmt.setString(4, a.getEmail());
                stmt.setString(5, a.getPassword());
                stmt.setString(6, a.getAffiliation());
                int _ = stmt.executeUpdate();
            }
        } else if (user.getRole().equals("organizzatore")) {
            Organizer o = (Organizer) user;
            String sql = "INSERT INTO Utenti(ID, NOME, COGNOME, EMAIL, PASSWORD, AFFILIAZIONE, RUOLO) VALUES(?, ?, ? ,?, ?, ?,'organizzatore);'";
            try(PreparedStatement stmt = conn.prepareStatement(sql);) {
                stmt.setString(1, o.getId().toString());
                stmt.setString(2, o.getName());
                stmt.setString(3, o.getLastName());
                stmt.setString(4, o.getEmail());
                stmt.setString(5, o.getPassword());
                stmt.setString(6, o.getAffiliation());
                int _ = stmt.executeUpdate(sql);
            }
        }
    }
}
