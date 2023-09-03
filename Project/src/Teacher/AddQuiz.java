package Teacher;

import UI.*;
import Model.*;
import java.awt.*;
import java.awt.event.*;
import java.time.*;
import java.util.Date;
import java.util.*;
import java.text.*;
import javax.swing.*;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import DAO.*;

/**
 *
 * @author Lara
 */
public class AddQuiz extends JFrame implements ActionListener, FocusListener {

    private int currentTeacherID, quizID, subjectID, courseID;
    private Quiz addedQuiz;
    private Date quizDate;
    private String quizDurationStr;
    private JSpinner timeSpinner;
    private JLabel title, quizTitleLbl, teacherLbl, CourseLbl, nbQLbl, durationLbl, dateLbl, fullMarkLbl, courseCbLbl, quizDescriptionLbl;
    private JTextArea quizDescription;
    private JTextField quizTitle, nbQ, fullMark;
    private JComboBox coursesCb;
    private JButton nextBtn, cancelBtn, back;
    private JPanel middlePanel;
    private JScrollPane scroll;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    private JLabel image, fullValidation, dateValidation, durationValidation;
    private ArrayList<String> courseData;
    private boolean isexist = false;
    private String RoleT = "Teacher";

    public AddQuiz(int c) {
        super("Add Quiz");
        setLayout(new BorderLayout());
        currentTeacherID = c;
        setIconImage(img.SystemIcon());
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setSize(1150, 720);
        setLocation(10, 10);

        title = new TitleLbl("Add Quiz");
        add(title, BorderLayout.NORTH);
        courseData = new ArrayList<>();

        middlePanel = new JPanel(null);
        middlePanel.setLayout(null);
        add(middlePanel, BorderLayout.CENTER);

        back = new JButton();
        back.setBounds(900, 0, 100, 40);
        middlePanel.add(back);
        back.addMouseListener(new UI.BackListener(currentTeacherID, back, RoleT, this));

        image = new JLabel(img.CreateImageIcon("icons/Q.png", 400, 400));
        image.setBounds(500, 90, 500, 500);
        middlePanel.add(image);

        courseCbLbl = new JLabel("Select Course");
        courseCbLbl.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        courseCbLbl.setBounds(80, 60, 120, 28);
        courseCbLbl.setHorizontalAlignment(JLabel.CENTER);
        middlePanel.add(courseCbLbl);

        coursesCb = new JComboBox();
        coursesCb.setSelectedIndex(-1);
        coursesCb.setBounds(200, 60, 140, 28);
        coursesCb.addActionListener(this);
        middlePanel.add(coursesCb);

        quizDescriptionLbl = new JLabel("Quiz Description");
        quizDescriptionLbl.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        quizDescriptionLbl.setBounds(80, 130, 140, 28);
        quizDescriptionLbl.setHorizontalAlignment(JLabel.CENTER);
        middlePanel.add(quizDescriptionLbl);

        quizDescription = new JTextArea();
        quizDescription.setLineWrap(true);
        quizDescription.setWrapStyleWord(true);
        scroll = new JScrollPane(quizDescription);
        scroll.setBounds(150, 160, 230, 100);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        quizDescription.setEditable(true);
        middlePanel.add(scroll);

        quizTitleLbl = new JLabel("Quiz Title");
        quizTitleLbl.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        quizTitleLbl.setBounds(80, 310, 120, 28);
        quizTitleLbl.setHorizontalAlignment(JLabel.LEFT);
        middlePanel.add(quizTitleLbl);

        quizTitle = new JTextField();
        quizTitle.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        quizTitle.setBounds(200, 310, 180, 28);
        quizTitle.setHorizontalAlignment(JLabel.CENTER);
        middlePanel.add(quizTitle);

        dateLbl = new JLabel("Quiz Date");
        dateLbl.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        dateLbl.setBounds(80, 360, 140, 28);
        dateLbl.setHorizontalAlignment(JLabel.LEFT);
        middlePanel.add(dateLbl);

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
        datePicker.addActionListener(this);

        courseData = CoursesDao.getTeacherCourse(currentTeacherID);
        for (int j = 0; j < courseData.size(); j++) {
            coursesCb.addItem(courseData.get(j));
        }

        ZoneId defaultZoneId = ZoneId.systemDefault();
        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        model.setValue(date);
        datePicker.setBounds(200, 360, 120, 30);
        datePicker.addActionListener(this);

        dateValidation = new ValidLabel("  ");
        dateValidation.setBounds(200, 395, 300, 10);
        middlePanel.add(dateValidation);
        middlePanel.add(datePicker);

        //Quiz Duration (JSpinner)
        durationLbl = new JLabel("Quiz Duration");
        durationLbl.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        durationLbl.setBounds(80, 440, 140, 28);
        durationLbl.setHorizontalAlignment(JLabel.LEFT);
        middlePanel.add(durationLbl);

        SpinnerDateModel model2 = new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY);
        timeSpinner = new JSpinner(model2);
        timeSpinner.setBounds(200, 425, 70, 50);
        JComponent editor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
        timeSpinner.setEditor(editor);
        middlePanel.add(timeSpinner);

        durationValidation = new ValidLabel("   ");
        durationValidation.setBounds(200, 480, 400, 10);
        middlePanel.add(durationValidation);

        fullMarkLbl = new JLabel("Quiz Fullmark");
        fullMarkLbl.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        fullMarkLbl.setBounds(80, 525, 140, 28);
        fullMarkLbl.setHorizontalAlignment(JLabel.LEFT);
        middlePanel.add(fullMarkLbl);

        fullMark = new JTextField();
        fullMark.addFocusListener(this);
        fullMark.setBounds(200, 525, 100, 28);
        fullMark.setToolTipText("Please enter full marks");
        middlePanel.add(fullMark);

        fullValidation = new ValidLabel("  ");
        fullValidation.setBounds(200, 560, 300, 10);
        middlePanel.add(fullValidation);

        //Next Button
        nextBtn = new CircleBtn("Next");
        nextBtn.addActionListener(this);
        nextBtn.setBounds(370, 575, 120, 30);
        middlePanel.add(nextBtn);

        //Cancel Button
        cancelBtn = new CircleBtn("Cancel");
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        cancelBtn.setBounds(590, 575, 120, 30);
        middlePanel.add(cancelBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == coursesCb) {
            Object selectedMain = coursesCb.getSelectedItem();
            if (selectedMain == null) {
                System.out.println("Null Value");
            } else {
                Object selected = coursesCb.getSelectedItem();
                String courseName = selected.toString();
                courseID = CoursesDao.getCourseId(courseName);
            }
        } else if (ae.getSource() == datePicker) {
            quizDate = (Date) datePicker.getModel().getValue();
        } else if (ae.getSource() == timeSpinner) {
            quizDurationStr = timeSpinner.getModel().getValue().toString();
        } else if (ae.getSource() == nextBtn) {
            String des = quizDescription.getText();
            String Titl = quizTitle.getText();
            String FM = fullMark.getText();
            Object o = datePicker.getModel().getValue();
            Object D = timeSpinner.getModel().getValue();
            isexist = Quiz.CheckIfTitleExist(Titl, isexist);
            Validation.IfDate(o, dateValidation);
            Validation.IfDuration(D, durationValidation);
            if (des.isEmpty() || Titl.isEmpty() || FM.isEmpty() || o == null || D == null) {
                JOptionPane.showConfirmDialog(null, "Empty Fields", "Empty Fields", JOptionPane.DEFAULT_OPTION);
            } else {
                if (fullValidation.getText().isEmpty()) {
                    if (dateValidation.getText().isEmpty()) {
                        if (durationValidation.getText().isEmpty()) {
                            if (isexist == false) {
                                //System.out.println("full mark valid!");
                                addedQuiz = new Quiz();
                                addedQuiz.setTeacherID(currentTeacherID);
                                addedQuiz.setCourseID(courseID);

                                addedQuiz.setQuizDescription(des);
                                addedQuiz.setQuizTitle(Titl);
                                addedQuiz.setFullMarks(Integer.parseInt(FM));

                                Quiz.addDate(o, addedQuiz);
                                System.out.println(addedQuiz.getQuizDate());

                                Quiz.addDuration(D, addedQuiz);
                                try {
                                    new CreateQuestion(addedQuiz, coursesCb.getSelectedItem().toString(),this);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                              //  this.dispose();
                            //  this.setVisible(false);
                            } else {
                                JOptionPane.showConfirmDialog(null, "Title already exist", null, JOptionPane.DEFAULT_OPTION);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Duration must be less than 3 hours and Cannot be 00:00:00");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Date must be after date of this day");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Full marks must be between 10 and 100 \n ");
                }
            }

        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        if (e.getSource() == fullMark) {
            fullValidation.setText(" ");
        }
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (e.getSource() == fullMark) {
            Validation.IfFullmarks(fullMark, fullValidation);
        }
    }
}
