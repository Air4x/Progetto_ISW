package org.groupone.entity;

import java.util.ArrayList;
import java.util.Objects;

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
     * Lo stato dell'articolo
     *
     */
    private String stato;
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
	this.stato = "sottomesso";
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
	this.stato = a.getStato();
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
     * Getter per lo stato
     *
     * @return lo stato attuale dell'articolo
     */
    public String getStato() {
	return this.stato;
    }

    /**
     * Getter per l'id 
     *
     * @return l'id dell'articolo
     */
    public ID getId(){
	return id;
    }
    /**
     * Override le metodo equals
     * @param o, l'elemento con cui fare la comparazione
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(getId(),
			      article.getId()) &&
	    Objects.equals(getTitle(), article.getTitle()) &&
	    Objects.equals(getAbstr(), article.getAbstr()) &&
	    Objects.equals(getAuthors(), article.getAuthors());
    }
}
