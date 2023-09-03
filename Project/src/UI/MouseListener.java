package UI;

import Admin.*;
import Student.*;
import Teacher.*;
import Users.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import project.AboutUs;
import project.LoginPage;

/**
 *
 * @author Salma
 */
public class MouseListener extends MouseAdapter {

    JFrame frame;
    JLabel label;
    Color oldColor;
    int Id;
    String role;
    static final String RoleA = "Admin";
    static final String RoleS = "Student";
    static final String RoleT = "Teacher";
    static final String CreateAccount = "CreateAccount";
    static final String AboutUs = "AboutUs";

    public MouseListener(int ID, JLabel l, Color color, String Role, JFrame f) {
        Id = ID;
        label = l;
        oldColor = color;
        frame = f;
        role = Role;
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                MouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                MouseExited(evt);
            }
        });

    }

    private void MouseClicked(java.awt.event.MouseEvent evt) {
        if (role.equals(RoleS)) {
            StudentPage st = new StudentPage(Id);
            frame.dispose();
        } else if (role.equals(RoleA)) {
            AdminPage a = new AdminPage(Id);
            frame.dispose();
        } else if (role.equals(RoleT)) {
            TeacherPage t = new TeacherPage(Id);
            frame.dispose();
        } else if (role.equals(CreateAccount)) {
            UserCreateAccount a = new UserCreateAccount(RoleS);
            a.AddButton.setText("Sign Up");
           // a.Title.setText("Sign Up");
            //a.setVisible(true);
          //  frame.dispose();
        }
        else if (role.equals(AboutUs)) {
            AboutUs a = new AboutUs();
            a.setVisible(true);
            //frame.dispose();
        }
    }

    private void MouseEntered(java.awt.event.MouseEvent evt) {
        label.setForeground(new Color(252, 79, 79));
    }

    private void MouseExited(java.awt.event.MouseEvent evt) {
        label.setForeground(oldColor);
    }
}
