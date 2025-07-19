package entity;
/**
 * Classe che implementa un Autore, estende {@see entity.User}
 *
 */
public class Author extends User{
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
                  String name, String password, String id) {
        super(affiliazione, email, lastName, name, password, id);
        this.role = "autore";
    }

    /**
     * Costruttore di copia
     *
     * @param autore 
     */
    public Author(Author autore){	
        super(autore.getAffiliazione(), autore.getEmail(), autore.getLastName(),
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
