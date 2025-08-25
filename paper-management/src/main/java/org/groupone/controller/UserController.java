package org.groupone.controller;

import java.sql.SQLException;

import org.groupone.DTO.RUserDTO;
import org.groupone.database.UserDAO;
import org.groupone.entity.Author;
import org.groupone.entity.Organizer;
import org.groupone.entity.User;
import org.groupone.utility.ID;

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
     * @param affiliazione
     * @param email
     * @param lastname
     * @param name
     * @param password
     * @param ruole
     * @return Un oggetto RUser che rappresenta l'unbtente appena registrato
     * @throws SQLException
     */
    public RUserDTO registerUser (String affiliazione, String email, String lastname, String name, String password, String ruole) throws SQLException{
        // true = User già presente
        // false = User non trovato
        User user = null ;
        ID id = ID.generate();
        if(user_dao.isUserPresentByEmail(email)==true){
            System.out.println("User già presente");
            return null;
        }else{
            RUserDTO fake_user = null;
            if(ruole == "organizzatore"){
                user = new Organizer(affiliazione,email,lastname,name,password,id);

            } else if (ruole == "autore"){
                user = new Author(affiliazione,email,lastname,name,password,id);
            }
            user_dao.saveUser(user);
            fake_user = new RUserDTO(user);
            System.out.println("User registrato con successo");
            return fake_user;
        }
    }

    /**
     * Metodo per l'accesso di un utente
     * @param email
     * @param password
     * @return Un oggetto RUserDTO che rappresenta l'uttente che ha appena fatto il login
     * @throws SQLException
     */
    public RUserDTO login (String email, String password) throws SQLException{
        if(user_dao.isUserPresentByEmail(email) == true && user_dao.getUserByEmail(email).getPassword().equals(password)){
            User user = user_dao.getUserByEmail(email);
            RUserDTO fake_user = new RUserDTO (user);
            return fake_user;
        }
        System.out.println("Email o password errati");
        return null;
    }

    public RUserDTO getRAuthorBYEmail (String email) throws SQLException{
        if(this.user_dao.isUserPresentByEmail(email)==true && this.user_dao.getUserByEmail(email).getRole()=="autore"){
            RUserDTO fake_user = new RUserDTO(user_dao.getUserByEmail(email));
            return fake_user;
        }
        return null;
    }
    
}
