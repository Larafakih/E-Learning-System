package Teacher;

import java.awt.*;
import java.awt.event.*;
import DAO.*;
import UI.*;
import Model.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Salma
 */
public class ViewMyStudentScore extends JFrame implements ActionListener {

    private String[] columnNames = new String[]{"STUDENT Name", "QUIZ Title", "RIGHT", "WRONG", "UNATTEMPTED", "PERCENTAGE"};
    private String[][] data;
    private JLabel title, cbLbl;
    private JPanel middlePanel;
    private JTable jtStudentData;
    private JComboBox filterByCourseCb;
    private JScrollPane jScrollPane1;
    private int currentId;
    private JButton back;
    private static final String RoleT = "Teacher";
    private DefaultTableModel model ;
    private ArrayList<Performance> performanceList;
    
    public ViewMyStudentScore(int c) {
        super("View My Students Score");
        currentId = c;
        setIconImage(img.SystemIcon());
        setSize(1280, 720);
        setLocation(10, 10);
        setLayout(new BorderLayout());

        title = new TitleLbl("My Students Scores Details");
        add(title, BorderLayout.NORTH);

        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        add(middlePanel, BorderLayout.CENTER);

        back = new JButton();
        back.setBounds(880, 0, 100, 40);
        middlePanel.add(back);
        back.addMouseListener(new UI.BackListener(currentId, back, RoleT, this));

        cbLbl = new TxtLabel("Filter By Courses");
        cbLbl.setBounds(80, 25, 300, 28);
        middlePanel.add(cbLbl);

        jScrollPane1 = new JScrollPane();
        jtStudentData = new UI.Table(90, columnNames);

        filterByCourseCb = new JComboBox(CoursesDao.getCourseNameForT(currentId));
        filterByCourseCb.setSelectedIndex(-1);
        filterByCourseCb.setBounds(300, 25, 320, 28);
        filterByCourseCb.addActionListener(this);
        middlePanel.add(filterByCourseCb);

        jScrollPane1.setViewportView(jtStudentData);
        jScrollPane1.setBounds(20, 80, 1230, 540);
        middlePanel.add(jScrollPane1);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == filterByCourseCb) {
            Object selected = filterByCourseCb.getSelectedItem();
            String CName = selected.toString();          
            if (CName.isEmpty()) {
                clearTable();
                JOptionPane.showConfirmDialog(null, "Please select a course");
            } else {
                performanceList = PerformanceDAO.getDataByCoursename(CName);
                showDataInTableBycoursename(CName);
            }
        }
    }

    private void showDataInTableBycoursename(String Cname) {
        model = (DefaultTableModel) jtStudentData.getModel();
        clearTable();
      //  ArrayList<Performance> performanceList = PerformanceDAO.getDataByCoursename(Cname);
        if (performanceList.size() == 0) {
            JOptionPane.showMessageDialog(null, "No records Found!", "No Data", JOptionPane.DEFAULT_OPTION);      
            return;
        }
      //  DefaultTableModel model = (DefaultTableModel) jtStudentData.getModel();
        Object[] row = new Object[7];
        for (Performance p : performanceList) {
            Student st = StudentDao.SelectStudentById(p.getStudentId());
            row[0] = st.getFullName();
            Quiz qu = QuizDao.SelectQuizByquizId(p.getQuizID());
            row[1] = qu.getQuizTitle();
            row[2] = p.getRightA();
            row[3] = p.getWrongA();
            row[4] = p.getUnattempted();
            DecimalFormat df = new DecimalFormat("0.00");           
            row[5] = df.format(p.getPer());
            model.addRow(row);
        }
    }
    private void clearTable()
    {
        model.setRowCount(0);
       // model.setColumnCount(0);
    }
}
