package Teacher;

import Controller.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Model.*;
import DAO.*;
import UI.*;

/**
 *
 * @author Salma
 */
public class AddCourse extends JFrame implements ActionListener {

    private JLabel title, subjectCbLbl, courseNameLbl, courseDescriptionLbl;
    private JLabel courseContentLbl, courseobjectivesLbl;
    private JComboBox subjectsCb;
    private JTextArea courseDescription, courseContent, courseObj;
    private JButton addBtn, generateSyllabus, back;
    private JPanel middlePanel;
    private JTextField courseName;
    private String SubjectsData[];
    private JScrollPane scroll, scroll2, scroll3;
    private int currentTeacherID;
    private Teacher teacher;
    private Color oldColor;
    private static JFrame f;

    public AddCourse(int c) {
        f = new JFrame("Add Course");
        f.setIconImage(img.SystemIcon());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setResizable(false);
        f.setSize(1280, 720);
        f.setLocation(10, 10);
        f.setLayout(new BorderLayout());
        currentTeacherID = c;

        teacher = TeacherDao.SelectTeacherById(currentTeacherID);

        title = new TitleLbl("Add Course");
        f.add(title, BorderLayout.NORTH);

        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        f.add(middlePanel, BorderLayout.CENTER);

        back = new JButton();
        back.setBounds(950, 0, 100, 40);
        middlePanel.add(back);
        back.addMouseListener(new UI.BackListener(currentTeacherID, back, "Teacher", f));

        subjectCbLbl = new TxtLabel("Select Subject");
        subjectCbLbl.setBounds(20, 20, 150, 28);
        middlePanel.add(subjectCbLbl);

        SubjectsData = SubjectsDao.getSubjects();
        subjectsCb = new JComboBox(SubjectsData);
        subjectsCb.setSelectedIndex(-1);
        subjectsCb.setBounds(220, 20, 150, 28);
        middlePanel.add(subjectsCb);

        courseNameLbl = new TxtLabel("Course Name");
        courseNameLbl.setBounds(20, 70, 150, 28);
        middlePanel.add(courseNameLbl);

        courseName = new JTextField();
        courseName.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        courseName.setBounds(220, 70, 150, 28);
        middlePanel.add(courseName);

        courseDescriptionLbl = new TxtLabel("Course Description");
        courseDescriptionLbl.setBounds(20, 125, 190, 28);
        middlePanel.add(courseDescriptionLbl);

        courseDescription = new JTextArea();
        courseDescription.setLineWrap(true);
        courseDescription.setWrapStyleWord(true);
        scroll = new JScrollPane(courseDescription);
        scroll.setBounds(220, 110, 460, 100);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        courseDescription.setEditable(true);
        middlePanel.add(scroll);

        courseobjectivesLbl = new TxtLabel("Course Objectives");
        courseobjectivesLbl.setBounds(20, 230, 190, 28);
        middlePanel.add(courseobjectivesLbl);

        courseObj = new JTextArea();
        courseObj.setLineWrap(true);
        courseObj.setWrapStyleWord(true);
        scroll3 = new JScrollPane(courseObj);
        scroll3.setBounds(220, 230, 460, 100);
        scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        courseObj.setEditable(true);
        middlePanel.add(scroll3);

        courseContentLbl = new TxtLabel("Course Content");
        courseContentLbl.setBounds(20, 360, 190, 28);
        middlePanel.add(courseContentLbl);

        courseContent = new JTextArea();
        courseContent.setLineWrap(true);
        courseContent.setWrapStyleWord(true);
        scroll2 = new JScrollPane(courseContent);
        scroll2.setBounds(220, 340, 600, 200);
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        courseContent.setEditable(true);
        middlePanel.add(scroll2);

        addBtn = new CircleBtn("Add Course");
        addBtn.addActionListener(this);
        addBtn.setBounds(650, 550, 150, 50);
        middlePanel.add(addBtn);

        generateSyllabus = new CircleBtn("Download This Syllabus As Pdf");
        generateSyllabus.addActionListener(this);
        middlePanel.add(generateSyllabus);
        generateSyllabus.setBounds(810, 550, 230, 50);

        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addBtn) {
            addCourse();
        } else if (ae.getSource() == generateSyllabus) {
            generate();
        }
    }

    public void addCourse() {
        String subjectStr = subjectsCb.getSelectedItem().toString();
        String courseNameStr = courseName.getText();
        String courseDescriptionStr = courseDescription.getText();
        String courseObj = this.courseObj.getText();
        String courseContentStr = courseContent.getText();
        TeacherController.AddCourse(subjectStr, courseNameStr, courseDescriptionStr, courseObj, courseContentStr, currentTeacherID);

    }

    public void generate() {
        String path = getpath();
        String subjectStr = subjectsCb.getSelectedItem().toString();
        String courseNameStr = courseName.getText();
        String courseDescriptionStr = courseDescription.getText();
        String courseObj = this.courseObj.getText();
        String courseContentStr = courseContent.getText();
        Document.GeneratePdf(teacher, subjectStr, courseNameStr, courseDescriptionStr, courseObj, courseContentStr, path);

    }

    public static String getpath() {
        String path = "";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(f);
        if (x == JFileChooser.APPROVE_OPTION) {
            path = j.getSelectedFile().getPath();
        }
        path = path.replaceAll("\\\\", "\\\\\\\\");
        path = path + "\\\\";
        return path;
    }
}
