package DAO;

import java.sql.*;
import util.DBConnection;
import javax.swing.JOptionPane;
import util.*;
import Model.*;
import project.*;
import java.util.*;

/**
 *
 * @author Lara
 */
public class QuizDao {

    public static int getQuizIdByTitle(String t) {
        int QuizId = 0;
        DBConnection c1 = new DBConnection();
        try {
            String q1 = "Select QuizID From Quiz Where Title = '" + t + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            while (rs.next()) {
                QuizId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return QuizId;
    }

    public static String[][] getQuizCourses(int ID) {
        String[][] data = null;
        String[] columnNames = null;
        DBConnection c1 = new DBConnection();

        try {
            String q1 = "Select Title From Quiz Where CourseID = '" + ID + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            columnNames = new String[columnCount];
            int CIndex = 1;
            for (int in = 0; in < columnCount; in++) {
                columnNames[in] = rsmd.getColumnName(CIndex);
                CIndex++;
            }
            data = new String[rowCount][columnCount];
            int row = 0;
            rs.beforeFirst();
            while (rs.next()) {
                for (int c = 0; c < columnCount; c++) {
                    data[row][c] = rs.getString(columnNames[c]);
                }
                row++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static int InsertIntoQuiz(Quiz quiz) {
        int x = 0;
        DBConnection c1 = new DBConnection();
        try {
            PreparedStatement ps = DBConnection.getConn().prepareStatement("INSERT INTO Quiz (Title, Description, TeacherID, CourseID, QuizDate, Duration, FullMarks)" + "values(?,?,?,?,?,?,?)");
            ps.setString(1, quiz.getQuizTitle());
            ps.setString(2, quiz.getQuizDescription());
            ps.setInt(3, quiz.getTeacherID());
            ps.setInt(4, quiz.getCourseID());
            ps.setDate(5, (java.sql.Date) quiz.getQuizDate());
            ps.setTime(6, quiz.getQuizDuration());
            ps.setInt(7, quiz.getFullMarks());

            x = ps.executeUpdate();

//            String q = "INSERT INTO Quiz (Title, Description, TeacherID, CourseID, QuizDate, Duration, FullMarks) "
//                    + "Values ('" + quiz.getQuizTitle() + "', '" 
//                    + quiz.getQuizDescription() + "', '" + quiz.getTeacherID() + "', '"
//                    + quiz.getCourseID() + "', '" + quiz.getQuizDate() + "', '"
//                    + quiz.getQuizDuration() + "', '" + quiz.getFullMarks() + "')";
//            x = DBConnection.getStatement().executeUpdate(q);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public static Quiz SelectQuizDate(int CourseID) {
        Quiz q = new Quiz();
        DBConnection c1 = new DBConnection();
        try {
            String q1 = "Select * From Quiz Where CourseID = '" + CourseID + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            rs.next();
            q = new Quiz(rs.getInt("QuizID"), rs.getInt("CourseID"), rs.getDate("QuizDate"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return q;
    }

    public static int DeleteQuiz(int QuizID, int CourseID) {
        int x = 0;
        DBConnection c1 = new DBConnection();
        try {
            String q = "Delete From Quiz Where QuizID ='" + QuizID + "' and CourseID='" + CourseID + "'";
            x = DBConnection.getStatement().executeUpdate(q);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return x;
    }

    public static Quiz SelectQuizByCourseId(int CourseID) {
        Quiz q = new Quiz();
        DBConnection c1 = new DBConnection();
        try {
            String q1 = "Select * From Quiz Where CourseID = '" + CourseID + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            rs.next();
            q = new Quiz(rs.getInt("QuizID"), rs.getInt("TeacherID"), rs.getInt("CourseID"), rs.getInt("FullMarks"), rs.getString("Title"), rs.getString("Description"), rs.getDate("QuizDate"), rs.getTime("Duration"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return q;
    }

    public static Quiz SelectQuizByquizId(int ID) {
        Quiz q = new Quiz();
        DBConnection c1 = new DBConnection();
        try {
            String q1 = "Select * From Quiz Where QuizID = '" + ID + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            while (rs.next()) {
                q = new Quiz(rs.getInt("QuizID"), rs.getInt("TeacherID"), rs.getInt("CourseID"), rs.getInt("FullMarks"), rs.getString("Title"), rs.getString("Description"), rs.getDate("QuizDate"), rs.getTime("Duration"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return q;
    }

    public static int CountQuizByCourseId(int CourseID) {
        int counter = 0;
        DBConnection c1 = new DBConnection();
        try {
            String query = "Select count(*) as counter from Quiz Where CourseID='" + CourseID + "'";
            PreparedStatement p1 = null;
            p1 = DBConnection.getConn().prepareStatement(query);
            ResultSet rs2 = null;
            rs2 = p1.executeQuery();
            if (rs2.next()) {
                counter = rs2.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return counter;
    }

    public static ArrayList<Quiz> getQuizzes() {
        ArrayList<Quiz> arrayquiz = new ArrayList<>();
        Quiz q;
        DBConnection c1 = new DBConnection();
        try {
            String q1 = "Select * From Quiz";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            while (rs.next()) {
                q = new Quiz(rs.getInt("QuizID"), rs.getInt("TeacherID"), rs.getInt("CourseID"), rs.getInt("FullMarks"), rs.getString("Title"), rs.getString("Description"), rs.getDate("QuizDate"), rs.getTime("Duration"));
                arrayquiz.add(q);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayquiz;
    }

    public static ArrayList<Quiz> getQuizzesForCourse(int Id) {
        ArrayList<Quiz> arrayquiz = new ArrayList<>();
        Quiz q;
        DBConnection c1 = new DBConnection();
        try {
            String q1 = "Select * From Quiz where CourseID='" + Id + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            while (rs.next()) {
                q = new Quiz(rs.getInt("QuizID"), rs.getInt("TeacherID"), rs.getInt("CourseID"), rs.getInt("FullMarks"), rs.getString("Title"), rs.getString("Description"), rs.getDate("QuizDate"), rs.getTime("Duration"));
                arrayquiz.add(q);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayquiz;
    }

    public static ArrayList<Quiz> getQuizzesForTeacher(int ID) {
        ArrayList<Quiz> arrayquiz = new ArrayList<>();
        Quiz q;
        DBConnection c1 = new DBConnection();
        try {
            String q1 = "Select * From Quiz where TeacherID='" + ID + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            while (rs.next()) {
                q = new Quiz(rs.getInt("QuizID"), rs.getInt("TeacherID"), rs.getInt("CourseID"), rs.getInt("FullMarks"), rs.getString("Title"), rs.getString("Description"), rs.getDate("QuizDate"), rs.getTime("Duration"));
                arrayquiz.add(q);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayquiz;
    }

    public static ArrayList<String> getAllTitle() {
        ArrayList<String> array = new ArrayList<>();
        DBConnection c1 = new DBConnection();

        try {
            String q = "select * from Quiz";
            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            while (rs.next()) {
                array.add(rs.getString("Title"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }
}
