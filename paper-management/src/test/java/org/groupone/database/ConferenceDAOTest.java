package org.groupone.database;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import org.junit.Test;
import org.groupone.utility.ID;
import org.groupone.entity.Conference;

public class ConferenceDAOTest {

    @Test
    public void getConferenceByID() {
	ID id = new ID("6279c9e1-b121-4c7a-a196-7a43b57fc16d");
	try {
	    ConferenceDAO dao = new ConferenceDAO();
	    Conference c = dao.getConferenceByID(id);
	    assertNotNull("failure - conferenza not found", c);
	}catch (SQLException e) {
	    e.printStackTrace();
	}
    }
}
