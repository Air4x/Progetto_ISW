package org.groupone.DTO;

import org.groupone.entity.User;
import org.groupone.utility.ID;

/**
 * @author Giuseppe Buglione
 * Classe DTO per il trasporto delle informazioni di un utente
 */
public class RUserDTO {

    private String name;
    private String lastname;
    private String email;
    private String affiliazione;
    private String ruolo;
    private ID id;

    /**
     * Costruttore
     * @param name
     * @param lastname
     * @param email
     * @param affiliazione
     * @param ruolo
     * @param id
     */
    public RUserDTO(String name, String lastname, String email, String affiliazione, String ruolo, ID id) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.affiliazione = affiliazione;
        this.ruolo = ruolo;
        this.id = id;
    }

    /**
     * Costruttore di copia per un utente
     * @param user
     * @param scelta
     */
    public RUserDTO(User user) {
        this.name = user.getName();
        this.lastname = user.getLastName();
        this.email = user.getEmail();
        this.affiliazione = user.getAffiliation();
        this.ruolo = user.getRole();
        this.id = user.getId();
    }

    /**
     * Costruttore di copia
     * @param Ruser
     */
    public RUserDTO(RUserDTO Ruser) {
        this.name = Ruser.getName();
        this.lastname= Ruser.getLastname();;
        this.email = Ruser.getEmail();
        this.affiliazione = Ruser.getAffiliation();
        this.ruolo = Ruser.getRole();
        this.id = Ruser.getId();
    }

    /**
     * Nome del'utente
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Cognome del'utente
     * @return
     */
    public String getLastname() {
        return this.lastname;
    }

    /**
     * Email del'utente
     * @return
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Affilazione del'utente
     * @return
     */
    public String getAffiliation() {
        return this.affiliazione;
    }

    /**
     * Ruolo del'utente
     * @return
     */
    public String getRole() {
        return this.ruolo;
    }

    /**
     * ID del'utente
     * @return
     */
    public ID getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "\n" + this.name + "\n" + this.lastname +"\n" + this.email + "\n" + this.affiliazione+"\n" + this.ruolo + "\n\n";
    }
}
