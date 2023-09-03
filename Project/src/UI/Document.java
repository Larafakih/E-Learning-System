package UI;

import javax.swing.*;
import java.text.*;
import java.util.*;
import java.time.*;
import java.time.format.*;
import Model.*;
import java.sql.Blob;
import java.sql.SQLException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import DAO.*;
import java.io.*;

/**
 *
 * @author Salma
 */
public class Document {

    static final String RoleT = "Teacher";

    public static void GeneratePdf(Teacher teacher, String subjectName, String courseName, String courseDescription, String courseObj, String courseContent, String path) {
        JLabel dateLabel, timeLabel;
        SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        dateLabel = new JLabel();
        dateLabel.setText(dFormat.format(date));
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        timeLabel = new JLabel();
        timeLabel.setText(dtf.format(now));
        if (subjectName.isEmpty() || courseName.isEmpty() || courseDescription.isEmpty() || courseContent.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please enter Course Details!!");
        }
        try {
            com.itextpdf.text.Document doc = new com.itextpdf.text.Document();

            PdfWriter.getInstance(doc, new FileOutputStream(path + "" + subjectName + "-- " + courseName + " -- " + dateLabel.getText() + ".pdf"));
            doc.open();

            Paragraph para1 = new Paragraph("         ------------------------------ Syllabus of Course:" + courseName + " ------------------------------\n\n by Teacher :" + teacher.getFullName() + " \n Contact No. : " + teacher.getPhone() + " \n Email : " + teacher.getEmail() + " \n\n");
            doc.add(para1);

            Paragraph para2 = new Paragraph("Date & Time : " + dateLabel.getText() + "  " + timeLabel.getText() + "\n\n       ------------------------------Course Details     : " + "------------------------------\n\n");
            doc.add(para2);

            Paragraph pd = new Paragraph("COURSE DESCRIPTION : \n" + courseDescription + "\n\n\n");
            doc.add(pd);

            Paragraph po = new Paragraph("COURSE OBJECTIVES : \n" + courseObj + "\n\n\n");
            doc.add(po);

            Paragraph pc = new Paragraph("COURSE CONTENT : \n" + courseContent + "\n\n\n");
            doc.add(pc);

            Paragraph para3 = new Paragraph("         ----------------------------- Thank You ! Happy Reading ! -----------------------------");
            doc.add(para3);

            doc.close();
            JOptionPane.showMessageDialog(null, "Syllabus has been Downloaded Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public static ReturnDifferentType.Im uploadpdf(int id) {
        String fname1 = null;
        FileInputStream fis1 = null;
        File f1 = null;
        JFileChooser fchoser = new JFileChooser();
        fchoser.showOpenDialog(null);
        f1 = fchoser.getSelectedFile();
        if (f1 == null) {
            new project.Error("No book added", id, RoleT);
        } else {
            fname1 = f1.getAbsolutePath();
            try {
                File image = new File(fname1);
                fis1 = new FileInputStream(image);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return new ReturnDifferentType.Im(fis1, f1, fname1);
    }

    public static void AddBooks(String Name, int courseId, int id) {
        ReturnDifferentType.Im i = Document.uploadpdf(id);
        FileInputStream fis = i.getFis();
        File f = i.getF();
        Book book = new Book(Name, courseId);
        int x = BookDao.AddBook(book, fis, f);
        if (x == 0) {
            new project.Error("File size too big", id, RoleT);
        } else {
            new JoptPane().displayGUI("Meassage", "Book is successfully Added", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void DownloadOneBook(String PathToStore, String BookName, int CourseID) {
        int BUFFER_SIZE = 4096;
        try {
            Blob blob = BookDao.SelectBookByCourseId(CourseID);
            InputStream inputStream = blob.getBinaryStream();
            OutputStream outputStream = new FileOutputStream(PathToStore + BookName);
            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            inputStream.close();
            outputStream.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void DownloadMultipleBook(String PathToStore, String BookName, int CourseID) {
        int BUFFER_SIZE = 4096;
        ArrayList<Blob> b1ob = new ArrayList<>();
        try {
            b1ob = BookDao.SelectAllBookByCourseId(CourseID);
            for (int i = 0; i < b1ob.size(); i++) {
                InputStream inputStream = b1ob.get(i).getBinaryStream();
                OutputStream outputStream = new FileOutputStream(PathToStore + BookName + "\\");
                int bytesRead = -1;
                byte[] buffer = new byte[BUFFER_SIZE];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
                inputStream.close();
                outputStream.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
