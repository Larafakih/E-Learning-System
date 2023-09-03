package Model;

import java.util.Date;

/**
 *
 * @author Lara
 */
public class ForumMessage implements Cloneable {

    private int ForumMessageID;
    private int StudentID;
    private Date MessageDate;
    private String Title;
    private String Message;
    private int nbofanswers;//not in database

    public ForumMessage(int ForumMessageID, int StudentID, Date MessageDate, String Title, String Message) {
        this.ForumMessageID = ForumMessageID;
        this.StudentID = StudentID;
        this.MessageDate = MessageDate;
        this.Title = Title;
        this.Message = Message;
    }

    public ForumMessage(int StudentID, Date MessageDate, String Title, String Message) {
        this.StudentID = StudentID;
        this.MessageDate = MessageDate;
        this.Title = Title;
        this.Message = Message;
    }

    public ForumMessage(int StudentID, Date MessageDate, String Title, String Message, int nbofanswers) {
        this.StudentID = StudentID;
        this.MessageDate = MessageDate;
        this.Title = Title;
        this.Message = Message;
        this.nbofanswers = nbofanswers;
    }

    public int getNbofanswers() {
        return nbofanswers;
    }

    public void setNbofanswers(int nbofanswers) {
        this.nbofanswers = nbofanswers;
    }

    public int getForumMessageID() {
        return ForumMessageID;
    }

    public void setForumMessageID(int ForumMessageID) {
        this.ForumMessageID = ForumMessageID;
    }

    public int getStudentID() {
        return StudentID;
    }

    public void setStudentID(int StudentID) {
        this.StudentID = StudentID;
    }

    public Date getMessageDate() {
        return MessageDate;
    }

    public void setMessageDate(Date MessageDate) {
        this.MessageDate = MessageDate;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    @Override
    public String toString() {
        return "ForumMessage{" + "ForumMessageID=" + ForumMessageID + ", StudentID=" + StudentID + ", MessageDate=" + MessageDate + ", Title=" + Title + ", Message=" + Message + '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
