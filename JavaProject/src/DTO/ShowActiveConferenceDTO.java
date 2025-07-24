package DTO;

import java.util.Date;
import utility.ID;

public class ShowActiveConferenceDTO {
    
    private ID id;
    private String titolo;
    private Date scadenza;
    private String decrizione;

    

    public ShowActiveConferenceDTO() {
        this.titolo = new String();
        this.scadenza = new Date();
        this.decrizione = new String();
    }

    public ShowActiveConferenceDTO(ID id, String titolo, Date scadenza, String decrizione) {
        this.id = id;
        this.titolo = titolo;
        this.scadenza = scadenza;
        this.decrizione = decrizione;
    }

    public ID getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public String getDecrizione() {
        return decrizione;
    }
    
    
}
