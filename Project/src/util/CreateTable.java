package util;

import java.sql.*;

public class CreateTable {

    public static void CreateTable() {
        try {
            DBConnection c1 = new DBConnection();
            Statement stmt = DBConnection.getStatement();
            String sqlAccount = "CREATE TABLE Account ( "
                    + "  Email varchar(255) PRIMARY KEY NOT NULL UNIQUE,"
                    + "  password varchar(200) NOT NULL,"
                    + "  role varchar(200) NOT NULL,"
                    + "  lastTime date NOT NULL )";
            //stmt.executeUpdate(sqlAccount);
            //System.out.println("Created table Account in given database...");

            String sqlAdmin = "CREATE TABLE Admin ( "
                    + " AdminId int PRIMARY KEY Not Null AUTO_INCREMENT, "
                    + " FullName VARCHAR(255) Not Null,"
                    + " Email VARCHAR(255) Not Null Unique, "
                    + " Phone int(11) Not Null, "
                    + " Gender varchar(10),"
                    + " picture MEDIUMBLOB"
                    + " )";
//            stmt.executeUpdate(sqlAdmin);
//            System.out.println("Created table Admin in given database...");

            String sqlStudent = "CREATE TABLE Student ( "
                    + " StudentId int PRIMARY KEY Not Null AUTO_INCREMENT, "
                    + " FullName VARCHAR(255) Not Null, "
                    + " Email VARCHAR(255) Not Null Unique, "
                    + " PhoneNb int(11) Not Null, "
                    + " Gender varchar(10),"
                    + " picture MEDIUMBLOB"
                    + " )";
//            stmt.executeUpdate(sqlStudent);
//            System.out.println("Created table Student in given database...");

            String sqlTeacher = "CREATE TABLE Teacher ( "
                    + " TeacherId int PRIMARY KEY Not Null AUTO_INCREMENT, "
                    + " FullName VARCHAR(255) Not Null, "
                    + " Email VARCHAR(255) Not Null Unique, "
                    + " PhoneNb int(11) Not Null, "
                    + " Gender varchar(10), "
                    + " picture MEDIUMBLOB "
                    + " )";
//            stmt.executeUpdate(sqlTeacher);
//            System.out.println("Created table Teacher in given database...");

            String sqlSubjects = "create Table Subjects ("
                    + "SubjectID int PRIMARY KEY Not Null AUTO_INCREMENT, "
                    + "Name varchar(150) Unique Not Null,"
                    + "AdminId int Not Null,"
                    + "Foreign Key (AdminId) References Admin(AdminId) ON DELETE CASCADE"
                    + ")";
//            stmt.executeUpdate(sqlSubjects);
//            System.out.println("Created table Subjects in given database...");

            String sqlCourses = "create Table Courses ("
                    + "CourseID int PRIMARY KEY Not Null AUTO_INCREMENT,"
                    + "Name varchar(250) Not Null Unique,"
                    + "Description varchar(1000) Not Null,"
                    + "Objective varchar(1000) Not Null,"
                    + "Content varchar(3000) Not Null,"
                    + "TeacherID int Not Null,"
                    + "SubjectID int Not Null,"
                    + "Foreign Key (TeacherID) References Teacher(TeacherId) ON DELETE CASCADE,"
                    + "Foreign Key (SubjectID) References Subjects(SubjectId) ON DELETE CASCADE"
                    + ")";
//            stmt.executeUpdate(sqlCourses);
//            System.out.println("Created table Courses in given database...");

            String sqlEnroll = "create Table Enrollments ("
                    + "EnrollmentID int PRIMARY KEY Not Null AUTO_INCREMENT,"
                    + "Enrollment_Date Date Not Null,"
                    + "CourseID int Not Null,"
                    + "StudentId int Not Null,"
                    + "Foreign Key (CourseID) References Courses(CourseID) ON DELETE CASCADE,"
                    + "Foreign Key (StudentId) References Student(StudentId) ON DELETE CASCADE,"
                    + "UNIQUE KEY unique_Enrollment(CourseID, StudentId)"
                    + ")";
//            stmt.executeUpdate(sqlEnroll);
//            System.out.println("Created table Enrollments in given database...");

            String sqlMsg = "create Table Messages ("
                    + "MessageID int PRIMARY KEY Not Null AUTO_INCREMENT,"
                    + "message varchar(2000) Not Null,"
                    + "time Datetime Not Null,"
                    + "UserID int Not Null,"
                    + "toUser_ID int Not Null,"
                    + "Foreign Key (UserID) References Student(StudentId) ON DELETE CASCADE,"
                    + "Foreign Key (toUser_ID) References Student(StudentId) ON DELETE CASCADE"
                    + ")";
            stmt.executeUpdate(sqlMsg);
            System.out.println("Created table Messages in given database...");

            String sqlQuiz = "Create Table Quiz ("
                    + "QuizID int PRIMARY KEY Not Null AUTO_INCREMENT,"
                    + "Title varchar(250) Not Null Unique,"
                    + "Description varchar(1000) Not Null,"
                    + "TeacherID int Not Null,"
                    + "CourseID int Not Null,"
                    + "QuizDate Date Not Null,"
                    + "Duration Time Not Null,"
                    + "FullMarks int Not Null,"
                    + "Foreign Key (TeacherID) References Teacher(TeacherId) ON DELETE CASCADE,"
                    + "Foreign Key (CourseID) References Courses(CourseID) ON DELETE CASCADE"
                    + ")";

//            stmt.executeUpdate(sqlQuiz);
//            System.out.println("Created table Quiz in given database...");

            String sqlQuestion = "Create Table Questions("
                    + "QuestionID int PRIMARY KEY Not Null AUTO_INCREMENT,"
                    + "QuizID int NOT NULL,"
                    + "QuestionTitle varchar(1000) Not Null,"
                    + "Answer1 varchar(255) Not Null,"
                    + "Answer2 varchar(255) Not Null,"
                    + "Answer3 varchar(255) Not Null,"
                    + "CorrectAnswer varchar(255) Not Null,"
                    + "Qnb int Not Null,"
                    + "Foreign Key (QuizID) References Quiz(QuizID) ON DELETE CASCADE"
                    + ")";
//            stmt.executeUpdate(sqlQuestion);
//            System.out.println("Created table Questions in given database...");

            String sqlFilesCourse = "create Table Book ("
                    + "BookId int PRIMARY KEY Not Null AUTO_INCREMENT,"
                    + "Name varchar(250) Not Null Unique,"
                    + "BookPdf LONGBLOB Not Null,"
                    + "CourseID int Not Null,"
                    + "Foreign Key (CourseID) References Courses(CourseID) ON DELETE CASCADE"
                    + ")";
//            stmt.executeUpdate(sqlFilesCourse);
//            System.out.println("Created table Book in given database...");

            String sqlStudentAnswer = "Create Table Performance("
                    + "ID int PRIMARY KEY Not Null AUTO_INCREMENT,"
                    + "StudentId int NOT NULL,"
                    + "QuizID int NOT NULL,"
                    + "RightA int Not NULL,"
                    + "WrongA int Not NULL,"
                    + "Unattempted int Not NULL,"
                    + "Per double,"
                    + "CourseName varchar(200) Not Null,"
                    + "Foreign Key (StudentId) References Student(StudentId) ON DELETE CASCADE,"
                    + "Foreign Key (QuizID) References Quiz(QuizID) ON DELETE CASCADE"
                    + ")";
//            stmt.executeUpdate(sqlStudentAnswer);
//            System.out.println("Created table Performance in given database...");

            String sqlForumMessage = "Create Table ForumMessages("
                    + "ForumMessageID int PRIMARY KEY Not Null AUTO_INCREMENT,"
                    + "StudentId int Not NULL,"
                    + "MessageDate Date NOT NULL,"
                    + "Title varchar(255) Not Null unique,"
                    + "Message longtext Not Null,"
                    + "Foreign Key (StudentId) References Student(StudentId) ON DELETE CASCADE"
                    + ")";
  //          stmt.executeUpdate(sqlForumMessage);
  //          System.out.println("Created table ForumMessages in given database...");

            String sqlForumAnswers = "Create Table ForumAnswers("
                    + "ForumAnswerID int PRIMARY KEY Not Null AUTO_INCREMENT,"
                    + "ForumMessageID int Not NULL,"
                    + "StudentId int Not NULL,"
                    + "AnswerDate Date NOT NULL,"
                    + "Answer longtext Not Null,"
                    + "Foreign Key (StudentId) References Student(StudentId) ON DELETE CASCADE,"
                    + "Foreign Key (ForumMessageID) References ForumMessages(ForumMessageID) ON DELETE CASCADE"
                    + ")";
    //        stmt.executeUpdate(sqlForumAnswers);
    //        System.out.println("Created table ForumAnswers in given database...");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
