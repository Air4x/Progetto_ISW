package org.groupone.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.groupone.DTO.RUserDTO;
import org.groupone.DTO.ReviewDTO;
import org.groupone.DTO.ShowArticleDTO;
import org.groupone.database.ArticleDAO;
import org.groupone.database.ReviewDAO;
import org.groupone.database.UserDAO;
import org.groupone.entity.Author;
import org.groupone.entity.Review;
import org.groupone.utility.ID;

/**
 * @author Giuseppe Buglione
 * Classe che implementa la logica di gestione di una revisione
 */
public class ReviewController {

    private ReviewDAO reviewer_dao;
    private UserDAO user_dao;
    private ArticleDAO article_dao;

    public ReviewController () throws SQLException {
        this.reviewer_dao = new ReviewDAO();
        this.user_dao = new UserDAO();
        this.article_dao = new ArticleDAO();
    }

    /**
     * Crea una revisione per ogni revisore passato come parametro
     * @param reviewers
     * @param article
     * @return
     * @throws SQLException
     */
    public boolean createReview(ArrayList<RUserDTO> reviewers, ShowArticleDTO article) throws SQLException {
        if (reviewers == null || reviewers.isEmpty()) {
            System.out.println("Invalid reviewers list");
            return false;
        }
        if (article == null) {
            System.out.println("Invalid article");
            return false;
        }
        for (RUserDTO reviewer : reviewers) {
            if (reviewer != null) {
                this.reviewer_dao.saveReview(new Review((Author)user_dao.getUserByID(reviewer.getId()), article_dao.getArticleByID(article.getId())));
            }
            
        }
        return true;
    }

    /**
     * Restituisce la lista di revisori che non hanno conflitti di interesse con l'articolo
     * @param articleId
     * @return
     * @throws SQLException
     */
    public ArrayList<RUserDTO> getPossibleReviewers(ID articleId) throws SQLException {
        ArrayList<RUserDTO> possibleReviewers = new ArrayList<>();
        ArrayList<Author> authors = user_dao.getAllAuthors();
        for (Author author : authors) {
            if (!this.reviewer_dao.hasConflitOfInterest(articleId, author.getId())) {
                possibleReviewers.add(new RUserDTO(author));
            }
        }
        return possibleReviewers;
    }

    /**
     * Permette di aggiornare una revisione
     * @param final_review
     * @param new_score
     * @param new_result
     * @return
     * @throws SQLException
     */    
    public ReviewDTO updateReview (ReviewDTO final_review, int new_score, String new_result) throws SQLException {
        Review r = this.reviewer_dao.getAllReviewByID(final_review.getId());
        if (final_review == null) {
            System.out.println("Invalid review");
            return null;
        }
        r.setScore(new_score);
        r.setResult(new_result);
        this.reviewer_dao.updateReview(r);
        return new ReviewDTO(r);
    }

    /**
     * Permette di ottenere la lista di tutte le revisioni di un dato
     * revisore
     *
     * @param reviewer, l'id del revisore
     */
    public ArrayList<ReviewDTO> getAllReviewsByReviewer(ID reviewer) throws SQLException {
        ArrayList<Review> reviews = (ArrayList<Review>) this.reviewer_dao.getAllReviewByReviewer(reviewer);
        ArrayList<ReviewDTO> reviews_dto = new ArrayList<>();
        for (Review r : reviews) {
            reviews_dto.add(new ReviewDTO(r));
        }
        return reviews_dto;
    }

    /**
     * Permette di ottenere la lista di tutte le revisioni di un dato
     * articolo
     *
     * @param article, l'id dell'articolo
     */
    public ArrayList<ReviewDTO> getAllReviewsByArticle(ID articleId) throws SQLException {
        ArrayList<Review> reviews = (ArrayList<Review>) this.reviewer_dao.getAllReviewByArticle(articleId);
        ArrayList<ReviewDTO> reviews_dto = new ArrayList<>();
        for (Review r : reviews) {
            reviews_dto.add(new ReviewDTO(r));
        }
        return reviews_dto;
    }

    @Deprecated
    public boolean assignReviewers(ShowArticleDTO article, ArrayList<RUserDTO> reviewers) throws SQLException {
        return this.createReview(reviewers, article);
    }

}



