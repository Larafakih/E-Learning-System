package Admin;

import UI.*;
import Users.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AdminPage implements ActionListener {

    private JPanel rightPanel, buttonsPanel, DLPanel;
    private JLabel label, backgroundPic;
    private JButton AddSubject, DeleteSubject, ViewStudents, ViewTeachers, ViewCourses;
    private JButton AddNewAdmin, AddNewStudent, AddNewTeacher, ViewScores, ViewQuiz;
    private JLabel usericon, lblUsername, Title2, clock, mainTitle;
    private int currentAdminID;
    private static final String RoleA = "Admin",RoleS = "Student",RoleT = "Teacher";
    private JFrame f;

    public AdminPage(int c) {
        f = new JFrame("Admin Module");
        f.setSize(1280, 720);
        f.setLocation(10, 10);
        f.setIconImage(img.SystemIcon());
        f.setLayout(new BorderLayout());
        currentAdminID = c;

        Profile.action(RoleA, currentAdminID, f);

        rightPanel = new JPanel(new BorderLayout());
        f.add(rightPanel, BorderLayout.CENTER);
        mainTitle = new TitleLbl("Admin Module");
        rightPanel.add(mainTitle, BorderLayout.NORTH);

        clock = new JLabel();
        SystemTime.clock(clock);
        rightPanel.add(clock, BorderLayout.SOUTH);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(null);
        rightPanel.add(buttonsPanel, BorderLayout.CENTER);

        Title2 = new JLabel("Admin Operations:");
        Title2.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        Title2.setForeground(new Color(0, 142, 204));
        Title2.setHorizontalAlignment(JLabel.LEFT);
        Title2.setBounds(20, 60, 250, 50);
        buttonsPanel.add(Title2);

        AddSubject = new OperationBtn("Add Subject", "icons/addSubject.png", 50, 50);
        AddSubject.setBounds(250, 120, 140, 90);
        AddSubject.addActionListener((ActionListener) this);
        buttonsPanel.add(AddSubject);

        DeleteSubject = new OperationBtn("Delete Subject", "icons/deleteSubject.png", 50, 50);
        DeleteSubject.setBounds(410, 120, 140, 90);
        DeleteSubject.addActionListener((ActionListener) this);
        buttonsPanel.add(DeleteSubject);

        ViewStudents = new OperationBtn("View Students", "icons/students.png", 50, 50);
        ViewStudents.setBounds(250, 220, 140, 90);
        ViewStudents.addActionListener((ActionListener) this);
        buttonsPanel.add(ViewStudents);

        ViewTeachers = new OperationBtn("View Teachers", "icons/Teachers.png", 50, 50);
        ViewTeachers.setBounds(410, 220, 140, 90);
        ViewTeachers.addActionListener((ActionListener) this);
        buttonsPanel.add(ViewTeachers);

        ViewCourses = new OperationBtn("View Courses", "icons/View-Courses.png", 50, 50);
        ViewCourses.setBounds(570, 220, 140, 90);
        ViewCourses.addActionListener((ActionListener) this);
        buttonsPanel.add(ViewCourses);

        AddNewAdmin = new OperationBtn("Add New Admin", "icons/add-admin.png", 50, 50);
        AddNewAdmin.setBounds(570, 320, 140, 90);
        AddNewAdmin.addActionListener((ActionListener) this);
        buttonsPanel.add(AddNewAdmin);

        AddNewStudent = new OperationBtn("Add New Student", "icons/add-students.png", 50, 50);
        AddNewStudent.setBounds(250, 320, 140, 90);
        AddNewStudent.addActionListener((ActionListener) this);
        buttonsPanel.add(AddNewStudent);

        AddNewTeacher = new OperationBtn("Add New Teacher", "icons/add-Teachers.png", 50, 50);
        AddNewTeacher.setBounds(410, 320, 140, 90);
        AddNewTeacher.addActionListener((ActionListener) this);
        buttonsPanel.add(AddNewTeacher);

        ViewQuiz = new OperationBtn("View Quiz", "icons/quiz-1.png", 50, 50);
        ViewQuiz.setBounds(250, 420, 140, 90);
        ViewQuiz.addActionListener((ActionListener) this);
        buttonsPanel.add(ViewQuiz);

        ViewScores = new OperationBtn("View Scores", "icons/scores.png", 50, 50);
        ViewScores.setBounds(410, 420, 140, 90);
        ViewScores.addActionListener((ActionListener) this);
        buttonsPanel.add(ViewScores);

        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == AddSubject) {
            new AddSubject(currentAdminID);
        } else if (ae.getSource() == DeleteSubject) {
            new DeleteSubject();
        } else if (ae.getSource() == ViewStudents) {
            new ViewStudents(currentAdminID);
            f.dispose();
        } else if (ae.getSource() == ViewTeachers) {
            new ViewTeachers(currentAdminID);
            f.dispose();
        } else if (ae.getSource() == ViewCourses) {
            new ViewCourse(currentAdminID);
            f.dispose();
        } else if (ae.getSource() == AddNewAdmin) {
            new UserCreateAccount(RoleA);
         //   f.dispose();
        } else if (ae.getSource() == AddNewStudent) {
            new UserCreateAccount(RoleS);
         //   f.dispose();
        } else if (ae.getSource() == AddNewTeacher) {
            new UserCreateAccount(RoleT);
          //  f.dispose();
        } else if (ae.getSource() == ViewQuiz) {
            new ViewQuizzes(currentAdminID);
            f.dispose();
        } else if (ae.getSource() == ViewScores) {
            new ViewAllStudentScore(currentAdminID);
            f.dispose();
        }
    }
}
