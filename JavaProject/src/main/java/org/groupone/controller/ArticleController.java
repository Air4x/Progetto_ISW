package org.groupone.controller;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.groupone.DTO.RUserDTO;
import org.groupone.DTO.ShowArticleDTO;
import org.groupone.database.ArticleDAO;
import org.groupone.database.ConferenceDAO;
import org.groupone.database.UserDAO;
import org.groupone.entity.Article;
import org.groupone.entity.Author;
import org.groupone.utility.ID;


/**
 * @author Giuseppe Buglione
 * Classe che implementa la logica di gestione degli Articoli
 */
public class ArticleController {

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
        ArrayList<Author> authors_list = new ArrayList<>();
        if (this.conf_dao.isConferencePresentByID(id_conf)==false){
            System.out.println("Conference not found");
            return false;
        }
        if(this.conf_dao.getConferenceByID(id_conf).getDeadline().before(Date.valueOf(LocalDate.now()))){
            System.out.println("The selected conference is expired");
            return false;
        }            
        if(article_autori.isEmpty()){
            System.out.println("List of authors is empty");
            return false;
        }
        for (RUserDTO fake_user : article_autori) {
            if(fake_user != null && user_dao.isUserPresentByEmail(fake_user.getEmail()) == true && user_dao.getUserByEmail(fake_user.getEmail()).getRole() == "autore") {
                authors_list.add((Author) user_dao.getUserByEmail(fake_user.getEmail()));
            }
        }
        if(authors_list.isEmpty()){
            System.out.println("List of authors dto is empty");
            return false;
        }
        Article art = new Article(article_id, article_abstract, authors_list, article_titolo);
        this.conf_dao.getConferenceByID(id_conf).getArticles().add(art);
        art_dao.saveArticle(art);
        System.out.println("Articolo consegnato");
        return true;
    }

    /**
     * Metodo per ottenere un articolo attraverso gli autori 
     * @param authorID
     * @return Un array list di ShowArticleDTO ottenuto attraverso l'ID dell'autore degli articoli
     * @throws SQLException
     */
    public ArrayList<ShowArticleDTO> getArticleByAuthor(ID authorID) throws SQLException {
        ArrayList<ShowArticleDTO> fake_article = new ArrayList<>();
        if (this.user_dao.isUserPresentByID(authorID) == true) {
            ArrayList<Article> real_article = art_dao.getArticlesByAuthor(authorID);
            for (Article article : real_article) {
                ShowArticleDTO articleDTO = new ShowArticleDTO(article);
                fake_article.add(articleDTO);
            }
            if(fake_article.isEmpty() || fake_article == null){
                System.out.println("Nessun articolo trovato per questo autore");
                return fake_article;
            }
            return fake_article;
        }else  if (this.user_dao.isUserPresentByID(authorID) == false){
            System.out.println("Utente non trovato");
        }
        return fake_article;
    }
}
