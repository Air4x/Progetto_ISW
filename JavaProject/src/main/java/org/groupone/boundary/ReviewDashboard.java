package org.groupone.boundary;

import org.groupone.DTO.RUserDTO;
import org.groupone.DTO.ReviewDTO;
import org.groupone.controller.ReviewController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class ReviewDashboard extends JFrame {

    private JPanel contentPane = new JPanel();
    private JScrollPane scrollarticletoreview =  new JScrollPane();
    private JList listarticletoreview =  new JList();


    ReviewDashboard(RUserDTO reviewer) {
        try {
            setTitle("Review Dashboard");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 450, 300);
            setContentPane(contentPane);
            contentPane.setLayout(null);
            ReviewController rc = new ReviewController();
            ReviewDTO[] articletoreview = rc.getAllReviewsByReviewer(reviewer.getId()).toArray(new ReviewDTO[0]);
            DefaultListModel reviewmodel= new DefaultListModel<>();
            for(int i=0;i<articletoreview.length;i++){
                reviewmodel.addElement(articletoreview[i]);
            }

            listarticletoreview.setModel(reviewmodel);
            listarticletoreview.setCellRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if(value instanceof ReviewDTO){
                        ReviewDTO review = (ReviewDTO) value;
                        setText("Article: " + review.getArticle() + "Status:" );
                    }
                    return this;
                }
            });

            scrollarticletoreview.setViewportView(listarticletoreview);
            scrollarticletoreview.setBounds(0, 0, 450, 300);
            listarticletoreview.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    try {
                        int indice = listarticletoreview.locationToIndex(e.getPoint());
                        ReviewDTO selected = (ReviewDTO) listarticletoreview.getModel().getElementAt(indice);
                        ReviewForm reviewform = new ReviewForm(selected,rc);
                        reviewform.setVisible(true);
                    } catch (SQLException ex) {
                        ex.printStackTrace();

                    }

                }
            });
            contentPane.add(scrollarticletoreview);



        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
