package entity;

import utility.ID;
/**
 * Classe che implementa un Autore, estende {@see entity.Utente}
 *
 */
public class Autore extends Utente {
    /**
     * Il ruolo dell'autore
     */
    final String ruolo;

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
        this.ruolo = "autore";
    }

    /**
     * Costruttore di copia
     *
     * @param autore 
     */
    public Autore(Autore autore){
        super(autore.getAffiliazione(), autore.getEmail(), autore.getCognome(),
	      autore.getNome(), autore.getPassword(), autore.getId());
	this.ruolo = autore.getRuolo();
    }	

    /**
     * Implementazione di {@see Utente.getRole}
     *
     * @return il ruole dell'autore
     */
    public String getRuolo() {
        return this.ruolo;
    }
}
