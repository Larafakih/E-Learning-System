package Teacher;

import UI.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Users.*;

public class TeacherPage implements ActionListener {

    private JPanel rightPanel, buttonsPanel, panel;
    private JButton AddCourse, ViewMyStudents, ViewMyCourses, UpdateCourse, viewScore, addQuizBtn, addBookBtn;
    private JButton clonequiz;
    private int currentTeacherID;
    private JLabel mainTitle, clock, Title2;
    private String Role = "Teacher";
    private JFrame f;

    public TeacherPage(int c) {
        f = new JFrame("Teacher Module");
        f.setIconImage(img.SystemIcon());
        f.setLayout(new BorderLayout());
        f.setSize(1280, 720);
        f.setLocation(10, 10);
        currentTeacherID = c;

        Profile.action(Role, currentTeacherID, f);

        rightPanel = new JPanel(new BorderLayout());
        f.add(rightPanel, BorderLayout.CENTER);

        mainTitle = new TitleLbl("Teacher Module");
        rightPanel.add(mainTitle, BorderLayout.NORTH);

        clock = new JLabel();
        SystemTime.clock(clock);
        rightPanel.add(clock, BorderLayout.SOUTH);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(null);
        rightPanel.add(buttonsPanel, BorderLayout.CENTER);

        Title2 = new JLabel("Teacher Operations:");
        Title2.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        Title2.setForeground(new Color(0, 142, 204));
        Title2.setHorizontalAlignment(JLabel.LEFT);
        Title2.setBounds(20, 60, 250, 50);
        buttonsPanel.add(Title2);

        AddCourse = new OperationBtn("Add Course", "icons/addSubject.png", 50, 50);
        AddCourse.setBounds(250, 210, 145, 90);
        AddCourse.addActionListener((ActionListener) this);
        buttonsPanel.add(AddCourse);

        addBookBtn = new OperationBtn("Add Book", "icons/Addbook.png", 50, 50);
        addBookBtn.setBounds(410, 210, 145, 90);
        addBookBtn.addActionListener((ActionListener) this);
        buttonsPanel.add(addBookBtn);

        UpdateCourse = new OperationBtn("Update Courses", "icons/updateCourse.png", 50, 50);
        UpdateCourse.setBounds(570, 210, 145, 90);
        UpdateCourse.addActionListener((ActionListener) this);
        buttonsPanel.add(UpdateCourse);

        ViewMyStudents = new OperationBtn("View My Students", "icons/study-group.png", 50, 50);
        ViewMyStudents.setBounds(250, 320, 145, 90);
        ViewMyStudents.addActionListener((ActionListener) this);
        buttonsPanel.add(ViewMyStudents);

        ViewMyCourses = new OperationBtn("View My Courses", "icons/View-Courses.png", 50, 50);
        ViewMyCourses.setBounds(410, 320, 145, 90);
        ViewMyCourses.addActionListener((ActionListener) this);
        buttonsPanel.add(ViewMyCourses);

        clonequiz = new OperationBtn("Make Clone Quiz", "icons/edit.png", 50, 50);
        clonequiz.setBounds(570, 320, 145, 90);
        clonequiz.addActionListener((ActionListener) this);
        buttonsPanel.add(clonequiz);

        addQuizBtn = new OperationBtn("Add Quiz", "icons/Quiz.png", 50, 50);
        addQuizBtn.setBounds(250, 430, 145, 90);
        addQuizBtn.addActionListener((ActionListener) this);
        buttonsPanel.add(addQuizBtn);

        viewScore = new OperationBtn("View Scores", "icons/grades.png", 50, 50);
        viewScore.setBounds(410, 430, 145, 90);
        viewScore.addActionListener((ActionListener) this);
        buttonsPanel.add(viewScore);

        f.setResizable(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == AddCourse) {
            new AddCourse(currentTeacherID);
            f.dispose();
        } else if (ae.getSource() == ViewMyStudents) {
            new ViewMyStudents(currentTeacherID);
            f.dispose();
        } else if (ae.getSource() == ViewMyCourses) {
            new ViewMyCourses(currentTeacherID);
            f.dispose();
        } else if (ae.getSource() == UpdateCourse) {
            new UpdateCourses(currentTeacherID);
            f.dispose();
        } else if (ae.getSource() == addQuizBtn) {
            new AddQuiz(currentTeacherID);
            f.dispose();
        } else if (ae.getSource() == addBookBtn) {
            new AddBook(currentTeacherID);
        } else if (ae.getSource() == viewScore) {
            new ViewMyStudentScore(currentTeacherID);
            f.dispose();
        } else if (ae.getSource() == clonequiz) {
            new ViewMyQuizzes(currentTeacherID);
            f.dispose();
        }
    }
}
