package database;

import entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import utility.ID;


/**
 * Classe responsabile per le operazioni sulla tabella articoli del
 * database
 *
 * @author Mario Calcagno
 */
public class ArticleDAO {
    /**
     * La connesione al database
     *
     */
    private Connection conn;

    /**
     * Costruttore di ArticleDAO, imposta la connesione al database
     *
     */
    public ArticleDAO() throws SQLException {
	conn = DBManager.getConnection();
    }

    /**
     * Permette di aggiungere un articolo al database
     *
     * @param l'articolo che si vuole salvare
     */
    public void saveArticle(Article a) throws SQLException {
	String sql = "INSERT INTO Articoli VALUES (?, ?, ?)";
	PreparedStatement pst = conn.prepareStatement(sql);
	pst.setNString(1, a.getId().toString());
	pst.setNString(2, a.getTitle());
	pst.setNString(3, a.getAbstr());
	int nRowsUpdated = pst.executeUpdate();
    }

    /**
     * Permette di ottenere un articolo dato il suo Id
     *
     * @param l'id dell'articolo
     * @return un'instanza della classe Article rappresentante
     * l'articolo ottenuto
     */
    public Article getArticleByID(ID id) throws SQLException {
	String titolo = null;
	String abs = null;
	ArrayList<Author> autori = new ArrayList<>();
	// ======To get title, id and abstract=====
	String fromArticoli = "SELECT TITOLO, ABSTRACT, ID FROM Articoli WHERE id = ?;";
	PreparedStatement stArticoli = conn.prepareStatement(fromArticoli);
	stArticoli.setString(1, id.toString());
	ResultSet rsArticoli = stArticoli.executeQuery();
	while(rsArticoli.next()){
	    titolo = rsArticoli.getString("TITOLO");
	    abs = rsArticoli.getString("ABSTRACT");
	}
	// ======To get all the authors========
	String fromAutori = "SELECT id_aut FROM Autori WHERE id_art = ?;";
	PreparedStatement stAutori = conn.prepareStatement(fromAutori);
	stAutori.setString(1, id.toString());
	ResultSet rsAutori = stArticoli.executeQuery();
	String fromUtenti = "SELECT NOME, COGNOME, AFFILIAZIONE, EMAIL, PASSWORD, ID FROM Utenti WHERE RUOLO = ? AND ID = ?";
	PreparedStatement pstUtenti= conn.prepareStatement(fromUtenti);
	while(rsAutori.next()) {
	    pstUtenti.setNString(1, "autore");
	    pstUtenti.setNString(2 , rsAutori.getString("id_aut"));
	    ResultSet rsUtenti = pstUtenti.executeQuery();
	    while(rsUtenti.next()){
		String affiliazione = rsUtenti.getString("AFFILIAZIONE");
		String email = rsUtenti.getString("EMAIL");
		String cognome = rsUtenti.getString("COGNOME");
		String nome = rsUtenti.getString("NOME");
		String password = rsUtenti.getString("PASSWORD");
		Author a = new Author(affiliazione, email, cognome, nome, password, new ID(rsAutori.getString("id_aut")));
		autori.add(a);
	    }  
	}
	return new Article(id, abs, autori, titolo);
    }

    /**
     * Permette di ottenere tutti gli articoli scritti da un'autore
     * 
     * @param L'id dell'autore
     * @return La lista di articoli scritti dall'autore
     */
    public ArrayList<Article> getArticlesByAuthor(ID id_aut) throws SQLException {
		ArrayList<Integer> articleIds = new ArrayList<>();
		ArrayList<Article> articoli = new ArrayList<>();
		String fromAutori = "SELECT id_art FROM Autori WHERE id_aut = "+ id_aut.toString();
		PreparedStatement stAutori = conn.prepareStatement(fromAutori);
		stAutori.setString(1, id_aut.toString());
		ResultSet rsAutori = stAutori.executeQuery();
		while(rsAutori.next()){
			Article a = getArticleByID(new ID(rsAutori.getString("id_art")));
			articoli.add(a);
		}
		return articoli;
	}


}
