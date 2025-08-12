package DTO;

import entity.User;
import utility.ID;

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
    private boolean esito;

    /**
     * Costruttore
     * @param name
     * @param lastname
     * @param email
     * @param affiliazione
     * @param ruolo
     * @param esito
     * @param id
     */
    public RUserDTO(String name, String lastname, String email, String affiliazione, String ruolo, boolean esito,ID id) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.affiliazione = affiliazione;
        this.ruolo = ruolo;
        this.id = id;
        this.esito=esito;
    }

    /**
     * Costruttore
     * @param user
     * @param esito
     */
    public RUserDTO(User user, boolean esito) {
        this.name = user.getName();
        this.lastname = user.getLastName();
        this.email = user.getLastName();
        this.affiliazione = user.getAffiliation();
        this.ruolo = user.getRole();
        this.id = user.getId();
        this.esito=esito;
    }

    /**
     * Nome del'utente
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Cognome del'utente
     * @return
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Email del'utente
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Affilazione del'utente
     * @return
     */
    public String getAffiliazione() {
        return affiliazione;
    }

    /**
     * Ruolo del'utente
     * @return
     */
    public String getRuolo() {
        return ruolo;
    }

    /**
     * ID del'utente
     * @return
     */
    public ID getId() {
        return id;
    }

    /**
     * Elemento per verifica della registrazione dell'untente
     * @return
     */
    public boolean getEsito(){
        return this.esito;
    }
}
