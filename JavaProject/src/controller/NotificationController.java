package controller;

import database.*;
import entity.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class NotificationController {

    private ConferenceDAO conf_dao;
    private UserDAO user_dao;

    public NotificationController() throws SQLException{
        this.conf_dao= new ConferenceDAO();
        this.user_dao= new UserDAO();
    }

    public void invioNotifiche () throws SQLException{
        ArrayList<Conference> conf = conf_dao.getActiveConferences();
        List<Author> auth = user_dao.getAllAuthors();
        for (Conference c : conf) {
            if (c.inScadenza()) {
                
            }
            
        }
    }

    private void sendEmail(){
        
    }
}
