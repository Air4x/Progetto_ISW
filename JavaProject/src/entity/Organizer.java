package entity;

import utility.ID;

/**
 * Classe che implementa un Organizzatore, estende {@see entity.User}
 *
 */
public class Organizer extends User{
    /**
     * Il ruolo dell'organizzatore
     */
    private final String role;

     /**
      * Costruttore di Organizer
      *
      * @param affiliazione
      * @param email
      * @param lastName
      * @param name
      * @param password
      * @param id 
      */
    public Organizer(String affiliazione, String email, String lastName, String name, String password, ID id) {
        super(affiliazione, email, lastName, name, password, id);
        this.role = "organizzatore";
    }
    /**
     * Costruttore di copia per Organizer
     *
     */
    public Organizer(Organizer organizer) {
	super(organizer.getAffiliazione(), organizer.getEmail(), organizer.getLastName(),
	      organizer.getName(), organizer.getPassword(), organizer.getId());
	this.role = organizer.getRole();
    }

    /**
     * Implementazione di {@see User.getRole}
     *
     * @return il ruole dell'organizzatore
     */
    public String getRole() {
        return this.role;
    }
}
