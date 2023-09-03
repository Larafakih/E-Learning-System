package DAO;

import java.sql.*;
import util.DBConnection;
import Model.*;
import java.util.ArrayList;

public class PerformanceDAO {

    public static ArrayList<Integer> getAllQuizId(int studentId) {
        ArrayList<Integer> examIdList = new ArrayList<>();
        DBConnection c1 = new DBConnection();
        try {
            Connection conn = DBConnection.getConn();
            PreparedStatement ps = conn.prepareStatement("select * from Performance where StudentId='" + studentId + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                examIdList.add(rs.getInt("QuizID"));
            }
        } catch (SQLException e) {
        }
        return examIdList;
    }

    public static ArrayList<Integer> get(int StudentId, int QuizID) {
        ArrayList<Integer> examIdList = new ArrayList<>();
        DBConnection c1 = new DBConnection();
        try {
            Connection conn = DBConnection.getConn();
            PreparedStatement ps = conn.prepareStatement("select * from Performance where StudentId='" + StudentId + "' and QuizID='" + QuizID + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                examIdList.add(rs.getInt("QuizID"));
            }
        } catch (SQLException e) {
        }
        return examIdList;
    }

    public static void addPerformance(Performance performance) {
        try {
            DBConnection c1 = new DBConnection();
            Connection conn = DBConnection.getConn();
            PreparedStatement ps = conn.prepareStatement("insert into Performance(StudentId,QuizID ,RightA,WrongA,Unattempted ,Per,CourseName) values(?,?,?,?,?,?,?)");
            ps.setInt(1, performance.getStudentId());
            ps.setInt(2, performance.getQuizID());
            ps.setInt(3, performance.getRightA());
            ps.setInt(4, performance.getWrongA());
            ps.setInt(5, performance.getUnattempted());
            ps.setDouble(6, performance.getPer());
            ps.setString(7, performance.getCourseName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Performance> getAllData() {
        ArrayList<Performance> performanceList = new ArrayList<>();
        DBConnection c1 = new DBConnection();
        try {
            Connection conn = DBConnection.getConn();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from Performance");

            while (rs.next()) {
                int userId = rs.getInt("StudentId");
                int examId = rs.getInt("QuizID");
                int right = rs.getInt("RightA");
                int wrong = rs.getInt("WrongA");
                int unattempted = rs.getInt("Unattempted");
                double per = rs.getDouble("Per");
                String Coursename = rs.getString("CourseName");
                Performance p = new Performance(userId, examId, right, wrong, unattempted, per, Coursename);
                performanceList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return performanceList;
    }

    public static ArrayList<Performance> getDataByCoursename(String name) {
        ArrayList<Performance> performanceList = new ArrayList<>();
        DBConnection c1 = new DBConnection();
        try {
            Connection conn = DBConnection.getConn();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from Performance where CourseName='" + name + "'");

            while (rs.next()) 
            {
                int userId = rs.getInt("StudentId");
                int examId = rs.getInt("QuizID");
                int right = rs.getInt("RightA");
                int wrong = rs.getInt("WrongA");
                int unattempted = rs.getInt("Unattempted");
                double per = rs.getDouble("Per");
              //  String Coursename = rs.getString("CourseName");
                Performance p = new Performance(userId, examId, right, wrong, unattempted, per);
                performanceList.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return performanceList;
    }

    public static ArrayList<String> getAllStudentId() throws SQLException {
        Connection conn = DBConnection.getConn();
        Statement st = conn.createStatement();
        DBConnection c1 = new DBConnection();

        ResultSet rs = st.executeQuery("select distinct StudentId from Performance");
        ArrayList<String> studentIdList = new ArrayList<>();
        while (rs.next()) {
            studentIdList.add(rs.getString(1));
        }
        return studentIdList;
    }

    public static StudentScore getStudentScore(int studentId, int quizid) {
        StudentScore score = null;
        DBConnection c1 = new DBConnection();
        try {
            Connection conn = DBConnection.getConn();
            PreparedStatement ps = conn.prepareStatement("select CourseName,Per from Performance where StudentId='" + studentId + "' and QuizID='" + quizid + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                score = new StudentScore(rs.getString(1), rs.getDouble(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return score;
    }

    public static String getCourseName(int userId, int examId) {
        String name = null;
        DBConnection c1 = new DBConnection();
        Connection conn = DBConnection.getConn();
        try {
            PreparedStatement ps = conn.prepareStatement("select CourseName from Performance where StudentId=? and QuizID=?");
            ps.setInt(1, userId);
            ps.setInt(2, examId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            name = rs.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public static double getPercentage(int userId, int examId) throws SQLException {
        Connection conn = DBConnection.getConn();
        DBConnection c1 = new DBConnection();
        PreparedStatement ps = conn.prepareStatement("select Per from Performance where StudentId=? and QuizID=?");
        ps.setInt(1, userId);
        ps.setInt(2, examId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getDouble(1);
    }
}
