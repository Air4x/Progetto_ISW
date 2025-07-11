package boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
                    CreateConferenceForm frame = new CreateConferenceForm();
                    frame.setVisible(true);

                }catch(Exception e){
                    e.printStackTrace();


                }
            }
        });
    }





    public CreateConferenceForm() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle("Create Conference");

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

        JButton buttonCreateConference = new JButton("Create Conference");
        buttonCreateConference.setBackground(new Color(100, 149, 237));
        buttonCreateConference.setBounds(160, 70, 150, 30);
        buttonCreateConference.setForeground(Color.white);
        buttonCreateConference.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonCreateConference.addMouseListener(new  MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //Creazione Conferenza (Controller)
            }
        });
        contentPane.add(buttonCreateConference);
    }
}
