package org.groupone.entity;

import org.groupone.utility.ID;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Classe che modella una Conferenza
 *
 * @author Mario Calcagno
 */
public class Conference {
    /**
     * L'id della conferenza
     */
    private ID id;

    /**
     * Il titolo della conferenza
     *
     */
    private String title;

    /**
     * La descrizione della conferenza
     *
     */
    private String description;

    /**
     * La scadenza per la sottomissione di articoli
     *
     */
    private Date deadline;

    private ID organizer;

    /**
     * La lista degli articoli sottomessi alla conferenza
     *
     */
    private ArrayList<Article> articles;

    /**
     * Costruttore per Conference
     *
     * @param deadline
     * @param title
     * @param description
     * @param id
     */
    public Conference(Date deadline, String title, String description, ID id, ID organizer) {
        this.id = id;
        this.deadline = deadline;
        this.title = title;
        this.description = description;
	this.organizer = organizer;
        this.articles = new ArrayList<>();
    }

    /**
     * Costruttre di copia per Conference
     *
     * @param c
     */
    public Conference(Conference c) {
	this.id = c.getId();
        this.deadline = c.getDeadline();
        this.title = c.getTitle();
        this.description = c.getDescription();
        this.articles = c.getArticles();
    }

    /**
     * Getter per la lista di articoli sottomessi alla conferenza
     *
     * @return la lista di articoli 
     */
    public ArrayList<Article> getArticles() {
        return this.articles;
    }

    /**
     * Getter per l'id della conferenza
     *
     * @return l'ide della conferenza
     */
    public ID getId() {
        return id;
    }

    /**
     * Getter per la descrizione della conferenza
     *
     * @return la descrizione della conferenza
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter per la scadenza
     *
     * @return la scadenza per la sottomissione di articoli
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     * Getter per il titolo
     *
     * @return il titolo della conferenza
     */
    public String getTitle() {
        return title;
    }

    public String getOrganizer() {
	return organizer.toString();
    }

    /**
     * Predicato che verifica se la conferenza è vicino (5 giorni)
     * alla sua data di scadenza per la sottomissione di articoli
     *
     * @return se la conferenza è vicino alla scadenza
     */
    public boolean nearDeadline() {
	Date now = new Date(LocalDate.now().toEpochDay());
	Calendar.Builder calBuilder = new Calendar.Builder();
        calBuilder.setInstant(now);
        Calendar cNow = calBuilder.build();
        calBuilder.setInstant(this.getDeadline());
        Calendar cScadenza = calBuilder.build();
        int giornoNow = cNow.get(Calendar.DAY_OF_YEAR);
        int giornoScadenza = cScadenza.get(Calendar.DAY_OF_YEAR);
        if((giornoScadenza - giornoNow) <= 5){
	    return true;
        } else { return false; }
    }
}
