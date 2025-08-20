package org.groupone.entity;

import java.util.ArrayList;
import org.groupone.utility.ID;

/**
 * Classe che modella un Articolo
 *
 * @author Mario Calcagno
 */

public class Article {
    /**
     * L'identificativo dell'articolo
     *
     */
    private ID id;
    
    /**
     * Il titolo dell'articolo
     *
     */
    private String title;

    /**
     * L'abstract dell'articolo
     *
     */
    private String abstr;

    /**
     * Lista degli autori dell'articolo
     *
     */
    private ArrayList<Author> authors;

    /**
     * Costruttore per Article
     *
     * @param id
     * @param abstr
     * @param authors
     * @param titolo
     */
    public Article(ID id, String abstr, ArrayList<Author> authors, String titolo) {
        this.abstr = abstr;
        this.authors = authors;
        this.title = titolo;
	this.id = id;
    }

    /**
     * Costruttore di copia per Article
     *
     * @param a
     */
    public Article(Article a){
	this.id = a.getId();
	this.title = a.getTitle();
	this.abstr = a.getAbstr();
	this.authors = a.getAuthors();
    }

    /**
     * Getter per ottenere la lista di autori
     *
     * @return lista degli autori
     */
    public ArrayList<Author> getAuthors() {
        return authors;
    }

    /**
     * Getter per l'abstract 
     *
     * @return l'abstract dell'articolo
     */
    public String getAbstr() {
        return abstr;
    }

    /**
     * Getter per il titolo
     *
     * @return il titolo dell'articolo
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter per l'id 
     *
     * @return l'id dell'articolo
     */
    public ID getId(){
	return id;
    }
}
