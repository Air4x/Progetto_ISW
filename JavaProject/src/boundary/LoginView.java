package boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame{
    private JButton loginButton;
    private JPanel panel;
    private JButton registerButton;
    private JPanel loginpanel;
    private JPanel registerpanel;
    private JLabel Email;
    private JLabel Password;
    private JPasswordField passwordfield;
    private JTextField emailtxt;
    private JButton sendlogin;


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
        loginButton.setBounds(10,30,90,30);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel loginpanel = new JPanel();



                loginpanel.setLayout(null);
                loginpanel.setBounds(5,10,5,5);
                setContentPane(loginpanel);

                JLabel Email = new JLabel("Email");
                Email.setBounds(10,30,90,30);
                Email.setFont(new Font("Arial",Font.BOLD,25));
                loginpanel.add(Email);

                emailtxt = new JTextField();
                emailtxt.setBounds(10,30,90,30);

                loginpanel.add(emailtext);
                emailtxt.setColumns(10);


                JLabel Password = new JLabel("Password");
                Password.setBounds(10,30,90,30);
                Password.setFont(new Font("Arial",Font.BOLD,25));
                loginpanel.add(Password);

                passwordfield= new JPasswordField();
                passwordfield.setBounds(10,30,90,30);
                loginpanel.add(passwordfield);
                passwordfield.setColumns(10);

                JButton sendlogin = new JButton("Send Login");
                sendlogin.setBounds(10,30,90,30);
                sendlogin.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Email:"+emailtxt.getText()+"\t\t Password"+ passwordfld.getText());
                    }
                });
            }

        });
        panel.add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(120,30,90,30);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panel.removeAll();
                JPanel registerpanel = new JPanel();
                registerpanel.setBounds(5,5,5,5);
                registerpanel.setLayout(null);
                setContentPane(registerpanel);





            }
        });
        panel.add(registerButton);



    }
    public static void main(String[] args) {
        LoginView loginView = new LoginView();
        loginView.setVisible(true);

    }
}
