package boundary;

import DTO.RUserDTO;
import DTO.ShowActiveConferenceDTO;
import DTO.ShowArticleDTO;
import controller.ArticleController;
import controller.ConferenceController;
import utility.ID;

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
    private JButton buttonSubmitArticle;
    private JScrollPane scrollSubmittedArticles;


    public AuthorDashboard(RUserDTO userDTO) throws SQLException {

        ConferenceController cc = new ConferenceController();
        ArticleController ac = new ArticleController();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle("Autore Dashboard");



        JPanel contentPane = new JPanel();
        contentPane.setBounds(5,5,5,5);
        setContentPane(contentPane);

        JList<ShowActiveConferenceDTO> listActiveConference = new JList<>((ListModel) cc.getActiveConferences());

        JScrollPane scrollActiveConference = new JScrollPane(listActiveConference);
        scrollActiveConference.setBounds(5,5,50,200);
        listActiveConference.addMouseListener( new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                try {
                    ShowActiveConferenceDTO selected = (ShowActiveConferenceDTO) listActiveConference.getSelectedValue();
                    SubmitArticleForm submitArticleForm = new SubmitArticleForm(userDTO, selected.getId());
                    submitArticleForm.setVisible(true);
                }
                catch (SQLException ex) {
                    Logger.getLogger(AuthorDashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        contentPane.add(scrollActiveConference);



        JList<ShowArticleDTO> listSubmittedArticles = new JList<>((ListModel<ShowArticleDTO>) ac.getArticleByAuthor(userDTO.getId()));
        JScrollPane scrollSubmittedArticles = new JScrollPane(listSubmittedArticles);
        scrollSubmittedArticles.setBounds(5,215,50,200);
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
