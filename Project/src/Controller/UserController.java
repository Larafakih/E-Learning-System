package Controller;

import UI.*;
import DAO.*;
import Model.*;
import java.io.*;
import javax.swing.*;
import Admin.*;
import Student.*;
import Teacher.*;
import java.awt.image.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import project.*;

/**
 *
 * @author Salma
 */
public class UserController {

    private static final String RoleA = "Admin", RoleS = "Student", RoleT = "Teacher";
    private static Boolean isExisted = false;

    public static User getAccountDetails(String userRole, int ID) {
        User user = null;
        String email;
        if (userRole == RoleA) {
            user = new Admin();
            Admin ad = AdminDao.SelectAdminById(ID);
            email = ad.getEmail();
            user.setFullName(ad.getFullName());
            user.setEmail(email);
            user.setGender(ad.getGender());
            user.setPhone(ad.getPhone());
            user.setPassword(ad.getPassword());
        } else if (userRole == RoleS) {
            user = new Student();
            Student st = StudentDao.SelectStudentById(ID);
            email = st.getEmail();
            user.setFullName(st.getFullName());
            user.setEmail(email);
            user.setGender(st.getGender());
            user.setPhone(st.getPhone());
            user.setPassword(st.getPassword());
        } else if (userRole == RoleT) {
            user = new Teacher();
            Teacher te = TeacherDao.SelectTeacherById(ID);
            email = te.getEmail();
            user.setFullName(te.getFullName());
            user.setEmail(email);
            user.setGender(te.getGender());
            user.setPhone(te.getPhone());
            user.setPassword(te.getPassword());
        } else {
            new project.Error("Error in get account details", ID, userRole);
        }
        return user;
    }

    public static void ChangeEmail(String Role, int ID, String newemail) {
        String email;
        int x = 0, x1 = 0;
        if (Role == RoleA) {
            Admin ad = AdminDao.SelectAdminById(ID);
            email = ad.getEmail();
            x = AdminDao.UpdateEmail(newemail, ID);
            x1 = AccountDao.UpdateEmail(newemail, email);

        } else if (Role == RoleS) {
            Student St = StudentDao.SelectStudentById(ID);
            email = St.getEmail();
            x = StudentDao.UpdateEmail(newemail, ID);
            x1 = AccountDao.UpdateEmail(newemail, email);

        } else if (Role == RoleT) {
            Teacher te = TeacherDao.SelectTeacherById(ID);
            email = te.getEmail();
            x = TeacherDao.UpdateEmail(newemail, ID);
            x1 = AccountDao.UpdateEmail(newemail, email);

        } else {
            new project.Error("Error", ID, Role);
        }
        if (x == 0 && x1 == 0) {
            new project.Error("Error in change email", ID, Role);
        } else {
            JOptionPane.showMessageDialog(null, "Your Email Updated Successfully");
        }
    }

    public static void ChangeName(String Role, int ID, String newName, JFrame frame) {
        int x;
        if (Role == RoleA) {
            x = AdminDao.UpdateFullName(newName, ID);
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Got some error");
            } else {
                JOptionPane.showMessageDialog(null, "Your Name Updated Successfully");
                frame.dispose();
                AdminPage a = new AdminPage(ID);
            }
        } else if (Role == RoleS) {
            x = StudentDao.UpdateFullName(newName, ID);
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Got some error");
            } else {
                JOptionPane.showMessageDialog(null, "Your Name Updated Successfully");
                frame.dispose();
                StudentPage st = new StudentPage(ID);
            }
        } else if (Role == RoleT) {
            x = TeacherDao.UpdateFullName(newName, ID);
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Got some error");
            } else {
                JOptionPane.showMessageDialog(null, "Your Name Updated Successfully");
                frame.dispose();
                TeacherPage Te = new TeacherPage(ID);
            }
        } else {
            new project.Error("Error in change name", ID, Role);
        }
    }

    public static void ChangePassword(String Role, int ID, String newPass) {
        String email;
        if (Role == RoleA) {
            Admin ad = AdminDao.SelectAdminById(ID);
            email = ad.getEmail();
            int x = AccountDao.UpdatePassword(newPass, email);
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Error in Update Password");
            } else {
                JOptionPane.showMessageDialog(null, "Your Password Updated Successfully");
            }
        } else if (Role == RoleS) {
            Student st = StudentDao.SelectStudentById(ID);
            email = st.getEmail();
            int x = AccountDao.UpdatePassword(newPass, email);
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Got some error");
            } else {
                JOptionPane.showMessageDialog(null, "Your Password Updated Successfully");

            }
        } else if (Role == RoleT) {
            Teacher te = TeacherDao.SelectTeacherById(ID);
            email = te.getEmail();
            int x = AccountDao.UpdatePassword(newPass, email);
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Got some error");
            } else {
                JOptionPane.showMessageDialog(null, "Your Password Updated Successfully");
            }
        } else {
            new project.Error("Error in change password", ID, Role);
        }
    }

    public static void DeleteAccount(String Role, int ID) {
        String email;
        int x, x1;
        if (Role == RoleA) {
            Admin ad = AdminDao.SelectAdminById(ID);
            email = ad.getEmail();
            x = AdminDao.DeleteAdmin(ID);
            x1 = AccountDao.DeleteAccount(email);
            if (x == 0 && x1 == 0) {
                JOptionPane.showMessageDialog(null, "Got some error");
            } else {
                JOptionPane.showMessageDialog(null, "Account Deleted Successfully");
            }
        } else if (Role == RoleS) {
            Student St = StudentDao.SelectStudentById(ID);
            email = St.getEmail();
            x1 = AccountDao.DeleteAccount(email);
            x = StudentDao.DeleteStudent(ID);

            if (x == 0 && x1 == 0) {
                JOptionPane.showMessageDialog(null, "Got some error");
            } else {
                JOptionPane.showMessageDialog(null, "Account Deleted Successfully");

            }
        } else if (Role == RoleT) {
            Teacher te = TeacherDao.SelectTeacherById(ID);
            email = te.getEmail();
            x = TeacherDao.DeleteTeacher(ID);
            x1 = AccountDao.DeleteAccount(email);
            if (x == 0 && x1 == 0) {
                JOptionPane.showMessageDialog(null, "Got some error");
            } else {
                JOptionPane.showMessageDialog(null, "Account Deleted Successfully");
            }
        }
    }

    public static void ChangePicture(String Role, int ID, JFrame frame) {
        FileInputStream fis = null;
        File f = null;
        String fname = null;
        ReturnDifferentType.Im i = img.upload();
        fname = i.getFname();
        fis = i.getFis();
        f = i.getF();
        if (Role == RoleA) {
            int x = AdminDao.UpdatePicture(ID, fis, f);
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Got Some Error");
            } else {
                JOptionPane.showMessageDialog(null, "Profile updated Successfully!");
                frame.dispose();
                AdminPage a = new AdminPage(ID);
            }
        } else if (Role == RoleS) {
            int x = StudentDao.UpdatePicture(ID, fis, f);
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Got Some Error");
            } else {
                JOptionPane.showMessageDialog(null, "Profile updated Successfully!");
                frame.dispose();
                StudentPage st = new StudentPage(ID);
            }
        } else if (Role == RoleT) {
            int x = TeacherDao.UpdatePicture(ID, fis, f);
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Got Some Error");
            } else {
                JOptionPane.showMessageDialog(null, "Profile updated Successfully!");
                frame.dispose();
                TeacherPage Te = new TeacherPage(ID);
            }
        }
    }

    public static void ViewPicture(BufferedImage bufferedImage) {
        JOptionPane.showMessageDialog(null, new ImageIcon(bufferedImage), "Profile Picture", JOptionPane.PLAIN_MESSAGE);
    }

    public static BufferedImage SetProfilePicture(String Role, int ID, BufferedImage bufferedImage, JLabel usericon, JLabel lblUsername) {
        String fullName = null, phone = null, gender = "";
        byte[] bytImage = null;
        if (Role == RoleT) {
            Teacher a = TeacherDao.SelectFPById(ID);
            fullName = a.getFullName();
            lblUsername.setText(fullName);
            bytImage = a.getPicture();//get image as byte
            ReturnDifferentType.ImageBuffer IB = img.setProfileUser(bytImage, bufferedImage);
            ImageIcon ic = IB.getIc();
            usericon.setIcon(ic);
            bufferedImage = IB.getBufferedImage();
        } else if (Role == RoleS) {
            Student a = StudentDao.SelectFPById(ID);
            fullName = a.getFullName();
            lblUsername.setText(fullName);
            bytImage = a.getPicture();
            ReturnDifferentType.ImageBuffer IB = img.setProfileUser(bytImage, bufferedImage);
            ImageIcon ic = IB.getIc();
            usericon.setIcon(ic);
            bufferedImage = IB.getBufferedImage();
        } else if (Role == RoleA) {
            Admin a = AdminDao.SelectFPById(ID);
            fullName = a.getFullName();
            lblUsername.setText(fullName);
            bytImage = a.getPicture();
            ReturnDifferentType.ImageBuffer IB = img.setProfileUser(bytImage, bufferedImage);
            ImageIcon ic = IB.getIc();
            usericon.setIcon(ic);
            bufferedImage = IB.getBufferedImage();
        }
        return bufferedImage;
    }

    public static boolean login(String e, String p) {
        int currentID;
        boolean loginT = false;
        int i;
        if (!e.isEmpty() && !p.isEmpty()) {
            ArrayList<Account> array = AccountDao.SelectAccount();
            for (i = 0; i < array.size(); i++) {
                Account account = array.get(i);
                if (account.getEmail().equals(e)) {
                    if (account.getPassword().equals(p)) {
                        isExisted = true;
                        LoginPage.setvalidation("");
                        if (account.getRole().equals("s")) {
                            JOptionPane.showMessageDialog(null, "login Successfull--welcome Student!!");
                            Student st = StudentDao.SelectStudent(e);
                            currentID = st.getId();
                            StudentPage s = new StudentPage(currentID);
                            loginT = true;

                        } else if (account.getRole().equals("t")) {
                            JOptionPane.showMessageDialog(null, "login Successfull--welcome Teacher!!");
                            Teacher te = TeacherDao.SelectTeacher(e);
                            currentID = te.getId();
                            TeacherPage t = new TeacherPage(currentID);
                            loginT = true;

                        } else if (account.getRole().equals("a")) {
                            JOptionPane.showMessageDialog(null, "login Successfull--Welcome admin!");
                            Admin ad = AdminDao.SelectAdmin(e);
                            currentID = ad.getId();
                            AdminPage a = new AdminPage(currentID);
                            loginT = true;

                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid login");
                        }
                    } else {
                        LoginPage.setvalidation("Invalid Email or Password");
                    }
                } else {
                    LoginPage.setvalidation("Invalid Email or Password");
                }
            }
        } else {
            new JoptPane().displayGUI("icons/login-1.png", "TO LOGIN", "Please enter Email and Password!!", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION);
        }
        return loginT;
    }

    public static Boolean getIsExisted() {
        return isExisted;
    }

    public static void logout(String Role, int ID) {
        java.util.Date dt = new java.util.Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String last_Login = dateFormat.format(dt);
        String email;
        if (Role == RoleA) {
            Admin ad = AdminDao.SelectAdminById(ID);
            email = ad.getEmail();
            int x = AccountDao.UpdateLastTime(email, last_Login);
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Got an Error");
            } else {
                JOptionPane.showMessageDialog(null, "Loggin Out...");
                LoginPage loginFrame = new LoginPage();
                loginFrame.setVisible(true);
            }
        } else if (Role == RoleS) {
            Student St = StudentDao.SelectStudentById(ID);
            email = St.getEmail();
            int x = AccountDao.UpdateLastTime(email, last_Login);
            if (x == 0) {
                JOptionPane.showMessageDialog(null, "Got an Error");
            } else {
                JOptionPane.showMessageDialog(null, "Loggin Out...");
                LoginPage loginFrame = new LoginPage();
                loginFrame.setVisible(true);
            }
        } else if (Role == RoleT) {
            Teacher Te = TeacherDao.SelectTeacherById(ID);
            email = Te.getEmail();
            int x = AccountDao.UpdateLastTime(email, last_Login);
            if (x == 0) {
                new project.Error("Invalid logout", ID, Role);
            } else {
                JOptionPane.showMessageDialog(null, "Loggin Out...");
                LoginPage loginFrame = new LoginPage();
                loginFrame.setVisible(true);
            }
        } else {
            new project.Error("Error in logout", ID, Role);
        }
    }

    public static void SignUp(User u, String password, File f, FileInputStream fis) {
        java.util.Date date = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        int counter1, counter2;
        String Role = u.getRole();
        String fullName = u.getFullName();
        String email = u.getEmail();
        String phone = u.getPhone();
        String genderStr = u.getGender();
        if (Role == RoleA) {
            Admin admin = new Admin(fullName, email, phone, genderStr);
            Account account = new Account(email, password, sqlDate);
            counter1 = AccountDao.CountAccountByEmail(email);
            counter2 = AdminDao.CountAdminByEmail(email);
            if (counter1 == 0 && counter2 == 0) {
                int x = AdminDao.AddAdmin(admin, fis, f);
                int x1 = AccountDao.InsertIntoAccount(account, "Admin");
                if (x == 0 && x1 == 0) {
                    JOptionPane.showMessageDialog(null, "This User already exist");
                } else {
                    JOptionPane.showMessageDialog(null, " The account for admin is successfully created.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Duplicate entry for key Email!");
            }
        } else if (Role == RoleS || Role == "Student1") {
            Student st = new Student(fullName, email, phone, genderStr);
            Account account = new Account(email, password, sqlDate);
            int x = StudentDao.AddStudent(st, fis, f);
            int x1 = AccountDao.InsertIntoAccount(account, "Student");
            if (x == 0 && x1 == 0) {
                JOptionPane.showMessageDialog(null, "This is User already exist");
            } else {
                JOptionPane.showMessageDialog(null, " The account of Student is successfully created.");
            }
        } else if (Role == RoleT) {
            Account account = new Account(email, password, sqlDate);
            Teacher t = new Teacher(fullName, email, phone, genderStr);
            int x1 = AccountDao.InsertIntoAccount(account, "Teacher");;
            int x = TeacherDao.AddTeacher(t, fis, f);
            if (x == 0 && x1 == 0) {
                JOptionPane.showMessageDialog(null, "This is User already exist");
            } else {
                JOptionPane.showMessageDialog(null, " The account of Teacher is successfully created.");
            }
        }
    }
}
