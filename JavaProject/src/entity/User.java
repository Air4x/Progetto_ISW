package entity;

import utility.ID;
/**
 * Classe di dominio astratta, contiene i dati e le funzionalità comuni tra {@see entity.Author} e {@see entity.Organizer}
 * @author Mario Calcagno
 */
public abstract class User {
    /**
     * Il codice identificativo dell'utente, è un UUIDv?
     */
    private ID id;
    /**
     * Il nome dell'utente
     */
    private String name;
    /**
     * Il cognome dell'utente
     */
    private String lastName;
    /**
     * L'email dell'utente
     */
    private String email;
    /**
     * L'università dell'utente
     */
    private String affiliazione;
    /**
     * Lo SHA256 della password dell'utente
     */
    private String password;

    /**
     * Istanzia un utente
     * @param affiliazione
     * @param email
     * @param lastName
     * @param name
     * @param password
     * @param id 
     */
    protected User(String affiliazione, String email, String lastName, String name, String password, ID id) {
        this.affiliazione = affiliazione;
        this.email = email;
        this.lastName = lastName;
        this.name = name;
        this.password = password;
        this.id = id;
    }

    protected User(String affiliazione, String email, String lastName, String name, String password) {
        this.affiliazione = affiliazione;
        this.email = email;
        this.lastName = lastName;
        this.name = name;
        this.password = password;
        this.id = ID.generate();
    }
    /**
     * Costruttore di copia
     * @param user
     */
    protected User(User user) {
	this.affiliazione = user.getAffiliazione();
        this.email = user.getEmail();
        this.lastName = user.getLastName();
        this.name = user.getName();
        this.password = user.getPassword();
        this.id = user.getId();
    }

    /**
     * getter per id
     * @return l'id dell'utente
     */
    public ID getId() {
        return id;
    }

    /**
     * getter per il nome dell'utente
     * @return il nome dell'utente
     */
    public String getName() {
        return name;
    }

    /**
     * getter per il cognome dell'utente
     * @return il cognome dell'utente
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * getter per l'affiliazione dell'utente
     * @return l'affiliazione dell'utente
     */
    public String getAffiliazione() {
        return affiliazione;
    }

    /**
     * getter per l'email dell'utente
     * @return l'email dell'utente
     */
    public String getEmail() {
        return email;
    }

    /**
     * getter per la password dell'utente
     * @return la password dell'utente
     */
    public String getPassword() {
        return password;
    }

    /**
     * Cambia l'affiliazione dell'utente
     * @param la nuova affiliazione
     */
    public void setAffiliazione(String affiliazione) {
        this.affiliazione = affiliazione;
    }

    /**
     * Cambia l'email per l'utente
     * @param la nuova email dell'utente
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Metodo astratto implementato da le sotto classi
     * @return il ruolo dell'utente
     */
    public abstract String getRole();
}
