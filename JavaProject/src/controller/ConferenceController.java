package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import database.ConferenceDAO;
import DTO.ShowActiveConferenceDTO;
import DTO.ShowArticleDTO;
import DTO.RUserDTO;
import database.UserDAO;
import entity.Conference;
import entity.Organizer;
import entity.User;
import utility.ID;
import entity.Article;

/**
 * @author Giuseppe Buglione
 * Classe utilizzata per la gestione delle Conferenzze
 */
public class ConferenceController {
    
    private User user;
    private UserDAO user_dao;
    private Conference conf;
    private ConferenceDAO conf_dao;
    private ShowActiveConferenceDTO dto;
    private ShowArticleDTO dto2;

    public ConferenceController() throws SQLException {
        this.conf_dao = new ConferenceDAO();
    }
    
    /**
     * Metodo per la creazione di una conferenzza
     * @param scadenza
     * @param title
     * @param descr
     * @param id
     * @param org
     * @throws SQLException
     */
    public void createConference (Date scadenza, String title, String descr, ID id, RUserDTO org) throws SQLException {
        this.conf = new Conference(scadenza,title,descr,id);
        this.user = (Organizer) this.user_dao.getUserByID(org.getId());;
        conf_dao.saveConference(this.conf);
    }

    /**
     * Metodo per ottenere una lista di conferenzze attive [NON ANCORA SCADUTE]
     * @return
     * @throws SQLException
     */
    public ArrayList<ShowActiveConferenceDTO> getActiveConferences() throws SQLException{
        Date data = new Date();
        ArrayList<Conference> all_conf = conf_dao.getAllConference();
        ArrayList<ShowActiveConferenceDTO> actconf = new ArrayList<>();
        
        for(Conference conf : all_conf){
            if(conf.getDeadline() != null && !conf.getDeadline().before(data))
            this.dto = new ShowActiveConferenceDTO(conf.getId(),conf.getTitle(),conf.getDeadline(),conf.getDescription());
            actconf.add(dto);
        }
            return actconf;
    }

    /**
     * Metodo per ottenere una lista di articoli
     * @param conf_Id
     * @return
     * @throws SQLException
     */
    public ArrayList<ShowArticleDTO> getArticlesByConference(ID conf_Id) throws SQLException {
        ArrayList<Article> art = conf_dao.getArticlesByConference(conf_Id);
        ArrayList<ShowArticleDTO> at = new ArrayList<>();
        for (Article a : art) {
            this.dto2 = new ShowArticleDTO (a.getId(),a.getTitle(),a.getAbstr(),a.getAuthors());
            at.add(dto2);
        }
        return at;
    }

}