package Model;

import DAO.AccountDao;

/**
 *
 * @author Salma
 */
public class Teacher extends User {

    private int TeacherId;
    private String FullName;
    private String Email;
    private String Phone;
    private String Gender;
    private byte[] picture = null;
    String Role = "Teacher";

    public Teacher() {

    }

    public Teacher(int TeacherId, String FullName, String Email, String Phone, String Gender) {
        this.TeacherId = TeacherId;
        this.FullName = FullName;
        this.Email = Email;
        this.Phone = Phone;
        this.Gender = Gender;
    }

    public Teacher(String FullName, String Email, String Phone, String Gender) {
        this.FullName = FullName;
        this.Email = Email;
        this.Phone = Phone;
        this.Gender = Gender;
    }

    public Teacher(String FullName, String Email, String Phone, String Gender, byte[] p) {
        this.FullName = FullName;
        this.Email = Email;
        this.Phone = Phone;
        this.Gender = Gender;
        this.picture = p;
    }

    public Teacher(String FullName, byte[] pi) {
        this.FullName = FullName;
        this.picture = pi;
    }

    public String getRole() {
        return Role;
    }

    public int getId() {
        return TeacherId;
    }

    public void setId(int TeacherId) {
        this.TeacherId = TeacherId;
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

    public void setPassword(String password) {

    }

    @Override
    public String toString() {
        return "Teacher{" + "FullName=" + FullName + ", Email=" + Email + ", Phone=" + Phone + ", Gender=" + Gender + ", picture=" + picture + ", Role=" + Role + '}';
    }

    public String getFirstName(String name) {
        return name.split(" (?!.* )")[0];
    }

    public String getLastname(String name) {
        return name.split(" (?!.* )")[1];
    }
}
