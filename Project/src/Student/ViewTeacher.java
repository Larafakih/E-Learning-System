package Student;

import java.awt.*;
import java.awt.event.*;
import DAO.*;
import UI.TitleLbl;
import UI.TxtLabel;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 *
 * @author Salma
 */
public class ViewTeacher extends JFrame implements ActionListener {

    JLabel title, courseCbLbl;
    JComboBox coursesCb;
    JPanel middlePanel;
    JScrollPane scroll;
    JTable table;
    DefaultTableModel model;
    JButton back;
    int currentStudentID;
    String teacherData[][];
    String[] columnNames = {"Name", "Email"};
    String RoleS = "Student";

    public ViewTeacher(int c) {
        super("View Teachers");
        setLayout(new BorderLayout());
        currentStudentID = c;
        title = new TitleLbl("Teachers");
        add(title, BorderLayout.NORTH);

        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        add(middlePanel, BorderLayout.CENTER);

        back = new JButton();
        back.setBounds(490, 0, 100, 40);
        middlePanel.add(back);
        back.addMouseListener(new UI.BackListener(currentStudentID, back, RoleS, this));

        courseCbLbl = new TxtLabel("Select Course");
        courseCbLbl.setBounds(40, 50, 200, 28);
        middlePanel.add(courseCbLbl);

        coursesCb = new JComboBox(CoursesDao.getEnrolledCourses(currentStudentID));
        coursesCb.setSelectedIndex(-1);
        coursesCb.setBounds(200, 50, 350, 28);
        coursesCb.addActionListener(this);
        middlePanel.add(coursesCb);

        table = new UI.Table(20, columnNames);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 110, 550, 245);
        middlePanel.add(pane);

        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(600, 600);
        setLocation(10, 10);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == coursesCb) {
            model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            model.setColumnCount(0);
            Object selected = coursesCb.getSelectedItem();
            String courseName = selected.toString();
            teacherData = CoursesDao.SelectTeacherOfCourse(courseName);
            for (int c = 0; c < columnNames.length; c++) {
                model.addColumn(columnNames[c]);
            }
            model.addRow(teacherData[0]);
        }
    }

}
