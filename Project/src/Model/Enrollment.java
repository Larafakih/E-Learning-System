package Model;

import java.sql.*;

/**
 *
 * @author Salma
 */
public class Enrollment {

    private int EnrollmentID;
    private Date Enrollment_Date;
    private int CourseID;
    private int StudentId;

    public Enrollment() {
    }

    public Enrollment(Date Enrollment_Date, int CourseID, int StudentId) {
        this.Enrollment_Date = Enrollment_Date;
        this.CourseID = CourseID;
        this.StudentId = StudentId;
    }

    public Enrollment(int EnrollmentID, Date Enrollment_Date, int CourseID, int StudentId) {
        this.EnrollmentID = EnrollmentID;
        this.Enrollment_Date = Enrollment_Date;
        this.CourseID = CourseID;
        this.StudentId = StudentId;
    }

    public int getEnrollmentID() {
        return EnrollmentID;
    }

    public void setEnrollmentID(int EnrollmentID) {
        this.EnrollmentID = EnrollmentID;
    }

    public Date getEnrollment_Date() {
        return Enrollment_Date;
    }

    public void setEnrollment_Date(Date Enrollment_Date) {
        this.Enrollment_Date = Enrollment_Date;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int StudentId) {
        this.StudentId = StudentId;
    }

}
