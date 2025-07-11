package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Conference {
    private int id;
    private String titolo;
    private String descrizione;
    private Date scadenza;
    private List<Articolo> articoli;

    public Conference(Date scadenza, String titolo, String descrizione, int id) {
        this.id = id;
        this.scadenza = scadenza;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.articoli = new ArrayList<>();
    }

    public List<Articolo> getArticoli() {
        return articoli;
    }

    public int getId() {
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
}
