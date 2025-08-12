package controller;

import database.ArticleDAO;
import DTO.RUserDTO;
import database.ConferenceDAO;
import entity.Articolo;
import entity.Author;
import database.UserDAO;
import java.sql.SQLException;
import DTO.ShowArticleDTO;
import java.util.ArrayList;
import utility.ID;


/**
 * @author Giuseppe Buglione
 * Classe per utilizzata per la gestione degli Articoli
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
     * @param a_titolo
     * @param a_abstrct
     * @param a_autori
     * @param id_conf
     * @return
     * @throws SQLException
     * 
     */
    public boolean submitArticle(String a_titolo, String a_abstrct,  ArrayList<RUserDTO> a_autori, ID id_conf) throws SQLException{
        ArrayList<Author> authors_list = new ArrayList<>();
        Author user= null;
        ID id = ID.generate();
        try {

            if(this.conf_dao.getConferenceByID(id_conf) != null){
            for(RUserDTO f_user : a_autori){
                if(user_dao.isUserPresentByEmail(f_user.getEmail()) && user_dao.getUserByEmail(f_user.getEmail()).getRole() == "autore" ){
                    user = (Author) user_dao.getUserByEmail(f_user.getEmail());
                    authors_list.add(user);
                }else{return false;}
            }
                    Articolo art = new Articolo(id, a_abstrct, authors_list, a_titolo);
                    art_dao.saveArticle(art); 
                    return true;
            }
        } catch (SQLException e) {
            e.getSQLState();
            e.getSQLState();
            e.getMessage();
        }  
        return false;
        
        
    }

    /**
     * Metodo per ottenere un articolo attraverso gli autori 
     * @param authorID
     * @return
     * @throws SQLException
     */
    public ArrayList<ShowArticleDTO> getArticleByAuthor(ID authorID) throws SQLException{
        ArrayList<Articolo> articoli = art_dao.getArticlesByAuthor(authorID);
        ArrayList<ShowArticleDTO> f_art = new ArrayList<>();
        for(Articolo c : articoli){
            ShowArticleDTO a = new ShowArticleDTO(c);
            f_art.add(a);
        }
        return f_art;
    }
}
