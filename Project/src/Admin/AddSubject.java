package Admin;

import Controller.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import UI.*;

public class AddSubject extends JFrame implements ActionListener {

    private JLabel title, subjectCbLbl, image;
    private JButton addBtn;
    private JPanel middlePanel, contentPane;
    private JTextField subjectName;
    private int currentAdminID;

    public AddSubject(int c) {
        super("Add Subject");
        setIconImage(img.SystemIcon());
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(790, 400);
        setLocation(10, 10);
        currentAdminID = c;

        title = new TitleLbl("Add Subject");
        add(title, BorderLayout.NORTH);

        contentPane = new JPanel();
        add(contentPane, BorderLayout.CENTER);
        contentPane.setLayout(null);

        image = new JLabel();
        image.setBounds(30, 40, 200, 200);
        image.setIcon(img.CreateImageIcon("icons/su-1.png", 200, 200));
        contentPane.add(image);

        subjectCbLbl = new TxtLabel("Subject Name");
        subjectCbLbl.setBounds(250, 70, 150, 43);
        contentPane.add(subjectCbLbl);

        subjectName = new TxtField("Please enter subject name");
        subjectName.setBounds(410, 70, 300, 50);
        contentPane.add(subjectName);

        addBtn = new CircleBtn("Add");
        addBtn.setBounds(410, 150, 200, 60);
        addBtn.addActionListener(this);
        contentPane.add(addBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addBtn) {
            SubjectController.addSubject(subjectName, currentAdminID,this);
        }
    }
}
