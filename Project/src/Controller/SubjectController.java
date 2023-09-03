package Controller;

import DAO.CoursesDao;
import DAO.SubjectsDao;
import Model.Subject;
import javax.swing.*;

/**
 *
 * @author Salma
 */
public class SubjectController {

    public static void addSubject(JTextField subjectName, int ID,JFrame f) {
        String Name = subjectName.getText();
        boolean isexist = false;
        isexist = SubjectController.CheckIfSubjectExist(Name, isexist);
        if (isexist == false) {
            if (Name.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter Subject");
            } else {
                Subject s = new Subject(Name, ID);
                int x = SubjectsDao.AddSubject(s);
                if (x == 0) {

                } else {
                    JOptionPane.showMessageDialog(null, "Subject Added!");
                    f.dispose();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "This is Subject already exist");
        }
    }

    public static void DeleteSubject(String subjectName) {
        int input = JOptionPane.showConfirmDialog(null, "Deleting Subject will delete all Corresponding Courses to this subject"
                + ", do you want to proceed?", "Select an Option...",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

        if (input == 0) {
            int x = SubjectsDao.DeleteSubject(subjectName);
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Got some error");
            } else {
                JOptionPane.showMessageDialog(null, "Subject Deleted Successfully");
            }
        }
    }

    public static void getCourseBySubject(String subjectName, JComboBox coursesCb) {
        int subjectID;
        subjectID = SubjectsDao.getSubjects(subjectName);
        String[] coursesData = CoursesDao.getSetCourses(subjectID);
        for (int j = 0; j < coursesData.length; j++) {
            coursesCb.addItem(coursesData[j]);
        }
    }

    public static boolean CheckIfSubjectExist(String Name, boolean isexist) {
        String[] ar = SubjectsDao.getSubjects();
        int i;
        isexist = false;
        for (i = 0; i < ar.length; i++) {
            if (ar[i].equals(Name)) {
                isexist = true;
                System.out.println("" + ar[i]);

            }
        }
        return isexist;
    }
}
