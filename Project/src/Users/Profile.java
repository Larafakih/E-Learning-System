package Users;

import Controller.*;
import UI.*;
import java.awt.*;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import project.*;

/**
 *
 * @author Salma
 */
public class Profile {

    private static JPanel sidePanel;
    private static JButton ManageA, ViewA, viewProfileBtn, logoutBtn, deleteaccount;
    private static BufferedImage bufferedImage1, bufferedImage = null;
    private static JLabel usericon, lblUsername;
    private static final String RoleA = "Admin", RoleS = "Student", RoleT = "Teacher";

    public static void action(String Role, int Id, JFrame f) {
        sidePanel = new JPanel();
        f.add(sidePanel, BorderLayout.WEST);
        sidePanel.setLayout(null);
        sidePanel.setBackground(new Color(238, 238, 238));
        Dimension sidePanelSize = new Dimension(180, 720);
        sidePanel.setPreferredSize(sidePanelSize);
        sidePanel.setBorder(BorderFactory.createLineBorder(new Color(57, 138, 185)));

        usericon = new JLabel();
        usericon.setBounds(38, 5, 96, 96);
        usericon.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        sidePanel.add(usericon);

        lblUsername = new TxtLabel();
        lblUsername.setBounds(20, 98, 150, 40);
        lblUsername.setHorizontalAlignment((int) CENTER_ALIGNMENT);
        sidePanel.add(lblUsername);

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (e.getActionCommand()) {
                    case "View":
                        UserController.ViewPicture(bufferedImage1);
                        break;
                    case "Manage":
                        new UserManageAccount(Role, Id, f);
                        f.dispose();
                        break;
                    case "Details":
                        new UserAccountDetails(Role, Id);
                        f.dispose();
                        break;
                    case "Delete":
                        UserDeleteAccount delete = new UserDeleteAccount(Role, Id);
                        if (delete.getInput() == 0) {
                            f.dispose();
                            new Main();
                        }
                        break;
                    case "Logout":
                        UserController.logout(Role, Id);
                        f.dispose();
                        break;
                    default:
                        break;
                }
            }
        };

        bufferedImage = Controller.UserController.SetProfilePicture(Role, Id, bufferedImage, usericon, lblUsername);
        bufferedImage1 = bufferedImage;

        viewProfileBtn = new CircleBtn("View Profile");
        viewProfileBtn.setBounds(30, 150, 130, 28);
        viewProfileBtn.setActionCommand("View");
        viewProfileBtn.addActionListener(actionListener);
        sidePanel.add(viewProfileBtn);

        ManageA = new CircleBtn("Manage Account", "icons/ManageAccount.png", 50, 50);
        ManageA.setBounds(30, 300, 130, 85);
        ManageA.setActionCommand("Manage");
        ManageA.addActionListener(actionListener);
        sidePanel.add(ManageA);

        if (Role == RoleS) {
            ViewA = new CircleBtn("View Account", "icons/student-1.png", 50, 50);
        } else if (Role == RoleA || Role.equals(RoleT)) {
            ViewA = new CircleBtn("View Account", "icons/user-1.png", 50, 50);
        }
        ViewA.setBounds(30, 200, 130, 85);
        ViewA.setActionCommand("Details");
        ViewA.addActionListener(actionListener);
        sidePanel.add(ViewA);

        deleteaccount = new CircleBtn("Delete Account", "icons/delete-account.png", 50, 50);
        deleteaccount.setBounds(30, 500, 130, 85);
        deleteaccount.setActionCommand("Delete");
        deleteaccount.addActionListener(actionListener);
        sidePanel.add(deleteaccount);

        logoutBtn = new CircleBtn("Logout");
        logoutBtn.setBounds(30, 600, 130, 28);
        logoutBtn.addActionListener(actionListener);
        logoutBtn.setActionCommand("Logout");
        sidePanel.add(logoutBtn);
    }
}
