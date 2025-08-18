package controller;

import database.ConferenceDAO;
import database.UserDAO;
import entity.Author;
import entity.Conference;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import utility.PasswordManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * @author Giuseppe Buglione
 * Classe utilizzata per la gestione della creazione e invio di email
*/
public class NotificationController {
    
    private ConferenceDAO conf_dao;
    private UserDAO user_dao;

    /**
     * Costruttore
     * @throws SQLException
     */
    public NotificationController() throws SQLException{
        this.conf_dao= new ConferenceDAO();
        this.user_dao= new UserDAO();
    }

    /**
     * Metodo utilizzato per l'invio di email
     * @throws SQLException
     * @throws MessagingException
     */
    public void invioNotifiche() throws SQLException, MessagingException {
        ArrayList<Conference> conf = conf_dao.getActiveConference();
        ArrayList<Author> auth = user_dao.getAllAuthors();
        String msg = " ",name_a,lastname_a,email_a,title_c;
        for (Conference c : conf) {
            if (c.nearDeadline()) {
                title_c = c.getTitle();
                for(Author a : auth){
                    name_a =  a.getName();
                    lastname_a =  a.getLastName();
                    email_a =  a.getEmail();
                    creatMessage(name_a,lastname_a,title_c,msg);
                    sendEmail(email_a,title_c,msg);
                }
            }
            
        }
    }

    /**
     * Metodo per la creazione del messaggio da inviare
     * @param aut_name: Nome del destinatario
     * @param aut_lastname: Cognome del destinatario
     * @param conf_title: Nome della conferenza in scadenza
     * @param msg: Messaggio da scrivere
     */
    private void creatMessage(String aut_name, String aut_lastname, String conf_title, String msg){
        msg = "Saluti "+aut_name+" "+aut_lastname+"\nLe Recordiamo che la consegna per "+conf_title+" sta per scadere";
    }

    /**
     * Metodo utilizzo per la configurazione di un host per l'invio delle email
     * @param email_d
     * @param conf_title
     * @param msg
     * @throws SQLException
     * @throws MessagingException
     */
    private void sendEmail (String email_d, String conf_title, String msg) throws SQLException, MessagingException {
        // Fare email da cui mandare messaggi
        final String m_email = PasswordManager.getInstance().get("email_username"); //mittente
        final String m_password = PasswordManager.getInstance().get("email_password");; //app_password

        //Configurazione del host
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.trust", "sandbox.smtp.mailtrap.io");
        prop.put("mail.smtp.ssl.trust", "*");
        prop.put("mail.smtp.ssl.checkserveridentity", false);

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
        message.setSubject("Scadenza Consegne Articoli per"+conf_title);

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
