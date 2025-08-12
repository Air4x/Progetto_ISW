package database;

import entity.Articolo;
import entity.Autore;
import entity.Conferenza;
import utility.ID;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Classe che si occupa delle operazioni sulla tabella conferenze nel
 * database
 *
 */
public class ConferenzeDAO {
    /**
     * La connesione al database
     *
     */
    private Connection conn;

    /**
     * Costruttre di ConferenzeDAO, si occupa di impostare la
     * connesione al database
     *
     */
    public ConferenzeDAO() throws SQLException {
        try {
            this.conn = DBManager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permette di ottenere una conferenza dato il suo Id
     *
     * @param L'id della conferenza
     * @return Un'instanza della classe Conferenza rappresentante la
     * conferenza ottenuta
     */
    public Conferenza getConferenzaByID(ID id) throws SQLException {
        String sql = "SELECT * FROM conference WHERE id = " + id;
        PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.setString(1, id.toString());
        ResultSet rs = stmt.executeQuery();
        return new Conferenza(rs.getDate("scadenza"), rs.getString("titolo"), rs.getString("descrizione"), new ID(rs.getString("id")));
    }

    /**
     * Permette di salvare una conferenza nel database
     *
     * @param La conferenza da salvare
     */
    public void salvaConferenza(Conferenza conf) throws SQLException {
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
    public ArrayList<Articolo> getArticoliByConferenza(ID conf_id) throws SQLException {
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
	    ArrayList<Autore> autori = new ArrayList<>();
	    stIdAuth.setString(1, idArt.getString("id_art"));
	    ResultSet idAuth = stIdAuth.executeQuery();
	    while(idAuth.next()){
		stAuth.setString(1, idAuth.getString(1));
		ResultSet authors = stAuth.executeQuery();
		Autore a = new Autore(authors.getString("affiliazione"), authors.getString("email"),
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

    /**
     * Permette di ottenere una lista di tutte le conferenze nel database
     *
     * @return La lista di tutte le conferenze
     */
    public ArrayList<Conferenza> getTutteConferenze() throws  SQLException{
	ArrayList<Conferenza> conferenze = new ArrayList<>();
	String query  ="SELECT * FROM conferenza";
	PreparedStatement st = conn.prepareStatement(query);
	ResultSet rs = st.executeQuery();
	while(rs.next()){
	    Conferenza c = new Conferenza(rs.getDate("scadenza"), rs.getString("titolo"), rs.getString("descrizione"), new ID(rs.getString("id")));
	    conferenze.add(c);
	}
	return conferenze;
    }

    /**
     * Permette di ottenere la lista di tutte le conferenze attive
     *
     * @return La lista di tutte le conferenze attive
     */
    public ArrayList<Conferenza> getConferenzeAttive() throws SQLException {
	ArrayList<Conferenza> conferenze = getTutteConferenze();
	ArrayList<Conferenza> conferenzeAttive = new ArrayList<>();
	Date now = new Date();
	for (Conferenza conf : conferenze) {
	    if(now.before(conf.getScadenza())){
		conferenzeAttive.add(conf);
	    }
	}
	return conferenzeAttive;
    }


}
