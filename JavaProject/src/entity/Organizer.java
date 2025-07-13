package entity;

public class Organizer extends User{
    private final String role;

    public Organizer(String affiliazione, String email, String lastName, String name, String password, String id) {
        super(affiliazione, email, lastName, name, password, id);
        this.role = "organizzatore";
    }

    public Organizer(Organizer o) {
	super(o.getAffiliazione(), o.getEmail(), o.getLastName(),
	      o.getName(), o.getPassword(), o.getId());
	this.role = o.getRole();
    }

    public String getRole() {
        return this.role;
    }
}
