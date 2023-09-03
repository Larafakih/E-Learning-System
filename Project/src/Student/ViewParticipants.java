package Student;

import java.awt.*;
import java.awt.event.*;
import DAO.*;
import UI.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class ViewParticipants implements ActionListener {

    JLabel title, courseCbLbl;
    JComboBox coursesCb;
    JPanel middlePanel;
    JScrollPane scroll;
    JTable table;
    DefaultTableModel model;
    JButton contactBtn, back;
    int currentStudentID;
    JFrame f;
    String RoleS = "Student";
    String[] columnNames = {"Name", "Email"};

    public ViewParticipants(int c) {
        f = new JFrame("View Particpants");
        f.setLayout(new BorderLayout());
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setSize(800, 600);
        f.setLocation(10, 10);
        currentStudentID = c;

        title = new TitleLbl("Particpants");
        f.add(title, BorderLayout.NORTH);

        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        f.add(middlePanel, BorderLayout.CENTER);

        back = new JButton();
        back.setBounds(500, 0, 100, 40);
        middlePanel.add(back);
        back.addMouseListener(new UI.BackListener(currentStudentID, back, RoleS, f));

        courseCbLbl = new TxtLabel("Select Course");
        courseCbLbl.setBounds(10, 50, 300, 28);
        middlePanel.add(courseCbLbl);

        coursesCb = new JComboBox(CoursesDao.getEnrolledCourses(currentStudentID));
        coursesCb.setSelectedIndex(-1);
        coursesCb.setBounds(250, 50, 350, 28);
        coursesCb.addActionListener(this);
        middlePanel.add(coursesCb);

        table = new Table(30, columnNames);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent event) {
                String role = table.getValueAt(table.getSelectedRow(), 1).toString();
                contactBtn.setEnabled(true);
            }
        });
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 110, 650, 300);
        middlePanel.add(pane);

        contactBtn = new JButton("Contact");
        contactBtn.setToolTipText("Click to Message the selected Participant.");
        contactBtn.setPreferredSize(new Dimension(50, 40));
        contactBtn.addActionListener(this);
        f.add(contactBtn, BorderLayout.SOUTH);

        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == coursesCb) {
            FilterByCourse();
        } else if (ae.getSource() == contactBtn) {
            SendMessage();
        }
    }

    public void SendMessage() {
        if (table.getSelectedRow() != -1) {
            int rowNum = table.getSelectedRow();
            String toName = model.getValueAt(rowNum, 0).toString();
            Model.Student s = StudentDao.SelectStudent(model.getValueAt(rowNum, 1).toString());
            int to_id = s.getId();
            new Message(currentStudentID, toName, to_id, f, "P");
        }
    }

    public void FilterByCourse() {
        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        Object selected = coursesCb.getSelectedItem();
        String courseName = selected.toString();
        Object[][] Studentdata = CoursesDao.ViewParticipantsInCourse(courseName, currentStudentID);
        for (int c = 0; c < columnNames.length; c++) {
            model.addColumn(columnNames[c]);
        }
        for (int r = 0; r < Studentdata.length; r++) {
            model.addRow(Studentdata[r]);
        }
    }
}
