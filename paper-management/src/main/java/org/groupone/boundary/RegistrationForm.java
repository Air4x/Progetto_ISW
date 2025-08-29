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
    private JLabel lblnome;
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
    private JTextField txtruolo;
    private JButton registerbutton;


    public RegistrationForm() {

        setDefaultCloseOperation(2);
        setBounds(250,300,300,350);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel= new JPanel();
        panel.setBounds(5,5,5,5);
        setContentPane(panel);
        panel.setLayout(null);

        JLabel lblname=new JLabel("Name:");
        lblname.setBounds(10,10,100,20);
        lblname.setFont(new Font("Arial",Font.PLAIN,20));
        panel.add(lblname);

        JTextField txtname=new JTextField();
        txtname.setBounds(10,30,100,20);
        txtname.setFont(new Font("Arial",Font.PLAIN,12));
        panel.add(txtname);

        JLabel lbllastname=new JLabel("Last Name:");
        lbllastname.setBounds(150,10,150,20);
        lbllastname.setFont(new Font("Arial",Font.PLAIN,20));
        panel.add(lbllastname);

        JTextField txtlastname=new JTextField();
        txtlastname.setBounds(150,30,100,20);
        txtlastname.setFont(new Font("Arial",Font.PLAIN,12));
        panel.add(txtlastname);

        JLabel lblemail=new JLabel("Email:");
        lblemail.setBounds(10,100,100,20);
        lblemail.setFont(new Font("Arial",Font.PLAIN,20));
        panel.add(lblemail);

        JTextField txtemail=new JTextField();
        txtemail.setBounds(10,120,100,20);
        txtemail.setFont(new Font("Arial",Font.PLAIN,12));
        panel.add(txtemail);

        JLabel lblpassword=new JLabel("Password:");
        lblpassword.setBounds(150,100,100,20);
        lblpassword.setFont(new Font("Arial",Font.PLAIN,20));
        panel.add(lblpassword);

        JPasswordField passwordField1=new JPasswordField();
        passwordField1.setBounds(150,120,100,20);
        passwordField1.setFont(new Font("Arial",Font.PLAIN,12));
        panel.add(passwordField1);

        JLabel lblaffiliazione=new JLabel("Affilation:");
        lblaffiliazione.setBounds(10,190,150,20);
        lblaffiliazione.setFont(new Font("Arial",Font.PLAIN,20));
        panel.add(lblaffiliazione);

        JTextField txtfaffiliazione=new JTextField();
        txtfaffiliazione.setBounds(10,210,100,20);
        txtaffiliazione.setFont(new Font("Arial",Font.PLAIN,12));
        panel.add(txtfaffiliazione);

        JLabel lblruolo=new JLabel("Role:");
        lblruolo.setBounds(150,190,100,20);
        lblruolo.setFont(new Font("Arial",Font.PLAIN,20));
        panel.add(lblruolo);

        JTextField txtruolo=new JTextField();
        txtruolo.setBounds(150,210,100,20);
        txtruolo.setFont(new Font("Arial",Font.PLAIN,12));
        panel.add(txtruolo);

        JButton registerbutton=new JButton("Register");
        registerbutton.setBounds(10,250,150,30);
        registerbutton.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
        registerbutton.setBackground(new Color(100, 149, 237));
        registerbutton.setForeground(Color.white);
        registerbutton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

                    if (!txtemail.getText().matches(regex)) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid email", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    if (txtname.getText().isEmpty() || txtlastname.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid name and last name", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    if (!txtruolo.getText().equalsIgnoreCase("organizzatore") && !txtruolo.getText().equalsIgnoreCase("autore")) {
                        JOptionPane.showMessageDialog(null, txtruolo.getText()+" is not a role", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    if(txtruolo.getText() == null ||  txtruolo.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "Please enter a valid role", "Warning", JOptionPane.WARNING_MESSAGE);
                    }

                    //Se tutto corrisponde
                    if (!txtemail.getText().isEmpty() && !txtemail.getText().isEmpty() && txtemail.getText().matches(regex)&&(txtruolo.getText().equalsIgnoreCase("autore")||txtruolo.getText().equalsIgnoreCase("organizzatore"))) {
                        UserController uc = new UserController();
                        RUserDTO userDTO = uc.registerUser(txtaffiliazione.getText(), txtemail.getText(), txtlastname.getText(), txtname.getText(), passwordField1.getSelectedText(), txtruolo.getText());
                        if (userDTO == null) {
                            JOptionPane.showMessageDialog(null, "User already register, proceed to login", "Warining", JOptionPane.WARNING_MESSAGE);

                        } else {
                            if (txtruolo.getText().equals("organizzatore")) {
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
