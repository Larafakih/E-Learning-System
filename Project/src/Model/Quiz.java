package Model;

import DAO.*;
import UI.*;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Lara
 */
public class Quiz implements Cloneable {

    public int QuizID, TeacherID, CourseID, FullMarks;
    String QuizTitle, QuizDescription;
    Date QuizDate;
    Time QuizDuration;

    public Quiz() {

    }

    public Quiz(int QuizID, int CourseID, Date QuizDate) {
        this.QuizID = QuizID;
        this.CourseID = CourseID;
        this.QuizDate = QuizDate;
    }

    public Quiz(int QuizID, int TeacherID, int CourseID, int FullMarks, String QuizTitle, String QuizDescription, Date QuizDate, Time QuizDuration) {
        this.QuizID = QuizID;
        this.TeacherID = TeacherID;
        this.CourseID = CourseID;
        this.FullMarks = FullMarks;
        this.QuizTitle = QuizTitle;
        this.QuizDescription = QuizDescription;
        this.QuizDate = QuizDate;
        this.QuizDuration = QuizDuration;
    }

    public Quiz(int TeacherID, int CourseID, int FullMarks, String QuizTitle, String QuizDescription, Date QuizDate, Time QuizDuration) {
        this.TeacherID = TeacherID;
        this.CourseID = CourseID;
        this.FullMarks = FullMarks;
        this.QuizTitle = QuizTitle;
        this.QuizDescription = QuizDescription;
        this.QuizDate = QuizDate;
        this.QuizDuration = QuizDuration;
    }

    public Time getQuizDuration() {
        return (Time) this.QuizDuration.clone();
    }

    public void setQuizDuration(Time QuizDuration) {
        this.QuizDuration = QuizDuration;
    }

    public int getTotalQuestions() {
        return QuestionDao.CountQuestionByQuizId(QuizID);
    }

    public int getQuizID() {
        return QuizID;
    }

    public void setQuizID(int QuizID) {
        this.QuizID = QuizID;
    }

    public int getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(int TeacherID) {
        this.TeacherID = TeacherID;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public int getFullMarks() {
        return FullMarks;
    }

    public void setFullMarks(int FullMarks) {
        this.FullMarks = FullMarks;
    }

    public String getQuizTitle() {
        return QuizTitle;
    }

    public void setQuizTitle(String QuizTitle) {
        this.QuizTitle = QuizTitle;
    }

    public String getQuizDescription() {
        return QuizDescription;
    }

    public void setQuizDescription(String QuizDescription) {
        this.QuizDescription = QuizDescription;
    }

    public Date getQuizDate() {
        //return QuizDate; // This will make your class mutable.
        return (Date) this.QuizDate.clone(); // This will make sure your date field cannot be changed.
    }

    public void setQuizDate(Date QuizDate) {
        this.QuizDate = QuizDate;
    }

    @Override
    public String toString() {
        return "Quiz{" + "QuizID=" + QuizID + ", TeacherID=" + TeacherID + ", CourseID=" + CourseID + ", FullMarks=" + FullMarks + ", QuizTitle=" + QuizTitle + ", QuizDescription=" + QuizDescription + ", QuizDate=" + QuizDate + ", QuizDuration=" + QuizDuration + '}';
    }

    @Override
    public Quiz clone() throws CloneNotSupportedException {
        Quiz cloned = (Quiz) super.clone();
        cloned.QuizDate = (Date) QuizDate.clone();
        cloned.QuizDuration = (Time) QuizDuration.clone();
        return cloned;
    }

    public static void setQuizDateInSQLFormat(Quiz q) {
        Date quizDate = q.getQuizDate();
        System.out.println(q.getQuizDate());

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String datreStr = format1.format(quizDate);
        q.setQuizDate(java.sql.Date.valueOf(datreStr));
    }

    public static void addDate(Object o, Quiz q) {
        Date quizDate = (Date) o;
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String datreStr = format1.format(quizDate);
        q.setQuizDate(java.sql.Date.valueOf(datreStr));
    }

    public static void addDate(String datestr, Quiz q) throws ParseException {
        Date quizDate = new SimpleDateFormat("yyyy-MM-dd").parse(datestr);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String datreStr = format1.format(quizDate);
        q.setQuizDate(java.sql.Date.valueOf(datreStr));
    }

    public static void addDuration(Object D, Quiz q) {
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");
        String timeStr = format2.format(D);
        Time timeDuration = java.sql.Time.valueOf(timeStr);
        q.setQuizDuration(timeDuration);
    }

    public static void addDuration(String durationstr, Quiz q) throws ParseException {
        Time timeDuration = java.sql.Time.valueOf(durationstr);
        q.setQuizDuration(timeDuration);
    }

    public static boolean CheckIfTitleExist(String Titl, boolean isexist) {
        ArrayList<String> arrayq = QuizDao.getAllTitle();
        isexist = false;
        for (int i = 0; i < arrayq.size(); i++) {
            if (Titl.equals(arrayq.get(i))) {
                isexist = true;
            }
        }
        return isexist;
    }

    public static String convertDurationtoHMS(Time Du) {
        long timeh, timem, times;
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        Time sqlTime = Du;
        LocalTime localTime = sqlTime.toLocalTime();
        Duration duration1 = Duration.between(LocalTime.MIDNIGHT, localTime);
        MyTime t = formatDuration(duration1);
        timeh = t.getHour();
        timem = t.getMin();
        times = t.getSec();
        String ss = timeh + ":" + timem + ":" + times;
        return ss;
    }

    private static MyTime formatDuration(Duration dur) {
        long hr = dur.toHours();
        Duration remainder = dur.minusHours(hr);
        long min = remainder.toMinutes();
        long sec = remainder.minusMinutes(min).getSeconds();
        return new MyTime(hr, min, sec);
    }
}
