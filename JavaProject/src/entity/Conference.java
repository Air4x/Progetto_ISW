package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

public class Conference {
    private String id;
    private String titolo;
    private String descrizione;
    private Date scadenza;
    private List<Articolo> articoli;

    public Conference(Date scadenza, String titolo, String descrizione, String id) {
        this.id = id;
        this.scadenza = scadenza;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.articoli = new ArrayList<>();
    }

    public Conference(Conference c) {
	this.id = c.getId();
        this.scadenza = c.getScadenza();
        this.titolo = c.getTitolo();
        this.descrizione = c.getDescrizione();
        this.articoli = c.getArticoli();
    }
    
    public List<Articolo> getArticoli() {
        return articoli;
    }

    public String getId() {
        return id;
    }
    public String getDescrizione() {
        return descrizione;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public String getTitolo() {
        return titolo;
    }

    public boolean inScadenza () {
	Date now = new Date();
	Calendar.Builder calBuilder = new Calendar.Builder();
        calBuilder.setInstant(now);
        Calendar cNow = calBuilder.build();
        calBuilder.setInstant(this.getScadenza());
        Calendar cScadenza = calBuilder.build();
        int giornoNow = cNow.get(Calendar.DAY_OF_YEAR);
        int giornoScadenza = cScadenza.get(Calendar.DAY_OF_YEAR);
        if((giornoScadenza - giornoNow) <= 5){
	    return true;
        } else { return false; }
    }
}
