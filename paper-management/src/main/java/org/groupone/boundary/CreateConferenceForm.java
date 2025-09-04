package org.groupone.boundary;

import org.groupone.DTO.RUserDTO;
import org.groupone.controller.ConferenceController;
import org.groupone.entity.Organizer;
import org.groupone.utility.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateConferenceForm extends JFrame {
    private JPanel contentPane = new JPanel();
    private JLabel lbltitle= new JLabel();
    private JTextField txttitle = new JTextField();
    private JLabel lbldescription = new JLabel();
    private JLabel lblduedate =  new JLabel();
    private JTextField txtduedate  = new JTextField();
    private JTextArea txtareadescription =  new JTextArea();
    private JButton buttonCreateConference = new JButton();
    private JButton buttonBack = new JButton();


    public CreateConferenceForm(RUserDTO organizer) {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle("Create Conference");
        setResizable(false);
        setLocationRelativeTo(null);


        contentPane.setLayout(null);
        setContentPane(contentPane);

        lbltitle.setText("Title");
        lbltitle.setFont(new Font("Arial", Font.PLAIN, 20));
        lbltitle.setBounds(10, 11, 80, 20);
        contentPane.add(lbltitle);


        txttitle.setText(" ");
        txttitle.setBounds(10, 40, 80, 20);
        contentPane.add(txttitle);

        lbldescription.setText("Description");
        lbldescription.setFont(new Font("Arial", Font.PLAIN, 20));
        lbldescription.setBounds(10, 70, 150, 20);
        contentPane.add(lbldescription);


        txtareadescription.setText(" ");
        txtareadescription.setBounds(10, 100, 300, 300);
        txtareadescription.setBackground(new Color(255, 255, 255));
        txtareadescription.setLineWrap(true);
        txtareadescription.setWrapStyleWord(true);
        contentPane.add(txtareadescription);

        lblduedate.setText("Due date");
        lblduedate.setFont(new Font("Arial", Font.PLAIN, 20));
        lblduedate.setBounds(160, 10, 150, 20);
        contentPane.add(lblduedate);


        txtduedate.setText("DD/MM/YYYY");
        txtduedate.setBounds(160, 40, 150, 20);
        contentPane.add(txtduedate);





        buttonCreateConference.setText("Create Conference");
        buttonCreateConference.setBackground(new Color(100, 149, 237));
        buttonCreateConference.setBounds(160, 70, 150, 30);
        buttonCreateConference.setForeground(Color.white);
        buttonCreateConference.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonCreateConference.addMouseListener(new  MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    System.out.println(organizer);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate duedate = LocalDate.parse(txtduedate.getText(),formatter);
                    ConferenceController cc = new ConferenceController();
                    LocalDate today =  LocalDate.now();
                    if (duedate.isBefore(today)) {
                        JOptionPane.showMessageDialog(null, "Conference creation failed, Due Date is earlier than today");
                    }
                    if (txttitle.getText().length() > 30) {
                        JOptionPane.showMessageDialog(null, "Conference creation failed, Title is too long");
                    }
                    if (txtareadescription.getText().length() > 100) {
                        JOptionPane.showMessageDialog(null, "Conference creation failed, Description is too long");
                    }
                    if (!duedate.isBefore(today) && txttitle.getText().length() <= 30 && txtareadescription.getText().length() <= 100) {
                        try {
                            cc.createConference( duedate, txttitle.getText(), txtareadescription.getText(), ID.generate(), organizer);
                            JOptionPane.showMessageDialog(null, "Conference created");
                            OrganizerDashboard frame = new OrganizerDashboard(organizer);
                            frame.setVisible(true);
                            dispose();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        }
                    }

                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, "Errore SQL");

                }
                }


        });
        contentPane.add(buttonCreateConference);

        buttonBack.setText("Back");
        buttonBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonBack.setForeground(Color.white);
        buttonBack.setBackground(new Color(100, 149, 237));
        buttonBack.setBounds(320, 70, 100, 30);
        buttonBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    OrganizerDashboard frame = new OrganizerDashboard(organizer);
                    frame.setVisible(true);


                    dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(AuthorDashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        contentPane.add(buttonBack);

    }
}
