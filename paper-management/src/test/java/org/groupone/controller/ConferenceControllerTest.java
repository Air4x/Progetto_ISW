package org.groupone.controller;

import java.sql.SQLException;
import java.util.Date;

import org.groupone.DTO.RUserDTO;
import org.groupone.database.UserDAO;
import org.groupone.utility.ID;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

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
            Date scadenza=new Date(2023,12,04);
            id_conference=ID.generate();
            org = user_controller.login("domenico.cotroneo@unina.it","virtualizzazione");
            esito = conference_conference.createConference(scadenza,title,description,id_conference,org);
        }else if (scelta==1) {
            Date scadenza=new Date(2026,12,04);
            id_conference=new ID("6279c9e1-b121-4c7a-a196-7a43b57fc16d");
            org = user_controller.login("domenico.cotroneo@unina.it","virtualizzazione");
            esito = conference_conference.createConference(scadenza,title,description,id_conference,org);
        }else if(scelta==2){
            Date scadenza=new Date(2026,12,04);
            id_conference=ID.generate();
            org = user_controller.login("giuseppe.aceto@unina.it","12345678!");
            esito = conference_conference.createConference(scadenza,title,description,id_conference,org);
        }
        assertFalse(esito);
    }

    @Test
    public void testGetActiveConferencesOK() throws SQLException {}

    @Test
    public void testGetActiveConferencesThereAreNotActiveConferences() throws SQLException {}

    @Test
    public void testGetArticlesByConferenceOK() throws SQLException {}

    @Test
    public void testGetArticlesByConferenceThreAreNotAArticlesForAuthor() throws SQLException {}


}
