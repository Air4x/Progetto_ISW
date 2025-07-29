package boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import DTO.RUserDTO;
import controller.UserController;
import entity.Author;
import entity.Organizer;
import entity.User;

import java.util.regex.Pattern;

public class LoginForm  extends JFrame {
    private JTextField emailTextField;
    private JLabel lblemail;
    private JPasswordField passwordfield;
    private JLabel lblpassword;
    private JPanel panel;
    private JButton sendLoginButton;

    public LoginForm() {
        setDefaultCloseOperation(2);
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


        JTextField emailTextField = new JTextField();
        emailTextField.setBounds(10,50,150,30);
        panel.add(emailTextField);

        JLabel lblpassword = new JLabel("Password:");
        lblpassword.setFont(new Font("Arial",Font.PLAIN,20));
        lblpassword.setBounds(10,90,100,30 );
        panel.add(lblpassword);

        JPasswordField passwordfield = new JPasswordField();
        passwordfield.setBounds(10,130,150,30);
        panel.add(passwordfield);

        JButton sendLoginButton = new JButton("Send");
        sendLoginButton.setCursor(Cursor.getPredefinedCursor(HAND_CURSOR));

        sendLoginButton.setBounds(10,170,100,30);
        sendLoginButton.setBackground(new Color(100, 149, 237));
        sendLoginButton.setForeground(Color.white);
        sendLoginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) throws SQLException {

                //Validazione Credenziali ed effettivo accesso
                String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
                if(emailTextField.getText().equals("") || passwordfield.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Please fill all the fields","Warning",JOptionPane.WARNING_MESSAGE);

                }
                if(passwordfield.getText().length()>30){
                    JOptionPane.showMessageDialog(null,"The password is too long(>30 characters)","Warning",JOptionPane.WARNING_MESSAGE);
                }
                if(!Pattern.matches(regex,emailTextField.getText())){
                    JOptionPane.showMessageDialog(null,"Please enter a valid email","Warning",JOptionPane.WARNING_MESSAGE);
                }
                if(passwordfield.getText().length()<30 && Pattern.matches(regex,emailTextField.getText())){
                    UserController userController = new UserController();
                    RUserDTO userDTO= userController.login(emailTextField.getText(),passwordfield.getSelectedText());
                    if(userDTO==null){
                        JOptionPane.showMessageDialog(null,"The User does not exist, proceed to register","Warning",JOptionPane.WARNING_MESSAGE);
                    }
                    else{
                        if(userDTO.getRuolo().equals("Organizer")){
                            OrganizerDashboard organizerDashboard = new OrganizerDashboard(userDTO);
                            organizerDashboard.setVisible(true);
                            dispose();

                        }
                        else{
                            AuthorDashboard authorDashboard = new AuthorDashboard(userDTO);
                            authorDashboard.setVisible(true);
                            dispose();

                        }

                    }

                }


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
