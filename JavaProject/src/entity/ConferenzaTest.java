package entity;

import org.junit.Test;

import utility.ID;

import java.util.Date;
import static org.junit.Assert.*;

public class ConferenzaTest {

    @Test
    public void inScadenza() {
        Date scadenza = new Date();
        Conferenza c = new Conferenza(scadenza, "Prova1", "prova1 descrizione", ID.generate());
        assertFalse(c.inScadenza());
    }
}
