package org.groupone.entity;

import org.groupone.utility.ID;

public class Review {
    private ID id;
    private Author reviewer;
    private Article article;
    private int score;
    private String result;

    public Review(Author reviewer, Article article) {
	this.id = ID.generate();
	this.reviewer = reviewer;
	this.article = article;
	this.score = 0;
	this.result = "inattesa";
    }

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
