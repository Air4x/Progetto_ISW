package org.groupone.database;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

import java.sql.SQLException;
import org.groupone.utility.ID;


public class ReviewDAOTest {

    @Test
    public void hasConflictOfInterest(){
	ID idArt = new ID("62366665-3430-4435-b764-316461623265");
	ID idUser = new ID("34633933-3933-4832-b533-373065323631");
	try {
	    ReviewDAO dao = new ReviewDAO();
	    boolean actual = dao.hasConflitOfInterest(idArt, idUser);
	    assertTrue("Conflitto di interessi non trovato", actual);
	}
	catch (SQLException e) {
	    e.printStackTrace();
	    fail("hasConflictOfInterest - Found Exception");
	}
    }
}
