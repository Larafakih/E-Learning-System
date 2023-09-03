package Model;

import java.sql.*;

/**
 *
 * @author Salma
 */
public class Account {

    private String Email;
    private String password;
    private String role;
    private Date lastTime;

    public Account()
    {
        
    }
    public Account(String Email, String password, String role, Date lastTime) {
        this.Email = Email;
        this.password = password;
        this.role = role;
        this.lastTime = lastTime;
    }

    public Account(String Email, String password, Date lastTime) {
        this.Email = Email;
        this.password = password;
        this.lastTime = lastTime;
    }

    public Account(String Email, String password, String role) {
        this.Email = Email;
        this.password = password;
        this.role = role;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public String toString() {
        return "Account{" + "Email=" + Email + ", password=" + password + ", role=" + role + ", lastTime=" + lastTime + '}';
    }
    
}
