package controller;

import java.sql.SQLException;

import database.UtenteDAO;
import entity.Autore;
import entity.Utente;
import entity.Organizzatore;
import utility.ID;
import DTO.RUserDTO;

/**
 * @author Giuseppe Buglione
 * Classe per la gestione degli utenti 
 */
public class UserController {

    private UtenteDAO user_dao;

    public UserController() {
        this.user_dao = new UtenteDAO();
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
        Utente utente = null ;
        ID id = ID.generate();
        if(user_dao.isUtentePresenteByEmail(email)==true){
        RUserDTO fake_user = new RUserDTO(null, null, null, null, null, false, id);
         return fake_user;
        }else{
            if(ruole == "organizzatore"){
                utente = new Organizzatore(aff,email,lastname,name,password,id);
            } else if (ruole == "autore"){
                utente = new Autore(aff,email,lastname,name,password,id);
            }
                RUserDTO fake_user = new RUserDTO(utente, true);
                user_dao.salvaUtente(utente);
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
        if(user_dao.isUtentePresenteByEmail(email) == true && user_dao.getUtenteByEmail(email).getPassword() == password){
            RUserDTO fake_user = new RUserDTO (user_dao.getUtenteByEmail(email),true);
            return fake_user;
        }
        RUserDTO fake_user = new RUserDTO (null,true);
        return fake_user;
    }

    public RUserDTO getRAuthorBYEmail (String Email) throws SQLException{
        if(this.user_dao.isUtentePresenteByEmail(Email)==true && this.user_dao.getUtenteByEmail(Email).getRuolo()=="autore"){
            Utente utente = (Autore) user_dao.getUtenteByEmail(Email);
            RUserDTO user_dto = new RUserDTO(utente, true);
            return user_dto;
        }

        return null;
    }
    
}