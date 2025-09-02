package org.groupone.boundary;

import org.groupone.DTO.RUserDTO;
import org.groupone.DTO.ShowActiveConferenceDTO;
import org.groupone.DTO.ShowArticleDTO;
import org.groupone.controller.ArticleController;
import org.groupone.controller.ConferenceController;
import org.groupone.utility.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AuthorDashboard extends JFrame {
    private JPanel contentPane;
    private JScrollPane scrollActiveConference;
    private JScrollPane scrollSubmittedArticles;
    private JList<ShowArticleDTO> listarticleSubmitted;
    private JList<ShowActiveConferenceDTO> listactiveConference;
    private JLabel lblwelcome;
    private JLabel lblarticlesubmitted;
    private JLabel lblactiveconference;


    public AuthorDashboard(RUserDTO userDTO) throws SQLException {

        ConferenceController cc = new ConferenceController();
        ArticleController ac = new ArticleController();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 600);
        setTitle("Dashboard Autore");





        contentPane.setBounds(5,5,5,5);
        contentPane.setLayout(null);
        setContentPane(contentPane);
        lblwelcome.setText("Welcome," +  userDTO.getName()+" "+userDTO.getLastname() );
        lblwelcome.setFont(new Font("Arial", Font.PLAIN, 20));
        lblwelcome.setBounds(10, 11, 414, 40);
        contentPane.add(lblwelcome);

        lblactiveconference.setText("Active Conference");
        lblactiveconference.setFont(new Font("Arial", Font.PLAIN, 13));
        lblactiveconference.setBounds(10, 35, 414, 40);
        contentPane.add(lblactiveconference);

        ShowActiveConferenceDTO[] activeconference = cc.getActiveConferences().toArray(new ShowActiveConferenceDTO[0]);
        DefaultListModel<ShowActiveConferenceDTO> model = new DefaultListModel<>();
        for(ShowActiveConferenceDTO showActiveConference : activeconference){
            model.addElement(showActiveConference);
        }
        listactiveConference.setModel(model);

        scrollActiveConference.setViewportView(listactiveConference);
        scrollActiveConference.setBounds(5,65,400,200);
        listactiveConference.addMouseListener( new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                try {
                    int indice =  listactiveConference.locationToIndex(e.getPoint());
                    ShowActiveConferenceDTO selected = model.getElementAt(indice);
                    SubmitArticleForm submitArticleForm = new SubmitArticleForm(userDTO, selected.getId());
                    submitArticleForm.setVisible(true);
                }
                catch (SQLException ex) {
                    Logger.getLogger(AuthorDashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        contentPane.add(scrollActiveConference);



        lblarticlesubmitted.setText("Article Submitted");
        lblarticlesubmitted.setFont(new Font("Arial", Font.PLAIN, 13));
        lblarticlesubmitted.setBounds(10, 255, 414, 40);
        contentPane.add(lblarticlesubmitted);

        ShowArticleDTO[] articlesubbmitted = ac.getArticleByAuthor(userDTO.getId()).toArray(new ShowArticleDTO[0]);
        DefaultListModel<ShowArticleDTO> articlemodel = new DefaultListModel<>();
        for(ShowArticleDTO showArticle : articlesubbmitted){
            articlemodel.addElement(showArticle);
        }
        listarticleSubmitted.setModel(articlemodel);
        scrollSubmittedArticles.setViewportView(listarticleSubmitted);
        scrollSubmittedArticles.setBounds(5,285,400,200);
        contentPane.add(scrollSubmittedArticles);




    }










    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                    String name = new String("Name");
                    String lastName = new String("LastName");
                    String email = new String("Email");
                    String affilation = new String("Affilation");
                    String role =  new String("Role");
                    ID id = ID.generate();


                    RUserDTO author = new RUserDTO(name,lastName,email, affilation,role,id);

                    AuthorDashboard frame=new AuthorDashboard(author);
                    frame.setVisible(true);

                }catch(Exception e){
                    e.printStackTrace();

                }


            }
        });
    }

}
