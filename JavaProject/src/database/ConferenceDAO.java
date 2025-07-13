package database;

import entity.Articolo;
import entity.Conference;
import entity.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConferenceDAO {
    private Connection conn;

    public ConferenceDAO() throws SQLException {
        try {
            this.conn = DBManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Conference getConferenceByID(String id) throws SQLException {
        String sql = "SELECT * FROM conference WHERE id = " + id;
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return new Conference(rs.getDate("scadenza"), rs.getString("titolo"), rs.getString("descrizione"), rs.getString("id"));
    }

    public void saveConference(Conference conf) throws SQLException {
        String sql = "INSERT INTO conferenza(id, titolo, descrizione, scadenza) VALUES("+
                conf.getId() + ","
                + conf.getTitolo() + ","
                + conf.getDescrizione() + ","
                + conf.getScadenza().toString() +");";
        Statement stmt = conn.createStatement();
        int nRowsUpadated = stmt.executeUpdate(sql);
    }

    public List<Articolo> getArticlesByConference(String conf_id) throws SQLException {
	ArrayList<Articolo> articoli = new ArrayList<>();
	// ========Ottenimento id articoli====================
	String queryIdArt = "SELECT id_art FROM REGISTRO WHERE id_art = "+ conf_id;
	Statement stIdArt =  conn.createStatement();
	ResultSet idArt = stIdArt.executeQuery(queryIdArt);
	// =======Ottenimento id autori=======================
	String queryIdAuth = "SELECT id_aut FROM AUTORI WHERE id_art = ";
	Statement stIdAuth = conn.createStatement();
	// =======Ottenimento dati autore=====================
	String queryAuth = "SELECT id, nome, cognome, password, affiliazione, email FROM USER WHERE id = ";
	Statement stAuth = conn.createStatement();
	// =======Ottenimento dati articolo==================
	String queryArt = "SELECT id, titolo, abstract FROM Articoli WHERE id = ";
	Statement stArt = conn.createStatement();
	while(idArt.next()){
	    ArrayList<Author> autori = new ArrayList<>();
	    ResultSet idAuth = stIdAuth.executeQuery(queryIdAuth + idArt.getInt("id_art"));
	    while(idAuth.next()){
		ResultSet authors = stAuth.executeQuery(queryAuth + idAuth.getInt(1));
		Author a = new Author(authors.getString("affiliazione"), authors.getString("email"),
				      authors.getString("cognome"), authors.getString("nome"),
				      authors.getString("password"), authors.getString("id"));
		autori.add(a);
	    }
	    ResultSet article = stArt.executeQuery(queryArt + idArt.getInt("id_art"));
	    Articolo articolo = new Articolo(idArt.getString("id_art"),article.getString("abstract"), autori, article.getString("titolo"));
	    articoli.add(articolo);
	}
	return articoli;
    }

    public List<Conference> getAllConferences() throws  SQLException{
	ArrayList<Conference> conferenze = new ArrayList<>();
	String query  ="SELECT * FROM conferenza";
	Statement st = conn.createStatement();
	ResultSet rs = st.executeQuery(query);
	while(rs.next()){
	    Conference c = new Conference(rs.getDate("scadenza"), rs.getString("titolo"), rs.getString("descrizione"), rs.getString("id"));
	    conferenze.add(c);
	}
	return conferenze;
    }
}
