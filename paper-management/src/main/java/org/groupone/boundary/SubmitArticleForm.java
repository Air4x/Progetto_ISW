package org.groupone.boundary;

import org.groupone.DTO.RUserDTO;
import org.groupone.controller.ArticleController;
import org.groupone.controller.UserController;
import org.groupone.utility.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class SubmitArticleForm extends JFrame{
    private JLabel lbltitle;
    private JTextField txttitle;
    private JLabel lblabstract;
    private JTextArea txtareaabstract;
    private JLabel lblcoauthors;
    private JTextField txtcoauthors;
    private JLabel lblconference;
    private JTextField txtconference;
    private JPanel contentPane;
    private JButton buttonSubmit;


    public SubmitArticleForm(RUserDTO userDTO, ID conferenceID) throws SQLException{
        setTitle("Submit Article Form");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 290, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel();
        contentPane.setBounds(5,5,5,5);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lbltitle = new JLabel("Title");
        lbltitle.setFont(new Font("Arial",Font.PLAIN,20));
        lbltitle.setBounds(10,10,100,20);
        contentPane.add(lbltitle);

        JTextField txttitle = new JTextField();
        txttitle.setBounds(10,40,100,20);
        contentPane.add(txttitle);

        JLabel lblabstract = new JLabel("Abstract");
        lblabstract.setFont(new Font("Arial",Font.PLAIN,20));
        lblabstract.setBounds(10,70,100,20);
        contentPane.add(lblabstract);

        JTextArea txtareaabstract = new JTextArea();
        txtareaabstract.setBounds(10,100,150,100);
        txtareaabstract.setWrapStyleWord(true);
        txtareaabstract.setLineWrap(true);
        contentPane.add(txtareaabstract);

        JLabel lblcoauthors = new JLabel("Email Co-Authors");
        lblcoauthors.setFont(new Font("Arial",Font.PLAIN,20));
        lblcoauthors.setBounds(170,10,100,20);
        contentPane.add(lblcoauthors);

        JTextField txtcoauthors = new JTextField("Please enter the email address of the Co-Authors");
        txtcoauthors.setBounds(170,40,100,20);
        contentPane.add(txtcoauthors);

        JLabel lblconference = new JLabel("Conference");
        lblconference.setFont(new Font("Arial",Font.PLAIN,20));
        lblconference.setBounds(170,70,150,20);
        contentPane.add(lblconference);

        JTextField txtconference = new JTextField();
        txtconference.setBounds(170,100,100,20);
        contentPane.add(txtconference);

        JButton buttonSubmit = new JButton("Submit");
        buttonSubmit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonSubmit.setBackground(new Color(100, 149, 237));
        buttonSubmit.setForeground(Color.white);
        buttonSubmit.setBounds(170,170,100,30);
        buttonSubmit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(txtareaabstract.getText().length()<100) {
                    try {
                        ArticleController ac = new ArticleController();
                        UserController uc = new UserController();
                        ArrayList<RUserDTO> listacoautori = new ArrayList<>(Integer.parseInt(Arrays.asList(uc.getRAuthorBYEmail(Arrays.toString(txtcoauthors.getText().split(",")))).toString()));

                        if (ac.submitArticle(txttitle.getText(), txtareaabstract.getText(), listacoautori, conferenceID)) {
                            JOptionPane.showMessageDialog(null, "Article Submitted Successfully");
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Article Not Submitted", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }


                }






            }
        });
        contentPane.add(buttonSubmit);






    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RUserDTO userDTO = new RUserDTO("Name","Lastaname","email@test.com","affilation","Role",false,ID.generate());
                    SubmitArticleForm finestra = new SubmitArticleForm(userDTO,ID.generate());
                    finestra.setVisible(true);

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
