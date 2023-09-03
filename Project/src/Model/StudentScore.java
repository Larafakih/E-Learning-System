package Model;

public class StudentScore {

    String Coursename;
    double per;

    public StudentScore() {
    }

    public StudentScore(String Coursename, double per) {
        this.Coursename = Coursename;
        this.per = per;
    }

    public double getPer() {
        return per;
    }

    public void setPer(double per) {
        this.per = per;
    }

    public String getCoursename() {
        return Coursename;
    }

    public void setCoursename(String Coursename) {
        this.Coursename = Coursename;
    }

}
