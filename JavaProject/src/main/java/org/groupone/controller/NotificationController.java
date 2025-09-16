package org.groupone.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TimerTask;

import jakarta.mail.*;
import org.groupone.database.ConferenceDAO;
import org.groupone.database.UserDAO;
import org.groupone.entity.Author;
import org.groupone.entity.Conference;
import org.groupone.utility.PasswordManager;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

/**
 * @author Giuseppe Buglione
 * Classe che estende java.util.TimerTask utilizzata per la gestione della creazione e invio di email 
*/
public class NotificationController extends TimerTask {
    
    private ConferenceDAO conf_dao;
    private UserDAO user_dao;

    /**
     * Costruttore
     * @throws SQLException
     */
    public NotificationController(){
        try {
            this.conf_dao= new ConferenceDAO();
            this.user_dao= new UserDAO();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'inizializzazione del NotificationController");
        }
    }

    /**
     * Metodo eseguito ad intervalli regolari per l'invio delle notifiche
     */
    @Override
    public void run() {
            invioNotifiche();
    }

    /**
     * Metodo utilizzato per l'invio di email
     * @throws SQLException
     * @throws MessagingException
     */
    public void invioNotifiche(){
        String msg,name_a,lastname_a,email_a,title_c;
        try {  
            ArrayList<Conference> conf = conf_dao.getActiveConference();
            ArrayList<Author> auth = user_dao.getAllAuthors();
            for (Conference c : conf) {
            if (c.nearDeadline()) {
                title_c = c.getTitle();
                for(Author a : auth){
                    name_a =  a.getName();
                    lastname_a =  a.getLastName();
                    email_a =  a.getEmail();
                    msg = createMessage(name_a,lastname_a,title_c);
                    sendEmail(email_a,title_c,msg);
                }
            }
        }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Errore nell'invio delle email");
        } catch(SendFailedException e){
            System.out.println("Errore nell'invio delle email");
        }catch( MessagingException e ){
            e.printStackTrace();
            System.out.println("Errore nella configurazione dell'host per l'invio delle email");
        }
        
    }
    /**
    * Metodo per la creazione del messaggio da inviare
     * @param aut_name: Nome del destinatario
     * @param aut_lastname: Cognome del destinatario
     * @param conf_title: Nome della conferenza in scadenza
     * @return Stringa che rapresenta il messagio da inviare
     */
    private String createMessage(String aut_name, String aut_lastname, String conf_title){
        return "Saluti "+aut_name+" "+aut_lastname+".\n\nLe Recordiamo che la consegna degli articoli per la conferenza:"+conf_title+" sta per scadere.\nLa preghiamo di effetuare la consegna degli articoli al più presto. Saluti e Buona giornata";
    }

    /**
     * Metodo utilizzo per la configurazione di un host per l'invio delle email
     * @param email_d
     * @param conf_title
     * @param msg
     * @throws SQLException
     * @throws MessagingException
     */
    private void sendEmail (String email_d, String conf_title, String msg) throws SQLException, MessagingException, SendFailedException {
        // Fare email da cui mandare messaggi
        final String m_email = PasswordManager.getInstance().get("email_username"); //mittente
        final String m_password = PasswordManager.getInstance().get("email_password");; //app_password

        //Configurazione del host
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(m_email, m_password);
            }
        });


        // Creazione di un istanza oggetto messagio con proprieta to, from
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(m_email));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email_d));
        message.setSubject("Scadenza Consegne Articoli per "+conf_title);

        //Codifica testo html
        MimeBodyPart mbp =  new MimeBodyPart();
        mbp.setContent(msg, "text/html; charset=utf-8");

        Multipart mp = new MimeMultipart();
        mp.addBodyPart(mbp);

        message.setContent(mp);
        Transport.send(message);

        System.out.println("Email inviata a "+email_d);
    }
}
