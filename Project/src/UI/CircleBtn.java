package UI;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Salma
 */
public class CircleBtn extends JButton {
    
    public CircleBtn() {
        setContentAreaFilled(false);
    }
    
    public CircleBtn(String text) {
        super(text);
        setContentAreaFilled(false);
    }
    
    public CircleBtn(String text, String path, int i1, int i2) {
        super(text);
        setIcon(img.CreateImageIcon(path, i1, i2));
        setContentAreaFilled(false);
    }
    
    public CircleBtn(String path, int i1, int i2) {
        setIcon(img.CreateImageIcon(path, i1, i2));
        setContentAreaFilled(false);
    }

    public CircleBtn(String text, String txt) {
        super(text);
        setContentAreaFilled(false);
        setToolTipText(txt);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.BLUE);
            setBackground(new Color(0, 142, 204));
            setForeground(Color.WHITE);
            setFont(new Font(Font.SERIF, Font.BOLD, 13));
            setVerticalTextPosition(JButton.BOTTOM);
            setHorizontalTextPosition(JButton.CENTER);
        } else {
            g.setColor(getBackground());
            setBackground(new Color(0, 142, 204));
            setForeground(Color.WHITE);
            setFont(new Font(Font.SERIF, Font.BOLD, 13));
            setVerticalTextPosition(JButton.BOTTOM);
            setHorizontalTextPosition(JButton.CENTER);
        }
        g.fillRoundRect(0, 0, getSize().width - 2, getSize().height - 2, 13, 13);
        super.paintComponent(g);
    }
    
    @Override
    protected void paintBorder(Graphics g) {
        //g.setColor(getForeground());
        //g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 13, 13);
    }
    
}
