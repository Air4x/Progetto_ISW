package org.groupone.boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.groupone.DTO.RUserDTO;
import org.groupone.controller.UserController;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class LoginForm  extends JFrame {
    private JTextField txtemail = new JTextField();
    private JLabel lblemail = new JLabel();
    private JPasswordField passwordfield =  new JPasswordField();
    private JLabel lblpassword =  new JLabel();
    private JPanel panel =  new JPanel();
    private JButton loginbutton = new JButton();
    private RUserDTO userDTO;

    public LoginForm() {

        setDefaultCloseOperation(3);
        setSize(200,300);
        setLocationRelativeTo(null);
        setResizable(false);

        panel.setBounds(5,5,5,5);
        setTitle("Login Form");

        panel.setLayout(null);
        setContentPane(panel);

        lblemail.setText("Email:");
        lblemail.setBounds(10,10,100,30 );
        lblemail.setFont(new Font("Arial",Font.PLAIN,20));
        panel.add(lblemail);

        txtemail.setText("");
        txtemail.setBounds(10,50,150,30);
        panel.add(txtemail);

        lblpassword.setText("Password:");
        lblpassword.setFont(new Font("Arial",Font.PLAIN,20));
        lblpassword.setBounds(10,90,100,30 );
        panel.add(lblpassword);

        passwordfield.setText("");
        passwordfield.setEchoChar((char)0);
        passwordfield.setBounds(10,130,150,30);
        panel.add(passwordfield);

        loginbutton.setText("Login");
        loginbutton.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));

        loginbutton.setBounds(10,170,100,30);
        loginbutton.setBackground(new Color(100, 149, 237));
        loginbutton.setForeground(Color.white);
        loginbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    //Validazione Credenziali ed effettivo accesso
                    String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                    if (txtemail.getText().equals("") || passwordfield.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Please fill all the fields", "Warning", JOptionPane.WARNING_MESSAGE);

                    }
                    if (passwordfield.getText().length() > 30) {
                        JOptionPane.showMessageDialog(null, "The password is too long", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    if (!Pattern.matches(regex, txtemail.getText())) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid email", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    if (passwordfield.getText().length() < 30 && Pattern.matches(regex, txtemail.getText())) {
                        UserController userController = new UserController();

                        userDTO = userController.login(txtemail.getText(), passwordfield.getText());
                        if (userDTO==null) {
                            JOptionPane.showMessageDialog(null, "The User does not exist, proceed to register", "Warning", JOptionPane.WARNING_MESSAGE);
                        } else {
                            if (userDTO.getRole().equals("organizzatore")) {

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
                }
                catch (Exception ex) {
                    Logger.getLogger(LoginForm.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
        });
        panel.add(loginbutton);



    }
}
