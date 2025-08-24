package org.groupone.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.groupone.entity.Article;
import org.groupone.entity.Author;
import org.groupone.utility.ID;


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
}
