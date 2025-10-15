package org.groupone.boundary;

import org.groupone.DTO.ReviewDTO;
import org.groupone.DTO.RUserDTO;
import org.groupone.DTO.ShowArticleDTO;
import org.groupone.controller.ReviewController;
import org.groupone.utility.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AssignReviewersView extends JFrame {


    private JPanel contentPane = new JPanel();
    private JLabel lblrevisori = new JLabel();
    private JButton buttonAssignReviewers= new JButton();
    private JComboBox reviewer1= new JComboBox();
    private JComboBox reviewer2= new JComboBox();
    private JComboBox reviewer3 = new JComboBox();
    private RUserDTO selected1,selected2,selected3;

    public AssignReviewersView(ShowArticleDTO article) {
        try {
            ReviewController rc = new ReviewController();
            setTitle("Assign Reviewers");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setBounds(100, 100, 450, 300);
            setTitle("Assign Reviewers");



            contentPane.setBounds(5, 5, 5, 5);
            contentPane.setLayout(null);
            setContentPane(contentPane);


            lblrevisori.setText("Reviewers");
            lblrevisori.setFont(new Font("Arial", Font.PLAIN, 20));
            lblrevisori.setBounds(10, 10, 100, 15);
            contentPane.add(lblrevisori);
            RUserDTO[] listreviewers = rc.getPossibleReviewers(article.getId()).toArray(new RUserDTO[0]);

            reviewer1.setModel(new DefaultComboBoxModel(listreviewers));
            reviewer1.setRenderer(new DefaultListCellRenderer() {
                @Override
                public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof RUserDTO) {
                        RUserDTO p = (RUserDTO) value;
                        setText(p.getName() +" "+p.getLastname());
                    }
                    return this;
                }
            });
            reviewer1.setFont(new Font("Arial", Font.PLAIN, 13));
            reviewer1.setBounds(10, 40, 250, 20);
            contentPane.add(reviewer1);

            reviewer2.setModel(new DefaultComboBoxModel(listreviewers));
            reviewer2.setRenderer(new DefaultListCellRenderer() {
                @Override
                public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof RUserDTO) {
                        RUserDTO p = (RUserDTO) value;
                        setText(p.getName() +" "+p.getLastname());
                    }
                    return this;
                }
            });
            reviewer2.setFont(new Font("Arial", Font.PLAIN, 13));
            reviewer2.setBounds(10, 60, 250, 20);
            contentPane.add(reviewer2);

            reviewer3.setModel(new DefaultComboBoxModel(listreviewers));
            reviewer3.setRenderer(new DefaultListCellRenderer() {
                @Override
               public java.awt.Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof RUserDTO) {
                        RUserDTO p = (RUserDTO) value;
                        setText(p.getName() +" "+p.getLastname());
                    }
                    return this;
                }
            });
            reviewer3.setFont(new Font("Arial", Font.PLAIN, 13));
            reviewer3.setBounds(10, 80, 250, 20);
            contentPane.add(reviewer3);



            buttonAssignReviewers.setText("Assign Reviewers");
            buttonAssignReviewers.setBounds(10, 150, 100, 30);
            buttonAssignReviewers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            buttonAssignReviewers.setBackground(new Color(100, 149, 237));
            buttonAssignReviewers.setForeground(Color.white);
            buttonAssignReviewers.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    selected1 = (RUserDTO) reviewer1.getSelectedItem();
                    selected2 = (RUserDTO) reviewer2.getSelectedItem();
                    selected3 = (RUserDTO) reviewer3.getSelectedItem();

                    if (selected1.getId().equals(selected2.getId()) || selected3.getId().equals(selected1.getId()) || selected2.getId().equals(selected3.getId())) {
                        JOptionPane.showMessageDialog(null, "At least one of the reviewers is duplicated");

                    }
                    if (!selected1.getId().equals(selected2.getId()) && !selected3.getId().equals(selected1.getId()) && !selected2.getId().equals(selected3.getId())) {
                        try {
                            ArrayList<RUserDTO> reviewrs = new ArrayList<RUserDTO>();
                            reviewrs.add(selected1);
                            reviewrs.add(selected2);
                            reviewrs.add(selected3);
                            rc.createReview(reviewrs, article);
                            JOptionPane.showMessageDialog(null, "Reviewers assigned successfully");
                            dispose();
                        }catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            });
            contentPane.add(buttonAssignReviewers);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
