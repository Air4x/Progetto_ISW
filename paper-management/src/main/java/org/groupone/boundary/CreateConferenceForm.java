package org.groupone.boundary;

import org.groupone.DTO.RUserDTO;
import org.groupone.controller.ConferenceController;
import org.groupone.utility.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Date;
import java.sql.SQLException;

public class CreateConferenceForm extends JFrame {
    private JPanel contentPane;
    private JLabel lbltitle;
    private JTextField txttitle;
    private JLabel lbldescription;
    private JLabel lblduedate;
    private JTextField txtduedate;
    private JTextArea txtareadescription;
    private JButton buttonCreateConference;


    //Run method

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    String affiliazione = new String("Affiliazione") ;
                    String email = new String("email");
                    String nome = new String("Nome");
                    String lastName = new String("Cognome");
                    RUserDTO organizer = new RUserDTO(nome,lastName,email,affiliazione,"Organizer",true,ID.generate());
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

        JPanel contentPane = new JPanel();
        //contentPane.setBackground(Color.lightGray);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lbltitle = new JLabel("Title");
        lbltitle.setFont(new Font("Arial", Font.PLAIN, 20));
        lbltitle.setBounds(10, 11, 80, 20);
        contentPane.add(lbltitle);

        JTextField txttitle = new JTextField();
        txttitle.setText("Title");
        txttitle.setBounds(10, 40, 80, 20);
        contentPane.add(txttitle);

        JLabel lbldescription = new JLabel("Description");
        lbldescription.setFont(new Font("Arial", Font.PLAIN, 20));
        lbldescription.setBounds(10, 70, 150, 20);
        contentPane.add(lbldescription);

        JTextArea txtareadescription = new JTextArea();
        txtareadescription.setText("Description");
        txtareadescription.setBounds(10, 100, 300, 300);
        txtareadescription.setBackground(new Color(255, 255, 255));
        txtareadescription.setLineWrap(true);
        txtareadescription.setWrapStyleWord(true);
        contentPane.add(txtareadescription);

        JLabel lblduedate = new JLabel("Due Date:");
        lblduedate.setFont(new Font("Arial", Font.PLAIN, 20));
        lblduedate.setBounds(160, 10, 150, 20);
        contentPane.add(lblduedate);

        JTextField txtduedate = new JTextField();
        txtduedate.setText("DD-MM-YYYY");
        txtduedate.setBounds(160, 40, 150, 20);
        contentPane.add(txtduedate);
        LocalDate duedate = LocalDate.parse(txtduedate.getText());




        JButton buttonCreateConference = new JButton("Create Conference");
        buttonCreateConference.setBackground(new Color(100, 149, 237));
        buttonCreateConference.setBounds(160, 70, 150, 30);
        buttonCreateConference.setForeground(Color.white);
        buttonCreateConference.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonCreateConference.addMouseListener(new  MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    ConferenceController cc = new ConferenceController();
                    LocalDate today =  LocalDate.now();

                    if (!duedate.isBefore(today) && txttitle.getText().length() < 30 && txtareadescription.getText().length() < 100) {
                        try {

                            cc.createConference((LocalDate) duedate, txttitle.getText(), txtareadescription.getText(), ID.generate(), organizer);
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
