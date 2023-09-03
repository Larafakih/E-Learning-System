package UI;

import Admin.*;
import Student.*;
import Teacher.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import javax.swing.*;
import project.*;
import Controller.*;

/**
 *
 * @author Salma
 */
public class LogoutListener extends MouseAdapter {

    JFrame frame;
    JLabel label;
    Color oldColor;
    int Id;
    static final String RoleA = "Admin";
    static final String RoleS = "Student";
    static final String RoleT = "Teacher";
    String role;

    public LogoutListener(int ID, JLabel l, Color color, String Role, JFrame f) {
        Id = ID;
        label = l;
        oldColor = color;
        frame = f;
        role = Role;

        label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LogoutMouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LogoutMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                LogoutMouseExited(evt);
            }
        });
    }

    private void LogoutMouseClicked(java.awt.event.MouseEvent evt) {
        UserController.logout(role, Id);
        frame.dispose();
        new LoginPage();
    }

    private void LogoutMouseEntered(java.awt.event.MouseEvent evt) {
        label.setForeground(new Color(252, 79, 79));
    }

    private void LogoutMouseExited(java.awt.event.MouseEvent evt) {
        label.setForeground(oldColor);
    }
}
