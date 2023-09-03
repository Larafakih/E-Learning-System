package DAO;

import Model.*;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;
import util.DBConnection;

/**
 *
 * @author Lara
 */
public class ForumDao {

    public static int InsertIntoForumMessages(ForumMessage FM) {
        int x = 0;
        try {
            DBConnection c1 = new DBConnection();
            PreparedStatement ps = DBConnection.getConn().prepareStatement("INSERT INTO ForumMessages (StudentId, MessageDate, Title, Message) "
                    + "Values (?,?,?,?)");
           ps.setInt(1, FM.getStudentID());
           ps.setDate(2, (java.sql.Date)FM.getMessageDate());
           ps.setString(3, FM.getTitle());
           ps.setString(4, FM.getMessage());
           x=ps.executeUpdate();
               
            //x = DBConnection.getStatement().executeUpdate(q);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return x;
    }

    public static int InsertIntoForumAnswers(ForumAnswer FA) {
        int x = 0;
        try {
            DBConnection c1 = new DBConnection();

            PreparedStatement ps = DBConnection.getConn().prepareStatement("INSERT INTO ForumAnswers (ForumMessageID ,StudentId, AnswerDate, Answer) "
                    + "Values (?,?,?,?)");
            
            
           ps.setInt(1, FA.getForumMessageID());
           ps.setInt(2, FA.getStudentID());
           ps.setDate(3, (java.sql.Date)FA.getAnswerDate());
           ps.setString(4, FA.getAnswer());
           x=ps.executeUpdate();
            
          //  x = DBConnection.getStatement().executeUpdate(q);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return x;
    }

    public static int DeleteForumMessById(int ID) {
        int x = 0;
        try {
            DBConnection c1 = new DBConnection();
            String q3 = "Delete From ForumMessages Where ForumMessageID ='" + ID + "'";
            x = DBConnection.getStatement().executeUpdate(q3);
        } catch (HeadlessException | SQLException exception) {
            exception.printStackTrace();
        }
        return x;
    }
    public static int DeleteForumAnsById(int ID) {
        int x = 0;
        try {
            DBConnection c1 = new DBConnection();
            String q3 = "Delete From ForumAnswers Where ForumAnswerID ='" + ID + "'";
            x = DBConnection.getStatement().executeUpdate(q3);
        } catch (HeadlessException | SQLException exception) {
            exception.printStackTrace();
        }
        return x;
    }


    public static ForumMessage SelectForumMessageById(int ID) {
        ForumMessage fm=null;
        DBConnection c1 = new DBConnection();
        try {
            String q1 = "select * from ForumMessages where ForumMessageID='" + ID + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            while (rs.next()) {
                fm = new ForumMessage(rs.getInt("ForumMessageID"), rs.getInt("StudentID"), rs.getDate("MessageDate"), rs.getString("Title"), rs.getString("Message"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return fm;
    }
    public static ArrayList<ForumMessage> loadForumMessages() {
        ArrayList<ForumMessage> arraym = new ArrayList<>();
        int count = 0;
        DBConnection c1 = new DBConnection();
        try {

            String q = "Select * from ForumMessages";
            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            while (rs.next()) {

                ForumMessage f = new ForumMessage(rs.getInt("ForumMessageID"), rs.getInt("StudentId"), rs.getDate("MessageDate"), rs.getString("Title"), rs.getString("Message"));
                arraym.add(f);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return arraym;
    }
    
    public static ArrayList<ForumAnswer> loadMessageAnswers(int id) {
        ArrayList<ForumAnswer> arrayAns = new ArrayList<>();
        int count = 0;
        DBConnection c1 = new DBConnection();
        try {

            String q = "Select * from ForumAnswers where ForumMessageID='" + id + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            while (rs.next()) {
                
                ForumAnswer fa = new ForumAnswer(rs.getInt("ForumAnswerID"),rs.getInt("ForumMessageID"), rs.getInt("StudentId"), rs.getDate("AnswerDate"), rs.getString("Answer"));
                arrayAns.add(fa);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayAns;
    }

    public static int getNbofAnswers(int id) {
        int count = 0;
        DBConnection c1 = new DBConnection();
        try {

            String q3 = "Select Count(*) From ForumAnswers "
                    + "Where ForumMessageID = '" + id + "'";
            ResultSet rs3 = DBConnection.getStatement().executeQuery(q3);
            while (rs3.next()) {
                count = rs3.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static int updateForumMessage (ForumMessage fm)
    {
        int x=0;
       try {
            DBConnection c1 = new DBConnection();
            String q = "UPDATE  ForumMessages SET Title=?, Message=? WHERE ForumMessageID=?";
      
      PreparedStatement preparedStmt =DBConnection.getConn().prepareStatement(q);
      preparedStmt.setString(1, fm.getTitle());
      preparedStmt.setString(2, fm.getMessage());
      preparedStmt.setInt(3, fm.getForumMessageID());

      x=preparedStmt.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        } 
       return x;
    }
    public static int updateForumAnswer (ForumAnswer fa)
    {
        int x=0;
       try {
            DBConnection c1 = new DBConnection();
            String q = "UPDATE  ForumAnswers SET Answer=? WHERE ForumAnswerID=?";
      
      PreparedStatement preparedStmt =DBConnection.getConn().prepareStatement(q);
      
      preparedStmt.setString(1, fa.getAnswer());
      preparedStmt.setInt(2, fa.getForumAnswerID());
      
      x=preparedStmt.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        } 
       return x;
    }
    public static String[][] loadMessageAnswers(ForumMessage FM) {
        String[][] forumAnswersListData = null;
        //  int nbOfAnswers[];
        try {
            DBConnection c1 = new DBConnection();
            String q = "Select FA.ForumAnswerID, FA.ForumMessageID, FA.StudentId, FA.AnswerDate, FA.Answer  From ForumAnswers As FA"
                    + "Where FA.ForumMessageID = '" + FM + "'";;

            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            forumAnswersListData = new String[rowCount][7];

            rs.beforeFirst();
            int i = 0;
            DBConnection c = new DBConnection();
            while (rs.next()) {
                forumAnswersListData[i][0] = rs.getString("ForumMessageID");
                forumAnswersListData[i][1] = rs.getString("ForumAnswerID");
                forumAnswersListData[i][2] = rs.getString("StudentId");
                forumAnswersListData[i][3] = rs.getString("AnswerDate");
                forumAnswersListData[i][4] = rs.getString("Answer");

                //get Name of Student Answering:
                String q2 = "Select FullName From Student "
                        + "Where StudentId = '" + forumAnswersListData[i][2] + "'";
                ResultSet rs2 = DBConnection.getStatement().executeQuery(q2);
                rs2.next();
                String StdName = rs2.getString("FullName");
                rs2.beforeFirst();
                forumAnswersListData[i][5] = StdName;

                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return forumAnswersListData;
    }
}
