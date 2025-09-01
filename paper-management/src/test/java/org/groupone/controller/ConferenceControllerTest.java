package org.groupone.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.groupone.DTO.RUserDTO;
import org.groupone.DTO.ShowActiveConferenceDTO;
import org.groupone.DTO.ShowArticleDTO;
import org.groupone.database.UserDAO;
import org.groupone.utility.ID;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
//ok
public class ConferenceControllerTest {

    private ConferenceController conference_conference;
    private UserDAO user_dao;
    private UserController user_controller;

    @Before
    public void setUp() throws SQLException {
        conference_conference = new ConferenceController();
        user_dao = new UserDAO();
        user_controller = new UserController();
    }

    @Test
    public void testCreateConferenceOK() throws SQLException {}

    // 0= Per scadenza precedente a today
    // 1= Conferenza già esistente
    // 2= Organizzatore non esistente
    @Test
    public void testCreateConferenceNotOK() throws SQLException {
        int scelta =0;
        String title ="Nintendo";
        String description ="Perche nintendo dovrebbe essere un monopolio";
        ID id_conference=null;
        RUserDTO org=null;
        boolean esito=true;
        if(scelta==0){
            LocalDate scadenza=LocalDate.of(2023,12,04);
            id_conference= ID.generate();
            org = user_controller.login("gpt1youtu@gmail.com","virtualizzazione");
            esito = conference_conference.createConference(scadenza,title,description,id_conference,org);
        }else if (scelta==1) {
            LocalDate scadenza=LocalDate.of(2026,12,04);
            id_conference=new ID("6279c9e1-b121-4c7a-a196-7a43b57fc16d");
            org = user_controller.login("gpt1youtu@gmail.com","virtualizzazione");
            esito = conference_conference.createConference(scadenza,title,description,id_conference,org);
        }else if(scelta==2){
            LocalDate scadenza=LocalDate.of(2026,12,04);
            id_conference=ID.generate();
            org = user_controller.login("fakenetflix2003@gmail.com","12345678!");
            esito = conference_conference.createConference(scadenza,title,description,id_conference,org);
        }
        assertFalse(esito);
    }

    @Test
    public void testGetActiveConferencesOK() throws SQLException {
        ArrayList<ShowActiveConferenceDTO> activeConferences = conference_conference.getActiveConferences();
        for(ShowActiveConferenceDTO conference : activeConferences) {
            System.out.println(conference.toString());
        }
        assertNotNull(activeConferences);
        assertFalse(activeConferences.isEmpty());
    }

    //per questo test non sono state inserite nel database di test conferenze attive
    @Test
    public void testGetActiveConferencesThereAreNotActiveConferences() throws SQLException {
        ArrayList<ShowActiveConferenceDTO> activeConferences = conference_conference.getActiveConferences();
        assertNull(activeConferences);
        assertTrue(activeConferences.isEmpty());
    }

    @Test
    public void testGetArticlesByConferenceOK() throws SQLException {
        ID id_conference = new ID ("6279c9e1-b121-4c7a-a196-7a43b57fc16d");
        ArrayList<ShowArticleDTO> articles = conference_conference.getArticlesByConference(id_conference);
        System.out.println(articles.isEmpty());
        for(ShowArticleDTO article : articles) {
            System.out.println(article.toString());
        }
       assertNotNull(articles);
        assertFalse(articles.isEmpty());
    }


    @Test
    public void testGetArticlesByConferenceThreAreNotAArticlesForAuthor() throws SQLException {
        ID id_conference = new ID ("6279c9e1-b121-4c7a-a196-7a43b57fc03d");
        ArrayList<ShowArticleDTO> articles = conference_conference.getArticlesByConference(id_conference);
        assertTrue(articles.isEmpty());
    }


}
