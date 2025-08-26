package org.groupone.boundary;

import org.groupone.DTO.RUserDTO;
import org.groupone.DTO.ShowActiveConferenceDTO;
import org.groupone.DTO.ShowArticleDTO;
import org.groupone.controller.ConferenceController;
import org.groupone.utility.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;


public class OrganizerDashboard extends JFrame{
    private JPanel contentPane;
    private JScrollPane scrollConferenceList;
    private JButton buttonCreateConference;
    private JScrollPane scrollActiveConference;
    private JList listActiveConference;
    private JList listarticleSubmitted;
    private JScrollPane scrollarticleSubmitted;

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
                    try {
                        int index = listActiveConference.locationToIndex(e.getPoint());
                        ShowActiveConferenceDTO selected = (ShowActiveConferenceDTO) listActiveConference.getSelectedValue();
                        JList listarticleSubmitted =new JList ((ListModel) cc.getArticlesByConference(selected.getId()));
                        JScrollPane scrollArticleSubmitted = new JScrollPane(listarticleSubmitted);
                        listarticleSubmitted.addMouseListener(new MouseAdapter() {
                            public void mouseClicked(MouseEvent e) {
                                int indexarticle = listarticleSubmitted.locationToIndex(e.getPoint());
                                ShowArticleDTO selected = (ShowArticleDTO) listarticleSubmitted.getSelectedValue();


                            }
                        });
                    }catch (SQLException ex){
                        ex.printStackTrace();


                    }



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
                    RUserDTO organizer = new RUserDTO("Name","Lastname","email@email.com","Affilation","Organizer",ID.generate());
                    OrganizerDashboard finestra = new OrganizerDashboard(organizer);
                    finestra.setVisible(true);



                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        });
    }
}
