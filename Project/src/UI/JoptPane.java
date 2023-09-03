package UI;

import javax.swing.*;
import project.*;

/**
 *
 * @author Salma
 */
public class JoptPane {

    public void displayGUI(String Path,String Title, String txt, int i1, int i2) {
        JOptionPane.showConfirmDialog(null, getPanel(Path,txt), Title,i1, i2);
    }
    public void displayGUI(String Title, String txt, int i1, int i2) {
        JOptionPane.showConfirmDialog(null,txt, Title,i1, i2);
    }
    private JPanel getPanel(String Path,String txt) {
        //String Path="icons/System.png";
        JPanel panel = new JPanel();
        JLabel label = new JLabel("" + txt);
        ImageIcon image = null;
        try {
            image = img.CreateImageIcon(Path, 100, 100);
        } catch (Exception mue) {
            mue.printStackTrace();
        }
        label.setIcon(image);
        panel.add(label);
        return panel;
    }
// new JoptPane().displayGUI( "Error", "AAAAAAAA",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
            
}
