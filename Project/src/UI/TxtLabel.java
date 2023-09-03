package UI;

import java.awt.*;
import javax.swing.*;
/**
 *
 * @author Salma
 */
public class TxtLabel extends JLabel{
    public TxtLabel() {

    }

    public TxtLabel(String text) {
        super(text);
    }

    @Override
    protected void paintComponent(Graphics g) {
        setForeground(new Color(0, 142, 204));
        setFont(new Font("Tahoma", Font.BOLD , 20));
        super.paintComponent(g);
    }
}

