package org.groupone.entity;

import java.sql.Date;
import java.time.LocalDate;

import org.groupone.utility.ID;
import org.junit.Assert;
import org.junit.Test;

public class ConferenceTest {

    
    @Test
    public void nearDeadlineOK() {
        Conference c = new Conference(Date.valueOf(LocalDate.of(2026,12,04)), "Prova1", "prova1 descrizione", ID.generate(), new ID("ee719226-43d5-4bfc-bf46-3e409bbbf425"));
        Assert.assertFalse("Conferenza è in scadenza?: ",c.nearDeadline());
    }
    
    @Test
    public void nearDeadlineNotOK() {
        Conference c = new Conference(Date.valueOf(LocalDate.now()), "Prova1", "prova1 descrizione", ID.generate(), new ID("ee719226-43d5-4bfc-bf46-3e409bbbf425"));
        Assert.assertTrue("Conferenza è in scadenza?: ",c.nearDeadline());
    }
}
