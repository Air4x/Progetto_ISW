package controller;

import database.ConferenceDAO;
import entity.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConferenceController {
    
    private User user;
    private Conference conf;
    private ConferenceDAO conf_dao;

    public ConferenceController() throws SQLException {
        this.conf_dao = new ConferenceDAO();
    }

    public void createConference (Date scadenza, String title, String descr, String id, Organizer org) throws SQLException {
        this.conf = new Conference(scadenza,title,descr,id);
        this.user = (Organizer) org;
        conf_dao.saveConference(this.conf);
    }

    public List<Conference> getActiveConferences() throws SQLException{
        Date data = new Date();
        List<Conference> all_conf = conf_dao.getAllConferences();
        List<Conference> act_conf = new ArrayList<>();
        
        for(Conference conf : all_conf){
            if(conf.getScadenza() != null && !conf.getScadenza().before(data))
            act_conf.add(conf);
        }
        return act_conf;
    }

    public List<Articolo> getArticlesByConference(String conf_Id) throws SQLException {
        List<Articolo> art = conf_dao.getArticlesByConference(conf_Id);
        return art;
    }

}