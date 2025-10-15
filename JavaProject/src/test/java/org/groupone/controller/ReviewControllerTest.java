package org.groupone.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.groupone.DTO.RUserDTO;
import org.groupone.DTO.ReviewDTO;
import org.groupone.DTO.ShowArticleDTO;
import org.groupone.database.ArticleDAO;
import org.groupone.database.UserDAO;
import org.groupone.utility.ID;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
//ok
public class ReviewControllerTest {

    private ReviewController review_Controller;
    private UserDAO user_dao;
    private ArticleDAO article_dao;

    @Before
    public void setUp() throws SQLException {
        review_Controller = new ReviewController();
        user_dao = new UserDAO();
        article_dao = new ArticleDAO();
    }

    @Test
    public void testCreateReview_ValidInput() throws SQLException {
        ArrayList<RUserDTO> reviewers = new ArrayList<>();
        reviewers.add(new RUserDTO(user_dao.getUserByEmail("test_autore_2@test.com")));
        reviewers.add(new RUserDTO(user_dao.getUserByEmail("test_autore_3@test.com")));
        reviewers.add(new RUserDTO(user_dao.getUserByEmail("test_autore_4@test.com")));
        Boolean esito= review_Controller.createReview(reviewers, new ShowArticleDTO(article_dao.getArticleByID(new ID("64383965-3762-4265-a539-316463343833"))));
        assertTrue(esito);
    }

    @Test
    public void testCreateReview_NullOrEmptyReviewers() throws SQLException {
        Boolean esito= review_Controller.createReview(null, new ShowArticleDTO(article_dao.getArticleByID(new ID("64383965-3762-4265-a539-316463343833"))));
        assertFalse(esito);
    }

    @Test
    public void testCreateReview_InvalidArticle() throws SQLException {
        ArrayList<RUserDTO> reviewers = new ArrayList<>();
        reviewers.add(new RUserDTO(user_dao.getUserByEmail("test_autore_2@test.com")));
        reviewers.add(new RUserDTO(user_dao.getUserByEmail("test_autore_3@test.com")));
        reviewers.add(new RUserDTO(user_dao.getUserByEmail("test_autore_4@test.com")));
        Boolean esito= review_Controller.createReview(reviewers, null);
        assertFalse(esito);
    }

    @Test
    public void testUpdateReview_ValidInput() throws SQLException{
        ArrayList<ReviewDTO> r = review_Controller.getAllReviewsByArticle(new ID("62366665-3430-4435-b764-316461623265"));
        ReviewDTO final_review = r.get(0);
        ReviewDTO updated_review = review_Controller.updateReview(final_review, 12, "rifiutato");
        assertNotNull(updated_review);
    }

    @Test
    public void testUpdateReview_InvalidInput() throws SQLException{
        ReviewDTO updated_review = review_Controller.updateReview(null, 12, "rifiutato");
        assertNull(updated_review);
    }
    }



