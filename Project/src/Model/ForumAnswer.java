package Model;

import java.util.Date;

/**
 *
 * @author Lara
 */
public class ForumAnswer implements Cloneable {

    private int ForumAnswerID;
    private int ForumMessageID;
    private int StudentID;
    private Date AnswerDate;
    private String Answer;

    public ForumAnswer(int ForumMessageID, int StudentID, Date AnswerDate, String Answer) {
        this.ForumAnswerID = ForumAnswerID;
        this.ForumMessageID = ForumMessageID;
        this.StudentID = StudentID;
        this.AnswerDate = AnswerDate;
        this.Answer = Answer;
    }

    public ForumAnswer() {
    }

    public ForumAnswer(int ForumAnswerID, int ForumMessageID, int StudentID, Date AnswerDate, String Answer) {
        this.ForumAnswerID = ForumAnswerID;
        this.ForumMessageID = ForumMessageID;
        this.StudentID = StudentID;
        this.AnswerDate = AnswerDate;
        this.Answer = Answer;
    }

    public Date getAnswerDate() {
        return AnswerDate;
    }

    public void setAnswerDate(Date AnswerDate) {
        this.AnswerDate = AnswerDate;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int StudentID) {
        this.StudentID = StudentID;
    }

    public int getForumAnswerID() {
        return ForumAnswerID;
    }

    public void setForumAnswerID(int ForumAnswerID) {
        this.ForumAnswerID = ForumAnswerID;
    }

    public int getForumMessageID() {
        return ForumMessageID;
    }

    public void setForumMessageID(int ForumMessageID) {
        this.ForumMessageID = ForumMessageID;
    }

    @Override
    public String toString() {
        return "ForumAnswer{" + "ForumAnswerID=" + ForumAnswerID + ", ForumMessageID=" + ForumMessageID + ", StudentID=" + StudentID + ", AnswerDate=" + AnswerDate + ", Answer=" + Answer + '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
     //   ForumAnswer a = (ForumAnswer) this.clone();
        return super.clone();
    }
}
