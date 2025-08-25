package org.groupone.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import org.groupone.DTO.PossibleReviewDTO;
import org.groupone.database.ArticleDAO;
import org.groupone.database.UserDAO;
import org.groupone.entity.Article;
import org.groupone.entity.Author;
import org.groupone.utility.ID;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

public class ReviewControllerTest {

    private ReviewController review_Controller;
    private ArticleDAO article_dao;
    private UserDAO u_DAO;

    @Before
    public void setUp() throws SQLException {
        review_Controller = new ReviewController();
        article_dao = new ArticleDAO();
        u_DAO = new UserDAO();
    }

    @Test
    public void testAssignReviewerSuccess() throws SQLException {}
  
    @Test
    public void testAssignReviewerFailure() throws SQLException {}

    @Test
    public void testGetListReviewerSuccess() throws SQLException {
        ArrayList<Author> list_a = u_DAO.getAllAuthors();
        for(Author author : list_a){
            System.out.println(list_a.size());
        }

        /*ID id_article = new ID("2e24cd58-a3d7-4057-a1b8-ce9a24669cea");
        ArrayList<PossibleReviewDTO> list_reviewer = review_Controller.getListReviewer(id_article);
        for(PossibleReviewDTO r : list_reviewer){
            System.out.println(r.toString());
        }
        if(list_reviewer.size() == 0){
            assert(false);
        } else {
            assert(true);
        }*/
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
        boolean esito = review_Controller.updateArticleStatus(id_article, "in_revisione");
        assert(esito);
    }
    
    @Test
    public void testUpdateArticleStatusFailure() throws SQLException {
        ID id_article = ID.generate();
        boolean esito = review_Controller.updateArticleStatus(id_article, "NEW_STATUS");
        assertFalse(esito);
    }

}

