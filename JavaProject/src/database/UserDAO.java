package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Author;
import entity.Organizer;
import entity.User;

public class UserDAO {
    private Connection conn;

    public UserDAO() {
        try {
            this.conn = DBManager.getConnection();
        } catch (SQLException e) {
              e.printStackTrace();
        }
    }

    public String getUserRoleByID(String id) throws SQLException {
        String sql = "SELECT role FROM user WHERE id = " + id;
        try (Statement stmt = conn.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            return rs.getString("role");
        }
    }

    public User getUserByID(String id) throws SQLException {
        String sql = "SELECT affiliazione, email, cognome, nome, password, id, ruolo FROM user WHERE id = " + id;
        Statement stmt = conn.createStatement();
        ResultSet rs =  stmt.executeQuery(sql);
        stmt.close();
        String role = rs.getString("ruolo");
        if (role.equals("Autore")) {
            return new Author(rs.getString("affiliazione"),
                    rs.getString("email"),
                    rs.getString("cognome"),
                    rs.getString("nome"),
                    rs.getString("password"),
                    rs.getString("id"));
        } else {
            return new Organizer(rs.getString("affiliazione"),
                    rs.getString("email"),
                    rs.getString("cognome"),
                    rs.getString("nome"),
                    rs.getString("password"),
                    rs.getString("id"));
        }
    }

    public boolean isUserPresentByID(String id) throws SQLException {
        boolean result = false;
        String sql = "SELECT id FROM user WHERE id = " + id;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        // ResultSet has a next() method that return the next row, if the set is empty (there is no next row) it returns
        // false, so we can verify if a user is in the database with a while loop on rs.next()
        while (rs.next()) {
            result = true;
        }
        return result;
    }

    public boolean isUserPresentByEmail(String email) throws SQLException {
        boolean result = false;
        String sql = "SELECT id FROM user WHERE email = " + email;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        // ResultSet has a next() method that return the next row, if the set is empty (there is no next row) it returns
        // false, so we can verify if a user is in the database with a while loop on rs.next()
        while (rs.next()) {
            result = true;
        }
        return result;
    }

    public User getUserByEmail(String email) throws SQLException {
        String sql = "SELECT affiliazione, email, cognome, nome, password, id, ruolo FROM user WHERE email = " + email;
        Statement stmt = conn.createStatement();
        ResultSet rs =  stmt.executeQuery(sql);
        stmt.close();
        String role = rs.getString("ruolo");
        if (role.equals("Autore")) {
            return new Author(rs.getString("affiliazione"),
                    rs.getString("email"),
                    rs.getString("cognome"),
                    rs.getString("nome"),
                    rs.getString("password"),
                    rs.getString("id"));
        } else {
            return new Organizer(rs.getString("affiliazione"),
                    rs.getString("email"),
                    rs.getString("cognome"),
                    rs.getString("nome"),
                    rs.getString("password"),
                    rs.getString("id"));
        }
    }

    public List<Author> getAllAuthors() throws SQLException {
        ArrayList<Author> authors = new ArrayList<Author>();
        String sql = "SELECT nome cognome email affiliazione id password FROM user WHERE role = 'Autore'";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()) {
            Author a = new Author(rs.getString("affiliazione"),
                    rs.getString("email"),
                    rs.getString("cognome"),
                    rs.getString("nome"),
                    rs.getString("password"),
                    rs.getString("id"));
            authors.add(a);
        }

        return authors;
    }

    public void saveUser(User user) throws SQLException {
        if (user.getRole().equals("autore")) {
            Author a = (Author) user;
            String sql = "INSERT INTO user(id, nome, cognome, email, password, affiliazione, ruolo) VALUES("
                    + a.getId() + ","
                    + a.getName()+ ","
                    + a.getLastName() + ","
                    + a.getEmail() + ","
                    + a.getPassword() + ","
                    + "autore);";
            Statement stmt = conn.createStatement();
            int nRowsUpdated = stmt.executeUpdate(sql);
        } else if (user.getRole().equals("organizer")) {
            Organizer o = (Organizer) user;
            String sql = "INSERT INTO user(id, nome, cognome, email, password, affiliazione, ruolo) VALUES("
                    + o.getId() + ","
                    + o.getName()+ ","
                    + o.getLastName() + ","
                    + o.getEmail() + ","
                    + o.getPassword() + ","
                    + "organizzatore);";
            Statement stmt = conn.createStatement();
            int nRowsUpdated = stmt.executeUpdate(sql);
        }
    }
}
