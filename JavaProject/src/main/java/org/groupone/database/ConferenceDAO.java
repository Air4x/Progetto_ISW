package org.groupone.database;

import org.groupone.entity.Article;
import org.groupone.entity.Author;
import org.groupone.entity.Conference;
import org.groupone.utility.ID;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Classe che si occupa delle operazioni sulla tabella conferenze nel
 * database
 *
 */
public class ConferenceDAO {
    /**
     * La connesione al database
     *
     */
    private Connection conn;

    /**
     * Costruttre di ConferenceDAO, si occupa di impostare la
     * connesione al database
     *
     */
    public ConferenceDAO() throws SQLException {
            this.conn = DBManager.getConnection();
    }

    /**
     * Permette di ottenere una conferenza dato il suo Id
     *
     * @param L'id della conferenza
     * @return Un'instanza della classe Conference rappresentante la
     * conferenza ottenuta
     */
    public Conference getConferenceByID(ID id) throws SQLException {
        String sql = "SELECT ID, TITOLO, DESCRIZIONE, SCADENZA, ORGANIZZATORE FROM Conferenze WHERE ID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.setString(1, id.toString());
        ResultSet rs = stmt.executeQuery();
	rs.next();
        return new Conference(rs.getDate("SCADENZA"), rs.getString("TITOLO"), rs.getString("DESCRIZIONE"), new ID(rs.getString("ID")), new ID(rs.getString("ORGANIZZATORE")));
    }

    /**
     * Dati un id controlla se la conferenza esiste
     *
     * @param id, l'id della conferenza
     * @return se la conferenza esiste o meno
     */
    public boolean isConferencePresentByID(ID id) throws SQLException {
	String sql = "SELECT ID FROM Conferenze WHERE ID=?";
	PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.setString(1, id.toString());
	ResultSet rs = stmt.executeQuery();
	if(rs.next()){
	    return true;
	} else {
	    return false;
	}
    }
    
    /**
     * Permette di salvare una conferenza nel database
     *
     * @param La conferenza da salvare
     */
    public void saveConference(Conference conf) throws SQLException {
        String sql = "INSERT INTO Conferenze(ID, TITOLO, DESCRIZIONE, SCADENZA, ORGANIZZATORE) VALUES(?, ?, ?, ?, ?);";
        PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.setString(1, conf.getId().toString());
	stmt.setString(2, conf.getTitle());
	stmt.setString(3, conf.getDescription());
	stmt.setDate(4, conf.getDeadline());
	stmt.setString(5, conf.getOrganizer());
        int ignore = stmt.executeUpdate();
    }

    /**
     * Permette di ottenere la lista di tutti gli articoli sottomessi
     * ad una conferenza
     *
     * @param L'id della conferenza
     * @return La lista degli articoli sottomessi
     */
    public ArrayList<Article> getArticlesByConference(ID conf_id) throws SQLException {
	ArrayList<Article> articoli = new ArrayList<>();
	// ========Ottenimento id articoli====================
	String queryIdArt = "SELECT id_art FROM Registro WHERE id_conf = ?";
	PreparedStatement stIdArt =  conn.prepareStatement(queryIdArt);
	stIdArt.setString(1, conf_id.toString());
	ResultSet idArt = stIdArt.executeQuery();
	// =======Ottenimento id autori=======================
	String queryIdAuth = "SELECT id_aut FROM Autori WHERE id_art = ?";
	PreparedStatement stIdAuth = conn.prepareStatement(queryIdAuth);
	// =======Ottenimento dati autore=====================
	String queryAuth = "SELECT ID, NOME, COGNOME, PASSWORD, AFFILIAZIONE, EMAIL FROM Utenti WHERE ID = ?";
	PreparedStatement stAuth = conn.prepareStatement(queryAuth);
	// =======Ottenimento dati articolo==================
	String queryArt = "SELECT ID, TITOLO, ABSTRACT FROM Articoli WHERE ID = ?";
	PreparedStatement stArt = conn.prepareStatement(queryArt);
	// =======Esecuzione queries=========================
	while(idArt.next()){
	    ArrayList<Author> autori = new ArrayList<>();
	    stIdAuth.setString(1, idArt.getString("id_art"));
	    ResultSet idAuth = stIdAuth.executeQuery();
	    while(idAuth.next()){
		stAuth.setString(1, idAuth.getString("id_aut"));
		ResultSet authors = stAuth.executeQuery();
		authors.next();
		Author a = new Author(authors.getString("AFFILIAZIONE"), authors.getString("EMAIL"),
				      authors.getString("COGNOME"), authors.getString("NOME"),
				      authors.getString("PASSWORD"), new ID(authors.getString("ID")));
		autori.add(a);
	    }
	    stArt.setString(1, idArt.getString("id_art"));
	    ResultSet article = stArt.executeQuery();
	    article.next();
	    Article articolo = new Article(new ID(idArt.getString("id_art")),article.getString("ABSTRACT"), autori, article.getString("TITOLO"));
	    articoli.add(articolo);
	}
	return articoli;
    }

    /**
     * Permette di ottenere una lista di tutte le conferenze nel database
     *
     * @return La lista di tutte le conferenze
     */
    public ArrayList<Conference> getAllConference() throws  SQLException{
	ArrayList<Conference> conferenze = new ArrayList<>();
	String query  ="SELECT * FROM Conferenze";
	PreparedStatement st = conn.prepareStatement(query);
	ResultSet rs = st.executeQuery();
	while(rs.next()){
	    Conference c = new Conference(rs.getDate("SCADENZA"), rs.getString("TITOLO"), rs.getString("DESCRIZIONE"), new ID(rs.getString("ID")), new ID(rs.getString("ORGANIZZATORE")));
	    conferenze.add(c);
	}
	return conferenze;
    }

    /**
     * Permette di ottenere la lista di tutte le conferenze attive
     *
     * @return La lista di tutte le conferenze attive
     */
    public ArrayList<Conference> getActiveConference() throws SQLException {
	ArrayList<Conference> conferenze = getAllConference();
	ArrayList<Conference> conferenzeAttive = new ArrayList<>();
	Date now = new Date(LocalDate.now().toEpochDay());
	for (Conference conf : conferenze) {
	    if(now.before(conf.getDeadline())){
		conferenzeAttive.add(conf);
	    }
	}
	return conferenzeAttive;
    }
}
