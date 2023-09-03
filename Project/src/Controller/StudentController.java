package Controller;

import UI.*;
import DAO.*;
import Model.*;
import javax.swing.*;
import Student.*;
import java.awt.event.*;
import java.util.*;

public class StudentController {

    private final static String RoleS = "Student";

    public static void EnrollInCourse(String courseNameStr, int currentStudentID, JFrame f) {
        java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
        int course_ID = CoursesDao.getCourseId(courseNameStr);
        boolean isExisted = EnrollmentDao.selectEnrollement(course_ID, currentStudentID);
        if (isExisted == true) {
            JOptionPane.showMessageDialog(null, "You are Already Enrolled in this Course!");
        } else {
            Enrollment Enr = new Enrollment(sqlDate, course_ID, currentStudentID);
            int x = EnrollmentDao.InsertIntoEnrollment(Enr);
            if (x == 0) {
                new project.Error("Some Error Occured", currentStudentID, RoleS);
            } else {
                JOptionPane.showMessageDialog(null, "Course Enrollement Successfull!");
                f.dispose();
                new StudentPage(currentStudentID);
            }
        }
    }

    public static void getCourse(String courseName, JTextArea courseDescription, JTextArea courseObjective) {
        Courses course = CoursesDao.getCoursebyName(courseName);
        String course_Description = course.getDescription();//CoursesDao.getDes(courseName);
        courseDescription.setText(course_Description);
        String course_Objective = course.getObjective();
        courseObjective.setText(course_Objective);
    }

    public static void DownloadBook(int CourseID, JPanel middlePanel) {
        JButton DownloadBtn = new CircleBtn("Download Book");
        DownloadBtn.setBounds(550, 600, 140, 28);
        int countBook = BookDao.CountBookByCourseID(CourseID);
        if (countBook == 0) {
            JOptionPane.showMessageDialog(null, "There is no Book for this Course..");
        } else if (countBook >= 1) {
            middlePanel.add(DownloadBtn);
        } else if (countBook > 1) {
        }
        DownloadBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String path = StudyCourse.getpath();
                ArrayList<String> bookname;
                bookname = BookDao.getBooksNameByCourseId(CourseID);
                if (countBook == 1) {
                    Document.DownloadOneBook(path, bookname.get(0) + ".pdf", CourseID);
                    JOptionPane.showMessageDialog(null, "The book is downloaded...\n in " + path);
                } else if (countBook > 1) {
                    int i;
                    for (i = 0; i < countBook; i++) {
                        Document.DownloadMultipleBook(path, bookname.get(i)+".pdf", CourseID);
                    }
                    JOptionPane.showMessageDialog(null, "All Books are downloaded.. \n in " + path);
                }
            }
        });
    }

    public static void DoQuiz(String coursename, JPanel middlePanel, int currentStudentID, JFrame f) {
        int CourseID = CoursesDao.getCourseId(coursename);
        JButton Quiz = new CircleBtn("Do Quiz");
        Quiz.setBounds(750, 600, 140, 28);
        Quiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                ViewQuiz v = new ViewQuiz(CourseID, currentStudentID, coursename);
                v.setVisible(true);
                f.dispose();
            }
        });
        int count = QuizDao.CountQuizByCourseId(CourseID);
        if (count != 0) {
            middlePanel.add(Quiz);
        } else {
            JOptionPane.showMessageDialog(null, "There is no quiz for this Course..");
        }
    }

    public static void withdrawCourse(String course_Name, int currentStudentID, JFrame f) {
        int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to Withdraw this Course?", "Select an Option...",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        if (input == 0) {
            int x = EnrollmentDao.DeleteEnrollment(course_Name, currentStudentID);
            if (x == 0) {
                new project.Error("Got some error in withdraw course", currentStudentID, RoleS);
            } else {
                JOptionPane.showMessageDialog(null, "Course Withdrawn Successfully");
                f.dispose();
                new StudentPage(currentStudentID);
            }

        }
    }
}
