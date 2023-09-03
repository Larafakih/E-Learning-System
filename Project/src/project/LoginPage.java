package project;

import UI.*;
import Controller.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author Salma
 */
public class LoginPage extends JFrame implements ActionListener {

    private static JLabel lEmail, lPass, mainTitle, createAccount, show, validation;
    private JTextField Email;
    private JPasswordField Password;
    private JButton b1, b2;
    private String emailStr, passStr;
    private JPanel headerPanel, mainPanel;
    private Color oldColor;
    private static final String RoleS = "Student1";

    public LoginPage() {
        super("Login");
        setIconImage(img.SystemIcon());
        setSize(1000, 720);
        setLocation(10, 10);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        headerPanel = new JPanel(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        add(mainPanel, BorderLayout.CENTER);

        mainTitle = new TitleLbl("Login");
        headerPanel.add(mainTitle, BorderLayout.CENTER);

        JLabel l3 = new JLabel(img.CreateImageIcon("icons/elearningLogin.png", 400, 400));
        l3.setBounds(460, 30, 500, 500);
        mainPanel.add(l3);

        validation = new JLabel("");
        validation.setFont(new Font("Tahoma", Font.BOLD, 18));
        validation.setBounds(152, 50, 300, 40);
        validation.setForeground(Color.RED);
        mainPanel.add(validation);

        lEmail = new TxtLabel("Email: ");
        lEmail.setBounds(40, 105, 130, 40);
        mainPanel.add(lEmail);

        Email = new TxtField();
        Email.setToolTipText("Please enter email");
        Email.setBounds(150, 100, 300, 50);
        mainPanel.add(Email);

        lPass = new TxtLabel("Password: ");
        lPass.setBounds(40, 165, 200, 60);
        mainPanel.add(lPass);

        Password = new PasswrdField();
        Password.setToolTipText("Please enter password");
        Password.setBounds(150, 160, 300, 50);
        mainPanel.add(Password);

        show = new JLabel();
        show.setToolTipText("Click here to show password !!");
        show.setIcon(img.CreateImageIcon("icons/show.png", 40, 40));
        show.setBounds(450, 160, 150, 50);
        mainPanel.add(show);
        show.addMouseListener(new ShowPasswordListener(show, Password));

        b1 = new CircleBtn("Login");
        b1.setBounds(50, 250, 130, 30);
        b1.addActionListener(this);
        mainPanel.add(b1);

        b2 = new CircleBtn("Cancel");
        b2.setBounds(190, 250, 130, 30);
        mainPanel.add(b2);
        b2.addActionListener(this);

        createAccount = new JLabel("<HTML><U>Create Account</U></HTML>");
        createAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        createAccount.setForeground(new Color(0, 142, 204));
        createAccount.setFont(new Font("Tahoma", Font.BOLD, 15));
        createAccount.setBounds(200, 300, 170, 30);
        mainPanel.add(createAccount);
        createAccount.addMouseListener(new UI.MouseListener(0, createAccount, oldColor, "CreateAccount", this));
        oldColor = createAccount.getForeground();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            validation.setText("");
            emailStr = Email.getText();
            passStr = String.valueOf(Password.getPassword());
            if (UserController.getIsExisted() == false) {
                System.out.println("existed false");
            } else {
                this.setVisible(false);
            }
            boolean loginT = UserController.login(emailStr, passStr);
            if (loginT) {
                this.dispose();
            }

        } else if (ae.getSource() == b2) {
            this.dispose();
            new Main();
        }
    }

    public static void setvalidation(String txt) {
        validation.setText(txt);
    }
}
