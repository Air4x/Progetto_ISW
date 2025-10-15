package org.groupone.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.groupone.DTO.RUserDTO;
import org.groupone.DTO.ShowArticleDTO;
import org.groupone.utility.ID;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

//ok
public class ArticleControllerTest {

    private ArticleController article_controller;
    private UserController user_controller;
    private ConferenceController conference_controller;

    @Before
    public void setUp() throws SQLException {
        article_controller = new ArticleController();
        user_controller = new UserController();
        conference_controller = new ConferenceController();
    }

    @Test
    public void testSubmitArticlesOK() throws SQLException{
        ArrayList<RUserDTO> autori =  new ArrayList<>();
        autori.add(user_controller.getRAuthorBYEmail("test_autore_2@test.com"));
        autori.add(user_controller.getRAuthorBYEmail("test_autore_3@test.com"));
        boolean esito = article_controller.submitArticle("Why Nintendo?","Nintendo",autori,new ID("62646636-3962-4238-b630-343665376536"));
        assertTrue(esito);
    }


    @Test
    public void testSubmitArticlesConferencenotfound() throws SQLException{
        ArrayList<RUserDTO> autori =  new ArrayList<>();
        boolean esito = article_controller.submitArticle("Why Nintendo?","Nintendo",autori,ID.generate());
        assertFalse(esito);
    }

    @Test
    public void testSubmitArticlesConferenceExpired() throws SQLException{
        ArrayList<RUserDTO> autori =  new ArrayList<>();
        autori.add(user_controller.getRAuthorBYEmail("test_autore_2@test.com"));
        autori.add(user_controller.getRAuthorBYEmail("test_autore_3@test.com"));
        boolean esito = article_controller.submitArticle("Why Nintendo?","Nintendo",autori,new ID("75b12873-5f30-4711-a0f6-11a6f9b98b48"));
        assertFalse(esito);
    }

    @Test
    public void testSubmitArticlesListEmpty() throws SQLException{
        ArrayList<RUserDTO> autori =  new ArrayList<>();
        boolean esito = article_controller.submitArticle("Why Nintendo?","Nintendo",autori,new ID("62646636-3962-4238-b630-343665376536"));
        assertFalse(esito);
    }

    @Test
    public void testSubmitArticlesListDTOEmpty() throws SQLException{
        ArrayList<RUserDTO> autori =  new ArrayList<>();
        autori.add(user_controller.getRAuthorBYEmail(null));
        boolean esito = article_controller.submitArticle("Why Nintendo?","Nintendo",autori,new ID("62646636-3962-4238-b630-343665376536"));
        assertFalse(esito);
    }

    @Test
    public void testGetArticleByAuthorOK() throws SQLException{
        ID author_id = new ID ("64393265-3937-4261-b563-326137626135");
        ArrayList<ShowArticleDTO> list_article = new ArrayList<>();
        list_article=this.article_controller.getArticleByAuthor(author_id);
        System.out.println(list_article.toString()+"\n\n");
        assertFalse(list_article.isEmpty());
    }

    @Test
    public void testGetArticleByAuthorNotFound() throws SQLException{
        ID author_id = new ID ("3a9e468f-ff6b-4a84-bbc0-acbffed869d3");
        ArrayList<ShowArticleDTO> list_article = new ArrayList<>();
        list_article=this.article_controller.getArticleByAuthor(author_id);
        assertNull(list_article);
    }

    @Test
    public void testGetArticleByAuthorListEmpty() throws SQLException{
        ID author_id = new ID ("31353664-3930-4465-a334-323164633932");
        ArrayList<ShowArticleDTO> list_article = new ArrayList<>();
        list_article=this.article_controller.getArticleByAuthor(author_id);
        assertNull(list_article);
    }
}
