package UI;

import Admin.*;
import Student.*;
import Teacher.*;
import Users.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import javax.swing.*;

/**
 *
 * @author Salma
 */
public class BackListener extends MouseAdapter {

    private JFrame frame;
    private JButton back;
    private int Id;
    private String role;
    private static final String RoleA = "Admin", RoleS = "Student", RoleT = "Teacher";
    private static final String viewquiz = "ViewMyQuizzes", Forum = "Forum", studyCourse = "StudyCourse";
    private static final String SpecificCourse = "SpecificCourse", ViewQuiz = "ViewQuizStudent", view = "ViewQ";
    private static final String viewTeacherquiz = "ViewQuizTeacher";

    public BackListener(int ID, JButton b, String Role, JFrame f) {
        Id = ID;
        frame = f;
        role = Role;
        back = b;
        back.setBackground(new Color(238, 238, 238));
        back.setText("Back");
        back.setIcon(img.CreateImageIcon("icons/back.png", 30, 30));
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonMouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                //ButtonMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                //ButtonMouseExited(evt);
            }
        });
    }

    private void ButtonMouseClicked(java.awt.event.MouseEvent evt) {
        if (role.equals(RoleS)) {
            StudentPage st = new StudentPage(Id);
            frame.dispose();
        } else if (role.equals(RoleA)) {
            AdminPage a = new AdminPage(Id);
            frame.dispose();
        } else if (role.equals(RoleT)) {
            TeacherPage t = new TeacherPage(Id);
            frame.dispose();
        } else if (role.equals(viewquiz)) {
            ViewMyQuizzes q = new ViewMyQuizzes(Id);
            frame.dispose();
        } else if (role.equals(Forum)) {
            Forum fr = new Forum(Id);
            frame.dispose();
        } else if ((role.equals(studyCourse)) || (role.equals(SpecificCourse)) || (role.equals(ViewQuiz))) {
            StudyCourse c = new StudyCourse(Id);
            frame.dispose();
        } else if (role.equals(view)) {
            new ViewQuizzes(Id);
            frame.dispose();
        } else if (role.equals(viewTeacherquiz)) {
            new ViewAddedQuizzes(Id);
            frame.dispose();
        }
    }
}
