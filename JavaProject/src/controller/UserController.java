package controller;

import java.sql.SQLException;

import database.UserDAO;
import entity.Autore;
import entity.Organizer;
import entity.User;
import utility.ID;
import DTO.RUserDTO;

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
        RUserDTO fake_user = new RUserDTO(null, null, null, null, null, false, id);
         return fake_user;
        }else{
            if(ruole == "organizzatore"){
                user = new Organizer(aff,email,lastname,name,password,id);
            } else if (ruole == "autore"){
                user = new Autore(aff,email,lastname,name,password,id);
            }
                RUserDTO fake_user = new RUserDTO(user, true);
                user_dao.saveUser(user);
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
        if(user_dao.isUserPresentByEmail(email) == true && user_dao.getUserByEmail(email).getPassword() == password){
            RUserDTO fake_user = new RUserDTO (user_dao.getUserByEmail(email),true);
            return fake_user;
        }
        RUserDTO fake_user = new RUserDTO (null,true);
        return fake_user;
    }

    public RUserDTO getRAuthorBYEmail (String Email) throws SQLException{
        if(this.user_dao.isUserPresentByEmail(Email)==true && this.user_dao.getUserByEmail(Email).getRole()=="autore"){
            User user = (Autore) user_dao.getUserByEmail(Email);
            RUserDTO user_dto = new RUserDTO(user, true);
            return user_dto;
        }

        return null;
    }
    
}