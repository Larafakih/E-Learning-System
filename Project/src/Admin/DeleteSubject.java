package Admin;

import Controller.*;
import java.awt.*;
import java.awt.event.*;
import DAO.*;
import UI.*;
import javax.swing.*;

public class DeleteSubject extends JFrame implements ActionListener {

    private JLabel title, subjectCbLbl, image;
    private JComboBox subjectsCb, coursesCb;
    private JButton deleteBtn;
    private JPanel middlePanel;

    public DeleteSubject() {
        super("Delete Subject");
        setIconImage(img.SystemIcon());
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(800, 400);
        setLocation(10, 10);

        title = new TitleLbl("Delete Subject");
        add(title, BorderLayout.NORTH);

        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        add(middlePanel, BorderLayout.CENTER);

        image = new JLabel();
        image.setBounds(30, 40, 200, 200);
        image.setIcon(img.CreateImageIcon("icons/su.png", 200, 200));
        middlePanel.add(image);

        subjectCbLbl = new TxtLabel("Select Subject");
        subjectCbLbl.setBounds(250, 70, 250, 28);
        middlePanel.add(subjectCbLbl);

        subjectsCb = new JComboBox(SubjectsDao.getSubjects());
        subjectsCb.setSelectedIndex(-1);
        subjectsCb.setBounds(410, 70, 200, 28);
        subjectsCb.addActionListener(this);
        middlePanel.add(subjectsCb);

        deleteBtn = new CircleBtn("Delete", "Delete Selected Subject");
        deleteBtn.addActionListener(this);
        deleteBtn.setBounds(410, 150, 140, 40);
        middlePanel.add(deleteBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == deleteBtn) {
            Object selected = subjectsCb.getSelectedItem();
            String subjectName = selected.toString();
            SubjectController.DeleteSubject(subjectName);
            dispose();
        }
    }
}
