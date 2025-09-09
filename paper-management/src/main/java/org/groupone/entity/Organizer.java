package org.groupone.entity;

import org.groupone.utility.ID;

import java.util.Objects;

/**
 * Classe che implementa un Organizer, estende {@see entity.User}
 *
 */
public class Organizer extends User {
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
	super(organizer.getAffiliation(), organizer.getEmail(), organizer.getLastName(),
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
    /**
     * Override le metodo equals
     * @param o, l'elemento con cui fare la comparazione
     * @return se i due oggetti sono uguali o meno
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Organizer organizer = (Organizer) o;
        return Objects.equals(getRole(), organizer.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRole());
    }
}
