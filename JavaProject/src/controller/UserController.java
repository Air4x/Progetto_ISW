package controller;
import database.UserDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import entity.*;

public class UserController {

    private UserDAO user_dao;

    public boolean registerUser (User utente) throws SQLException{
        // true = Utente già presente
        // false = Utente non trovato
        if(user_dao.isUserPresentByEmail(utente.getEmail()) == true){
            return true;
        } else {
            user_dao.saveUser(utente);
        }
        return false;
    }

    public boolean login (String email, String password) throws SQLException{
        User accesso;
        if(user_dao.isUserPresentByEmail(email) == true){
            accesso = user_dao.getUserByEmail(email);
            if(accesso.getPassword() == password){
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
    
}