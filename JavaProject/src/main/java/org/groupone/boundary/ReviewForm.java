package org.groupone.boundary;

import org.groupone.DTO.ReviewDTO;
import org.groupone.controller.ReviewController;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ReviewForm extends JFrame {
    private JPanel contentPane = new JPanel();
    private JComboBox comboBoxresult = new JComboBox();
    private JLabel lblscore = new JLabel("Score");
    private JLabel lblresult=  new JLabel("Result");
    private JTextField txtscore = new JTextField();
    private JButton updatereviewbutton =  new JButton("Review");

    public ReviewForm(ReviewDTO review, ReviewController rc) throws SQLException {

        setTitle("Review Form");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane.setLayout(null);
        setContentPane(contentPane);
        setLocationRelativeTo(null);

        lblscore.setBounds(10, 11, 80, 14);
        contentPane.add(lblscore);

        txtscore.setBounds(10, 40, 80, 20);
        contentPane.add(txtscore);
        lblresult.setBounds(10, 70, 80, 14);
        contentPane.add(lblresult);


        String[] results = {"Accettato", "Rifiutato"};
        comboBoxresult.setModel(new DefaultComboBoxModel(results));
        comboBoxresult.setSelectedIndex(0);
        comboBoxresult.setBounds(10, 110, 80, 20);
        contentPane.add(comboBoxresult);



        updatereviewbutton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        updatereviewbutton.setBackground(new Color(100,149,237));
        updatereviewbutton.setBounds(10, 149, 80, 23);
        updatereviewbutton.setForeground(Color.white);
        updatereviewbutton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updatereviewbutton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    int score = Integer.parseInt(txtscore.getText());

                    if(score>100 || score<0){
                        JOptionPane.showMessageDialog(null,"Score non compreso tra 0 e 100");
                    } else{
                        if((score<60 && comboBoxresult.getSelectedItem().toString().equals("Accettato"))||(score>=60 && comboBoxresult.getSelectedItem().toString().equals("Rifiutato"))){
                        JOptionPane.showMessageDialog(null, "Il punteggio e lo stato sono in conflitto");
                        }
                        else{
                            rc.updateReview(review,score,comboBoxresult.getSelectedItem().toString());
                            JOptionPane.showMessageDialog(null, "Review Updated Successfully");
                            dispose();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
        });
        contentPane.add(updatereviewbutton);


        


    }
}
