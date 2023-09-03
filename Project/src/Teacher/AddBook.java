package Teacher;

import Controller.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import DAO.*;
import UI.*;

/**
 *
 * @author Salma
 */
public class AddBook implements ActionListener {

    private JLabel title, courselbl, BookNameLbl;
    private int courseId, teacherid;
    private JButton upload;
    private JPanel middlePanel;
    private JTextField BookName;
    private String CourseData[];
    private JComboBox coursesCb;
    private JFrame f;

    public AddBook(int id) {
        f = new JFrame("Add Book");
        f.setIconImage(img.SystemIcon());
        f.setLayout(new BorderLayout());
        teacherid = id;

        title = new TitleLbl("Upload Book");
        f.add(title, BorderLayout.NORTH);

        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        f.add(middlePanel, BorderLayout.CENTER);

        courselbl = new TxtLabel("Select Course");
        courselbl.setBounds(20, 20, 150, 28);
        middlePanel.add(courselbl);

        CourseData = CoursesDao.getCourseName();
        coursesCb = new JComboBox(CourseData);
        coursesCb.setSelectedIndex(-1);
        coursesCb.setBounds(200, 20, 200, 28);
        coursesCb.addActionListener(this);
        middlePanel.add(coursesCb);

        BookNameLbl = new TxtLabel("Book Name");
        BookNameLbl.setBounds(20, 80, 150, 28);
        middlePanel.add(BookNameLbl);

        BookName = new TxtField();
        BookName.setToolTipText("Please enter Book Name");
        BookName.setBounds(190, 70, 200, 50);
        middlePanel.add(BookName);

        upload = new CircleBtn("Upload");
        upload.setBounds(250, 150, 80, 50);
        upload.addActionListener(this);
        middlePanel.add(upload);

        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setResizable(false);
        f.setSize(500, 500);
        f.setLocation(10, 10);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == upload) {
            TeacherController.UploadBook(BookName, courseId, coursesCb, f, teacherid);
        }
    }
}
