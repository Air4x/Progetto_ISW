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

    public boolean assignReviewer (String articleID, String reviewID) throws SQLException{
        if(r_DAO.hasConflitOfInterest(articleID, reviewID)){
            return false;
        } else if (!u_DAO.isUserPresentByID(reviewID)){
            return false;
        }
        r_DAO.assignReviewer(articleID,reviewID);
        return true;
    }

}
