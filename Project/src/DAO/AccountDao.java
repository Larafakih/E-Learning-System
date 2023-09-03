package DAO;

import java.sql.*;
import util.DBConnection;
import Model.*;
import java.util.*;

/**
 *
 * @author Salma
 */
public class AccountDao {

    public static Account SelectAccountByEmail(String email) {
        Account a = new Account();
        DBConnection c1 = new DBConnection();
        try {
            String q = "select * from Account where Email = '" + email + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            while (rs.next()) {
                a = new Account(rs.getString("Email"), rs.getString("password"), rs.getString("role"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public static int UpdateEmail(String Newemail, String email) {
        int x = 0;
        DBConnection c1 = new DBConnection();

        try {
            String q2 = "update Account SET Email = '" + Newemail + "'"
                    + "Where Email ='" + email + "'";
            x = DBConnection.getStatement().executeUpdate(q2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public static int UpdatePassword(String pass, String email) {
        int x = 0;
        DBConnection c1 = new DBConnection();

        try {
            String q = "update Account SET password = '" + pass + "'"
                    + "Where Email ='" + email + "'";
            x = DBConnection.getStatement().executeUpdate(q);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public static int UpdateLastTime(String email, String last_Login) {
        int x = 0;
        DBConnection c1 = new DBConnection();

        try {
            String q = "update Account "
                    + "Set lastTime = '" + last_Login + "'"
                    + "Where Email = '" + email + "'";
            x = DBConnection.getStatement().executeUpdate(q);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public static int DeleteAccount(String email) {
        int x = 0;
        DBConnection c1 = new DBConnection();

        try {
            String q = "Delete From Account Where Email ='" + email + "'";
            x = DBConnection.getStatement().executeUpdate(q);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public static ArrayList<Account> SelectAccount() {
        //Account a = new Account();
        ArrayList<Account> array = new ArrayList<>();
        DBConnection c1 = new DBConnection();

        try {
            String q = "select * from Account";
            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            while (rs.next()) {
                array.add(new Account(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return array;
    }

    public static Account SelectAccount(String email, String pass) {
        Account a = new Account();
        DBConnection c1 = new DBConnection();

        try {
            String q = "select * from Account where Email='" + email + "' and password='" + pass + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            while (rs.next()) {
                a = new Account(rs.getString(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public static int CountAccountByEmail(String email) {
        int counter2 = 0;
        DBConnection c1 = new DBConnection();
        try {
            String query = "Select count(*) as counter from Account Where Email='" + email + "'";
            PreparedStatement p1 = null;
            p1 = DBConnection.getConn().prepareStatement(query);
            ResultSet rs2 = null;
            rs2 = p1.executeQuery();
            if (rs2.next()) {
                counter2 = rs2.getInt(1);
                System.out.println("Account with same email= " + counter2);
            } else {
                System.out.println("error: could not get the record counts for Account");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return counter2;
    }

    public static int InsertIntoAccount(Account a, String role) {
        int x = 0;
        String email = a.getEmail();
        String password = a.getPassword();
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        DBConnection c1 = new DBConnection();
        try {
            PreparedStatement ps1;
            if (role == "Admin") {
                ps1 = DBConnection.getConn().prepareStatement("INSERT INTO Account (Email , password, role, lastTime)"
                        + "values(?,?,'a',?)");
                ps1.setString(1, email);
                ps1.setString(2, password);
                ps1.setDate(3, sqlDate);
                x = ps1.executeUpdate();
            } else if (role == "Student") {
                ps1 = DBConnection.getConn().prepareStatement("Insert into Account (Email , password, role, lastTime) "
                        + "values(?,?,'s',?)");
                ps1.setString(1, email);
                ps1.setString(2, password);
                ps1.setDate(3, sqlDate);
                x = ps1.executeUpdate();
            } else if (role == "Teacher") {
                ps1 = DBConnection.getConn().prepareStatement("Insert into Account (Email , password, role, lastTime) "
                        + "values(?,?,'t',?)");
                ps1.setString(1, email);
                ps1.setString(2, password);
                ps1.setDate(3, sqlDate);
                x = ps1.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }
}
