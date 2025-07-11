package entity;

public class Organizer extends User{
    private final String role;

    public Organizer(String affiliazione, String email, String lastName, String name, String password, int id) {
        super(affiliazione, email, lastName, name, password, id);
        this.role = "organizzatore";
    }

    public String getRole() {
        return this.role;
    }
}
