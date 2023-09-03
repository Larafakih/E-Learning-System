package Student;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import DAO.*;
import Model.*;
import UI.*;

public class Message extends JFrame implements ActionListener {

    private JTextArea messageTextArea;
    private JButton sendBtn;
    private JLabel receiverNameLabel, writeMsgLbl;
    private JScrollPane scroll;
    private JPanel mainPanel;
    private int to_ID, currentStudentID;
    private String toname, role;
    private JFrame other;

    public Message(int from_ID, String toName, int to_ID, final JFrame otherFrame, String r) {
        super("Message");
        setLayout(new BorderLayout());
        this.to_ID = to_ID;
        currentStudentID = from_ID;
        toname = toName;
        other = otherFrame;
        role = r;

        receiverNameLabel = new JLabel(toname);
        receiverNameLabel.setHorizontalAlignment(JLabel.CENTER);
        receiverNameLabel.setFont(new Font(Font.SERIF, Font.BOLD, 23));
        add(receiverNameLabel, BorderLayout.NORTH);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        writeMsgLbl = new JLabel("Write Message");
        writeMsgLbl.setHorizontalAlignment(JLabel.LEFT);
        writeMsgLbl.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
        mainPanel.add(writeMsgLbl, BorderLayout.NORTH);

        messageTextArea = new JTextArea();
        messageTextArea.setLineWrap(true);
        messageTextArea.setWrapStyleWord(true);
        messageTextArea.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        messageTextArea.setToolTipText("Write Message Here.");
        scroll = new JScrollPane(messageTextArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(scroll, BorderLayout.CENTER);

        sendBtn = new JButton("Send");
        sendBtn.setIcon(img.CreateImageIcon("icons/send.png", 30, 30));
        sendBtn.setPreferredSize(new Dimension(0, 50));
        sendBtn.addActionListener(this);
        add(sendBtn, BorderLayout.SOUTH);

        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 320);
        setLocation(515, 300);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sendBtn) {
            AddMessage();
        }
    }

    public void AddMessage() {
        String messageStr = messageTextArea.getText();
        java.util.Date dt = new java.util.Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String time_Stamp = dateFormat.format(dt);

        Messages Mes = new Messages(messageStr, time_Stamp, currentStudentID, to_ID);
        int x = MessageDao.InsertIntoMessage(Mes);
        if (x == 0) {
            new project.Error("Some Error occured", currentStudentID, "Student");
        } else {
            JOptionPane.showMessageDialog(null, "Message Sent");
            dispose();
            if (role == "Sent") {
                other.dispose();
                new SentBox(currentStudentID);
            } else {

            }
        }
    }
}
