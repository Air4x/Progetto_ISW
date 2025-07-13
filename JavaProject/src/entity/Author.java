package entity;

public class Author extends User{
    final String role;

    public Author(String affiliazione, String email, String lastName,
                  String name, String password, String id) {
        super(affiliazione, email, lastName, name, password, id);
        this.role = "autore";
    }

    public Author(Author a){	
        super(a.getAffiliazione(), a.getEmail(), a.getLastName(),
	      a.getName(), a.getPassword(), a.getId());
	this.role = a.getRole();
    }	

    public String getRole() {
        return this.role;
    }
}
