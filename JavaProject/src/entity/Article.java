package entity;

import java.util.ArrayList;
import utility.ID;

public class Article {
    private ID id;
    private String title;
    private String abstr;
    private ArrayList<Autore> authors;

    public Article(ID id, String abstr, ArrayList<Autore> authors, String titolo) {
        this.abstr = abstr;
        this.authors = authors;
        this.title = titolo;
	this.id = id;
    }

    public Article(Article a){
	this.id = a.getId();
    this.title = a.getTitle();
	this.abstr = a.getAbstr();
	this.authors = a.getAuthors();
    }

    public ArrayList<Autore> getAuthors() {
        return authors;
    }

    public String getAbstr() {
        return abstr;
    }

    public String getTitle() {
        return title;
    }

    public ID getId(){
	return id;
    }
}
