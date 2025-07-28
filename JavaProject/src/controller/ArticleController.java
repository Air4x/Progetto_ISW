package controller;

import database.ArticleDAO;
import database.ConferenceDAO;
import entity.Articolo;
import entity.Author;
import java.sql.SQLException;
import DTO.ShowArticleDTO;

import java.util.ArrayList;
import java.util.List;
import utility.ID;


/**
 * @author Giuseppe Buglione
 * Classe per utilizzata per la sgestione degli Articoli
 */
public class ArticleController {

    private ArticleDAO art_dao;
    private ConferenceDAO conf_dao;

    public ArticleController () throws SQLException{
        this.art_dao= new ArticleDAO();
        this.conf_dao= new ConferenceDAO();
    }

    /**
     * Metodo per la sottomisione di un articolo a una conferenza
     * @param a_titolo
     * @param a_abstrct
     * @param a_autori
     * @param id_conf
     * @return
     * @throws SQLException
     * 
     */
    public boolean submitArticle(String a_titolo, String a_abstrct,  ArrayList<Author> a_autori, ID id_conf) throws SQLException{
        if(this.conf_dao.getConferenceByID(id_conf) != null){
            ID id = ID.generate();
            Articolo art = new Articolo(id, a_abstrct, a_autori, a_titolo);
            art_dao.saveArticle(art); 
            return true;
        }
        return false;
    }

    /**
     * Metodo per ottenere un articolo attraverso gli autori 
     * @param authorID
     * @return
     * @throws SQLException
     */
    public List<ShowArticleDTO> getArticleByAuthor(ID authorID) throws SQLException{
        List<Articolo> articoli = art_dao.getArticlesByAuthor(authorID);
        List<ShowArticleDTO> f_art = new ArrayList<>();
        for(Articolo c : articoli){
            ShowArticleDTO a = new ShowArticleDTO(c);
            f_art.add(a);
        }
        return f_art;
    }

    /**
     * Metodo per aggiorna lo status di un articolo
     * @param ID
     * @param status
     * @return
     * @throws SQLException
     */
    public boolean updateArticleStatus (ID ID, String status) throws SQLException {
        if(art_dao.getArticleByID(ID) != null){
        this.art_dao.updateArticleStatus(ID, status);
            return true;
        }
        return false;
    }

}
