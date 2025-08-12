package entity;

import utility.ID;
/**
 * Classe che implementa un Autore, estende {@see entity.User}
 *
 */
public class Autore extends User {
    /**
     * Il ruolo dell'autore
     */
    final String role;

    /**
     * Costruttore di Autore
     *
     * @param affiliazione
     * @param email
     * @param lastName
     * @param name
     * @param password
     * @param id 
     */
    public Autore(String affiliazione, String email, String lastName,
                  String name, String password, ID id) {
        super(affiliazione, email, lastName, name, password, id);
        this.role = "autore";
    }

    /**
     * Costruttore di copia
     *
     * @param autore 
     */
    public Autore(Autore autore){
        super(autore.getAffiliation(), autore.getEmail(), autore.getLastName(),
	      autore.getName(), autore.getPassword(), autore.getId());
	this.role = autore.getRole();
    }	

    /**
     * Implementazione di {@see User.getRole}
     *
     * @return il ruole dell'autore
     */
    public String getRole() {
        return this.role;
    }
}
