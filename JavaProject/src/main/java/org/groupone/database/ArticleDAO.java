package org.groupone.database;

import org.groupone.entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.groupone.utility.ID;


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
     * @param [a] l'articolo che si vuole salvare
     */
    public void saveArticle(Article a) throws SQLException {
	String sql = "INSERT INTO Articoli VALUES (?, ?, ?, ?);";
	PreparedStatement pst = conn.prepareStatement(sql);
	pst.setNString(1, a.getId().toString());
	pst.setNString(2, a.getTitle());
	pst.setNString(3, a.getAbstr());
	pst.setNString(4, a.getStato());
	int ignore = pst.executeUpdate();
	String insertAuthors = "INSERT INTO Autori VALUES(?, ?);";
	PreparedStatement stAuthors = conn.prepareStatement(insertAuthors);
	for(Author autore : a.getAuthors()){
	    stAuthors.setString(1, autore.getId().toString());
	    stAuthors.setString(2, a.getId().toString());
	    int nRow = stAuthors.executeUpdate();
	}
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
	String stato = null;
	ArrayList<Author> autori = new ArrayList<>();
	// ======To get title, id and abstract=====
	String fromArticoli = "SELECT TITOLO, ABSTRACT, ID, STATO FROM Articoli WHERE ID = ?;";
	PreparedStatement stArticoli = conn.prepareStatement(fromArticoli);
	stArticoli.setString(1, id.toString());
	ResultSet rsArticoli = stArticoli.executeQuery();
	while(rsArticoli.next()){
	    titolo = rsArticoli.getString("TITOLO");
	    abs = rsArticoli.getString("ABSTRACT");
	    stato = rsArticoli.getString("STATO");
	}
	// ======To get all the authors========
	String fromAutori = "SELECT ID_UTENTE FROM Autori WHERE ID_ARTICOLO = ?;";
	PreparedStatement stAutori = conn.prepareStatement(fromAutori);
	stAutori.setString(1, id.toString());
	ResultSet rsAutori = stAutori.executeQuery();
	String fromUtenti = "SELECT NOME, COGNOME, AFFILIAZIONE, EMAIL, PASSWORD, ID FROM Utenti WHERE RUOLO = ? AND ID = ?";
	PreparedStatement pstUtenti= conn.prepareStatement(fromUtenti);
	while(rsAutori.next()) {
	    pstUtenti.setNString(1, "autore");
	    pstUtenti.setNString(2 , rsAutori.getString("ID_UTENTE"));
	    ResultSet rsUtenti = pstUtenti.executeQuery();
	    while(rsUtenti.next()){
		String affiliazione = rsUtenti.getString("AFFILIAZIONE");
		String email = rsUtenti.getString("EMAIL");
		String cognome = rsUtenti.getString("COGNOME");
		String nome = rsUtenti.getString("NOME");
		String password = rsUtenti.getString("PASSWORD");
		Author a = new Author(affiliazione, email, cognome, nome, password, new ID(rsAutori.getString("ID_UTENTE")));
		autori.add(a);
	    }  
	}
	return new Article(id, abs, autori, titolo, stato);
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
	String fromAutori = "SELECT ID_ARTICOLO FROM Autori WHERE ID_UTENTE = ?";
	PreparedStatement stAutori = conn.prepareStatement(fromAutori);
	stAutori.setString(1, id_aut.toString());
	ResultSet rsAutori = stAutori.executeQuery();
	while(rsAutori.next()){
	    Article a = getArticleByID(new ID(rsAutori.getString("ID_ARTICOLO")));
	    articoli.add(a);
	}
	return articoli;
    }

    /**
     * Dato l'id di un articoli ne verifica la presenza nel database
     *
     * @param id, l'id dell'articolo
     * @return se l'articolo esiste o meno
     */
    public boolean isArticlePresentByID(ID id) throws SQLException {
	String sql = "SELECT ID FROM Articoli WHERE Articoli.ID=?;";
	PreparedStatement stmt = conn.prepareStatement(sql);
	stmt.setString(1, id.toString());
	ResultSet rs = stmt.executeQuery();
	return rs.next();
     }

    
    /**
     * Aggiorna il titolo di un articolo dato il suo id
     *
     * @param artid, l'id dell'articolo
     * @param newTitle, il nuovo titolo dell'articolo
     */
    public void updateTitle(ID artId, String newTitle) throws SQLException {
	String updateQuery = "UPDATE Articoli SET Articoli.TITOLO=? WHERE Articoli.ID=?;";
	PreparedStatement updateArticoli = conn.prepareStatement(updateQuery);
	updateArticoli.setString(1, artId.toString());
	updateArticoli.setString(2, newTitle);
	int ignore = updateArticoli.executeUpdate();
    }

    /**
     * Aggiorna l'abstract di un articolo dato il suo id
     *
     * @param artId, l'id dell'articolo
     * @param newAbs, il nuovo abstract dell'articolo
     */
    public void updateAbstract(ID artId, String newAbs) throws SQLException {
	String updateQuery = "UPDATE Articoli SET Articoli.ABSTRACT=? WHERE Articoli.ID=?;";
	PreparedStatement updateArticoli = conn.prepareStatement(updateQuery);
	updateArticoli.setString(1, artId.toString());
	updateArticoli.setString(2, newAbs);
	int ignore = updateArticoli.executeUpdate();
    }
}
