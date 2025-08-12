package boundary;

import DTO.RUserDTO;
import DTO.ShowActiveConferenceDTO;
import DTO.ShowArticleDTO;
import controller.ConferenceController;
import utility.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;


public class OrganizerDashboard extends JFrame{
    private JPanel contentPane;
    private JScrollPane scrollConferenceList;
    private JButton buttonCreateConference;
    private JList listActiveConference;
    private JScrollPane articleSubmitted;

    //Composizione frame
    public OrganizerDashboard(RUserDTO organizer) throws SQLException {
        try {

            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setTitle("Organizer Dashboard");
            setBounds(100, 100, 450, 300);
            JPanel contentPane = new JPanel();
            contentPane.setBounds(5, 5, 5, 5);
            setContentPane(contentPane);
            setLocationRelativeTo(null);

            contentPane.setLayout(null);

            //Parte gestione delle Lista conferenze attive
            ConferenceController cc = new ConferenceController();

            JScrollPane scrollConferenceList = new JScrollPane();
            scrollConferenceList.getViewport().add(contentPane);
            scrollConferenceList.getViewport().setBackground(Color.LIGHT_GRAY);
            scrollConferenceList.setBounds(0, 0, 0, 0);
            JList listActiveConference = new JList(cc.getActiveConferences().toArray(new ShowActiveConferenceDTO[0]));
            listActiveConference.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            for (int i = 0; i < listActiveConference.getModel().getSize(); i++) {
                JLabel label = new JLabel(listActiveConference.getModel().getElementAt(i).toString());
                label.setHorizontalAlignment(JLabel.CENTER);
                scrollConferenceList.getViewport().add(label);

            }
            listActiveConference.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    int index = listActiveConference.locationToIndex(e.getPoint());
                    ShowActiveConferenceDTO selected = (ShowActiveConferenceDTO) listActiveConference.getSelectedValue();
                    JList articleSubmitted = (JList) cc.getArticlesByConference(selected.getId());
                    JScrollPane listarticleSubmitted = (JScrollPane) articleSubmitted.getSelectedValue();
                    articleSubmitted.addMouseListener(new MouseAdapter() {
                        public void mouseClicked(MouseEvent e) {
                            int indexarticle = articleSubmitted.locationToIndex(e.getPoint());
                            ShowArticleDTO selected = (ShowArticleDTO) articleSubmitted.getSelectedValue();

                        }
                    });


                }
            });
            contentPane.add(scrollConferenceList);


            JButton buttonCreateConference = new JButton("New Conference");
            buttonCreateConference.setBounds(50, 50, 200, 50);
            buttonCreateConference.setBackground(new Color(100, 149, 237));
            buttonCreateConference.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
            buttonCreateConference.setForeground(Color.white);
            buttonCreateConference.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    //Crea la conferenza la conferenza
                    CreateConferenceForm form = new CreateConferenceForm(organizer);
                    form.setVisible(true);

                }
            });


            contentPane.add(buttonCreateConference);
        }catch (SQLException e){
            e.printStackTrace();
        }








    }

    //Come viene avviato il programma
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RUserDTO organizer = new RUserDTO("Name","Lastname","email@email.com","Affilation","Organizer",true, ID.generate());
                    OrganizerDashboard finestra = new OrganizerDashboard(organizer);
                    finestra.setVisible(true);



                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
    }
}
