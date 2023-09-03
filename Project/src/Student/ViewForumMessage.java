package Student;

import DAO.*;
import Model.*;
import UI.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.SwingConstants.LEFT;
import static javax.swing.SwingConstants.RIGHT;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Lara
 */
public class ViewForumMessage extends JFrame implements ActionListener {

    int currentFmID, ansNb = 0, currentStdID;
    ForumMessage fm = null;
    //  Student currentStudent = null;
    JFrame f;
    JPanel msgP;
    JPanel mainPanel;
    JScrollPane scrollpane = null;
    Font font;
    JButton postBtn, editPostBtn, backBtn, deletePostBtn, deleteReplyBtn, editReplyBtn;
    JLabel imageLb;
    JTextArea replyTextArea, replyAreaToEdit;
    ArrayList<ForumAnswer> RepliesList = null;
    String RoleS = "Forum";
    ForumAnswer faEdited, fa;
    ArrayList<Pair> PairsList;
    final String EditRepClicked = "EDIT";
    final String DelRepClicked = "DELETE";
    String btnRepClicked = "";

    public ViewForumMessage(int fmId, int stdId) {
        f = new JFrame();
        f.setLayout(new BorderLayout());
        currentFmID = fmId;
        currentStdID = stdId;
        ansNb = ForumDao.getNbofAnswers(currentFmID);
        fm = ForumDao.SelectForumMessageById(currentFmID);
        Student stdOfMsg = StudentDao.SelectStudentById(fm.getStudentID());
        f.setTitle(stdOfMsg.getFirstName() + "'s Message");
        fm.setNbofanswers(ansNb);

        font = new Font("Tahoma", 1, 12);
        
        PairsList = new ArrayList<Pair>();
        JLabel title = new TitleLbl("Reply to a Post");
        f.add(title, BorderLayout.NORTH);

        mainPanel = new JPanel();
        Border paneEdge = BorderFactory.createEmptyBorder(10, 5, 5, 5);
        mainPanel.setBorder(paneEdge);
        mainPanel.setBackground(Color.white);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        f.add(mainPanel, BorderLayout.CENTER);

        backBtn = new JButton();
        backBtn.setBounds(0, 0, 100, 40);
        backBtn.addMouseListener(new UI.BackListener(currentStdID, backBtn, "Forum", f));
        backBtn.setAlignmentX(FlowLayout.LEFT);

        JPanel designP1 = new JPanel();
        designP1.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        designP1.add(backBtn);
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

        GenerateMessagePanel();
        GenerateReplyPanels();
        GenerateUserReplyArea();

        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setResizable(false);
        f.setSize(940, 700);
        f.setLocation(30, 10);
        f.setVisible(true);
    }

    private void GenerateUserReplyArea() {
        JPanel flowRepP = new JPanel();
        flowRepP.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
        flowRepP.setBackground(new Color(255, 230, 171));
        flowRepP.setSize(new Dimension(450, 450));
        flowRepP.setBorder(new EmptyBorder(5, 0, 5, 0));

        JLabel rplLbl = new JLabel("Type your reply");
        rplLbl.setFont(font);
        flowRepP.add(rplLbl);

        imageLb = new JLabel(img.CreateImageIcon("icons/msg.png", 100, 100));
        flowRepP.add(imageLb);

        replyTextArea = new JTextArea();
        replyTextArea.setLineWrap(true);
        replyTextArea.setWrapStyleWord(true);
        replyTextArea.setFont(font);
        replyTextArea.setSize(new Dimension(350, 100));
        replyTextArea.setMaximumSize(new Dimension(350, 100));

        JScrollPane scroll = new JScrollPane(replyTextArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setMaximumSize(replyTextArea.getMaximumSize());
        scroll.setPreferredSize(replyTextArea.getSize());
        flowRepP.add(scroll);

        postBtn = new JButton("Reply");
        postBtn.setIcon(img.CreateImageIcon("icons/send.png", 30, 30));
        postBtn.setPreferredSize(new Dimension(100, 50));
        postBtn.addActionListener(this);
        flowRepP.add(postBtn);

        mainPanel.add(flowRepP);
    }

    private void GenerateMessagePanel() {

        msgP = new JPanel();
        msgP.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 0));
        msgP.setBackground(new Color(255, 230, 171));

        JPanel flowUserP = new JPanel();
        flowUserP.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 5));
        flowUserP.setBackground(new Color(255, 230, 171));

        JPanel UserBoxP = new JPanel();
        UserBoxP.setLayout(new BoxLayout(UserBoxP, BoxLayout.Y_AXIS));
        UserBoxP.setBackground(new Color(255, 230, 171));
        flowUserP.add(UserBoxP);

        JLabel usericon = new JLabel();
        BufferedImage bufferedImage = null;
        JLabel fullNameLabel = new JLabel();
        fullNameLabel.setFont(font);
        int UserId = fm.getStudentID();
        Student std = StudentDao.SelectStudentById(UserId);
        bufferedImage = Controller.UserController.SetProfilePicture("Student", UserId, bufferedImage, usericon, fullNameLabel);

        UserBoxP.add(usericon);
        UserBoxP.add(fullNameLabel);
        UserBoxP.setPreferredSize(UserBoxP.getPreferredSize());

        JPanel msgBoxP = new JPanel();
        msgBoxP.setLayout(new BoxLayout(msgBoxP, BoxLayout.Y_AXIS));
        msgBoxP.setBackground(Color.BLUE);

        JPanel flowtitleP = new JPanel();
        flowtitleP.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        flowtitleP.setBackground(new Color(255, 230, 171));

        JLabel titleLbl = new JLabel("Post Title: " + fm.getTitle());
        titleLbl.setFont(font);
        titleLbl.setAlignmentX(0);
        flowtitleP.add(titleLbl);
        msgBoxP.add(flowtitleP);

        JLabel msgLbl = new JLabel("Post Message: ");
        msgLbl.setFont(font);
        msgLbl.setForeground(Color.BLACK);

        JPanel flowmsgP = new JPanel();
        flowmsgP.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        flowmsgP.setBackground(new Color(255, 230, 171));

        JTextArea MsgTextArea = new JTextArea();
        MsgTextArea.setLineWrap(true);
        MsgTextArea.setWrapStyleWord(true);
        MsgTextArea.setFont(font);
        MsgTextArea.setBackground(new Color(255, 230, 171));
        MsgTextArea.setEditable(false);
        MsgTextArea.setText(fm.getMessage());
        MsgTextArea.setSize(new Dimension(350, 170));
        MsgTextArea.setMaximumSize(new Dimension(350, 170));

        JScrollPane scroll = new JScrollPane(MsgTextArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        scroll.setPreferredSize(MsgTextArea.getSize());

        flowmsgP.add(msgLbl);
        msgBoxP.add(flowmsgP);
        msgBoxP.add(scroll);

        JLabel dateLbl = new JLabel("Date: " + fm.getMessageDate());
        dateLbl.setFont(font.deriveFont(Font.ITALIC));

        JPanel flowdateP = new JPanel();
        flowdateP.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        flowdateP.setBackground(new Color(255, 230, 171));
        flowdateP.add(dateLbl);

        msgBoxP.add(flowdateP);

        msgP.add(flowUserP);
        msgP.add(msgBoxP);

        if (this.fm.getStudentID() == currentStdID) {
            editPostBtn = new CircleBtn();
            editPostBtn.setText("Edit");
            editPostBtn.setBounds(100, 70, 100, 50);
            editPostBtn.addActionListener(this);
            msgP.add(editPostBtn);

            deletePostBtn = new CircleBtn();
            deletePostBtn.setText("Delete");
            deletePostBtn.setBounds(100, 70, 100, 50);
            deletePostBtn.addActionListener(this);
            msgP.add(deletePostBtn);
        }

        JPanel spaceP1 = new JPanel();
        spaceP1.setBorder(new EmptyBorder(5, 0, 0, 0));
        spaceP1.setBackground(Color.white);

        JPanel spaceP2 = new JPanel();
        spaceP2.setBorder(new EmptyBorder(5, 0, 0, 0));

        this.mainPanel.add(msgP);
        this.mainPanel.add(spaceP1);
        JSeparator s = new JSeparator();
        s.setOrientation(SwingConstants.HORIZONTAL);
        this.mainPanel.add(spaceP2);
    }

    private void GenerateReplyPanels() {
        int i = 0;
        ForumAnswer reply = null;
        RepliesList = ForumDao.loadMessageAnswers(fm.getForumMessageID());
        System.out.println(fm.getForumMessageID());
        System.out.println("ans: " + ansNb);
        System.out.println("l: " + RepliesList.size());
        for (i = 0; i < ansNb; i++) {
            //System.out.println("" + ansNb);
            reply = RepliesList.get(i);

            Border paneEdge = BorderFactory.createEmptyBorder(0, 30, 0, 0);

            JPanel rplP1 = new JPanel();
            rplP1.setBackground(Color.white);
            rplP1.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
            rplP1.setBorder(paneEdge);

            JPanel rplP2 = new JPanel();
            rplP2.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0));
            rplP2.setBackground(Color.WHITE);
            rplP1.add(rplP2);

            JPanel UserFlowP = new JPanel();
            UserFlowP.setBackground(Color.white);
            UserFlowP.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 5));

            JPanel UserBoxP = new JPanel();
            UserBoxP.setLayout(new BoxLayout(UserBoxP, BoxLayout.Y_AXIS));
            UserBoxP.setBackground(Color.white);
            UserFlowP.add(UserBoxP);

            JLabel usericon = new JLabel();
            BufferedImage bufferedImage = null;
            JLabel fullNameLabel = new JLabel();
            fullNameLabel.setFont(font);
            int UserId = reply.getStudentID();
            Student std = StudentDao.SelectStudentById(UserId);
            bufferedImage = Controller.UserController.SetProfilePicture("Student", UserId, bufferedImage, usericon, fullNameLabel);

            UserBoxP.add(usericon);
            UserBoxP.add(fullNameLabel);
            UserBoxP.setPreferredSize(UserBoxP.getPreferredSize());

            JPanel flowP = new JPanel();
            flowP.setLayout(new FlowLayout(FlowLayout.LEFT, 70, 5));
            flowP.setMaximumSize(new Dimension(600, 100));
            flowP.setBackground(Color.white);

            JPanel DetailsBoxP = new JPanel();
            DetailsBoxP.setLayout(new BoxLayout(DetailsBoxP, BoxLayout.Y_AXIS));
            DetailsBoxP.setBackground(Color.white);
            DetailsBoxP.setMaximumSize(new Dimension(400, 100));
            flowP.add(DetailsBoxP);

            JTextArea AnsTextArea = new JTextArea();
            AnsTextArea.setLineWrap(true);
            AnsTextArea.setWrapStyleWord(true);
            AnsTextArea.setFont(font);
            AnsTextArea.setEditable(false);
            AnsTextArea.setText(RepliesList.get(i).getAnswer());
            AnsTextArea.setSize(new Dimension(400, 100));
            AnsTextArea.setMaximumSize(new Dimension(400, 100));

            JScrollPane scroll = new JScrollPane(AnsTextArea);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            scroll.setMaximumSize(new Dimension(630, 170));
            DetailsBoxP.add(scroll);

            JLabel DateLbl = new JLabel("Date: " + RepliesList.get(i).getAnswerDate().toString());
            DateLbl.setFont(font.deriveFont(Font.ITALIC));
            DateLbl.setForeground(Color.gray);

            DetailsBoxP.add(DateLbl);

            if (reply.getStudentID() == currentStdID) {
                JPanel desP = new JPanel();
                desP.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 0));
                //desP.setMaximumSize(new Dimension(600, 100));
                desP.setBackground(Color.white);

                editReplyBtn = new CircleBtn();
                editReplyBtn.setText("Edit");
                editReplyBtn.setBounds(0, 0, 100, 50);
                editReplyBtn.addActionListener(this);
                desP.add(editReplyBtn);

                AnsTextArea.setEditable(true);
                deleteReplyBtn = new CircleBtn();
                deleteReplyBtn.setText("Delete");
                deleteReplyBtn.setBounds(0, 0, 100, 50);
                deleteReplyBtn.addActionListener(this);

                Pair<JTextArea, JButton, JButton, ForumAnswer> pairObj = new Pair<JTextArea, JButton, JButton, ForumAnswer>(AnsTextArea, editReplyBtn, deleteReplyBtn, reply);
                PairsList.add(pairObj);

                desP.add(deleteReplyBtn);
                flowP.add(desP);
            }
            rplP2.add(UserFlowP);
            rplP2.add(flowP);

            JPanel spaceP1 = new JPanel();
            spaceP1.setBorder(new EmptyBorder(1, 0, 0, 0));

            JPanel spaceP2 = new JPanel();
            spaceP2.setBorder(new EmptyBorder(1, 0, 0, 0));

            JPanel spaceP4 = new JPanel();
            spaceP4.setBorder(new EmptyBorder(0, 20, 0, 0));

            this.mainPanel.add(rplP1);
            JSeparator s = new JSeparator();
            s.setOrientation(SwingConstants.HORIZONTAL);
            
            this.mainPanel.add(spaceP1);
            this.mainPanel.add(s);
            this.mainPanel.add(spaceP2);
        }
    }

    private Pair findPairOfEventfromBtn(ActionEvent evt) {
        Pair evPair = null;
        int i = 0;
        for (i = 0; i < PairsList.size(); i++) {
            if (PairsList.get(i).getSecond() == evt.getSource() || PairsList.get(i).getThird() == evt.getSource()) {
                evPair = PairsList.get(i);
                break;
            }
        }
        return evPair;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Pair evP = findPairOfEventfromBtn(e);
        if (evP != null) {
            fa = (ForumAnswer) evP.getFourth();
            if (evP.getSecond() == e.getSource()) {
                try {

                    faEdited = (ForumAnswer) fa.clone();
                } catch (CloneNotSupportedException ex) {
                    Logger.getLogger(ViewForumMessage.class.getName()).log(Level.SEVERE, null, ex);
                }
                String reply = ((JTextArea) (evP.getFirst())).getText();
                if (reply.isEmpty()) {
                    JOptionPane.showConfirmDialog(null, "You must type a reply", "Invalid Input", JOptionPane.DEFAULT_OPTION);
                } else {
                    faEdited.setAnswer(reply);
                    if (ForumDao.updateForumAnswer(faEdited) > 0) {
                        JOptionPane.showConfirmDialog(null, "Your reply has been edited", "Reply Edited", JOptionPane.DEFAULT_OPTION);
                        f.dispose();
                        new ViewForumMessage(currentFmID, currentStdID);
                    } else {
                        new project.Error("Something went wrong", currentStdID, RoleS);
                    }
                }
            } else if (evP.getThird() == e.getSource()) {
                if (ForumDao.DeleteForumAnsById(fa.getForumAnswerID()) > 0) {
                    JOptionPane.showConfirmDialog(null, "Your reply has been deleted", "Reply Deleted", JOptionPane.DEFAULT_OPTION);
                    f.dispose();
                    new ViewForumMessage(currentFmID, currentStdID);
                } else {
                    new project.Error("Something went wrong", currentStdID, RoleS);
                }
            }
        } else if (e.getSource() == postBtn) {
            if (replyTextArea.getText().isEmpty()) {
                JOptionPane.showConfirmDialog(null, "Please Fill All Fields", "Invalid Inputs", JOptionPane.DEFAULT_OPTION);
            } else {
                String replyStr = replyTextArea.getText().toString();
                long milli = System.currentTimeMillis();
                ForumAnswer fa = new ForumAnswer(fm.getForumMessageID(), currentStdID, new java.sql.Date(milli), replyStr);
                if (ForumDao.InsertIntoForumAnswers(fa) > 0) {
                    JOptionPane.showConfirmDialog(null, "Your reply has been posted successfully", "Reply added", JOptionPane.DEFAULT_OPTION);
                    f.dispose();
                    new ViewForumMessage(currentFmID, currentStdID);
                } else {
                    new project.Error("Something went wrong", currentStdID, RoleS);
                }

            }
        } else if (e.getSource() == editPostBtn) {
            try {
                new EditPost(currentStdID, fm, this.f);
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(ViewForumMessage.class.getName()).log(Level.SEVERE, null, ex);
            }
            f.dispose();
        } else if (e.getSource() == deletePostBtn) {
            if (ForumDao.DeleteForumMessById(fm.getForumMessageID()) > 0) {
                JOptionPane.showConfirmDialog(null, "Your post has been deleted", "Post Deleted", JOptionPane.DEFAULT_OPTION);
                f.dispose();
                new Forum(currentStdID);
            } else {
                new project.Error("Something went wrong in deleting your post from Forum", currentStdID, RoleS);
            }

        }

    }
}
