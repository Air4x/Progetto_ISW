package controller;

import database.ReviewDAO;
import database.UserDAO;
import entity.*;

import java.sql.SQLException;

public class ReviewController {

    private ReviewDAO r_DAO;
    private UserDAO u_DAO;

    public ReviewController () throws SQLException {
        this.r_DAO = new ReviewDAO();
    }

    public boolean assignReviewer (int articleID, int reviewID) throws SQLException{
        String art_id = Integer.toString(articleID);
        String rew_id = Integer.toString(reviewID);
        if(r_DAO.hasConflitOfInterest(art_id, rew_id)){
            return false;
        } else if (!u_DAO.isUserPresentByID(reviewID)){
            return false;
        }
        r_DAO.assignReviewer(art_id,rew_id);
        return true;
    }

}
