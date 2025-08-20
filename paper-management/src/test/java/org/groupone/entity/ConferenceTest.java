package org.groupone.entity;

import org.junit.Assert;
import org.junit.Test;

import org.groupone.utility.ID;

import java.util.Date;

public class ConferenceTest {

    @Test
    public void inScadenza() {
        Date scadenza = new Date();
        Conference c = new Conference(scadenza, "Prova1", "prova1 descrizione", ID.generate());
        Assert.assertTrue("Conferenza non in scadenza",c.nearDeadline());
    }
}
