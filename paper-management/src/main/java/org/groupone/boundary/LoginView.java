package org.groupone.boundary;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView extends JFrame{
    private JButton loginButton;
    private JPanel panel;
    private JButton registerButton;



    public LoginView() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Login View");
        setBounds(100, 100, 230, 150);
        setResizable(false);
        JPanel panel = new JPanel();
        panel.setBounds(5,5,5,5);
        panel.setLayout(null);
        setLocationRelativeTo(null);
        setContentPane(panel);


        JButton loginButton = new JButton("Login");
        loginButton.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));
        loginButton.setBackground(new Color(100, 149, 237));
        loginButton.setForeground(Color.white);
        loginButton.setBounds(10,30,90,30);
        loginButton.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {

                LoginForm loginForm = new LoginForm();
                loginForm.setVisible(true);


            }

        });
        panel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));

        registerButton.setBounds(120,30,90,30);
        registerButton.setBackground(new Color(100, 149, 237));
        registerButton.setForeground(Color.white);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                RegistrationForm registrationForm = new RegistrationForm();
                registrationForm.setVisible(true);


            }
        });
        panel.add(registerButton);



    }
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        loginView.setVisible(true);

    }
}
