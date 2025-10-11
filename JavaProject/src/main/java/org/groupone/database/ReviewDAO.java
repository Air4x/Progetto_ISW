package org.groupone.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.groupone.utility.ID;
import org.groupone.entity.Review;
import org.groupone.entity.Article;
import org.groupone.entity.Author;


/**
 * Classe responsabile per le operazioni sulla tabella registro nel database
 *
 */
public class ReviewDAO {
    /**
     * La connesione al database
     *
     */
    private Connection conn;

    /**
     * Il costruttore di ReviewDAO, si occupa di impostare la
     * connesione al database
     *
     */
    public ReviewDAO() throws SQLException{
	conn = DBManager.getConnection();
    }

    /**
     * Predicato che verifica la presenza di conflitti di interesse
     *
     * @param L'id dell'articolo
     * @param L'id del possibile revisore
     *
     * @return Se ci sono dei conflitti di interesse
     */
    public boolean hasConflitOfInterest(ID idArt, ID idUser) throws SQLException {
	ArrayList<String> autori = new ArrayList<>();
	String fromAutori = "SELECT ID_UTENTE FROM Autori WHERE ID_ARTICOLO = ?;";
	PreparedStatement stAutori = conn.prepareStatement(fromAutori);
	stAutori.setString(1, idArt.toString());
	ResultSet rsAutori = stAutori.executeQuery();
	while(rsAutori.next()) {
	    autori.add(rsAutori.getString("ID_UTENTE"));
	}
	if (autori.contains(idUser.toString())) {
	    return true;
	} else {
	    return false;
	}
    }


    /**
     * Permette di salvare una revisione nel database
     *
     * @param review, la revisione da salvare
     */
    public void saveReview(Review review) throws SQLException {
	String intoRevisioni = "INSERT INTO Revisioni VALUES (?, ?, ?, ?, ?);";
	PreparedStatement stRevisioni = conn.prepareStatement(intoRevisioni);
	stRevisioni.setString(1, review.getId().toString());
	stRevisioni.setString(2, review.getReviewer().getId().toString());
	stRevisioni.setString(3, review.getArticle().getId().toString());
	stRevisioni.setInt(4, review.getScore());
	stRevisioni.setString(5, review.getResult());
	stRevisioni.executeUpdate();
    }

    /**
     * Permette di ottenere la lista di tutte le revisioni assegnare
     * ad un dato autore
     *
     * @param reviewer, l'id del revisore
     */
    public List<Review> getAllReviewByReviewer(ID reviewer) throws SQLException {
	ArrayList<Review> revs = new ArrayList<>();
	UserDAO userdao = new UserDAO();
	ArticleDAO articledao = new ArticleDAO();
	String fromRevisioni = "SELECT ID, ID_REVISORE, ID_ARTICOLO, PUNTEGGIO, ESITO FROM Revisioni WHERE ID_REVISORE = ?;";
	PreparedStatement stRevisioni = conn.prepareStatement(fromRevisioni);
	stRevisioni.setString(1, reviewer.toString());
	ResultSet rs = stRevisioni.executeQuery();
	while(rs.next()){
	    Article art = articledao.getArticleByID(new ID(rs.getString("ID_ARTICOLO")));
	    Author aut = (Author) userdao.getUserByID(new ID(rs.getString("ID_REVISORE")));
	    revs.add(new Review(new ID(rs.getString("ID")), aut, art, rs.getInt("PUNTEGGIO"), rs.getString("ESITO")));
	}
	return revs;
     }
    
    /**
     * Permette di ottenere la lista di tutte le revisioni di un dato
     * articolo
     *
     * @param articolo, l'id dell'articolo
     */
    public List<Review> getAllReviewByArticle(ID article) throws SQLException {
	ArrayList<Review> revs = new ArrayList<>();
	UserDAO userdao = new UserDAO();
	ArticleDAO articledao = new ArticleDAO();
	String fromRevisioni = "SELECT ID, ID_REVISORE, ID_ARTICOLO, PUNTEGGIO, ESITO FROM Revisioni WHERE ID_ARTICOLO = ?;";
	PreparedStatement stRevisioni = conn.prepareStatement(fromRevisioni);
	stRevisioni.setString(1, article.toString());
	ResultSet rs = stRevisioni.executeQuery();
	while(rs.next()){
	    Article art = articledao.getArticleByID(new ID(rs.getString("ID_ARTICOLO")));
	    Author aut = (Author) userdao.getUserByID(new ID(rs.getString("ID_REVISORE")));
	    revs.add(new Review(new ID(rs.getString("ID")), aut, art, rs.getInt("PUNTEGGIO"), rs.getString("ESITO")));
	}
	return revs;
     }

    /**
     * Permette di aggiornare il punteggio e l'esito di una revisione.
     *
     * @param rev, la Review da aggiornare
     */
    public void updateReview(Review rev) throws SQLException{
	String st = "UPDATE Revisioni SET ESITO = ?, PUNTEGGIO = ? WHERE ID = ?;";
	PreparedStatement pst = conn.prepareStatement(st);
	pst.setString(1, rev.getResult());
	pst.setInt(2, rev.getScore());
	pst.setString(3, rev.getId().toString());
	pst.executeUpdate();
    }
    
    
    /**
     * Permette di assegnare un autore come revisore ad un articolo
     *
     * @param L'id dell'articolo
     * @param L'id del revisore
     */
    @Deprecated
    public void assignReviewer(ID idArt, ID idRev) throws SQLException {
	String intoRevisori = "INSERT INTO Revisori VALUES(?, ?);";
	PreparedStatement stRevisori = conn.prepareStatement(intoRevisori);
	stRevisori.setString(1, idArt.toString());
	stRevisori.setString(2, idRev.toString());
	stRevisori.executeUpdate();
    }

    /**
     * Permette di ottenere tutti i revisori assegnati ad un articolo
     *
     * @param L'id dell'articolo
     * @return La lista dei revisori assegnati
     */
    @Deprecated
    public ArrayList<String> getReviewersByArticle(ID idArt) throws SQLException {
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
    
    /**
     * Permette di aggiornare lo stato di un articolo
     *
     * @param L'id dell'articolo
     * @param Il nuovo stato
     */
    @Deprecated
    public void updateArticleStatus(ID id, String status) throws SQLException{
	String intoRegistro = "UPDATE Registro SET STATUS = ? WHERE id_art = ?;";
	PreparedStatement st = conn.prepareStatement(intoRegistro);
	st.setString(1, status);
	st.setString(2, id.toString());
	st.executeUpdate();
    }
}
