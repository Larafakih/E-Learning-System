package Admin;

import DAO.*;
import Model.Courses;
import UI.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Salma
 */
public class ViewCourse implements ActionListener {

    private JLabel title;
    private JTextArea courseDescription, courseContent, courseObj;
    private JTextField teachername, teacheremail, SubjectName;
    private JScrollPane scroll, scroll2, scroll3;
    private JPanel Panel;
    private JComboBox courseCb;
    private JLabel courseNameLbl, SubjectnameLbl, courseDescriptionLbl, courseContentLbl, courseobjectivesLbl;
    private JLabel teachernameLbl, teacheremailLbl;
    private JButton back;
    private static final String RoleA = "Admin";
    private int adminId;
    private ArrayList<Model.Courses> co;
    private JFrame f;

    public ViewCourse(int id) {
        f = new JFrame("View Courses");
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setResizable(false);
        f.setSize(1280, 700);
        f.setLocation(10, 10);
        f.setLayout(new BorderLayout());
        adminId = id;

        title = new TitleLbl("Update Course");
        f.add(title, BorderLayout.NORTH);

        Panel = new JPanel();
        Panel.setLayout(new BoxLayout(Panel, BoxLayout.Y_AXIS));
        f.add(Panel, BorderLayout.CENTER);

        back = new JButton();
        back.setBounds(880, 0, 100, 40);
        Panel.add(back);
        back.addMouseListener(new UI.BackListener(adminId, back, RoleA, f));

        JScrollPane scrollpane = new JScrollPane(Panel);
        scrollpane.setBounds(0, 20, 1280, 700);
        scrollpane.setBackground(Color.white);
        scrollpane.setViewportView(Panel);
        f.add(scrollpane);
        scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel CourseP = new JPanel();
        CourseP.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        Panel.add(CourseP);

        courseNameLbl = new TxtLabel("Select Course ");
        courseNameLbl.setBounds(20, 10, 300, 28);
        CourseP.add(courseNameLbl);

        courseCb = new JComboBox();
        co = CoursesDao.getArrayofCourses();
        for (int i = 0; i < co.size(); i++) {
            Courses c = co.get(i);
            courseCb.addItem(c.getName());
        }
        courseCb.setSelectedIndex(-1);
        courseCb.setBounds(250, 10, 350, 28);
        courseCb.addActionListener(this);
        CourseP.add(courseCb);

        JPanel backBtnP = new JPanel();
        backBtnP.setLayout(new FlowLayout(FlowLayout.LEFT, 200, 0));
        CourseP.add(backBtnP);

        GenerateCourse();
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == courseCb) {
            Object selected = courseCb.getSelectedItem();
            String courseName = selected.toString();

            LoadData(courseName);
        }
    }

    public void GenerateCourse() {
        JPanel SubjectP = new JPanel();
        SubjectP.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        Panel.add(SubjectP);

        SubjectnameLbl = new TxtLabel("Subject Name: ");
        SubjectnameLbl.setPreferredSize(new Dimension(200, 28));
        SubjectP.add(SubjectnameLbl);

        SubjectName = new JTextField("");
        SubjectName.setEditable(false);
        SubjectName.setPreferredSize(new Dimension(200, 40));
        SubjectP.add(SubjectName);

        JPanel teachernameP = new JPanel();
        teachernameP.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        Panel.add(teachernameP);

        teachernameLbl = new TxtLabel("Teacher Name: ");
        teachernameLbl.setPreferredSize(new Dimension(200, 28));
        teachernameP.add(teachernameLbl);

        teachername = new JTextField("");
        teachername.setEditable(false);
        teachername.setPreferredSize(new Dimension(350, 28));
        teachernameP.add(teachername);

        JPanel teacheremailP = new JPanel();
        teacheremailP.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        Panel.add(teacheremailP);

        teacheremailLbl = new TxtLabel("Teacher Email: ");
        teacheremailLbl.setPreferredSize(new Dimension(200, 28));
        teacheremailP.add(teacheremailLbl);

        teacheremail = new JTextField("");
        teacheremail.setPreferredSize(new Dimension(350, 28));
        teacheremail.setEditable(false);
        teacheremailP.add(teacheremail);

        JPanel courseDescriptionP = new JPanel();
        courseDescriptionP.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        Panel.add(courseDescriptionP);

        courseDescriptionLbl = new TxtLabel("Description: ");
        // courseDescriptionLbl.setBounds(20, 240, 190, 28);
        courseDescriptionLbl.setPreferredSize(new Dimension(190, 28));
        courseDescriptionP.add(courseDescriptionLbl);

        courseDescription = new JTextArea();
        courseDescription.setLineWrap(true);
        courseDescription.setWrapStyleWord(true);
        scroll = new JScrollPane(courseDescription);
        //   scroll.setBounds(250, 240, 600, 200);
        scroll.setPreferredSize(new Dimension(600, 200));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        courseDescription.setEditable(false);
        courseDescriptionP.add(scroll);

        JPanel courseobjectivesP = new JPanel();
        courseobjectivesP.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        Panel.add(courseobjectivesP);

        courseobjectivesLbl = new TxtLabel("Objective: ");
        courseobjectivesLbl.setPreferredSize(new Dimension(190, 28));
        courseobjectivesP.add(courseobjectivesLbl);

        courseObj = new JTextArea("");
        courseObj.setLineWrap(true);
        courseObj.setWrapStyleWord(true);
        scroll3 = new JScrollPane(courseObj);
        scroll3.setPreferredSize(new Dimension(600, 200));
        scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        courseObj.setEditable(false);
        courseobjectivesP.add(scroll3);

        JPanel courseContentP = new JPanel();
        courseContentP.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 10));
        Panel.add(courseContentP);

        courseContentLbl = new TxtLabel("Content: ");
        courseContentLbl.setPreferredSize(new Dimension(190, 28));
        courseContentP.add(courseContentLbl);

        courseContent = new JTextArea();
        courseContent.setLineWrap(true);
        courseContent.setWrapStyleWord(true);
        scroll2 = new JScrollPane(courseContent);
        scroll2.setPreferredSize(new Dimension(600, 200));
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        courseContent.setEditable(false);
        courseContentP.add(scroll2);

    }

    public void LoadData(String courseName) {
        if (!courseName.isEmpty()) {
            Courses course = CoursesDao.getCoursebyName(courseName);
            courseDescription.setText(course.getDescription());
            courseContent.setText(course.getContent());
            courseObj.setText(course.getObjective());
            SubjectName.setText(SubjectsDao.getSubjectsname(course.getSubjectID()));
            Model.Teacher t = TeacherDao.SelectTeacherById(course.getTeacherID());
            teachername.setText(t.getFullName());
            teacheremail.setText(t.getEmail());
        }
    }
}
