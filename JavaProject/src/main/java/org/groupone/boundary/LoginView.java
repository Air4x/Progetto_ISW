package org.groupone.boundary;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView extends JFrame{
    private JButton loginButton =  new JButton();
    private JPanel panel =  new JPanel();
    private JButton registerButton = new JButton();



    public LoginView() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Login View");
        setBounds(100, 100, 230, 150);
        setResizable(false);
        panel.setBounds(5,5,5,5);
        panel.setLayout(null);
        setLocationRelativeTo(null);
        setContentPane(panel);


        loginButton.setText("Login");
        loginButton.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
        loginButton.setBackground(new Color(100, 149, 237));
        loginButton.setForeground(Color.white);
        loginButton.setBounds(10,30,90,30);
        loginButton.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                LoginForm loginForm = new LoginForm();
                loginForm.setVisible(true);
                dispose();


            }

        });
        panel.add(loginButton);

        registerButton.setText("Register");
        registerButton.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));

        registerButton.setBounds(120,30,90,30);
        registerButton.setBackground(new Color(100, 149, 237));
        registerButton.setForeground(Color.white);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                RegistrationForm registrationForm = new RegistrationForm();
                registrationForm.setVisible(true);
                dispose();



            }
        });
        panel.add(registerButton);



    }

}
