package database;

import entity.Articolo;
import entity.Conference;
import entity.Author;
import utility.ID;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class ConferenceDAO {
    private Connection conn;

    public ConferenceDAO() throws SQLException {
        try {
            this.conn = DBManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Conference getConferenceByID(ID id) throws SQLException {
        String sql = "SELECT * FROM conference WHERE id = " + id;
        PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.setString(1, id.toString());
        ResultSet rs = stmt.executeQuery();
        return new Conference(rs.getDate("scadenza"), rs.getString("titolo"), rs.getString("descrizione"), new ID(rs.getString("id")));
    }

    public void saveConference(Conference conf) throws SQLException {
        String sql = "INSERT INTO conferenza(id, titolo, descrizione, scadenza) VALUES(?, ?, ?, ?);";
        PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.setString(1, conf.getId().toString());
	stmt.setString(2, conf.getTitolo());
	stmt.setString(3, conf.getDescrizione());
	stmt.setString(4, conf.getScadenza().toString());
        int nRowsUpadated = stmt.executeUpdate();
    }

    /**
     * Permette di ottenere la lista di tutti gli articoli sottomessi
     * ad una conferenza
     *
     * @param L'id della conferenza
     * @return La lista degli articoli sottomessi
     */
    public ArrayList<Articolo> getArticlesByConference(ID conf_id) throws SQLException {
	ArrayList<Articolo> articoli = new ArrayList<>();
	// ========Ottenimento id articoli====================
	String queryIdArt = "SELECT id_art FROM REGISTRO WHERE id_art = ?";
	PreparedStatement stIdArt =  conn.prepareStatement(queryIdArt);
	stIdArt.setString(1, conf_id.toString());
	ResultSet idArt = stIdArt.executeQuery();
	// =======Ottenimento id autori=======================
	String queryIdAuth = "SELECT id_aut FROM AUTORI WHERE id_art = ?";
	PreparedStatement stIdAuth = conn.prepareStatement(queryIdAuth);
	// =======Ottenimento dati autore=====================
	String queryAuth = "SELECT id, nome, cognome, password, affiliazione, email FROM USER WHERE id = ?";
	PreparedStatement stAuth = conn.prepareStatement(queryAuth);
	// =======Ottenimento dati articolo==================
	String queryArt = "SELECT id, titolo, abstract FROM Articoli WHERE id = ?";
	PreparedStatement stArt = conn.prepareStatement(queryArt);
	// =======Esecuzione queries=========================
	while(idArt.next()){
	    ArrayList<Author> autori = new ArrayList<>();
	    stIdAuth.setString(1, idArt.getString("id_art"));
	    ResultSet idAuth = stIdAuth.executeQuery();
	    while(idAuth.next()){
		stAuth.setString(1, idAuth.getString(1));
		ResultSet authors = stAuth.executeQuery();
		Author a = new Author(authors.getString("affiliazione"), authors.getString("email"),
				      authors.getString("cognome"), authors.getString("nome"),
				      authors.getString("password"), new ID(authors.getString("id")));
		autori.add(a);
	    }
	    stArt.setString(1, idArt.getString("id_art"));
	    ResultSet article = stArt.executeQuery(queryArt + idArt.getString("id_art"));
	    Articolo articolo = new Articolo(new ID(idArt.getString("id_art")),article.getString("abstract"), autori, article.getString("titolo"));
	    articoli.add(articolo);
	}
	return articoli;
    }

    public ArrayList<Conference> getAllConferences() throws  SQLException{
	ArrayList<Conference> conferenze = new ArrayList<>();
	String query  ="SELECT * FROM conferenza";
	PreparedStatement st = conn.prepareStatement(query);
	ResultSet rs = st.executeQuery();
	while(rs.next()){
	    Conference c = new Conference(rs.getDate("scadenza"), rs.getString("titolo"), rs.getString("descrizione"), new ID(rs.getString("id")));
	    conferenze.add(c);
	}
	return conferenze;
    }

    public ArrayList<Conference> getActiveConferences() throws SQLException {
	ArrayList<Conference> conferenze = getAllConferences();
	ArrayList<Conference> conferenzeAttive = new ArrayList<>();
	Date now = new Date();
	for (Conference conf : conferenze) {
	    if(now.before(conf.getScadenza())){
		conferenzeAttive.add(conf);
	    }
	}
	return conferenzeAttive;
    }


}
