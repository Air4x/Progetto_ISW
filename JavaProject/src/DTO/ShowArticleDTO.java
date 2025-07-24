package DTO;

import java.util.ArrayList;
import utility.ID;

public class ShowArticleDTO {

    
    private ID id;
    private String titolo;
    private String abstr;
    private ArrayList<ID> autori;
    
    public ShowArticleDTO(ID id, String titolo, String abstr, ArrayList<ID> autori) {
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

    public ArrayList<ID> getAutori() {
        return autori;
    }



}
