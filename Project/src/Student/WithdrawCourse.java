package Student;

import Controller.*;
import java.awt.*;
import java.awt.event.*;
import DAO.*;
import UI.*;
import javax.swing.*;

public class WithdrawCourse extends JFrame implements ActionListener {

    private JLabel title, courseCbLbl, image, attention, notation, notation1;
    private JComboBox courseCb;
    private JButton withdrawBtn, back;
    private JPanel middlePanel;
    private int currentStudentID;
    private JFrame f;
    private static final String RoleS = "Student";

    public WithdrawCourse(int c) {
        f = new JFrame("Withdraw Course");
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setResizable(false);
        f.setSize(720, 500);
        f.setLocation(10, 10);
        currentStudentID = c;

        title = new TitleLbl("Withdraw Course");
        f.add(title, BorderLayout.NORTH);

        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        f.add(middlePanel, BorderLayout.CENTER);

        back = new JButton();
        back.setBounds(590, 0, 100, 40);
        middlePanel.add(back);
        back.addMouseListener(new UI.BackListener(currentStudentID, back, RoleS, f));

        courseCbLbl = new TxtLabel("Select Course");
        courseCbLbl.setBounds(40, 50, 200, 28);
        middlePanel.add(courseCbLbl);

        courseCb = new JComboBox(CoursesDao.getEnrolledCourses(currentStudentID));
        courseCb.setSelectedIndex(-1);
        courseCb.setBounds(200, 50, 200, 28);
        courseCb.addActionListener(this);
        middlePanel.add(courseCb);

        image = new JLabel(img.CreateImageIcon("icons/withdraw.png", 200, 200));
        image.setBounds(450, 50, 200, 200);
        middlePanel.add(image);

        withdrawBtn = new CircleBtn("Withdraw");
        withdrawBtn.addActionListener(this);
        withdrawBtn.setBounds(200, 170, 200, 40);
        middlePanel.add(withdrawBtn, BorderLayout.SOUTH);

        attention = new JLabel(img.CreateImageIcon("icons/warning.png", 50, 50));
        attention.setBounds(0, 300, 100, 100);
        middlePanel.add(attention);

        notation = new JLabel("If you withdraw from a course, you lose access to content and quizzes of the course,");
        notation.setBounds(90, 330, 1000, 50);
        notation.setForeground(new Color(252, 79, 79));
        middlePanel.add(notation);

        notation1 = new JLabel(" and you lose your scores in quizzes belonging to this course.");
        notation1.setBounds(90, 360, 1000, 50);
        notation1.setForeground(new Color(252, 79, 79));
        middlePanel.add(notation1);

        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == withdrawBtn) {
            Object selected = courseCb.getSelectedItem();
            String course_Name = selected.toString();
            StudentController.withdrawCourse(course_Name, currentStudentID, f);
        }
    }
}
