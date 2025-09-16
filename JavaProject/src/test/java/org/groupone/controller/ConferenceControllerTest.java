package org.groupone.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.groupone.DTO.RUserDTO;
import org.groupone.DTO.ShowActiveConferenceDTO;
import org.groupone.DTO.ShowArticleDTO;

import org.groupone.utility.ID;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
//ok
public class ConferenceControllerTest {

    private ConferenceController conference_controller;
    private UserController user_controller;

    @Before
    public void setUp() throws SQLException {
        conference_controller = new ConferenceController();
        user_controller = new UserController();
    }

    @Test
    public void testCreateConferenceOK() throws SQLException {
        boolean conf = conference_controller.createConference(LocalDate.of(2026,12,04), "Nintendo per sempre", "I was nerd before it was cool", ID.generate(), user_controller.login("gpt1youtu@gmail.com","virtualizzazione"));
        assertTrue(conf);
    }

    @Test
    public void testCreateConferenceOrganizzaroreisnotFound() throws SQLException {
        boolean esito = conference_controller.createConference(LocalDate.of(2026,12,04),"Nintendo","Perche nintendo dovrebbe essere un monopolio",ID.generate(),user_controller.login("toolvpstaiscal@gmail.com","12345678!"));
        assertFalse(esito);
    }

    @Test
    public void testCreateConferenceAlreadyExists() throws SQLException {
        boolean esito = conference_controller.createConference(LocalDate.of(2026,12,04),"Nintendo","Perche nintendo dovrebbe essere un monopolio",new ID("6279c9e1-b121-4c7a-a196-7a43b57fc16d"),user_controller.login("gpt1youtu@gmail.com","virtualizzazione"));
        assertFalse(esito);
    }

    @Test
    public void testCreateConferenceScadenzaisaftertoday() throws SQLException {
        boolean esito = conference_controller.createConference(LocalDate.of(2023,12,04),"Nintendo","Perche nintendo dovrebbe essere un monopolio",ID.generate(),user_controller.login("gpt1youtu@gmail.com","virtualizzazione"));
        assertFalse(esito);
    }

    @Test
    public void testGetActiveConferencesOK() throws SQLException {
        ArrayList<ShowActiveConferenceDTO> activeConferences = conference_controller.getActiveConferences();
        for(ShowActiveConferenceDTO conference : activeConferences) {
            System.out.println(conference.toString());
        }
        assertNotNull(activeConferences);
        assertFalse(activeConferences.isEmpty());
    }

    //per questo test non sono state inserite nel database di test conferenze attive
    @Test
    public void testGetActiveConferencesThereAreNotActiveConferences() throws SQLException {
        ArrayList<ShowActiveConferenceDTO> activeConferences = conference_controller.getActiveConferences();
        assertNull(activeConferences);
        assertTrue(activeConferences.isEmpty());
    }

    @Test
    public void testGetArticlesByConferenceOK() throws SQLException {
        ID id_conference = new ID ("6279c9e1-b121-4c7a-a196-7a43b57fc16d");
        ArrayList<ShowArticleDTO> articles = conference_controller.getArticlesByConference(id_conference);
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
        ArrayList<ShowArticleDTO> articles = conference_controller.getArticlesByConference(id_conference);
        assertTrue(articles.isEmpty());
    }


}
