package Student;

import Controller.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import DAO.*;
import UI.*;

public class EnrollCourse implements ActionListener {

    private JLabel title, subjectCbLbl, courseCbLbl, courseDescriptionLbl, Objectivelbl, image;
    private JTextArea courseDescription, courseObjective;
    private JComboBox subjectsCb, coursesCb;
    private JButton enrollBtn, back;
    private JPanel middlePanel;
    private JScrollPane scroll, scroll2;
    private int currentStudentID;
    private JFrame f;
    private final static String RoleS = "Student";

    public EnrollCourse(int c) {
        f = new JFrame("Enroll Course");
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setResizable(false);
        f.setSize(800, 720);
        f.setLocation(30, 10);
        currentStudentID = c;

        title = new TitleLbl("Enroll Course");
        f.add(title, BorderLayout.NORTH);

        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        f.add(middlePanel, BorderLayout.CENTER);

        back = new JButton();
        back.setBounds(690, 0, 100, 40);
        middlePanel.add(back);
        back.addMouseListener(new UI.BackListener(currentStudentID, back, RoleS, f));

        image = new JLabel(img.CreateImageIcon("icons/enroll-form.png", 200, 200));
        image.setBounds(500, 110, 200, 200);
        middlePanel.add(image);

        subjectCbLbl = new TxtLabel("Select Subject");
        subjectCbLbl.setBounds(20, 30, 200, 28);
        middlePanel.add(subjectCbLbl);

        subjectsCb = new JComboBox(SubjectsDao.getSubjects());
        subjectsCb.setSelectedIndex(-1);
        subjectsCb.setBounds(200, 30, 140, 28);
        subjectsCb.addActionListener(this);
        middlePanel.add(subjectsCb);

        courseCbLbl = new TxtLabel("Select Course");
        courseCbLbl.setBounds(20, 80, 200, 28);
        middlePanel.add(courseCbLbl);

        coursesCb = new JComboBox();
        coursesCb.setSelectedIndex(-1);
        coursesCb.setBounds(200, 80, 140, 28);
        coursesCb.addActionListener(this);
        middlePanel.add(coursesCb);

        courseDescriptionLbl = new TxtLabel("Course Description");
        courseDescriptionLbl.setBounds(20, 130, 250, 28);
        middlePanel.add(courseDescriptionLbl);

        courseDescription = new JTextArea();
        courseDescription.setLineWrap(true);
        courseDescription.setWrapStyleWord(true);
        scroll = new JScrollPane(courseDescription);
        scroll.setBounds(100, 160, 300, 140);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        courseDescription.setEditable(false);
        middlePanel.add(scroll);

        Objectivelbl = new TxtLabel("Course Objective");
        Objectivelbl.setBounds(20, 320, 250, 28);
        middlePanel.add(Objectivelbl);

        courseObjective = new JTextArea();
        courseObjective.setLineWrap(true);
        courseObjective.setWrapStyleWord(true);
        scroll2 = new JScrollPane(courseObjective);
        scroll2.setBounds(100, 350, 300, 140);
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        courseObjective.setEditable(false);
        middlePanel.add(scroll2);

        enrollBtn = new CircleBtn("Enroll");
        enrollBtn.setBounds(150, 540, 160, 50);
        enrollBtn.addActionListener(this);
        middlePanel.add(enrollBtn);

        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == subjectsCb) {
            coursesCb.removeAllItems();
            courseDescription.setText(null);
            Object selected = subjectsCb.getSelectedItem();
            String subjectName = selected.toString();
            SubjectController.getCourseBySubject(subjectName, coursesCb);
        } else if (ae.getSource() == coursesCb) {
            Object selectedMain = coursesCb.getSelectedItem();
            if (selectedMain == null) {
                System.out.println("Null Value");
            } else {
                Object selected = coursesCb.getSelectedItem();
                String courseName = selected.toString();
                StudentController.getCourse(courseName, courseDescription, courseObjective);
            }
        } else if (ae.getSource() == enrollBtn) {
            String courseNameStr = coursesCb.getSelectedItem().toString();
            StudentController.EnrollInCourse(courseNameStr, currentStudentID, f);
        }
    }
}
