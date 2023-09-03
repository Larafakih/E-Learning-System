package Users;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import UI.*;

/**
 *
 * @author Salma
 */
public class UserManageAccount extends JFrame implements ActionListener {

    private JPanel panel, panel2;
    private JLabel title, image;
    private JButton pass, name, email, profile, exit, back;
    private int UserID;
    private String UserRole;
    private JFrame frame1;

    public UserManageAccount(String Role, int ID, JFrame frame) {
        super("Manage Account");
        setIconImage(img.SystemIcon());
        setSize(700, 700);
        setLocation(10, 10);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        UserRole = Role;
        UserID = ID;
        frame1 = frame;
        setLayout(new BorderLayout());

        title = new TitleLbl("Manage Account");
        add(title, BorderLayout.NORTH);

        panel = new JPanel();
        panel.setLayout(null);
        add(panel);

        back = new JButton();
        back.setBounds(400, 0, 100, 40);
        panel.add(back);
        back.addMouseListener(new UI.BackListener(ID, back, UserRole, this));

        image = new JLabel(img.CreateImageIcon("icons/accountManager.png", 200, 200));
        image.setBounds(400, 400, 200, 200);
        panel.add(image);

        pass = new CircleBtn("Change Password");
        pass.setBounds(200, 100, 200, 30);
        pass.addActionListener((ActionListener) this);

        name = new CircleBtn("Change Name");
        name.setBounds(200, 150, 200, 30);
        name.addActionListener((ActionListener) this);

        email = new CircleBtn("Change Email");
        email.setBounds(200, 200, 200, 30);
        email.addActionListener((ActionListener) this);

        profile = new CircleBtn("Change Profile");
        profile.setBounds(200, 250, 200, 30);
        profile.addActionListener((ActionListener) this);

        exit = new CircleBtn("Exit");
        exit.setBounds(200, 300, 200, 30);
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        panel.add(pass);
        panel.add(name);
        panel.add(email);
        panel.add(profile);
        panel.add(exit);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pass) {
            new UserChangePassword(UserRole, UserID);
         //   this.dispose();
        } else if (ae.getSource() == name) {
            new UserChangeName(UserRole, UserID, frame1);
            this.dispose();
        } else if (ae.getSource() == email) {
            new UserChangeEmail(UserRole, UserID);
        } else if (ae.getSource() == profile) {
            Controller.UserController.ChangePicture(UserRole, UserID, frame1);
            this.dispose();
        }       
    }
}
