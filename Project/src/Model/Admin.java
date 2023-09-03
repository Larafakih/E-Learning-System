package Model;

import DAO.AccountDao;
import javax.swing.UIManager;

/**
 *
 * @author Salma
 */
public class Admin extends User {

    private int AdminId;
    private String FullName;
    private String Email;
    private String Phone;
    private String Gender;
    private byte[] picture = null;
    private String Role = "Admin";

    public Admin() {

    }

    public Admin(int AdminId, String FullName, String Email, String Phone, String Gender, byte[] pi) {
        this.AdminId = AdminId;
        this.FullName = FullName;
        this.Email = Email;
        this.Phone = Phone;
        this.Gender = Gender;
        this.picture = pi;
    }

    public Admin(String FullName, String Email, String Phone, String Gender) {
        this.FullName = FullName;
        this.Email = Email;
        this.Phone = Phone;
        this.Gender = Gender;
    }

    public Admin(String FullName, byte[] pi) {
        this.FullName = FullName;
        this.picture = pi;
    }

    public String getRole() {
        return Role;
    }

    public int getId() {
        return AdminId;
    }

    public void setId(int AdminId) {
        this.AdminId = AdminId;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getPassword() {
        String password;
        System.out.println("" + Email);
        Account account = AccountDao.SelectAccountByEmail(Email);
        password = account.getPassword();
        return password;
    }

    @Override
    public String toString() {
        return "Admin{" + "FullName=" + FullName + ", Email=" + Email + ", Phone=" + Phone + ", Gender=" + Gender + ", picture=" + picture + ", Role=" + Role + '}';
    }

    public void setPassword(String password) {
        // this.Password=Password;
    }

    public String getFirstName(String name) {
        return name.split(" (?!.* )")[0];
    }

    public String getLastname(String name) {
        return name.split(" (?!.* )")[1];
    }

}
