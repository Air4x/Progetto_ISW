package entity;

import utility.ID;

/**
 * Classe che implementa un Organizzatore, estende {@see entity.Utente}
 *
 */
public class Organizzatore extends Utente {
    /**
     * Il ruolo dell'organizzatore
     */
    private final String ruolo;

     /**
      * Costruttore di Organizzatore
      *
      * @param affiliazione
      * @param email
      * @param lastName
      * @param name
      * @param password
      * @param id 
      */
    public Organizzatore(String affiliazione, String email, String lastName, String name, String password, ID id) {
        super(affiliazione, email, lastName, name, password, id);
        this.ruolo = "organizzatore";
    }
    /**
     * Costruttore di copia per Organizzatore
     *
     */
    public Organizzatore(Organizzatore organizzatore) {
	super(organizzatore.getAffiliazione(), organizzatore.getEmail(), organizzatore.getCognome(),
	      organizzatore.getNome(), organizzatore.getPassword(), organizzatore.getId());
	this.ruolo = organizzatore.getRuolo();
    }

    /**
     * Implementazione di {@see Utente.getRole}
     *
     * @return il ruole dell'organizzatore
     */
    public String getRuolo() {
        return this.ruolo;
    }
}
