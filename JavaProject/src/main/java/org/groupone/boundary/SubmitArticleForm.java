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
import java.util.logging.Level;
import java.util.logging.Logger;

public class SubmitArticleForm extends JFrame {
    private JLabel lbltitle = new JLabel();
    private JTextField txttitle = new JTextField();
    private JLabel lblabstract = new JLabel();
    private JTextArea txtareaabstract = new JTextArea();
    private JLabel lblcoauthors = new JLabel();
    private JTextField txtcoauthors = new JTextField();
    private JPanel contentPane = new JPanel();
    private JButton buttonSubmit = new JButton();
    private JButton buttonBack = new JButton();


    public SubmitArticleForm(RUserDTO userDTO, ID conferenceID) throws SQLException {
        setTitle("Submit Article Form");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 290, 300);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane.setBounds(5, 5, 5, 5);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        lbltitle.setText("Title");
        lbltitle.setFont(new Font("Arial", Font.PLAIN, 20));
        lbltitle.setBounds(10, 10, 100, 20);
        contentPane.add(lbltitle);

        txttitle.setBounds(10, 40, 100, 20);
        contentPane.add(txttitle);

        lblabstract.setText("Abstract");
        lblabstract.setFont(new Font("Arial", Font.PLAIN, 20));
        lblabstract.setBounds(10, 70, 100, 20);
        contentPane.add(lblabstract);

        txtareaabstract.setBounds(10, 100, 150, 100);
        txtareaabstract.setWrapStyleWord(true);
        txtareaabstract.setLineWrap(true);
        contentPane.add(txtareaabstract);


        lblcoauthors.setText("Co Authors");
        lblcoauthors.setFont(new Font("Arial", Font.PLAIN, 20));
        lblcoauthors.setBounds(120, 10, 100, 20);
        contentPane.add(lblcoauthors);


        txtcoauthors.setText("Please Enter the email of the coauthors");
        txtcoauthors.setBounds(120, 40, 150, 20);
        contentPane.add(txtcoauthors);


        buttonSubmit.setText("Submit");
        buttonSubmit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonSubmit.setBackground(new Color(100, 149, 237));
        buttonSubmit.setForeground(Color.white);
        buttonSubmit.setBounds(170, 170, 100, 30);
        buttonSubmit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                try {
                    ArticleController ac = new ArticleController();
                    UserController uc = new UserController();
                    String[] coautori = txtcoauthors.getText().split(",");
                    ArrayList<RUserDTO> listacoautori = new ArrayList<>();

                    for (String s : coautori) {
                        listacoautori.add(uc.getRAuthorBYEmail(s));
                    }
                    if (listacoautori.size() > 3) {
                        JOptionPane.showMessageDialog(null, "There are more than 3 coauthors in your article.");

                    }
                    if (txttitle.getText().length() > 150) {
                        JOptionPane.showMessageDialog(null, "The title is too long.");
                    }
                    if (txtareaabstract.getText().length() > 250) {
                        JOptionPane.showMessageDialog(null, "The abstract is too long.");

                    }
                    if (txtcoauthors.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please Enter Co Author.");
                    }
                    if (txtareaabstract.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please Enter the Abstract.");
                    }
                    if (txttitle.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please Enter the Title.");
                    }

                    if (txtcoauthors.getText().length() <= 150 && txtareaabstract.getText().length() <= 250 && listacoautori.size() <= 3) {
                        System.out.println(listacoautori);
                        System.out.println(Arrays.toString(coautori));
                        if (ac.submitArticle(txttitle.getText(), txtareaabstract.getText(), listacoautori, conferenceID)) {
                            JOptionPane.showMessageDialog(null, "Article Submitted Successfully");
                            AuthorDashboard frame = new AuthorDashboard(userDTO);
                            frame.setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Article Not Submitted", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


            }
        });
        contentPane.add(buttonSubmit);

        buttonBack.setText("Back");
        buttonBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonBack.setForeground(Color.white);
        buttonBack.setBackground(new Color(100, 149, 237));
        buttonBack.setBounds(170, 210, 100, 30);
        buttonBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    AuthorDashboard frame = new AuthorDashboard(userDTO);
                    frame.setVisible(true);


                    dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(AuthorDashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });


    }
}
