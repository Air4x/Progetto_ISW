package org.groupone.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.groupone.DTO.RUserDTO;
import org.groupone.controller.UserController;

public class RegistrationForm extends JFrame {
    private JTextField txtname;
    private JLabel lblname;
    private JPanel panel;
    private JLabel lbllastname;
    private JTextField txtlastname;
    private JTextField txtemail;
    private JLabel lblemail;
    private JPasswordField passwordField1;
    private JLabel lblpassword;
    private JTextField txtaffiliazione;
    private JLabel lblaffiliazione;
    private JLabel lblruolo;
    private JButton registerbutton;
    private JComboBox comboboxruoli;
    private RUserDTO userDTO;


    public RegistrationForm() {

        setDefaultCloseOperation(2);
        setBounds(250,300,300,350);
        setLocationRelativeTo(null);
        setResizable(false);


        panel.setBounds(5,5,5,5);
        setContentPane(panel);
        panel.setLayout(null);

        lblname.setText("Nome :");
        lblname.setBounds(10,10,100,20);
        lblname.setFont(new Font("Arial",Font.PLAIN,20));
        panel.add(lblname);


        txtname.setBounds(10,30,100,20);
        txtname.setFont(new Font("Arial",Font.PLAIN,12));
        panel.add(txtname);

        lbllastname.setText("Last Name :");
        lbllastname.setBounds(150,10,150,20);
        lbllastname.setFont(new Font("Arial",Font.PLAIN,20));
        panel.add(lbllastname);


        txtlastname.setBounds(150,30,100,20);
        txtlastname.setFont(new Font("Arial",Font.PLAIN,12));
        panel.add(txtlastname);

        lblemail.setText("Email :");
        lblemail.setBounds(10,100,100,20);
        lblemail.setFont(new Font("Arial",Font.PLAIN,20));
        panel.add(lblemail);


        txtemail.setBounds(10,120,100,20);
        txtemail.setFont(new Font("Arial",Font.PLAIN,12));
        panel.add(txtemail);

        

        lblpassword.setText("Password :");
        lblpassword.setBounds(150,100,100,20);
        lblpassword.setFont(new Font("Arial",Font.PLAIN,20));
        panel.add(lblpassword);


        passwordField1.setBounds(150,120,100,20);
        passwordField1.setFont(new Font("Arial",Font.PLAIN,12));
        panel.add(passwordField1);

        lblaffiliazione.setText("Affiliazione :");
        lblaffiliazione.setBounds(10,190,150,20);
        lblaffiliazione.setFont(new Font("Arial",Font.PLAIN,20));
        panel.add(lblaffiliazione);


        txtaffiliazione.setBounds(10,210,100,20);
        txtaffiliazione.setFont(new Font("Arial",Font.PLAIN,12));
        panel.add(txtaffiliazione);

        lblruolo.setText("Ruolo :");
        lblruolo.setBounds(150,190,100,20);
        lblruolo.setFont(new Font("Arial",Font.PLAIN,20));
        panel.add(lblruolo);



        String[] ruoli = {"organizzatore", "autore"};
        comboboxruoli.setModel(new DefaultComboBoxModel(ruoli));
        comboboxruoli.setSelectedIndex(0);
        comboboxruoli.setBounds(150,210,100,20);
        panel.add(comboboxruoli);





        registerbutton.setText("Register");
        registerbutton.setBounds(10,250,150,30);
        registerbutton.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
        registerbutton.setBackground(new Color(100, 149, 237));
        registerbutton.setForeground(Color.white);
        registerbutton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    String regexemail = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";



                    if (!txtemail.getText().matches(regexemail) || txtemail.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid email", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    if (txtname.getText().isEmpty() || txtlastname.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid name and last name", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    if (passwordField1.getText().isEmpty() || passwordField1.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid password", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    if(txtname.getText().length()>30 || txtlastname.getText().length()>30){
                        JOptionPane.showMessageDialog(null, "The name or the last name is too long", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    if(passwordField1.getText().length()>40){
                        JOptionPane.showMessageDialog(null, "The password is too long", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    if(!passwordField1.getText().matches(".*^[a-zA-Z0-9-].*")){
                        JOptionPane.showMessageDialog(null, "The password does not contain special characters ", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    if(txtaffiliazione.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Affilation is too long", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    if(txtaffiliazione.getText().length()>50){
                        JOptionPane.showMessageDialog(null, "The affilation is too long", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    if(!txtaffiliazione.getText().matches(".*^[a-zA-Z0-9-].*")){
                        JOptionPane.showMessageDialog(null, "The affilation contains special characters", "Warning", JOptionPane.WARNING_MESSAGE);
                    }

                    //Se tutto corrisponde
                    if (txtemail.getText().matches(regexemail) && !txtlastname.getText().isEmpty() && !txtname.getText().isEmpty() && (txtname.getText().length() <= 30) && (txtlastname.getText().length() <= 30) && !passwordField1.getText().isEmpty()&&(passwordField1.getText().length() <= 40) && passwordField1.getText().matches(".*^[a-zA-Z0-9-].*")&&txtaffiliazione.getText().length() <=50) {
                        System.out.println(comboboxruoli.getSelectedItem().toString());
                        UserController uc = new UserController();

                        userDTO = uc.registerUser(txtaffiliazione.getText(), txtemail.getText(), txtlastname.getText(), txtname.getText(), passwordField1.getText(), comboboxruoli.getSelectedItem().toString());
                        if (userDTO == null) {
                            JOptionPane.showMessageDialog(null, "User already register, proceed to login", "Warining", JOptionPane.WARNING_MESSAGE);

                        } else {
                            if (comboboxruoli.getSelectedItem().toString().equals("organizzatore")) {
                                OrganizerDashboard organizerDashboard = new OrganizerDashboard(userDTO);
                                organizerDashboard.setVisible(true);
                                dispose();
                            } else {
                                AuthorDashboard authorDashboard = new AuthorDashboard(userDTO);
                                authorDashboard.setVisible(true);
                                dispose();

                            }
                        }

                    }
                }catch (Exception ex) {
                    Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
                }





            }


        });


        panel.add(registerbutton);






    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run(){
                try{
                    RegistrationForm finestra = new RegistrationForm();
                    finestra.setVisible(true);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
