package Model;

/**
 *
 * @author Salma
 */
public class Subject {

    int SubjectID;
    String Name;
    int AdminId;

    public Subject(int SubjectID, String Name, int AdminId) {
        this.SubjectID = SubjectID;
        this.Name = Name;
        this.AdminId = AdminId;
    }

    public Subject(String Name, int AdminId) {
        this.Name = Name;
        this.AdminId = AdminId;
    }

    public int getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(int SubjectID) {
        this.SubjectID = SubjectID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getAdminId() {
        return AdminId;
    }

    public void setAdminId(int AdminId) {
        this.AdminId = AdminId;
    }

}
