package DAO;

import UI.img;
import Model.*;
import java.io.*;
import java.sql.*;
import UI.ReturnDifferentType;
import java.util.ArrayList;
import util.DBConnection;
import project.*;

/**
 *
 * @author Salma
 */
public class TeacherDao {

    public static Teacher SelectFPById(int ID) {
        Teacher a = new Teacher();
        DBConnection c1 = new DBConnection();
        try {
            PreparedStatement ps = DBConnection.getConn().prepareStatement("select * from Teacher where TeacherId = '" + ID + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Teacher(rs.getString("FullName"), rs.getBytes("picture"));
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
            PreparedStatement ps = DBConnection.getConn().prepareStatement("update Teacher SET picture =? Where TeacherID =?");
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

    public static int UpdateFullName(String fullname, int ID) {
        int x = 0;
        DBConnection c1 = new DBConnection();
        try {
            String q = "update Teacher SET FullName = '" + fullname + "'" + "Where TeacherId ='" + ID + "'";
            x = DBConnection.getStatement().executeUpdate(q);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public static Teacher SelectTeacherById(int ID) {
        Teacher a = new Teacher();
        DBConnection c1 = new DBConnection();
        try {
            String q1 = "select * from Teacher where TeacherId = '" + ID + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            while (rs.next()) {
                a = new Teacher(rs.getString("FullName"), rs.getString("Email"), rs.getString("PhoneNb"), rs.getString("Gender"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public static int UpdateEmail(String Email, int ID) {
        int x = 0;
        DBConnection c1 = new DBConnection();
        try {
            String q1 = "update Teacher SET Email = '" + Email + "'"
                    + "Where TeacherId ='" + ID + "'";
            x = DBConnection.getStatement().executeUpdate(q1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public static Teacher SelectTeacher(String email) {
        Teacher st = new Teacher();
        DBConnection c1 = new DBConnection();
        try {
            String q1 = "select * from Teacher where Email='" + email + "'";
            ResultSet rs1 = DBConnection.getStatement().executeQuery(q1);
            while (rs1.next()) {
                st.setId(rs1.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return st;
    }

    public static int AddTeacher(Teacher t, FileInputStream fis, File f) {
        int x = 0;
        String firstName = t.getFullName();
        String email = t.getEmail();
        String phoneNb = t.getPhone();
        String genderStr = t.getGender();
        DBConnection c1 = new DBConnection();
        try {
            PreparedStatement ps = DBConnection.getConn().prepareStatement("Insert into Teacher (FullName, Email,PhoneNb ,Gender,picture) "
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

    public static int DeleteTeacher(int id) {
        int x = 0;
        DBConnection c1 = new DBConnection();
        try {
            String q = "Delete From Teacher Where TeacherId ='" + id + "'";
            x = DBConnection.getStatement().executeUpdate(q);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return x;
    }

    public static int DeleteTeacher(String id) {
        int x = 0;
        DBConnection c1 = new DBConnection();
   try {
            String q = "Delete From Teacher Where TeacherId ='" + id + "'";
            x = DBConnection.getStatement().executeUpdate(q);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return x;
    }

    public static ArrayList<Teacher> getArrayTeachers() {
        ArrayList<Teacher> arrayte = new ArrayList<>();
        Teacher t;
        DBConnection c1 = new DBConnection();
        try {
            String q = "select FullName,Email,PhoneNb,Gender,picture from Teacher";
            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            while (rs.next()) {
                t = new Teacher(rs.getString("FullName"), rs.getString("Email"), rs.getString("PhoneNb"), rs.getString("Gender"),rs.getBytes("picture"));
                arrayte.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            c1.Close();
        }
        return arrayte;
    }
}
