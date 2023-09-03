package Student;

import DAO.*;
import Model.Student;
import UI.CircleBtn;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.*;

public class SentBox implements ActionListener, ListSelectionListener {

    private JList messagesList;
    private DefaultListModel listModel;
    private JLabel title, messageLbl,selectStdLb;
    private JPanel mainPanel;
    private JTextArea messageTxt;
    private JButton messageBtn, deleteBtn, newMsg;
    private JScrollPane scroll1, scroll2;
    private String[][] messagesListData = null;
    private int currentToUserID, currentMessageID, currentStudentID;
    private String currentToUserName, currentUserName;
    private JFrame f;
    private JComboBox studentsCb;
    private String stdData[];
    private ArrayList<Student> datalist;

    public SentBox(int c) {
        f = new JFrame("Sent");
        f.setLayout(new BorderLayout());
        currentStudentID = c;
        title = new JLabel("Sent");
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
        selectStdLb.setBounds(30, 8, 150, 50);
        mainPanel.add(selectStdLb);
        
        System.out.println(currentStudentID);
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
        studentsCb.setBounds(200, 15, 150, 28);
        mainPanel.add(studentsCb);
        
        newMsg = new CircleBtn("New Message");
        newMsg.addActionListener(this);
        newMsg.setEnabled(true);
        newMsg.setBounds(400, 15, 150, 30);
        mainPanel.add(newMsg);
        
        listModel = new DefaultListModel();
        messagesList = new JList(listModel);
        messagesList.setFixedCellHeight(40);
        messagesList.setFixedCellWidth(150);
        messagesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        messagesList.addListSelectionListener(this);
        scroll1 = new JScrollPane(messagesList);
        scroll1.setBounds(50, 50, 500, 220);
        mainPanel.add(scroll1);

        
        messagesListData = MessageDao.loadMessagesSent(currentStudentID);
        int modelIndex = 0;
        for (int n = messagesListData.length - 1; n >= 0; n--) {
            StringBuilder elementStr = new StringBuilder();
            elementStr.append("<html><pre><b>");
            elementStr.append(String.format("%s \t\t\t %s", "To: " + messagesListData[n][5], "At:  " + messagesListData[n][1]));
            elementStr.append("</b></pre></html>");
            listModel.addElement(elementStr);
            messagesListData[n][6] = String.valueOf(modelIndex);
            modelIndex++;
        }

        messageLbl = new JLabel("Message");
        messageLbl.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        messageLbl.setBounds(30, 270, 70, 50);
        mainPanel.add(messageLbl);

        messageTxt = new JTextArea();
        messageTxt.setLineWrap(true);
        messageTxt.setWrapStyleWord(true);
        messageTxt.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
        messageTxt.setEditable(false);
        scroll2 = new JScrollPane(messageTxt);
        scroll2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll2.setBounds(30, 310, 550, 210);
        mainPanel.add(scroll2);

        messageBtn = new JButton("Text");
        messageBtn.addActionListener(this);
        messageBtn.setEnabled(false);
        messageBtn.setBounds(455, 530, 80, 30);
        mainPanel.add(messageBtn);

        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setSize(600, 640);
        f.setLocation(385, 100);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == messageBtn) {
            SendMessage();
        }
        else if (e.getSource() == newMsg)
        {
            if(studentsCb.getSelectedIndex()!=-1)            
            {    
            int index = studentsCb.getSelectedIndex();
            Student ToStd=datalist.get(index);
            currentToUserName = ToStd.getFullName();
            currentToUserID = ToStd.getId();

            if(!currentToUserName.isEmpty())
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
            if (index == Integer.parseInt(messagesListData[i][6])) {
                messageTxt.setText(messagesListData[i][3]);
                currentToUserID = Integer.parseInt(messagesListData[i][4]);
                currentToUserName = messagesListData[i][5];
                currentMessageID = Integer.parseInt(messagesListData[i][0]);
                messageBtn.setEnabled(true);
            }
        }
    }

    public void SendMessage() {
        new Message(currentStudentID, currentToUserName, currentToUserID, f, "Sent");
    }
}
