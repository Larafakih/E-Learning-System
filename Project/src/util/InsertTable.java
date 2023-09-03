package util;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class InsertTable {

    static final String DB_URL = "jdbc:mysql:///ELearningSystem";
    static final String USER = "root";
    static final String PASS = "1234mysql";
    //LOAD_FILE('C://Users//Dear//Documents//NetBeansProjects//Project//profile//M1.png')
    static Connection conn;
    DBConnection b = new DBConnection();
    static Statement stmt;

    public static void InsertStudent() {
        try {
            conn = DBConnection.getConn();
            stmt = conn.createStatement();
            String sql = "INSERT INTO Student (FullName,Email,PhoneNb,Gender,picture)VALUES ('Anna Dora', 'Anna@gmail.com', 70245678, 'Female',LOAD_FILE('C://Users//Dear//Documents//NetBeansProjects//Project//profile//F1.png'))";
//            stmt.executeUpdate(sql);
            String sq2 = "INSERT INTO Account VALUES ('Anna@gmail.com','Anna4@1#0', 's','2022-02-12')";
//            stmt.executeUpdate(sq2);
//-----------------------------------------------------------
            String sq3 = "INSERT INTO Student (FullName,Email,PhoneNb,Gender,picture)VALUES ('Ali Elreda', 'AliR@gmail.com', 71963245, 'Male',LOAD_FILE('C://Users//Dear//Documents//NetBeansProjects//Project//profile//M1.png'))";
//            stmt.executeUpdate(sq3);
            String sq4 = "INSERT INTO Account VALUES ('AliR@gmail.com','Ali71@961#', 's','2022-02-12')";
//            stmt.executeUpdate(sq4);
//-----------------------------------------------------------
        } catch (Exception e) {
        }
    }

    public static void InsertTeacher() {
        try {
            conn = DBConnection.getConn();
            stmt = conn.createStatement();

            String sq5 = "INSERT INTO Teacher(FullName,Email,PhoneNb,Gender,picture) VALUES ('Ihab Mq', 'Ihab@gmail.com', 71963024, 'Male',LOAD_FILE('C://Users//Dear//Documents//NetBeansProjects//Project//profile//M2.png'))";
//            stmt.executeUpdate(sq5);
            String sq6 = "INSERT INTO Account VALUES ('Ihab@gmail.com','IH@3b1m#', 't','2022-02-12')";
//            stmt.executeUpdate(sq6);
//-----------------------------------------------------------
        } catch (Exception e) {
        }
    }

    public static void InsertAdmin() {
        try {
            conn = DBConnection.getConn();
            stmt = conn.createStatement();

            String sq7 = "INSERT INTO Admin(FullName,Email,Phone,Gender,picture) VALUES ('Mohamad Admin', 'MoAdmin@gmail.com', 70987654, 'Male',LOAD_FILE('C://Users//Dear//Documents//NetBeansProjects//Project//profile//M4.png'))";
//            stmt.executeUpdate(sq7);
            String sq8 = "INSERT INTO Account VALUES ('MoAdmin@gmail.com','ADMIn@#12', 'a','2022-02-12')";
//            stmt.executeUpdate(sq8);
//-----------------------------------------------------------
        } catch (Exception e) {
        }
    }

    public static void InsertSubject() {
        try {
            conn = DBConnection.getConn();
            stmt = conn.createStatement();

            String sq9 = "INSERT INTO elearningsystem.subjects (`Name`, `AdminId`) \n"
                    + "	VALUES ('Life Sciences', 4)";
//            stmt.executeUpdate(sq9);
//-----------------------------------------------------------            
            String sq10 = "INSERT INTO elearningsystem.subjects (`Name`, `AdminId`) \n"
                    + "	VALUES ('Mathematics', 4)";
//            stmt.executeUpdate(sq10);
//-----------------------------------------------------------
            String sq11 = "INSERT INTO elearningsystem.subjects (`Name`, `AdminId`) \n"
                    + "	VALUES ('Computer Science', 3)";
//            stmt.executeUpdate(sq11);
//-----------------------------------------------------------
            String sq12 = "INSERT INTO elearningsystem.subjects (`Name`, `AdminId`) \n"
                    + "	VALUES ('Biology', 3)";
//            stmt.executeUpdate(sq12);
//-----------------------------------------------------------
            String sq13 = "INSERT INTO elearningsystem.subjects (`Name`, `AdminId`) \n"
                    + "	VALUES ('Chemistry', 3)";
//            stmt.executeUpdate(sq13);
//-----------------------------------------------------------
            String sq14 = "INSERT INTO elearningsystem.subjects (`Name`, `AdminId`) \n"
                    + "	VALUES ('Biomedical Sciences', 3)";
//            stmt.executeUpdate(sq14);
//-----------------------------------------------------------
            String sq15 = "INSERT INTO elearningsystem.subjects (`Name`, `AdminId`) "
                    + "	VALUES ('Architecture', 4)";
//            stmt.executeUpdate(sq15);
//-----------------------------------------------------------            
            String sq16 = "INSERT INTO elearningsystem.subjects (`Name`, `AdminId`) "
                    + "	VALUES ('Accounting', 4)";
//            stmt.executeUpdate(sq16);
//----------------------------------------------------------- 
            String sq17 = "INSERT INTO elearningsystem.subjects (`Name`, `AdminId`) "
                    + "	VALUES ('Quality Management', 4)";
//            stmt.executeUpdate(sq17);
//----------------------------------------------------------- 
            String sq18 = "INSERT INTO elearningsystem.subjects (`Name`, `AdminId`) "
                    + "	VALUES ('Human Resource Management', 4)";
//            stmt.executeUpdate(sq18);
//----------------------------------------------------------- 
            String sq19 = "INSERT INTO elearningsystem.subjects (`Name`, `AdminId`) "
                    + "	VALUES ('Marketing', 4)";
//            stmt.executeUpdate(sq19);
//----------------------------------------------------------- 
            String sq20 = "INSERT INTO elearningsystem.subjects (`Name`, `AdminId`) "
                    + "	VALUES ('Multimedia', 4)";
//            stmt.executeUpdate(sq20);
//----------------------------------------------------------- 
            String sq21 = "INSERT INTO elearningsystem.subjects (`Name`, `AdminId`) "
                    + "	VALUES ('Industrial Design', 3)";
//            stmt.executeUpdate(sq21);
//----------------------------------------------------------- 
            String sq22 = "INSERT INTO elearningsystem.subjects (`Name`, `AdminId`) "
                    + "	VALUES ('Interior Design', 3)";
//            stmt.executeUpdate(sq22);
//----------------------------------------------------------- 
            String sq23 = "INSERT INTO elearningsystem.subjects (`Name`, `AdminId`) "
                    + "	VALUES ('Civil Engineering', 4)";
//            stmt.executeUpdate(sq23);
//----------------------------------------------------------- 
            String sq24 = "INSERT INTO elearningsystem.subjects (`Name`, `AdminId`) "
                    + "	VALUES ('Health Studies', 3)";
//            stmt.executeUpdate(sq24);
//----------------------------------------------------------- 
            String sq25 = "INSERT INTO elearningsystem.subjects (`Name`, `AdminId`) "
                    + "	VALUES ('Psychology', 4)";
//            stmt.executeUpdate(sq25);
//----------------------------------------------------------- 
            String sq26 = "INSERT INTO elearningsystem.subjects (`Name`, `AdminId`) "
                    + "	VALUES ('Criminal Law', 4)";
//            stmt.executeUpdate(sq26);
//----------------------------------------------------------- 
            String sq27 = "INSERT INTO elearningsystem.subjects (`Name`, `AdminId`) "
                    + "	VALUES ('Film & Television', 3)";
//            stmt.executeUpdate(sq27);
//----------------------------------------------------------- 
            String sq28 = "INSERT INTO elearningsystem.subjects (`Name`, `AdminId`) "
                    + "	VALUES ('Social Work', 3)";
//            stmt.executeUpdate(sq28);
//----------------------------------------------------------- 
            String sq29 = "INSERT INTO elearningsystem.subjects (`Name`, `AdminId`) "
                    + "	VALUES ('Photography', 4)";
//            stmt.executeUpdate(sq29);
//----------------------------------------------------------- 
        } catch (Exception e) {
        }
    }

    public static void InsertCourses() {
        try {
            conn = DBConnection.getConn();
            stmt = DBConnection.getConn().createStatement();

            String sq30 = "INSERT INTO elearningsystem.courses (`Name`, `Description`, `Objective`, `Content`, `TeacherID`, `SubjectID`) \n"
                    + "	VALUES ('UX Design', 'Prepare for a career in the high-growth field of UX design, no experience or degree required. "
                    + "There are currently 113,700 U.S. job openings in UX design with an average entry-level salary of $58,600.¹"
                    + " User experience (UX) designers focus on the interaction that users have with products, "
                    + "like websites, apps, and physical objects. They make those everyday interactions useful, enjoyable, and accessible.',"
                    + " 'You’ll learn how to complete the design process from beginning to end, including: Empathizing with users;"
                    + " Defining user pain points; Coming up with ideas for design solutions; "
                    + "Creating wireframes, mockups, and prototypes; Testing designs through usability studies; "
                    + "Iterating on designs based on feedback.', "
                    + "' Foundations of User Experience (UX) Design, "
                    + " Start the UX Design Process: Empathize, Define, and Ideate, "
                    + "Build Wireframes and Low-Fidelity Prototypes, "
                    + "Conduct UX Research and Test Early Concepts, "
                    + "Create High-Fidelity Designs and Prototypes in Figma ,"
                    + "Responsive Web Design in Adobe XD,Design a User Experience for Social Good & Prepare for Jobs', 5, 24)\n"
                    + "";
//            stmt.executeUpdate(sq30);
//-----------------------------------------------------------
            String sq31 = "INSERT INTO elearningsystem.courses (`Name`, `Description`, `Objective`, `Content`, `TeacherID`, `SubjectID`) \n"
                    + "	VALUES ('Java Programming and Software Engineering Fundamentals', 'Take your first step towards a career in software development with this introduction "
                    + "to Java—one of the most in-demand programming languages and the foundation of the Android operating system. "
                    + "Designed for beginners, this Specialization will teach you core programming concepts and equip you to write programs to solve complex problems. "
                    + "In addition, you will gain the foundational skills a software engineer needs to solve real-world problems, "
                    + "from designing algorithms to testing and debugging your programs.',"
                    + " 'this Specialization will help you create a portfolio of work to demonstrate your new programming skills.', "
                    + "'Programming Foundations with JavaScript, HTML and CSS,  "
                    + "Java Programming: Solving Problems with Software, "
                    + " Java Programming: Arrays, Lists, and Structured Data, "
                    + " Java Programming: Principles of Software Design,  "
                    + "Java Programming: Build a Recommendation System', 5, 24)";
            //stmt.executeUpdate(sq31);
//-----------------------------------------------------------
            String sq32 = "INSERT INTO elearningsystem.courses (`Name`, `Description`, `Objective`, `Content`, `TeacherID`, `SubjectID`) \n"
                    + "	VALUES ('Introduction to International Criminal Law', 'From the Nuremberg trial to the case against Saddam Hussein, from the prosecution of Al-Qaeda terrorists to the trial of Somali pirates – no area of law is as important to world peace and security as international criminal law.  Taught by one of the world’s leading experts in the field, this course will educate students about the fundamentals of international criminal law and policy.  We will explore the contours of international crimes such as genocide, war crimes, terrorism, and piracy.  We will examine unique modes of international criminal liability and specialized defenses.  And we will delve into the challenges of obtaining custody of the accused and maintaining control of the courtroom.', 'null', 'The order of class sessions will be:\n"
                    + "\n"
                    + "(1)     History: From Nuremberg to The Hague\n"
                    + "\n"
                    + "(2)     International Crimes Part 1: War Crimes, Genocide, Crimes against Humanity, and Torture\n"
                    + "\n"
                    + "(3)     International Crimes Part 2: Terrorism and Piracy\n"
                    + "\n"
                    + "(4)     Special modes of liability: command responsibility, co-perpetration, and incitement\n"
                    + "\n"
                    + "(5)      Special defenses: insanity, obedience to orders, duress, and head of state immunity\n"
                    + "\n"
                    + "(6)      Gaining custody of the accused: extradition, luring, abduction, and targeted killing\n"
                    + "\n"
                    + "(7)      Pre-Trial Issues: plea bargaining, self-representation, and exclusion of torture evidence\n"
                    + "\n"
                    + "(8)      Maintaining control of the courtroom   ', 5, 47);";
            stmt.executeUpdate(sq32);
            System.out.println("inserted");
//-----------------------------------------------------------
        } catch (Exception e) {
        }
    }

    public static void InsertQuiz() {
        try {
            conn = DBConnection.getConn();
            stmt = conn.createStatement();

            String sq33 = "INSERT INTO elearningsystem.quiz (`Title`, `Description`, `TeacherID`, `CourseID`, `QuizDate`, `Duration`, `FullMarks`) \n"
                    + "	VALUES ('User-Experience Quiz', 'Test your usability knowledge by taking our quiz. ', 5, 11, '2022-03-24', '00:10:00', 20)\n"
                    + "";
            //stmt.executeUpdate(sq33);
//-----------------------------------------------------------
        } catch (Exception e) {
        }
    }

    public static void InsertQuestions() {
        try {
            conn = DBConnection.getConn();
            stmt = conn.createStatement();

            String sq34 = "INSERT INTO elearningsystem.questions (`QuizID`, `QuestionTitle`, `Answer1`, `Answer2`, `Answer3`, `CorrectAnswer`, `Qnb`) \n"
                    + "	VALUES (57, 'Which of the following is NOT an example of a context research method?',"
                    + " 'Field study ', 'Whatever elements they are interested in first ', 'Usability test ', 'Answer2', 1)";
            stmt.executeUpdate(sq34);
//-----------------------------------------------------------
            String sq35 = "INSERT INTO elearningsystem.questions (`QuizID`, `QuestionTitle`, `Answer1`, `Answer2`, `Answer3`, `CorrectAnswer`, `Qnb`) \n"
                    + "	VALUES (57, 'Visual hierarchy refers to the visual organization of page elements so that the user can easily perceive: '"
                    + ", 'The breadth of the content and controls available on the page ', "
                    + "'Whatever elements they are interested in first ',"
                    + " 'Which elements are related and their relative importance ', 'Answer3', 2)";
            stmt.executeUpdate(sq35);
//-----------------------------------------------------------
            String sq36 = "INSERT INTO elearningsystem.questions (`QuizID`, `QuestionTitle`, `Answer1`, `Answer2`, `Answer3`, `CorrectAnswer`, `Qnb`) \n"
                    + "	VALUES (57, 'Which of the following is an example of a good How might we question? ', "
                    + "'How might we implement the tax-payment flow? ', 'How might we ensure that people pay taxes on time? ',"
                    + " 'How might we improve the user experience of the tax-payment flow? ', 'Answer2', 3)";
            stmt.executeUpdate(sq36);
//-----------------------------------------------------------
        } catch (Exception e) {
        }
    }
    public static void main(String[] args) {
        InsertCourses();
    }
}
