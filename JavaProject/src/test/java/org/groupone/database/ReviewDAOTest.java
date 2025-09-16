package org.groupone.database;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;

import java.sql.SQLException;
import org.groupone.utility.ID;


public class ReviewDAOTest {

    @Test
    public void hasConflictOfInterest(){
	ID idArt = new ID("2e24cd58-a3d7-4057-a1b8-ce9a24669cea");
	ID idUser = new ID("9c388e06-3c9e-43bd-9327-acbffed869d3");
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
