package controller;

import entity.Conference;
import entity.Articolo;
import database.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleController {

    private ArticleDAO art_dao;
    private ConferenceDAO conf_dao;

    public ArticleController () throws SQLException{
        this.art_dao= new ArticleDAO();
        this.conf_dao= new ConferenceDAO();
    }

    public boolean submitArticle(Articolo art, Conference conf) throws SQLException{
        String ID_conf = conf.getId();
        //if(this.conf_dao.getArticlesByConference(ID_conf) != null){art_dao.saveArticle(art); return true;}
        return false;
    }

    public List<Articolo> getArticleByAuthor(String authorID) throws SQLException{
        List<Articolo> articoli = art_dao.getArticlesByAuthor(authorID);
        return articoli;
    }

    public boolean updateArticleStatus (String ID, String status) throws SQLException {
        if()
        this.art_dao.updateArticleStatus(ID, status);
    }

}
