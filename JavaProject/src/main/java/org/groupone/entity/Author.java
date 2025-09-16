package org.groupone.entity;

import org.groupone.utility.ID;

import java.util.Objects;

/**
 * Classe che implementa un Autore, estende {@see entity.User}
 *
 */
public class Author extends User {
    /**
     * Il ruolo dell'autore
     */
    final String role;

    /**
     * Costruttore di Author
     *
     * @param affiliazione
     * @param email
     * @param lastName
     * @param name
     * @param password
     * @param id 
     */
    public Author(String affiliazione, String email, String lastName,
                  String name, String password, ID id) {
        super(affiliazione, email, lastName, name, password, id);
        this.role = "autore";
    }

    /**
     * Costruttore di copia
     *
     * @param autore 
     */
    public Author(Author autore){
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
    /**
     * Override le metodo equals
     * @param o, l'elemento con cui fare la comparazione
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Author author = (Author) o;
        return Objects.equals(getRole(), author.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRole());
    }
}
