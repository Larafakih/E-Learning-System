package Model;

/**
 *
 * @author Salma
 */
public class Courses {

    private int CourseID;
    private String Name;
    private String Description;
    private String Objective;
    private String Content;
    private int TeacherID;
    private int SubjectID;

    public Courses() {
    }

    public Courses(int CourseID, String Name, String Description, String Content, int TeacherID, int SubjectID) {
        this.CourseID = CourseID;
        this.Name = Name;
        this.Description = Description;
        this.Content = Content;
        this.TeacherID = TeacherID;
        this.SubjectID = SubjectID;
    }

    public Courses(int CourseID, String Name, String Description, String Objective, String Content, int TeacherID, int SubjectID) {
        this.CourseID = CourseID;
        this.Name = Name;
        this.Description = Description;
        this.Objective = Objective;
        this.Content = Content;
        this.TeacherID = TeacherID;
        this.SubjectID = SubjectID;
    }

    public Courses(String Name) {
        this.Name = Name;
    }

    public Courses(String Name, String Description, String Objective, String Content, int TeacherID, int SubjectID) {
        this.Name = Name;
        this.Description = Description;
        this.Objective = Objective;
        this.Content = Content;
        this.TeacherID = TeacherID;
        this.SubjectID = SubjectID;
    }

    public Courses(String Name, String Description, String Objective, String Content, int TeacherID) {
        this.Name = Name;
        this.Description = Description;
        this.Objective = Objective;
        this.Content = Content;
        this.TeacherID = TeacherID;
    }

    public Courses(String Description, String Objective, String Content, int TeacherID) {
        this.Description = Description;
        this.Objective = Objective;
        this.Content = Content;
        this.TeacherID = TeacherID;
    }

    public String getObjective() {
        return Objective;
    }

    public void setObjective(String Objective) {
        this.Objective = Objective;
    }

    public Courses(String Name, String Description, String Content, int TeacherID, int SubjectID) {
        this.Name = Name;
        this.Description = Description;
        this.Content = Content;
        this.TeacherID = TeacherID;
        this.SubjectID = SubjectID;
    }

    public int getCourseID() {
        return CourseID;
    }

    public void setCourseID(int CourseID) {
        this.CourseID = CourseID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }

    public int getTeacherID() {
        return TeacherID;
    }

    public void setTeacherID(int TeacherID) {
        this.TeacherID = TeacherID;
    }

    public int getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(int SubjectID) {
        this.SubjectID = SubjectID;
    }

    @Override
    public String toString() {
        return "Courses{" + "CourseID=" + CourseID + ", Name=" + Name + ", Description=" + Description + ", Objective=" + Objective + ", Content=" + Content + ", TeacherID=" + TeacherID + ", SubjectID=" + SubjectID + '}';
    }

}
