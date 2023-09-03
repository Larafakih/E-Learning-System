package Student;

import Controller.*;
import UI.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import DAO.*;
import Model.*;

public class StudyCourse implements ActionListener {

    private JLabel title1, courseCbLbl;
    private JComboBox coursesCb;
    private JPanel middlePanel1;
    private int currentStudentID;
    private JButton cancel1, back1;
    private JLabel mainScreenLbl;
    private Color oldColor;
    private static final String RoleS = "Student";
    private JFrame f;
    //for specific course frame:
    private JLabel title, courseContentLbl, desLbl, ObjLbl, TeID, Subject;
    private JTextArea courseContent, desContent, objContent;
    private JPanel middlePanel;
    private JTextField teachername, SubjectName;
    private JScrollPane scroll, scroll2, scroll3;
    private JButton cancelSpecific, back;
    private String coursename, course_Content, des, obj;
    private int CourseID, TeacherId, subjectId;
    private static JFrame fr;
    private final static String Role = "StudyCourse";

    public StudyCourse(int c) {
        f = new JFrame("Study Course");
        f.setIconImage(img.SystemIcon());
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setResizable(false);
        f.setSize(700, 700);
        f.setLocation(10, 10);
        currentStudentID = c;

        title1 = new TitleLbl("Study Course");
        f.add(title1, BorderLayout.NORTH);

        middlePanel1 = new JPanel();
        middlePanel1.setLayout(null);
        f.add(middlePanel1, BorderLayout.CENTER);

        courseCbLbl = new TxtLabel("Select Course");
        courseCbLbl.setBounds(40, 80, 200, 28);
        middlePanel1.add(courseCbLbl);

        coursesCb = new JComboBox(CoursesDao.getEnrolledCourses(currentStudentID));
        coursesCb.setSelectedIndex(-1);
        coursesCb.setBounds(200, 80, 240, 28);
        coursesCb.addActionListener(this);
        middlePanel1.add(coursesCb);

        cancel1 = new CircleBtn();
        cancel1.setText("Cancel");
        cancel1.setBounds(220, 190, 200, 40);
        cancel1.addActionListener(this);
        middlePanel1.add(cancel1);

        mainScreenLbl = new JLabel(img.CreateImageIcon("icons/StudyCourse.png", 320, 320));
        mainScreenLbl.setBounds(260, 260, 320, 320);
        middlePanel1.add(mainScreenLbl);

        back1 = new JButton();
        back1.setBounds(590, 0, 100, 40);
        middlePanel1.add(back1);
        back1.addMouseListener(new UI.BackListener(currentStudentID, back1, RoleS, f));

        oldColor = back1.getForeground();
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == coursesCb) {
            Object selected = coursesCb.getSelectedItem();
            String courseName = selected.toString();
            SpecificCourse(currentStudentID, courseName);
            f.dispose();
        } else if (ae.getSource() == cancel1) {
            f.dispose();
            new StudentPage(currentStudentID);
        } else if (ae.getSource() == cancelSpecific) {
            fr.dispose();
            new StudyCourse(currentStudentID);
        }
    }

    public void SpecificCourse(int c, String courseName) {
        fr = new JFrame("Specific Course");
        fr.setIconImage(img.SystemIcon());
        fr.setLayout(new BorderLayout());
        fr.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fr.setResizable(false);
        fr.setSize(1280, 720);
        fr.setLocation(10, 10);
        currentStudentID = c;
        coursename = courseName;

        title = new TitleLbl(coursename);
        fr.add(title, BorderLayout.NORTH);

        middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        fr.add(middlePanel, BorderLayout.CENTER);

        JScrollPane scrollpane = new JScrollPane(middlePanel);
        scrollpane.setBounds(0, 20, 1280, 700);
        scrollpane.setBackground(Color.white);
        scrollpane.setViewportView(middlePanel);
        fr.add(scrollpane);
        scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        Courses cours = CoursesDao.getCoursebyName(coursename);
        course_Content = cours.getContent();
        des = cours.getDescription();
        obj = cours.getObjective();
        TeacherId = cours.getTeacherID();
        CourseID = cours.getCourseID();
        subjectId = cours.getSubjectID();

        back = new JButton();
        back.setBounds(1000, 0, 100, 40);
        middlePanel.add(back);
        back.addMouseListener(new UI.BackListener(currentStudentID, back, Role, fr));

        LoadData();
        JPanel PanelButton = new JPanel();
        PanelButton.setLayout(new FlowLayout(FlowLayout.LEFT, 100, 10));
        middlePanel.add(PanelButton);

        StudentController.DownloadBook(CourseID, PanelButton);
        StudentController.DoQuiz(coursename, PanelButton, currentStudentID, fr);

        cancelSpecific = new CircleBtn("Cancel");
        cancelSpecific.addActionListener(this);
        PanelButton.add(cancelSpecific);

        fr.setVisible(true);
    }

    public static String getpath() {
        String path = "";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(fr);
        if (x == JFileChooser.APPROVE_OPTION) {
            path = j.getSelectedFile().getPath();
        }
        path = path.replaceAll("\\\\", "\\\\\\\\");
        path = path + "\\\\";
        return path;
    }

    public void LoadData() {
        JPanel teachernameP = new JPanel();
        teachernameP.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        middlePanel.add(teachernameP);

        Teacher t = TeacherDao.SelectTeacherById(TeacherId);

        TeID = new TxtLabel("Teacher Name: ");
        TeID.setPreferredSize(new Dimension(210, 28));
        teachernameP.add(TeID);

        teachername = new JTextField("" + t.getFullName());
        teachername.setEditable(false);
        teachername.setPreferredSize(new Dimension(350, 28));
        teachernameP.add(teachername);

        JPanel SubjectP = new JPanel();
        SubjectP.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        middlePanel.add(SubjectP);

        String name = SubjectsDao.getSubjectsname(subjectId);

        Subject = new TxtLabel("Subject Name: ");
        Subject.setPreferredSize(new Dimension(210, 28));
        SubjectP.add(Subject);

        SubjectName = new JTextField("" + name);
        SubjectName.setEditable(false);
        SubjectName.setPreferredSize(new Dimension(210, 40));
        SubjectP.add(SubjectName);

        JPanel courseDescriptionP = new JPanel();
        courseDescriptionP.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        middlePanel.add(courseDescriptionP);

        desLbl = new TxtLabel("Course Description: ");
        desLbl.setPreferredSize(new Dimension(210, 28));
        courseDescriptionP.add(desLbl);

        desContent = new JTextArea();
        desContent.setText("" + des);
        desContent.setLineWrap(true);
        desContent.setWrapStyleWord(true);
        scroll2 = new JScrollPane(desContent);
        scroll2.setPreferredSize(new Dimension(600, 200));
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        desContent.setEditable(false);
        courseDescriptionP.add(scroll2);

        JPanel courseobjectivesP = new JPanel();
        courseobjectivesP.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        middlePanel.add(courseobjectivesP);

        ObjLbl = new TxtLabel("Course Objective:");
        ObjLbl.setPreferredSize(new Dimension(210, 28));
        courseobjectivesP.add(ObjLbl);

        objContent = new JTextArea();
        objContent.setText("" + obj);
        objContent.setLineWrap(true);
        objContent.setWrapStyleWord(true);
        scroll3 = new JScrollPane(objContent);
        scroll3.setPreferredSize(new Dimension(600, 200));
        scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        desContent.setEditable(false);
        courseobjectivesP.add(scroll3);

        JPanel courseContentP = new JPanel();
        courseContentP.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        middlePanel.add(courseContentP);

        courseContentLbl = new TxtLabel("Course Content:");
        courseContentLbl.setPreferredSize(new Dimension(210, 28));

        courseContentP.add(courseContentLbl);

        courseContent = new JTextArea();
        courseContent.setLineWrap(true);
        courseContent.setWrapStyleWord(true);
        scroll = new JScrollPane(courseContent);
        scroll.setPreferredSize(new Dimension(600, 200));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        courseContent.setEditable(false);
        courseContentP.add(scroll);
        courseContent.setText(course_Content);
    }
}
