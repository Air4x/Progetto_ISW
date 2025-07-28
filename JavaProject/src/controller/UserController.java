package controller;
import database.UserDAO;
import entity.User;
import entity.Author;
import entity.Organizer;
import utility.ID;
import DTO.RUserDTO;
import java.sql.SQLException;

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
        // true = Utente già presente
        // false = Utente non trovato
        User utente = null ;
        ID id = ID.generate();
        if(user_dao.isUserPresentByEmail(email)==true){
        RUserDTO fake_user = new RUserDTO(null, null, null, null, null, false, id);
         return fake_user;
        }else{
            if(ruole == "organizzatore"){
                utente = new Organizer (aff,email,lastname,name,password,id);
            } else if (ruole == "autore"){
                utente = new Author (aff,email,lastname,name,password,id);
            }
                RUserDTO fake_user = new RUserDTO(utente, true);
                user_dao.saveUser(utente);
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
    
}