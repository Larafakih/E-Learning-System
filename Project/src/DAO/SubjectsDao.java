package DAO;

import java.sql.*;
import javax.swing.JOptionPane;
import util.*;
import Model.*;

/**
 *
 * @author Salma
 */
//DAO : Data Access Object
public class SubjectsDao {

    public static int AddSubject(Subject s) {
        int x = 0;
        DBConnection c1 = new DBConnection();
        String Name = s.getName();
        int AdminID = s.getAdminId();
        try {
            String q = "INSERT INTO Subjects (Name, AdminId) "
                    + "Values ('" + Name + "', '" + AdminID + "')";

            x = DBConnection.getStatement().executeUpdate(q);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public static int DeleteSubject(String subjectName) {
        int x = 0;
        DBConnection c1 = new DBConnection();
        try {
            String q = "Delete From Subjects Where Name ='" + subjectName + "'";
            x = DBConnection.getStatement().executeUpdate(q);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.Close();
        }
        return x;
    }

    public static String[] getSubjects() {
        String[] subjectsData = null;
        DBConnection c1 = new DBConnection();
        try {
            String q = "select * from Subjects";
            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            subjectsData = new String[rowCount];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                subjectsData[i] = rs.getString("Name");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjectsData;
    }

    public static int getSubjects(String subjectName) {
        int subjectID = 0;
        DBConnection c1 = new DBConnection();
        try {
            String q1 = "Select SubjectID From Subjects Where Name = '" + subjectName + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            rs.next();
            subjectID = rs.getInt("SubjectID");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (subjectID == 0) {
            JOptionPane.showMessageDialog(null, "Error in get Subject!");
        }
        return subjectID;
    }

    public static String getSubjectsname(int id) {
        String name = null;
        DBConnection c1 = new DBConnection();
        try {
            String q1 = "Select Name From Subjects Where SubjectID = '" + id + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            rs.next();
            name = rs.getString("Name");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return name;
    }

    public static String[] getSubjectsName() {
        String[] subjects = null;
        DBConnection c1 = new DBConnection();
        try {
            String q = "select Name From Subjects";

            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            subjects = new String[rowCount + 1];
            subjects[0] = "All";
            int row = 1;
            rs.beforeFirst();
            while (rs.next()) {
                subjects[row] = rs.getString("Name");
                row++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }
}
