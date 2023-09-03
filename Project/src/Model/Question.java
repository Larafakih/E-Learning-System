package Model;

import DAO.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author Salma
 */
public class Question {

    private int QuestionID;
    private int QuizID;
    private String QuestionTitle;
    private String Answer1;
    private String Answer2;
    private String Answer3;
    private String CorrectAnswer;
    private int Qnb;

    public Question() {
    }

    public Question(String QuestionTitle, String Answer1, String Answer2, String Answer3, String CorrectAnswer) {
        this.QuestionTitle = QuestionTitle;
        this.Answer1 = Answer1;
        this.Answer2 = Answer2;
        this.Answer3 = Answer3;
        this.CorrectAnswer = CorrectAnswer;
    }

    public Question(int QuestionID, int QuizID, String QuestionTitle, String Answer1, String Answer2, String Answer3, String CorrectAnswer, int Qnb) {
        this.QuestionID = QuestionID;
        this.QuizID = QuizID;
        this.QuestionTitle = QuestionTitle;
        this.Answer1 = Answer1;
        this.Answer2 = Answer2;
        this.Answer3 = Answer3;
        this.CorrectAnswer = CorrectAnswer;
        this.Qnb = Qnb;
    }

    public Question(int QuestionID, int QuizID, String QuestionTitle, String Answer1, String Answer2, String Answer3, String CorrectAnswer) {
        this.QuestionID = QuestionID;
        this.QuizID = QuizID;
        this.QuestionTitle = QuestionTitle;
        this.Answer1 = Answer1;
        this.Answer2 = Answer2;
        this.Answer3 = Answer3;
        this.CorrectAnswer = CorrectAnswer;
    }

    public Question(int QuizID, String QuestionTitle, String Answer1, String Answer2, String Answer3, String CorrectAnswer, int Qnb) {
        this.QuizID = QuizID;
        this.QuestionTitle = QuestionTitle;
        this.Answer1 = Answer1;
        this.Answer2 = Answer2;
        this.Answer3 = Answer3;
        this.CorrectAnswer = CorrectAnswer;
        this.Qnb = Qnb;
    }

    public Question(int QuizID, String QuestionTitle, String Answer1, String Answer2, String Answer3, String CorrectAnswer) {
        this.QuizID = QuizID;
        this.QuestionTitle = QuestionTitle;
        this.Answer1 = Answer1;
        this.Answer2 = Answer2;
        this.Answer3 = Answer3;
        this.CorrectAnswer = CorrectAnswer;
    }

    public Question(String QuestionTitle, int QuestionNb, String Answer1, String Answer2, String Answer3, String CorrectAnswer) {

        this.QuestionTitle = QuestionTitle;
        this.Qnb = QuestionNb;
        this.Answer1 = Answer1;
        this.Answer2 = Answer2;
        this.Answer3 = Answer3;
        this.CorrectAnswer = CorrectAnswer;
    }

    public String getAnswer1() {
        return Answer1;
    }

    public int getQnb() {
        return Qnb;
    }

    public void setQnb(int Qnb) {
        this.Qnb = Qnb;
    }

    public void setAnswer1(String Answer1) {
        this.Answer1 = Answer1;
    }

    public String getAnswer2() {
        return Answer2;
    }

    public void setAnswer2(String Answer2) {
        this.Answer2 = Answer2;
    }

    public String getAnswer3() {
        return Answer3;
    }

    public void setAnswer3(String Answer3) {
        this.Answer3 = Answer3;
    }

    public String getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setCorrectAnswer(String CorrectAnswer) {
        this.CorrectAnswer = CorrectAnswer;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int QuestionID) {
        this.QuestionID = QuestionID;
    }

    public int getQuizID() {
        return QuizID;
    }

    public void setQuizID(int QuizID) {
        this.QuizID = QuizID;
    }

    public String getQuestionTitle() {
        return QuestionTitle;
    }

    public void setQuestionTitle(String QuestionTitle) {
        this.QuestionTitle = QuestionTitle;
    }

    @Override
    public String toString() {
        return "Question{" + "QuestionID=" + QuestionID + ", QuizID=" + QuizID + ", QuestionTitle=" + QuestionTitle + ", Answer1=" + Answer1 + ", Answer2=" + Answer2 + ", Answer3=" + Answer3 + ", CorrectAnswer=" + CorrectAnswer + ", Qnb=" + Qnb + '}';
    }

    public static void setQuizIDForQuestions(Quiz q, QuestionStore qS) throws SQLException {
        q.setQuizID(QuizDao.getQuizIdByTitle(q.getQuizTitle()));
        ArrayList<Question> questionList = qS.getAllQuestions();
        for (Question qu : questionList) {
            qu.setQuizID(q.getQuizID());
        }
    }

    public static void insertQuestionsIntoDatabse(Quiz q, QuestionStore qS) throws SQLException {
        setQuizIDForQuestions(q, qS);//to be able to insert its quizID
        System.out.println(qS.getCount() + " questions were added");
        int y = QuestionDao.insertQuestion(qS);
        if (y == 0) {
            System.out.println("Some Error Occured");
        } else {
            JOptionPane.showMessageDialog(null, "Question Added!");
        }
    }

}
