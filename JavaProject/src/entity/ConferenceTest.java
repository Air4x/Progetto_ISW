package entity;

import org.junit.Test;

import utility.ID;

import java.util.Date;
import static org.junit.Assert.*;

public class ConferenceTest {

    @Test
    public void inScadenza() {
        Date scadenza = new Date();
        Conference c = new Conference(scadenza, "Prova1", "prova1 descrizione", ID.generate());
        assertFalse(c.inScadenza());
    }
}
