package controller;

import database.ArticoloDAO;
import DTO.RUserDTO;
import database.ConferenzeDAO;
import database.UtenteDAO;
import entity.Articolo;
import entity.Autore;

import java.sql.SQLException;
import DTO.ShowArticleDTO;
import java.util.ArrayList;

import utility.ID;


/**
 * @author Giuseppe Buglione
 * Classe per utilizzata per la gestione degli Articoli
 */
public class ArticleController {

    private ArticoloDAO art_dao;
    private UtenteDAO user_dao;
    private ConferenzeDAO conf_dao;

    public ArticleController () throws SQLException {
        this.art_dao= new ArticoloDAO();
        this.user_dao= new UtenteDAO();
        this.conf_dao= new ConferenzeDAO();
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
        ArrayList<Autore> authors_list = new ArrayList<>();
        Autore user= null;
        ID id = ID.generate();
        try {

            if(this.conf_dao.getConferenzaByID(id_conf) != null){
            for(RUserDTO f_user : a_autori){
                if(user_dao.isUtentePresenteByEmail(f_user.getEmail()) && user_dao.getUtenteByEmail(f_user.getEmail()).getRuolo() == "autore" ){
                    user = (Autore) user_dao.getUtenteByEmail(f_user.getEmail());
                    authors_list.add(user);
                }else{return false;}
            }
                    Articolo art = new Articolo(id, a_abstrct, authors_list, a_titolo);
                    art_dao.salvaArticolo(art);
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
        ArrayList<Articolo> articoli = art_dao.getArticoliByAutore(authorID);
        ArrayList<ShowArticleDTO> f_art = new ArrayList<>();
        for(Articolo c : articoli){
            ShowArticleDTO a = new ShowArticleDTO(c);
            f_art.add(a);
        }
        return f_art;
    }
}
