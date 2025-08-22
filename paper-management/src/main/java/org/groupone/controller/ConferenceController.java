package org.groupone.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import org.groupone.DTO.RUserDTO;
import org.groupone.DTO.ShowActiveConferenceDTO;
import org.groupone.DTO.ShowArticleDTO;
import org.groupone.database.ConferenceDAO;
import org.groupone.database.UserDAO;
import org.groupone.entity.Article;
import org.groupone.entity.Author;
import org.groupone.entity.Conference;
import org.groupone.entity.Organizer;
import org.groupone.entity.User;
import org.groupone.utility.ID;

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
        this.user_dao = new UserDAO();
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
    public boolean createConference (LocalDate scadenza, String title, String descr, ID id, RUserDTO org) throws SQLException {
        LocalDate today = LocalDate.now();
        Date deadline = Date.valueOf(scadenza);
        if(conf_dao.getConferenceByID(id)!=null){
            System.out.println("Conference Already Exists");
            return false;
        }else if(today.isAfter(scadenza)){
            System.out.println("Scadenza is after today");
            return false;
        }else if(user_dao.isUserPresentByID(org.getId())==false || user_dao.getUserByEmail(org.getEmail()).getRole()!="organizzatore"){
            System.out.println("Organizzarore is not Found");
            return false;
        }else if(conf_dao.getConferenceByID(id)==null){
            this.conf = new Conference(deadline,title,descr,id);
            this.user = (Organizer) this.user_dao.getUserByID(org.getId());;
            conf_dao.saveConference(this.conf);
            return true;
        }
        return false;
    }

    /**
     * Metodo per ottenere una lista di conferenzze attive [NON ANCORA SCADUTE]
     * @return
     * @throws SQLException
     */
    public ArrayList<ShowActiveConferenceDTO> getActiveConferences() throws SQLException{
        LocalDate today = LocalDate.now();
        ArrayList<Conference> all_conf = conf_dao.getAllConference();
        ArrayList<ShowActiveConferenceDTO> actconf = new ArrayList<>();
        for(Conference conf : all_conf){
            if(conf.getDeadline() != null && !conf.getDeadline().before(today)){
                this.dto = new ShowActiveConferenceDTO(conf.getId(),conf.getTitle(),conf.getDeadline(),conf.getDescription());
                actconf.add(dto);
            }
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
        ArrayList<Article> art_1 = conf_dao.getArticlesByConference(conf_Id);
        ArrayList<ShowArticleDTO> art_2 = new ArrayList<>();
        ShowArticleDTO art_3= null;
        ArrayList<RUserDTO> list_a  = new ArrayList<>();
        RUserDTO usr = null;
        for (Article a : art_1) {
            for(Author auth : a.getAuthors()){
                usr = new RUserDTO (auth.getName(),auth.getLastName(),auth.getEmail(),auth.getAffiliation(),auth.getRole(),false,auth.getId());
                list_a.add(usr);
            }
            art_3 = new ShowArticleDTO(a.getId(), a.getTitle(), a.getAbstr(), list_a);
            art_2.add(dto2);
        }
        return art_2;
    }

}
