package Controller;

import DAO.*;
import Model.*;
import javax.swing.*;
import Admin.*;

public class AdminController {

    public static void DeleteTeacher(String email, JFrame fr,int adminId) {
        Teacher te = TeacherDao.SelectTeacher(email);
        int id = te.getId();
        int x = TeacherDao.DeleteTeacher(id);
        int x2 = AccountDao.DeleteAccount(email);
        if (x == 0 || x2 == 0) {
            System.out.println("Error Occured");
        } else {
            JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
            fr.dispose();
            new ViewTeachers(adminId);
        }
    }

    public static void DeleteStudent(String email,JFrame f,int adminId) {
        Student stt = StudentDao.SelectStudent(email);
        int id = stt.getId();
        int x = StudentDao.DeleteStudent(id);
        int x2 = AccountDao.DeleteAccount(email);
        if (x == 0 || x2 == 0) {
            System.out.println("Error Occured");
        } else {
            JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
            f.dispose();
            new ViewStudents(adminId);
        }
    }
}
