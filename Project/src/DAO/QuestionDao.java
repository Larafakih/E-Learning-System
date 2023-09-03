package DAO;

import java.sql.*;
import javax.swing.JOptionPane;
import util.*;
import Model.*;
import project.*;
import java.util.*;

/**
 *
 * @author Lara
 */
public class QuestionDao {

    public int insertQuestion(Question qu) {
        int x=0;
        DBConnection c1 = new DBConnection();
        try {
            String q = "INSERT INTO questions ( QuizID, QuestionTitle,Answer1,Answer2,Answer3,CorrectAnswer) "
                    + "Values (?,?,?,?,?,?)";
            PreparedStatement ps = DBConnection.getConn().prepareStatement(q);
            ps.setInt(1, qu.getQuizID());
            ps.setString(2, qu.getQuestionTitle());
            ps.setString(3, qu.getAnswer1());
            ps.setString(4, qu.getAnswer2());
            ps.setString(5, qu.getAnswer3());
            ps.setString(6, qu.getCorrectAnswer());

            x = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return x;
    }
    public static int insertQuestion(QuestionStore qStore) 
    {
        int x=0;
        DBConnection c1 = new DBConnection();
        try {
            
            String query = "INSERT INTO questions (QuizID, QuestionTitle, Answer1, Answer2, Answer3, CorrectAnswer, Qnb)"
                    + "Values (?,?,?,?,?,?,?)";
            ArrayList<Question> questionList=qStore.getAllQuestions();
            PreparedStatement ps = DBConnection.getConn().prepareStatement(query);
            for(Question qu: questionList){
            ps.setInt(1, qu.getQuizID());
           System.out.println( qu.getQuizID()+"question");
            ps.setString(2, qu.getQuestionTitle());
            ps.setString(3, qu.getAnswer1());
            ps.setString(4, qu.getAnswer2());
            ps.setString(5, qu.getAnswer3());
            ps.setString(6, qu.getCorrectAnswer());
            ps.setInt(7, qu.getQnb());
            x =  ps.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return x;
    }
    public static void updateQuestions(QuestionStore qstore) throws SQLException{
        DBConnection c1 = new DBConnection();
        PreparedStatement ps=DBConnection.getConn().prepareStatement("update questions set QuestionTitle=?,Answer1=?,Answer2=?,Answer3=?,CorrectAnswer=? where Qnb=? and QuizID=?");
        ArrayList<Question> questionList=qstore.getAllQuestions();
        for(Question q: questionList){
            ps.setString(1, q.getQuestionTitle());
            ps.setString(2, q.getAnswer1());
            ps.setString(3, q.getAnswer2());
            ps.setString(4, q.getAnswer3());
            ps.setString(5, q.getCorrectAnswer());
            ps.setInt(6, q.getQnb());
            ps.setInt(7,q.getQuestionID());
            ps.executeUpdate();
        }
    }
    public static int CountQuestionByQuizId(int QuizID) {
        int counter = 0;
        DBConnection c1 = new DBConnection();
        try {
            String query = "Select count(*) as counter from Questions Where QuizID='" + QuizID + "'";
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

    public static ArrayList<Question> getQuestionByQuizId(int QuizId) {
        ArrayList<Question> questionList = new ArrayList<>();
        DBConnection c1 = new DBConnection();
        try {
           PreparedStatement ps = DBConnection.getConn().prepareStatement("select * from Questions where QuizID='" + QuizId + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String question = rs.getString("QuestionTitle");
                String option1 = rs.getString("Answer1");
                String option2 = rs.getString("Answer2");
                String option3 = rs.getString("Answer3");
                String correctAnswer = rs.getString("CorrectAnswer");
                int Qnb = rs.getInt("Qnb");
                Question ques = new Question(QuizId, question, option1, option2, option3, correctAnswer, Qnb);
                questionList.add(ques);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionList;
    }
}
