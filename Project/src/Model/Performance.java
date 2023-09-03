package Model;

public class Performance {

    private int ID;
    private int StudentId, QuizID, RightA, WrongA, Unattempted;
    private double Per;
    private String CourseName;

    public Performance(int ID, int StudentId, int QuizID, int RightA, int WrongA, int Unattempted, double Per, String CourseName) {
        this.ID = ID;
        this.StudentId = StudentId;
        this.QuizID = QuizID;
        this.RightA = RightA;
        this.WrongA = WrongA;
        this.Unattempted = Unattempted;
        this.Per = Per;
        this.CourseName = CourseName;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String CourseName) {
        this.CourseName = CourseName;
    }

    public Performance(int StudentId, int QuizID, int RightA, int WrongA, int Unattempted, double Per, String CourseName) {
        this.StudentId = StudentId;
        this.QuizID = QuizID;
        this.RightA = RightA;
        this.WrongA = WrongA;
        this.Unattempted = Unattempted;
        this.Per = Per;
        this.CourseName = CourseName;
    }
    
    public Performance(int StudentId, int QuizID, int RightA, int WrongA, int Unattempted, double Per) {
        this.StudentId = StudentId;
        this.QuizID = QuizID;
        this.RightA = RightA;
        this.WrongA = WrongA;
        this.Unattempted = Unattempted;
        this.Per = Per;
        
    }

    public Performance(int id, int StudentId, int QuizID, int RightA, int WrongA, int Unattempted, double Per) {
        this.StudentId = StudentId;
        this.QuizID = QuizID;
        this.RightA = RightA;
        this.WrongA = WrongA;
        this.Unattempted = Unattempted;
        this.Per = Per;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int StudentId) {
        this.StudentId = StudentId;
    }

    public int getQuizID() {
        return QuizID;
    }

    public void setQuizID(int QuizID) {
        this.QuizID = QuizID;
    }

    public int getRightA() {
        return RightA;
    }

    public void setRightA(int RightA) {
        this.RightA = RightA;
    }

    public int getWrongA() {
        return WrongA;
    }

    public void setWrongA(int WrongA) {
        this.WrongA = WrongA;
    }

    public int getUnattempted() {
        return Unattempted;
    }

    public void setUnattempted(int Unattempted) {
        this.Unattempted = Unattempted;
    }

    public double getPer() {
        return Per;
    }

    public void setPer(double Per) {
        this.Per = Per;
    }

    public Performance() {
    }

    @Override
    public String toString() {
        return "Performance{" + "StudentId=" + StudentId + ", QuizID=" + QuizID + ", RightA=" + RightA + ", WrongA=" + WrongA + ", Unattempted=" + Unattempted + ", Per=" + Per + '}';
    }

}
