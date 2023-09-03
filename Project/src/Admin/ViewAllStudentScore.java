package Admin;

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
public class ViewAllStudentScore extends JFrame implements ActionListener {

    private JLabel title, cbLbl;
    private JPanel middlePanel;
    private JTable jtStudentData;
    private JComboBox filterByCourseCb;
    private JScrollPane jScrollPane1;
    private String[] columnName;
    private ArrayList<Performance> performanceList;
    private DefaultTableModel model;
    private JButton back;
    private int adminId;

    public ViewAllStudentScore(int id) {
        super("View All Student Score");
        setIconImage(img.SystemIcon());
        setSize(1280, 720);
        setLocation(10, 10);
        setLayout(new BorderLayout());
        adminId = id;

        title = new TitleLbl("All Students Scores Details");
        add(title, BorderLayout.NORTH);

        middlePanel = new JPanel();
        middlePanel.setLayout(null);
        add(middlePanel, BorderLayout.CENTER);

        back = new JButton();
        back.setBounds(880, 0, 100, 40);
        middlePanel.add(back);
        back.addMouseListener(new UI.BackListener(adminId, back, "Admin", this));

        cbLbl = new TxtLabel("Filter By Courses");
        cbLbl.setBounds(100, 25, 300, 28);
        middlePanel.add(cbLbl);

        filterByCourseCb = new JComboBox(CoursesDao.getCourseName());
        filterByCourseCb.setBounds(300, 25, 140, 28);
        filterByCourseCb.setSelectedIndex(-1);
        filterByCourseCb.addActionListener(this);
        middlePanel.add(filterByCourseCb);

        columnName = new String[]{"STUDENT Name", "Quiz Title", "Course Name", "RIGHT", "WRONG", "UNATTEMPTED", "PERCENTAGE"};
        jtStudentData = new Table(50, columnName);
        jScrollPane1 = new JScrollPane(jtStudentData);
        jScrollPane1.setBounds(20, 95, 1230, 540);
        middlePanel.add(jScrollPane1);
        performanceList = PerformanceDAO.getAllData();
        showDataInTable(performanceList, 0);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == filterByCourseCb) 
        {
            Object selected = filterByCourseCb.getSelectedItem();
            String CName = selected.toString();
            performanceList = PerformanceDAO.getDataByCoursename(CName);
            System.out.println(performanceList.toString());
            System.out.println(performanceList.size());
            showDataInTable(performanceList, 1);
        }
    }

    private void showDataInTable(ArrayList<Performance> performanceList, int i) {
        model = (DefaultTableModel) jtStudentData.getModel();
        if (performanceList.size() == 0) {
            model.setRowCount(0);
          //  model.setColumnCount(0);
            return;
        }
        if (i == 1) {
            columnName = new String[]{"STUDENT Name", "Quiz Title", "RIGHT", "WRONG", "UNATTEMPTED", "PERCENTAGE"};
            model = new DefaultTableModel(new Object[][]{},columnName);
            jtStudentData.setModel(model);
        }

        Object[] row = new Object[7];
        for (Performance p : performanceList) {
            Student st = StudentDao.SelectStudentById(p.getStudentId());
            row[0] = st.getFullName();
            Quiz qu = QuizDao.SelectQuizByquizId(p.getQuizID());
            row[1] = qu.getQuizTitle();
            if (i == 0) {
                row[2] = p.getCourseName();
                row[3] = p.getRightA();
                row[4] = p.getWrongA();
                row[5] = p.getUnattempted();
                DecimalFormat df = new DecimalFormat("0.00");           
                row[6] = df.format(p.getPer());
            } else {
                row[2] = p.getRightA();
                row[3] = p.getWrongA();
                row[4] = p.getUnattempted();
                DecimalFormat df = new DecimalFormat("0.00");           
                row[5] = df.format(p.getPer());
            }
            model.addRow(row);
        }
    }
}
