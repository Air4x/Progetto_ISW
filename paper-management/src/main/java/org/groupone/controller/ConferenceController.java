package org.groupone.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.groupone.DTO.RUserDTO;
import org.groupone.DTO.ShowActiveConferenceDTO;
import org.groupone.DTO.ShowArticleDTO;
import org.groupone.database.ConferenceDAO;
import org.groupone.database.UserDAO;
import org.groupone.entity.Article;
import org.groupone.entity.Author;
import org.groupone.entity.Conference;
import org.groupone.utility.ID;

/**
 * @author Giuseppe Buglione
 * Classe utilizzata per la gestione delle Conferenzze
 */
public class ConferenceController {
    
    private UserDAO user_dao;
    private ConferenceDAO conf_dao;

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
     * @return Valore booleano che indica se la conferenza è stata creata con successo
     * @throws SQLException
     */
    public boolean createConference (LocalDate scadenza, String title, String descr, ID id, RUserDTO org) throws SQLException {
        LocalDate today = LocalDate.now();
        Date deadline = Date.valueOf(scadenza);
        if(conf_dao.isConferencePresentByID(id)){
            System.out.println("Conference Already Exists");
            return false;
        }else if(today.isAfter(scadenza)){
            System.out.println("Scadenza is after today");
            return false;
        }else if(user_dao.isUserPresentByID(org.getId())==false || user_dao.getUserByEmail(org.getEmail()).getRole()!="organizzatore"){
            System.out.println("Organizzarore is not Found");
            return false;
        }
        Conference new_conference = new Conference(deadline,title,descr,id,org.getId());
        conf_dao.saveConference(new_conference);
        return true;
    }

    /**
     * Metodo per ottenere una lista di conferenzze attive [NON ANCORA SCADUTE]
     * @return Un array list di ShowActiveConferenceDTO che indicano le conferenze attive
     * @throws SQLException
     */
    public ArrayList<ShowActiveConferenceDTO> getActiveConferences() throws SQLException{
        ShowActiveConferenceDTO active_conference =null;
        Date today = Date.valueOf(LocalDate.now());
        ArrayList<Conference> all_conf = conf_dao.getAllConference();
        ArrayList<ShowActiveConferenceDTO> actconf = new ArrayList<>();
        for(Conference conf : all_conf){
            Date deadline = conf.getDeadline();
            ID id = conf.getId();
            if(deadline != null && deadline.after(today)){
                active_conference = new ShowActiveConferenceDTO(id,conf.getTitle(),conf.getDeadline(),conf.getDescription());
                actconf.add(active_conference);
            }
        }
            return actconf;
    }

    /**
     * Metodo per ottenere una lista di articoli
     * @param ID_conference
     * @return Un array list di ShowArticleDTO che indicano gli articoli della conferenza
     * @throws SQLException
     */
    public ArrayList<ShowArticleDTO> getArticlesByConference(ID ID_conference) throws SQLException {
        ArrayList<Article> real_list_articles = conf_dao.getArticlesByConference(ID_conference);
        ArrayList<ShowArticleDTO> fake_list_articles = new ArrayList<>();
        ArrayList<RUserDTO> fake_list_author  = new ArrayList<>();
        ShowArticleDTO fake_article= null;
        RUserDTO fake_user = null;
        for(Article a : real_list_articles) {
            for(Author auth : a.getAuthors()){
                ID id= auth.getId();
                fake_user = new RUserDTO (auth.getName(),auth.getLastName(),auth.getEmail(),auth.getAffiliation(),auth.getRole(),id);
                fake_list_author.add(fake_user);
            }
            fake_article = new ShowArticleDTO(a.getId(), a.getTitle(), a.getAbstr(), fake_list_author);
            fake_list_articles.add(fake_article);
        }
        return fake_list_articles;
    }

}
