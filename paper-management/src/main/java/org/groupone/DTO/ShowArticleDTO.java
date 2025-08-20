package org.groupone.DTO;

import org.groupone.entity.Article;
import org.groupone.entity.Author;
import java.util.ArrayList;
import org.groupone.utility.ID;

/**
 * @author Giuseppe Buglione
 * Classe DTO per il trasporto delle informazioni di un articolo
 */
public class ShowArticleDTO {

    private ID id;
    private String titolo;
    private String abstr;
    private ArrayList<RUserDTO> autori;
    
    /**
     * Costrutore
     * @param id
     * @param titolo
     * @param abstr
     * @param autori
     */
    public ShowArticleDTO(ID id, String titolo, String abstr, ArrayList<RUserDTO> autori) {
        this.id = id;
        this.titolo = titolo;
        this.abstr = abstr;
        this.autori = autori;
    }

    /**
     * Costruttore
     * @param article
     */
    public ShowArticleDTO (Article article){
        RUserDTO user_f= null;
        for (Author a: article.getAuthors()){
            user_f= new RUserDTO(a.getName(), a.getLastName(), a.getEmail(), a.getAffiliation(), a.getRole(), false, a.getId());
            this.autori.add(user_f);
        }
        this.id= article.getId();
        this.titolo = article.getTitle();
        this.abstr = article.getAbstr();
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
    public String getTitle() {
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
    public ArrayList<RUserDTO> getAuthors() {
        return autori;
    }



}
