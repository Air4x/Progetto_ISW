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
    private boolean scelta;

    /**
     * Costruttore
     * @param name
     * @param lastname
     * @param email
     * @param affiliazione
     * @param ruolo
     * @param scelta
     * @param id
     */
    public RUserDTO(String name, String lastname, String email, String affiliazione, String ruolo, boolean scelta,ID id) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.affiliazione = affiliazione;
        this.ruolo = ruolo;
        this.id = id;
        this.scelta =scelta;
    }

    /**
     * Costruttore
     * @param user
     * @param scelta
     */
    public RUserDTO(User user, boolean scelta) {
        this.name = user.getName();
        this.lastname = user.getLastName();
        this.email = user.getEmail();
        this.affiliazione = user.getAffiliation();
        this.ruolo = user.getRole();
        this.id = user.getId();
        this.scelta = scelta;
    }

    /**
     * Costruttore di copia
     * @param user
     */
    public RUserDTO(RUserDTO user) {
        this.name = user.getName();
        this.lastname= user.getLastname();;
        this.email = user.getEmail();
        this.affiliazione = user.getAffiliation();
        this.ruolo = user.getRole();
        this.id = user.getId();
        this.scelta =false;
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

    /**
     * Elemento per verifica della registrazione dell'untente
     * @return
     */
    public boolean getResult(){
        return this.scelta;
    }

    @Override
    public String toString() {
        return "\nID=" + this.id + "\nNome=" + this.name + "\nCognome=" + this.lastname +"\nEmail=" + this.email + "\nAffiliazione=" + this.affiliazione+"\nRuolo=" + this.ruolo + "\n";
    }
}
