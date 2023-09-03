package UI;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 *
 * @author Salma
 */
public class PasswrdField extends JPasswordField {
    public PasswrdField()
    { setEchoChar('*');
        
    }
    @Override
    protected void paintComponent(Graphics g) {

        Font fieldFont = new Font("Arial", Font.PLAIN, 23);
        setFont(fieldFont);
        setBackground(Color.white);
        setForeground(Color.BLACK);
        setColumns(19);
       
        setBorder(BorderFactory.createCompoundBorder(new CustomeBorder(), new EmptyBorder(new Insets(10, 25, 10, 30))));
        g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 13, 13);
        super.paintComponent(g);
    }

    @SuppressWarnings("serial")
    class CustomeBorder extends AbstractBorder {

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            super.paintBorder(c, g, x, y, width, height);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(12));
            g2d.setColor(new Color(238, 238, 238));
            g2d.drawRoundRect(x, y, width - 1, height - 1, 25, 25);
        }
    }
}
