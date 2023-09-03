package project;

import UI.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Users.*;

/**
 *
 * @author Salma
 */
public class Main extends JFrame implements ActionListener {

    JPanel headerPanel, mainPanel;
    JButton LoginButton, SignupButton;
    JLabel footerLbl, mainScreenLbl, mainTitle, aboutUs, aboutusimg;
    JMenu file, about;
    JMenuItem exit;
    Color oldColor;
    static final String RoleS = "Student1";

    public Main() {
        super("E-Learning System");
        setIconImage(img.SystemIcon());
        setSize(1280, 720);
        setLocation(10, 10);
        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        headerPanel = new JPanel(new BorderLayout());
        add(headerPanel, BorderLayout.NORTH);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        add(mainPanel, BorderLayout.CENTER);

        about = new JMenu("About");
        exit = new JMenuItem("Exit");

        mainTitle = new TitleLbl("E-Learning System");
        headerPanel.add(mainTitle);
        JLabel clock = new JLabel();
        SystemTime.clock(clock);
        headerPanel.add(clock, BorderLayout.SOUTH);

        aboutusimg = new JLabel();
        aboutusimg.setIcon(img.CreateImageIcon("icons/info.png", 50, 50));
        aboutusimg.setBounds(1, 545, 50, 50);
        mainPanel.add(aboutusimg);
        aboutUs = new JLabel("About Us");
        aboutUs.setForeground(new Color(0, 142, 204));
        aboutUs.setBounds(50, 550, 200, 50);
        mainPanel.add(aboutUs);
        aboutUs.addMouseListener(new UI.MouseListener(0, aboutUs, oldColor, "AboutUs", this));

        LoginButton = new CircleBtn("Login", "icons/login.png", 96, 96);
        LoginButton.setBounds(150, 90, 200, 140);
        LoginButton.addActionListener((ActionListener) this);
        mainPanel.add(LoginButton);

        SignupButton = new CircleBtn("icons/signup.png", 96, 96);
        SignupButton.setBounds(150, 300, 200, 140);
        SignupButton.addActionListener((ActionListener) this);
        mainPanel.add(SignupButton);

        mainScreenLbl = new JLabel(img.CreateImageIcon("icons/ElearningMain.png", 600, 550));
        mainScreenLbl.setBounds(550, 3, 600, 600);
        mainPanel.add(mainScreenLbl);
        oldColor = aboutUs.getForeground();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == LoginButton) {
            new LoginPage();
            dispose();
        } else if (ae.getSource() == SignupButton) {
            new UserCreateAccount(RoleS);
        }  else if (ae.getSource() == exit) {
            //System.exit(0);
        }
    }
}
