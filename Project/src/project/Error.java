package project;

import UI.*;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Salma
 */
public class Error extends JFrame {

    private JPanel contentPane;
    private JLabel Title, image;
    private String error, role;
    private JButton backBtn;
    private int id;
    static final String RoleA = "Admin";
    static final String RoleS = "Student";
    static final String RoleT = "Teacher";

    public Error(String t, int id, String role) {
        super("ERROR");
        error = t;
        setLocation(10, 10);
        setSize(600, 600);
        setIconImage(img.SystemIcon());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        contentPane = new JPanel();
        contentPane.setLayout(null);
        Title = new TitleLbl("" + error);
        add(Title, BorderLayout.NORTH);

        add(contentPane, BorderLayout.CENTER);

        backBtn = new JButton();
        backBtn.setBounds(0, 0, 100, 40);
        backBtn.addMouseListener(new UI.BackListener(id, backBtn, role, this));
        backBtn.setAlignmentX(FlowLayout.LEFT);
        contentPane.add(backBtn);

        image = new JLabel(img.CreateImageIcon("icons/404-error.png", 500, 500));
        image.setBounds(0, 0, 500, 500);
        contentPane.add(image);
        setVisible(true);
    }

    public Error() {

    }
}
