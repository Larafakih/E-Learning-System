package Controller;

import Interfaces.*;
import java.util.ArrayList;
import Model.Student;

/**
 *
 * @author Salma
 */
public class FilterName implements Filter {

    @Override
    public ArrayList<Student> filterName(ArrayList<Student> users, String charsSearched) {
        ArrayList<Student> u = new ArrayList<>();
        charsSearched = Student.capitalize(charsSearched);
        System.out.println("1: " + charsSearched);
        String n1 = Student.capitalize(charsSearched.split(" (?!.* )")[0]);
        String n2 = null;
        try {
            n2 = Student.capitalize(charsSearched.split(" (?!.* )")[1]);
            charsSearched = n1 + " " + n2;
        } catch (Exception e) {

        }
        System.out.println("" + n1 + " " + n2);
        System.out.println("2: " + charsSearched);
        for (Student user : users) {
            String full = user.getFullName();
            String first = user.getFirstName(full);
            String last = user.getLastname(full);
            if (full.startsWith(charsSearched)) {
                u.add(user);
            } else if (first.equals(charsSearched) || last.equals(charsSearched) || full.equals(charsSearched)) {
                u.add(user);
            }
        }
        return u;
    }
}
