package boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

    //MOLTO IN WIP

public class OrganizerDashboard extends JFrame{
    private JPanel contentPane;
    private JScrollPane scrollConferenceList;
    private JButton buttonCreateConference;
    private JList listActiveConference;

    //Composizione frame
    public OrganizerDashboard() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Organizer Dashboard");
        setBounds(100,100,450,300);
        contentPane = new JPanel();
        contentPane.setBounds(5,5,5,5);
        setContentPane(contentPane);
        setLocationRelativeTo(null);

        contentPane.setLayout(null);
        /*
        Parte gestione delle Lista conferenze attive

        JScrollPane scrollConferenceList = new JScrollPane();
        scrollConferenceList.getViewport().add(contentPane);
        scrollConferenceList.getViewport().setBackground(Color.LIGHT_GRAY);
        scrollConferenceList.setBounds(0,0,0,0);
        contentPane.add(scrollConferenceList);

        */

        JButton buttonCreateConference = new JButton("New Conference");
        buttonCreateConference.setBounds(50,50,200,50);
        buttonCreateConference.setBackground(new Color(100,149,237));
        buttonCreateConference.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
        buttonCreateConference.setForeground(Color.white);
        buttonCreateConference.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //Crea la conferenza la conferenza
                CreateConferenceForm form = new CreateConferenceForm();
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
                    OrganizerDashboard finestra = new OrganizerDashboard();
                    finestra.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
    }
}
