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
    private JPanel contentPane =  new JPanel();
    private JScrollPane scrollConferenceList =  new JScrollPane();
    private JButton buttonCreateConference =   new JButton();
    private JList listActiveConference =  new JList();
    private JList listarticleSubmitted =  new JList();
    private JScrollPane scrollarticleSubmitted =   new JScrollPane();
    private JLabel lblwelcome =   new JLabel();
    private JLabel lblactiveconference =    new JLabel();
    private JLabel lblarticlesubmitted =    new JLabel();

    //Composizione frame
    public OrganizerDashboard(RUserDTO organizer) throws SQLException {
        try {

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Organizer Dashboard");
            setBounds(100, 100, 650, 800);
            contentPane.setBounds(5, 5, 5, 5);
            setContentPane(contentPane);
            setLocationRelativeTo(null);
            setResizable(false);

            contentPane.setLayout(null);

            //Parte gestione delle Lista conferenze attive
            ConferenceController cc = new ConferenceController();

            lblwelcome.setText("Welcome, "+organizer.getName()+" "+organizer.getLastname());
            lblwelcome.setFont(new Font("Arial",Font.PLAIN,20));
            lblwelcome.setBounds(10,10,550,20);
            contentPane.add(lblwelcome);

            lblactiveconference.setText("Active Conference");
            lblactiveconference.setFont(new Font("Arial",Font.PLAIN,13));
            lblactiveconference.setBounds(10,40,550,20);
            contentPane.add(lblactiveconference);

            
            ShowActiveConferenceDTO[] activeConference = cc.getActiveConferences().toArray(new ShowActiveConferenceDTO[0]);
            DefaultListModel<ShowActiveConferenceDTO> activeConferencemodel = new DefaultListModel<>();
            for(ShowActiveConferenceDTO activeConf : activeConference){
                activeConferencemodel.addElement(activeConf);
            }
            listActiveConference.setModel(activeConferencemodel);

            scrollConferenceList.getViewport().setView(listActiveConference);
            scrollConferenceList.setBounds(10, 70, 414, 200);
            listActiveConference.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    try {
                        int indexc = listActiveConference.locationToIndex(e.getPoint());
                        ShowActiveConferenceDTO selectedconference = activeConferencemodel.getElementAt(indexc);
                        ShowArticleDTO[] articlesubmitted = cc.getArticlesByConference(selectedconference.getId()).toArray(new ShowArticleDTO[0]);
                        DefaultListModel<ShowArticleDTO> articlemodel = new DefaultListModel<>();
                        for(ShowArticleDTO article : articlesubmitted){
                            articlemodel.addElement(article);
                        }
                        listarticleSubmitted.setModel(articlemodel);
                        scrollarticleSubmitted.getViewport().setView(listarticleSubmitted);
                        listarticleSubmitted.addMouseListener(new MouseAdapter() {
                            public void mouseClicked(MouseEvent e) {
                                int indexa = listarticleSubmitted.locationToIndex(e.getPoint());
                                ShowArticleDTO selectedarticle = articlemodel.getElementAt(indexa);
                                AssignReviewersView frame  = new AssignReviewersView(selectedarticle);
                                frame.setVisible(true);

                            }
                        });

                    }catch (SQLException ex){
                        ex.printStackTrace();
                    }
                }
            });
            scrollarticleSubmitted.setBounds(10, 290, 414, 200);
            contentPane.add(scrollarticleSubmitted);
            lblarticlesubmitted.setText("Article Submitted");
            lblarticlesubmitted.setFont(new Font("Arial",Font.PLAIN,13));
            lblarticlesubmitted.setBounds(10,270,550,20);
            contentPane.add(lblarticlesubmitted);
            contentPane.add(scrollConferenceList);



            buttonCreateConference.setText("Create Conference");
            buttonCreateConference.setBounds(50, 550, 200, 50);
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
