package DTO;

import java.util.ArrayList;
import utility.ID;
import entity.Articolo;
import entity.Author;

/**
 * @author Giuseppe Buglione
 * Classe DTO per il trasporto delle informazioni di un articolo
 */
public class ShowArticleDTO {

    private ID id;
    private String titolo;
    private String abstr;
    private ArrayList<Author> autori;
    
    /**
     * Costrutore
     * @param id
     * @param titolo
     * @param abstr
     * @param autori
     */
    public ShowArticleDTO(ID id, String titolo, String abstr, ArrayList<Author> autori) {
        this.id = id;
        this.titolo = titolo;
        this.abstr = abstr;
        this.autori = autori;
    }

    /**
     * Costruttore
     * @param art
     */
    public ShowArticleDTO (Articolo art){
        this.id=art.getId();
        this.titolo = art.getTitolo();
        this.abstr = art.getAbstr();
        this.autori = art.getAutori();
    }

    /**
     * ID dell'articolo
     * @return
     */
    public ID getId() {
        return id;
    }

    /**
     * Titolo dell'articolo
     * @return
     */
    public String getTitolo() {
        return titolo;
    }

    /**
     * Abstract dell'articolo
     * @return
     */
    public String getAbstr() {
        return abstr;
    }

    /**
     * Autori dell'articolo
     * @return
     */
    public ArrayList<Author> getAutori() {
        return autori;
    }



}
