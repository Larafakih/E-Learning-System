package project;

import UI.*;
import java.awt.*;
import javax.swing.*;

public class AboutUs extends JFrame {

    private JPanel contentPane;
    private JLabel Title, image, version, release, developed, s, and, l, l8, l9, b;

    public AboutUs() {
        super("About Us");
        setLocation(10, 10);
        setSize(1000, 700);
        setIconImage(img.SystemIcon());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        contentPane = new JPanel();
        contentPane.setLayout(null);

        Title = new TitleLbl("E-Learning System");
        add(Title, BorderLayout.NORTH);

        add(contentPane, BorderLayout.CENTER);

        image = new JLabel(img.CreateImageIcon("icons/developer.png", 120, 120));
        image.setBounds(630, 160, 200, 130);
        contentPane.add(image);

        version = new JLabel("Version: v1.1");
        version.setForeground(new Color(17, 29, 94));
        version.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
        version.setBounds(100, 10, 300, 50);
        contentPane.add(version);

        release = new JLabel("Released on: March 18,2022");
        release.setForeground(new Color(17, 29, 94));
        release.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
        release.setBounds(100, 50, 500, 50);
        contentPane.add(release);

        String[][] rowData = {{},
        {"93314", "Lara Fakih"}};

        developed = new JLabel("Developed By :");
        developed.setForeground(new Color(17, 29, 94));
        developed.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        developed.setBounds(100, 100, 300, 100);
        contentPane.add(developed);

        s = new JLabel("103909  --" + "  Salma Slim");
        s.setForeground(new Color(18, 92, 19));
        s.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        s.setBounds(280, 150, 700, 100);
        contentPane.add(s);

        and = new JLabel("And");
        and.setForeground(new Color(18, 92, 19));
        and.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        and.setBounds(280, 230, 600, 34);
        contentPane.add(and);

        l = new JLabel("93314  --" + "   Lara Fakih");
        l.setForeground(new Color(18, 92, 19));
        l.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        l.setBounds(280, 270, 600, 34);
        contentPane.add(l);

        b = new JLabel(img.CreateImageIcon("icons/love.png", 70, 70));
        b.setBounds(280, 300, 200, 130);
        contentPane.add(b);

        l8 = new JLabel("Contact: salma.slim@st.ul.edu.lb And lara.fakih.2@st.ul.edu.lb");
        l8.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        l8.setBounds(80, 500, 1000, 34);
        contentPane.add(l8);

        l9 = new JLabel("Education: Master 1 - Computer Science - Lebanese University");
        l9.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
        l9.setBounds(80, 550, 1000, 34);
        contentPane.add(l9);

        setVisible(true);
    }
}
