package DTO;

import entity.Autore;
import utility.ID;

/**
 * @author Giuseppe Buglione
 * Classe DTO per il trasporto delle informazioni di un revisore [autore]
 */
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
     * @param autore
     */
    public PossibleReviewDTO(Autore autore) {
        this.id = autore.getId();
        this.name = autore.getNome();
        this.lastname = autore.getCognome();
        this.affiliazione = autore.getAffiliazione();
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
