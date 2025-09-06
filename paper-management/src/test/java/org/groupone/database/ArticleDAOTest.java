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
	autori.add(new Author("TEST", "test@test.test", "PROVA", "AUTORE", "test321",new ID("95ba1fc7-bf7e-4764-341b-eac6051437fb")));
	autori.add(new Author("TEST", "test@test.test", "PROVA", "AUTORE", "test321",new ID("3b804533-cd62-5b84-f55b-0074120e0376")));
	autori.add(new Author("TEST", "test@test.test", "PROVA", "AUTORE", "test321",new ID("0dd9344a-04a5-d3a4-f7c3-35894467b3e0")));
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
	ID id = new ID("15e9add4-f93a-4457-b058-b1135eb8e789");
	ArrayList<Author> autori = new ArrayList<>();
	autori.add(new Author("TEST", "test@test.test", "PROVA", "AUTORE", "test321", new ID("0dd9344a-04a5-d3a4-f7c3-35894467b3e0")));
	autori.add(new Author("TEST", "test@test.test", "PROVA", "AUTORE", "test321", new ID("3b804533-cd62-5b84-f55b-0074120e0376")));
	autori.add(new Author("TEST", "test@test.test", "PROVA", "AUTORE", "test321", new ID("95ba1fc7-bf7e-4764-341b-eac6051437fb")));
	Article expected = new Article(id, "ARTICOLO_PROVA", autori, "PROVA");
	try {
	    ArticleDAO dao = new ArticleDAO();
	    Article result = dao.getArticleByID(id);
	    assertEquals(expected, result);
	} catch (SQLException e){
	    e.printStackTrace();
	    fail("getArticleByID - Found Exception");
	}
    }

    @Test
    public void getArticleByAuthorsOK(){
	ID id = new ID("9c388e06-3c9e-43bd-9327-acbffed869d3");
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
	ID id = new ID("2e24cd58-a3d7-4057-a1b8-ce9a24669cea");
	try {
	    ArticleDAO dao = new ArticleDAO();
	    assertTrue("isArticlePresentByID - articolo non trovato", dao.isArticlePresentByID(id));
	} catch (SQLException e){
	    e.printStackTrace();
	    fail("isArticlePresentByID - Found exception");
	}
    }

}
