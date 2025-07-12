package controller;
import database.UserDAO;

import java.sql.SQLException;
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

    public User login (String email, String password) throws SQLException{
        if(user_dao.isUserPresentByEmail(email) == true && user_dao.getUserByEmail(email).getPassword() == password){
            return user_dao.getUserByEmail(email);
        }
        return null;
    }
    
}