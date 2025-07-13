package boundary;

import controller.ConferenceController;
import entity.Organizer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
                    String password = new String("Password");
                    String nome = new String("Nome");
                    String lastName = new String("Cognome");
                    int id=1;
                    int idConference=0;
                    Organizer organizer = new Organizer(affiliazione,email,password,nome,lastName,id);
                    CreateConferenceForm frame = new CreateConferenceForm(organizer,idConference);
                    frame.setVisible(true);

                }catch(Exception e){
                    e.printStackTrace();


                }
            }
        });
    }





    public CreateConferenceForm(Organizer organizer, int idConference) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle("Create Conference n."+idConference);

        contentPane = new JPanel();
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
        txtduedate.setText("DD/MM/YYYY");
        txtduedate.setBounds(160, 40, 150, 20);
        contentPane.add(txtduedate);
        Date duedate = new Date(txtduedate.getText());


        JButton buttonCreateConference = new JButton("Create Conference");
        buttonCreateConference.setBackground(new Color(100, 149, 237));
        buttonCreateConference.setBounds(160, 70, 150, 30);
        buttonCreateConference.setForeground(Color.white);
        buttonCreateConference.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonCreateConference.addMouseListener(new  MouseAdapter() {
            public void mouseClicked(MouseEvent e){
               ConferenceController.createConference(duedate,txttitle.getText(),txtareadescription.getText(),idConference,organizer);
               JOptionPane.showMessageDialog(null,"Conference created");
               dispose();
            }
        });
        contentPane.add(buttonCreateConference);
    }
}
