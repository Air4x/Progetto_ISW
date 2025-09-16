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

    @Before
    public void setUp() throws SQLException {
        article_controller = new ArticleController();
        user_controller = new UserController();
    }

    @Test
    public void testSubmitArticlesOK() throws SQLException{
        ArrayList<RUserDTO> autori =  new ArrayList<>();
        autori.add(user_controller.getRAuthorBYEmail("toolvpstaiscal@gmail.com"));
        autori.add(user_controller.getRAuthorBYEmail("fakenetflix2003b@gmail.com"));
        ID conference_id = new ID("6279c9e1-b121-4c7a-a196-7a43b57fc16d");
        boolean esito = article_controller.submitArticle("Why Nintendo?","Nintendo",autori,conference_id);
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
        autori.add(user_controller.getRAuthorBYEmail("fakenetflix2003b@gmail.com"));
        autori.add(user_controller.getRAuthorBYEmail("toolvpstaiscal@gmail.com"));
        boolean esito = article_controller.submitArticle("Why Nintendo?","Nintendo",autori,new ID("6279c9e1-b121-4c7a-a196-7a43b57fc03d"));
        assertFalse(esito);
    }

    @Test
    public void testSubmitArticlesListEmpty() throws SQLException{
        ArrayList<RUserDTO> autori =  new ArrayList<>();
        boolean esito = article_controller.submitArticle("Why Nintendo?","Nintendo",autori,new ID("611f4dff-28c2-42b9-982d-762a3e9e2b3c"));
        assertFalse(esito);
    }

    @Test
    public void testSubmitArticlesListDTOEmpty() throws SQLException{
        ArrayList<RUserDTO> autori =  new ArrayList<>();
        autori.add(user_controller.getRAuthorBYEmail(null));
        boolean esito = article_controller.submitArticle("Why Nintendo?","Nintendo",autori,new ID("611f4dff-28c2-42b9-982d-762a3e9e2b3c"));
        assertTrue(esito);
    }

    @Test
    public void testGetArticleByAuthorOK() throws SQLException{
        ID author_id = new ID ("9c388e06-3c9e-43bd-9327-acbffed869d3");
        ArrayList<ShowArticleDTO> list_article = new ArrayList<>();
        list_article=this.article_controller.getArticleByAuthor(author_id);
        System.out.println(list_article.toString()+"\n\n");
        assertFalse(list_article.isEmpty());
    }

    @Test
    public void testGetArticleByAuthorNotOK() throws SQLException{
        ID author_id = new ID ("3a9e468f-ff6b-4a84-bbc0-acbffed869d3");
        ArrayList<ShowArticleDTO> list_article = new ArrayList<>();
        list_article=this.article_controller.getArticleByAuthor(author_id);
        assertNull(list_article);
    }
}
