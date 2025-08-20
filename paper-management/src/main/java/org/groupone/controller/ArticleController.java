package org.groupone.controller;

import org.groupone.database.ArticleDAO;
import org.groupone.DTO.RUserDTO;
import org.groupone.database.ConferenceDAO;
import org.groupone.database.UserDAO;
import org.groupone.entity.Article;
import org.groupone.entity.Author;
import java.sql.SQLException;
import org.groupone.DTO.ShowArticleDTO;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.groupone.utility.ID;


/**
 * @author Giuseppe Buglione
 * Classe per utilizzata per la gestione degli Articoli
 */
public class ArticleController {

    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);
    private ArticleDAO art_dao;
    private UserDAO user_dao;
    private ConferenceDAO conf_dao;

    public ArticleController () throws SQLException {
        this.art_dao= new ArticleDAO();
        this.user_dao= new UserDAO();
        this.conf_dao= new ConferenceDAO();
    }

    /**
     * Metodo per la sottomisione di un articolo a una conferenza
     * @param article_titolo
     * @param article_abstract
     * @param article_autori
     * @param id_conf
     * @return
     * @throws SQLException
     * 
     */
    public boolean submitArticle(String article_titolo, String article_abstract,  ArrayList<RUserDTO> article_autori, ID id_conf) throws SQLException{
        ID article_id = ID.generate();
        ArrayList<Author> authors_list = new ArrayList<>();
        Date scadenza = this.conf_dao.getConferenceByID(id_conf).getDeadline();
        Date today = new Date();;
        if(this.conf_dao.getConferenceByID(id_conf) != null && today.after(scadenza) == false) {
            for (RUserDTO fake_user : article_autori) {if (user_dao.isUserPresentByEmail(fake_user.getEmail()) && user_dao.getUserByEmail(fake_user.getEmail()).getRole() == "autore") {
                authors_list.add((Author) user_dao.getUserByEmail(fake_user.getEmail()));}
            }
            Article art = new Article(article_id, article_abstract, authors_list, article_titolo);
            art_dao.saveArticle(art);
            return true;
        }else if (today.after(scadenza) == true){
            System.out.println("Conferenza Scaduta");
        }
        return false;
    }

    /**
     * Metodo per ottenere un articolo attraverso gli autori 
     * @param authorID
     * @return
     * @throws SQLException
     */
    public ArrayList<ShowArticleDTO> getArticleByAuthor(ID authorID) throws SQLException {
        if (this.user_dao.isUserPresentByID(authorID) == true) {
            ArrayList<ShowArticleDTO> fake_article = new ArrayList<>();
            ArrayList<Article> real_article = art_dao.getArticlesByAuthor(authorID);
            for (Article article : real_article) {
                ShowArticleDTO articleDTO = new ShowArticleDTO(article);
                fake_article.add(articleDTO);
            }
            return fake_article;
        }else  if (this.user_dao.isUserPresentByID(authorID) == false){
            System.out.println("Utente non trovato");
        }
        return null;
    }
}
