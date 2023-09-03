package UI;

import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Salma
 */
public class ValidLabel extends JLabel{

    public ValidLabel(String string) {
        super(string);
    }

    public ValidLabel() {
    }
    
     @Override
    protected void paintComponent(Graphics g) {
        setForeground(new Color(255,0,0));//(255, 63, 0)
        //setFont(new Font("Tahoma", Font.BOLD , 20));
        super.paintComponent(g);
    }
}
