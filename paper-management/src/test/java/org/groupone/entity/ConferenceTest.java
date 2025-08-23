package org.groupone.entity;

import org.junit.Assert;
import org.junit.Test;

import org.groupone.utility.ID;

import java.sql.Date;
import java.time.LocalDate;

public class ConferenceTest {

    @Test
    public void inScadenza() {
        Date scadenza = Date.valueOf(LocalDate.now());
        Conference c = new Conference(scadenza, "Prova1", "prova1 descrizione", ID.generate(), new ID("ee719226-43d5-4bfc-bf46-3e409bbbf425"));
        Assert.assertTrue("Conferenza non in scadenza",c.nearDeadline());
    }
}
