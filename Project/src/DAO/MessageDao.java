package DAO;

import java.awt.*;
import java.sql.*;
import util.DBConnection;
import Model.*;

/**
 *
 * @author Salma
 */
public class MessageDao {

    public static int InsertIntoMessage(Messages M) {
        int x = 0;
        DBConnection c1 = new DBConnection();
        try {
            String q = "INSERT INTO Messages (message, time, UserID, toUser_ID) "
                    + "Values ('" + M.getMessage() + "', '" + M.getTime() + "', '" + M.getUserID() + "',"
                    + " '" + M.getToUser_ID() + "')";
            x = DBConnection.getStatement().executeUpdate(q);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return x;
    }

    public static int DeleteById(int ID) {
        int x = 0;
        DBConnection c1 = new DBConnection();
        try {
            String q3 = "Delete From Messages Where MessageID ='" + ID + "'";
            x = DBConnection.getStatement().executeUpdate(q3);
        } catch (HeadlessException | SQLException exception) {
            exception.printStackTrace();
        }
        return x;
    }

    public static int CountInboxByStdId(int StdID) {
        int counter = 0;
        DBConnection c1 = new DBConnection();
        try {
            String query = "Select count(*) as counter from Messages Where toUser_ID='" + StdID + "'";
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
    public static String[][] loadMessagesInbox(int ID) {
        String[][] messagesListData = null;
        try {
            DBConnection c1 = new DBConnection();

            String q = "Select M.MessageID, M.time, M.UserID, M.message ,M.toUser_ID From Messages As M"
                    + " where M.UserID <> '" + ID + "'";

            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            messagesListData = new String[rowCount][6];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                messagesListData[i][0] = rs.getString("MessageID");
                messagesListData[i][1] = rs.getString("time");
                messagesListData[i][2] = rs.getString("UserID");
                messagesListData[i][3] = rs.getString("message");
                Student s = StudentDao.SelectFPById(Integer.parseInt(messagesListData[i][2]));
                messagesListData[i][4] = s.getFullName();
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return messagesListData;
    }

    public static String[][] loadMessagesSent(int ID) {
        String[][] messagesListData = null;
        ResultSet rs;
        DBConnection c1 = new DBConnection();
        try {
            String q = "Select M.MessageID, M.time, M.UserID, M.message, M.toUser_ID From Messages As M"
                    + " where M.UserID =" + ID + "";

            rs = DBConnection.getStatement().executeQuery(q);
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            messagesListData = new String[rowCount][7];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                messagesListData[i][0] = rs.getString("MessageID");
                messagesListData[i][1] = rs.getString("time");
                messagesListData[i][2] = rs.getString("UserID");
                messagesListData[i][3] = rs.getString("message");
                messagesListData[i][4] = rs.getString("toUser_ID");
                Student s = StudentDao.SelectFPById(Integer.parseInt(messagesListData[i][4]));
                messagesListData[i][5] =s.getFullName();
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return messagesListData;
    }
}
