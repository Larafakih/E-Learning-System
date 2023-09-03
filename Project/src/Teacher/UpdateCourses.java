package Teacher;

import DAO.*;
import Model.*;
import UI.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UpdateCourses extends JFrame implements ActionListener {

    private JLabel title;
    private JPanel middlePanel;
    private JComboBox courseCb;
    private JTextArea courseDescription, courseContent, courseObj;
    private JScrollPane scroll, scroll2, scroll3;
    private JButton updateBtn, back;
    private JLabel courseDescriptionLbl, courseContentLbl, courseobjectivesLbl, cbLbl;
    private String courseName;
    private int currentTeacherID;
    private static final String RoleT = "Teacher";

    public UpdateCourses(int c) {
        super("Update Course");
        setLayout(new BorderLayout());
        currentTeacherID = c;

        title = new TitleLbl("Update Course");
        add(title, BorderLayout.NORTH);

        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        add(middlePanel, BorderLayout.CENTER);

        back = new JButton();
        back.setBounds(880, 0, 100, 40);
        middlePanel.add(back);
        back.addMouseListener(new UI.BackListener(currentTeacherID, back, RoleT, this));

        cbLbl = new TxtLabel("Select Course: ");
        cbLbl.setBounds(80, 25, 300, 28);
        middlePanel.add(cbLbl);

        courseCb = new JComboBox(CoursesDao.getMyCourses(currentTeacherID));
        courseCb.setSelectedIndex(-1);
        courseCb.setBounds(250, 30, 350, 28);
        courseCb.addActionListener(this);
        middlePanel.add(courseCb);

        courseDescriptionLbl = new TxtLabel("Course Description");
        courseDescriptionLbl.setBounds(20, 125, 190, 28);
        middlePanel.add(courseDescriptionLbl);

        courseDescription = new JTextArea();
        courseDescription.setLineWrap(true);
        courseDescription.setWrapStyleWord(true);
        scroll = new JScrollPane(courseDescription);
        scroll.setBounds(220, 110, 460, 100);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        courseDescription.setEditable(true);
        middlePanel.add(scroll);

        courseobjectivesLbl = new TxtLabel("Course Objectives");
        courseobjectivesLbl.setBounds(20, 230, 190, 28);
        middlePanel.add(courseobjectivesLbl);

        courseObj = new JTextArea();
        courseObj.setLineWrap(true);
        courseObj.setWrapStyleWord(true);
        scroll3 = new JScrollPane(courseObj);
        scroll3.setBounds(220, 230, 460, 100);
        scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        courseObj.setEditable(true);
        middlePanel.add(scroll3);

        courseContentLbl = new TxtLabel("Course Content");
        courseContentLbl.setBounds(20, 360, 190, 28);
        middlePanel.add(courseContentLbl);

        courseContent = new JTextArea();
        courseContent.setLineWrap(true);
        courseContent.setWrapStyleWord(true);
        scroll2 = new JScrollPane(courseContent);
        scroll2.setBounds(220, 340, 600, 200);
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        courseContent.setEditable(true);
        middlePanel.add(scroll2);

        updateBtn = new CircleBtn("Update");
        updateBtn.addActionListener(this);
        updateBtn.setBounds(300, 550, 180, 50);
        middlePanel.add(updateBtn);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setSize(1280, 720);
        setLocation(10, 10);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == courseCb) {
            Object selected = courseCb.getSelectedItem();
            courseName = selected.toString();
            Courses co = CoursesDao.getCoursebyNameandTeacherId(courseName, currentTeacherID);
            courseDescription.setText(co.getDescription());
            courseContent.setText(co.getContent());
            courseObj.setText(co.getObjective());

        } else if (e.getSource() == updateBtn) {
            Object selected = courseCb.getSelectedItem();
            courseName = selected.toString();
            String courseDescriptionStr = courseDescription.getText();
            String courseContentStr = courseContent.getText();
            String objectivecourse = courseObj.getText();
            Courses cu = new Courses(courseName, courseDescriptionStr, objectivecourse, courseContentStr, currentTeacherID);
            int x = CoursesDao.updateCourse(cu);
            if (x == 0) {
                System.out.println("Got some error");
            } else {
                JOptionPane.showMessageDialog(null, "Course Updated Successfully");
                setVisible(false);
                new TeacherPage(currentTeacherID);
            }

        }
    }
}
