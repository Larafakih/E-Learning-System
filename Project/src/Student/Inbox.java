package Student;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import Model.*;
import DAO.*;
import UI.CircleBtn;
import java.util.ArrayList;

public class Inbox implements ActionListener, ListSelectionListener {

    JList messagesList;
    DefaultListModel listModel;
    JLabel title, messageLbl,selectStdLb;
    JPanel mainPanel;
    JTextArea messageTxt;
    JButton replyBtn, deleteBtn,newMsg;
    JScrollPane scroll1, scroll2;
    String[][] messagesListData;
    int currentFromUserID, currentMessageID, ToUserID;
    String currentFromUserName, ToUserName;
    int currentStudentID;
    JFrame f;
    private JComboBox studentsCb;
    private String stdData[];
    private ArrayList<Student> datalist;

    public Inbox(int c) {
        f = new JFrame("Received");
        f.setLayout(new BorderLayout());
        currentStudentID = c;

        title = new JLabel("Received");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font(Font.SERIF, Font.BOLD, 23));
        title.setBackground(Color.LIGHT_GRAY);
        title.setForeground(Color.BLACK);
        title.setOpaque(true);
        f.add(title, BorderLayout.NORTH);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        f.add(mainPanel, BorderLayout.CENTER);

        selectStdLb = new JLabel("Select Participant: ");
        selectStdLb.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        selectStdLb.setBounds(30, 20, 150, 50);
        mainPanel.add(selectStdLb);
        
        datalist = CoursesDao.SameCourse(currentStudentID);
        stdData = new String[datalist.size()];
        System.out.println(stdData.length);
        if(datalist.size()!=0)
        {
        int i;
        for(i=0;i<datalist.size();i++)
        stdData[i]=(String) datalist.get(i).getFullName();
        }

        studentsCb = new JComboBox(stdData);
        studentsCb.setSelectedIndex(-1);
        studentsCb.setBounds(200, 25, 150, 28);
        mainPanel.add(studentsCb);
        
        newMsg = new CircleBtn("New Message");
        newMsg.addActionListener(this);
        newMsg.setEnabled(true);
        newMsg.setBounds(400, 20, 150, 30);
        mainPanel.add(newMsg);
        
        listModel = new DefaultListModel();
        int x= MessageDao.CountInboxByStdId(currentStudentID);
        if(x==0)
        {/*
            JLabel emptyLbl = new JLabel("You haven't received any message");
        emptyLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        emptyLbl.setBounds(30, 270, 70, 50);
        mainPanel.add(emptyLbl);
*/
        }
        else
        {
            loadInboxList();
        }
        messagesList = new JList(listModel);
        messagesList.setFixedCellHeight(40);
        messagesList.setFixedCellWidth(150);
        messagesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        messagesList.addListSelectionListener(this);
        scroll1 = new JScrollPane(messagesList);
        scroll1.setBounds(50, 70, 500, 220);
        mainPanel.add(scroll1);

        messageLbl = new JLabel("Message");
        messageLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        messageLbl.setBounds(30, 290, 70, 50);
        mainPanel.add(messageLbl);

        messageTxt = new JTextArea();
        messageTxt.setLineWrap(true);
        messageTxt.setWrapStyleWord(true);
        messageTxt.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        messageTxt.setEditable(true);
        scroll2 = new JScrollPane(messageTxt);
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll2.setBounds(30, 330, 550, 210);
        mainPanel.add(scroll2);

        replyBtn = new JButton("Reply");
        replyBtn.addActionListener(this);
        replyBtn.setEnabled(false);
        replyBtn.setBounds(455, 550, 100, 30);
        mainPanel.add(replyBtn);

        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(600, 660);
        f.setLocation(385, 70);
        f.setVisible(true);
    }
private void loadInboxList()
{
        messagesListData = MessageDao.loadMessagesInbox(currentStudentID);

        int modelIndex = 0;
        for (int n = messagesListData.length - 1; n >= 0; n--) {

            StringBuilder elementStr = new StringBuilder();
            elementStr.append("<html><pre><b>");
            elementStr.append(String.format("%s \t\t\t %s", "From: " + messagesListData[n][4], "At:  " + messagesListData[n][1]));
            elementStr.append("</b></pre></html>");
            listModel.addElement(elementStr);
            messagesListData[n][5] = String.valueOf(modelIndex);
            modelIndex++;
        }
}
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == replyBtn) {
            SendMessage();
        }
        else if (e.getSource() == newMsg)
        {
            if(studentsCb.getSelectedIndex()!=-1)            
            {    
            int index = studentsCb.getSelectedIndex();
            Student ToStd=datalist.get(index);
            currentFromUserName = ToStd.getFullName();
            currentFromUserID = ToStd.getId();
            if(!currentFromUserName.isEmpty())
            SendMessage();
           // new NewMessage(currentStudentID);
            }
            else
             JOptionPane.showMessageDialog(null, "Please select a participant");       
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int index = messagesList.getSelectedIndex();
        for (int i = 0; i < messagesListData.length; i++) {
            if (index == Integer.parseInt(messagesListData[i][5])) {
                messageTxt.setText(messagesListData[i][3]);
                currentFromUserID = Integer.parseInt(messagesListData[i][2]);
                currentFromUserName = messagesListData[i][4];
                currentMessageID = Integer.parseInt(messagesListData[i][0]);
                replyBtn.setEnabled(true);
            }
        }
    }
    public void SendMessage() {
        new Message(currentStudentID, currentFromUserName, currentFromUserID, f, "I");
    }
}
