package Student;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import DAO.*;
import Model.*;
import UI.*;
import java.util.Date;

/**
 *
 * @author Lara
 */
public class Post extends JFrame implements ActionListener {

    int currentStID, fmViewId;
    JTextArea postTextArea;
    JScrollPane scroll;
    JButton postBtn, backBtn;
    JPanel topPanel, designP;
    JLabel writeLbl, titleLbl, imageLb, title;
    JTextField titleFd;
    JFrame f, other;
    Font font;
    ForumMessage FmToUpdate, fmToAdd;
    String RoleS = "Forum";

    public Post(int s, final JFrame otherFrame, ForumMessage fmToUpdate) {
        f = new JFrame("Message");
        f.setLayout(new BorderLayout());
        currentStID = s;
        other = otherFrame;
        font = new Font("Tahoma", 1, 12);

        title = new TitleLbl("Write a Post");
        f.add(title, BorderLayout.NORTH);

        topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setAlignmentX(100);
        topPanel.setMaximumSize(new Dimension(300, 100));
        f.add(topPanel, BorderLayout.CENTER);

        backBtn = new JButton();
        backBtn.setBounds(0, 0, 100, 40);
        backBtn.addMouseListener(new UI.BackListener(currentStID, backBtn, RoleS, f));
        backBtn.setAlignmentX(FlowLayout.LEFT);

        JPanel designP2 = new JPanel();
        designP2.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        designP2.add(backBtn);
         Dimension d = new Dimension(1000, 40);
        designP2.setSize(d);
        designP2.setMaximumSize(d);
        topPanel.add(designP2);

        JPanel desPanel = new JPanel();
        desPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
        desPanel.setMaximumSize(new Dimension(600, 100));
        topPanel.add(desPanel);

        titleLbl = new JLabel("Title: ");
        titleLbl.setHorizontalAlignment(JLabel.LEFT);
        titleLbl.setBounds(80, 300, 180, 28);
        titleLbl.setFont(font);
        desPanel.add(titleLbl);

        titleFd = new JTextField(20);
        titleFd.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        titleFd.setBounds(80, 310, 180, 28);
        titleFd.setHorizontalAlignment(JLabel.CENTER);
        titleFd.setMaximumSize(new Dimension(150, 20));
        titleFd.addActionListener(this);
        desPanel.add(titleFd);

        JPanel desPane2 = new JPanel();
        desPane2.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
        desPane2.setMaximumSize(new Dimension(600, 250));
        topPanel.add(desPane2);

        writeLbl = new JLabel("Post");
        writeLbl.setHorizontalAlignment(JLabel.LEFT);
        writeLbl.setFont(font);
        desPane2.add(writeLbl);

        postTextArea = new JTextArea();
        postTextArea.setLineWrap(true);
        postTextArea.setWrapStyleWord(true);
        postTextArea.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        postTextArea.setToolTipText("Write Post Here.");
        postTextArea.setMaximumSize(new Dimension(300, 200));
        postTextArea.setLineWrap(true);
        postTextArea.setWrapStyleWord(true);

        imageLb = new JLabel(img.CreateImageIcon("icons/onmind.png", 120, 120));
        desPane2.add(imageLb);

        scroll = new JScrollPane(postTextArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(postTextArea.getMaximumSize());
        desPane2.add(scroll);

        postBtn = new JButton("Post");
        postBtn.setIcon(img.CreateImageIcon("icons/send.png", 30, 30));
        postBtn.setPreferredSize(new Dimension(0, 50));
        postBtn.addActionListener(this);
        f.add(postBtn, BorderLayout.SOUTH);

        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setResizable(false);
        f.setSize(700, 500);
        f.setLocation(515, 100);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String titleStr = titleFd.getText();
        String msgStr = postTextArea.getText();

        if (e.getSource() == postBtn) {
            if (postBtn.getText().equals("Post")) {
                if (!(validateInputs(titleStr, msgStr))) {
                    JOptionPane.showMessageDialog(null, "Fill All Fields", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    long m = System.currentTimeMillis();
                    Date d = new java.sql.Date(m);
                    System.out.println(d);
                    fmToAdd = new ForumMessage(currentStID, d, titleStr, msgStr);
                    if (ForumDao.InsertIntoForumMessages(fmToAdd) > 0) {
                        JOptionPane.showConfirmDialog(null, "Your post has been posted successfully", "Post added", JOptionPane.DEFAULT_OPTION);
                        f.dispose();
                        other.dispose();
                        new Forum(currentStID);
                    } else {
                        JOptionPane.showConfirmDialog(null, "Something went wrong", "Error Occured", JOptionPane.DEFAULT_OPTION);
                    }
                }
            } else {
                if (!(validateInputs(titleStr, msgStr))) {
                    JOptionPane.showMessageDialog(null, "Fill All Fields", "Invalid Input", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    FmToUpdate.setTitle(titleStr);
                    FmToUpdate.setMessage(msgStr);
                    if (ForumDao.updateForumMessage(FmToUpdate) > 0) {
                        JOptionPane.showConfirmDialog(null, "Your post has been updates successfully", "Post updated", JOptionPane.DEFAULT_OPTION);
                    } else {
                        JOptionPane.showConfirmDialog(null, "Something went wrong", "Error Occured", JOptionPane.DEFAULT_OPTION);
                    }
                    f.dispose();
                    other.dispose();
                    new Forum(currentStID);
                }
            }
        }
    }

    public Boolean validateInputs(String str1, String str2) {
        Boolean b = true;
        if (str1.isEmpty() || str2.isEmpty()) {
            b = false;
        }
        return b;
    }
}
