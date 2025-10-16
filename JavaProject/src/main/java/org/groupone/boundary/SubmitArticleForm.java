package org.groupone.boundary;

import org.groupone.DTO.RUserDTO;
import org.groupone.controller.ArticleController;
import org.groupone.controller.UserController;
import org.groupone.utility.CheckListItem;
import org.groupone.utility.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SubmitArticleForm extends JFrame {
    private JLabel lbltitle = new JLabel();
    private JTextField txttitle = new JTextField();
    private JLabel lblabstract = new JLabel();
    private JTextArea txtareaabstract = new JTextArea();
    private JLabel lblcoauthors = new JLabel();
    private JTextField txtcoauthors = new JTextField();
    private JPanel contentPane = new JPanel();
    private JButton buttonSubmit = new JButton();
    private JButton buttonBack = new JButton();
    private JList<CheckListItem> listacoautori = new JList();
    private JScrollPane scrollcoautori = new  JScrollPane();


    public SubmitArticleForm(RUserDTO userDTO, ID conferenceID) throws SQLException {
        ArticleController ac = new ArticleController();
        UserController uc = new UserController();

        setTitle("Submit Article Form");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 590, 500);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane.setBounds(5, 5, 5, 5);
        contentPane.setLayout(null);
        setContentPane(contentPane);

        lbltitle.setText("Title");
        lbltitle.setFont(new Font("Arial", Font.PLAIN, 20));
        lbltitle.setBounds(10, 10, 100, 20);
        contentPane.add(lbltitle);

        txttitle.setBounds(10, 40, 100, 20);
        contentPane.add(txttitle);

        lblabstract.setText("Abstract");
        lblabstract.setFont(new Font("Arial", Font.PLAIN, 20));
        lblabstract.setBounds(10, 70, 100, 20);
        contentPane.add(lblabstract);

        txtareaabstract.setBounds(10, 100, 150, 100);
        txtareaabstract.setWrapStyleWord(true);
        txtareaabstract.setLineWrap(true);
        contentPane.add(txtareaabstract);


        lblcoauthors.setText("Co Authors");
        lblcoauthors.setFont(new Font("Arial", Font.PLAIN, 20));
        lblcoauthors.setBounds(170, 10, 100, 20);
        contentPane.add(lblcoauthors);

        RUserDTO[] coautori =uc.getCooAuthors(userDTO.getEmail()).toArray(new RUserDTO[0]);

        DefaultListModel<CheckListItem> model = new DefaultListModel<>();
        for (RUserDTO user : coautori) {
            model.addElement(new CheckListItem(user));
        }

        listacoautori.setModel(model);
        listacoautori.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {

            JCheckBox check = new JCheckBox(value.toString(), value.isSelected());
            check.setBackground(isSelected ? list.getSelectionBackground() : list.getBackground());
            check.setForeground(isSelected ? list.getSelectionForeground() : list.getForeground());
            return check;
        });

        listacoautori.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = listacoautori.getSelectedIndex();
                if(index>=0){
                    CheckListItem item = model.get(index);
                    item.setSelected(!item.isSelected());
                    listacoautori.repaint(listacoautori.getCellBounds(index, index));
                }

            }
        });
        scrollcoautori.setViewportView(listacoautori);
        scrollcoautori.setBounds(170, 40, 250, 220);
        contentPane.add(scrollcoautori);

        buttonSubmit.setText("Submit");
        buttonSubmit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonSubmit.setBackground(new Color(100, 149, 237));
        buttonSubmit.setForeground(Color.white);
        buttonSubmit.setBounds(10, 300, 100, 30);
        buttonSubmit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                try {


                    ArrayList<RUserDTO> coautoriselezionati = new ArrayList<>();
                    coautoriselezionati.add(userDTO);

                    for(int i=0;i<model.size();i++){
                        if(model.get(i).isSelected()){
                            coautoriselezionati.add(model.getElementAt(i).getUser());
                        }
                    }

                    if(coautoriselezionati.size()>4){
                        JOptionPane.showMessageDialog(null,"The Coauthors selected are more than 3");
                    }
                    if (txttitle.getText().length() > 150) {
                        JOptionPane.showMessageDialog(null, "The title is too long.");
                    }
                    if (txtareaabstract.getText().length() > 250) {
                        JOptionPane.showMessageDialog(null, "The abstract is too long.");
                    }
                    if (txtareaabstract.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please Enter the Abstract.");
                    }
                    if (txttitle.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please Enter the Title.");
                    }

                    if (txtcoauthors.getText().length() <= 150 && txtareaabstract.getText().length() <= 250 && coautoriselezionati.size() <= 4) {
                        if (ac.submitArticle(txttitle.getText(), txtareaabstract.getText(), coautoriselezionati, conferenceID)) {
                            JOptionPane.showMessageDialog(null, "Article Submitted Successfully");
                            AuthorDashboard frame = new AuthorDashboard(userDTO);
                            frame.setVisible(true);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Article Not Submitted", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }


            }
        });
        contentPane.add(buttonSubmit);

        buttonBack.setText("Back");
        buttonBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonBack.setForeground(Color.white);
        buttonBack.setBackground(new Color(100, 149, 237));
        buttonBack.setBounds(170, 300, 100, 30);
        buttonBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    AuthorDashboard frame = new AuthorDashboard(userDTO);
                    frame.setVisible(true);


                    dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(AuthorDashboard.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        contentPane.add(buttonBack);





    }
}
