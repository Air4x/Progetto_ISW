package org.groupone.boundary;

import org.groupone.DTO.PossibleReviewDTO;
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


    private JPanel contentPane;
    private JLabel lblrevisori;
    private JButton buttonAssignReviewers;
    private PossibleReviewDTO selected1,selected2,selected3;

    public AssignReviewersView(ShowArticleDTO article) {
        try {
            ReviewController rc = new ReviewController();
            setTitle("Assign Reviewers");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setBounds(100, 100, 450, 300);
            setTitle("Assign Reviewers");


            JPanel contentPane = new JPanel();
            contentPane.setBounds(5, 5, 5, 5);
            contentPane.setLayout(null);
            setContentPane(contentPane);


            JLabel lblrevisori = new JLabel("Reviewers");
            lblrevisori.setFont(new Font("Arial", Font.PLAIN, 20));
            lblrevisori.setBounds(10, 40, 100, 15);
            contentPane.add(lblrevisori);
            java.util.List<PossibleReviewDTO> listarevisori = rc.getListReviewer(article.getId());

            JMenu revisore1 = new JMenu("1st Reviewer");
            JMenu revisore2 = new JMenu("2nd Reviewer");
            JMenu revisore3 = new JMenu("3rd Reviewer");
            for (int i = 0; i < listarevisori.size(); ) {
                JMenuItem item1 = new JMenuItem(listarevisori.get(i).toString());
                int currenti = i;
                item1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        selected1 = listarevisori.get(currenti);

                    }
                });
                revisore1.add(item1);
                JMenuItem item2 = new JMenuItem(listarevisori.get(currenti).toString());
                item2.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        selected2 = listarevisori.get(currenti);
                    }

                });
                revisore2.add(item2);
                JMenuItem item3 = new JMenuItem(listarevisori.get(i).toString());
                item3.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        selected3 = listarevisori.get(currenti);
                    }
                });
                revisore3.add(item3);
                i++;
            }


            JButton buttonAssignReviewers = new JButton("Assign Reviewers");
            buttonAssignReviewers.setFont(new Font("Arial", Font.PLAIN, 15));
            buttonAssignReviewers.setBounds(230, 110, 100, 30);
            buttonAssignReviewers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            buttonAssignReviewers.setBackground(new Color(100, 149, 237));
            buttonAssignReviewers.setForeground(Color.white);
            buttonAssignReviewers.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {

                    if (selected1.getId().equals(selected2.getId()) || selected3.getId().equals(selected1.getId()) || selected2.getId().equals(selected3.getId())) {
                        JOptionPane.showMessageDialog(null, "At least one of the reviewers is duplicated");

                    }
                    if (!selected1.getId().equals(selected2.getId()) && !selected3.getId().equals(selected1.getId()) && !selected2.getId().equals(selected3.getId())) {
                        try {
                            ArrayList<PossibleReviewDTO> reviewrs = new ArrayList<PossibleReviewDTO>();
                            reviewrs.add(selected1);
                            reviewrs.add(selected2);
                            reviewrs.add(selected3);
                            rc.assignReviewer(article.getId(), reviewrs);
                        }catch(SQLException ex) {
                            Logger.getLogger(ReviewController.class.getName()).log(Level.SEVERE, null, ex);
                        }


                    }
                }
            });
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ArrayList<RUserDTO> lista = new  ArrayList();


                    ShowArticleDTO article =  new ShowArticleDTO(ID.generate(),"Title","Abstract",lista);
                    AssignReviewersView frame = new AssignReviewersView(article);
                    frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }
}
