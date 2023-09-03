package Teacher;

import DAO.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import Model.*;
import UI.*;

public class ViewMyStudents implements ActionListener {

    private static final String RoleT = "Teacher";
    private JLabel title, courseCbLbl;
    private JComboBox coursesCb;
    private JPanel middlePanel;
    private JScrollPane scroll;
    private JTable table;
    private int currentTeacherID;
    private ArrayList<Student> arrayst;
    private JFrame f;
    private JButton back;
    private String[] columnName = new String[]{"Full Name", "Email", "Phone Number", "Gender", "Picture"};

    public ViewMyStudents(int c) {
        f = new JFrame("View My Students");
        f.setLayout(new BorderLayout());
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setSize(1280, 720);
        f.setLocation(10, 10);
        currentTeacherID = c;

        title = new TitleLbl("View My Students");
        f.add(title, BorderLayout.NORTH);

        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        f.add(middlePanel, BorderLayout.CENTER);

        back = new JButton();
        back.setBounds(880, 0, 100, 40);
        middlePanel.add(back);
        back.addMouseListener(new UI.BackListener(currentTeacherID, back, RoleT, f));

        table = new UI.Table(90, columnName);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        courseCbLbl = new TxtLabel("Select Course");
        courseCbLbl.setBounds(50, 30, 300, 28);
        middlePanel.add(courseCbLbl);

        coursesCb = new JComboBox();
        coursesCb.setSelectedIndex(-1);
        coursesCb.setBounds(200, 30, 350, 28);
        coursesCb.addActionListener(this);
        middlePanel.add(coursesCb);
        ArrayList<String> arrayc = CoursesDao.getTeacherCourse(currentTeacherID);
        for (int i = 0; i < arrayc.size(); i++) {
            coursesCb.addItem(arrayc.get(i));
            //coursesCb.setToolTipText(""+arrayc.get(i));
        }

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 90, 1200, 600);
        middlePanel.add(pane);

        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == coursesCb) {
            Object selected = coursesCb.getSelectedItem();
            String courseName = selected.toString();
            if (!courseName.isEmpty()) {
                load(courseName);
            } else {
                System.out.println("empty");
            }
        }

    }

    public void load(String course) {
        arrayst = StudentDao.getMyStudents(course);

        Object[][] row = new Object[arrayst.size()][5];
        for (int i = 0; i < arrayst.size(); i++) {
            row[i][0] = arrayst.get(i).getFullName();
            row[i][1] = arrayst.get(i).getEmail();
            row[i][2] = arrayst.get(i).getPhone();
            row[i][3] = arrayst.get(i).getGender();
            if (arrayst.get(i).getPicture() != null) {
                ImageIcon image = new ImageIcon(new ImageIcon(arrayst.get(i).getPicture()).getImage()
                        .getScaledInstance(90, 90, Image.SCALE_SMOOTH));
                row[i][4] = image;
            } else {
                row[i][4] = null;
            }
        }
        TheModelTable model = new TheModelTable(row, columnName);
        table.setModel(model);
    }
}
