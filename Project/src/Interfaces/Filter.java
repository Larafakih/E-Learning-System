package Interfaces;

import java.util.*;
import Model.*;
/**
 *
 * @author Salma
 */
public interface Filter {
    public ArrayList<Student> filterName(ArrayList<Student> users,String charsSearched);
}
