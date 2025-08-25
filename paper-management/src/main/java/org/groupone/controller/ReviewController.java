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

    private ReviewDAO r_DAO;
    private UserDAO u_DAO;
    private ArticleDAO art_dao;

    public ReviewController () throws SQLException {
        this.r_DAO = new ReviewDAO();
        this.u_DAO = new UserDAO();
        this.art_dao = new ArticleDAO();
    }

    /**
     * Metodo per l'assegnazion di revisore
     * @param articleID
     * @param list_reviewer_selected
     * @return Valore Booleano che indica se l'assegnazione di revisore è avvenuta con successo
     * @throws SQLException
     */
    public boolean assignReviewer (ID articleID, ArrayList<PossibleReviewDTO> list_reviewer_selected) throws SQLException{
        if(list_reviewer_selected.size()==0 || list_reviewer_selected.size()>3){
            return false;
        }
        for(PossibleReviewDTO r: list_reviewer_selected){
            this.r_DAO.assignReviewer(articleID, r.getId());
        }
       return true;
    }

    /**
     * Metodo per ottenere una lista di possibili revisori
     * @param articleID
     * @return un arraylist di PossibleReviewDTO che si possono assegnare come revisori
     * @throws SQLException
     */
    public ArrayList<PossibleReviewDTO> getListReviewer(ID articleID) throws SQLException{
        ArrayList<PossibleReviewDTO> list_r = new ArrayList<>();
        ArrayList<Author> list_a = u_DAO.getAllAuthors();
        for(Author a: list_a){
            if(r_DAO.hasConflitOfInterest(articleID, a.getId()) != true && u_DAO.isUserPresentByID(a.getId()) == true){
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
        if(art_dao.getArticleByID(id_article) != null){
            this.r_DAO.updateArticleStatus(id_article, status);
            return true;
        }
        return false;
    }

}
