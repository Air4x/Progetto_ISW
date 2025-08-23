package org.groupone.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.groupone.DTO.RUserDTO;
import org.groupone.DTO.ShowArticleDTO;
import org.groupone.database.ArticleDAO;
import org.groupone.database.ConferenceDAO;
import org.groupone.database.UserDAO;
import org.groupone.entity.Article;
import org.groupone.entity.Author;
import org.groupone.entity.Conference;
import org.groupone.utility.ID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
     * @return Oggetto boolean che va a indicare se la sottomissione di un articoloè avvenuta con successo
     * @throws SQLException
     * 
     */
    public boolean submitArticle(String article_titolo, String article_abstract,  ArrayList<RUserDTO> article_autori, ID id_conf) throws SQLException{
        ID article_id = ID.generate();
        boolean result = false; // verifica che la lista di autori risulta tutta nulla true=not null, false=null
        ArrayList<Author> authors_list = new ArrayList<>();
        Date today = new Date();
        if (this.conf_dao.isConferencePresentByID(id_conf)==false){
            System.out.println("Conference not found");
            return false;
        }
        Date scadenza = this.conf_dao.getConferenceByID(id_conf).getDeadline();
        if(scadenza.before(today)){
            System.out.println("The selected conference is expired");
            return false;
        }
        for(RUserDTO autori : article_autori){
            if(autori != null){
                result =true;
                break;
            }
        }
        if(result == false){
            System.out.println("List of authors is empty");
            return false;
        }

        Conference conf = this.conf_dao.getConferenceByID(id_conf);
        for (RUserDTO fake_user : article_autori) {
            if(fake_user == null) {
                System.out.println("User not found");
            }else if(user_dao.isUserPresentByEmail(fake_user.getEmail()) == true && user_dao.getUserByEmail(fake_user.getEmail()).getRole() == "autore") {
                authors_list.add((Author) user_dao.getUserByEmail(fake_user.getEmail()));
            }
        }
        Article art = new Article(article_id, article_abstract, authors_list, article_titolo);
        conf.getArticles().add(art);
        art_dao.saveArticle(art);
        return true;
    }

    /**
     * Metodo per ottenere un articolo attraverso gli autori 
     * @param authorID
     * @return Un array list di ShowArticleDTO ottenuto attraverso l'ID dell'autore degli articoli
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
