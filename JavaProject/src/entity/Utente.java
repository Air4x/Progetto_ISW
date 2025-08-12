package entity;

import utility.ID;
/**
 * Classe di dominio astratta, contiene i dati e le funzionalità comuni tra {@see entity.Autore} e {@see entity.Organizzatore}
 * @author Mario Calcagno
 */
public abstract class Utente {
    /**
     * Il codice identificativo dell'utente, è un UUIDv?
     */
    private ID id;
    /**
     * Il nome dell'utente
     */
    private String nome;
    /**
     * Il cognome dell'utente
     */
    private String cognome;
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
     * @param cognome
     * @param nome
     * @param password
     * @param id 
     */
    protected Utente(String affiliazione, String email, String cognome, String nome, String password, ID id) {
        this.affiliazione = affiliazione;
        this.email = email;
        this.cognome = cognome;
        this.nome = nome;
        this.password = password;
        this.id = id;
    }

    protected Utente(String affiliazione, String email, String cognome, String nome, String password) {
        this.affiliazione = affiliazione;
        this.email = email;
        this.cognome = cognome;
        this.nome = nome;
        this.password = password;
        this.id = ID.generate();
    }
    /**
     * Costruttore di copia
     * @param utente
     */
    protected Utente(Utente utente) {
	this.affiliazione = utente.getAffiliazione();
        this.email = utente.getEmail();
        this.cognome = utente.getCognome();
        this.nome = utente.getNome();
        this.password = utente.getPassword();
        this.id = utente.getId();
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
    public String getNome() {
        return nome;
    }

    /**
     * getter per il cognome dell'utente
     * @return il cognome dell'utente
     */
    public String getCognome() {
        return cognome;
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
    public abstract String getRuolo();
}
