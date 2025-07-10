package boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginForm  extends JFrame {
    private JTextField emailTextField;
    private JLabel lblemail;
    private JPasswordField passwordfield;
    private JLabel lblpassword;
    private JPanel panel;
    private JButton sendLoginButton;

    public LoginForm() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(200,300);
        setLocationRelativeTo(null);
        setResizable(false);
        JPanel panel = new JPanel();
        panel.setBounds(5,5,5,5);
        setTitle("Login Form");

        panel.setLayout(null);
        setContentPane(panel);

        JLabel lblEmail = new JLabel("Email:");
        lblemail.setBounds(10,10,100,30 );
        lblemail.setFont(new Font("Arial",Font.PLAIN,20));
        panel.add(lblemail);


        emailTextField = new JTextField();
        emailTextField.setBounds(10,50,150,30);
        panel.add(emailTextField);

        JLabel lblpassword = new JLabel("Password:");
        lblpassword.setFont(new Font("Arial",Font.PLAIN,20));
        lblpassword.setBounds(10,90,100,30 );
        panel.add(lblpassword);

        passwordfield = new JPasswordField();
        passwordfield.setBounds(10,130,150,30);
        panel.add(passwordfield);

        JButton sendLoginButton = new JButton("Send Login");
        sendLoginButton.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));

        sendLoginButton.setBounds(10,170,100,30);
        sendLoginButton.setBackground(new Color(100, 149, 237));
        sendLoginButton.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e){


            }
        });
        panel.add(sendLoginButton);



    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {

                try {
                    LoginForm loginForm = new LoginForm();
                    loginForm.setVisible(true);
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        });

    }
}
