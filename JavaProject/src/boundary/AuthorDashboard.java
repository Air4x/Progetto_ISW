package boundary;

import entity.Author;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AuthorDashboard extends JFrame {
    private JPanel contentPane;
    private JScrollPane scrollActiveConference;
    private JList listActiveConference;
    private JButton buttonSubmitArticle;
    private JScrollPane scrollSubmittedArticles;
    private JList listSubmittedArticles;

    public AuthorDashboard(Author author) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle("Author Dashboard");


        contentPane = new JPanel();
        contentPane.setBounds(5,5,5,5);
        setContentPane(contentPane);

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


    }










    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                    AuthorDashboard frame=new AuthorDashboard();
                    frame.setVisible(true);

                }catch(Exception e){
                    e.printStackTrace();

                }


            }
        });
    }

}
