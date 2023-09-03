package Users;

import UI.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import Model.*;
import Student.*;
import Admin.*;
import Teacher.*;

/**
 *
 * @author Salma
 */
public class UserAccountDetails extends JFrame implements ActionListener {

    private JPanel headerPanel, contentPane;
    private JLabel lblDetails, lblName, lblEmailAddress, lblphone, lblPassword, lblGender, image;
    private JTextField FullName, Email, Phone, PasswordField, Gender;
    private JButton OkButton;
    private int UserID;
    private User u;
    private String Userrole;
    private static final String RoleA = "Admin", RoleS = "Student", RoleT = "Teacher",ma = "Male", fe = "Female";

    public UserAccountDetails(String role, int id) {
        super("Account Details");
        UserID = id;
        Userrole = role;
        setIconImage(img.SystemIcon());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1110, 720);
        setLocation(10, 10);
        setLayout(new BorderLayout());
        u = Controller.UserController.getAccountDetails(role, UserID);
        setResizable(false);

        headerPanel = new JPanel(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);

        lblDetails = new TitleLbl(role + " Details");
        lblDetails.setBounds(362, 52, 325, 50);
        headerPanel.add(lblDetails);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        add(contentPane, BorderLayout.CENTER);

        lblName = new TxtLabel("Full Name");
        lblName.setBounds(20, 50, 180, 50);
        contentPane.add(lblName);

        lblEmailAddress = new TxtLabel("Email");
        lblEmailAddress.setBounds(20, 150, 180, 50);
        contentPane.add(lblEmailAddress);

        lblphone = new TxtLabel("Phone");
        lblphone.setBounds(20, 250, 180, 50);
        contentPane.add(lblphone);

        lblPassword = new TxtLabel("Password");
        lblPassword.setBounds(20, 350, 180, 24);
        contentPane.add(lblPassword);

        lblGender = new TxtLabel("Gender");
        lblGender.setBounds(20, 450, 180, 24);
        contentPane.add(lblGender);
        Font font = new Font("Tahoma", Font.PLAIN, 32);

        FullName = new JTextField("" + u.getFullName());
        FullName.setFont(font);
        FullName.setBounds(214, 55, 400, 50);
        FullName.setEditable(false);
        contentPane.add(FullName);

        Email = new JTextField(u.getEmail());
        Email.setFont(font);
        Email.setBounds(214, 150, 400, 50);
        Email.setEditable(false);
        contentPane.add(Email);

        Phone = new JTextField(u.getPhone());
        Phone.setFont(font);
        Phone.setBounds(214, 250, 400, 50);
        Phone.setEditable(false);
        contentPane.add(Phone);

        PasswordField = new JTextField(u.getPassword());
        PasswordField.setFont(font);
        PasswordField.setBounds(214, 350, 400, 50);
        PasswordField.setEditable(false);
        contentPane.add(PasswordField);

        Gender = new JTextField(u.getGender());
        Gender.setFont(font);
        Gender.setBounds(214, 450, 400, 50);
        Gender.setEditable(false);
        contentPane.add(Gender);

        OkButton = new CircleBtn("Ok");
        OkButton.setBounds(430, 550, 228, 60);
        OkButton.addActionListener((ActionListener) this);
        contentPane.add(OkButton);
        image = new JLabel();
        image.setBounds(707, 150, 500, 500);
        if (Userrole.equals(RoleS)) {
            if (u.getGender().equals(ma)) {
                image.setIcon(img.CreateImageIcon("icons/male.png", 300, 300));
            } else if (u.getGender().equals(fe)) {
                image.setIcon(img.CreateImageIcon("icons/female.png", 300, 300));
            }
        } else if (Userrole.equals(RoleA) || Userrole.equals(RoleT)) {
            if (u.getGender().equals(ma)) {
                image.setIcon(img.CreateImageIcon("icons/male-1.png", 300, 300));
            } else if (u.getGender().equals(fe)) {
                image.setIcon(img.CreateImageIcon("icons/female-1.png", 300, 300));
            }
        }
        contentPane.add(image);
        image.setBounds(707, 200, 500, 500);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == OkButton) {
            dispose();
            if (Userrole.equals(RoleS)) {
                StudentPage p = new StudentPage(UserID);
            } else if (Userrole.equals(RoleA)) {
                AdminPage a = new AdminPage(UserID);
            } else if (Userrole.equals(RoleT)) {
                TeacherPage t = new TeacherPage(UserID);
            }
        }
    }
}
