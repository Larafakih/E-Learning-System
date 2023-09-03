package Student;

import DAO.*;
import Model.*;
import java.awt.event.*;
import javax.swing.*;
import UI.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 *
 * @author Salma
 */
public class ViewScores implements ActionListener {

    private JButton back;
    private JLabel Title, selectquiz, coursename, per, lblUserName,image;
    private JPanel jPanel1;
    private JComboBox<String> jcSelectquizId;
    private JTextField txtLanguage, txtPercentage;
    private int StudentId;
    private String username;
    private Font font;
    private JFrame f;
    private static final String RoleS = "Student";

    public ViewScores(int studentId) {
        f = new JFrame("view Scores");
        StudentId = studentId;
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(false);
        f.setSize(700, 700);
        f.setLocation(10, 10);
        f.setLayout(new BorderLayout());

        jPanel1 = new JPanel(null);
        f.add(jPanel1, BorderLayout.CENTER);

        Student st = StudentDao.SelectStudentById(StudentId);
        username = st.getFullName();
        lblUserName = new TxtLabel(username.toUpperCase());
        lblUserName.setBounds(10, 10, 700, 50);
        jPanel1.add(lblUserName);

        Title = new TitleLbl("STUDENT SCORE DETAILS");
        f.add(Title, BorderLayout.NORTH);

        selectquiz = new TxtLabel("Select Quiz: ");
        selectquiz.setBounds(10, 200, 200, 50);
        jPanel1.add(selectquiz);

        jcSelectquizId = new JComboBox<>();
        jcSelectquizId.addActionListener(this);
        jcSelectquizId.setBounds(200, 200, 350, 40);
        jPanel1.add(jcSelectquizId);

        coursename = new TxtLabel("Course Name: ");
        coursename.setBounds(10, 290, 300, 50);
        jPanel1.add(coursename);

        txtLanguage = new JTextField();
        txtLanguage.setBounds(200, 290, 350, 50);
        txtLanguage.setEditable(false);
        jPanel1.add(txtLanguage);

        per = new TxtLabel("Percentage: ");
        per.setBounds(10, 370, 300, 50);
        jPanel1.add(per);

        txtPercentage = new JTextField();
        txtPercentage.setEditable(false);
        txtPercentage.setBounds(200, 370, 350, 50);
        jPanel1.add(txtPercentage);
        
        image = new JLabel(img.CreateImageIcon("icons/score-1.png", 120, 120));
        image.setBounds(350, 10, 120, 120);
        jPanel1.add(image);

        back = new JButton();
        back.setBounds(590, 0, 100, 40);
        back.addMouseListener(new UI.BackListener(StudentId, back, RoleS, f));
        jPanel1.add(back);

        loadquiz();
        f.setVisible(true);
    }

    private void loadquiz() {
        ArrayList<Integer> examIdList = PerformanceDAO.getAllQuizId(StudentId);
        if (examIdList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Scores", "No Quiz Scores For You!! Go Do Quiz", JOptionPane.DEFAULT_OPTION);
            return;
        }
        jcSelectquizId.removeAllItems();
        for (int examId : examIdList) {
            Quiz d = QuizDao.SelectQuizByquizId(examId);
            jcSelectquizId.addItem("" + d.getQuizTitle());
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == jcSelectquizId) {
            Object selected = jcSelectquizId.getSelectedItem();
            String QuizT = selected.toString();
            int quizID = QuizDao.getQuizIdByTitle(QuizT);
            StudentScore score = PerformanceDAO.getStudentScore(StudentId, quizID);
            DecimalFormat df = new DecimalFormat("0.00");           
            txtLanguage.setText(score.getCoursename());
            txtPercentage.setText("" +df.format(score.getPer())+"/100");
        }
    }
}
