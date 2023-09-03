package DAO;

import Model.*;
import java.io.*;
import java.sql.*;
import util.DBConnection;
import java.util.*;

/**
 *
 * @author Salma
 */
public class BookDao {

    public static Blob SelectBookById(int ID) {
        Book a = new Book();
        DBConnection c1 = new DBConnection();
        Blob b = null;
        try {
            PreparedStatement ps = DBConnection.getConn().prepareStatement("select * from Book where BookId = '" + ID + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // a = new Book(rs.getBytes("BookPdf"));
                b = rs.getBlob("BookPdf");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public static ArrayList<Blob> SelectAllBookByCourseId(int ID) {
        // Book a = new Book();
        //Blob[] b=null;
        ArrayList<Blob> b1 = new ArrayList<>();
        DBConnection c1 = new DBConnection();
        try {
            PreparedStatement ps = DBConnection.getConn().prepareStatement("select * from Book where CourseID = '" + ID + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // a = new Book(rs.getBytes("BookPdf"));
                b1.add(rs.getBlob("BookPdf"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b1;
    }

    public static Blob SelectBookByCourseId(int CourseID) {
        Blob b = null;
        DBConnection c1 = new DBConnection();
        try {
            PreparedStatement ps = DBConnection.getConn().prepareStatement("select * from Book where CourseID = '" + CourseID + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                // a = new Book(rs.getBytes("BookPdf"));
                b = rs.getBlob("BookPdf");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }

    public static ArrayList<String> getBooksNameByCourseId(int CourseID) {
        ArrayList<String> array = new ArrayList<>();
        DBConnection c1 = new DBConnection();

        try {
            String q = "select * from Book where CourseID="+CourseID+"";
            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            while (rs.next()) {
                array.add(rs.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static Book SelectBookByName(String Name) {
        Book a = new Book();
        InputStream in = null;
        DBConnection c1 = new DBConnection();
        try {
            PreparedStatement ps = DBConnection.getConn().prepareStatement("select * from Book where Name = '" + Name + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Book(rs.getBytes("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public static int AddBook(Book b, FileInputStream fis, File f) {
        int x = 0;
        String Name = b.getName();
        int CourseID = b.getCourseID();
        DBConnection c1 = new DBConnection();
        try {
            PreparedStatement ps = DBConnection.getConn().prepareStatement("Insert into Book (Name,BookPdf,CourseID) "
                    + "values(?,?,?)");
            ps.setString(1, Name);
            ps.setBinaryStream(2, (InputStream) fis, (int) f.length());
            ps.setInt(3, CourseID);
            x = ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBConnection.Close();
        }
        return x;
    }

    public static int CountBookByCourseID(int CourseID) {
        int counter = 0;
        DBConnection c1 = new DBConnection();
        try {
            System.out.println("" + CourseID);
            String query = "Select count(*) as counter from Book Where CourseID='" + CourseID + "'";
            PreparedStatement p1 = null;
            p1 = DBConnection.getConn().prepareStatement(query);
            ResultSet rs2 = null;
            rs2 = p1.executeQuery();
            if (rs2.next()) {
                counter = rs2.getInt(1);
                System.out.println("nb of Book for this courseID = " + counter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counter;
    }
}
