package boundary;

import DTO.RUserDTO;
import DTO.ShowActiveConferenceDTO;
import DTO.ShowArticleDTO;
import controller.ArticleController;
import controller.ConferenceController;
import entity.Author;
import utility.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class AuthorDashboard extends JFrame {
    private JPanel contentPane;
    private JScrollPane scrollActiveConference;
    private JButton buttonSubmitArticle;
    private JScrollPane scrollSubmittedArticles;


    public AuthorDashboard(RUserDTO userDTO) throws SQLException {
        ConferenceController cc = new ConferenceController();
        ArticleController ac = new ArticleController();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle("Author Dashboard");



        JPanel contentPane = new JPanel();
        contentPane.setBounds(5,5,5,5);
        setContentPane(contentPane);

        List listActiveConference<ShowActiveConferenceDTO>= cc.getActiveConferences();

        JScrollPane scrollActiveConference = new JScrollPane(listActiveConference);
        scrollActiveConference.setBounds(5,5,50,200);
        listActiveConference.addActionListener( new MouseListener() {
            public void mouseClicked(MouseEvent e){
                SubmitArticleForm submitArticleForm = new SubmitArticleForm(userDTO,ShowActiveConferenceDTO.getId());
                submitArticleForm.setVisible(true);
            }
        });
        contentPane.add(scrollActiveConference);



        List listSubmittedArticles<ShowArticleDTO> = ac.getArticleByAuthor(userDTO.getId());
        JScrollPane scrollSubmittedArticles = new JScrollPane(listSubmittedArticles);
        scrollSubmittedArticles.setBounds(5,215,50,200);
        contentPane.add(scrollSubmittedArticles);

        /*
        JButton buttonSubmitArticle = new JButton("Submit Article");
        buttonSubmitArticle.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
        buttonSubmitArticle.setBackground(new Color(100,149,237));
        buttonSubmitArticle.setBounds(50,50,200,50);
        buttonSubmitArticle.setForeground(Color.white);
        buttonSubmitArticle.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                SubmitArticleForm submitArticleForm = new SubmitArticleForm();
                submitArticleForm.setVisible(true);

            }
        });
        contentPane.add(buttonSubmitArticle);

         */


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
                    ID id = new ID();
                    boolean esito = true;

                    RUserDTO author = new RUserDTO(name,lastName,email, affilation,role,esito,id);

                    AuthorDashboard frame=new AuthorDashboard(author);
                    frame.setVisible(true);

                }catch(Exception e){
                    e.printStackTrace();

                }


            }
        });
    }

}
