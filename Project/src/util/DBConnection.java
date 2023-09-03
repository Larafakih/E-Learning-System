package util;

import java.sql.*;
import java.util.logging.*;

public class DBConnection {

    private static Connection conn ;
    private static Statement statement;
    private static final String DB_DRIVER
            = "com.mysql.jdbc.Driver";
    private static final String DB_URL
            = "jdbc:mysql:///ELearningSystem";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "1234mysql";

    public DBConnection() {
        try {
            //Register JDBC Driver with Class's Static method
            Class.forName(DB_DRIVER);
            conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                  ResultSet.CONCUR_UPDATABLE);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }
    }

    public static Statement getStatement() {
        //System.out.println("get Statement");
        return statement;
    }

    public static Connection getConn() {
        return conn;
    }

    public static void Close() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
