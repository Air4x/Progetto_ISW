package DTO;

import java.util.ArrayList;
import utility.ID;
import entity.Author;

public class ShowArticleDTO {

    private ID id;
    private String titolo;
    private String abstr;
    private ArrayList<Author> autori;
    
    public ShowArticleDTO(ID id, String titolo, String abstr, ArrayList<Author> autori) {
        this.id = id;
        this.titolo = titolo;
        this.abstr = abstr;
        this.autori = autori;
    }

    public ID getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public String getAbstr() {
        return abstr;
    }

    public ArrayList<Author> getAutori() {
        return autori;
    }



}
