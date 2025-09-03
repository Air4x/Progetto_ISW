package org.groupone.boundary;

import org.groupone.DTO.RUserDTO;
import org.groupone.controller.ConferenceController;
import org.groupone.utility.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.sql.SQLException;

public class CreateConferenceForm extends JFrame {
    private JPanel contentPane = new JPanel();
    private JLabel lbltitle= new JLabel();
    private JTextField txttitle = new JTextField();
    private JLabel lbldescription = new JLabel();
    private JLabel lblduedate =  new JLabel();
    private JTextField txtduedate  = new JTextField();
    private JTextArea txtareadescription =  new JTextArea();
    private JButton buttonCreateConference = new JButton();


    //Run method

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    String affiliazione = new String("Affiliazione") ;
                    String email = new String("email");
                    String nome = new String("Nome");
                    String lastName = new String("Cognome");
                    RUserDTO organizer = new RUserDTO(nome,lastName,email,affiliazione,"Organizer",ID.generate());
                    CreateConferenceForm frame = new CreateConferenceForm(organizer);
                    frame.setVisible(true);

                }catch(Exception e){
                    e.printStackTrace();


                }
            }
        });
    }





    public CreateConferenceForm(RUserDTO organizer) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle("Create Conference");


        contentPane.setLayout(null);
        setContentPane(contentPane);

        lbltitle.setText("Title");
        lbltitle.setFont(new Font("Arial", Font.PLAIN, 20));
        lbltitle.setBounds(10, 11, 80, 20);
        contentPane.add(lbltitle);


        txttitle.setText("Title");
        txttitle.setBounds(10, 40, 80, 20);
        contentPane.add(txttitle);

        lbldescription.setText("Description");
        lbldescription.setFont(new Font("Arial", Font.PLAIN, 20));
        lbldescription.setBounds(10, 70, 150, 20);
        contentPane.add(lbldescription);


        txtareadescription.setText("Description");
        txtareadescription.setBounds(10, 100, 300, 300);
        txtareadescription.setBackground(new Color(255, 255, 255));
        txtareadescription.setLineWrap(true);
        txtareadescription.setWrapStyleWord(true);
        contentPane.add(txtareadescription);

        lblduedate.setText("Due date");
        lblduedate.setFont(new Font("Arial", Font.PLAIN, 20));
        lblduedate.setBounds(160, 10, 150, 20);
        contentPane.add(lblduedate);


        txtduedate.setText("DD-MM-YYYY");
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
                    LocalDate duedate = LocalDate.parse(txtduedate.getText());
                    ConferenceController cc = new ConferenceController();
                    LocalDate today =  LocalDate.now();

                    if (!duedate.isBefore(today) && txttitle.getText().length() < 30 && txtareadescription.getText().length() < 100) {
                        try {
                            cc.createConference( duedate, txttitle.getText(), txtareadescription.getText(), ID.generate(), organizer);
                            JOptionPane.showMessageDialog(null, "Conference created");
                            dispose();
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex);
                        }
                    }
                    if (duedate.isBefore(today)) {
                        JOptionPane.showMessageDialog(null, "Conference creation failed, Due Date is earlier than today");
                    }
                    if (txttitle.getText().length() > 30) {
                        JOptionPane.showMessageDialog(null, "Conference creation failed, Title is too long");
                    }
                    if (txtareadescription.getText().length() > 100) {
                        JOptionPane.showMessageDialog(null, "Conference creation failed, Description is too long");
                    }
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(null, "Errore SQL");

                }
                }


        });
        contentPane.add(buttonCreateConference);
    }
}
