package org.groupone.DTO;

import java.util.ArrayList;

import org.groupone.entity.Article;
import org.groupone.entity.Author;
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
    private String status;
    
    /**
     * Costrutore
     * @param id
     * @param titolo
     * @param abstr
     * @param autori
     */
    public ShowArticleDTO(ID id, String titolo, String abstr, ArrayList<RUserDTO> autori, String status) {
        this.id = id;
        this.titolo = titolo;
        this.abstr = abstr;
        this.autori = autori;
        this.status = status;
    }

    /**
     * Costruttore di copia per un articolo
     * @param article
     */
    public ShowArticleDTO (Article article){
        RUserDTO user_f= null;
        this.id= article.getId();
        this.titolo = article.getTitle();
        this.abstr = article.getAbstr();
        this.autori = new ArrayList<>();
        for (Author author : article.getAuthors()){
            user_f= new RUserDTO((Author)author);
            this.autori.add(user_f);
        }
        this.status= article.getStato();
    }

    /**
     * Costruttore di copia
     * @param aritcle_dto
     */
    public ShowArticleDTO (ShowArticleDTO aritcle_dto) {
        RUserDTO user_f= null;
        this.id= aritcle_dto.getId();
        this.titolo= aritcle_dto.getTitle();
        this.abstr= aritcle_dto.getAbstr();
        this.autori = new ArrayList<>();
        for(RUserDTO fake_user: aritcle_dto.getAuthors()){
            user_f= new RUserDTO(fake_user);
            this.autori.add(user_f);
        }
        this.status= aritcle_dto.getStatus();
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

    /**
     * Stato di un articolo
     */
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "\n" + this.titolo + "\n" + this.abstr + "\n"  + this.status + "\n" + this.autori.toString()+"\n\n";
    }

}
