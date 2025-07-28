package DTO;

import java.util.Date;
import utility.ID;

public class ShowActiveConferenceDTO {
    
    private ID id;
    private String titolo;
    private Date scadenza;
    private String decrizione;

    /**
     * Costrutore
     */
    public ShowActiveConferenceDTO() {
        this.titolo = new String();
        this.scadenza = new Date();
        this.decrizione = new String();
    }

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
    public String getTitolo() {
        return titolo;
    }

    /**
     * Scadenza della conferenzza
     * @return
     */
    public Date getScadenza() {
        return scadenza;
    }

    /**
     * Descrizione della conferenzza
     * @return
     */
    public String getDecrizione() {
        return decrizione;
    }
    
    
}
