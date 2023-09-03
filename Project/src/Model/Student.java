package Model;

import DAO.AccountDao;

/**
 *
 * @author Salma
 */
public class Student extends User {

    private int StudentId;
    private String FullName;
    private String Email;
    private String Phone;
    private String Gender;
    private byte[] picture = null;
    private String Role = "Student";

    public Student() {

    }

    public Student(int StudentId, String FullName, String Email, String Phone, String Gender) {
        this.StudentId = StudentId;
        this.FullName = FullName;
        this.Email = Email;
        this.Phone = Phone;
        this.Gender = Gender;
    }

    public Student(String FullName, String Email, String Phone, String Gender) {
        this.FullName = FullName;
        this.Email = Email;
        this.Phone = Phone;
        this.Gender = Gender;
    }

    public Student(String FullName, String Email, String Phone, String Gender, byte[] p) {
        this.FullName = FullName;
        this.Email = Email;
        this.Phone = Phone;
        this.Gender = Gender;
        this.picture = p;
    }

    public Student(String FullName, byte[] pi) {
        this.FullName = FullName;
        this.picture = pi;
    }

    public String getRole() {
        return Role;
    }

    public int getId() {
        return StudentId;
    }

    public void setId(int StudentId) {
        this.StudentId = StudentId;
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
        return "Student{" + "FullName=" + FullName + ", Email=" + Email + ", Phone=" + Phone + ", Gender=" + Gender + ", picture=" + picture + ", Role=" + Role + '}';
    }

    public String getFirstName(String name) {
        return name.split(" (?!.* )")[0];
    }

    public String getFirstName() {
        return this.FullName.split(" (?!.* )")[0];
    }

    public String getLastname(String name) {
        return name.split(" (?!.* )")[1];
    }

    public String getLastname() {
        return this.FullName.split(" (?!.* )")[1];
    }

    public static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
