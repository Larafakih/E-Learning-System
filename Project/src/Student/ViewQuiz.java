package Student;

import UI.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Model.*;
import DAO.*;
import java.util.*;
import javax.swing.event.*;
import javax.swing.table.*;

/**
 *
 * @author Salma
 */
public class ViewQuiz extends JFrame implements ActionListener {

    JPanel middlePanel;
    JLabel title, QuizTitle;
    int currentStudentID, CourseID, NbofQuiz;
    String courseName, selectedData = null;
    String[][] data = null;
    String[] columnNames = {"Quiz Title"};
    JTable table;
    JButton back;
    DefaultTableModel model;
    private final String Role = "SpecificCourse";
    //////
    int courseID, studentId, QuizId, NbofQuestions;
    JLabel title2, Qtitle, desc, Date, duration, FullMarks, image, nbQ;
    JPanel middlePanel2;
    Question question;
    Quiz quiz;
    JTextArea QuizTitle2, QuizDesc;
    JButton start, back2;
    Font font;
    String Name;
    JScrollPane scroll, scroll2;
    ArrayList<Integer> st;
    String coursenam;
    JFrame fr;
    private final static String ViewQuiz = "ViewQuizStudent";

    public ViewQuiz(int courseId, int c, String s) {
        super("View Quiz");
        setIconImage(img.SystemIcon());
        CourseID = courseId;
        currentStudentID = c;
        courseName = s;
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setSize(1000, 720);
        setLocation(10, 10);

        courseName = s;

        title = new TitleLbl("Quiz Title for Course: " + courseName);
        add(title, BorderLayout.NORTH);

        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        add(middlePanel, BorderLayout.CENTER);

        NbofQuiz = QuizDao.CountQuizByCourseId(CourseID);

        back = new JButton();
        back.setBounds(690, 0, 100, 40);
        middlePanel.add(back);
        back.addMouseListener(new UI.BackListener(currentStudentID, back, Role, this));

        data = QuizDao.getQuizCourses(CourseID);
        model = new DefaultTableModel(data, columnNames);
        table = new Table(model, 90);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(30, 70, 800, 500);
        middlePanel.add(pane, BorderLayout.CENTER);

        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                cellSelectionAction();
            }

        });

        setVisible(true);
    }

    public void cellSelectionAction() {
        int[] selectedRow = table.getSelectedRows();
        int[] selectedColumns = table.getSelectedColumns();
        for (int i = 0; i < selectedRow.length; i++) {
            for (int j = 0; j < selectedColumns.length; j++) {
                selectedData = (String) table.getValueAt(selectedRow[i], selectedColumns[j]);
            }
        }
        SpecificQuiz(currentStudentID, selectedData, courseName);
        this.dispose();
    }

    public void SpecificQuiz(int studentid, String name, String coursename) {
        fr = new JFrame("View Specific Quiz");
        Name = name;
        studentId = studentid;
        coursenam = coursename;
        fr.setIconImage(img.SystemIcon());
        fr.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        fr.setResizable(false);
        fr.setLayout(new BorderLayout());
        fr.setSize(1000, 720);
        fr.setLocation(10, 10);
        font = new Font("Tahoma", 1, 15);

        QuizId = QuizDao.getQuizIdByTitle(Name);
        System.out.println("" + QuizId);
        quiz = QuizDao.SelectQuizByquizId(QuizId);

        title2 = new TitleLbl("Take this Quiz");
        fr.add(title2, BorderLayout.NORTH);

        middlePanel2 = new JPanel();
        middlePanel2.setLayout(null);
        fr.add(middlePanel2, BorderLayout.CENTER);

        back2 = new JButton();
        back2.setBounds(690, 0, 100, 40);
        middlePanel2.add(back2);
        back2.addMouseListener(new UI.BackListener(currentStudentID, back2, ViewQuiz, fr));

        Qtitle = new TxtLabel("Quiz Title: ");
        Qtitle.setBounds(10, 30, 250, 50);
        middlePanel2.add(Qtitle);

        QuizTitle2 = new JTextArea();
        QuizTitle2.setText("" + quiz.getQuizTitle());
        QuizTitle2.setLineWrap(true);
        QuizTitle2.setWrapStyleWord(true);
        scroll2 = new JScrollPane(QuizTitle2);
        scroll2.setBounds(200, 40, 500, 90);
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        QuizTitle2.setEditable(false);
        middlePanel2.add(scroll2);

        desc = new TxtLabel("Quiz Description: ");
        desc.setBounds(10, 100, 250, 150);
        middlePanel2.add(desc);
        QuizDesc = new JTextArea();
        QuizDesc.setText("" + quiz.getQuizDescription());
        QuizDesc.setLineWrap(true);
        QuizDesc.setWrapStyleWord(true);
        scroll = new JScrollPane(QuizDesc);
        scroll.setBounds(200, 150, 500, 90);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        QuizDesc.setEditable(false);
        middlePanel2.add(scroll);

        Date = new TxtLabel("Quiz Date:  " + quiz.getQuizDate());
        Date.setFont(font);
        Date.setBounds(10, 200, 800, 150);
        middlePanel2.add(Date);

        NbofQuestions = QuestionDao.CountQuestionByQuizId(QuizId);
        nbQ = new TxtLabel("Nb of Questions:  " + NbofQuestions);
        nbQ.setBounds(10, 280, 800, 90);
        nbQ.setFont(font);
        middlePanel2.add(nbQ);

        duration = new TxtLabel("Duration:  " + quiz.getQuizDuration());
        duration.setFont(font);
        duration.setBounds(10, 300, 800, 150);
        middlePanel2.add(duration);

        FullMarks = new TxtLabel("Fullmarks: " + quiz.getFullMarks());
        FullMarks.setFont(font);
        FullMarks.setBounds(10, 350, 150, 150);
        middlePanel2.add(FullMarks);

        image = new JLabel();
        image.setBounds(600, 200, 500, 500);
        image.setIcon(img.CreateImageIcon("icons/quiz-online.png", 300, 300));
        middlePanel2.add(image);

        start = new CircleBtn("start");
        start.setBounds(214, 500, 228, 60);
        start.addActionListener(this);
        middlePanel2.add(start);

        st = PerformanceDAO.get(studentId, QuizId);
        if (!st.isEmpty()) {
            JOptionPane.showConfirmDialog(null, "already do this quiz", "already do this quiz", JOptionPane.DEFAULT_OPTION);
            start.setEnabled(false);
        }
        fr.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == start) {
            new TakeQuiz(studentId, QuizId, coursenam);
            fr.setVisible(false);
        }
    }
}
