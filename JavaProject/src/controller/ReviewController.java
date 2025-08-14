package controller;

import database.ArticleDAO;
import database.ReviewDAO;
import DTO.PossibleReviewDTO;
import database.UserDAO;
import entity.Author;
import java.sql.SQLException;
import java.util.ArrayList;

import utility.ID;

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
    }

    /**
     * Metodo per l'assegnazion di revisore
     * @param articleID
     * @param reviewID
     * @return
     * @throws SQLException
     */
    public boolean assignReviewer (ID articleID, ArrayList<PossibleReviewDTO> list_r ) throws SQLException{
        int v = 0;
        for(PossibleReviewDTO rp : list_r){
            if(rp.getSelezione() == true){
                v++;
            }
        }
        if(v <= 3){
            for(PossibleReviewDTO rp : list_r){
                if(rp.getSelezione() == true && u_DAO.isUserPresentByID(rp.getId())){
                r_DAO.assignReviewer(articleID, rp.getId());
                    }
                }
            return true;
        }
        return false;
        
    }

    /**
     * Metodo per ottenere una lista di possibili revisori
     * @param articleID
     * @return
     * @throws SQLException
     */
    public ArrayList<PossibleReviewDTO> getListReviewer(ID articleID) throws SQLException{
        ArrayList<PossibleReviewDTO> list_r = new ArrayList<>();
        ArrayList<Author> list_a = u_DAO.getAllAuthors();

        for(Author a: list_a){
            if(r_DAO.hasConflitOfInterest(articleID, a.getId()) != true && u_DAO.isUserPresentByID(a.getId()) != true){
                PossibleReviewDTO r = new PossibleReviewDTO(a);
                list_r.add(r);
            }
        }
        return list_r;
    }

    /**
     * Metodo per aggiorna lo status di un articolo
     * @param ID
     * @param status
     * @return
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
