package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import utility.ID;

public class Conference {
    private ID id;
    private String title;
    private String description;
    private Date deadline;
    private ArrayList<Article> articles;

    public Conference(Date deadline, String title, String description, ID id) {
        this.id = id;
        this.deadline = deadline;
        this.title = title;
        this.description = description;
        this.articles = new ArrayList<>();
    }

    public Conference(Conference c) {
	this.id = c.getId();
        this.deadline = c.getDeadline();
        this.title = c.getTitle();
        this.description = c.getDescription();
        this.articles = c.getArticles();
    }
    
    public ArrayList<Article> getArticles() {
        return this.articles;
    }

    public ID getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public String getTitle() {
        return title;
    }

    public boolean inScadenza () {
	Date now = new Date();
	Calendar.Builder calBuilder = new Calendar.Builder();
        calBuilder.setInstant(now);
        Calendar cNow = calBuilder.build();
        calBuilder.setInstant(this.getDeadline());
        Calendar cScadenza = calBuilder.build();
        int giornoNow = cNow.get(Calendar.DAY_OF_YEAR);
        int giornoScadenza = cScadenza.get(Calendar.DAY_OF_YEAR);
        if((giornoScadenza - giornoNow) <= 5){
	    return true;
        } else { return false; }
    }
}
