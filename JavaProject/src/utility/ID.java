package utility;

import java.util.UUID;


public class ID {
    private UUID id;

    public ID(String s){
	id = UUID.fromString(s);
    }

    private ID() {
	this.id = UUID.randomUUID();
    }
    
    public static synchronized ID generate(){
	return new ID();
    }

    public UUID getID() {
	return this.id;
    }

    @Override
    public String toString() {
	return this.id.toString();
    }
}
