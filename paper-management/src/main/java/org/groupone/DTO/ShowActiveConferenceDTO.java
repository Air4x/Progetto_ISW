package org.groupone.DTO;

import java.sql.Date;

import org.groupone.entity.Conference;
import org.groupone.utility.ID;

/**
 * @author Giuseppe Buglione
 * Classe DTO per il trasporto delle informazioni di una conferenzza attiva
 */
public class ShowActiveConferenceDTO {
    
    private ID id;
    private String titolo;
    private Date scadenza;
    private String decrizione;

    /**
     * Costruttore
     * @param id
     * @param titolo
     * @param scadenza
     * @param decrizione
     */
    public ShowActiveConferenceDTO(ID id, String titolo, Date scadenza, String decrizione) {
        this.id = id;
        this.titolo = titolo;
        this.scadenza = scadenza;
        this.decrizione = decrizione;
    }

    /**
     * Costruttore di copia
     * @param active_conference
     */
    public ShowActiveConferenceDTO (ShowActiveConferenceDTO active_conference){
        this.id= active_conference.getId();
        this.titolo= active_conference.getTitle();
        this.scadenza= active_conference.getDeadline();
        this.decrizione= active_conference.getDescription();
    }

    /**
     * Costrutore di copia per una conferenza
     * @param conference
     */
    public ShowActiveConferenceDTO (Conference conference){
        this.id= conference.getId();
        this.titolo= conference.getTitle();
        this.scadenza= conference.getDeadline();
        this.decrizione= conference.getDescription();
    }

    /**
     * ID della conferenzza
     * @return
     */
    public ID getId() {
        return id;
    }

    /**
     * Titolo  della conferenzza
     * @return
     */
    public String getTitle() {
        return titolo;
    }

    /**
     * Scadenza della conferenzza
     * @return
     */
    public Date getDeadline() {
        return scadenza;
    }

    /**
     * Descrizione della conferenzza
     * @return
     */
    public String getDescription() {
        return decrizione;
    }
    
    @Override
    public String toString() {
        return  this.titolo + " \n" + this.decrizione + " \n" + this.scadenza +"\n\n";
    }
}
