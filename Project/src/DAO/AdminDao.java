package DAO;

import util.*;
import Model.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Salma
 */
//DAO : Data Access Object 
public class AdminDao {

    public static Admin SelectFPById(int ID) {
        Admin a = new Admin();
        DBConnection c1 = new DBConnection();

        try {
            PreparedStatement ps = DBConnection.getConn().prepareStatement("select * from Admin where AdminId = '" + ID + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Admin(rs.getString("FullName"), rs.getBytes("picture"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public static Admin SelectAdminById(int ID) {
        Admin a = new Admin();
        DBConnection c1 = new DBConnection();

        try {
            String q1 = "select * from Admin where AdminId = '" + ID + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            while (rs.next()) {
                a = new Admin(rs.getString("FullName"), rs.getString("Email"), rs.getString("Phone"), rs.getString("Gender"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public static int UpdateFullName(String fullname, int ID) {
        int x = 0;
        DBConnection c1 = new DBConnection();

        try {
            String q = "update Admin SET FullName = '" + fullname + "'" + "Where AdminId ='" + ID + "'";
            x = DBConnection.getStatement().executeUpdate(q);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public static int UpdatePicture(int ID, FileInputStream fis, File f) {
        int x = 0;
        DBConnection c1 = new DBConnection();

        try {
            PreparedStatement ps = DBConnection.getConn().prepareStatement("update Admin SET picture =? Where AdminId =?");
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
            String q1 = "update Admin SET Email = '" + Email + "'"
                    + "Where AdminId ='" + ID + "'";
            x = DBConnection.getStatement().executeUpdate(q1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public static int DeleteAdmin(int ID) {
        int x = 0;
        DBConnection c1 = new DBConnection();
        try {
            String q = "Delete From Admin Where AdminId ='" + ID + "'";
            x = DBConnection.getStatement().executeUpdate(q);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public static Admin SelectAdmin(String email) {
        Admin admin = new Admin();
        DBConnection c1 = new DBConnection();
        try {
            String q1 = "select * from Admin where Email='" + email + "'";
            ResultSet rs1 = DBConnection.getStatement().executeQuery(q1);
            while (rs1.next()) {
                admin = new Admin(rs1.getInt("AdminId"), rs1.getString("FullName"), rs1.getString("Email"), rs1.getString("Phone"), rs1.getString("Gender"), rs1.getBytes("picture"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }

    public static int AddAdmin(Admin a, FileInputStream fis, File f) {
        int x = 0;
        String fullName = a.getFullName();
        String email = a.getEmail();
        String phone = a.getPhone();
        String genderStr = a.getGender();
        DBConnection c1 = new DBConnection();
        try {
            PreparedStatement ps = DBConnection.getConn().prepareStatement("Insert into Admin (FullName,Email,Phone, Gender,picture) "
                    + "values(?,?,?,?,?)");
            ps.setString(1, fullName);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, genderStr);
            ps.setBinaryStream(5, (InputStream) fis, (int) f.length());
            x = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.Close();
        }
        return x;
    }

    public static int CountAdminByEmail(String email) {
        int counter2 = 0;
        DBConnection c1 = new DBConnection();
        try {
            String query = "Select count(*) as counter from Admin Where Email='" + email + "'";
            PreparedStatement p1 = null;
            p1 = DBConnection.getConn().prepareStatement(query);
            ResultSet rs2 = null;
            rs2 = p1.executeQuery();
            if (rs2.next()) {
                counter2 = rs2.getInt(1);
                System.out.println("Admin with same email= " + counter2);
            } else {
                System.out.println("error: could not get the record counts for Admin");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counter2;
    }
}
