package database;

import entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ArticleDAO {
    private Connection conn;

    public ArticleDAO() throws SQLException {
	conn = DBManager.getConnection();
    }

    public void saveArticle(Articolo a) throws SQLException {
	String sql = "INSERT INTO Articoli VALUES (?, ?, ?)";
	PreparedStatement pst = conn.prepareStatement(sql);
	pst.setNString(1, a.getId());
	pst.setNString(2, a.getTitolo());
	pst.setNString(3, a.getAbstr());
	int nRowsUpdated = pst.executeUpdate();
    }

    public Articolo getArticleByID(String id) throws SQLException {
	String titolo = null;
	String abs = null;
	ArrayList<Author> autori = new ArrayList<>();
	// ======To get title, id and abstract=====
	String fromArticoli = "SELECT TITOLO, ABSTRACT, ID FROM Articoli WHERE id = "+id;
	Statement stArticoli = conn.createStatement();
	ResultSet rsArticoli = stArticoli.executeQuery(fromArticoli);
	while(rsArticoli.next()){
	    titolo = rsArticoli.getString("TITOLO");
	    abs = rsArticoli.getString("ABSTRACT");
	}
	// ======To get all the authors========
	String fromAutori = "SELECT id_aut FROM Autori WHERE id_art = " + id;
	Statement stAutori = conn.createStatement();
	ResultSet rsAutori = stArticoli.executeQuery(fromAutori);
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
		Author a = new Author(affiliazione, email, cognome, nome, password, rsAutori.getString("id_aut"));
		autori.add(a);
	    }  
	}
	return new Articolo(id, abs, autori, titolo);
    }

    public ArrayList<Articolo> getArticlesByAuthor(String id_aut) throws SQLException {
	ArrayList<Integer> articleIds = new ArrayList<>();
	ArrayList<Articolo> articoli = new ArrayList<>();
	String fromAutori = "SELECT id_art FROM Autori WHERE id_aut = "+ id_aut;
	Statement stAutori = conn.createStatement();
	ResultSet rsAutori = stAutori.executeQuery(fromAutori);
	while(rsAutori.next()){
	    Articolo a = getArticleByID(rsAutori.getString("id_art"));
	    articoli.add(a);
	}
	return articoli;
    }

    public void updateArticleStatus(String id, String status) throws SQLException{
	String intoRegistro = "UPDATE Registro SET STATUS = " + status + "WHERE id_art = " + id;
	Statement st = conn.createStatement();
	st.executeUpdate(intoRegistro);
    }
}
