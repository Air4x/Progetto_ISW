package controller;

import controller.ArticleController;
import entity.Author;
import utility.ID;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.Assert;

public class ArticleControllerTest {

    @Test
    public void submitArticle(String a_titolo, String a_abstrct,  ArrayList<Author> a_autori, ID id_conf) throws SQLException{
        ArticleController art_contr = new ArticleController();
        assertFalse(art_contr.submitArticle(a_titolo, a_abstrct, a_autori, id_conf));
    }

    @Test
    public void getArticleByAuthor(ID authorID) throws SQLException{

    }

}
