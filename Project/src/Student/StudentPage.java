package Student;

import UI.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Users.*;

public class StudentPage implements ActionListener {

    private JPanel rightPanel, buttonsPanel;
    private JButton EnrollCourse, StudyBtn, WithdrawCourse, ViewParticipants;
    private JButton ViewTeachers, viewscore, inboxBtn, sentboxBtn, ForumBtn;
    private int currentID;
    private String email;
    private final static String RoleS = "Student";
    private JFrame f;
    private JLabel Title2, mainTitle, clock;

    public StudentPage(int c) {
        f = new JFrame("Student Module");
        f.setIconImage(img.SystemIcon());
        f.setLayout(new BorderLayout());
        f.setSize(1280, 720);
        f.setLocation(10, 10);
        currentID = c;

        System.out.println(currentID);
        
        rightPanel = new JPanel(new BorderLayout());
        f.add(rightPanel, BorderLayout.CENTER);

        Profile.action(RoleS, currentID, f);

        mainTitle = new TitleLbl("Student Module");
        rightPanel.add(mainTitle, BorderLayout.NORTH);
        clock = new JLabel();
        SystemTime.clock(clock);
        rightPanel.add(clock, BorderLayout.SOUTH);

        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(null);
        rightPanel.add(buttonsPanel, BorderLayout.CENTER);

        Title2 = new JLabel("Student Operations:");
        Title2.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        Title2.setForeground(new Color(0, 142, 204));
        Title2.setHorizontalAlignment(JLabel.LEFT);
        Title2.setBounds(20, 60, 250, 50);
        buttonsPanel.add(Title2);

        EnrollCourse = new OperationBtn("Enroll Course", "icons/enrollCourse.png", 50, 50);
        EnrollCourse.setBounds(250, 210, 140, 90);
        EnrollCourse.addActionListener((ActionListener) this);
        buttonsPanel.add(EnrollCourse);

        StudyBtn = new OperationBtn("LET'S STUDY!", "icons/StartCourse.png", 50, 50);
        StudyBtn.setBounds(410, 210, 140, 90);
        StudyBtn.addActionListener((ActionListener) this);
        buttonsPanel.add(StudyBtn);

        WithdrawCourse = new OperationBtn("Withdraw Course", "icons/withdrawCourse.png", 40, 40);
        WithdrawCourse.setBounds(570, 210, 140, 90);
        WithdrawCourse.addActionListener((ActionListener) this);
        buttonsPanel.add(WithdrawCourse);

        ViewParticipants = new OperationBtn("View Participants", "icons/study-group.png", 50, 50);
        ViewParticipants.setBounds(250, 320, 140, 90);
        ViewParticipants.addActionListener((ActionListener) this);
        buttonsPanel.add(ViewParticipants);

        ViewTeachers = new OperationBtn("View Teachers", "icons/view-teachers.png", 50, 50);
        ViewTeachers.setBounds(410, 320, 140, 90);
        ViewTeachers.addActionListener((ActionListener) this);
        buttonsPanel.add(ViewTeachers);

        viewscore = new OperationBtn("View score", "icons/score.png", 50, 50);
        viewscore.setBounds(570, 320, 140, 90);
        viewscore.addActionListener((ActionListener) this);
        buttonsPanel.add(viewscore);

        ForumBtn = new OperationBtn("Discussion Forum", "icons/Forum.png", 50, 50);
        ForumBtn.setBounds(410, 430, 140, 90);
        ForumBtn.addActionListener((ActionListener) this);
        buttonsPanel.add(ForumBtn);

        inboxBtn = new OperationBtn("Received", "icons/inbox.png", 50, 50);
        inboxBtn.setBounds(950, 200, 140, 100);
        inboxBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        inboxBtn.setBorderPainted(false);
        inboxBtn.addActionListener(this);
        buttonsPanel.add(inboxBtn);

        sentboxBtn = new OperationBtn("Sent", "icons/sentbox.png", 50, 50);
        sentboxBtn.setBounds(950, 320, 140, 100);
        sentboxBtn.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        sentboxBtn.setBorderPainted(false);
        sentboxBtn.addActionListener(this);
        buttonsPanel.add(sentboxBtn);

        f.setResizable(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == EnrollCourse) {
            new EnrollCourse(currentID);
            f.setVisible(false);
        } else if (ae.getSource() == StudyBtn) {
            new StudyCourse(currentID);
            f.setVisible(false);
        } else if (ae.getSource() == WithdrawCourse) {
            new WithdrawCourse(currentID);
            f.setVisible(false);
        } else if (ae.getSource() == ViewParticipants) {
            new ViewParticipants(currentID);
            f.setVisible(false);
        } else if (ae.getSource() == ViewTeachers) {
            new ViewTeacher(currentID);
            f.setVisible(false);
        } else if (ae.getSource() == viewscore) {
            new ViewScores(currentID);
            f.setVisible(false);
        } else if (ae.getSource() == inboxBtn) {
            new Inbox(currentID);
        } else if (ae.getSource() == sentboxBtn) {
            new SentBox(currentID);
        } else if (ae.getSource() == ForumBtn) {
            new Forum(currentID);
            f.setVisible(false);
        }
    }
}
