package org.groupone.database;

import java.sql.SQLException;
import java.util.ArrayList;

import org.groupone.entity.Article;
import org.groupone.entity.Author;
import org.groupone.utility.ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;



public class ArticleDAOTest {


    @Test
    public void saveArticle() {
	ArrayList<Author> autori = new ArrayList<>();
	autori.add(new Author("Federico II", "test_autore_10@test.com", "Bartell", "Cindy", "A0sD9fG8hJ7kL6mN", new ID("64393265-3937-4261-b563-326137626135")));
	autori.add(new Author("Federico II", "test_autore_7@test.com", "Ledner", "Garth", "HjK9lMnB8vCxZ7aQ", new ID("34633933-3933-4832-b533-373065323631")));
	Article a = new Article(ID.generate(), "ARTICOLO_PROVA", autori, "PROVA");
	try {
	    ArticleDAO dao = new ArticleDAO();
	    dao.saveArticle(a);
	} catch (SQLException e) {
	    e.printStackTrace();
	    fail("saveArticle - Found exception");
	}
    }
    
    @Test
    public void getArticleById() {
	ID id = new ID("62366665-3430-4435-b764-316461623265");
	ArrayList<Author> autori = new ArrayList<>();
	// autori
	/// id1: 64393265-3937-4261-b563-326137626135
	/// id2: 34633933-3933-4832-b533-373065323631
	autori.add(new Author("Federico II", "test_autore_10@test.com", "Bartell", "Cindy", "A0sD9fG8hJ7kL6mN", new ID("64393265-3937-4261-b563-326137626135")));
	autori.add(new Author("Federico II", "test_autore_7@test.com", "Ledner", "Garth", "HjK9lMnB8vCxZ7aQ", new ID("34633933-3933-4832-b533-373065323631")));
	Article expected = new Article(new ID("62366665-3430-4435-b764-316461623265"), "Test Abstract", autori, "interdum orci condimentum");
	try {
	    ArticleDAO dao = new ArticleDAO();
	    Article result = dao.getArticleByID(id);
	    assertTrue(result.getTitle().equals(expected.getTitle()));
	} catch (SQLException e){
	    e.printStackTrace();
	    fail("getArticleByID - Found Exception");
	}
    }

    @Test
    public void getArticleByAuthorsOK(){
	ID id = new ID("64393265-3937-4261-b563-326137626135");
	try {
	    ArticleDAO dao = new ArticleDAO();
	    ArrayList<Article> actual = dao.getArticlesByAuthor(id);
	    assertTrue("getArticleByAuthors - articoli non trovati", actual.size() > 0);
	} catch (SQLException e) {
	    e.printStackTrace();
	    fail("getArticleByAuthors - Found exception");
	}
    }

	@Test
    public void getArticleByAuthorsArticleNotFound(){
	ID id = ID.generate();
	try {
	    ArticleDAO dao = new ArticleDAO();
	    ArrayList<Article> actual = dao.getArticlesByAuthor(id);
	    assertTrue("getArticleByAuthors - articoli non trovati", actual.size() <= 0);
	} catch (SQLException e) {
	    e.printStackTrace();
	    fail("getArticleByAuthors - Found exception");
	}
    }

    @Test
    public void isArticlePresentByID(){
	ID id = new ID("64383965-3762-4265-a539-316463343833");
	try {
	    ArticleDAO dao = new ArticleDAO();
	    assertTrue("isArticlePresentByID - articolo non trovato", dao.isArticlePresentByID(id));
	} catch (SQLException e){
	    e.printStackTrace();
	    fail("isArticlePresentByID - Found exception");
	}
    }

}
