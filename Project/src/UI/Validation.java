package UI;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

/**
 *
 * @author Salma
 */
//class for validation of FullName,Phone,Password, Email,Fullmark of quiz,duration of quiz,date of quiz
public class Validation {

    public static boolean isValidFullMarks(String str) {
        // interval [10-100]  // false: 1, 9, 101, 120
        // true: 100 10 99 90 80 20
        String expression = "^(([1-9])([0-9]))$";
        boolean s = str.matches(expression);
        if (str.equals("100")) {
            s = true;
        }
        return s;
    }

    public static boolean isValidFullname(String str) {
        String expression = "(\\b[A-Z]{1}[a-z]+)( )([A-Z]{1}[a-z]+\\b)";
        return str.matches(expression);
    }

    public static boolean isValidPhone(String str) {
        String expression = "(70|71|76|78|81|86)?[0-9]{6}";
        return str.matches(expression);
    }

    public static boolean isValidEmail(String str) {
        String expression = "[\\w]+@[\\w]+\\.[a-zA-Z]{2,3}";
        return str.matches(expression);
    }

    public static boolean isValidPassword(String password) {
        boolean isValid = true;
        if (password.length() > 15 || password.length() < 8) {
            JOptionPane.showMessageDialog(null, "Password must be less than 20 and more than 8 characters in length.");
            isValid = false;
        }
        String upperCaseChars = "(.*[A-Z].*)";
        String lowerCaseChars = "(.*[a-z].*)";
        String numbers = "(.*[0-9].*)";
        String specialChars = "(.*[@,#,$,%].*$)";
        if (!password.matches(upperCaseChars) || !password.matches(lowerCaseChars) || !password.matches(numbers) || !password.matches(specialChars)) {
            JOptionPane.showMessageDialog(null, "Password must have \n atleast one uppercase character \n atleast one lowercase character \n  atleast one number \n  atleast one special character among @#$%");
            isValid = false;
        }
        return isValid;
    }

    public static void IfEmail(JTextField JTextFieldEmail, JLabel emailValidation) {
        String emailTxt = JTextFieldEmail.getText();
        if (emailTxt.isEmpty()) {
            emailValidation.setText("Please Enter Email");
        } else {
            boolean valid = isValidEmail(emailTxt);
            if (!valid) {
                emailValidation.setText("Invalid Email!!");
            } else {
                emailValidation.setText("");
            }
        }
    }

    public static void IfFullName(JTextField JTextFieldName, JLabel fnameValidation) {
        String fName = JTextFieldName.getText();
        if (fName.isEmpty()) {
            fnameValidation.setText("Please Enter First Name");
        } else {
            boolean valid = isValidFullname(fName);
            if (!valid) {
                fnameValidation.setText("Invalid First Name!!");
            } else {
                fnameValidation.setText("");
            }
        }
    }

    public static void IfPhone(JTextField JTextFieldPhone, JLabel phoneValidation) {
        String phoneTxt = JTextFieldPhone.getText();
        if (phoneTxt.isEmpty()) {
            phoneValidation.setText("Please Enter Phone");
        } else {
            boolean valid = Validation.isValidPhone(phoneTxt);
            if (!valid) {
                JOptionPane.showMessageDialog(null, "Begins with 70 or 71 or 76 or 78 or 81 or 86  \n Then contains 6 digits");
                phoneValidation.setText("Invalid phone number!");
            } else {
                phoneValidation.setText("");
            }
        }
    }

    public static void IfFullmarks(JTextField JTextField, JLabel LValidation) {
        String Txt = JTextField.getText();
        if (Txt.isEmpty()) {
            LValidation.setText("Please Enter Full mark");
            //System.out.println("empty");
        } else {
            boolean valid = Validation.isValidFullMarks(Txt);
            if (!valid) {
                //JOptionPane.showMessageDialog(null, "Between 10 and 100 \n ");
                LValidation.setText("Invalid full mark!");
            } else {
                LValidation.setText("");
            }
        }
    }

    public static void IfPassword(JTextField JTextFieldPass, JLabel passwordValidation) {
        String pass = JTextFieldPass.getText();
        if (pass.isEmpty()) {
            passwordValidation.setText("PLease Enter Password");
        } else {
            boolean valid = Validation.isValidPassword(pass);
            if (!valid) {
                passwordValidation.setText("Invalid password!");
            } else {
                passwordValidation.setText("");
            }
        }
    }

    public static void IfDate(Object o, JLabel datevalidattion) {
        try {
            Date quizDate = (Date) o;
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            Date d2 = format1.parse(LocalDate.now().getYear() + "-" + LocalDate.now().getMonthValue() + "-" + LocalDate.now().getDayOfMonth());
            if (quizDate.compareTo(d2) > 0) {
                datevalidattion.setText("");
            } else if (quizDate.compareTo(d2) < 0) {
                datevalidattion.setText("Date occurs before Date of this days");
            } else if (quizDate.compareTo(d2) == 0) {
                datevalidattion.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void IfDuration(Object D, JLabel durationvalidattion) {
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");
        String timeStr = format2.format(D);
        String time2 = "03:30:00";
        String time3 = "00:00:00";
        if (timeStr.equals(time2)) {
            durationvalidattion.setText("");
        } else if (timeStr.compareTo(time2) > 0 || timeStr.compareTo(time3) == 0) {
            durationvalidattion.setText("Duration must be less than 3 hours and cannot be 00:00:00");
        } else if (timeStr.compareTo(time2) < 0) {
            durationvalidattion.setText("");
        }
    }


}
