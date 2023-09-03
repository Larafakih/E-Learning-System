package Student;

import Model.*;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Lara
 */
public class EditPost 
{
    int stdID;
    ForumMessage Fm, fmEdited;;
   
    JFrame other;

    public EditPost(int currentStdID, ForumMessage fm, JFrame oth) throws CloneNotSupportedException 
    {
        stdID = currentStdID;
        Fm = fm;
        other = oth;
         
        try {
            fmEdited = (ForumMessage)Fm.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        
         Post editWindow=new Post(stdID,other,fmEdited); 
         editWindow.FmToUpdate=fmEdited;
         editWindow.f.setTitle("Edit Post");
         editWindow.title.setText("Edit Your Post");
         editWindow.titleFd.setText(Fm.getTitle());
         editWindow.postTextArea.setText(Fm.getMessage());
         editWindow.postBtn.setText("Update");
    }
}
