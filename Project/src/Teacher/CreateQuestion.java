package Teacher;

import DAO.*;
import Model.*;
import UI.CircleBtn;
import java.awt.*;
import static java.awt.Component.LEFT_ALIGNMENT;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.*;

/**
 *
 * @author Lara
 */
public class CreateQuestion extends JFrame implements ActionListener {

    private JFrame f,otherF;
    Quiz currentQuiz;
    int currentTeacherID, nbQst = 0;
    Boolean currentQuizAdded = false;
    String crsName, datreStr, questionStr, choice1Str, choice2Str, choice3Str;
    JLabel title, TitleLb1, CouseLb1, SubjectLb1, DateLb1, DurationLb1, FullMarkLb1;
    JLabel DescrLbl, questionLbl, choiceLbl, chooseCorrAnsLbl;
    JComboBox correctsAnsCmb;
    JTextField  choiceField1, choiceField2, choiceField3;
    JTextArea questionArea;
    JButton btnNext, btnFinish, btnCancel;
    JPanel detailsPanelV1, detailsPanelV2, detailsPanelH, DescrPanel, FinalPanel;
    JPanel designPan1, designPan2, designPan3, designPan4;
    JPanel designPan5, designPan6, designPan7, designPan8, designPan9, designPan10;
    Question currentQuestion;
    QuestionStore qStore;
    private String questionTitleStr;
    private String correctsAns;
    int fullmark, marks = 0;
    private Font font;
    private static final String RoleT = "Teacher";

    public CreateQuestion(Quiz q, String crs,Frame otherF) throws ParseException {
        f = new JFrame("Create Question");
        currentQuiz = q;
        fullmark = q.getFullMarks();
        currentTeacherID = q.getTeacherID();
        crsName = crs;
        qStore = new QuestionStore();
        otherF = otherF;
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setResizable(false);
        f.setSize(800, 700);
        f.setLocation(10, 10);
        System.out.println("" + marks);
        font = new Font("Tahoma", 1, 12);

        title = new JLabel("Create Question", JLabel.CENTER);
        title.setFont(title.getFont().deriveFont(22.0f));
        title.setBackground(Color.LIGHT_GRAY);
        title.setForeground(Color.BLACK);
        title.setOpaque(true);
        f.add(title, BorderLayout.NORTH);

        FinalPanel = new JPanel();
        FinalPanel.setLayout(new BoxLayout(FinalPanel, BoxLayout.Y_AXIS));
        FinalPanel.setAlignmentX(LEFT_ALIGNMENT);

        detailsPanelV1 = new JPanel();
        detailsPanelV1.setLayout(new BoxLayout(detailsPanelV1, BoxLayout.Y_AXIS));

        CouseLb1 = new JLabel("Couse: " + crsName);
        CouseLb1.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        CouseLb1.setBounds(80, 30, 120, 28);
        CouseLb1.setHorizontalAlignment(JLabel.CENTER);
        detailsPanelV1.add(CouseLb1);

        TitleLb1 = new JLabel("Title: " + currentQuiz.getQuizTitle());
        TitleLb1.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        TitleLb1.setBounds(80, 30, 120, 28);
        TitleLb1.setHorizontalAlignment(JLabel.CENTER);
        detailsPanelV1.add(TitleLb1);

        detailsPanelV2 = new JPanel();
        detailsPanelV2.setLayout(new BoxLayout(detailsPanelV2, BoxLayout.Y_AXIS));

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        datreStr = format.format(currentQuiz.getQuizDate());

        DateLb1 = new JLabel("Date: " + datreStr);
        DateLb1.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        DateLb1.setBounds(80, 30, 120, 28);
        DateLb1.setHorizontalAlignment(JLabel.CENTER);
        detailsPanelV2.add(DateLb1);

        DurationLb1 = new JLabel("Duration: " + currentQuiz.getQuizDuration());
        DurationLb1.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        DurationLb1.setBounds(80, 30, 120, 28);
        DurationLb1.setHorizontalAlignment(JLabel.CENTER);
        detailsPanelV2.add(DurationLb1);

        FullMarkLb1 = new JLabel("Fullmark: " + currentQuiz.getFullMarks());
        FullMarkLb1.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        FullMarkLb1.setBounds(80, 30, 120, 28);
        FullMarkLb1.setHorizontalAlignment(JLabel.CENTER);
        detailsPanelV2.add(FullMarkLb1);

        detailsPanelH = new JPanel();
        detailsPanelH.setLayout(new FlowLayout(FlowLayout.LEADING, 80, 5));
        detailsPanelH.add(detailsPanelV1);
        detailsPanelH.add(detailsPanelV2);
        detailsPanelH.setMaximumSize(detailsPanelH.getPreferredSize());

        FinalPanel.add(detailsPanelH);

        designPan1 = new JPanel();
        designPan1.setLayout(new FlowLayout(FlowLayout.LEADING, 80, 5));

        DescrLbl = new JLabel("Description: " + currentQuiz.getQuizDescription());
        DescrLbl.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        DescrLbl.setBounds(80, 30, 120, 28);
        DescrLbl.setHorizontalAlignment(JLabel.CENTER);
        designPan1.add(DescrLbl);
        designPan1.getPreferredSize();

        FinalPanel.add(designPan1);

        designPan2 = new JPanel();
        designPan2.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 10));
        designPan2.setLocation(80, 10);

        questionLbl = new JLabel("Type your question here:");
        questionLbl.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        questionLbl.setBounds(80, 0, 0, 28);
        questionLbl.setHorizontalAlignment(JLabel.CENTER);
        designPan2.add(questionLbl);
/*
        questionField = new JTextField("", 40);
        questionField.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        questionField.setBounds(5, 0, 180, 28);
        questionField.setHorizontalAlignment(JLabel.CENTER);
        designPan2.add(questionField);
  */      
         questionArea = new JTextArea();
        questionArea.setLineWrap(true);
        questionArea.setFont(font);
        questionArea.setWrapStyleWord(true);
        questionArea.setEditable(true);
        questionArea.setSize(new Dimension(250, 80));
        questionArea.setMaximumSize(new Dimension(200, 50));
        
        
        JScrollPane jScrollPane1 = new JScrollPane(questionArea);
        jScrollPane1.setBounds(250, 200, 500, 100);
        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setViewportView(questionArea);
        jScrollPane1.setMaximumSize(questionArea.getMaximumSize());
        jScrollPane1.setPreferredSize(questionArea.getSize());        
        designPan2.add(jScrollPane1);

        designPan2.getPreferredSize();
        FinalPanel.add(designPan2);

        designPan3 = new JPanel();
        designPan3.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 10));
        designPan3.setLocation(80, 10);

        choiceLbl = new JLabel("Option 1:");
        choiceLbl.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        choiceLbl.setBounds(80, 0, 120, 28);
        choiceLbl.setHorizontalAlignment(JLabel.CENTER);
        designPan3.add(choiceLbl);

        choiceField1 = new JTextField("", 25);
        choiceField1.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        choiceField1.setBounds(100, 0, 180, 28);
        choiceField1.setHorizontalAlignment(JLabel.CENTER);
        choiceField1.addActionListener(this);
        designPan3.add(choiceField1);

        FinalPanel.add(designPan3);

        designPan9 = new JPanel();
        designPan9.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 5));
        designPan9.setLocation(80, 10);

        chooseCorrAnsLbl = new JLabel("Correct Option:");
        chooseCorrAnsLbl.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        chooseCorrAnsLbl.setBounds(80, 0, 120, 28);
        chooseCorrAnsLbl.setHorizontalAlignment(JLabel.CENTER);
        designPan9.add(chooseCorrAnsLbl);

        correctsAnsCmb = new JComboBox();
        correctsAnsCmb.setModel(new DefaultComboBoxModel<>(new String[]{"Option 1", "Option 2", "Option 3"}));
        correctsAnsCmb.setBounds(200, 60, 140, 28);
        correctsAnsCmb.addActionListener(this);
        designPan9.add(correctsAnsCmb);

        designPan3.add(designPan9);

        designPan4 = new JPanel();
        designPan4.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 10));

        choiceLbl = new JLabel("Option 2:");
        choiceLbl.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        choiceLbl.setBounds(80, 0, 120, 28);
        choiceLbl.setHorizontalAlignment(JLabel.CENTER);
        designPan4.add(choiceLbl);

        choiceField2 = new JTextField("", 25);
        choiceField2.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        choiceField2.setHorizontalAlignment(JLabel.CENTER);
        choiceField2.addActionListener(this);
        designPan4.add(choiceField2);

        FinalPanel.add(designPan4);

        designPan5 = new JPanel();
        designPan5.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 10));

        choiceLbl = new JLabel("Option 3:");
        choiceLbl.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        choiceLbl.setBounds(80, 0, 120, 28);
        choiceLbl.setHorizontalAlignment(JLabel.CENTER);
        designPan5.add(choiceLbl);

        choiceField3 = new JTextField("", 25);
        choiceField3.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        choiceField3.setHorizontalAlignment(JLabel.CENTER);
        choiceField3.addActionListener(this);
        designPan5.add(choiceField3);

        FinalPanel.add(designPan5);

        designPan6 = new JPanel();
        designPan6.setLayout(new FlowLayout(FlowLayout.LEADING, 30, 30));

        designPan7 = new JPanel();
        designPan7.setLayout(new FlowLayout(FlowLayout.LEADING, 160, 10));

        btnNext = new CircleBtn("Next Question");
        btnNext.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        btnNext.setHorizontalAlignment(JButton.CENTER);
        btnNext.addActionListener(this);
        designPan7.add(btnNext);

        btnFinish = new CircleBtn("Finish");
        btnFinish.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        btnFinish.setHorizontalAlignment(JButton.CENTER);
        btnFinish.addActionListener(this);
        designPan7.add(btnFinish);
        
        btnCancel = new CircleBtn("Cancel");
        btnCancel.setFont(new Font(Font.SERIF, Font.BOLD, 15));
        btnCancel.setHorizontalAlignment(JButton.CENTER);
    //  new UI.BackListener(currentTeacherID, btnCancel, RoleT, f));
        btnCancel.addActionListener(this);
        designPan7.add(btnCancel);

        FinalPanel.add(designPan7);
        f.setContentPane(FinalPanel);

        f.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnNext) {
            if (!validateInputs()) {
                JOptionPane.showMessageDialog(null, "Fill all fields");
            } else {
                nbQst++;
                getInputs(qStore);
            }
        } else if (ae.getSource() == btnFinish) {
            int x = 0;
            if (!currentQuizAdded) // this quiz is already added , we are adding only questions into it
            {
                Quiz.setQuizDateInSQLFormat(currentQuiz);
                x = QuizDao.InsertIntoQuiz(currentQuiz);
                currentQuizAdded = true;
            }
            if (!IsEmptyFields()) //There is new inputs when finish button is clicked
            {
                nbQst++;
                getInputs(qStore);
            }
            try {
                Question.insertQuestionsIntoDatabse(currentQuiz, qStore);
                f.dispose();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else if (ae.getSource() == btnCancel)
        {
            new UI.BackListener(currentTeacherID, btnCancel, RoleT, f);
        }
    }

    public Boolean IsEmptyFields() {
        if (questionArea.getText().isEmpty() && choiceField1.getText().isEmpty() && choiceField2.getText().isEmpty() && choiceField3.getText().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean IsEmptyInputs() {
        if (questionStr.isEmpty() && choice1Str.isEmpty() && choice2Str.isEmpty() && choice3Str.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public void getInputs(QuestionStore qS) {
        questionStr = questionArea.getText().trim();
        choice1Str = choiceField1.getText().trim();
        choice2Str = choiceField2.getText().trim();
        choice3Str = choiceField3.getText().trim();
        correctsAns = correctsAnsCmb.getSelectedItem().toString();

        currentQuestion = new Question(questionStr, nbQst, choice1Str, choice2Str, choice3Str, correctsAns);
        qS.addQuestion(currentQuestion);
        clearAll();
    }

    public boolean validateInputs() {

        questionStr = questionArea.getText().trim();
        choice1Str = choiceField1.getText().trim();
        choice2Str = choiceField2.getText().trim();
        choice3Str = choiceField3.getText().trim();

        correctsAns = correctsAnsCmb.getSelectedItem().toString();
        if (questionStr.isEmpty() || choice1Str.isEmpty() || choice2Str.isEmpty() || choice3Str.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }

    public void clearAll() {
        questionArea.setText("");
        choiceField1.setText("");
        choiceField2.setText("");
        choiceField3.setText("");
        correctsAnsCmb.setSelectedIndex(0);
    }
}
