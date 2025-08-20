package org.groupone.controller;

import static org.junit.Assert.*;
import org.groupone.DTO.RUserDTO;
import org.groupone.DTO.ShowArticleDTO;
import org.groupone.controller.UserController;
import org.junit.Test;
import org.junit.Before;
import org.groupone.controller.ArticleController;
import org.groupone.utility.ID;
import java.sql.SQLException;
import java.util.ArrayList;


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
        autori.add(user_controller.getRAuthorBYEmail("gian.rombanini@outlook.it"));
        autori.add(user_controller.getRAuthorBYEmail("giuseppe.aceto@unina.it"));
        ID conference_id = new ID("6279c9e1-b121-4c7a-a196-7a43b57fc16d");
        boolean esito = article_controller.submitArticle("Why Nintendo?","Nintendo",autori,conference_id);
        assertTrue(esito);
    }

    @Test
    public void testSubmitArticlesWithExpiredAConference() throws SQLException{
        ArrayList<RUserDTO> autori =  new ArrayList<>();
        autori.add(user_controller.getRAuthorBYEmail("gian.rombanini@outlook.it"));
        autori.add(user_controller.getRAuthorBYEmail("giuseppe.aceto@unina.it"));
        ID conference_id = new ID("6279c9e1-b121-4c7a-a196-7a43b57fc03d");
        boolean esito = article_controller.submitArticle("Why Nintendo?","Nintendo",autori,conference_id);
        assertFalse(esito);
    }

    @Test
    public void testGetArticleByAuthorOK() throws SQLException{
        ID author_id = new ID ("9c388e06-3c9e-43bd-9327-acbffed869d3");
        ArrayList<ID> list_id = new ArrayList<>();
        ArrayList<ShowArticleDTO> list_article = new ArrayList<>();
        list_article=this.article_controller.getArticleByAuthor(author_id);
        list_id.add(new ID ("2e24cd58-a3d7-4057-a1b8-ce9a24669cea"));
        list_id.add(new ID ("90d0f680-b4c1-416f-903c-3d2976025efb"));
        int i=0;
        for(ShowArticleDTO article : list_article){
            assertEquals(article.getId(),list_id.get(i));
            i++;
        }
    }

    @Test
    public void testGetArticleByAuthorNotOK() throws SQLException{
        ID author_id = new ID ("3a9e468f-ff6b-4a84-bbc0-acbffed869d3");
        ArrayList<ShowArticleDTO> list_article = new ArrayList<>();
        list_article=this.article_controller.getArticleByAuthor(author_id);
        assertNull(list_article);
    }
}
