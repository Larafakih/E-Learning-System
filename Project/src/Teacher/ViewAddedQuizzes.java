package Teacher;

import UI.*;
import java.awt.*;
import javax.swing.*;
import DAO.*;
import java.util.*;
import Model.*;
import javax.swing.border.*;

/**
 *
 * @author Salma
 */
public class ViewAddedQuizzes {

    private JFrame f, fquestion;
    private JPanel p, panelq, mainPanel;
    private ArrayList<Quiz> arrayquiz;
    private int quizid, count = 0, TeacherId;
    private JButton back;
    private JLabel Title, cbLbl;
    private ArrayList<Question> arrayquestion;
    private JScrollPane scrollpane;
    private static final String RoleA = "Teacher";
    private Font font;
    private ArrayList<Pair> PairsList;
    Pair evPair = null;

    public ViewAddedQuizzes(int id) {
        f = new JFrame("View Quizzes");
        f.setSize(870, 720);
        f.setLocation(10, 10);
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        TeacherId = id;
        font = new Font("Tahoma", 1, 12);

        PairsList = new ArrayList<Pair>();

        p = new JPanel();
        p.setLayout(null);
        f.add(p, BorderLayout.CENTER);

        Title = new TitleLbl("View Quizzes");
        f.add(Title, BorderLayout.NORTH);

        mainPanel = new JPanel();
        Border paneEdge = BorderFactory.createEmptyBorder(10, 5, 5, 5);
        mainPanel.setBorder(paneEdge);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        f.add(mainPanel, BorderLayout.CENTER);

        back = new JButton();
        back.setBounds(0, 0, 100, 40);
        back.addMouseListener(new UI.BackListener(TeacherId, back, RoleA, f));
        back.setAlignmentX(FlowLayout.LEFT);

        JPanel designP1 = new JPanel();
        designP1.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        designP1.add(back);
        Dimension d = new Dimension(900, 50);
        designP1.setSize(d);
        designP1.setMaximumSize(d);
        mainPanel.add(designP1);

        JPanel designP = new JPanel();
        designP.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 0));

        scrollpane = new JScrollPane(mainPanel);
        scrollpane.setBounds(20, 70, 800, 200);
        scrollpane.setViewportView(mainPanel);
        f.add(scrollpane);
        scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        arrayquiz = QuizDao.getQuizzesForTeacher(TeacherId);

        GenerateQuizPanels();
       

        f.setVisible(true);
    }


    private void GenerateQuizPanels() {
        Border paneEdge = BorderFactory.createEmptyBorder(0, 30, 0, 0);
        int j;
        //Teacher Tch = null;
        String CourseNm;
        Quiz q = null;

        JPanel spaceP1 = new JPanel();
        spaceP1.setBorder(new EmptyBorder(1, 0, 0, 0));
        this.mainPanel.add(spaceP1);

        for (j = 0; j < arrayquiz.size(); j++) {
            q = arrayquiz.get(j);
            System.out.println(q.toString());

            JPanel TchP = new JPanel();
            TchP.setBackground(Color.WHITE);
            TchP.setLayout(new FlowLayout(FlowLayout.LEFT, 80, 10));
        
            JTextArea txtDetails = new JTextArea();
            txtDetails.setLineWrap(true);
            txtDetails.setFont(font);
            txtDetails.setWrapStyleWord(true);
            txtDetails.setEditable(false);
            txtDetails.setSize(new Dimension(200, 50));
            txtDetails.setMaximumSize(new Dimension(200, 50));
            CourseNm = CoursesDao.getCourseName(q.getCourseID()).getName();
            txtDetails.setText(CourseNm);

            JScrollPane jScrollPane1 = new JScrollPane(txtDetails);
            jScrollPane1.setBounds(250, 200, 500, 100);
            jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            jScrollPane1.setViewportView(txtDetails);
            jScrollPane1.setMaximumSize(txtDetails.getMaximumSize());
            jScrollPane1.setPreferredSize(txtDetails.getSize());
            TchP.add(jScrollPane1);

            JTextArea qTitle = new JTextArea();
            qTitle.setLineWrap(true);
            qTitle.setFont(font);
            qTitle.setWrapStyleWord(true);
            qTitle.setEditable(false);
            qTitle.setSize(new Dimension(200, 50));
            qTitle.setMaximumSize(new Dimension(200, 50));
            qTitle.setText(q.getQuizTitle());

            JScrollPane jScrollPane12 = new JScrollPane(qTitle);
            jScrollPane12.setBounds(250, 200, 500, 100);
            jScrollPane12.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            jScrollPane12.setViewportView(qTitle);
            jScrollPane12.setMaximumSize(qTitle.getMaximumSize());
            jScrollPane12.setPreferredSize(txtDetails.getSize());
            TchP.add(jScrollPane12);

            JLabel viewLbl = new JLabel("<HTML><U>View</U></HTML>");
            viewLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            viewLbl.setForeground(Color.BLUE);
            viewLbl.setAlignmentX(FlowLayout.LEFT);
            viewLbl.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    ViewMouseClicked(evt);
                }

                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    ViewMouseEntered(evt);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    ViewMouseExited(evt);
                }
            });

            Integer JLbID = new Integer(arrayquiz.get(j).getQuizID());

            Pair<JLabel, Integer, Object, Object> pairObj = new Pair<JLabel, Integer, Object, Object>(viewLbl, JLbID);
            PairsList.add(pairObj);

            TchP.add(viewLbl);

            JPanel spaceP2 = new JPanel();
            spaceP2.setBorder(new EmptyBorder(0, 0, 1, 0));

            this.mainPanel.add(TchP);
            JSeparator s = new JSeparator();
            s.setOrientation(SwingConstants.HORIZONTAL);

            this.mainPanel.add(spaceP1);
            this.mainPanel.add(s);
            this.mainPanel.add(spaceP2);

        }
    }




    private void ViewMouseClicked(java.awt.event.MouseEvent evt) {

        findPairOfEvent(evt);
        ViewSpecificQuiz v = null;
        v = new ViewSpecificQuiz((int) (evPair.getSecond()), TeacherId);
        f.setVisible(false);
    }

    private Pair findPairOfEvent(java.awt.event.MouseEvent evt) {
        int i = 0;
        for (i = 0; i < PairsList.size(); i++) {
            if (PairsList.get(i).getFirst() == evt.getSource()) {
                System.out.println("id of quiz :" + (int) PairsList.get(i).getSecond());
                evPair = PairsList.get(i);
                break;
            }
        }
        return evPair;
    }

    private void ViewMouseEntered(java.awt.event.MouseEvent evt) {
        findPairOfEvent(evt);
        JLabel jl = (JLabel) evPair.getFirst();
        jl.setForeground(Color.RED);
    }

    private void ViewMouseExited(java.awt.event.MouseEvent evt) {
        findPairOfEvent(evt);
        JLabel jl = (JLabel) evPair.getFirst();
        jl.setForeground(Color.BLUE);
    }

}

class ViewSpecificQuiz {

    int currQuizId, adminId;
    JFrame f;
    JPanel mainPanel;
    JButton back;
    JLabel Title;
    Font font;
    ArrayList<Question> arrayquestion;
    JScrollPane scrollpane;
    Quiz quiz;

    public ViewSpecificQuiz(int qId, int adId) {
        f = new JFrame("View Quiz Details");
        currQuizId = qId;
        adminId = adId;
        quiz = QuizDao.SelectQuizByquizId(qId);
        f.setSize(870, 720);
        f.setLocation(10, 10);
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      
        mainPanel = new JPanel();
        Border paneEdge = BorderFactory.createEmptyBorder(10, 5, 5, 5);
        mainPanel.setBorder(paneEdge);
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        f.add(mainPanel, BorderLayout.CENTER);
        font = new Font("Tahoma", Font.BOLD, 13);

        Title = new TitleLbl("View Specific Quiz Details");
        f.add(Title, BorderLayout.NORTH);

        back = new JButton();
        back.setBounds(0, 0, 100, 40);
        back.addMouseListener(new UI.BackListener(adminId, back, "ViewQuizTeacher", f));
        back.setAlignmentX(FlowLayout.LEFT);

        JPanel designP1 = new JPanel();
        designP1.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        designP1.add(back);
        designP1.setBackground(Color.WHITE);
        Dimension d = new Dimension(900, 40);
        designP1.setSize(d);
        designP1.setMaximumSize(d);
        mainPanel.add(designP1);

        JPanel designP = new JPanel();
        designP.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 0));

        scrollpane = new JScrollPane(mainPanel);
        scrollpane.setBounds(20, 70, 800, 200);
        scrollpane.setViewportView(mainPanel);
        f.add(scrollpane);
        scrollpane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        GenerateQuizDetailsPanel();
        GenerateQuestionDetails();

        f.setVisible(true);
    }

    private void GenerateQuizDetailsPanel() {

        JPanel QuizP = new JPanel();
        QuizP.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 0));
        QuizP.setBackground(new Color(255, 230, 171));

        JPanel quizBoxP = new JPanel();
        quizBoxP.setLayout(new BoxLayout(quizBoxP, BoxLayout.Y_AXIS));
        quizBoxP.setBackground(Color.BLUE);

        JPanel flowtitleP = new JPanel();
        flowtitleP.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        flowtitleP.setBackground(new Color(255, 230, 171));

        JLabel titleLbl = new JLabel("Title: " + quiz.getQuizTitle());
        titleLbl.setFont(font);
        titleLbl.setAlignmentX(0);
        flowtitleP.add(titleLbl);
        quizBoxP.add(flowtitleP);

        JLabel descLbl = new JLabel("Description: ");
        descLbl.setFont(font);
        descLbl.setForeground(Color.BLACK);

        JPanel flowmsgP = new JPanel();
        flowmsgP.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        flowmsgP.setBackground(new Color(255, 230, 171));

        JTextArea descTextArea = new JTextArea();
        descTextArea.setLineWrap(true);
        descTextArea.setWrapStyleWord(true);
        descTextArea.setFont(font);
        descTextArea.setBackground(new Color(255, 230, 171));
        descTextArea.setEditable(false);
        descTextArea.setText(quiz.getQuizDescription());
        descTextArea.setSize(new Dimension(350, 170));
        descTextArea.setMaximumSize(new Dimension(350, 170));

        JScrollPane scroll = new JScrollPane(descTextArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        scroll.setPreferredSize(descTextArea.getSize());

        flowmsgP.add(descLbl);
        quizBoxP.add(flowmsgP);
        quizBoxP.add(scroll);

        JLabel dateLbl = new JLabel("Date: " + quiz.getQuizDate());
        dateLbl.setFont(font.deriveFont(Font.ITALIC));

        JPanel flowdateP = new JPanel();
        flowdateP.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        flowdateP.setBackground(new Color(255, 230, 171));
        flowdateP.add(dateLbl);

        quizBoxP.add(flowdateP);

        JLabel durationLbl = new JLabel("Duration: " + quiz.getQuizDuration());
        durationLbl.setFont(font.deriveFont(Font.ITALIC));

        JPanel flowdurationP = new JPanel();
        flowdurationP.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        flowdurationP.setBackground(new Color(255, 230, 171));
        flowdurationP.add(durationLbl);

        quizBoxP.add(flowdurationP);

        JLabel fullmarkLbl = new JLabel("Full Mark: " + quiz.getFullMarks());
        fullmarkLbl.setFont(font.deriveFont(Font.ITALIC));

        JPanel flowfullmarkP = new JPanel();
        flowfullmarkP.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        flowfullmarkP.setBackground(new Color(255, 230, 171));
        flowfullmarkP.add(fullmarkLbl);

        quizBoxP.add(flowfullmarkP);

        QuizP.add(quizBoxP);

        JPanel spaceP1 = new JPanel();
        spaceP1.setBorder(new EmptyBorder(5, 0, 0, 0));
        spaceP1.setBackground(Color.white);

        JPanel spaceP2 = new JPanel();
        spaceP2.setBorder(new EmptyBorder(5, 0, 0, 0));

        this.mainPanel.add(QuizP);
        this.mainPanel.add(spaceP1);
        JSeparator s = new JSeparator();
        s.setOrientation(SwingConstants.HORIZONTAL);
        this.mainPanel.add(spaceP2);
    }

    public void GenerateQuestionDetails() {
        int quizid = quiz.getQuizID();
        arrayquestion = QuestionDao.getQuestionByQuizId(quizid);
        int questionNb = QuestionDao.CountQuestionByQuizId(quizid);
        int i = 0;
        Question question = null;
        System.out.println("size of array: " + arrayquestion.size());
        System.out.println("nb of questions: " + questionNb);
        for (i = 0; i < questionNb; i++) {
            question = arrayquestion.get(i);

            Border paneEdge = BorderFactory.createEmptyBorder(0, 30, 0, 0);

            JPanel quP1 = new JPanel();
            quP1.setBackground(Color.white);
            quP1.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
            quP1.setBorder(paneEdge);

            JPanel quP2 = new JPanel();
            quP2.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0));
            quP2.setBackground(Color.WHITE);
            quP1.add(quP2);

            JPanel flowP = new JPanel();
            flowP.setLayout(new FlowLayout(FlowLayout.LEFT, 70, 5));
            flowP.setMaximumSize(new Dimension(600, 100));
            flowP.setBackground(Color.white);

            JPanel DetailsBoxP = new JPanel();
            DetailsBoxP.setLayout(new BoxLayout(DetailsBoxP, BoxLayout.Y_AXIS));
            DetailsBoxP.setBackground(Color.white);
            DetailsBoxP.setMaximumSize(new Dimension(400, 100));
            flowP.add(DetailsBoxP);

            JTextArea QuTextArea = new JTextArea();
            QuTextArea.setLineWrap(true);
            QuTextArea.setWrapStyleWord(true);
            QuTextArea.setFont(font);
            QuTextArea.setEditable(false);
            QuTextArea.setText(arrayquestion.get(i).getQuestionTitle());
            QuTextArea.setSize(new Dimension(400, 100));
            QuTextArea.setMaximumSize(new Dimension(400, 100));

            JScrollPane scroll = new JScrollPane(QuTextArea);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setMaximumSize(new Dimension(630, 170));
            DetailsBoxP.add(scroll);

            JLabel A1Lbl = new JLabel("Answer1: ");
            A1Lbl.setFont(font.deriveFont(Font.ITALIC));
            A1Lbl.setForeground(Color.gray);

            DetailsBoxP.add(A1Lbl);

            JTextArea A1TextArea = new JTextArea();
            A1TextArea.setLineWrap(true);
            A1TextArea.setWrapStyleWord(true);
            A1TextArea.setFont(font);
            A1TextArea.setEditable(false);
            A1TextArea.setText(arrayquestion.get(i).getAnswer1());
            A1TextArea.setSize(new Dimension(400, 100));
            A1TextArea.setMaximumSize(new Dimension(400, 100));

            JScrollPane scroll1 = new JScrollPane(A1TextArea);
            scroll1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scroll1.setMaximumSize(new Dimension(630, 170));
            DetailsBoxP.add(scroll1);

            JLabel A2Lbl = new JLabel("Answer2: ");
            A2Lbl.setFont(font.deriveFont(Font.ITALIC));
            A2Lbl.setForeground(Color.gray);

            DetailsBoxP.add(A2Lbl);

            JTextArea A2TextArea = new JTextArea();
            A2TextArea.setLineWrap(true);
            A2TextArea.setWrapStyleWord(true);
            A2TextArea.setFont(font);
            A2TextArea.setEditable(false);
            A2TextArea.setText(arrayquestion.get(i).getAnswer2());
            A2TextArea.setSize(new Dimension(400, 100));
            A2TextArea.setMaximumSize(new Dimension(400, 100));

            JScrollPane scroll2 = new JScrollPane(A2TextArea);
            scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scroll2.setMaximumSize(new Dimension(630, 170));
            DetailsBoxP.add(scroll2);

            JLabel A3Lbl = new JLabel("Answer3: ");
            A3Lbl.setFont(font.deriveFont(Font.ITALIC));
            A3Lbl.setForeground(Color.gray);

            DetailsBoxP.add(A3Lbl);

            JTextArea A3TextArea = new JTextArea();
            A3TextArea.setLineWrap(true);
            A3TextArea.setWrapStyleWord(true);
            A3TextArea.setFont(font);
            A3TextArea.setEditable(false);
            A3TextArea.setText(arrayquestion.get(i).getAnswer2());
            A3TextArea.setSize(new Dimension(400, 100));
            A3TextArea.setMaximumSize(new Dimension(400, 100));

            JScrollPane scroll3 = new JScrollPane(A3TextArea);
            scroll3.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scroll3.setMaximumSize(new Dimension(630, 170));
            DetailsBoxP.add(scroll3);

            JLabel correct = new JLabel("correct answer: " + arrayquestion.get(i).getCorrectAnswer());
            correct.setFont(font.deriveFont(Font.BOLD));
            correct.setForeground(Color.red);

            DetailsBoxP.add(correct);
            quP2.add(flowP);

            JPanel spaceP1 = new JPanel();
            spaceP1.setBorder(new EmptyBorder(1, 0, 0, 0));

            JPanel spaceP2 = new JPanel();
            spaceP2.setBorder(new EmptyBorder(1, 0, 0, 0));

            JPanel spaceP4 = new JPanel();
            spaceP4.setBorder(new EmptyBorder(0, 20, 0, 0));

            this.mainPanel.add(quP1);
            JSeparator s = new JSeparator();
            s.setOrientation(SwingConstants.HORIZONTAL);

            this.mainPanel.add(spaceP1);
            this.mainPanel.add(s);
            this.mainPanel.add(spaceP2);
        }
    }
}
