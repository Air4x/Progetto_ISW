package org.groupone.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.groupone.DTO.PossibleReviewDTO;
import org.groupone.database.ArticleDAO;
import org.groupone.database.ReviewDAO;
import org.groupone.database.UserDAO;
import org.groupone.entity.Author;
import org.groupone.utility.ID;

/**
 * @author Giuseppe Buglione
 * Classe utilizzata per la gestione dei revisori
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
     * Metodo per l'assegnazion di revisore
     * @param articleID
     * @param list_reviewer_selected
     * @return Valore Booleano che indica se l'assegnazione di revisore è avvenuta con successo
     * @throws SQLException
     */
    public boolean assignReviewer (ID articleID, ArrayList<PossibleReviewDTO> list_reviewer_selected) throws SQLException{
        if(list_reviewer_selected.size()==0 || list_reviewer_selected.size()>3 || list_reviewer_selected == null){
            return false;
        }
        for(PossibleReviewDTO r: list_reviewer_selected){
            this.reviewer_dao.assignReviewer(articleID, r.getId());
        }
       return true;
    }

    /**
     * Metodo per ottenere una lista di possibili revisori
     * @param articleID
     * @return Un arraylist di PossibleReviewDTO che si possono assegnare come revisori
     * @throws SQLException
     */
    public ArrayList<PossibleReviewDTO> getListReviewer(ID articleID) throws SQLException{
        ArrayList<PossibleReviewDTO> list_r = new ArrayList<>();
        ArrayList<Author> list_a = user_dao.getAllAuthors();
        for(Author a: list_a){
            if(reviewer_dao.hasConflitOfInterest(articleID, a.getId()) != true && user_dao.isUserPresentByID(a.getId()) == true){
                PossibleReviewDTO r = new PossibleReviewDTO(a);
                list_r.add(r);
            }
        }
        return list_r;
    }

    /**
     * Metodo per aggiorna lo status di un articolo
     * @param id_article
     * @param status
     * @return Valore Booleano che indica se l'aggiornamento dello stato è avvenuto con successo
     * @throws SQLException
     */
    public boolean updateArticleStatus (ID id_article, String status) throws SQLException {
        if(article_dao.getArticleByID(id_article) != null){
            this.reviewer_dao.updateArticleStatus(id_article, status);
            return true;
        }
        return false;
    }

}
