package DAO;

import UI.*;
import java.sql.*;
import javax.swing.JOptionPane;
import util.*;
import Model.*;
import project.*;
import java.util.*;

/**
 *
 * @author Salma
 */
public class CoursesDao {

    public static int DeleteCourseByname(String name) {
        int x = 0;
        try {
            DBConnection c1 = new DBConnection();
            String q = "Delete From Courses Where Name ='" + name + "'";
            x = DBConnection.getStatement().executeUpdate(q);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public static int getCourseIdbycourseName(String name) {
        int id = 0;
        DBConnection c = new DBConnection();
        try {
            String q2 = "Select CourseID  from Courses where Name='" + name + "'";
            ResultSet rs2 = DBConnection.getStatement().executeQuery(q2);
            while (rs2.next()) {
                id = rs2.getInt("CourseID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;

    }

    public static String[][] SelectTeacherOfCourse(String courseName) {
        String teacherData[][] = new String[1][4];
        try {
            DBConnection c1 = new DBConnection();
            String q2 = "Select T.FullName, T.Email"
                    + " From Teacher As T"
                    + " Inner Join Courses As C ON C.TeacherID = T.TeacherID"
                    + " Where C.CourseID = (select CourseID From Courses Where Name = '" + courseName + "')";
            ResultSet rs2 = DBConnection.getStatement().executeQuery(q2);
            rs2.next();
            //String teacherData[][] = new String[1][4];
            //teacherData[0][0] = rs2.getString(1);
            String fnameTeacher = rs2.getString(1);
            teacherData[0][0] = fnameTeacher;
            teacherData[0][1] = rs2.getString(2);
            teacherData[0][2] = "Teacher";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teacherData;
    }

    public static String[] getMyCourses(int ID) {
        String data[] = null;
        try {
            DBConnection c1 = new DBConnection();
            String q1 = "Select CourseID, Name From Courses Where TeacherID = '" + ID + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            data = new String[rowCount];
            int row = 0;
            rs.beforeFirst();
            while (rs.next()) {
                data[row] = rs.getString("Name");
                row++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static Courses getCoursebyNameandTeacherId(String name, int ID) {
        Courses co = new Courses();
        try {
            DBConnection c1 = new DBConnection();
            String q1 = "Select * From Courses As C"
                    + " Where C.Name = '" + name + "' And C.TeacherID = '" + ID + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            while (rs.next()) {
                co = new Courses(rs.getString("Description"), rs.getString("Objective"), rs.getString("Content"), rs.getInt("TeacherID"));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return co;
    }

    public static Courses getCoursebyName(String name) {
        Courses co = new Courses();
        try {
            DBConnection c1 = new DBConnection();
            String q1 = "Select * From Courses As C"
                    + " Where C.Name = '" + name + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            while (rs.next()) {
                co = new Courses(rs.getInt("CourseID"), rs.getString("Name"), rs.getString("Description"), rs.getString("Objective"), rs.getString("Content"), rs.getInt("TeacherID"), rs.getInt("SubjectID"));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return co;
    }

    public static int updateCourse(Courses c) {
        int x = 0;
        try {
            DBConnection c1 = new DBConnection();
            PreparedStatement ps = DBConnection.getConn().prepareStatement("Update Courses Set Description = ?,Content = ?,Objective=? " + "Where Name = ?");
            ps.setString(1, c.getDescription());
            ps.setString(2, c.getContent());
            ps.setString(3, c.getObjective());
            ps.setString(4, c.getName());
//            String q1 = "Update Courses Set Description = '" + c.getDescription() + "', Content = '" + c.getContent() + "', Objective='" + c.getObjective() + "'"
//                    + " ;
            x = ps.executeUpdate();
            //x = DBConnection.getStatement().executeUpdate(q1);
        } catch (Exception exc) {
            //JOptionPane.showMessageDialog(null, "This Course is Already being Offered By other Teacher!");
            exc.printStackTrace();
        }
        return x;
    }

    public static int AddCourse(Courses c) {
        int x = 0;
        DBConnection c1 = new DBConnection();
        try {
            PreparedStatement ps = DBConnection.getConn().prepareStatement("INSERT INTO Courses (Name, Description, Content, TeacherID, SubjectID,Objective)" + "values(?,?,?,?,?,?)");
            ps.setString(1, c.getName());
            ps.setString(2, c.getDescription());
            ps.setString(3, c.getContent());
            ps.setInt(4, c.getTeacherID());
            ps.setInt(5, c.getSubjectID());
            ps.setString(6, c.getObjective());

//            String q = "INSERT INTO Courses (Name, Description, Content, TeacherID, SubjectID,Objective) "
//                    + "Values ('" + c.getName() + "', '" + c.getDescription() + "', '" + c.getContent() + "', '"
//                    + c.getTeacherID() + "', '" + c.getSubjectID() + "', '" + c.getObjective() + "')";
            x = ps.executeUpdate();
//x = DBConnection.getStatement().executeUpdate(q);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error in added course!");
            e.printStackTrace();
        }
        return x;
    }

    public static Courses getCourseName(int CourseID) {
        Courses co = new Courses();
        DBConnection c1 = new DBConnection();
        try {
            String q = "select * from Courses Where CourseID = '" + CourseID + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            while (rs.next()) {
                co = new Courses(rs.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return co;
    }

    public static String[] getCourseNameForT(int id) {
        String[] CourseData = null;
        DBConnection c1 = new DBConnection();
        try {

            String q = "select * from Courses where TeacherID='" + id + "'";

            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            CourseData = new String[rowCount];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                CourseData[i] = rs.getString("Name");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CourseData;
    }

    public static String[] getCourseName() {
        String[] CourseData = null;
        try {
            DBConnection c1 = new DBConnection();

            String q = "select * from Courses";
            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            CourseData = new String[rowCount];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                CourseData[i] = rs.getString("Name");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CourseData;
    }

    public static ArrayList<Courses> getArrayofTeacherCourses(int ID) {
        ArrayList<Courses> arrayco = new ArrayList<>();
        Courses cou = new Courses();
        try {
            DBConnection c1 = new DBConnection();
            String q1 = "select * from Courses where TeacherID='" + ID + "'";
            System.out.println("" + ID);
            Statement s = DBConnection.getConn().createStatement();
            ResultSet rs = s.executeQuery(q1);
            System.out.println("rs");
            rs.next();
            System.out.println("" + rs.getString(1));
            while (rs.next()) {
                cou = new Courses(rs.getInt("CourseID"), rs.getString("Name"), rs.getString("Description"), rs.getString("Objective"), rs.getString("Content"), rs.getInt("TeacherID"), rs.getInt("SubjectID"));
                arrayco.add(cou);
                System.out.println("" + cou.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayco;
    }

   
    
    public static ArrayList<String> getTeacherCourse(int ID) {
        ArrayList<String> a = new ArrayList<>();
        try {
            DBConnection c1 = new DBConnection();

            String q1 = "Select * From Courses Where TeacherID = '" + ID + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            while (rs.next()) {
                a.add(rs.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public static String[] getSetCourses(int subjectID) {
        String[] coursesData = null;
        try {
            DBConnection c1 = new DBConnection();
            String q = "select * from Courses Where SubjectID = '" + subjectID + "'";

            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            coursesData = new String[rowCount];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                coursesData[i] = rs.getString("Name");
                i++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (coursesData == null) {
            JOptionPane.showMessageDialog(null, "Cannot get Courses!");
        }
        return coursesData;
    }

//    public static Courses getAllByName(String courseName) {
//        Courses c = new Courses();
//        try {
//            DBConnection c1 = new DBConnection();
//            String q1 = "Select * From Courses Where Name = '" + courseName + "'";
//            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
//            while (rs.next()) {
//                c = new Courses(rs.getString("Description"), rs.getString("Objective"), rs.getString("Content"), rs.getInt("TeacherID"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return c;
//    }
    public static String getCourseContent(String courseName) {
        String course_Content = null;
        try {
            DBConnection c1 = new DBConnection();
            String q1 = "Select Content From Courses Where Name = '" + courseName + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            rs.next();

            course_Content = rs.getString("Content");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return course_Content;
    }

    public static String getDes(String courseName) {
        String course_Description = null;
        try {
            DBConnection c1 = new DBConnection();
            String q1 = "Select Description From Courses Where Name = '" + courseName + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            rs.next();
            course_Description = rs.getString("Description");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return course_Description;
    }

    public static String getObjective(String courseName) {
        String course_obj = null;
        try {
            DBConnection c1 = new DBConnection();
            String q1 = "Select Objective From Courses Where Name = '" + courseName + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            rs.next();
            course_obj = rs.getString("Objective");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return course_obj;
    }

    public static Object[][] ViewParticipantsInCourse(String courseName, int ID) {
        Object[][] Studentdata = null;
        try {
            DBConnection c1 = new DBConnection();
            String q = "Select S.StudentId, S.FullName, S.Email"
                    + " From Student As S"
                    + " Inner Join Enrollments As E ON E.StudentId = S.StudentId"
                    + " Where E.CourseID = (select CourseID From Courses Where Name = '" + courseName + "')"
                    + " And S.StudentId <> '" + ID + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            int StdrowCount = 0;
            while (rs.next()) {
                StdrowCount++;
            }
            Studentdata = new Object[StdrowCount][5];
            rs.beforeFirst();
            int rowCount = 0;
            while (rs.next()) {
                //Studentdata[rowCount][0] = rs.getString(1);
                String fname = rs.getString("FullName");
                Studentdata[rowCount][0] = fname;
                Studentdata[rowCount][1] = rs.getString("Email");
                Studentdata[rowCount][2] = rs.getInt("StudentId");
                rowCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Studentdata;
    }

    public static ArrayList<Student> sameCourseStudents(int studentId) {
        ArrayList<Student> arrayStudents = new ArrayList<>();
        //ArrayList<Courses> arrayc=getCourseOfStudent(studentId);
        try {
            DBConnection c1 = new DBConnection();
            String q = "Select *"
                    + " From Student As S"
                    + " Inner Join Enrollments As E ON E.StudentId = S.StudentId"
                    + " Where E.CourseID = (Select C.CourseID From Courses As C"
                    + " Inner Join Enrollments As E1 ON E1.CourseID = C.CourseID"
                    + " Where E1.StudentId = '" + studentId + "')"
                    + " And S.StudentId <> '" + studentId + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            while (rs.next()) {
                Student st = new Student(rs.getInt("StudentId"), rs.getString("FullName"), rs.getString("Email"), rs.getString("PhoneNb"), rs.getString("Gender"));
                arrayStudents.add(st);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayStudents;
    }

    public static ArrayList<Student> SameCourse(int studentId) {
        ArrayList<Student> arrayStudents = new ArrayList<>();
        boolean T = true;
        Student st;
        try {
            DBConnection c1 = new DBConnection();
            String q = "Select S.StudentId, S.FullName, S.Email"
                    + " From Student As S"
                    + " Inner Join Enrollments As E ON E.StudentId = S.StudentId "
                    + "Where E.CourseID in (Select C.CourseID From Courses As C"
                    + " Inner Join Enrollments As E ON E.CourseID = C.CourseID"
                    + " Where E.StudentId = '" + studentId + "')"
                    + "And S.StudentId <> '" + studentId + "'";

            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            while (rs.next()) {
                st = new Student();
                st.setId(rs.getInt("StudentId"));
                st.setFullName(rs.getString("FullName"));
                st.setEmail(rs.getString("Email"));
                System.out.println("st: " + st.toString());
                for (Student ss
                        : arrayStudents) {
                    if (ss.getFullName().equals(st.getFullName())) {
                        T = false;
                    }
                }
                if (T == true) {
                    arrayStudents.add(st);
                }

//                
            }
            System.out.println("array of Students: " + arrayStudents.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayStudents;
    }

    public static String[] getEnrolledCourses(int ID) {
        String[] coursesData = null;
        try {
            DBConnection c1 = new DBConnection();
            String q = "Select C.Name From Courses As C"
                    + " Inner Join Enrollments As E ON E.CourseID = C.CourseID"
                    + " Where E.StudentId = '" + ID + "'";

            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            int rowCount = 0;
            while (rs.next()) {
                rowCount++;
            }
            coursesData = new String[rowCount];
            rs.beforeFirst();
            int i = 0;
            while (rs.next()) {
                coursesData[i] = rs.getString("Name");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coursesData;
    }

    public static int getCourseId(String courseName) {
        int courseID = 0;
        try {
            DBConnection c1 = new DBConnection();
            String q1 = "Select courseID From Courses Where Name = '" + courseName + "'";
            ResultSet rs = DBConnection.getStatement().executeQuery(q1);
            rs.next();
            courseID = rs.getInt("courseID");

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (courseID == 0) {
            JOptionPane.showMessageDialog(null, "Error in get Course!");
        }
        return courseID;
    }

    public static int getTeacheridbyCourseid(int id) {
        int tid = 0;
        try {
            DBConnection c1 = new DBConnection();
            String q = "Select * from Courses";
            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            while (rs.next()) {
                tid = rs.getInt("TeacherID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tid;
    }

    public static ArrayList<Courses> getArrayofCoursesFromSubjectName(String subjectName) {
        ArrayList<Courses> arrayco = new ArrayList<>();
        Courses cou;
        DBConnection c1 = new DBConnection();
        try {
            String q = "select * from Courses as C"
                    + " Where C.SubjectID = (select SubjectID From Subjects Where Name = '" + subjectName + "')";
            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            while (rs.next()) {
                cou = new Courses(rs.getInt("CourseID"), rs.getString("Name"), rs.getString("Description"), rs.getString("Objective"), rs.getString("Content"), rs.getInt("TeacherID"), rs.getInt("SubjectID"));
                arrayco.add(cou);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            c1.Close();
        }
        return arrayco;
    }

    public static ArrayList<Courses> getArrayofCourses() {
        ArrayList<Courses> arrayco = new ArrayList<>();
        Courses cou;
        DBConnection c1 = new DBConnection();
        try {
            String q = "select * from Courses ";
            ResultSet rs = DBConnection.getStatement().executeQuery(q);
            while (rs.next()) {
                cou = new Courses(rs.getInt("CourseID"), rs.getString("Name"), rs.getString("Description"), rs.getString("Objective"), rs.getString("Content"), rs.getInt("TeacherID"), rs.getInt("SubjectID"));
                arrayco.add(cou);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            c1.Close();
        }
        return arrayco;
    }
}
