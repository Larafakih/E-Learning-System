package Admin;

import Controller.*;
import Model.*;
import DAO.*;
import UI.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

public class ViewTeachers implements ActionListener {

    private Object[][] data;
    private JTable table;
    private JButton deleteButton, back;
    private DefaultTableModel model;
    private JLabel title;
    private JPanel panel;
    private JFrame fr;
    private int adminId;
    private String[] columnNames = new String[]{"FullName", "Email", "Phone Number", "Gender", "Picture"};

    public ViewTeachers(int id) {
        fr = new JFrame("View Teachers");
        fr.setSize(1280, 720);
        fr.setLocation(10, 10);
        fr.setLayout(new BorderLayout());
        adminId = id;

        title = new TitleLbl("Teachers");
        fr.add(title, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(null);

        back = new JButton();
        back.setBounds(890, 0, 100, 40);
        panel.add(back);
        back.addMouseListener(new UI.BackListener(adminId, back, "Admin", fr));

        table = new Table(90) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        load();
        JScrollPane pane = new JScrollPane(table);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        pane.setBounds(30, 50, 1170, 500);
        panel.add(pane, BorderLayout.CENTER);

        deleteButton = new CircleBtn("Delete", "Click to Delete Selected Record.");
        deleteButton.addActionListener(this);
        deleteButton.setBounds(390, 550, 200, 60);
        panel.add(deleteButton, BorderLayout.SOUTH);

        fr.add(panel, BorderLayout.CENTER);
        fr.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fr.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deleteButton) {
            if (table.getSelectedRow() != -1) {
                int rowNum = table.getSelectedRow();
                String email = table.getModel().getValueAt(rowNum, 1).toString();
                AdminController.DeleteTeacher(email, fr, adminId);
            }
        }
    }

    public void load() {
        ArrayList<Teacher> List = TeacherDao.getArrayTeachers();
        Object[][] row = new Object[List.size()][7];
        for (int i = 0; i < List.size(); i++) {
            row[i][0] = List.get(i).getFullName();
            row[i][1] = List.get(i).getEmail();
            row[i][2] = List.get(i).getPhone();
            row[i][3] = List.get(i).getGender();
            if (List.get(i).getPicture() != null) {
                ImageIcon image = new ImageIcon(new ImageIcon(List.get(i).getPicture()).getImage()
                        .getScaledInstance(90, 90, Image.SCALE_SMOOTH));
                row[i][4] = image;
            } else {
                row[i][4] = null;
            }
        }
        TheModelTable model = new TheModelTable(row, columnNames);
        table.setModel(model);
    }
}
