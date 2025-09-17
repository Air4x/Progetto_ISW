package org.groupone.DTO;

import org.groupone.entity.Author;
import org.groupone.utility.ID;

/**
 * @author Giuseppe Buglione
 * Classe DTO per il trasporto delle informazioni di un revisore [autore]
 */
public class PossibleReviewDTO {

    private ID id;
    private String name;
    private String lastname;
    private String affiliazione;
    
    /**
     * Costruttore
     * @param id
     * @param name
     * @param lastname
     * @param affiliazione
     * @param selezione
     */
    public PossibleReviewDTO(ID id, String name, String lastname, String affiliazione) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.affiliazione = affiliazione;
    }

    /**
     * Costruttore di copia per un autore
     * @param autore
     */
    public PossibleReviewDTO(Author autore) {
        this.id = autore.getId();
        this.name = autore.getName();
        this.lastname = autore.getLastName();
        this.affiliazione = autore.getAffiliation();
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
    public String getAffiliation() {
        return affiliazione;
    }

    @Override
    public String toString() {
        return "\n"+this.name + "\n" + this.lastname + "\n" + this.affiliazione + "\n";
    }
}
