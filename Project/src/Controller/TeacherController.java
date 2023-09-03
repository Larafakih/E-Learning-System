package Controller;

import UI.*;
import DAO.*;
import Model.*;
import javax.swing.*;


public class TeacherController {

    static final String RoleT = "Teacher";

    public static void UploadBook(JTextField BookName, int courseId, JComboBox coursesCb, JFrame f, int id) {
        if (BookName.getText().isEmpty() || coursesCb.getSelectedItem().toString().isEmpty()){ //|| type.getSelectedItem().toString().isEmpty()) {
            new JoptPane().displayGUI("Error", "Please select Course name and type of book \n and enter Book name", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE);
        } else {
            String coursename = coursesCb.getSelectedItem().toString();
            //String typeofbook=type.getSelectedItem().toString();
            courseId = CoursesDao.getCourseId(coursename);
            String name = BookName.getText();//+typeofbook;
            Document.AddBooks(name, courseId, id);
            f.dispose();
        }
    }

    public static void AddCourse(String subjectStr, String courseNameStr, String courseDescriptionStr, String courseObj, String courseContentStr, int currentTeacherID) {
        if (subjectStr.isEmpty() || courseNameStr.isEmpty() || courseDescriptionStr.isEmpty() || courseContentStr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter Course Details!!");
        }
        int subject_ID = SubjectsDao.getSubjects(subjectStr);
        Courses co = new Courses(courseNameStr, courseDescriptionStr, courseObj, courseContentStr, currentTeacherID, subject_ID);
        int x = CoursesDao.AddCourse(co);
        if (x == 0) {
            new project.Error("Some Error Occured", currentTeacherID, RoleT);
        } else {
            JOptionPane.showMessageDialog(null, "Course Added successfully!");
        }
    }
}
