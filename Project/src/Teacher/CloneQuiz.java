package Teacher;

import UI.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import DAO.*;
import java.util.*;
import Model.*;
import java.text.ParseException;

/**
 *
 * @author Salma
 */
public class CloneQuiz implements ActionListener {

    JFrame f;
    JPanel panelq;
    JTable tableq, table;
    JLabel Title;
    JButton create, addrow, deleterow, back;
    JScrollPane pane2, pane;
    int quizid, clonequizid;
    ArrayList<Question> arrayquestion;
    DefaultTableModel model1, model;
    Quiz quiz, clonequiz;
    QuestionStore qStore;
    boolean istrue;
    int teacherid;
    static final String[] columnsquiz = {"Title", "Description", "Date", "Duration", "Full Mark"};
    static final String[] columnsquestion = {"Question", "option 1", "option 2", "option 3", "Correct answer"};

    public CloneQuiz(Quiz q) {
        f = new JFrame("Clone Quiz");
        quiz = q;
        try {
            clonequiz = (Quiz) q.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        teacherid = quiz.getTeacherID();
        qStore = new QuestionStore();
        f.setSize(1280, 720);
        f.setLocation(10, 10);
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        panelq = new JPanel();
        panelq.setLayout(null);

        Title = new TitleLbl("Questions");
        f.add(Title, BorderLayout.NORTH);

        panelq = new JPanel();
        panelq.setLayout(null);

        back = new JButton();
        back.setBounds(900, 0, 100, 40);
        panelq.add(back);
        back.addMouseListener(new UI.BackListener(teacherid, back, "ViewMyQuizzes", f));

        create = new CircleBtn("create quiz");
        create.addActionListener(this);
        create.setBounds(100, 570, 100, 40);
        panelq.add(create);

        deleterow = new CircleBtn("delete row");
        deleterow.addActionListener(this);
        deleterow.setBounds(250, 570, 100, 40);
        panelq.add(deleterow);

        addrow = new CircleBtn("add row");
        addrow.addActionListener(this);
        addrow.setBounds(400, 570, 100, 40);
        panelq.add(addrow);

        f.add(panelq);
        getQuiz();
        getQuestions();
        f.setVisible(true);

    }

    public void getQuiz() {
        model = new DefaultTableModel(columnsquiz, 0);
        table = new JTable(model);
        pane = new JScrollPane(table);
        JTableHeader header1 = table.getTableHeader();
        header1.setBackground(Color.LIGHT_GRAY);
        table.setEnabled(true);
        table.getTableHeader().setFont(new Font("segoe ui", Font.BOLD, 15));
        table.getTableHeader().setOpaque(false);
        table.getTableHeader().setBackground(new Color(32, 136, 203));
        table.getTableHeader().setForeground(new Color(255, 255, 255));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFont(new Font("SansSerif", Font.PLAIN, 15));
        Dimension d1 = new Dimension(0, 0);
        table.setIntercellSpacing(d1);
        table.setSelectionBackground(new Color(230, 57, 95));
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setRowHeight(70);
        pane.setBounds(20, 40, 1200, 100);
        panelq.add(pane);
        loadQuiz();
    }

    public void getQuestions() {
        model1 = new DefaultTableModel(columnsquestion, 0);
        tableq = new JTable(model1);
        pane2 = new JScrollPane(tableq);
        JTableHeader header = tableq.getTableHeader();
        header.setBackground(Color.LIGHT_GRAY);
        tableq.setEnabled(true);
        tableq.getTableHeader().setFont(new Font("segoe ui", Font.BOLD, 15));
        tableq.getTableHeader().setOpaque(false);
        tableq.getTableHeader().setBackground(new Color(32, 136, 203));
        tableq.getTableHeader().setForeground(new Color(255, 255, 255));
        tableq.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableq.setFont(new Font("SansSerif", Font.PLAIN, 15));
        Dimension d = new Dimension(0, 0);
        tableq.setIntercellSpacing(d);
        tableq.setSelectionBackground(new Color(230, 57, 95));
        tableq.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableq.setPreferredScrollableViewportSize(tableq.getPreferredSize());
        tableq.setRowHeight(90);
        pane2.setBounds(20, 150, 1200, 400);
        panelq.add(pane2);
        loadQuestions();
    }

    public void InsertQuiz() throws ParseException {
        getDataQuiz();
        getDataQuestions();
        if (istrue == false) {
            try {
                int x = QuizDao.InsertIntoQuiz(clonequiz);
                if (x == 0) {
                    new Error("error in added quiz!");
                } else {
                    JOptionPane.showConfirmDialog(null, "Quiz added!", null, JOptionPane.DEFAULT_OPTION);
                    f.dispose();
                    new ViewMyQuizzes(teacherid);
                }
                clonequizid = QuizDao.getQuizIdByTitle(clonequiz.getQuizTitle());
                Question.insertQuestionsIntoDatabse(clonequiz, qStore);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
        }
    }

    public void loadQuestions() {
        quizid = quiz.getQuizID();
        arrayquestion = QuestionDao.getQuestionByQuizId(quizid);
        putdataInTable(arrayquestion);
    }

    public void loadQuiz() {
        model.setRowCount(0);
        Object[] row1 = new Object[7];
        row1[0] = quiz.getQuizTitle();
        row1[1] = quiz.getQuizDescription();
        row1[2] = quiz.getQuizDate();
        row1[3] = quiz.getQuizDuration();
        row1[4] = quiz.getFullMarks();
        model.addRow(row1);
    }

    public void getDataQuiz() throws ParseException {
        ArrayList aa = new ArrayList();
        istrue = false;
        int column = 0, row = 0;
        for (row = 0; row < table.getRowCount(); row++) {
            for (column = 0; column < table.getColumnCount(); column++) {
                if (column == 0) {
                    String title = table.getValueAt(row, column).toString();
                    istrue = Quiz.CheckIfTitleExist(title, istrue);
                    if (istrue == false) {
                        clonequiz.setQuizTitle(table.getValueAt(row, column).toString());
                    } else {
                        JOptionPane.showConfirmDialog(null, "Title already exist", null, JOptionPane.DEFAULT_OPTION);
                    }
                }
                if (column == 1) {
                    clonequiz.setQuizDescription(table.getValueAt(row, column).toString());
                }
                if (column == 2) {
                    Object o = table.getValueAt(row, column);
                    Quiz.addDate(o.toString(), clonequiz);
                }
                if (column == 3) {
                    Object D = table.getValueAt(row, column);
                    Quiz.addDuration(D.toString(), clonequiz);
                }
                if (column == 4) {
                    int p = Integer.parseInt(table.getValueAt(row, column).toString());
                    clonequiz.setFullMarks(p);
                }
            }
        }
    }

    public void getDataQuestions() {
        int rowcount = arrayquestion.size();
        int columnCount = tableq.getColumnCount();
        int row = 0, column = 0;
        for (row = 0; row < rowcount; row++) {
            Question qq = new Question();
            for (column = 0; column < columnCount; column++) {

                if (column == 0) {
                    qq.setQuestionTitle(tableq.getModel().getValueAt(row, column).toString());
                }
                if (column == 1) {
                    qq.setAnswer1(tableq.getModel().getValueAt(row, column).toString());
                }
                if (column == 2) {
                    qq.setAnswer2(tableq.getModel().getValueAt(row, column).toString());
                }
                if (column == 3) {
                    qq.setAnswer3(tableq.getModel().getValueAt(row, column).toString());
                }
                if (column == 4) {
                    qq.setCorrectAnswer(tableq.getModel().getValueAt(row, column).toString());
                }
            }
            qStore.addQuestion(qq);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == create) {
            try {
                InsertQuiz();
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        } else if (ae.getSource() == addrow) {
            Question q = new Question("", "", "", "", "");
            arrayquestion.add(q);
            putdataInTable(arrayquestion);
            JOptionPane.showMessageDialog(null, "New row is successfully added");
            table.validate();
        } else if (ae.getSource() == deleterow) {
            if (tableq.getSelectedRow() != -1) {
                int rowNum = tableq.getSelectedRow();
                arrayquestion.remove(rowNum);
                putdataInTable(arrayquestion);
                JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                tableq.validate();
            }
        }
    }

    public void putdataInTable(ArrayList<Question> a) {
        model1 = (DefaultTableModel) tableq.getModel();
        model1.setRowCount(0);
        Object[] row = new Object[7];
        for (Question q : a) {
            row[0] = q.getQuestionTitle();
            row[1] = q.getAnswer1();
            row[2] = q.getAnswer2();
            row[3] = q.getAnswer3();
            row[4] = q.getCorrectAnswer();
            model1.addRow(row);
        }
    }

}
