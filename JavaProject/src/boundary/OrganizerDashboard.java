package boundary;

import controller.ConferenceController;
import entity.Conference;
import entity.Organizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

//MOLTO IN WIP

public class OrganizerDashboard extends JFrame{
    private JPanel contentPane;
    private JScrollPane scrollConferenceList;
    private JButton buttonCreateConference;
    private JList listActiveConference;

    //Composizione frame
    public OrganizerDashboard(Organizer organizer) throws SQLException {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Organizer Dashboard");
        setBounds(100,100,450,300);
        contentPane = new JPanel();
        contentPane.setBounds(5,5,5,5);
        setContentPane(contentPane);
        setLocationRelativeTo(null);

        contentPane.setLayout(null);

        //Parte gestione delle Lista conferenze attive

        JScrollPane scrollConferenceList = new JScrollPane();
        scrollConferenceList.getViewport().add(contentPane);
        scrollConferenceList.getViewport().setBackground(Color.LIGHT_GRAY);
        scrollConferenceList.setBounds(0,0,0,0);
        //JList listActiveConference = new JList(ConferenceController.getActiveConferences().toArray(new Conference[0]));
        listActiveConference.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        for (int i = 0; i<listActiveConference.getModel().getSize();i++){
            JLabel label = new JLabel(listActiveConference.getModel().getElementAt(i).toString());
            label.setHorizontalAlignment(JLabel.CENTER);
            scrollConferenceList.getViewport().add(label);
        }
        //int idConference = ConferenceController.getActiveConferences().size();
        contentPane.add(scrollConferenceList);



        JButton buttonCreateConference = new JButton("New Conference");
        buttonCreateConference.setBounds(50,50,200,50);
        buttonCreateConference.setBackground(new Color(100,149,237));
        buttonCreateConference.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
        buttonCreateConference.setForeground(Color.white);
        buttonCreateConference.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                idConference = idConference+1;
                //Crea la conferenza la conferenza
                CreateConferenceForm form = new CreateConferenceForm(organizer, idConference);
                form.setVisible(true);

            }
        });


        contentPane.add(buttonCreateConference);








    }

    //Come viene avviato il programma
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    String affiliazione = new String("Affiliazione");
                    String email= new String("Email");
                    String password =  new String("Password");
                    String nome = new  String("Nome");
                    String lastName = new String("Cognome");
                    int id= 0;
                    Organizer organizer = new Organizer(affiliazione,email,password,nome,lastName,id);
                    OrganizerDashboard finestra = new OrganizerDashboard(organizer);
                    finestra.setVisible(true);



                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
    }
}
