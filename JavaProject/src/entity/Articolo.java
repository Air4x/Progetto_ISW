package entity;

import java.util.List;

public class Articolo {
    private String titolo;
    private String abstr;
    private List<Author> autori;

    public Articolo(String abstr, List<Author> autori, String titolo) {
        this.abstr = abstr;
        this.autori = autori;
        this.titolo = titolo;
    }

    public List<Author> getAutori() {
        return autori;
    }

    public String getAbstr() {
        return abstr;
    }

    public String getTitolo() {
        return titolo;
    }
}
