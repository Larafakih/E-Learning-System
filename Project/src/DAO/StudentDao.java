package DAO;

import UI.ReturnDifferentType;
import UI.*;
import Model.*;
import java.io.*;
import java.sql.*;
import util.DBConnection;
import java.util.*;

/**
 *
 * @author Salma
 */
public class StudentDao {

    public static Student SelectFPById(int ID) {
        Student a = new Student();
        DBConnection c1 = new DBConnection();
        try {
            PreparedStatement ps = DBConnection.getConn().prepareStatement("select * from Student where StudentId = '" + ID + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Student(rs.getString("FullName"), rs.getBytes("picture"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public static int UpdatePicture(int ID, FileInputStream fis, File f) {
        int x = 0;
        DBConnection c1 = new DBConnection();
        try {
            PreparedStatement ps = DBConnection.getConn().prepareStatement("update Student SET picture =? Where StudentId =?");
            ps.setBinaryStream(1, (InputStream) fis, (int) f.length());
            ps.setInt(2, ID);
            x = ps.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            DBConnection.Close();
        }
        return x;
    }

    public static int UpdateEmail(String Email, int ID) {
        int x = 0;
        DBConnection c1 = new DBConnection();
        try {
            String q1 = "update Student SET Email = '" + Email + "'"
                    + "Where StudentId ='" + ID + "'";
            x = DBConnection.getStatement().executeUpdate(q1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public static Student SelectStudentById(int ID) {
        Student St = new Student();
        DBConnection c1 = new DBConnection();
        try {
            String q1 = "select * from Student where StudentId='" + ID + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            while (rs.next()) {
                St = new Student(rs.getString("FullName"), rs.getString("Email"), rs.getString("PhoneNb"), rs.getString("Gender"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return St;
    }

    public static int DeleteStudent(int ID) {
        int x = 0;
        DBConnection c1 = new DBConnection();
        try {
            String q = "Delete From Student Where StudentId ='" + ID + "'";
            x = DBConnection.getStatement().executeUpdate(q);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public static int UpdateFullName(String fullname, int ID) {
        int x = 0;
        DBConnection c1 = new DBConnection();
        try {
            String q = "update Student SET FullName = '" + fullname + "' Where StudentId ='" + ID + "'";
            x = DBConnection.getStatement().executeUpdate(q);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public static Student SelectStudent(String email) {
        Student st = new Student();
        DBConnection c1 = new DBConnection();
        try {
            String q1 = "select * from Student where Email='" + email + "'";
            ResultSet rs1 = DBConnection.getStatement().executeQuery(q1);
            while (rs1.next()) {
                st.setId(rs1.getInt(1));
                st.setEmail(rs1.getString("Email"));
                st.setFullName(rs1.getString("FullName"));
                st.setGender(rs1.getString("Gender"));
                st.setPhone(rs1.getString("PhoneNb"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }

    public static int AddStudent(Student s, FileInputStream fis, File f) {
        int x = 0;
        DBConnection c1 = new DBConnection();
        String firstName = s.getFullName();
        String email = s.getEmail();
        String phoneNb = s.getPhone();
        String genderStr = s.getGender();
        try {
            PreparedStatement ps = DBConnection.getConn().prepareStatement("Insert into Student (FullName, Email,PhoneNb ,Gender,picture) "
                    + "values(?,?,?,?,?)");
            ps.setString(1, firstName);
            ps.setString(2, email);
            ps.setString(3, phoneNb);
            ps.setString(4, genderStr);
            ps.setBinaryStream(5, (InputStream) fis, (int) f.length());
            x = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public static ArrayList<Student> getArrayofstudents() {
        ArrayList<Student> arrayst = new ArrayList<>();
        DBConnection c1 = new DBConnection();
        Student st;
        try {
            String q = "select FullName,Email,PhoneNb,Gender,picture from Student";
            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            while (rs.next()) {
                st = new Student(rs.getString("FullName"), rs.getString("Email"), rs.getString("PhoneNb"), rs.getString("Gender"),rs.getBytes("picture"));
                arrayst.add(st);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.Close();
        }
        return arrayst;
    }

    public static ArrayList<Student> getMyStudents(String courseName) {
        ArrayList<Student> arrayst = new ArrayList<>();
        try {
            DBConnection c1 = new DBConnection();
            String q = "Select *"
                    + " From Student As S"
                    + " Inner Join Enrollments As E ON E.StudentID = S.StudentID"
                    + " Where E.CourseID = (select CourseID From Courses Where Name = '" + courseName + "')";

            ResultSet rs = DBConnection.getStatement().executeQuery(q);

            while (rs.next()) {

                Student st = new Student(rs.getString("FullName"), rs.getString("Email"), rs.getString("PhoneNb"), rs.getString("Gender"),rs.getBytes("picture"));
                arrayst.add(st);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayst;
    }
}
