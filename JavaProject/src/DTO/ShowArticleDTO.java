package DTO;

import java.util.ArrayList;

import entity.Autore;
import utility.ID;
import entity.Articolo;

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
     * @param articolo
     */
    public ShowArticleDTO (Articolo articolo){
        RUserDTO user_f= null;
        for (Autore a: articolo.getAutori()){
            user_f= new RUserDTO(a.getNome(), a.getCognome(), a.getEmail(), a.getAffiliazione(), a.getRuolo(), false, a.getId());
            this.autori.add(user_f);
        }
        this.id=articolo.getId();
        this.titolo = articolo.getTitolo();
        this.abstr = articolo.getAbstr();
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
    public ArrayList<RUserDTO> getAutori() {
        return autori;
    }



}
