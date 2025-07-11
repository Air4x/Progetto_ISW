package entity;

public class Author extends User{
    final String role;

    public Author(String affiliazione, String email, String lastName,
                  String name, String password, int id) {
        super(affiliazione, email, lastName, name, password, id);
        this.role = "autore";
    }

    public String getRole() {
        return this.role;
    }
}
