package entity;

public abstract class User {
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String affiliazione;
    private String password;

    protected User(String affiliazione, String email, String lastName, String name, String password, String id) {
        this.affiliazione = affiliazione;
        this.email = email;
        this.lastName = lastName;
        this.name = name;
        this.password = password;
        this.id = id;
    }

    protected User(User a) {
	this.affiliazione = a.getAffiliazione();
        this.email = a.getEmail();
        this.lastName = a.getLastName();
        this.name = a.getName();
        this.password = a.getPassword();
        this.id = a.getId();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAffiliazione() {
        return affiliazione;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setAffiliazione(String affiliazione) {
        this.affiliazione = affiliazione;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public abstract String getRole();
}
