package controller;

import DTO.ShowActiveConferenceDTO;
import DTO.ShowArticleDTO;
import database.ConferenceDAO;
import entity.Articolo;
import entity.Conference;
import entity.Organizer;
import entity.User;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utility.ID;

public class ConferenceController {
    
    private User user;
    private Conference conf;
    private ConferenceDAO conf_dao;
    private ShowActiveConferenceDTO dto;
    private ShowArticleDTO dto2;

    public ConferenceController() throws SQLException {
        this.conf_dao = new ConferenceDAO();
    }

    public void createConference (Date scadenza, String title, String descr, ID id, Organizer org) throws SQLException {
        this.conf = new Conference(scadenza,title,descr,id);
        this.user = (Organizer) org;
        conf_dao.saveConference(this.conf);
    }

    public List<ShowActiveConferenceDTO> getActiveConferences() throws SQLException{
        Date data = new Date();
        List<Conference> all_conf = conf_dao.getAllConferences();
        List<ShowActiveConferenceDTO> actconf = new ArrayList<>();
        
        for(Conference conf : all_conf){
            if(conf.getScadenza() != null && !conf.getScadenza().before(data))
            this.dto = new ShowActiveConferenceDTO(conf.getId(),conf.getTitolo(),conf.getScadenza(),conf.getDescrizione());
            actconf.add(dto);
        }
            return actconf;
    }

    public List<ShowArticleDTO> getArticlesByConference(ID conf_Id) throws SQLException {
        List<Articolo> art = conf_dao.getArticlesByConference(conf_Id);
        List<ShowArticleDTO> at = new ArrayList<>();
        for (Articolo a : art) {
            this.dto2 = new ShowArticleDTO (a.getId(),a.getTitolo(),a.getAbstr(),a.getAutori());
            at.add(dto2);
        }
        return at;
    }

}