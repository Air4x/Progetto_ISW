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
    public void testSubmitArticlesWithAActiveConference() throws SQLException{
        ArrayList<RUserDTO> autori =  new ArrayList<>();
        autori.add(user_controller.getRAuthorBYEmail("toolvpstaiscal@gmail.com"));
        autori.add(user_controller.getRAuthorBYEmail("gian.rombanini@outlook.it"));
        ID conference_id = new ID("6279c9e1-b121-4c7a-a196-7a43b57fc16d");
        boolean esito = article_controller.submitArticle("Why Nintendo?","Nintendo",autori,conference_id);
        assertTrue(esito);
    }

    /*
     * 0: Conference not found
     * 1: Expired conference
     * 2: List of author is empty
     */
    @Test
    public void testSubmitArticlesNotOK() throws SQLException{
        ArrayList<RUserDTO> autori =  new ArrayList<>();
        ID conference_id=null;
        int scelta = 0;
        if(scelta == 0){
            autori.add(user_controller.getRAuthorBYEmail("gian.rombanini@outlook.it"));
            autori.add(user_controller.getRAuthorBYEmail("toolvpstaiscal@gmail.com"));
            conference_id=ID.generate();
        }else if(scelta == 1){
            autori.add(user_controller.getRAuthorBYEmail("gian.rombanini@outlook.it"));
            autori.add(user_controller.getRAuthorBYEmail("toolvpstaiscal@gmail.com"));
            conference_id = new ID("6279c9e1-b121-4c7a-a196-7a43b57fc03d");
        }else if(scelta == 2){
            autori.add(user_controller.getRAuthorBYEmail(""));
            autori.add(user_controller.getRAuthorBYEmail(""));
            conference_id = new ID("6279c9e1-b121-4c7a-a196-7a43b57fc16d");
        }
        boolean esito = article_controller.submitArticle("Why Nintendo?","Nintendo",autori,conference_id);
        assertFalse(esito);
    }

    @Test
    public void testGetArticleByAuthorOK() throws SQLException{
        ID author_id = new ID ("9c388e06-3c9e-43bd-9327-acbffed869d3");
        ArrayList<ShowArticleDTO> list_article = new ArrayList<>();
        list_article=this.article_controller.getArticleByAuthor(author_id);
        System.out.println(list_article.toString());
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
