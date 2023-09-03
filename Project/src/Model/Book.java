package Model;

/**
 *
 * @author Salma
 */
public class Book {

    private int BookId;
    private byte[] BookPdf;
    private String Name;

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Book(String Name) {
        this.Name = Name;
    }

    public Book(int BookId, byte[] BookPdf, String Name, int CourseID) {
        this.BookId = BookId;
        this.BookPdf = BookPdf;
        this.Name = Name;
        this.CourseID = CourseID;
    }
    private int CourseID;

    public Book() {
    }

    public Book(int BookId, byte[] BookPdf, int CourseID) {
        this.BookId = BookId;
        this.BookPdf = BookPdf;
        this.CourseID = CourseID;
    }

    public Book(byte[] BookPdf) {
        this.BookPdf = BookPdf;
    }

    public Book(String Name, int CourseID) {
        this.Name = Name;
        this.CourseID = CourseID;
    }

    public Book(int CourseID) {
        this.CourseID = CourseID;
    }

    public int getBookId() {
        return BookId;
    }

    public void setBookId(int BookId) {
        this.BookId = BookId;
    }

    public byte[] getBookPdf() {
        return BookPdf;
    }

    public void setBookPdf(byte[] BookPdf) {
        this.BookPdf = BookPdf;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

}
