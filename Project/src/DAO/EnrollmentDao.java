package DAO;

import java.sql.*;
import javax.swing.*;
import util.DBConnection;
import Model.*;

/**
 *
 * @author Salma
 */
public class EnrollmentDao {

    public static int DeleteEnrollment(String course_Name, int ID) {
        int x = 0;
        DBConnection c1 = new DBConnection();
        try {
            String q = "Delete From Enrollments Where CourseID = (Select CourseID From Courses Where Name = '" + course_Name + "')"
                    + " And StudentID = '" + ID + "'";
            x = DBConnection.getStatement().executeUpdate(q);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public static int InsertIntoEnrollment(Enrollment e) {
        int x = 0;
        DBConnection c1 = new DBConnection();
        try {
            String q = "INSERT INTO Enrollments (Enrollment_Date, CourseID, StudentID) "
                    + "Values ('" + e.getEnrollment_Date() + "', '" + e.getCourseID() + "', '" + e.getStudentId() + "')";
            x = DBConnection.getStatement().executeUpdate(q);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return x;
    }

    public static boolean selectEnrollement(int CourseID, int StudentID) {
        Enrollment en = new Enrollment();
        boolean isExisted = false;
        DBConnection c1 = new DBConnection();
        try {
            String q = "Select * from Enrollments where CourseID='" + CourseID + "' and StudentID='" + StudentID + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            while (rs.next()) {
                en = new Enrollment(rs.getDate("Enrollment_Date"), rs.getInt("CourseID"), rs.getInt("StudentID"));
                isExisted = true;
                System.out.println("" + en.getCourseID() + "-" + en.getStudentId());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return isExisted;
    }
}
