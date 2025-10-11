package org.groupone.entity;

import org.groupone.utility.ID;


/**
 * Classe che rappresenta una revisione
 *
 */
public class Review {
    /**
     * L'id della revisione
     *
     */
    private ID id;
    /**
     * L'autore a cui è stata assegnara la revisione
     *
     */
    private Author reviewer;
    /**
     * L'articolo di cui si fa la revisione
     *
     */
    private Article article;
    /**
     * Il punteggio della revisione
     *
     */
    private int score;
    /**
     * L'esito della revisione
     *
     */
    private String result;

    /**
     * Costruttore di Review, utilizza valori di default
     *
     * @param reviewer
     */
    public Review(Author reviewer, Article article) {
	this.id = ID.generate();
	this.reviewer = reviewer;
	this.article = article;
	this.score = 0;
	this.result = "inattesa";
    }
    
    /**
     * Costruttore di Review, senza valori di default
     *
     * @param id
     * @param reviewer
     * @param article
     * @param score
     * @param esito
     */
    public Review(ID id, Author reviewer, Article article, int score, String esito){
	this.id = id;
	this.reviewer = reviewer;
	this.article = article;
	this.score = score;
	this.result = esito;	
    }

    public ID getId() {
	return id;
    }
    
    public Author getReviewer() {
	return reviewer;
    }

    public Article getArticle() {
	return article;
    }

    public int getScore() {
	return score;
    }

    public String getResult() {
	return result;
    }

    public void setScore(int score) {
	this.score = score;
    }

    public void setResult(String result) {
	this.result = result;
    }
}
