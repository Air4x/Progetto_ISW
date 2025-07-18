package boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AssignReviewersView extends JFrame {


    private JPanel contentPane;
    private JLabel lbltitle;
    private JTextField txttitle;
    private JLabel lblrevisori;
    private JTextField txtidrevisore1;
    private JTextField txtidrevisore2;
    private JTextField txtidrevisore3;
    private JButton buttonAssignReviewers;

    public AssignReviewersView() {
        setTitle("Assign Reviewers");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle("Assign Reviewers");


        JPanel contentPane = new JPanel();
        contentPane.setBounds(5,5,5,5);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lbltitle = new JLabel("Title");
        lbltitle.setFont(new Font("Arial", Font.PLAIN, 20));
        lbltitle.setBounds(10,10,100,15);
        contentPane.add(lbltitle);

        JTextField  txttitle = new JTextField();
        txttitle.setBounds(100,5,100,20);
        txttitle.setFont(new Font("Arial", Font.PLAIN, 15));
        contentPane.add(txttitle);

        JLabel lblrevisori = new JLabel("Reviewers");
        lblrevisori.setFont(new Font("Arial", Font.PLAIN, 20));
        lblrevisori.setBounds(10,40,100,15);
        contentPane.add(lblrevisori);


        JTextField  txtidrevisore1 = new JTextField();
        txtidrevisore1.setBounds(10,70,100,20);
        txtidrevisore1.setFont(new Font("Arial", Font.PLAIN, 15));
        contentPane.add(txtidrevisore1);

        JTextField  txtidrevisore2 = new JTextField();
        txtidrevisore2.setBounds(120,70,100,20);
        txtidrevisore2.setFont(new Font("Arial", Font.PLAIN, 15));
        contentPane.add(txtidrevisore2);


        JTextField  txtidrevisore3 = new JTextField();
        txtidrevisore3.setBounds(230,70,100,20);
        txtidrevisore3.setFont(new Font("Arial", Font.PLAIN, 15));
        contentPane.add(txtidrevisore3);


        JButton  buttonAssignReviewers = new JButton("Assign Reviewers");
        buttonAssignReviewers.setFont(new Font("Arial", Font.PLAIN, 15));
        buttonAssignReviewers.setBounds(230,110,100,30);
        buttonAssignReviewers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buttonAssignReviewers.setBackground(new Color(100, 149, 237));
        buttonAssignReviewers.setForeground(Color.white);
        buttonAssignReviewers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(txtidrevisore1.getText().isEmpty()|| txtidrevisore2.getText().isEmpty()||txtidrevisore3.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "At least one of the reviewers is null, Please enter a valid ID");
                }
                if (txttitle.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a title");
                }
                if (txtidrevisore1.getText().equals(txtidrevisore2.getText()) || txtidrevisore3.getText().equals(txtidrevisore1.getText())|| txtidrevisore2.getText().equals(txtidrevisore3.getText())) {
                    JOptionPane.showMessageDialog(null, "At least one of the reviewers is duplicated");

                }
            }
        });






    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    AssignReviewersView frame = new AssignReviewersView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
