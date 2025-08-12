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
    private final String role;

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
        this.role = "organizzatore";
    }
    /**
     * Costruttore di copia per Organizzatore
     *
     */
    public Organizzatore(Organizzatore organizzatore) {
	super(organizzatore.getAffiliazione(), organizzatore.getEmail(), organizzatore.getLastName(),
	      organizzatore.getName(), organizzatore.getPassword(), organizzatore.getId());
	this.role = organizzatore.getRole();
    }

    /**
     * Implementazione di {@see Utente.getRole}
     *
     * @return il ruole dell'organizzatore
     */
    public String getRole() {
        return this.role;
    }
}
