package org.groupone.database;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDate;

import org.junit.Test;
import org.groupone.utility.ID;
import org.groupone.entity.Article;
import org.groupone.entity.Conference;

public class ConferenceDAOTest {

    @Test
    public void getConferenceByID() {
	ID id = new ID("6279c9e1-b121-4c7a-a196-7a43b57fc16d");
	try {
	    ConferenceDAO dao = new ConferenceDAO();
	    Conference c = dao.getConferenceByID(id);
	    assertNotNull("getConferenceByID - non è stata trovata la conferenza", c);
	} catch (SQLException e){
	    e.printStackTrace();
	    fail("getConferenceByID - Exception found");
	}
    }

    @Test
    public void getArticlesByConference() {
	ID id = new ID("6279c9e1-b121-4c7a-a196-7a43b57fc16d");
	try {
	    ConferenceDAO dao = new ConferenceDAO();
	    ArrayList<Article> arts = dao.getArticlesByConference(id);
	    assertTrue("getArticlesByConference - non sono stati trovati articoli", arts.size() > 0);
	} catch (SQLException e) {
	    e.printStackTrace();
	    fail("getArticlesByConference - Found exception");
	}
    }

    @Test
    public void getAllConference() {
	try {
	    ConferenceDAO dao = new ConferenceDAO();
	    ArrayList<Conference> conf = dao.getAllConference();
	    assertTrue("failure - non sono state trovate conferenze", conf.size() > 0);
	} catch (SQLException e) {
	    e.printStackTrace();
	    fail("Found exception");
	}
    }

    @Test
    public void saveConference() {
	Date scadenza = Date.valueOf(LocalDate.now());
	Conference c = new Conference(scadenza,
				      "TITOLO_PROVA",
				      "DESCRIZIONE_PROVA_RIMUOVERE_PRIMA_DI_TEST", ID.generate());
	try {
	    ConferenceDAO dao = new ConferenceDAO();
	    dao.saveConference(c);
	}catch (SQLException e){
	    e.printStackTrace();
	    fail("saveConference - Found exception");
	}
    }
}
