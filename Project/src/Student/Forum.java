package Student;

import DAO.*;
import Model.*;
import java.util.*;
import UI.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import javax.swing.*;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import javax.swing.table.*;

/**
 *
 * @author Lara
 */
public class Forum extends JFrame implements ActionListener {

    JPanel middlePanel;
    int currentStudentID;
    JLabel title, imageLb;
    JButton addMessageBtn,backBtn;
    DefaultTableModel model;
    JTable table;
    Font font;
    JFrame f;
    JScrollPane scrollpane = null;
   // JLabel repliesLbl;
    Pair evPair=null;
    ArrayList<Pair> PairsList;
    String RoleS = "Student";

    public Forum(int StdID) {
        f = new JFrame("Students Forum");
        f.setLayout(new BorderLayout());
        this.currentStudentID = StdID;
        font = new Font("Tahoma", 1, 14);
        PairsList = new ArrayList<Pair>();

        title = new TitleLbl("Welcome to Students Forum!");
        f.add(title, BorderLayout.NORTH);

        middlePanel = new JPanel();
        middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
        f.add(middlePanel, BorderLayout.CENTER);

        backBtn = new JButton();
        backBtn.setBounds(0, 0, 100, 40);
        backBtn.addMouseListener(new UI.BackListener(currentStudentID, backBtn, RoleS, f));
        backBtn.setAlignmentX(FlowLayout.LEFT);

        JPanel designP1 = new JPanel();
        designP1.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        designP1.add(backBtn);
       // designP1.setLocation(0,30);
      //  designP1.setBackground(Color.WHITE);
        Dimension d=new Dimension(1000,40);
        designP1.setSize(d);
        designP1.setMaximumSize(d);
//        designP1.setAlignmentX(FlowLayout.LEFT);
        middlePanel.add(designP1);
        
        JPanel designP = new JPanel();
        designP.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 1));

        addMessageBtn = new CircleBtn();
        addMessageBtn.setText("Add Message");
        addMessageBtn.setBounds(100, 70, 100, 50);
        addMessageBtn.addActionListener(this);
        designP.add(addMessageBtn);
        middlePanel.add(designP);

        JPanel imageFlowP = new JPanel();
        imageFlowP.setLayout(new FlowLayout(FlowLayout.LEFT, 350, 0));
        imageLb = new JLabel(img.CreateImageIcon("icons/discussion1.png", 120, 120));
        imageFlowP.add(imageLb);
        designP.add(imageFlowP);

        JSeparator s = new JSeparator();
        s.setOrientation(SwingConstants.HORIZONTAL);
        middlePanel.add(s);

        scrollpane = new JScrollPane(middlePanel);
        scrollpane.setBounds(20, 70, 800, 200);
        scrollpane.setBackground(Color.white);
        //scrollpane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setViewportView(middlePanel);
        f.add(scrollpane);

        GeneratePostPanels(ForumDao.loadForumMessages());

        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setResizable(false);
        f.setSize(900, 710);
        f.setLocation(10, 5);
        f.setVisible(true);
    }

    public void GeneratePostPanels(ArrayList<ForumMessage> msgsArray) {
        int j;
        System.out.println(msgsArray.size());
        for (j = 0; j < msgsArray.size(); j++) {

            JPanel postPanel = new JPanel();
            postPanel.setBackground(Color.white);
            postPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
            
            JPanel UserFlowP = new JPanel();
            UserFlowP.setBackground(Color.WHITE);
            UserFlowP.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 5));
            UserFlowP.setAlignmentX(0);
                    
            JPanel UserBoxP = new JPanel();
            UserBoxP.setLayout(new BoxLayout(UserBoxP, BoxLayout.Y_AXIS));
            UserBoxP.setBackground(Color.white);

            UserFlowP.add(UserBoxP);

            int UserId = msgsArray.get(j).getStudentID();
            Student std = StudentDao.SelectStudentById(UserId);

            JLabel usericon = new JLabel();
            usericon.setAlignmentX(FlowLayout.LEFT);
            BufferedImage bufferedImage = null;
            JLabel fullNameLabel = new JLabel();
            fullNameLabel.setFont(font);

            bufferedImage = Controller.UserController.SetProfilePicture("Student", UserId, bufferedImage, usericon, fullNameLabel);

            UserBoxP.add(usericon);
            UserBoxP.add(fullNameLabel);
            UserBoxP.setPreferredSize(UserBoxP.getPreferredSize());

            JPanel DetailsFlowP = new JPanel();
            DetailsFlowP.setLayout(new FlowLayout(FlowLayout.LEFT, 200, 5));
            DetailsFlowP.setLocation(100, 0);
            DetailsFlowP.setBackground(Color.white);

            JPanel DetailsBoxP = new JPanel();
            DetailsBoxP.setLayout(new BoxLayout(DetailsBoxP, BoxLayout.Y_AXIS));
            DetailsBoxP.setAlignmentX(FlowLayout.LEFT);
            DetailsBoxP.setBackground(Color.white);

            JPanel titleFlowP = new JPanel();
            titleFlowP.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
            titleFlowP.setAlignmentX(FlowLayout.LEFT);
            titleFlowP.setBackground(Color.white);
            
            TxtLabelLines titleMsgLbl = new TxtLabelLines(msgsArray.get(j).getTitle());
            titleMsgLbl.setFont(new Font(Font.SERIF, Font.PLAIN, 15).deriveFont(Font.BOLD));
            titleMsgLbl.breakintoLines();
            titleFlowP.add(titleMsgLbl);
            DetailsBoxP.add(titleFlowP);

            JLabel DateLbl = new JLabel("Date : " + msgsArray.get(j).getMessageDate().toString());
            DateLbl.setFont(font.deriveFont(Font.ITALIC));
            DateLbl.setForeground(Color.gray);
            DetailsBoxP.add(DateLbl);

            
            JLabel repliesLbl = new JLabel("<HTML><U>View</U></HTML>");
            repliesLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            repliesLbl.setForeground(Color.BLUE);
            repliesLbl.setAlignmentX(FlowLayout.LEFT);
            repliesLbl.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    RepliesMouseClicked(evt);
                }

                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    RepliesMouseEntered(evt);
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    RepliesMouseExited(evt);
                }
            });
            int id = msgsArray.get(j).getForumMessageID();
            Integer JLbID = new Integer(msgsArray.get(j).getForumMessageID());
            
            Pair<JLabel, Integer,Object,Object> pairObj = new Pair<JLabel, Integer,Object,Object>(repliesLbl, JLbID);
            PairsList.add(pairObj);

            DetailsFlowP.add(DetailsBoxP);
            
            int count = ForumDao.getNbofAnswers(msgsArray.get(j).getForumMessageID());
            JLabel countAnsLbl = new JLabel("("+String.valueOf(count) + " Answers)");
            countAnsLbl.setForeground(Color.BLUE);
            countAnsLbl.setAlignmentX(FlowLayout.LEFT);
            
            JPanel ansP = new JPanel();
            ansP.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
            ansP.setBackground(Color.WHITE);
            ansP.add(repliesLbl);
            ansP.add(countAnsLbl);
            
            DetailsFlowP.add(ansP);

            postPanel.add(UserFlowP);
            postPanel.add(DetailsFlowP);

            this.middlePanel.add(postPanel);

            JSeparator s = new JSeparator();
            s.setOrientation(SwingConstants.HORIZONTAL);
            this.middlePanel.add(s);
        }
    }

    private void RepliesMouseClicked(java.awt.event.MouseEvent evt) {

        findPairOfEvent(evt);
        ViewForumMessage v = null;
        v = new ViewForumMessage((int)evPair.getSecond(), currentStudentID);
       // f.dispose();
       f.setVisible(false);
    }

    private Pair findPairOfEvent(java.awt.event.MouseEvent evt)
    {
        int i = 0;    
        for (i = 0; i < PairsList.size(); i++) {
            if (PairsList.get(i).getFirst() == evt.getSource()) 
            {
                System.out.println("id of message :" + (int) PairsList.get(i).getSecond());               
                evPair=PairsList.get(i);
                break;
            }
        }
        return evPair;
    }
    private void RepliesMouseEntered(java.awt.event.MouseEvent evt) {
        findPairOfEvent(evt);
        JLabel jl=(JLabel)evPair.getFirst();
        jl.setForeground(Color.RED);
    }

    private void RepliesMouseExited(java.awt.event.MouseEvent evt) {
        findPairOfEvent(evt);
        JLabel jl=(JLabel)evPair.getFirst();
        jl.setForeground(Color.BLUE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addMessageBtn) {
            f.setVisible(false);
            new Post(currentStudentID, f,null);
        }
    }
}
