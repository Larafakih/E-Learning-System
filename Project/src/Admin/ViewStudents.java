package Admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Model.*;
import DAO.*;
import UI.*;
import Controller.*;
import java.util.*;

public class ViewStudents implements ActionListener {

    private JTable table;
    private JButton deleteButton, search, back;
    private JPanel panel;
    private JLabel title, labelname;
    private JTextField name;
    private JFrame f;
    private ArrayList<Student> List, Lists;
    private Object[][] row;
    private String[] columnName = new String[]{"FullName", "Email", "Phone Number", "Gender", "Picture"};
    private int Id;
    private TheModelTable model;

    public ViewStudents(int id) {
        f = new JFrame("View Students");
        f.setSize(1280, 720);
        f.setLocation(10, 10);
        f.setIconImage(img.SystemIcon());
        f.setLayout(new BorderLayout());
        Id = id;

        title = new TitleLbl("Students");
        f.add(title, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(null);

        back = new JButton();
        back.setBounds(880, 0, 100, 40);
        panel.add(back);
        back.addMouseListener(new UI.BackListener(Id, back, "Admin", f));

        labelname = new TxtLabel("Filter by name");
        labelname.setBounds(30, 20, 200, 30);
        panel.add(labelname);

        name = new TxtField();
        name.addActionListener(this);
        name.setBounds(210, 20, 210, 40);
        panel.add(name);

        search = new JButton("Search");
        search.setBackground(new Color(238, 238, 238));
        search.setIcon(img.CreateImageIcon("icons/search.png", 20, 20));
        search.addActionListener(this);
        search.setBounds(430, 22, 100, 30);
        panel.add(search);

        table = new Table(90, columnName);
        List = StudentDao.getArrayofstudents();
        putdataInTable(List);

        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(30, 70, 1170, 500);
        panel.add(pane);

        deleteButton = new CircleBtn("Delete", "Click to Delete Selected Record.");
        deleteButton.addActionListener(this);
        deleteButton.setBounds(390, 580, 200, 40);
        panel.add(deleteButton);
        f.add(panel, BorderLayout.CENTER);

        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deleteButton) {
            if (table.getSelectedRow() != -1) {
                int rowNum = table.getSelectedRow();
                String email = table.getModel().getValueAt(rowNum, 1).toString();
                AdminController.DeleteStudent(email, f, Id);
            }
        } else if (ae.getSource() == search) {
            Search();
        }
    }

    public void putdataInTable(ArrayList<Student> stds) {
        row = new Object[stds.size()][7];
        for (int i = 0; i < stds.size(); i++) {
            row[i][0] = stds.get(i).getFullName();
            row[i][1] = stds.get(i).getEmail();
            row[i][2] = stds.get(i).getPhone();
            row[i][3] = stds.get(i).getGender();
            if (stds.get(i).getPicture() != null) {
                ImageIcon image = new ImageIcon(new ImageIcon(stds.get(i).getPicture()).getImage()
                        .getScaledInstance(90, 90, Image.SCALE_SMOOTH));
                row[i][4] = image;
            } else {
                row[i][4] = null;
            }
        }
        TheModelTable model = new TheModelTable(row, columnName);
        table.setModel(model);
    }

    public void Search() {
        Lists = new ArrayList<Student>();
        if (name.getText().isEmpty()) {
            Lists = StudentDao.getArrayofstudents();
            putdataInTable(Lists);
        } else {
            Lists.clear();
            FilterName filter = new FilterName();
            String NAME = name.getText();
            Lists = filter.filterName(List, NAME);
            System.out.println("" + name.getText());
            putdataInTable(Lists);
        }
    }
}
