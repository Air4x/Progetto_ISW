package org.groupone.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.TimerTask;

import org.groupone.database.ConferenceDAO;
import org.groupone.database.UserDAO;
import org.groupone.entity.Author;
import org.groupone.entity.Conference;
import org.groupone.utility.PasswordManager;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.SendFailedException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

/**
 * @author Giuseppe Buglione
 * Classe che estende java.util.TimerTask Classe che implementa la logica di gestione della creazione e invio di email 
*/
public class NotificationController extends TimerTask {
    
    private ConferenceDAO conf_dao;
    private UserDAO user_dao;

    /**
     * Costruttore della classe
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
     * Override del metodo run della classe TimerTask per esecuzione ad intervalli regolari per l'invio delle notifiche
     */
    @Override
    public void run() {
            sendNotificationDeadline();
    }

    /**
     * Metodo utilizzato per l'invio di email
     * @throws SQLException
     * @throws MessagingException
     */
    public void sendNotificationDeadline(){
        try {  
            ArrayList<Conference> conf = conf_dao.getActiveConference();
            ArrayList<Author> auth = user_dao.getAllAuthors();
            ArrayList<String> emails = new ArrayList<String>();
            for (Conference c : conf) {
            if (c.nearDeadline()) {
                System.out.println("Conferenza in scadenza: " + c.getTitle());
                emails.add(c.getTitle());
            }}
            if(!emails.isEmpty()){
            for(Author a : auth){
                sendEmail(a.getEmail(), createMessageExpireConference(a.getName(), a.getLastName(), emails),"Scadenza Consegne Articoli ");
            }}

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
     * @param conf_title: Lista dei nome della conferenza in scadenza
     * @return Stringa che rapresenta il messagio da inviare
     */
    private String createMessageExpireConference(String aut_name, String aut_lastname, ArrayList<String> conf_title){
        StringBuilder sb = new StringBuilder();
        sb.append("Saluti ").append(aut_name).append(" ").append(aut_lastname).append(".<br><br>");
        sb.append("Le ricordiamo che le seguenti conferenze risultano in scadenza:<br>");

        if (!conf_title.isEmpty()) {
            sb.append("<ul>");
            for (String title : conf_title) {
                sb.append("<li>").append(title).append("</li>");
            }
            sb.append("</ul>");
        } else {
            sb.append("<br>Nessuna conferenza in scadenza nei prossimi giorni.<br>");
        }

        sb.append("La preghiamo di effettuare la consegna degli articoli al più presto.<br>");
        sb.append("Saluti e Buona giornata");
        return sb.toString();
    }

    /**
     * Metodo utilizzo per la configurazione di un host per l'invio delle email
     * @param email_d: Email del destinatario
     * @param msg: Messaggio da inviare
     * @throws SQLException
     * @throws MessagingException
     */
    private void sendEmail (String email_d, String msg, String subject) throws SQLException, MessagingException, SendFailedException {
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
        message.setSubject(subject);

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
