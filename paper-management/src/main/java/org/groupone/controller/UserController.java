package org.groupone.controller;

import java.sql.SQLException;
import java.util.Objects;
import org.groupone.database.UserDAO;
import org.groupone.entity.Author;
import org.groupone.entity.Organizer;
import org.groupone.entity.User;
import org.groupone.utility.ID;
import org.groupone.DTO.RUserDTO;

/**
 * @author Giuseppe Buglione
 * Classe per la gestione degli utenti 
 */
public class UserController {

    private UserDAO user_dao;

    public UserController() {
        this.user_dao = new UserDAO();
    }

    /**
     * Metodo per la registarzione di un untente
     * @param aff
     * @param email
     * @param lastname
     * @param name
     * @param password
     * @param ruole
     * @return
     * @throws SQLException
     */
    public RUserDTO registerUser (String aff, String email, String lastname, String name, String password, String ruole) throws SQLException{
        // true = User già presente
        // false = User non trovato
        User user = null ;
        ID id = ID.generate();
        if(user_dao.isUserPresentByEmail(email)==true){
         return null;
        }else{
            RUserDTO fake_user = null;
            if(ruole == "organizzatore"){
                user = new Organizer(aff,email,lastname,name,password,id);

            } else if (ruole == "autore"){
                user = new Author(aff,email,lastname,name,password,id);
            }
            user_dao.saveUser(user);
            fake_user = new RUserDTO(user,false);
            return fake_user;
        }
    }

    /**
     * Metodo per l'accesso di un utente
     * @param email
     * @param password
     * @return
     * @throws SQLException
     */
    public RUserDTO login (String email, String password) throws SQLException{
        if(user_dao.isUserPresentByEmail(email) == true && user_dao.getUserByEmail(email).getPassword().equals(password)){
            User user = user_dao.getUserByEmail(email);
            RUserDTO fake_user = new RUserDTO (user,false);
            return fake_user;
        }
        return null;
    }

    public RUserDTO getRAuthorBYEmail (String email) throws SQLException{
        if(this.user_dao.isUserPresentByEmail(email)==true && this.user_dao.getUserByEmail(email).getRole()=="autore"){
            RUserDTO fake_user = new RUserDTO(user_dao.getUserByEmail(email), false);
            return fake_user;
        }
        return null;
    }
    
}
