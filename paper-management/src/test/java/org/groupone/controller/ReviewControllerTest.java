package org.groupone.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import org.groupone.database.ArticleDAO;
import org.groupone.DTO.PossibleReviewDTO;
import org.groupone.entity.Article;
import org.groupone.utility.ID;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

public class ReviewControllerTest {

    private ReviewController review_Controller;
    private ArticleDAO article_dao;

    @Before
    public void setUp() throws SQLException {
        review_Controller = new ReviewController();
        article_dao = new ArticleDAO();
    }

    @Test
    public void testAssignReviewerSuccess() throws SQLException {}
  
    @Test
    public void testAssignReviewerFailure() throws SQLException {}

    @Test
    public void testGetListReviewerSuccess() throws SQLException {
        ID id_article = new ID("2e24cd58-a3d7-4057-a1b8-ce9a24669cea");
        ArrayList<PossibleReviewDTO> list_reviewer = review_Controller.getListReviewer(id_article);
        for(PossibleReviewDTO r : list_reviewer){
            System.out.println(r.toString());
        }
        if(list_reviewer.size() == 0){
            assert(false);
        } else {
            assert(true);
        }
    }

    @Test
    public void testGetListReviewerthereAreNoReviewers() throws SQLException {
        ID id_article = new ID("7cf18f80-b41a-42f0-af41-fbb9b60303ab");
        ArrayList<PossibleReviewDTO> list_reviewer = review_Controller.getListReviewer(id_article);
        if(list_reviewer.size() == 0){
            assert(true);
        } else {
            assert(false);
        }

    }

    @Test
    public void testUpdateArticleStatusSuccess() throws SQLException {
        ID id_article = new ID("2e24cd58-a3d7-4057-a1b8-ce9a24669cea");
        boolean esito = review_Controller.updateArticleStatus(id_article, "UNDER_REVIEW");
        assert(esito);
    }
    
    @Test
    public void testUpdateArticleStatusFailure() throws SQLException {
        ID id_article = ID.generate();
        boolean esito = review_Controller.updateArticleStatus(id_article, "NEW_STATUS");
        assertFalse(esito);
    }

    @Test
    public void testDeleteArticleSuccess() throws SQLException {
        ID id_article1= ID.generate();
        ID id_article2 = new ID("2e24cd58-a3d7-4057-a1b8-ce9a24669cea");
        Article article1 = article_dao.getArticleByID(id_article1);
        Article article2 = article_dao.getArticleByID(id_article2);
        System.out.println(article2.getTitle());
    }

}

