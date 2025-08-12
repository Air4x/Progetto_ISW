package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import database.ConferenzeDAO;
import DTO.ShowActiveConferenceDTO;
import DTO.ShowArticleDTO;
import DTO.RUserDTO;
import database.UtenteDAO;
import entity.Conferenza;
import entity.Organizzatore;
import entity.Utente;
import utility.ID;
import entity.Articolo;

/**
 * @author Giuseppe Buglione
 * Classe utilizzata per la gestione delle Conferenzze
 */
public class ConferenceController {
    
    private Utente utente;
    private UtenteDAO user_dao;
    private Conferenza conf;
    private ConferenzeDAO conf_dao;
    private ShowActiveConferenceDTO dto;
    private ShowArticleDTO dto2;

    public ConferenceController() throws SQLException {
        this.conf_dao = new ConferenzeDAO();
    }
    
    /**
     * Metodo per la creazione di una conferenzza
     * @param scadenza
     * @param title
     * @param descr
     * @param id
     * @param org
     * @throws SQLException
     */
    public void createConference (Date scadenza, String title, String descr, ID id, RUserDTO org) throws SQLException {
        this.conf = new Conferenza(scadenza,title,descr,id);
        this.utente = (Organizzatore) this.user_dao.getUtenteByID(org.getId());;
        conf_dao.salvaConferenza(this.conf);
    }

    /**
     * Metodo per ottenere una lista di conferenzze attive [NON ANCORA SCADUTE]
     * @return
     * @throws SQLException
     */
    public ArrayList<ShowActiveConferenceDTO> getActiveConferences() throws SQLException{
        Date data = new Date();
        ArrayList<Conferenza> all_conf = conf_dao.getTutteConferenze();
        ArrayList<ShowActiveConferenceDTO> actconf = new ArrayList<>();
        
        for(Conferenza conf : all_conf){
            if(conf.getScadenza() != null && !conf.getScadenza().before(data))
            this.dto = new ShowActiveConferenceDTO(conf.getId(),conf.getTitolo(),conf.getScadenza(),conf.getDescrizione());
            actconf.add(dto);
        }
            return actconf;
    }

    /**
     * Metodo per ottenere una lista di articoli
     * @param conf_Id
     * @return
     * @throws SQLException
     */
    public ArrayList<ShowArticleDTO> getArticlesByConference(ID conf_Id) throws SQLException {
        ArrayList<Articolo> art = conf_dao.getArticoliByConferenza(conf_Id);
        ArrayList<ShowArticleDTO> at = new ArrayList<>();
        for (Articolo a : art) {
            this.dto2 = new ShowArticleDTO (a.getId(),a.getTitolo(),a.getAbstr(),a.getAutori());
            at.add(dto2);
        }
        return at;
    }

}