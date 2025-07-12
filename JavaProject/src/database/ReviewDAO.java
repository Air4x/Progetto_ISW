package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import java.util.ArrayList;


public class ReviewDAO {
    private Connection conn;

    public ReviewDAO(){
	conn = DBManager.getConnection();
    }

    public boolean hasConflitOfInterest(String idArt, String idUser) throws SQLException {
	ArrayList<String> autori = new ArrayList<>();
	String fromAutori = "SELECT id_aut FROM Autori WHERE id_art = " + idArt +";";
	Statement stAutori = conn.createStatement();
	ResultSet rsAutori = stAutori.executeQuery(fromAutori);
	while(rsAutori.next()) {
	    autori.add(rsAutori.getString("id_aut"));
	}
	if (autori.contains(idUser)) {
	    return true;
	} else {
	    return false;
	}
    }
    
    public void assignReviewer(String idArt, String idRev) throws SQLException {
	String intoRevisori = "INSERT INTO Revisori VALUES("+idArt+","+idRev+");";
	Statement stRevisori = conn.createStatement();
	stRevisori.executeUpdate(intoRevisori);
    }

    public ArrayList<String> getReviewersByArticles(String idArt) throws SQLException {
	ArrayList<String> revisori = new ArrayList<>();
	String fromRevisori = "SELECT id_rev FROM Revisori WHERE id_art = "+ idArt + ";";
	Statement stRevisori = conn.createStatement();
	ResultSet rsRevisori = stRevisori.executeQuery(fromRevisori);
	while(rsRevisori.next()) {
	    revisori.add(rsRevisori.getString("id_rev"));
	}
	return revisori;
    }
}
