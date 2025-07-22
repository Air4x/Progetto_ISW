package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import utility.ID;

public class ReviewDAO {
    private Connection conn;

    public ReviewDAO() throws SQLException{
	conn = DBManager.getConnection();
    }

    public boolean hasConflitOfInterest(ID idArt, ID idUser) throws SQLException {
	ArrayList<String> autori = new ArrayList<>();
	String fromAutori = "SELECT id_aut FROM Autori WHERE id_art = ?;";
	PreparedStatement stAutori = conn.prepareStatement(fromAutori);
	stAutori.setString(1, idArt.toString());
	ResultSet rsAutori = stAutori.executeQuery();
	while(rsAutori.next()) {
	    autori.add(rsAutori.getString("id_aut"));
	}
	if (autori.contains(idUser.toString())) {
	    return true;
	} else {
	    return false;
	}
    }

    /**
     * Permette di assegnare un autore come revisore ad un articolo
     *
     * @param L'id dell'articolo
     * @param L'id del revisore
     */
    public void assignReviewer(ID idArt, ID idRev) throws SQLException {
	String intoRevisori = "INSERT INTO Revisori VALUES(?, ?);";
	PreparedStatement stRevisori = conn.prepareStatement(intoRevisori);
	stRevisori.setString(1, idArt.toString());
	stRevisori.setString(2, idRev.toString());
	stRevisori.executeUpdate();
    }

    public ArrayList<String> getReviewersByArticles(ID idArt) throws SQLException {
	ArrayList<String> revisori = new ArrayList<>();
	String fromRevisori = "SELECT id_rev FROM Revisori WHERE id_art = ?;";
	PreparedStatement stRevisori = conn.prepareStatement(fromRevisori);
	stRevisori.setString(1, idArt.toString());
	ResultSet rsRevisori = stRevisori.executeQuery(fromRevisori);
	while(rsRevisori.next()) {
	    revisori.add(rsRevisori.getString("id_rev"));
	}
	return revisori;
    }
}
