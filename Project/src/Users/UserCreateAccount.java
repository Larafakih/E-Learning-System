package Users;

import Controller.*;
import UI.*;
import Model.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import project.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author Salma
 */
public class UserCreateAccount extends JFrame implements ActionListener, FocusListener {

    private JPanel contentPane;
    private JTextField FullName, Email, PhoneNb;
    private JPasswordField PasswordField;
    public JButton AddButton, backBtn ;
    private JRadioButton maleRB, femaleRB;
    private ButtonGroup radioBtns;
    private JLabel fullnameValidation, emailValidation, phoneValidation, passwordValidation, profilePicLbl;
    private JLabel show, login, login1, upload, image;
    public JLabel Title;
    private int counter1, counter2;
    private Color oldColor;
    private FileInputStream fis = null;
    private File fl = null;
    private String fname = null, UserRole;
    private User u;
    static final String LoginPage = "LoginPage";
    private static final String RoleA = "Admin", RoleS = "Student", RoleS1 = "Student1", RoleT = "Teacher";
    private static final String ma = "Male", fe = "Female";
    JFrame f;
    public UserCreateAccount(String Role) {
        f = new JFrame("Create Account");
        UserRole = Role;
        f.setIconImage(img.SystemIcon());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(1280, 720);
        f.setLocation(10, 10);
        f.setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        f.add(contentPane, BorderLayout.CENTER);
        contentPane.setLayout(null);

        Title = new TitleLbl();
        Title.setFont(new Font("Courier", Font.BOLD, 30));
        Title.setForeground(new Color(0, 142, 204));
        f.add(Title, BorderLayout.NORTH);

        /*
        backBtn = new JButton();
        backBtn.setBounds(0, 0, 100, 40);
        backBtn.addMouseListener(new UI.BackListener(0, backBtn, UserRole, f));
        backBtn.setAlignmentX(FlowLayout.LEFT);
        contentPane.add(backBtn);
        */
        upload = new JLabel();
        upload.setBounds(550, 105, 60, 60);
        upload.setToolTipText("Upload profile picture");
        upload.setIcon(img.CreateImageIcon("icons/add-3.png", 60, 60));
        upload.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ReturnDifferentType.Im i = img.upload();
                fname = i.getFname();
                fis = i.getFis();
                fl = i.getF();
                profilePicLbl.setIcon(img.resizeImage(i.getFname()));
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {

            }

            public void mouseExited(java.awt.event.MouseEvent evt) {

            }
        });
        contentPane.add(upload);

        profilePicLbl = new JLabel();
        profilePicLbl.setBounds(500, 0, 250, 150);
        contentPane.add(profilePicLbl);
        profilePicLbl.setIcon(img.CreateImageIcon("icons/user.png", 110, 110));

        JLabel lblName = new TxtLabel("Full name");
        lblName.setBounds(58, 130, 99, 43);
        contentPane.add(lblName);

        FullName = new TxtField();
        FullName.setToolTipText("Please enter full name");
        FullName.addFocusListener(this);
        FullName.setBounds(214, 120, 250, 50);
        contentPane.add(FullName);

        JLabel lblEmailAddress = new TxtLabel("Email");
        lblEmailAddress.setBounds(58, 210, 110, 29);
        contentPane.add(lblEmailAddress);

        Email = new TxtField();
        Email.setToolTipText("Please enter email address");
        Email.setBounds(214, 200, 250, 50);
        Email.addFocusListener(this);
        contentPane.add(Email);

        JLabel lblPhone = new TxtLabel("Phone");
        lblPhone.setBounds(58, 290, 99, 29);
        contentPane.add(lblPhone);

        PhoneNb = new TxtField();
        PhoneNb.requestFocusInWindow();
        PhoneNb.setToolTipText("Please enter Phone number");
        PhoneNb.setBounds(214, 280, 250, 50);
        PhoneNb.addFocusListener(this);
        contentPane.add(PhoneNb);

        JLabel lblPassword = new TxtLabel("Password");
        lblPassword.setBounds(58, 370, 99, 20);
        contentPane.add(lblPassword);

        PasswordField = new PasswrdField();
        PasswordField.setBounds(214, 360, 250, 50);
        PasswordField.setToolTipText("Please enter password");
        PasswordField.addFocusListener(this);
        contentPane.add(PasswordField);

        show = new JLabel();
        show.setToolTipText("Show Password");
        show.setIcon(img.CreateImageIcon("icons/show.png", 35, 35));
        show.setBounds(480, 360, 150, 50);
        contentPane.add(show);
        show.addMouseListener(new UI.ShowPasswordListener(show, PasswordField));

        JLabel genderLbl = new TxtLabel("Gender");
        genderLbl.setBounds(58, 450, 99, 24);
        contentPane.add(genderLbl);

        maleRB = new JRadioButton(ma);
        femaleRB = new JRadioButton(fe);
        radioBtns = new ButtonGroup();

        maleRB.setFont(new Font("Tahoma", Font.PLAIN, 20));
        maleRB.setBounds(214, 440, 110, 50);
        maleRB.setActionCommand(ma);
        contentPane.add(maleRB);

        femaleRB.setFont(new Font("Tahoma", Font.PLAIN, 20));
        femaleRB.setBounds(320, 440, 120, 50);
        femaleRB.setActionCommand(fe);
        contentPane.add(femaleRB);

        radioBtns.add(maleRB);
        radioBtns.add(femaleRB);

        fullnameValidation = new ValidLabel();
        fullnameValidation.setBounds(214, 170, 150, 10);
        contentPane.add(fullnameValidation);

        emailValidation = new ValidLabel();
        emailValidation.setBounds(214, 250, 150, 10);
        contentPane.add(emailValidation);

        phoneValidation = new ValidLabel();
        phoneValidation.setBounds(214, 330, 150, 10);
        contentPane.add(phoneValidation);

        passwordValidation = new ValidLabel();
        passwordValidation.setBounds(214, 410, 150, 10);
        contentPane.add(passwordValidation);

        login = new JLabel("Already has an account? ");
        login.setBounds(214, 550, 300, 60);

        login1 = new JLabel("Log in");
        login1.setBounds(360, 550, 300, 60);
        login1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        login1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginMouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LoginMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                LoginMouseExited(evt);
            }
        });

        AddButton = new CircleBtn();
        AddButton.setBounds(214, 500, 190, 60);
        AddButton.addActionListener((ActionListener) this);
        contentPane.add(AddButton);
        image = new JLabel();
        image.setBounds(770, 170, 500, 500);
        if (UserRole == RoleA) {
            Title.setText("Create Account: " + UserRole);
            AddButton.setText("Add Admin");
            image.setIcon(img.CreateImageIcon("icons/administrator.png", 340, 340));
        } else if (UserRole == RoleT) {
            Title.setText("Create Account: " + UserRole);
            AddButton.setText("Add Teacher");
            image.setIcon(img.CreateImageIcon("icons/AddTeacher.png", 300, 300));
        } else if (UserRole == RoleS) {
            Title.setText("Create Account: " + UserRole);
            AddButton.setText("Add Student");
            image.setIcon(img.CreateImageIcon("icons/Student.png", 300, 300));
        } else if (UserRole == RoleS1) {
            Title.setText("Create Your Account");
            AddButton.setText("SIGN UP");
            image.setIcon(img.CreateImageIcon("icons/Student.png", 300, 300));
            contentPane.add(login);
            contentPane.add(login1);
        }
        contentPane.add(image);
        oldColor = login1.getForeground();
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == AddButton) {
            String fullName = FullName.getText();
            String email = Email.getText();
            String phone = PhoneNb.getText();
            String password = String.valueOf(PasswordField.getPassword());
            String genderStr = null;
            if (radioBtns.getSelection() != null) {
                genderStr = radioBtns.getSelection().getActionCommand();
            }
            String msg = "" + fullName;
            if (fullnameValidation.getText().isEmpty()
                    && emailValidation.getText().isEmpty()
                    && phoneValidation.getText().isEmpty()
                    && passwordValidation.getText().isEmpty()) {
                if (fullName.isEmpty() || email.isEmpty() || phone.isEmpty() || password.isEmpty()
                        || genderStr.isEmpty() || this.f == null || this.fis == null) {
                    JOptionPane.showMessageDialog(null, "Please Fill All Fields !");
                } else {
                    if (UserRole == RoleA) {
                        u = new Admin(fullName, email, phone, genderStr);
                        UserController.SignUp(u, password, fl, fis);
                        this.dispose();
                    } else if (UserRole == RoleS || UserRole == RoleS1) {
                        u = new Student(fullName, email, phone, genderStr);
                        UserController.SignUp(u, password, fl, fis);
                        this.dispose();
                    } else if (UserRole == RoleT) {
                        u = new Teacher(fullName, email, phone, genderStr);
                        UserController.SignUp(u, password, fl, fis);
                        this.dispose();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please Fill accurate Info !");
            }
        }
    }

    @Override
    public void focusGained(FocusEvent e
    ) {
        if (e.getSource() == FullName) {
            fullnameValidation.setText("");
        } else if (e.getSource() == Email) {
            emailValidation.setText("");
        } else if (e.getSource() == PhoneNb) {
            phoneValidation.setText("");
        } else if (e.getSource() == PasswordField) {
            passwordValidation.setText("");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == FullName) {
            Validation.IfFullName(FullName, fullnameValidation);
        } else if (e.getSource() == Email) {
            Validation.IfEmail(Email, emailValidation);
        } else if (e.getSource() == PhoneNb) {
            Validation.IfPhone(PhoneNb, phoneValidation);
        } else if (e.getSource() == PasswordField) {
            Validation.IfPassword(PasswordField, passwordValidation);
        }
    }

    private void LoginMouseClicked(java.awt.event.MouseEvent evt) {
        f.dispose();
        LoginPage a = new LoginPage();
     //   a.setVisible(true);
        
    }

    private void LoginMouseEntered(java.awt.event.MouseEvent evt) {
        login1.setForeground(Color.RED);
    }

    private void LoginMouseExited(java.awt.event.MouseEvent evt) {
        login1.setForeground(oldColor);
    }
}
