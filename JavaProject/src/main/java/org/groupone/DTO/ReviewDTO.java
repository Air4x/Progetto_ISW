package org.groupone.DTO;

import org.groupone.entity.Review;

/**
 * @author Giuseppe Buglione
 * Classe DTO per il trasporto delle informazioni di un revisione
 */
public class ReviewDTO {

    private String article;
    private int score;
    private String result;
    
    /**
     * Costruttore
     * @param article
     * @param score
     * @param result
     */
    public ReviewDTO(String article, int score, String result) {
        this.article = article;
        this.score = score;
        this.result = result;
    }

    /**
     * Costruttore di copia per un autore
     * @param review
     */
    public ReviewDTO(Review review) {
        this.article = review.getArticle().getTitle();
        this.score = review.getScore();
        this.result = review.getResult();
    }

    /**
     * Nome dell'articolo della revisione
     * @return
     */
    public String getArticle() {
        return article;
    }

    /**
     * Risultato della revisione
     * @return
     */
    public String getResult() {
        return result;
    }

    /**
     * Punteggio della revisione
     * @return
     */
    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "\n"+this.article + "\n" + this.score + "\n" + this.result + "\n";
    }
}
