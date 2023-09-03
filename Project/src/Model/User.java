package Model;

/**
 *
 * @author Salma
 */
public abstract class User {

    public abstract String getPassword();

    public abstract void setPassword(String password);

    public abstract int getId();

    public abstract void setId(int UserId);

    public abstract String getFullName();

    public abstract void setFullName(String FullName);

    public abstract String getEmail();

    public abstract void setEmail(String Email);

    public abstract String getPhone();

    public abstract void setPhone(String Phone);

    public abstract String getGender();

    public abstract void setGender(String Gender);

    public abstract byte[] getPicture();

    public abstract void setPicture(byte[] picture);

    public abstract String getRole();
    
     public abstract String getFirstName(String name);

     public abstract String getLastname(String name);
    // public abstract void setUserRole(String UserRole);
}
