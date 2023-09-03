package Model;

/**
 *
 * @author Salma
 */
public class Messages {

    private int MessageID;
    private String message;
    private String time;
    private int UserID;
    private int toUser_ID;

    public Messages() {
    }

    public Messages(int MessageID, String message, String time, int UserID, int toUser_ID) {
        this.MessageID = MessageID;
        this.message = message;
        this.time = time;
        this.UserID = UserID;
        this.toUser_ID = toUser_ID;
    }

    public Messages(String message, String time, int UserID, int toUser_ID) {
        this.message = message;
        this.time = time;
        this.UserID = UserID;
        this.toUser_ID = toUser_ID;
    }

    public int getMessageID() {
        return MessageID;
    }

    public void setMessageID(int MessageID) {
        this.MessageID = MessageID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getToUser_ID() {
        return toUser_ID;
    }

    public void setToUser_ID(int toUser_ID) {
        this.toUser_ID = toUser_ID;
    }

}
