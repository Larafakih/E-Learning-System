package Student;

import DAO.*;
import Model.*;
import java.awt.event.*;
import javax.swing.*;
import UI.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Salma
 */
public class TakeQuiz extends JFrame implements ActionListener {

    JButton btnCancel, btnSubmit, btnNext, btnPrevious;
    ButtonGroup buttonGroup1;
    Quiz quiz;
    Color oldColor;
    QuestionStore qstore;
    int pos = 0, Qnb, StudentId, CourseID, QuizId, time, nbOfQuestions, fullmark;

    Time QuizDuration;
    AnswerStore astore;
    JLabel Timeleft, goodluck, lblLogout, lblQnb, lbltitle, lblTimer, lblUserName, Titlelb;
    JPanel jPanel1;
    JScrollPane jScrollPane1;
    JRadioButton jrbOption1, jrbOption2, jrbOption3;
    JTextArea txtQuestion;
    String coursename, username;
    Font font;
    String ss;
    Time duration;
    static final String RoleS = "Student";
    TimerThread tr;

    public TakeQuiz(int studentId, int Quizid, String course) {
        super("Quiz");
        StudentId = studentId;
        QuizId = Quizid;
        coursename = course;
        setIconImage(img.SystemIcon());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(1050, 720);
        setLocation(10, 10);
        setLayout(new BorderLayout());
        font = new Font("Tahoma", 1, 15);

        jPanel1 = new JPanel(null);
        add(jPanel1, BorderLayout.CENTER);

        Titlelb = new TitleLbl(" Take test of " + coursename.toUpperCase());
        add(Titlelb, BorderLayout.NORTH);

        quiz = QuizDao.SelectQuizByquizId(QuizId);
        nbOfQuestions = quiz.getTotalQuestions();
        lbltitle = new TxtLabel("" + quiz.getQuizTitle());
        lbltitle.setBounds(450, 10, 700, 60);
        jPanel1.add(lbltitle);

        fullmark = quiz.getFullMarks();

        lblLogout = new JLabel();
        lblLogout.setForeground(new Color(0, 142, 204));
        lblLogout.setFont(font);
        lblLogout.setText("Logout");
        lblLogout.setBounds(940, 5, 100, 50);
        lblLogout.addMouseListener(new LogoutListener(StudentId, lblLogout, oldColor, RoleS, this));
        jPanel1.add(lblLogout);

        Student st = StudentDao.SelectStudentById(StudentId);
        username = st.getFullName();
        lblUserName = new TxtLabel("Hello " + username);
        lblUserName.setBounds(10, 5, 700, 50);
        jPanel1.add(lblUserName);

        goodluck = new JLabel(img.CreateImageIcon("icons/good-luck.png", 120, 120));
        goodluck.setBounds(400, 30, 200, 200);
        jPanel1.add(goodluck);

        Timeleft = new JLabel();
        Timeleft.setFont(font);
        Timeleft.setText("Time Left: ");
        Timeleft.setBounds(710, 50, 100, 50);
        jPanel1.add(Timeleft);

        lblTimer = new JLabel();
        lblTimer.setFont(font);
        lblTimer.setText("timer");
        lblTimer.setBounds(810, 50, 100, 50);
        jPanel1.add(lblTimer);

        Qnb = 1;
        lblQnb = new JLabel();
        lblQnb.setText("Question No: " + lblQnb.getText() + Qnb);
        lblQnb.setFont(font);
        lblQnb.setBounds(100, 190, 200, 50);
        jPanel1.add(lblQnb);

        txtQuestion = new JTextArea();
        txtQuestion.setLineWrap(true);
        txtQuestion.setWrapStyleWord(true);
        jScrollPane1 = new JScrollPane(txtQuestion);
        jScrollPane1.setBounds(250, 200, 500, 100);
        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setViewportView(txtQuestion);
        txtQuestion.setEditable(false);
        jPanel1.add(jScrollPane1);

        buttonGroup1 = new ButtonGroup();

        jrbOption1 = new JRadioButton();
        jrbOption1.setBounds(310, 290, 900, 50);
        jrbOption1.setFont(font);
        jrbOption1.setText("jRadioButton1");
        jrbOption1.setOpaque(false);
        buttonGroup1.add(jrbOption1);

        jrbOption2 = new JRadioButton();
        jrbOption2.setBounds(310, 370, 900, 50);
        jrbOption2.setFont(font);
        jrbOption2.setText("jRadioButton2");
        jrbOption2.setOpaque(false);
        buttonGroup1.add(jrbOption2);

        jrbOption3 = new JRadioButton();
        jrbOption3.setBounds(310, 450, 900, 50);
        jrbOption3.setFont(font);
        jrbOption3.setText("jRadioButton3");
        jrbOption3.setOpaque(false);
        buttonGroup1.add(jrbOption3);

        jPanel1.add(jrbOption1);
        jPanel1.add(jrbOption2);
        jPanel1.add(jrbOption3);

        btnPrevious = new CircleBtn("Previous");
        btnPrevious.addActionListener(this);
        btnPrevious.setBounds(200, 550, 100, 50);
        jPanel1.add(btnPrevious);

        btnNext = new CircleBtn("Next");
        btnNext.addActionListener(this);
        btnNext.setBounds(350, 550, 100, 50);
        jPanel1.add(btnNext);

        btnCancel = new CircleBtn("Cancel");
        btnCancel.addActionListener(this);
        btnCancel.setBounds(500, 550, 100, 50);
        jPanel1.add(btnCancel);

        btnSubmit = new CircleBtn("Submit");
        btnSubmit.addActionListener(this);
        btnSubmit.setBounds(650, 550, 100, 50);
        jPanel1.add(btnSubmit);

        oldColor = lblLogout.getForeground();
        qstore = new QuestionStore();
        astore = new AnswerStore();

        duration = quiz.getQuizDuration();
        ss = Quiz.convertDurationtoHMS(duration);
        time = toSec(ss);
        tr = new TimerThread();
        tr.start();
        loadQuestions();
        showQuestion();
        setVisible(true);
    }

    private int toSec(String h) {
        String[] h1 = h.split(":");
        int hour = Integer.parseInt(h1[0]);
        int minute = Integer.parseInt(h1[1]);
        int second = Integer.parseInt(h1[2]);
        int temp;
        temp = second + (60 * minute) + (3600 * hour);
        return temp;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnNext) {
            Next();
        } else if (ae.getSource() == btnPrevious) {
            Previous();
        } else if (ae.getSource() == btnSubmit) {
            Submit();
        } else if (ae.getSource() == btnCancel) {
            cancel();;
        }
    }

    class TimerThread extends Thread {

        public void run() {
            while (time >= 0) {
                try {
                    int hours = (int) time / 3600;
                    int remainder = (int) time - hours * 3600;
                    int mins = remainder / 60;
                    remainder = remainder - mins * 60;
                    int secs = remainder;

                    lblTimer.setText(hours + ": " + mins + ":" + secs + " sec");
                    Thread.sleep(1000);
                    time--;
                } catch (InterruptedException ex) {
                    new project.Error("Error occured..Thread interrupted", StudentId, RoleS);
                }
            }
            JOptionPane.showMessageDialog(null, "Time given for Quiz is completed\nPress submit", "No Time Left!", JOptionPane.INFORMATION_MESSAGE);
            btnNext.setEnabled(false);
            btnPrevious.setEnabled(false);
            btnCancel.setEnabled(false);
            jrbOption1.setEnabled(false);
            jrbOption2.setEnabled(false);
            jrbOption3.setEnabled(false);
        }

    }

    public void cancel() {
        JOptionPane.showConfirmDialog(null, "", "Cancel quiz, return to your student page", JOptionPane.OK_OPTION);
        StudentPage st = new StudentPage(StudentId);
        this.dispose();
    }

    public void Next() {
        btnNext.setEnabled(true);
        String chosenAnswer = getUserAnswer();
        if (chosenAnswer != null) {
            Question question = qstore.getQuestionByQno(Qnb);
            String correctAnswer = question.getCorrectAnswer();
            Answer newAnswer = new Answer(quiz.getQuizID(), coursename, Qnb, chosenAnswer, correctAnswer);
            Answer answer = astore.getAnswerByQno(Qnb);
            if (answer == null) {
                astore.addAnswer(newAnswer);
            } else {
                if (newAnswer.getChosenAnswer().equals(answer.getChosenAnswer()) == false) {
                    int apos = astore.removeAnswer(answer);
                    astore.setAnswerAt(pos, newAnswer);
                }
            }
        }
        pos++;
        if (pos >= qstore.getCount()) {
            pos = 0;
        }
        Qnb++;
        if (Qnb >= qstore.getCount()) {
            btnNext.setEnabled(false);
            btnPrevious.setEnabled(true);
        }
        lblQnb.setText("Question No: " + Qnb);
        showQuestion();
    }

    public void Previous() {
        btnPrevious.setEnabled(true);
        String chosenAnswer = getUserAnswer();
        if (chosenAnswer != null) {
            Question question = qstore.getQuestionByQno(Qnb);
            String correctAnswer = question.getCorrectAnswer();
            Answer newAnswer = new Answer(quiz.getQuizID(), coursename, Qnb, chosenAnswer, correctAnswer);
            Answer answer = astore.getAnswerByQno(Qnb);
            if (answer == null) {
                astore.addAnswer(newAnswer);
            } else {
                if (newAnswer.getChosenAnswer().equals(answer.getChosenAnswer()) == false) {
                    int apos = astore.removeAnswer(answer);
                    astore.setAnswerAt(pos, newAnswer);
                }
            }
        }
        pos--;
        Qnb--;
        if (Qnb == 1) {
            btnPrevious.setEnabled(false);
        } else {
            btnPrevious.setEnabled(true);
        }
        if (Qnb <= qstore.getCount()) {
            btnNext.setEnabled(true);
        }

        lblQnb.setText("Question No: " + Qnb);
        showQuestion();
    }

    public void Submit() {
        String chosenAnswer = getUserAnswer();
        if (chosenAnswer != null) {
            Question question = qstore.getQuestionByQno(Qnb);
            String correctAnswer = question.getCorrectAnswer();
            Answer newAnswer = new Answer(quiz.getQuizID(), coursename, Qnb, chosenAnswer, correctAnswer);
            Answer answer = astore.getAnswerByQno(Qnb);
            if (answer == null) {
                astore.addAnswer(newAnswer);
            } else {
                if (newAnswer.getChosenAnswer().equals(answer.getChosenAnswer()) == false) {
                    int apos = astore.removeAnswer(answer);
                    astore.setAnswerAt(pos, newAnswer);
                }
            }
        }
        int right = 0, wrong = 0;
        for (Question question : qstore.getAllQuestions()) {
            int qno = question.getQnb();
            Answer answer = astore.getAnswerByQno(qno);
            if (answer == null) {
                continue;
            } else {
                String chosenAnswer1 = answer.getChosenAnswer();
                String correctAnswer = answer.getCorrectAnswer();
                if (chosenAnswer1.equals(correctAnswer)) {
                    right++;
                } else {
                    wrong++;
                }
            }
        }
        String reportCard = "Total Questions: " + qstore.getCount() + "\nRight Answers: " + right + "\nWrong Answer: " + wrong + "\nUnattempted: " + (qstore.getCount() - right - wrong);
        JOptionPane.showMessageDialog(null, reportCard, "Your Result!", JOptionPane.INFORMATION_MESSAGE);

        Performance performance = new Performance(StudentId, QuizId, right, wrong, (qstore.getCount() - right - wrong), (double) ((fullmark*right / qstore.getCount())*100)/fullmark , coursename);
        PerformanceDAO.addPerformance(performance);
        JOptionPane.showMessageDialog(null, "So you can view your score later!!", "Performance Added!", JOptionPane.INFORMATION_MESSAGE);
        StudentPage st = new StudentPage(StudentId);
        this.dispose();
    }

    private void loadQuestions() {
        ArrayList<Question> questionList = QuestionDao.getQuestionByQuizId(QuizId);
        for (Question q : questionList) {
            qstore.addQuestion(q);
        }
    }

    private void showQuestion() {
        Question question = qstore.getQuestion(pos);
        buttonGroup1.clearSelection();
        txtQuestion.setText(question.getQuestionTitle());
        jrbOption1.setText(question.getAnswer1());
        jrbOption2.setText(question.getAnswer2());
        jrbOption3.setText(question.getAnswer3());
        Answer answer = astore.getAnswerByQno(Qnb);
        if (answer == null) {
            return;
        }
        String chosenAnswer = answer.getChosenAnswer();
        switch (chosenAnswer) {
            case "Option 1":
                jrbOption1.setSelected(true);
                break;
            case "Option 2":
                jrbOption2.setSelected(true);
                break;
            case "Option 3":
                jrbOption3.setSelected(true);
                break;

        }
    }

    public String getUserAnswer() {
        if (jrbOption1.isSelected()) {
            return "Option 1";
        } else if (jrbOption2.isSelected()) {
            return "Option 2";
        } else if (jrbOption3.isSelected()) {
            return "Option 3";
        } else {
            return null;
        }
    }
}
