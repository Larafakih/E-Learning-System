package Teacher;

import UI.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import DAO.*;
import java.util.*;
import Model.*;
import javax.swing.event.*;

/**
 *
 * @author Salma
 */
public class ViewMyQuizzes {

    JFrame f, fquestion;
    JPanel p;//, panelq;
    ArrayList<Quiz> arrayquiz;
    int NbofQuiz, quizid, currentTeacherid;
    JTable table, tableq;
    JLabel Title, cbLbl;
    ArrayList<Question> arrayquestion;
    String selectedData;
    JScrollPane pane, pane2;
    DefaultTableModel model, model1;
    HashMap<Integer, Quiz> h;
    ListSelectionModel cellSelectionModel;
    int count = 0;
    String[] columnName;
    JButton back;
    private String RoleT = "Teacher";

    public ViewMyQuizzes(int c) {
        f = new JFrame("View Quizzes");
        f.setSize(1280, 720);
        f.setLocation(10, 10);
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        h = new HashMap<>();
        currentTeacherid = c;
        p = new JPanel();
        p.setLayout(null);

        Title = new TitleLbl("View Quizzes");
        f.add(Title, BorderLayout.NORTH);

        back = new JButton();
        back.setBounds(900, 0, 100, 40);
        p.add(back);
        back.addMouseListener(new UI.BackListener(currentTeacherid, back, RoleT, f));

        columnName = new String[]{"Title", "Description", "Date", "Duration", "Full Mark", "Course Name"};
        table = new Table(90, columnName);
        pane = new JScrollPane(table);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        pane.setBounds(20, 50, 1200, 600);
        p.add(pane);

        load();
        count = 0;
        cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                count++;
                int[] selectedRow = table.getSelectedRows();
                int[] selectedColumns = table.getSelectedColumns();
                for (int i = 0; i < selectedRow.length; i++) {
                    for (int j = 0; j < selectedColumns.length; j++) {
                        selectedData = (String) table.getValueAt(selectedRow[i], 0);
                        break;
                    }
                }
                if (count == 1) {
                    displayData(selectedData);
                }

            }

        });

        f.add(p, BorderLayout.CENTER);
        f.setVisible(true);
    }

    public void displayData(String selectedData) {
        int id = QuizDao.getQuizIdByTitle(selectedData);
        Quiz q = QuizDao.SelectQuizByquizId(id);
        while (h.containsKey(id)) {
            CloneQuiz a = new CloneQuiz(q);
            f.dispose();
            break;
        }
    }

    public void load() {
        arrayquiz = QuizDao.getQuizzesForTeacher(currentTeacherid);
        NbofQuiz = arrayquiz.size();
        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        Object[] row = new Object[7];
        for (Quiz q : arrayquiz) {
            h.put(q.getQuizID(), q);
            row[0] = q.getQuizTitle();
            row[1] = q.getQuizDescription();
            row[2] = q.getQuizDate();
            row[3] = q.getQuizDuration();
            row[4] = q.getFullMarks();
            Courses co = CoursesDao.getCourseName(q.getCourseID());
            row[5] = co.getName();
            model.addRow(row);
        }
    }
}
