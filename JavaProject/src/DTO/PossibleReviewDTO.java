package DTO;

import utility.ID;
import entity.Author;

public class PossibleReviewDTO {

    private ID id;
    private String name;
    private String lastname;
    private String affiliazione;
    private boolean selezione;
    
    /**
     * Costruttore
     * @param id
     * @param name
     * @param lastname
     * @param affiliazione
     * @param roule
     * @param selezione
     */
    public PossibleReviewDTO(ID id, String name, String lastname, String affiliazione,  boolean selezione) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.affiliazione = affiliazione;
        this.selezione = selezione;
    }

    /**
     * Costruttore
     * @param author
     */
    public PossibleReviewDTO(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.lastname = author.getLastName();
        this.affiliazione = author.getAffiliazione();
        this.selezione = false;
    }

    /**
     * Id del revisore
     * @return
     */
    public ID getId() {
        return id;
    }

    /**
     * Nome del revisore
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Cognome del revisore
     * @return
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Affiliazione del revisore
     * @return
     */
    public String getAffiliazione() {
        return affiliazione;
    }

    /**
     * Elemento per la selezione dell'autore
     * @return
     */
    public boolean getSelezione() {
        return selezione;
    }
}
