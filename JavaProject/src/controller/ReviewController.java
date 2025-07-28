package controller;

import database.ReviewDAO;
import DTO.PossibleReviewDTO;
import database.UserDAO;
import entity.Author;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utility.ID;

/**
 * @author Giuseppe Buglione
 * CLasse utilizzata per la gestione dei revisori
 */
public class ReviewController {

    private ReviewDAO r_DAO;
    private UserDAO u_DAO;

    public ReviewController () throws SQLException {
        this.r_DAO = new ReviewDAO();
    }

    /**
     * Metodo per l'assegnazion di revisore
     * @param articleID
     * @param reviewID
     * @return
     * @throws SQLException
     */
    public boolean assignReviewer (ID articleID, List<PossibleReviewDTO> list_r ) throws SQLException{
        int v = 3;
        for(PossibleReviewDTO rp : list_r){
            if(rp.getSelezione() == true){
                r_DAO.assignReviewer(articleID,rp.getId());
                v--;
            }
        }
        if(v == 0){
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
    public List<PossibleReviewDTO> getListReviewer(ID articleID) throws SQLException{
        List<PossibleReviewDTO> list_r = new ArrayList<>();
        List<Author> list_a = u_DAO.getAllAuthors();

        for(Author a: list_a){
            if(r_DAO.hasConflitOfInterest(articleID, a.getId()) != true && u_DAO.isUserPresentByID(a.getId()) != true){
                PossibleReviewDTO r = new PossibleReviewDTO(a);
                list_r.add(r);
            }
        }
        return list_r;
    }

}
