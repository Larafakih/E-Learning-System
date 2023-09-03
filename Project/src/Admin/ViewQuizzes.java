package Admin;

import UI.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import DAO.*;
import java.util.*;
import Model.*;
import Student.ViewForumMessage;
import java.awt.font.TextAttribute;
import javax.swing.border.*;
import javax.swing.event.*;

/**
 *
 * @author Lara
 */
public class ViewQuizzes implements ActionListener {

    private JFrame f, fquestion;//?
    private JPanel p, panelq, mainPanel;//?
    private ArrayList<Quiz> arrayquiz;
    private int NbofQuiz, quizid, count = 0,adminId; //?
    private JButton back;
 //   private JTable table, tableq;
    private JLabel Title, cbLbl; //?
    private ArrayList<Question> arrayquestion;
   // private JComboBox filterByCourses;
   // private String selectedData = null;
    private JScrollPane scrollpane;
   // private DefaultTableModel model, model1;
  //  private String[] columnName, columnNameq;
    private static final String RoleA = "Admin";
    private Font font;
    private ArrayList<Pair> PairsList;
    Pair evPair=null;
    private final String ViewStr = "EDIT";
    
    public ViewQuizzes(int id) {
        f = new JFrame("View Quizzes");
        f.setSize(870, 720);
        f.setLocation(10, 10);
        f.setLayout(new BorderLayout());
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        adminId=id;
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
    //    mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        f.add(mainPanel, BorderLayout.CENTER);
        
        back = new JButton();
        back.setBounds(0, 0, 100, 40);
        back.addMouseListener(new UI.BackListener(adminId, back, RoleA, f));
        back.setAlignmentX(FlowLayout.LEFT);

        JPanel designP1 = new JPanel();
        designP1.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        designP1.add(back);
  //      designP1.setBackground(Color.WHITE);
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

        arrayquiz = QuizDao.getQuizzes();
        
        GenerateQuizPanels();
        /*
        cbLbl = new JLabel("Filter By Course");
        cbLbl.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        cbLbl.setBounds(80, 25, 120, 28);
        cbLbl.setHorizontalAlignment(JLabel.LEFT);
        p.add(cbLbl);

        filterByCourses = new JComboBox(CoursesDao.getCourseName());
        filterByCourses.setBounds(200, 25, 140, 28);
        filterByCourses.addActionListener(this);
        p.add(filterByCourses);
       */
        
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       // if (e.getSource() == ) 
        {       
        }
    }
    
    private void GenerateQuizPanels()
    {
        Border paneEdge = BorderFactory.createEmptyBorder(0, 30, 0, 0);
        int TchId = 0, j;
        Teacher Tch = null;
        String CourseNm;
        Quiz q = null;
        
        JPanel spaceP1 = new JPanel();
        spaceP1.setBorder(new EmptyBorder(1, 0, 0, 0));
        this.mainPanel.add(spaceP1);
        
      for (j = 0; j < arrayquiz.size(); j++)
      {
          q = arrayquiz.get(j);
          System.out.println(q.toString());
          
        JPanel TchP = new JPanel();
        TchP.setBackground(Color.WHITE);
        TchP.setLayout(new FlowLayout(FlowLayout.LEFT, 80, 10));
       // TchP.setPreferredSize(TchP.getPreferredSize());
        
        JTextArea txtDetails = new JTextArea();
        txtDetails.setLineWrap(true);
        txtDetails.setFont(font);
        txtDetails.setWrapStyleWord(true);
        txtDetails.setEditable(false);
        txtDetails.setSize(new Dimension(200, 50));
        txtDetails.setMaximumSize(new Dimension(200, 50));
        TchId = q.getTeacherID();
        Tch = TeacherDao.SelectTeacherById(TchId);
        CourseNm = CoursesDao.getCourseName(q.getCourseID()).getName();
        txtDetails.setText(Tch.getFullName()+"\n"+CourseNm);
        
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
            
            Pair<JLabel, Integer,Object,Object> pairObj = new Pair<JLabel, Integer,Object,Object>(viewLbl, JLbID);
            PairsList.add(pairObj);
            
            TchP.add(viewLbl);

        JPanel spaceP2 = new JPanel();
        spaceP2.setBorder(new EmptyBorder(0, 0, 1, 0));

        //  JPanel spaceP4 = new JPanel();
        //  spaceP4.setBorder(new EmptyBorder(0, 20, 0, 0));

      //  this.mainPanel.add(spaceP1);
        this.mainPanel.add(TchP);
        JSeparator s = new JSeparator();
        s.setOrientation(SwingConstants.HORIZONTAL);
            
        this.mainPanel.add(spaceP1);
        this.mainPanel.add(s);
        this.mainPanel.add(spaceP2);
      
    }
    }
    public void load(ArrayList<Quiz> arrayquiz) 
    {
    }

    public void getQuestions(String selecteddata) {
        fquestion = new JFrame("questions");
        fquestion.setLayout(new BorderLayout());
        fquestion.setSize(1280, 720);
        fquestion.setLocation(10, 10);
        fquestion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        panelq = new JPanel();
        panelq.setLayout(null);

        fquestion.add(panelq);

        

        fquestion.setVisible(true);
    }

    public void loadQuestions(String selecteddata) {
        quizid = QuizDao.getQuizIdByTitle(selecteddata);
        arrayquestion = QuestionDao.getQuestionByQuizId(quizid);
        System.out.println("" + selecteddata);

        
    }
    private void ViewMouseClicked(java.awt.event.MouseEvent evt) {

        findPairOfEvent(evt);
        ViewQuiz v = null;
        v = new ViewQuiz ((int)(evPair.getSecond()),adminId);
       // f.dispose();
       f.setVisible(false);
    }

    private Pair findPairOfEvent(java.awt.event.MouseEvent evt)
    {
        int i = 0;    
        for (i = 0; i < PairsList.size(); i++) {
            if (PairsList.get(i).getFirst() == evt.getSource()) 
            {
                System.out.println("id of quiz :" + (int) PairsList.get(i).getSecond());               
                evPair=PairsList.get(i);
                break;
            }
        }
        return evPair;
    }
    private void ViewMouseEntered(java.awt.event.MouseEvent evt) {
        findPairOfEvent(evt);
        JLabel jl=(JLabel)evPair.getFirst();
        jl.setForeground(Color.RED);
    }

    private void ViewMouseExited(java.awt.event.MouseEvent evt) {
        findPairOfEvent(evt);
        JLabel jl=(JLabel)evPair.getFirst();
        jl.setForeground(Color.BLUE);
    }
}