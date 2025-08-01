package entity;

import java.util.ArrayList;
import utility.ID;

public class Articolo {
    private ID id;
    private String titolo;
    private String abstr;
    private ArrayList<Author> autori;

    public Articolo(ID id, String abstr, ArrayList<Author> autori, String titolo) {
        this.abstr = abstr;
        this.autori = autori;
        this.titolo = titolo;
	this.id = id;
    }

    public Articolo(Articolo a){
	this.id = a.getId();
        this.titolo = a.getTitolo();
	this.abstr = a.getAbstr();
	this.autori = a.getAutori();
    }

    public ArrayList<Author> getAutori() {
        return autori;
    }

    public String getAbstr() {
        return abstr;
    }

    public String getTitolo() {
        return titolo;
    }

    public ID getId(){
	return id;
    }
}
